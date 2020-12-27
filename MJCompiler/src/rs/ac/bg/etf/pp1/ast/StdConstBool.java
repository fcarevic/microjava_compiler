// generated with ast extension for cup
// version 0.8
// 27/11/2020 3:23:2


package rs.ac.bg.etf.pp1.ast;

public class StdConstBool extends StdConstType {

    private String val;

    public StdConstBool (String val) {
        this.val=val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val=val;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StdConstBool(\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StdConstBool]");
        return buffer.toString();
    }
}
