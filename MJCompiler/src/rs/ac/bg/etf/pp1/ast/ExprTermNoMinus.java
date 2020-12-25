// generated with ast extension for cup
// version 0.8
// 25/11/2020 1:49:10


package rs.ac.bg.etf.pp1.ast;

public class ExprTermNoMinus extends Expr {

    private Term Term;
    private AddopTermExprOption AddopTermExprOption;

    public ExprTermNoMinus (Term Term, AddopTermExprOption AddopTermExprOption) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.AddopTermExprOption=AddopTermExprOption;
        if(AddopTermExprOption!=null) AddopTermExprOption.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public AddopTermExprOption getAddopTermExprOption() {
        return AddopTermExprOption;
    }

    public void setAddopTermExprOption(AddopTermExprOption AddopTermExprOption) {
        this.AddopTermExprOption=AddopTermExprOption;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Term!=null) Term.accept(visitor);
        if(AddopTermExprOption!=null) AddopTermExprOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(AddopTermExprOption!=null) AddopTermExprOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(AddopTermExprOption!=null) AddopTermExprOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprTermNoMinus(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddopTermExprOption!=null)
            buffer.append(AddopTermExprOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprTermNoMinus]");
        return buffer.toString();
    }
}
