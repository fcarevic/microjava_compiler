// generated with ast extension for cup
// version 0.8
// 25/11/2020 1:49:10


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private DesignatorOptionList DesignatorOptionList;

    public Designator (DesignatorOptionList DesignatorOptionList) {
        this.DesignatorOptionList=DesignatorOptionList;
        if(DesignatorOptionList!=null) DesignatorOptionList.setParent(this);
    }

    public DesignatorOptionList getDesignatorOptionList() {
        return DesignatorOptionList;
    }

    public void setDesignatorOptionList(DesignatorOptionList DesignatorOptionList) {
        this.DesignatorOptionList=DesignatorOptionList;
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
        if(DesignatorOptionList!=null) DesignatorOptionList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorOptionList!=null) DesignatorOptionList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorOptionList!=null) DesignatorOptionList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator(\n");

        if(DesignatorOptionList!=null)
            buffer.append(DesignatorOptionList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator]");
        return buffer.toString();
    }
}
