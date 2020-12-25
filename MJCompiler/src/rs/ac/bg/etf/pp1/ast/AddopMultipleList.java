// generated with ast extension for cup
// version 0.8
// 25/11/2020 19:54:47


package rs.ac.bg.etf.pp1.ast;

public class AddopMultipleList extends AddopList {

    private AddopList AddopList;
    private AddopTermExprOption AddopTermExprOption;

    public AddopMultipleList (AddopList AddopList, AddopTermExprOption AddopTermExprOption) {
        this.AddopList=AddopList;
        if(AddopList!=null) AddopList.setParent(this);
        this.AddopTermExprOption=AddopTermExprOption;
        if(AddopTermExprOption!=null) AddopTermExprOption.setParent(this);
    }

    public AddopList getAddopList() {
        return AddopList;
    }

    public void setAddopList(AddopList AddopList) {
        this.AddopList=AddopList;
    }

    public AddopTermExprOption getAddopTermExprOption() {
        return AddopTermExprOption;
    }

    public void setAddopTermExprOption(AddopTermExprOption AddopTermExprOption) {
        this.AddopTermExprOption=AddopTermExprOption;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AddopList!=null) AddopList.accept(visitor);
        if(AddopTermExprOption!=null) AddopTermExprOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AddopList!=null) AddopList.traverseTopDown(visitor);
        if(AddopTermExprOption!=null) AddopTermExprOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AddopList!=null) AddopList.traverseBottomUp(visitor);
        if(AddopTermExprOption!=null) AddopTermExprOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddopMultipleList(\n");

        if(AddopList!=null)
            buffer.append(AddopList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddopTermExprOption!=null)
            buffer.append(AddopTermExprOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddopMultipleList]");
        return buffer.toString();
    }
}
