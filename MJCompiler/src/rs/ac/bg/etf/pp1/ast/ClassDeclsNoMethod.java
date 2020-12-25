// generated with ast extension for cup
// version 0.8
// 25/11/2020 1:49:9


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclsNoMethod extends ClassDecl {

    private ExtendsClause ExtendsClause;
    private VarDecl VarDecl;

    public ClassDeclsNoMethod (ExtendsClause ExtendsClause, VarDecl VarDecl) {
        this.ExtendsClause=ExtendsClause;
        if(ExtendsClause!=null) ExtendsClause.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public ExtendsClause getExtendsClause() {
        return ExtendsClause;
    }

    public void setExtendsClause(ExtendsClause ExtendsClause) {
        this.ExtendsClause=ExtendsClause;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExtendsClause!=null) ExtendsClause.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtendsClause!=null) ExtendsClause.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtendsClause!=null) ExtendsClause.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclsNoMethod(\n");

        if(ExtendsClause!=null)
            buffer.append(ExtendsClause.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclsNoMethod]");
        return buffer.toString();
    }
}
