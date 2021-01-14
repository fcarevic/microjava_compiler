// generated with ast extension for cup
// version 0.8
// 14/0/2021 0:39:34


package rs.ac.bg.etf.pp1.ast;

public class ConditionOR extends Condition {

    private ConditionLeft ConditionLeft;
    private ConditionTerm ConditionTerm;

    public ConditionOR (ConditionLeft ConditionLeft, ConditionTerm ConditionTerm) {
        this.ConditionLeft=ConditionLeft;
        if(ConditionLeft!=null) ConditionLeft.setParent(this);
        this.ConditionTerm=ConditionTerm;
        if(ConditionTerm!=null) ConditionTerm.setParent(this);
    }

    public ConditionLeft getConditionLeft() {
        return ConditionLeft;
    }

    public void setConditionLeft(ConditionLeft ConditionLeft) {
        this.ConditionLeft=ConditionLeft;
    }

    public ConditionTerm getConditionTerm() {
        return ConditionTerm;
    }

    public void setConditionTerm(ConditionTerm ConditionTerm) {
        this.ConditionTerm=ConditionTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionLeft!=null) ConditionLeft.accept(visitor);
        if(ConditionTerm!=null) ConditionTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionLeft!=null) ConditionLeft.traverseTopDown(visitor);
        if(ConditionTerm!=null) ConditionTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionLeft!=null) ConditionLeft.traverseBottomUp(visitor);
        if(ConditionTerm!=null) ConditionTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionOR(\n");

        if(ConditionLeft!=null)
            buffer.append(ConditionLeft.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionTerm!=null)
            buffer.append(ConditionTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionOR]");
        return buffer.toString();
    }
}
