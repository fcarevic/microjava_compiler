// generated with ast extension for cup
// version 0.8
// 11/0/2021 20:14:37


package rs.ac.bg.etf.pp1.ast;

public class NoMethodDeclList_ extends NoMethodDeclList {

    private NoMethodDeclList NoMethodDeclList;
    private NoMethodDecl NoMethodDecl;

    public NoMethodDeclList_ (NoMethodDeclList NoMethodDeclList, NoMethodDecl NoMethodDecl) {
        this.NoMethodDeclList=NoMethodDeclList;
        if(NoMethodDeclList!=null) NoMethodDeclList.setParent(this);
        this.NoMethodDecl=NoMethodDecl;
        if(NoMethodDecl!=null) NoMethodDecl.setParent(this);
    }

    public NoMethodDeclList getNoMethodDeclList() {
        return NoMethodDeclList;
    }

    public void setNoMethodDeclList(NoMethodDeclList NoMethodDeclList) {
        this.NoMethodDeclList=NoMethodDeclList;
    }

    public NoMethodDecl getNoMethodDecl() {
        return NoMethodDecl;
    }

    public void setNoMethodDecl(NoMethodDecl NoMethodDecl) {
        this.NoMethodDecl=NoMethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NoMethodDeclList!=null) NoMethodDeclList.accept(visitor);
        if(NoMethodDecl!=null) NoMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NoMethodDeclList!=null) NoMethodDeclList.traverseTopDown(visitor);
        if(NoMethodDecl!=null) NoMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NoMethodDeclList!=null) NoMethodDeclList.traverseBottomUp(visitor);
        if(NoMethodDecl!=null) NoMethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoMethodDeclList_(\n");

        if(NoMethodDeclList!=null)
            buffer.append(NoMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NoMethodDecl!=null)
            buffer.append(NoMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NoMethodDeclList_]");
        return buffer.toString();
    }
}
