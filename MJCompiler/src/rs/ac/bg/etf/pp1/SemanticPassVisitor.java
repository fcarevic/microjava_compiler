package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;


import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
public class SemanticPassVisitor extends VisitorAdaptor {
	private static Struct boolStruct  = new Struct(Struct.Bool);
	
	
	
	public static void init() {
		Tab.init();
		Tab.insert(Obj.Type, "bool", boolStruct);
	}
	
	
	boolean errorDetected  =false;
	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	
	
	
	@Override
	public void visit(Program program) {
			Tab.chainLocalSymbols(Tab.find(program.getProgName().getProgName()));
			Tab.closeScope();
	
	}
	
	
	@Override
	public void visit(ProgName ProgName) {
		Tab.insert(Obj.Prog, ProgName.getProgName(), Tab.noType);
		Tab.openScope();
	}
	///////////////////////////////////////////////////////////////
	
	
	@Override
	public void visit(Type type) {
		Obj obj = Tab.find(type.getTypeName()); 
		if(obj==Tab.noObj) {
				report_error("Nije pronadjen tip u tabeli simbola : " + type.getTypeName(), type);
					type.struct=Tab.noType;
		}
		else {
			if(Obj.Type== obj.getKind()) {
				type.struct = obj.getType();
			}
			else {
				report_error("Ne postoji tip : " + type.getTypeName(), type);
				type.struct=Tab.noType;
				
			}
		}
		
			
	}
	
	/** 	CONST TYPE DECLARATIONS  **/
	
	private List<ConstInit> constNameList= new ArrayList<ConstInit>();
	
	@Override
	public void visit(ConstDeclNoMethodDecl constList) {
			Struct type = constList.getConstDecl().getType().struct;
			
			for(ConstInit constInit : constNameList) {
				
				if(type != constInit.getStdConstType().struct || type == Tab.noType) {
					report_error("Ne poklapaju se tipovi i inicijalna vrednost konstante : " + constInit.getName(), constInit);
					continue;
				}
				Obj obj = Tab.insert(Obj.Con, constInit.getName(), type);
				
				if( type == boolStruct) {
					StdConstBool boolVal =(StdConstBool)(constInit.getStdConstType());
					//obj.setAdr(boolVal.get);
				} else if(type == Tab.intType) {
						StdConstNumber numVal = (StdConstNumber)(constInit.getStdConstType());
						obj.setAdr(numVal.getVal());
				} else if(type == Tab.charType) {
					StdConstChar charVal = (StdConstChar)(constInit.getStdConstType());
					obj.setAdr(charVal.getVal());
					
				} 
			}
			constNameList.clear();
	
	}
	
	@Override
	public void visit(ConstInit constInit) {
		
		Obj obj = Tab.find(constInit.getName());
		if(obj != Tab.noObj) {
			report_error("Postoji vec deklaracija za ime : " + constInit.getName(), null);
			constInit.obj= Tab.noObj;
		 return;
		}
		constNameList.add(constInit);
		
	}
	
	@Override
	public void visit(StdConstChar StdConstChar) {
		StdConstChar.struct=Tab.charType;
	}
	
	@Override
	public void visit(StdConstString StdConstString) {
		System.err.println("NEMA STRING");
	}
	@Override
	public void visit(StdConstNumber StdConstNumber) {
		
		StdConstNumber.struct = Tab.intType;
	}
	
	@Override
	public void visit(StdConstBool stdConstBool) {
			stdConstBool.struct = Tab.find("bool").getType();
	}
	//////////////////////////////////////////////////////////////////
	
	
	/**		VARIABLE DECLARATIONS      **/
	
	private List<VarInit> variableNames = new ArrayList<VarInit>();
	
	@Override
	public void visit(VarInitArray varInitArray) {
		Obj obj = Tab.currentScope.findSymbol(varInitArray.getName());
		if(obj!= null) {
			report_error("Vec postoji deklarisano ime : " + varInitArray.getName(), varInitArray);
			return;
		}
		variableNames.add(varInitArray);
	}
	
