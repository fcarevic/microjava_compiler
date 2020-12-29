// generated with ast extension for cup
// version 0.8
// 29/11/2020 4:46:5


package rs.ac.bg.etf.pp1.ast;

public class ConditionAND extends ConditionTerm {

    private ConditionTerm ConditionTerm;
    private ConditonFact ConditonFact;

    public ConditionAND (ConditionTerm ConditionTerm, ConditonFact ConditonFact) {
        this.ConditionTerm=ConditionTerm;
        if(ConditionTerm!=null) ConditionTerm.setParent(this);
        this.ConditonFact=ConditonFact;
        if(ConditonFact!=null) ConditonFact.setParent(this);
    }

    public ConditionTerm getConditionTerm() {
        return ConditionTerm;
    }

    public void setConditionTerm(ConditionTerm ConditionTerm) {
        this.ConditionTerm=ConditionTerm;
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
        if(ConditionTerm!=null) ConditionTerm.accept(visitor);
        if(ConditonFact!=null) ConditonFact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionTerm!=null) ConditionTerm.traverseTopDown(visitor);
        if(ConditonFact!=null) ConditonFact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionTerm!=null) ConditionTerm.traverseBottomUp(visitor);
        if(ConditonFact!=null) ConditonFact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionAND(\n");

        if(ConditionTerm!=null)
            buffer.append(ConditionTerm.toString("  "+tab));
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
