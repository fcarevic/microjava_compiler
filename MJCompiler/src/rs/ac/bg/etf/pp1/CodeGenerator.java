package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.AddopMultipleList;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementFuncCall;
import rs.ac.bg.etf.pp1.ast.FactorBool;
import rs.ac.bg.etf.pp1.ast.FactorChar;
import rs.ac.bg.etf.pp1.ast.FactorDesignator;
import rs.ac.bg.etf.pp1.ast.FactorFunctionCall;
import rs.ac.bg.etf.pp1.ast.FactorNumber;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodName;
import rs.ac.bg.etf.pp1.ast.NoPrintNumConst;
import rs.ac.bg.etf.pp1.ast.PrintNumConst_;
import rs.ac.bg.etf.pp1.ast.PrintStatement;
import rs.ac.bg.etf.pp1.ast.ReturnStatement_;
import rs.ac.bg.etf.pp1.ast.TermMul;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

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
			Code.put2(factorFunctionCall.getDesignator().obj.getAdr());
	}
	
	
	@Override
	public void visit(ReturnStatement_ ReturnStatement_) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(DesignatorStatementFuncCall designatorStatementFuncCall) {
		if(designatorStatementFuncCall.getDesignator().obj.getType()==Tab.noType) {
			Code.put(Code.pop);
		}
	}
	
	/**  FACTORS  **/
	
	@Override
	public void visit(FactorBool FactorBool) {
	 //STA OVDE
	}
	
	@Override
	public void visit(FactorChar factorChar) {
		//sta ovde
	}
	
	@Override
	public void visit(FactorNumber factorNumber) {
	  Code.loadConst(factorNumber.getVal());
	}
	
	
	/*** EXPRESSIONS**/
	
	@Override
	public void visit(AddopMultipleList AddopMultipleList) {
		Code.put(Code.add);
	}
	
	@Override
	public void visit(TermMul TermMul) {
		Code.put(Code.mul);
	}
	
	@Override
	public void visit(FactorDesignator factorDesignator) {
	
		Code.load(factorDesignator.getDesignator().obj);
	}
	

}
