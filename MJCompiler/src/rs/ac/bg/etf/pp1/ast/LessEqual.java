// generated with ast extension for cup
// version 0.8
// 27/11/2020 23:35:44


package rs.ac.bg.etf.pp1.ast;

public class LessEqual extends Relop {

    public LessEqual () {
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
        buffer.append("LessEqual(\n");

        buffer.append(tab);
        buffer.append(") [LessEqual]");
        return buffer.toString();
    }
}