	@Override
	public void visit(VarInitPrimitive varInitPrimitive) {
	
		Obj obj = Tab.currentScope.findSymbol(varInitPrimitive.getName());
		if(obj!= null) {
			report_error("Vec postoji deklarisano ime : " + varInitPrimitive.getName(), varInitPrimitive);
			return;
		}
		variableNames.add(varInitPrimitive);
	}
	
	@Override
	public void visit(VarDecl_ varDecl) {
		
		Struct type = varDecl.getType().struct;
		int objKind = Obj.Var;
		if(!currentMethod && currentClassCount>0) objKind = Obj.Fld; 
		for(VarInit varInit : variableNames) {
			
			if(varInit instanceof VarInitPrimitive) {
				
				Obj obj = Tab.insert(objKind, ((VarInitPrimitive) varInit).getName(), type);
			} else if( varInit instanceof VarInitArray) {
				Obj obj = Tab.insert(objKind, ((VarInitArray) varInit).getName(), new Struct(Struct.Array, type));
				
			} else {
				System.err.println("VARIJABLA TIP NEDEFINISAN");
			}
			
		}
	
		variableNames.clear();
	}
	/////////////////////////////////////////////////////
	
	/*  CLASS DECLARATIONS */
	
	private int currentClassCount = 0;
	private List<Struct> currentClassStruct=new ArrayList<Struct>();
	
	
	
	@Override
	public void visit(ClassDeclsNoMethod classDecl) {
		currentClassCount--;
		currentClassStruct.remove(currentClassStruct.size()-1);
		if(classDecl.getClassName().obj != Tab.noObj ) {
			Struct extendsType = classDecl.getExtendsClause().struct;
			classDecl.getClassName().obj.getType().setElementType(extendsType);
			Tab.chainLocalSymbols(classDecl.getClassName().obj.getType());
		}	
	Tab.closeScope();
	}
	
	
	
	
	@Override
	public void visit(ClassDeclMethod classDecl) {
		currentClassCount--;
		currentClassStruct.remove(currentClassStruct.size()-1);
		if(classDecl.getClassName().obj != Tab.noObj ) {
			Struct extendsType = classDecl.getExtendsClause().struct;
			classDecl.getClassName().obj.getType().setElementType(extendsType);
			Tab.chainLocalSymbols(classDecl.getClassName().obj.getType());
		
		}	
	Tab.closeScope();
	}
	
	
	
	
	@Override
	public void visit(ClassName className) {
		currentClassCount++;
		Obj obj = Tab.find(className.getClassName());
		if(obj!= Tab.noObj) {
			report_error("Postoji vec deklarisano ime za klasu : " + className.getClassName(), className);
			className.obj = Tab.noObj;
			currentClassStruct.add(null);
			Tab.openScope();
			return;
		}
		className.obj=Tab.insert(Obj.Type, className.getClassName(), new Struct(Struct.Class));
		currentClassStruct.add(className.obj.getType());
		Tab.openScope();
	}
	
	@Override
	public void visit(ExtendsClause_ extendsClause) {
		extendsClause.struct = extendsClause.getType().struct;
	}
	
	@Override
	public void visit(NoExtendsClause noExtendsClause) {
		noExtendsClause.struct = Tab.noType;
	}
	//////////////////////////////////////////////////////////////////
	

	/** 		METHOD DECLARATIONS     **/
	
	private Struct returnType;
	private boolean currentMethod=false;
	private int numberOfFormalParams=0;
	
	@Override
	public void visit(ReturnTypeType returnType) {
		returnType.struct = returnType.getType().struct;
		this.returnType= returnType.struct;
		currentMethod=true;
		
		
	}
	
	@Override
	public void visit(ReturnTypeVoid returnTypeVoid) {
		returnTypeVoid.struct = Tab.noType;
		this.returnType = returnTypeVoid.struct;
		
	}
	
