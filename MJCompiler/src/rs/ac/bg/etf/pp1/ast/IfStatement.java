// generated with ast extension for cup
// version 0.8
// 29/11/2020 1:49:52


package rs.ac.bg.etf.pp1.ast;

public class IfStatement extends Statement {

    private IfErrorCorection IfErrorCorection;
    private Statement Statement;

    public IfStatement (IfErrorCorection IfErrorCorection, Statement Statement) {
        this.IfErrorCorection=IfErrorCorection;
        if(IfErrorCorection!=null) IfErrorCorection.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public IfErrorCorection getIfErrorCorection() {
        return IfErrorCorection;
    }

    public void setIfErrorCorection(IfErrorCorection IfErrorCorection) {
        this.IfErrorCorection=IfErrorCorection;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfErrorCorection!=null) IfErrorCorection.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfErrorCorection!=null) IfErrorCorection.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfErrorCorection!=null) IfErrorCorection.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStatement(\n");

        if(IfErrorCorection!=null)
            buffer.append(IfErrorCorection.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStatement]");
        return buffer.toString();
    }
}
