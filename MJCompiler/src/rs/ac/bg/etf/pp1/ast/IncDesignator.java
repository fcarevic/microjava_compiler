// generated with ast extension for cup
// version 0.8
// 25/11/2020 19:54:47


package rs.ac.bg.etf.pp1.ast;

public class IncDesignator extends DesingatorOption {

    public IncDesignator () {
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
        buffer.append("IncDesignator(\n");

        buffer.append(tab);
        buffer.append(") [IncDesignator]");
        return buffer.toString();
    }
}