	@Override
	public void visit(FormalParamPrimitive formalParam) {
		numberOfFormalParams++;
		Obj o = Tab.currentScope.findSymbol(formalParam.getParamName());
		if(o!= null) {
			report_error("Formalni parametar vec postoji + " + formalParam.getParamName() , formalParam);
			return;
		}
		Tab.insert(Obj.Var, formalParam.getParamName(), formalParam.getType().struct);
	}
	
	@Override
	public void visit(FormalParamArray formalParam) {
		numberOfFormalParams++;
		Obj o = Tab.currentScope.findSymbol(formalParam.getParamName());
		if(o!= null) {
			report_error("Formalni parametar vec postoji + " + formalParam.getParamName() , formalParam);
			return;
		}
		Tab.insert(Obj.Var, formalParam.getParamName(), new Struct(Struct.Array, formalParam.getType().struct));
		
	
	}
	
	@Override
	public void visit(MethodName methodName) {
		numberOfFormalParams=0;
		Obj obj = Tab.currentScope.findSymbol(methodName.getMethodName());
		if(obj != null) {
			report_error("Vec postoji tip sa deklarisanim imenom kao metoda : "+ methodName.getMethodName() + " ", methodName);
			methodName.obj=null;
		} else {
			methodName.obj = Tab.insert(Obj.Meth, methodName.getMethodName(), returnType);
		}
		
		Tab.openScope();
		
		if(currentClassCount>0 && currentClassStruct.get(currentClassStruct.size()-1)!=null) {
			Tab.insert(Obj.Var, "this", currentClassStruct.get(currentClassStruct.size()-1));	
		}
		
	}
	
	@Override
	public void visit(MethodDecl methodDecl) {
		
		currentMethod=false;
		Obj method = methodDecl.getMethodName().obj;
		if(method!= null) {
			method.setLevel(numberOfFormalParams);
			Tab.chainLocalSymbols(method);
		}
		Tab.closeScope();
	}
	
	

	/******* SEMANTIC RETURN CHECK ***************************/
	
	
	
	@Override
	public void visit(ReturnStatementExpr returnExpr) {
		if(returnType == Tab.noType) {
			report_error("Funkcija je deklarisana kao void, a return ima expr ", returnExpr);
			return;
		}
		if( !returnExpr.getExpr().struct.assignableTo(returnType))  {
			report_error(" Tip return expessiona nije moguce dodeliti tipu povratne vrednosi ", returnExpr);
		}
	}
	
	@Override
	public void visit(ReturnStatementNoExpr returnStatementNoExpr) {
			if(returnType!=Tab.noType) {
				report_error("Missing return expression statement", returnStatementNoExpr);
				
			}
	}
	
	
	/******* SEMANTIC CHECK ***************************/
	
	@Override
	public void visit(FactorBool factorBool) {
		factorBool.struct = boolStruct;
	}
	
	@Override
	public void visit(FactorChar factorChar) {
		factorChar.struct= Tab.charType;
	}
	
	@Override
	public void visit(FactorFunctionCall funcCall) {
		if(funcCall.getDesignator().obj.getKind() != Obj.Meth) {
				report_error("Ime " + funcCall.getDesignator().getDesignatorName().getName() + "nije funkcija ", funcCall);
				funcCall.struct = Tab.noType;
				return;
		}
		checkActualParameterFuncCall(funcCall.getDesignator().obj, funcCall.getActualParameterList(), funcCall);
		funcCall.struct = getFinalTypeForDesignator(funcCall.getDesignator());
		
	}
	
	@Override
	public void visit(FactorDesignator factorDesignator) {
		factorDesignator.struct = getFinalTypeForDesignator(factorDesignator.getDesignator());
	}
	
