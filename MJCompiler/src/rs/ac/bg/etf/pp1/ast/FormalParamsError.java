// generated with ast extension for cup
// version 0.8
// 7/0/2021 1:43:46


package rs.ac.bg.etf.pp1.ast;

public class FormalParamsError extends FormalParams {

    public FormalParamsError () {
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
        buffer.append("FormalParamsError(\n");

        buffer.append(tab);
        buffer.append(") [FormalParamsError]");
        return buffer.toString();
    }
}
