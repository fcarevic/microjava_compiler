// generated with ast extension for cup
// version 0.8
// 27/11/2020 1:44:40


package rs.ac.bg.etf.pp1.ast;

public class ActualParameterMultipleList extends ActualParameterList {

    private ActualParameterList ActualParameterList;
    private Expr Expr;

    public ActualParameterMultipleList (ActualParameterList ActualParameterList, Expr Expr) {
        this.ActualParameterList=ActualParameterList;
        if(ActualParameterList!=null) ActualParameterList.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public ActualParameterList getActualParameterList() {
        return ActualParameterList;
    }

    public void setActualParameterList(ActualParameterList ActualParameterList) {
        this.ActualParameterList=ActualParameterList;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActualParameterList!=null) ActualParameterList.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualParameterList!=null) ActualParameterList.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualParameterList!=null) ActualParameterList.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParameterMultipleList(\n");

        if(ActualParameterList!=null)
            buffer.append(ActualParameterList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualParameterMultipleList]");
        return buffer.toString();
    }
}
