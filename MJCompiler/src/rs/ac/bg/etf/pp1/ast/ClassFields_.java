// generated with ast extension for cup
// version 0.8
// 14/0/2021 0:39:34


package rs.ac.bg.etf.pp1.ast;

public class ClassFields_ extends ClassFields {

    private VarDeclList VarDeclList;

    public ClassFields_ (VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassFields_(\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassFields_]");
        return buffer.toString();
    }
}
