// generated with ast extension for cup
// version 0.8
// 26/11/2020 1:27:38


package rs.ac.bg.etf.pp1.ast;

public class FactorString extends Factor {

    public FactorString () {
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
        buffer.append("FactorString(\n");

        buffer.append(tab);
        buffer.append(") [FactorString]");
        return buffer.toString();
    }
}
