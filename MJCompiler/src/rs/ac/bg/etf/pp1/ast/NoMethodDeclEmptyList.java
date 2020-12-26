// generated with ast extension for cup
// version 0.8
// 26/11/2020 1:27:37


package rs.ac.bg.etf.pp1.ast;

public class NoMethodDeclEmptyList extends NoMethodDeclList {

    public NoMethodDeclEmptyList () {
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
        buffer.append("NoMethodDeclEmptyList(\n");

        buffer.append(tab);
        buffer.append(") [NoMethodDeclEmptyList]");
        return buffer.toString();
    }
}
