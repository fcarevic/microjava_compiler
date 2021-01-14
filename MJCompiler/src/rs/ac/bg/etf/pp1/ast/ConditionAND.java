// generated with ast extension for cup
// version 0.8
// 14/0/2021 0:39:34


package rs.ac.bg.etf.pp1.ast;

public class ConditionAND extends ConditionTerm {

    private ConditionTermLeft ConditionTermLeft;
    private ConditonFact ConditonFact;

    public ConditionAND (ConditionTermLeft ConditionTermLeft, ConditonFact ConditonFact) {
        this.ConditionTermLeft=ConditionTermLeft;
        if(ConditionTermLeft!=null) ConditionTermLeft.setParent(this);
        this.ConditonFact=ConditonFact;
        if(ConditonFact!=null) ConditonFact.setParent(this);
    }

    public ConditionTermLeft getConditionTermLeft() {
        return ConditionTermLeft;
    }

    public void setConditionTermLeft(ConditionTermLeft ConditionTermLeft) {
        this.ConditionTermLeft=ConditionTermLeft;
    }

    public ConditonFact getConditonFact() {
        return ConditonFact;
    }

    public void setConditonFact(ConditonFact ConditonFact) {
        this.ConditonFact=ConditonFact;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionTermLeft!=null) ConditionTermLeft.accept(visitor);
        if(ConditonFact!=null) ConditonFact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionTermLeft!=null) ConditionTermLeft.traverseTopDown(visitor);
        if(ConditonFact!=null) ConditonFact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionTermLeft!=null) ConditionTermLeft.traverseBottomUp(visitor);
        if(ConditonFact!=null) ConditonFact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionAND(\n");

        if(ConditionTermLeft!=null)
            buffer.append(ConditionTermLeft.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditonFact!=null)
            buffer.append(ConditonFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionAND]");
        return buffer.toString();
    }
}
