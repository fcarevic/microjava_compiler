// generated with ast extension for cup
// version 0.8
// 11/0/2021 20:14:37


package rs.ac.bg.etf.pp1.ast;

public class VarInitMultipleList extends VarInitList {

    private VarInitList VarInitList;
    private VarInit VarInit;

    public VarInitMultipleList (VarInitList VarInitList, VarInit VarInit) {
        this.VarInitList=VarInitList;
        if(VarInitList!=null) VarInitList.setParent(this);
        this.VarInit=VarInit;
        if(VarInit!=null) VarInit.setParent(this);
    }

    public VarInitList getVarInitList() {
        return VarInitList;
    }

    public void setVarInitList(VarInitList VarInitList) {
        this.VarInitList=VarInitList;
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
        if(VarInitList!=null) VarInitList.accept(visitor);
        if(VarInit!=null) VarInit.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarInitList!=null) VarInitList.traverseTopDown(visitor);
        if(VarInit!=null) VarInit.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarInitList!=null) VarInitList.traverseBottomUp(visitor);
        if(VarInit!=null) VarInit.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarInitMultipleList(\n");

        if(VarInitList!=null)
            buffer.append(VarInitList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarInit!=null)
            buffer.append(VarInit.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarInitMultipleList]");
        return buffer.toString();
    }
}
