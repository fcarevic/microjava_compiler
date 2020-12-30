// generated with ast extension for cup
// version 0.8
// 30/11/2020 3:8:7


package rs.ac.bg.etf.pp1.ast;

public class NoExtendsClause extends ExtendsClause {

    public NoExtendsClause () {
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
        buffer.append("NoExtendsClause(\n");

        buffer.append(tab);
        buffer.append(") [NoExtendsClause]");
        return buffer.toString();
    }
}
