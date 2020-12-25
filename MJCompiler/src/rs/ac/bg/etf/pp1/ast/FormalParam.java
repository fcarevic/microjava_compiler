// generated with ast extension for cup
// version 0.8
// 25/11/2020 1:49:9


package rs.ac.bg.etf.pp1.ast;

public class FormalParam implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private VarInit VarInit;

    public FormalParam (Type Type, VarInit VarInit) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarInit=VarInit;
        if(VarInit!=null) VarInit.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarInit getVarInit() {
        return VarInit;
    }

    public void setVarInit(VarInit VarInit) {
        this.VarInit=VarInit;
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
        if(Type!=null) Type.accept(visitor);
        if(VarInit!=null) VarInit.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarInit!=null) VarInit.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarInit!=null) VarInit.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParam(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarInit!=null)
            buffer.append(VarInit.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParam]");
        return buffer.toString();
    }
}
