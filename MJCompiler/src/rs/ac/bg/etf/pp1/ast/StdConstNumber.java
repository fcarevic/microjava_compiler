// generated with ast extension for cup
// version 0.8
// 25/11/2020 1:49:9


package rs.ac.bg.etf.pp1.ast;

public class StdConstNumber extends StdConstType {

    public StdConstNumber () {
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
        buffer.append("StdConstNumber(\n");

        buffer.append(tab);
        buffer.append(") [StdConstNumber]");
        return buffer.toString();
    }
}
