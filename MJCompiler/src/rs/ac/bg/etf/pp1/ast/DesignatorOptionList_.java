// generated with ast extension for cup
// version 0.8
// 14/0/2021 0:39:35


package rs.ac.bg.etf.pp1.ast;

public class DesignatorOptionList_ extends DesignatorOptionList {

    private DesignatorOption DesignatorOption;
    private DesignatorOptionList DesignatorOptionList;

    public DesignatorOptionList_ (DesignatorOption DesignatorOption, DesignatorOptionList DesignatorOptionList) {
        this.DesignatorOption=DesignatorOption;
        if(DesignatorOption!=null) DesignatorOption.setParent(this);
        this.DesignatorOptionList=DesignatorOptionList;
        if(DesignatorOptionList!=null) DesignatorOptionList.setParent(this);
    }

    public DesignatorOption getDesignatorOption() {
        return DesignatorOption;
    }

    public void setDesignatorOption(DesignatorOption DesignatorOption) {
        this.DesignatorOption=DesignatorOption;
    }

    public DesignatorOptionList getDesignatorOptionList() {
        return DesignatorOptionList;
    }

    public void setDesignatorOptionList(DesignatorOptionList DesignatorOptionList) {
        this.DesignatorOptionList=DesignatorOptionList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorOption!=null) DesignatorOption.accept(visitor);
        if(DesignatorOptionList!=null) DesignatorOptionList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorOption!=null) DesignatorOption.traverseTopDown(visitor);
        if(DesignatorOptionList!=null) DesignatorOptionList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorOption!=null) DesignatorOption.traverseBottomUp(visitor);
        if(DesignatorOptionList!=null) DesignatorOptionList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorOptionList_(\n");

        if(DesignatorOption!=null)
            buffer.append(DesignatorOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorOptionList!=null)
            buffer.append(DesignatorOptionList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorOptionList_]");
        return buffer.toString();
    }
}
