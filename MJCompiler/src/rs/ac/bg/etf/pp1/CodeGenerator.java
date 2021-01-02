package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.ac.bg.etf.pp1.ast.AddopMultipleList;
import rs.ac.bg.etf.pp1.ast.BreakStatement;
import rs.ac.bg.etf.pp1.ast.Case;
import rs.ac.bg.etf.pp1.ast.CaseClause;
import rs.ac.bg.etf.pp1.ast.CaseList;
import rs.ac.bg.etf.pp1.ast.CaseList_;
import rs.ac.bg.etf.pp1.ast.CaseSingle;
import rs.ac.bg.etf.pp1.ast.ConditionAND;
import rs.ac.bg.etf.pp1.ast.ConditionFactExprRelop;
import rs.ac.bg.etf.pp1.ast.ConditionOR;
import rs.ac.bg.etf.pp1.ast.ContinueStatement;
import rs.ac.bg.etf.pp1.ast.DesignatoStatementDec;
import rs.ac.bg.etf.pp1.ast.Designator;
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
			Code.put(Code.call);
			//todo NE RADI ZA ULANCAVANJE
			Code.put2(calculatePCOffset(factorFunctionCall.getDesignator().obj.getAdr()));
	}
	
	
	@Override
	public void visit(ReturnStatement_ ReturnStatement_) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(DesignatorStatementFuncCall designatorStatementFuncCall) {
		Code.put(Code.call);
		Code.put2(calculatePCOffset(designatorStatementFuncCall.getDesignator().obj.getAdr()));
		if(designatorStatementFuncCall.getDesignator().obj.getType()!=Tab.noType) {
			Code.put(Code.pop);
		}
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
	 if(des.obj.getType().getKind()==Struct.Array) {
		 
	 	} else {
		 Code.store(des.obj);
	 	}
	}
	
	private int calculatePCOffset(int adr) {
		return adr-Code.pc+1;
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
	
	
	
	
}
