// generated with ast extension for cup
// version 0.8
// 2/0/2021 3:42:30


package rs.ac.bg.etf.pp1.ast;

public class FactorFunctionCall extends Factor {

    private Designator Designator;
    private ActualParameterList ActualParameterList;

    public FactorFunctionCall (Designator Designator, ActualParameterList ActualParameterList) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.ActualParameterList=ActualParameterList;
        if(ActualParameterList!=null) ActualParameterList.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
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
        if(Designator!=null) Designator.accept(visitor);
        if(ActualParameterList!=null) ActualParameterList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(ActualParameterList!=null) ActualParameterList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(ActualParameterList!=null) ActualParameterList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorFunctionCall(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualParameterList!=null)
            buffer.append(ActualParameterList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorFunctionCall]");
        return buffer.toString();
    }
}
