// generated with ast extension for cup
// version 0.8
// 14/0/2021 0:39:34


package rs.ac.bg.etf.pp1.ast;

public class NoConditionAND extends ConditionTerm {

    private ConditonFact ConditonFact;

    public NoConditionAND (ConditonFact ConditonFact) {
        this.ConditonFact=ConditonFact;
        if(ConditonFact!=null) ConditonFact.setParent(this);
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
        if(ConditonFact!=null) ConditonFact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditonFact!=null) ConditonFact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditonFact!=null) ConditonFact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoConditionAND(\n");

        if(ConditonFact!=null)
            buffer.append(ConditonFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NoConditionAND]");
        return buffer.toString();
    }
}
