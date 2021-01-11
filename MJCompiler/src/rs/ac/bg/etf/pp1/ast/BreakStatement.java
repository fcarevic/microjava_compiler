// generated with ast extension for cup
// version 0.8
// 11/0/2021 20:14:38


package rs.ac.bg.etf.pp1.ast;

public class BreakStatement extends Statement {

    private Break Break;

    public BreakStatement (Break Break) {
        this.Break=Break;
        if(Break!=null) Break.setParent(this);
    }

    public Break getBreak() {
        return Break;
    }

    public void setBreak(Break Break) {
        this.Break=Break;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Break!=null) Break.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Break!=null) Break.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Break!=null) Break.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BreakStatement(\n");

        if(Break!=null)
            buffer.append(Break.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BreakStatement]");
        return buffer.toString();
    }
}
