// generated with ast extension for cup
// version 0.8
// 30/11/2020 3:8:7


package rs.ac.bg.etf.pp1.ast;

public class IfElseStatement extends Statement {

    private IfErrorCorection IfErrorCorection;
    private Statement Statement;
    private Statement Statement1;

    public IfElseStatement (IfErrorCorection IfErrorCorection, Statement Statement, Statement Statement1) {
        this.IfErrorCorection=IfErrorCorection;
        if(IfErrorCorection!=null) IfErrorCorection.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.Statement1=Statement1;
        if(Statement1!=null) Statement1.setParent(this);
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

    public Statement getStatement1() {
        return Statement1;
    }

    public void setStatement1(Statement Statement1) {
        this.Statement1=Statement1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfErrorCorection!=null) IfErrorCorection.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(Statement1!=null) Statement1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfErrorCorection!=null) IfErrorCorection.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(Statement1!=null) Statement1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfErrorCorection!=null) IfErrorCorection.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(Statement1!=null) Statement1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElseStatement(\n");

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

        if(Statement1!=null)
            buffer.append(Statement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElseStatement]");
        return buffer.toString();
    }
}
