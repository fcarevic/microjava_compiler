// generated with ast extension for cup
// version 0.8
// 14/0/2021 17:36:52


package rs.ac.bg.etf.pp1.ast;

public class TernaryExpr extends Expr {

    private TernaryCondition TernaryCondition;
    private Expr1 Expr1;
    private TernaryColon TernaryColon;
    private Expr1 Expr11;

    public TernaryExpr (TernaryCondition TernaryCondition, Expr1 Expr1, TernaryColon TernaryColon, Expr1 Expr11) {
        this.TernaryCondition=TernaryCondition;
        if(TernaryCondition!=null) TernaryCondition.setParent(this);
        this.Expr1=Expr1;
        if(Expr1!=null) Expr1.setParent(this);
        this.TernaryColon=TernaryColon;
        if(TernaryColon!=null) TernaryColon.setParent(this);
        this.Expr11=Expr11;
        if(Expr11!=null) Expr11.setParent(this);
    }

    public TernaryCondition getTernaryCondition() {
        return TernaryCondition;
    }

    public void setTernaryCondition(TernaryCondition TernaryCondition) {
        this.TernaryCondition=TernaryCondition;
    }

    public Expr1 getExpr1() {
        return Expr1;
    }

    public void setExpr1(Expr1 Expr1) {
        this.Expr1=Expr1;
    }

    public TernaryColon getTernaryColon() {
        return TernaryColon;
    }

    public void setTernaryColon(TernaryColon TernaryColon) {
        this.TernaryColon=TernaryColon;
    }

    public Expr1 getExpr11() {
        return Expr11;
    }

    public void setExpr11(Expr1 Expr11) {
        this.Expr11=Expr11;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TernaryCondition!=null) TernaryCondition.accept(visitor);
        if(Expr1!=null) Expr1.accept(visitor);
        if(TernaryColon!=null) TernaryColon.accept(visitor);
        if(Expr11!=null) Expr11.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TernaryCondition!=null) TernaryCondition.traverseTopDown(visitor);
        if(Expr1!=null) Expr1.traverseTopDown(visitor);
        if(TernaryColon!=null) TernaryColon.traverseTopDown(visitor);
        if(Expr11!=null) Expr11.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TernaryCondition!=null) TernaryCondition.traverseBottomUp(visitor);
        if(Expr1!=null) Expr1.traverseBottomUp(visitor);
        if(TernaryColon!=null) TernaryColon.traverseBottomUp(visitor);
        if(Expr11!=null) Expr11.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TernaryExpr(\n");

        if(TernaryCondition!=null)
            buffer.append(TernaryCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr1!=null)
            buffer.append(Expr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TernaryColon!=null)
            buffer.append(TernaryColon.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr11!=null)
            buffer.append(Expr11.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TernaryExpr]");
        return buffer.toString();
    }
}
