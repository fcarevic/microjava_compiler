// generated with ast extension for cup
// version 0.8
// 29/11/2020 4:46:4


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclEmptyList extends MethodDeclList {

    public MethodDeclEmptyList () {
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
        buffer.append("MethodDeclEmptyList(\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclEmptyList]");
        return buffer.toString();
    }
}