	@Override
	public void visit(FactorNumber factorNumber) {
		// TODO Auto-generated method stub
		factorNumber.struct= Tab.intType;
	}
	@Override
	public void visit(FactorNew factorNew) {
		// TODO Auto-generated method stub
		factorNew.struct = factorNew.getType().struct;
	}
 	
	@Override
	public void visit(FactorNewExpr factorNewExpr) {
		factorNewExpr.struct = new Struct(Struct.Array, factorNewExpr.getType().struct);
	}
	
	@Override
	public void visit(FactorExpr factorExpr) {
	  factorExpr.struct = factorExpr.getExpr().struct;
	}
	
	@Override
	public void visit(TermMul termMul) {
	  Struct leftPart = termMul.getTerm().struct;
	  Struct rightPart = termMul.getFactor().struct;
	  if(leftPart == rightPart && leftPart== Tab.intType) {
		  termMul.struct = Tab.intType;
		  return;
	  }
	  report_error("Nekompatibilni tipovi prilikom mnozenja ", termMul);
	  termMul.struct = Tab.noType;
	  
	  
	}
	
	@Override
	public void visit(TermNoMul termNoMul) {
		termNoMul.struct = termNoMul.getFactor().struct;
	}
	
	
	@Override
	public void visit(AddopEmptyList addopEmptyList) {
		addopEmptyList.struct = Tab.noType;
		
	}
	
	@Override
	public void visit(AddopMultipleList addopMultipleList) {
		// TODO Auto-generated method stub
		if(addopMultipleList.getAddopList() instanceof AddopEmptyList ) {
			addopMultipleList.struct = addopMultipleList.getTerm().struct;
			return;
		} else {
			if(addopMultipleList.getTerm().struct == Tab.intType && addopMultipleList.getAddopList().struct == Tab.intType)
				addopMultipleList.struct = Tab.intType;
			else 
				addopMultipleList.struct = Tab.noType;
		}
		
	}
	
	@Override
	public void visit(ExprTermMinus exprTermMinus) {
		if(exprTermMinus.getAddopList()instanceof AddopEmptyList) {
			exprTermMinus.struct = ( exprTermMinus.getTerm().struct == Tab.intType? Tab.intType : Tab.noType);
		}
		else {
			Struct leftPart = exprTermMinus.getTerm().struct;
			Struct rightPart = exprTermMinus.getAddopList().struct;
			if(leftPart==rightPart && leftPart == Tab.intType) {
				exprTermMinus.struct = Tab.intType;
			} else {
				report_error("Nekompatibilni tipovi prilikom sabiranja ", exprTermMinus);
				exprTermMinus.struct = Tab.noType;
			}
		}
	
	}
	
	
	@Override
	public void visit(ExprTermNoMinus exprTermNoMinus) {
		if(exprTermNoMinus.getAddopList() instanceof AddopEmptyList) {
			exprTermNoMinus.struct =  exprTermNoMinus.getTerm().struct;
		}
		else {
			Struct leftPart = exprTermNoMinus.getTerm().struct;
			Struct rightPart = exprTermNoMinus.getAddopList().struct;
			if(leftPart==rightPart && leftPart == Tab.intType) {
				exprTermNoMinus.struct = Tab.intType;
			} else {
				report_error("Nekompatibilni tipovi prilikom sabiranja ", exprTermNoMinus);
				exprTermNoMinus.struct= Tab.noType;
			}
		}
	
	}
	
	/***  DESIGNATOR STATEMENT CHECKS****/
	
	@Override
	public void visit(DesignatoStatementDec designatoStatementDec) {
		if(getFinalTypeForDesignator(designatoStatementDec.getDesignator()) != Tab.intType) {
			report_error("Nije tip int za dec ", designatoStatementDec);
		}
	}

	@Override
	public void visit(DesignatorStatementInc designatorStatementInc) {
				if(getFinalTypeForDesignator(designatorStatementInc.getDesignator()) != Tab.intType) {
					report_error("Nije tip int za inc ", designatorStatementInc);
					
				}
	}
	
