// generated with ast extension for cup
// version 0.8
// 2/0/2021 17:43:55


package rs.ac.bg.etf.pp1.ast;

public class SwitchStatement extends Statement {

    private SwitchClause SwitchClause;
    private CaseList CaseList;

    public SwitchStatement (SwitchClause SwitchClause, CaseList CaseList) {
        this.SwitchClause=SwitchClause;
        if(SwitchClause!=null) SwitchClause.setParent(this);
        this.CaseList=CaseList;
        if(CaseList!=null) CaseList.setParent(this);
    }

    public SwitchClause getSwitchClause() {
        return SwitchClause;
    }

    public void setSwitchClause(SwitchClause SwitchClause) {
        this.SwitchClause=SwitchClause;
    }

    public CaseList getCaseList() {
        return CaseList;
    }

    public void setCaseList(CaseList CaseList) {
        this.CaseList=CaseList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SwitchClause!=null) SwitchClause.accept(visitor);
        if(CaseList!=null) CaseList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SwitchClause!=null) SwitchClause.traverseTopDown(visitor);
        if(CaseList!=null) CaseList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SwitchClause!=null) SwitchClause.traverseBottomUp(visitor);
        if(CaseList!=null) CaseList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchStatement(\n");

        if(SwitchClause!=null)
            buffer.append(SwitchClause.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseList!=null)
            buffer.append(CaseList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchStatement]");
        return buffer.toString();
    }
}
