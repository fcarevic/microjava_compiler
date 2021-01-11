// generated with ast extension for cup
// version 0.8
// 11/0/2021 20:14:37


package rs.ac.bg.etf.pp1.ast;

public class VarDeclEmptyList extends VarDeclList {

    public VarDeclEmptyList () {
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
        buffer.append("VarDeclEmptyList(\n");

        buffer.append(tab);
        buffer.append(") [VarDeclEmptyList]");
        return buffer.toString();
    }
}