	@Override
	public void visit(DesignatorStatementAssignop statement) {
		
		if(!statement.getExpr().struct.assignableTo(getFinalTypeForDesignator(statement.getDesignator()))) {
			report_error("Neispravna tipizacija za dodelu vrednosti ", statement);
			
		}
	
	}
	
	@Override
	public void visit(DesignatorStatementFuncCall designatorFuncCall) {
		
		String name = getLastDesignatorNameInChaining(designatorFuncCall.getDesignator());
		Obj mth = Tab.find(name);
		if(mth.getKind() != Obj.Meth) {
				report_error("Ime " + name + " nije funkcija ", designatorFuncCall);
				return;
		}
		
		checkActualParameterFuncCall(mth, designatorFuncCall.getActualParameterList(), designatorFuncCall);
	}
	
	private boolean checkActualParameterFuncCall(Obj mth, ActualParameterList paramList, SyntaxNode info) {
		List<Obj> localSymbols = mth.getLocalSymbols().stream().collect(Collectors.toList());
		
		boolean classMethod = false;
		
		if((paramList instanceof ActualParameterEmptyList)) {
			if(mth.getLevel()>1 || mth.getLevel()==1 && ! localSymbols.get(0).getName().equals("this")) {
			report_error("Previse parametara specificirano za fju " + mth.getName() + " ", info);
			return false;
			}
			return true;
			
		}	
		
		
		
		ActualParams param = ((ActualParameterList_)paramList).getActualParams();
		
		for(int i= 0 ; i<mth.getLevel(); i++) {
		
					if(localSymbols.get(i).getName().equals("this") && i==0)
					{
						classMethod = true;
						continue;
					}
					 if (param instanceof ActualParameterSingle) {
						ActualParameterSingle singleParam = (ActualParameterSingle) param;
						if(!singleParam.getExpr().struct.assignableTo(localSymbols.get(i).getType())) {
							report_error(" Tip parametra " + (classMethod? i: (i+1)) + "( " + localSymbols.get(i).getName()+ ") nije odgovarajuci" , info);
							return false;			
						}
						
						if(i!= mth.getLevel()-1) {
							
							report_error(" Premalo parametara ,  fja  " + mth.getName()+ " zahteva "  + (classMethod? mth.getLevel()-1 : mth.getLevel()) + " parametara ", info);
							return false;			
						}
						return true;
					}
					
					else if(param instanceof ActualParameterMultipleList) {
						ActualParameterMultipleList multList = (ActualParameterMultipleList) param;
						if(!multList.getExpr().struct.assignableTo(localSymbols.get(i).getType())) {
							report_error(" Tip parametra " + (classMethod? i: (i+1)) + "( " + localSymbols.get(i).getName()+ ") nije odgovarajuci" , info);
						return false;			
					}
							param = multList.getActualParams();
					}
					else {
						report_error(" GRESKA " , info);
						return false;	
					}
				}
		
	
		report_error("Previse parametara za fju " + mth.getName(), info);
		return false;
		
	}
	
	
	/** 	DESIGNATOR CHAINING CHECK		**/
	private Map<String, Struct> designatorTypeChainingFinals = new HashMap<String, Struct>();
	Obj currentDesignator= null;
	Struct currentDesignatorType = null;
	
	
	private Struct getFinalTypeForDesignator(Designator designator) {
	    visit(designator);
	    return currentDesignatorType;
	}
	
	
	@Override
	public void visit(Designator designator) {
		
		currentDesignator = Tab.find(designator.getDesignatorName().getName());
		if(currentDesignator == Tab.noObj) return;
		currentDesignatorType = currentDesignator.getType();
		DesignatorOptionList optList = designator.getDesignatorOptionList();
		do {
			 
			if( optList instanceof DesignatorOptionEmptyList) 
				break;
			else 
			{
				DesignatorOptionList_ list = (DesignatorOptionList_) optList;
				DesignatorOption option = list.getDesignatorOption();
					if(option instanceof DesignatorDotIdentOption) {
						checkDesignatorDotIdentOption((DesignatorDotIdentOption) option);
					} else 
						if (option instanceof DesignatorIndexingOption)
							checkDesignatorIndexingOption((DesignatorIndexingOption)option);
				
				optList = list.getDesignatorOptionList();
			}
			
		}while(currentDesignatorType!=Tab.noType);
	  designator.obj=currentDesignator;	
		
	}
	
