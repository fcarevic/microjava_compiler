// generated with ast extension for cup
// version 0.8
// 25/11/2020 1:49:9


package rs.ac.bg.etf.pp1.ast;

public class DesingatorOptionDerived1 extends DesingatorOption {

    private ActualParameterList ActualParameterList;

    public DesingatorOptionDerived1 (ActualParameterList ActualParameterList) {
        this.ActualParameterList=ActualParameterList;
        if(ActualParameterList!=null) ActualParameterList.setParent(this);
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
        if(ActualParameterList!=null) ActualParameterList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualParameterList!=null) ActualParameterList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualParameterList!=null) ActualParameterList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesingatorOptionDerived1(\n");

        if(ActualParameterList!=null)
            buffer.append(ActualParameterList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesingatorOptionDerived1]");
        return buffer.toString();
    }
}
