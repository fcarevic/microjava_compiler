// generated with ast extension for cup
// version 0.8
// 2/0/2021 3:42:30


package rs.ac.bg.etf.pp1.ast;

public class DoWhileStatement extends Statement {

    private DoClause DoClause;
    private Statement Statement;
    private DoWhileCondition DoWhileCondition;

    public DoWhileStatement (DoClause DoClause, Statement Statement, DoWhileCondition DoWhileCondition) {
        this.DoClause=DoClause;
        if(DoClause!=null) DoClause.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.DoWhileCondition=DoWhileCondition;
        if(DoWhileCondition!=null) DoWhileCondition.setParent(this);
    }

    public DoClause getDoClause() {
        return DoClause;
    }

    public void setDoClause(DoClause DoClause) {
        this.DoClause=DoClause;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public DoWhileCondition getDoWhileCondition() {
        return DoWhileCondition;
    }

    public void setDoWhileCondition(DoWhileCondition DoWhileCondition) {
        this.DoWhileCondition=DoWhileCondition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DoClause!=null) DoClause.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(DoWhileCondition!=null) DoWhileCondition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DoClause!=null) DoClause.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(DoWhileCondition!=null) DoWhileCondition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DoClause!=null) DoClause.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(DoWhileCondition!=null) DoWhileCondition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DoWhileStatement(\n");

        if(DoClause!=null)
            buffer.append(DoClause.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DoWhileCondition!=null)
            buffer.append(DoWhileCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DoWhileStatement]");
        return buffer.toString();
    }
}
