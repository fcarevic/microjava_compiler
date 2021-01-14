// generated with ast extension for cup
// version 0.8
// 14/0/2021 17:36:51


package rs.ac.bg.etf.pp1.ast;

public class DoWhileStatement extends Statement {

    private DoClause DoClause;
    private Statement Statement;
    private DoWhileClause DoWhileClause;
    private Condition Condition;

    public DoWhileStatement (DoClause DoClause, Statement Statement, DoWhileClause DoWhileClause, Condition Condition) {
        this.DoClause=DoClause;
        if(DoClause!=null) DoClause.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.DoWhileClause=DoWhileClause;
        if(DoWhileClause!=null) DoWhileClause.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
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

    public DoWhileClause getDoWhileClause() {
        return DoWhileClause;
    }

    public void setDoWhileClause(DoWhileClause DoWhileClause) {
        this.DoWhileClause=DoWhileClause;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DoClause!=null) DoClause.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(DoWhileClause!=null) DoWhileClause.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DoClause!=null) DoClause.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(DoWhileClause!=null) DoWhileClause.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DoClause!=null) DoClause.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(DoWhileClause!=null) DoWhileClause.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
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

        if(DoWhileClause!=null)
            buffer.append(DoWhileClause.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DoWhileStatement]");
        return buffer.toString();
    }
}
