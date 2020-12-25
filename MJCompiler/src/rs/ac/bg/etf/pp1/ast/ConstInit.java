// generated with ast extension for cup
// version 0.8
// 25/11/2020 19:54:47


package rs.ac.bg.etf.pp1.ast;

public class ConstInit implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private StdConstType StdConstType;

    public ConstInit (StdConstType StdConstType) {
        this.StdConstType=StdConstType;
        if(StdConstType!=null) StdConstType.setParent(this);
    }

    public StdConstType getStdConstType() {
        return StdConstType;
    }

    public void setStdConstType(StdConstType StdConstType) {
        this.StdConstType=StdConstType;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StdConstType!=null) StdConstType.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StdConstType!=null) StdConstType.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StdConstType!=null) StdConstType.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstInit(\n");

        if(StdConstType!=null)
            buffer.append(StdConstType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstInit]");
        return buffer.toString();
    }
}
