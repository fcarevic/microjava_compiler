package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;

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
		Obj o = Tab.currentScope.findSymbol(formalParam.getParamName());
		if(o!= null) {
			report_error("Formalni parametar vec postoji + " + formalParam.getParamName() , formalParam);
			return;
		}
		Tab.insert(Obj.Var, formalParam.getParamName(), formalParam.getType().struct);
	}
	
	@Override
	public void visit(FormalParamArray formalParam) {
		Obj o = Tab.currentScope.findSymbol(formalParam.getParamName());
		if(o!= null) {
			report_error("Formalni parametar vec postoji + " + formalParam.getParamName() , formalParam);
			return;
		}
		Tab.insert(Obj.Var, formalParam.getParamName(), new Struct(Struct.Array, formalParam.getType().struct));
		
	
	}
	
	@Override
	public void visit(MethodName methodName) {
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
				report_error("Missing return statement", returnStatementNoExpr);
				
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
		funcCall.struct = currentDesignatorType;
		
	}
	
	@Override
	public void visit(FactorDesignator factorDesignator) {
		factorDesignator.struct = currentDesignatorType;
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
	
	private boolean wasAddopEmptyList = false;
	
	@Override
	public void visit(AddopEmptyList addopEmptyList) {
		addopEmptyList.struct = Tab.noType;
		wasAddopEmptyList=true;
	}
	
	@Override
	public void visit(AddopMultipleList addopMultipleList) {
		// TODO Auto-generated method stub
		if(wasAddopEmptyList ) {
			addopMultipleList.struct = addopMultipleList.getTerm().struct;
			wasAddopEmptyList=false;
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
		if(wasAddopEmptyList) {
			wasAddopEmptyList=false;
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
		if(wasAddopEmptyList) {
			wasAddopEmptyList=false;
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
	
	/** 	DESIGNATOR CHAINING CHECK		**/
	
	private Obj currentDesignator= null;
	private Struct currentDesignatorType = null;
	
	@Override
	public void visit(Designator designator) {
		designator.obj=currentDesignator;
		
	}
	
	@Override
	public void visit(DesignatorName designatorName) {
		System.err.println("DESIGNATOR NAME");
		
		Obj obj  = Tab.find(designatorName.getName());
		if(obj==Tab.noObj) {
			report_error("Nije definisano ime " + designatorName.getName() + " ", designatorName);
			currentDesignator = Tab.noObj;
			currentDesignatorType = Tab.noType;
			return;
		}
		
		currentDesignator=obj;
		currentDesignatorType = currentDesignator.getType();
	}
	

	
	@Override
	public void visit(DesignatorDotIdentOption nextDesignator) {
		
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
	
	/** *
	 * NE RADIS INDEKSIARNJE, VEC SAMO SEMANTICKU PROVERUUUU !!!!!!
	 * 
	 * */
	@Override
	public void visit(DesignatorIndexingOption DesignatorIndexingOption) {
		System.err.println("DESIGNATOR INDEXING");	
		
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
	
	
	

}
