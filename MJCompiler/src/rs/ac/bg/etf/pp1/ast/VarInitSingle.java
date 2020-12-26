// generated with ast extension for cup
// version 0.8
// 26/11/2020 1:27:37


package rs.ac.bg.etf.pp1.ast;

public class VarInitSingle extends VarInitList {

    private VarInit VarInit;

    public VarInitSingle (VarInit VarInit) {
        this.VarInit=VarInit;
        if(VarInit!=null) VarInit.setParent(this);
    }

    public VarInit getVarInit() {
        return VarInit;
    }

    public void setVarInit(VarInit VarInit) {
        this.VarInit=VarInit;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarInit!=null) VarInit.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarInit!=null) VarInit.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarInit!=null) VarInit.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarInitSingle(\n");

        if(VarInit!=null)
            buffer.append(VarInit.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarInitSingle]");
        return buffer.toString();
    }
}
