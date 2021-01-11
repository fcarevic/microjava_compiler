// generated with ast extension for cup
// version 0.8
// 11/0/2021 20:14:38


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementFuncCall extends DesignatorStatement {

    private DesignatorFunc DesignatorFunc;
    private ActualParameterList ActualParameterList;

    public DesignatorStatementFuncCall (DesignatorFunc DesignatorFunc, ActualParameterList ActualParameterList) {
        this.DesignatorFunc=DesignatorFunc;
        if(DesignatorFunc!=null) DesignatorFunc.setParent(this);
        this.ActualParameterList=ActualParameterList;
        if(ActualParameterList!=null) ActualParameterList.setParent(this);
    }

    public DesignatorFunc getDesignatorFunc() {
        return DesignatorFunc;
    }

    public void setDesignatorFunc(DesignatorFunc DesignatorFunc) {
        this.DesignatorFunc=DesignatorFunc;
    }

    public ActualParameterList getActualParameterList() {
        return ActualParameterList;
    }

    public void setActualParameterList(ActualParameterList ActualParameterList) {
        this.ActualParameterList=ActualParameterList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorFunc!=null) DesignatorFunc.accept(visitor);
        if(ActualParameterList!=null) ActualParameterList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorFunc!=null) DesignatorFunc.traverseTopDown(visitor);
        if(ActualParameterList!=null) ActualParameterList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorFunc!=null) DesignatorFunc.traverseBottomUp(visitor);
        if(ActualParameterList!=null) ActualParameterList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementFuncCall(\n");

        if(DesignatorFunc!=null)
            buffer.append(DesignatorFunc.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualParameterList!=null)
            buffer.append(ActualParameterList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementFuncCall]");
        return buffer.toString();
    }
}
