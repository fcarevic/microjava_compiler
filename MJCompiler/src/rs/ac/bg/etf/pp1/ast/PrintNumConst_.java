// generated with ast extension for cup
// version 0.8
// 14/0/2021 17:36:52


package rs.ac.bg.etf.pp1.ast;

public class PrintNumConst_ extends PrintNumConst {

    private Integer val;

    public PrintNumConst_ (Integer val) {
        this.val=val;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val=val;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintNumConst_(\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintNumConst_]");
        return buffer.toString();
    }
}
