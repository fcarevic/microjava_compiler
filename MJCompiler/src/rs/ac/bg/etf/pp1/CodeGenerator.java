package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import rs.ac.bg.etf.pp1.ast.AddopMultipleList;
import rs.ac.bg.etf.pp1.ast.BreakStatement;
import rs.ac.bg.etf.pp1.ast.Case;
import rs.ac.bg.etf.pp1.ast.CaseClause;
import rs.ac.bg.etf.pp1.ast.CaseList;
import rs.ac.bg.etf.pp1.ast.CaseList_;
import rs.ac.bg.etf.pp1.ast.CaseSingle;
import rs.ac.bg.etf.pp1.ast.ClassDeclMethod;
import rs.ac.bg.etf.pp1.ast.ConditionAND;
import rs.ac.bg.etf.pp1.ast.ConditionFactExprRelop;
import rs.ac.bg.etf.pp1.ast.ConditionOR;
import rs.ac.bg.etf.pp1.ast.ContinueStatement;
import rs.ac.bg.etf.pp1.ast.DesignatoStatementDec;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorDotIdentOption;
import rs.ac.bg.etf.pp1.ast.DesignatorFunc;
import rs.ac.bg.etf.pp1.ast.DesignatorIndexingOption;
import rs.ac.bg.etf.pp1.ast.DesignatorName;
import rs.ac.bg.etf.pp1.ast.DesignatorOptionEmptyList;
import rs.ac.bg.etf.pp1.ast.DesignatorOptionList_;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementAssignop;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementFuncCall;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementInc;
import rs.ac.bg.etf.pp1.ast.Div;
import rs.ac.bg.etf.pp1.ast.DoClause;
import rs.ac.bg.etf.pp1.ast.DoWhileCondition;
import rs.ac.bg.etf.pp1.ast.DoWhileStatement;
import rs.ac.bg.etf.pp1.ast.Else;
import rs.ac.bg.etf.pp1.ast.FactorBool;
import rs.ac.bg.etf.pp1.ast.FactorChar;
import rs.ac.bg.etf.pp1.ast.FactorDesignator;
import rs.ac.bg.etf.pp1.ast.FactorFunctionCall;
import rs.ac.bg.etf.pp1.ast.FactorNew;
import rs.ac.bg.etf.pp1.ast.FactorNewExpr;
import rs.ac.bg.etf.pp1.ast.FactorNumber;
import rs.ac.bg.etf.pp1.ast.GreaterEqual;
import rs.ac.bg.etf.pp1.ast.GreaterThen;
import rs.ac.bg.etf.pp1.ast.IfCondition;
import rs.ac.bg.etf.pp1.ast.IfElseStatement;
import rs.ac.bg.etf.pp1.ast.IfStatement;
import rs.ac.bg.etf.pp1.ast.LessEqual;
import rs.ac.bg.etf.pp1.ast.LessThen;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodName;
import rs.ac.bg.etf.pp1.ast.Mul;
import rs.ac.bg.etf.pp1.ast.NoPrintNumConst;
import rs.ac.bg.etf.pp1.ast.NotSame;
import rs.ac.bg.etf.pp1.ast.Plus;
import rs.ac.bg.etf.pp1.ast.PrintNumConst_;
import rs.ac.bg.etf.pp1.ast.PrintStatement;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.ReadStatement;
import rs.ac.bg.etf.pp1.ast.Relop;
import rs.ac.bg.etf.pp1.ast.Rem;
import rs.ac.bg.etf.pp1.ast.ReturnStatement_;
import rs.ac.bg.etf.pp1.ast.Same;
import rs.ac.bg.etf.pp1.ast.SwitchClause;
import rs.ac.bg.etf.pp1.ast.SwitchStatement;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.TermMul;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	
	
	@Override
	public void visit(Program program) {
		// TODO Auto-generated method stub
		program.traverseBottomUp(new ClassDeclVisitorForPoly());
	
	}
	
	
	@Override
	public void visit(ReadStatement readStatement) {
		if(readStatement.getDesignator().obj.getType()==Tab.charType) {
			Code.put(Code.bread);
		} else Code.put(Code.read);
		Code.store(readStatement.getDesignator().obj);
	}
	
	/*** PRINT ****/
	@Override
	public void visit(PrintNumConst_ numConst) {
		Code.loadConst(numConst.getVal());
	}
	@Override
	public void visit(NoPrintNumConst NoPrintNumConst) {
		Code.loadConst(1);
	}
	
	@Override
	public void visit(PrintStatement printStatement) {
			if(printStatement.getPrintExpr().getExpr().struct==Tab.intType) {
				Code.put(Code.print);
			}
			else {
				Code.put(Code.bprint);
			}
	}
	
	
	
	/*** METHODS ****/
	
	@Override
	public void visit(MethodName methodName) {
		//System.out.println(methodName.getMethodName());
		methodName.obj.setAdr(Code.pc);
		if(methodName.obj.getLevel()==0 && methodName.obj.getName().equals("main")) {
			Code.mainPc = methodName.obj.getAdr();
		}
		
		Code.put(Code.enter);
		Code.put(methodName.obj.getLevel());
		Code.put(methodName.obj.getLocalSymbols().size());
	}
	
	@Override
	public void visit(MethodDecl MethodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(FactorFunctionCall factorFunctionCall) {
			
			if(!callClassMethod(factorFunctionCall.getDesignatorFunc())) {
			Code.put(Code.call);
			//todo NE RADI ZA ULANCAVANJE
			Code.put2(calculatePCOffset(factorFunctionCall.getDesignatorFunc().obj.getAdr()));
			}
	}
	
	
	@Override
	public void visit(ReturnStatement_ ReturnStatement_) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(DesignatorStatementFuncCall designatorStatementFuncCall) {
			
		
		 if(!callClassMethod(designatorStatementFuncCall.getDesignatorFunc()))  {
		
		Code.put(Code.call);
		Code.put2(calculatePCOffset(designatorStatementFuncCall.getDesignatorFunc().obj.getAdr()));
		if(designatorStatementFuncCall.getDesignatorFunc().obj.getType()!=Tab.noType) {
			Code.put(Code.pop);
			}
		}
	}
	@Override
	public void visit(DesignatorFunc designatorFunc) {
		if(!wasDotIdent) {
			if(isClassMethod(designatorFunc.obj)) {
				Code.put(Code.load_n+0);
			}
			//designatorFunc.getDesignator().traverseBottomUp(this);
		}
		
		
	}
	
	private boolean isClassMethod(Obj mthObj) {
		List<Obj> locals = mthObj.getLocalSymbols().stream().collect(Collectors.toList());
		if(locals.isEmpty()) return false;
		if(locals.get(0)== null || ! locals.get(0).getName().equals("this") || locals.get(0).getType().getKind()!=Struct.Class) return false;
		return true;
	}
	private boolean callClassMethod(DesignatorFunc desFunc) {
		Obj mthObj = desFunc.obj;
		if(!isClassMethod(mthObj)) return false;
		desFunc.traverseBottomUp(this);
		Code.put(Code.getfield);
		Code.put2(0);
		Code.put(Code.invokevirtual);
		for(int i= 0 ; i < mthObj.getName().length();i++ ) {
			Code.put4(mthObj.getName().charAt(i));
		}
		Code.put4(-1);
		
		return true;
		
	}
	
	/**  FACTORS  **/
	
	@Override
	public void visit(FactorBool factorBool) {
			Code.loadConst(factorBool.getVal().equals("true")?1:0);
	
	}
	
	@Override
	public void visit(FactorChar factorChar) {
		Code.loadConst(factorChar.getVal());
	}
	
	@Override
	public void visit(FactorNumber factorNumber) {
	  Code.loadConst(factorNumber.getVal());
	}
	
	
	/*** EXPRESSIONS**/
	
	
	
	@Override
	public void visit(AddopMultipleList addopMultipleList) {
		int codeOp = 1;
		if(addopMultipleList.getAddop() instanceof Plus) codeOp = Code.add;
		else codeOp = Code.sub;
		Code.put(codeOp);
	}
	
	@Override
	public void visit(TermMul termMul) {
		int codeOp = 1;
		if(termMul.getMulop() instanceof Mul ) codeOp = Code.mul;
		else if (termMul.getMulop() instanceof Div ) codeOp = Code.div;
		else if(termMul.getMulop() instanceof Rem ) codeOp = Code.rem;
		Code.put(codeOp);
	}
	
	@Override
	public void visit(FactorDesignator factorDesignator) {
	
		Code.load(factorDesignator.getDesignator().obj);
	}
	
 /*** Designator statements **************/
	@Override
	public void visit(DesignatorStatementInc designatorStatementInc) {
		Designator des = designatorStatementInc.getDesignator();
		Code.load(des.obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(des.obj);
	}
	
	@Override
	public void visit(DesignatoStatementDec designatoStatementDec) {
		Designator des = designatoStatementDec.getDesignator();
		Code.load(des.obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(des.obj);
	}
	
	@Override
	public void visit(DesignatorStatementAssignop designatorStatementAssignop) {
	 Designator des = designatorStatementAssignop.getDesignator();
	 
		 Code.store(des.obj);
	 	
	}
	
	private int calculatePCOffset(int adr) {
		return adr-Code.pc+1;
	}
	
	private Obj currentDesignatorObj;
	private Struct currentDesignatorStruct;
	
 
	@Override
	public void visit(DesignatorIndexingOption designatorIndexingOption) {
		Obj currentDesignatorObj = null;
		SyntaxNode parent = designatorIndexingOption.getParent().getParent();
		 if(parent instanceof Designator) {
		currentDesignatorObj=((Designator)parent).getDesignatorName().obj;
		 } else if(parent instanceof DesignatorOptionList_) {
			 currentDesignatorObj=((DesignatorOptionList_)parent).getDesignatorOption().obj;
		}
		 currentDesignatorStruct=currentDesignatorObj.getType();
		designatorIndexingOption.obj = new Obj(Obj.Elem, "", currentDesignatorStruct.getElemType());
		currentDesignatorStruct= currentDesignatorStruct.getElemType();
	}
	
	@Override
	public void visit(DesignatorDotIdentOption designatorDotIdentOption) {
		//System.err.println(designatorDotIdentOption.getName());
		wasDotIdent=true;
		Obj currentDesignatorObj = null;
		SyntaxNode parent = designatorDotIdentOption.getParent().getParent();
		 if(parent instanceof Designator) {
		currentDesignatorObj=((Designator)parent).getDesignatorName().obj;
		 } else if(parent instanceof DesignatorOptionList_) {
			 currentDesignatorObj=((DesignatorOptionList_)parent).getDesignatorOption().obj;
		}//System.err.println(currentDesignatorObj.getName());
		currentDesignatorStruct=currentDesignatorObj.getType();
		Code.load(currentDesignatorObj);
		currentDesignatorObj = currentDesignatorStruct.getMembersTable().searchKey(designatorDotIdentOption.getName());
		currentDesignatorStruct = currentDesignatorObj.getType();
		designatorDotIdentOption.obj=currentDesignatorObj;
		
		if(((DesignatorOptionList_)(designatorDotIdentOption.getParent())).getDesignatorOptionList()instanceof DesignatorOptionList_) {
			if(currentDesignatorStruct.getKind()==Struct.Array) Code.load(currentDesignatorObj);
		}
	}
	
	private boolean wasDotIdent = false;
	
	@Override
	public void visit(DesignatorName designatorName) {
		wasDotIdent=false;
		//System.err.println(designatorName.obj.getKind());
		currentDesignatorObj=designatorName.obj;
		currentDesignatorStruct= currentDesignatorObj.getType();
		if(designatorName.obj.getKind()==Obj.Fld) Code.put(Code.load_n+0); //ukoliko je u metodi, a nema this, a polje je
		if(((Designator)(designatorName.getParent())).getDesignatorOptionList() instanceof DesignatorOptionEmptyList) return;
		if( currentDesignatorStruct.getKind()==Struct.Array) Code.load(currentDesignatorObj);
		
	}
	
	@Override
	public void visit(Designator designator) {
		// TODO Auto-generated method stub
		if(designator.getDesignatorOptionList()instanceof DesignatorOptionEmptyList) {
			designator.obj=designator.getDesignatorName().obj;
		} else 
			{
			SyntaxNode next = designator.getDesignatorOptionList();
			while(! (next instanceof DesignatorOptionEmptyList)) {
					designator.obj = ((DesignatorOptionList_)(next)).getDesignatorOption().obj;
					next =  ((DesignatorOptionList_)(next)).getDesignatorOptionList();
			}
		}
	}
	/****   OOP  *****/
	private Map<Struct, List<Integer>> mapNew = new HashMap<>();
	
	@Override
	public void visit(FactorNew factorNew) {
		// TODO Auto-generated method stub
		Code.put(Code.new_);
		
		Code.put2(factorNew.getType().struct.getNumberOfFields()*4);
		Code.put(Code.dup);
		Code.put(Code.const_);
		if(mapNew.get(factorNew.getType().struct)==null)
			mapNew.put(factorNew.getType().struct, new LinkedList<Integer>());
		mapNew.get(factorNew.getType().struct).add(Code.pc);
		Code.put4(0); //dummy
		Code.put(Code.putfield);
		Code.put2(0);
		
	}
	
	@Override
	public void visit(FactorNewExpr factorNewExpr) {
		Code.put(Code.newarray);
		if(factorNewExpr.getType().struct == Tab.charType)
			Code.put(0);
		else Code.put(1);
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	/**CONDITIONS **/
	
	@Override
	public void visit(ConditionFactExprRelop conditionFactExprRelop) {
	int op=1;
	
	Relop relop = conditionFactExprRelop.getRelop();
	if(relop instanceof  Same) op = Code.eq;
	else if(relop instanceof NotSame) op = Code.ne;
	else if(relop instanceof GreaterThen) op = Code.gt;
	else if(relop instanceof GreaterEqual) op= Code.ge;
	else if(relop instanceof LessThen ) op = Code.lt;
	else if(relop instanceof LessEqual) op = Code.le;
	
	int currPc =Code.pc+1;
	Code.putFalseJump(Code.inverse[op], 0);
	Code.loadConst(0);
	int secPc = Code.pc +1;
	Code.putJump(0);
	Code.fixup(currPc);
	Code.loadConst(1);
	Code.fixup(secPc);
	}
	
	
	@Override
	public void visit(ConditionAND conditionAND) {
	 Code.put(Code.add);
	 Code.loadConst(1);
	 int currPc = Code.pc +1;
	 Code.putFalseJump(Code.inverse[Code.le], 1);
	 Code.loadConst(1);
	 int secPc= Code.pc +1;
	 Code.putJump(0);
	 Code.fixup(currPc);
	 Code.loadConst(0);
	 Code.fixup(secPc);
	}
	
	@Override
	public void visit(ConditionOR ConditionOR) {
		 Code.put(Code.add);
		 Code.loadConst(0);
		 int currPc = Code.pc+1;
		 Code.putFalseJump(Code.inverse[Code.le], 0);  
		 Code.loadConst(1);
		 int secPc= Code.pc +1;
		 Code.putJump(0);
		 Code.fixup(currPc); // postavi nulu
		 Code.loadConst(0);
		 Code.fixup(secPc); //preskoci nulu
	
	}
	
	
	@Override
	public void visit(Else elseSt) {
	   IfElseStatement ifElseSt = (IfElseStatement)(elseSt.getParent());
	   IfCondition ifCond = (IfCondition)(ifElseSt.getIfErrorCorection());
	   Code.fixup(ifCond.obj.getAdr());
	}
	
	@Override
	public void visit(IfCondition ifCondition) {
		ifCondition.obj= new Obj(Obj.Con,"", Tab.intType);
		Code.loadConst(1);
		ifCondition.obj.setAdr(Code.pc+1);
		Code.putFalseJump(Code.inverse[Code.ne], 0); 
	}
	
	@Override
	public void visit(IfStatement ifStatement) {
		Code.fixup(ifStatement.getIfErrorCorection().obj.getAdr());
	}
	
	
	
	
	
	
	/****** DO WHILE ********////////
	
	private List<Integer> doWhileStartStack = new ArrayList<Integer>();
	private List<Integer> doWhileContinueStack = new ArrayList<Integer>();
	
	@Override
	public void visit(ContinueStatement continueStatement) {
		// TODO Auto-generated method stub
		doWhileContinueStack.add(Code.pc+1);
		Code.putJump(0);
	}
	
	@Override
	public void visit(DoClause DoClause) {
		// TODO Auto-generated method stub
		doWhileStartStack.add(Code.pc);
	}
	@Override
	public void visit(DoWhileCondition doWhileCondition) {
		// TODO Auto-generated method stub
		for(Integer adr:doWhileContinueStack) {
			Code.fixup(adr);
		}
		doWhileContinueStack.removeIf(adr-> adr>doWhileStartStack.get(doWhileStartStack.size()-1)&& adr<Code.pc);
		
		doWhileCondition.getCondition().traverseBottomUp(this);
		Code.loadConst(1);
		Code.putFalseJump(Code.inverse[Code.eq],doWhileStartStack.get(doWhileStartStack.size()-1));
	}
	
	@Override
	public void visit(DoWhileStatement doWhileStatement) {
		// TODO Auto-generated method stub
		doWhileStartStack.remove(doWhileStartStack.size()-1);
		doWhileStatement.getParent().traverseTopDown(new DoWhileBreakFixup());
		Code.put(Code.pop); //skida prvi do while uslov
	}
	
	/******* SWITCH ********************/
	
	private Map<Integer, Integer> caseClausemap = new HashMap<Integer, Integer>();
	private Map <SyntaxNode , Integer> breakMap = new HashMap<SyntaxNode, Integer>();
	public void visit(SwitchClause switchCl) {
		SwitchStatement switchSt = (SwitchStatement)(switchCl.getParent());
		
		switchSt.traverseBottomUp(new SwitchCaseCodeEntranceCreator());
		
	}
	
	
	
	@Override
	public void visit(CaseClause caseSt) {
		Code.fixup(caseClausemap.get(caseSt.getValue()));
		caseClausemap.remove(caseSt.getValue());
	}
	
	
	
	@Override
	public void visit(BreakStatement breakStatement) {
		breakMap.put(breakStatement, Code.pc+1);
		Code.putJump(0);
	}
	@Override
	public void visit(SwitchStatement switchSt){
			switchSt.traverseTopDown(new SwitchBreakFixup());
			Code.put(Code.pop); //skida izraz sa steka
	}
	
	private class SwitchBreakFixup extends VisitorAdaptor{
		@Override
		public void visit(BreakStatement BreakStatement) {
			 if(breakMap.containsKey(BreakStatement)) {
					Code.fixup(breakMap.get(BreakStatement));
					breakMap.remove(BreakStatement);
				 }
		}
	}
	
	private class SwitchCaseCodeEntranceCreator extends VisitorAdaptor{
		private void generateCaseEnteranceCode(int num) {
			Code.put(Code.dup);
			Code.loadConst(num);
			caseClausemap.put(num, Code.pc+1);
			Code.putFalseJump(Code.inverse[Code.eq], 0);
		
		}
		@Override
		public void visit(Case case_){
			generateCaseEnteranceCode(case_.getCaseClause().getValue());
		}
	}
	
	private class DoWhileBreakFixup extends VisitorAdaptor{
		@Override
		public void visit(BreakStatement BreakStatement) {
		 if(breakMap.containsKey(BreakStatement)) {
			Code.fixup(breakMap.get(BreakStatement));
			breakMap.remove(BreakStatement);
		 }
		}
	
		
	}
	
	private class PolyCreator {
	
		
		
		public void create(Obj meth) {
			//System.out.println(meth.getName());
			String name = meth.getName();
			for(int i=0 ; i < name.length(); i++) {
				char c= name.charAt(i);
				Code.loadConst(c);
				Code.put(Code.putstatic);
				Code.put2(Code.dataSize++);
			}
			Code.loadConst(-1);
			Code.put(Code.putstatic);
			Code.put2(Code.dataSize++);
			Code.loadConst(meth.getAdr());
			Code.put(Code.putstatic);
			Code.put2(Code.dataSize++);
		}
		
		 
	}
		private class ClassDeclVisitorForPoly extends VisitorAdaptor{
			private int oldMainpc;
			public ClassDeclVisitorForPoly() {
				oldMainpc = Code.mainPc;
				Code.mainPc=Code.pc;
			}
			
			@Override
			public void visit(Program program) {
				Code.putJump(oldMainpc);
			}
			
			
			@Override
			public void visit(ClassDeclMethod classDeclMethod) {
				classDeclMethod.getClassName().obj.setAdr(Code.dataSize);
				//System.out.println(classDeclMethod.getClassName().getClassName());

				for(Obj obj : classDeclMethod.getClassName().obj.getType().getMembers()) {
					if(obj.getKind()==Obj.Meth) {
						if(obj.getAdr()==-1) {
							int adr = classDeclMethod.getClassName().obj.getType().getElemType().getMembersTable().searchKey(obj.getName()).getAdr();
							System.err.println(adr);
							obj.setAdr(adr);
						}
						new PolyCreator().create(obj);
					}
				}
				List<Integer> list = mapNew.get(classDeclMethod.getClassName().obj.getType());
				if(list!=null){
					for(int pc:list) {
						Code.put2(pc, classDeclMethod.getClassName().obj.getAdr() >> 16);
						Code.put2(pc+2, classDeclMethod.getClassName().obj.getAdr());
					}
				}
				mapNew.remove(classDeclMethod.getClassName().obj.getType());
				
			}
			
		}
	
	
	
	
}
