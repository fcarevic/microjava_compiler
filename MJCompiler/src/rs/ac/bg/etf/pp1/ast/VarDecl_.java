// generated with ast extension for cup
// version 0.8
// 14/0/2021 17:36:51


package rs.ac.bg.etf.pp1.ast;

public class VarDecl_ extends VarDecl {

    private Type Type;
    private VarInitList VarInitList;

    public VarDecl_ (Type Type, VarInitList VarInitList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarInitList=VarInitList;
        if(VarInitList!=null) VarInitList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarInitList getVarInitList() {
        return VarInitList;
    }

    public void setVarInitList(VarInitList VarInitList) {
        this.VarInitList=VarInitList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarInitList!=null) VarInitList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarInitList!=null) VarInitList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarInitList!=null) VarInitList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl_(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarInitList!=null)
            buffer.append(VarInitList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl_]");
        return buffer.toString();
    }
}
