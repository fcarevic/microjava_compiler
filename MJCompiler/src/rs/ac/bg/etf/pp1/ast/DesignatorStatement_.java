// generated with ast extension for cup
// version 0.8
// 27/11/2020 1:44:40


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatement_ extends DesignatorStatement {

    private Designator Designator;
    private DesingatorOption DesingatorOption;

    public DesignatorStatement_ (Designator Designator, DesingatorOption DesingatorOption) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DesingatorOption=DesingatorOption;
        if(DesingatorOption!=null) DesingatorOption.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DesingatorOption getDesingatorOption() {
        return DesingatorOption;
    }

    public void setDesingatorOption(DesingatorOption DesingatorOption) {
        this.DesingatorOption=DesingatorOption;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(DesingatorOption!=null) DesingatorOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DesingatorOption!=null) DesingatorOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DesingatorOption!=null) DesingatorOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatement_(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesingatorOption!=null)
            buffer.append(DesingatorOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatement_]");
        return buffer.toString();
    }
}