	@Override
	public void visit(DesignatorName designatorName) {
		Obj obj  = Tab.find(designatorName.getName());
		if(obj==Tab.noObj) {
			report_error("Nije definisano ime " + designatorName.getName() + " ", designatorName);
		}		
	}
	

	
	@Override
	public void visit(DesignatorDotIdentOption nextDesignator) {
		
		if(Tab.find(nextDesignator.getName())==Tab.noObj) {
			
			report_error("Ime " + nextDesignator.getName() + "  nije definisano  ", nextDesignator);
		}
	}
	
	/** *
	 * NE RADIS INDEKSIARNJE, VEC SAMO SEMANTICKU PROVERUUUU !!!!!!
	 * 
	 * */
	@Override
	public void visit(DesignatorIndexingOption designatorIndexingOption) {
		
		if(designatorIndexingOption.getExpr().struct != Tab.intType) {
			report_error("Indeks nije nije tipa int ", designatorIndexingOption);
			
			
		}
	}
	
	
	private void checkDesignatorDotIdentOption(DesignatorDotIdentOption nextDesignator) {
		
		if(currentDesignatorType==Tab.noType) return;
		
		if(currentDesignatorType.getKind() != Struct.Class) {
			report_error("Ime " +  currentDesignator.getName() + " nije objekat klase", nextDesignator);
			currentDesignator=Tab.noObj;
			currentDesignatorType = Tab.noType;
			return;
			
		}
		Obj nextDes  = Tab.find(nextDesignator.getName());
		
		for(Obj obj : currentDesignatorType.getMembers()) {
			if(obj.getName().equals(nextDes.getName())) {
				if(nextDes.getKind() != Obj.Fld) {
					report_error("Ime " +  nextDesignator.getName() + " nije polje klase ", nextDesignator);
					currentDesignator=Tab.noObj;
					currentDesignatorType = Tab.noType;
					return;
				} else {
					currentDesignator= nextDes;
					currentDesignatorType = nextDes.getType();
				}
			}
		}
		report_error("Ime " + nextDesignator.getName() +" nije polje objekta " + currentDesignator.getName()+"  ", nextDesignator);
		currentDesignator = Tab.noObj;
		currentDesignatorType = Tab.noType;
		
	}
	
	private void checkDesignatorIndexingOption(DesignatorIndexingOption DesignatorIndexingOption) {
		
		
		if(currentDesignator == Tab.noObj) return;
			if(currentDesignatorType == Tab.noType) return;
			if(currentDesignatorType.getKind() != Struct.Array ) {
				report_error("Ime " + currentDesignator.getName() + " nije niz ", DesignatorIndexingOption);
				currentDesignator=Tab.noObj;
				currentDesignatorType = Tab.noType;
				return;
			}
			currentDesignatorType  = currentDesignatorType.getElemType() ;
			
	}
	private String getLastDesignatorNameInChaining(Designator designator) {
		String name = "";
		
		name = designator.getDesignatorName().getName();
		
		DesignatorOptionList optList = designator.getDesignatorOptionList();
		
		while(! (optList instanceof DesignatorOptionEmptyList)) {
			DesignatorOption option = ((DesignatorOptionList_)optList).getDesignatorOption();
			if(option instanceof DesignatorDotIdentOption)
				name = ((DesignatorDotIdentOption)option).getName();
		}
		return name;
	}
	
public boolean isSuccess() {
	return !errorDetected;
}
	
}
