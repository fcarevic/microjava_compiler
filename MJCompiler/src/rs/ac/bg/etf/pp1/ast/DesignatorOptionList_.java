// generated with ast extension for cup
// version 0.8
// 26/11/2020 1:27:38


package rs.ac.bg.etf.pp1.ast;

public class DesignatorOptionList_ extends DesignatorOptionList {

    private DesignatorOptionList DesignatorOptionList;
    private DesignatorOption DesignatorOption;

    public DesignatorOptionList_ (DesignatorOptionList DesignatorOptionList, DesignatorOption DesignatorOption) {
        this.DesignatorOptionList=DesignatorOptionList;
        if(DesignatorOptionList!=null) DesignatorOptionList.setParent(this);
        this.DesignatorOption=DesignatorOption;
        if(DesignatorOption!=null) DesignatorOption.setParent(this);
    }

    public DesignatorOptionList getDesignatorOptionList() {
        return DesignatorOptionList;
    }

    public void setDesignatorOptionList(DesignatorOptionList DesignatorOptionList) {
        this.DesignatorOptionList=DesignatorOptionList;
    }

    public DesignatorOption getDesignatorOption() {
        return DesignatorOption;
    }

    public void setDesignatorOption(DesignatorOption DesignatorOption) {
        this.DesignatorOption=DesignatorOption;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorOptionList!=null) DesignatorOptionList.accept(visitor);
        if(DesignatorOption!=null) DesignatorOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorOptionList!=null) DesignatorOptionList.traverseTopDown(visitor);
        if(DesignatorOption!=null) DesignatorOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorOptionList!=null) DesignatorOptionList.traverseBottomUp(visitor);
        if(DesignatorOption!=null) DesignatorOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorOptionList_(\n");

        if(DesignatorOptionList!=null)
            buffer.append(DesignatorOptionList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorOption!=null)
            buffer.append(DesignatorOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorOptionList_]");
        return buffer.toString();
    }
}
