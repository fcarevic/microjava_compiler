// generated with ast extension for cup
// version 0.8
// 29/11/2020 4:46:4


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclsNoMethod extends ClassDecl {

    private ClassName ClassName;
    private ExtendsClause ExtendsClause;
    private ClassFields ClassFields;

    public ClassDeclsNoMethod (ClassName ClassName, ExtendsClause ExtendsClause, ClassFields ClassFields) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.ExtendsClause=ExtendsClause;
        if(ExtendsClause!=null) ExtendsClause.setParent(this);
        this.ClassFields=ClassFields;
        if(ClassFields!=null) ClassFields.setParent(this);
    }

    public ClassName getClassName() {
        return ClassName;
    }

    public void setClassName(ClassName ClassName) {
        this.ClassName=ClassName;
    }

    public ExtendsClause getExtendsClause() {
        return ExtendsClause;
    }

    public void setExtendsClause(ExtendsClause ExtendsClause) {
        this.ExtendsClause=ExtendsClause;
    }

    public ClassFields getClassFields() {
        return ClassFields;
    }

    public void setClassFields(ClassFields ClassFields) {
        this.ClassFields=ClassFields;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassName!=null) ClassName.accept(visitor);
        if(ExtendsClause!=null) ExtendsClause.accept(visitor);
        if(ClassFields!=null) ClassFields.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(ExtendsClause!=null) ExtendsClause.traverseTopDown(visitor);
        if(ClassFields!=null) ClassFields.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
        if(ExtendsClause!=null) ExtendsClause.traverseBottomUp(visitor);
        if(ClassFields!=null) ClassFields.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclsNoMethod(\n");

        if(ClassName!=null)
            buffer.append(ClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExtendsClause!=null)
            buffer.append(ExtendsClause.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassFields!=null)
            buffer.append(ClassFields.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclsNoMethod]");
        return buffer.toString();
    }
}
