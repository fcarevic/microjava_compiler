package rs.ac.bg.etf.pp1;


import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class MyStruct extends  rs.etf.pp1.symboltable.concepts.Struct {
	
	
	public static Struct getBaseStruct(Struct str1, Struct str2) {
		if(str1.equals(str2)) return str1;
		if(str1.getElemType() != null && str1.getElemType().equals(str2)) return str2;
		if(str2.getElemType() != null && str2.getElemType().equals(str1)) return str1;
		return Tab.noType;
				
		
	}
	
	
	public MyStruct(int kind, MyStruct elemType) {
		super(kind, elemType);
		// TODO Auto-generated constructor stub
	}

	public MyStruct(int kind, SymbolDataStructure members) {
		super(kind, members);
		// TODO Auto-generated constructor stub
	}

	public MyStruct(int kind) {
		super(kind);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean assignableTo( rs.etf.pp1.symboltable.concepts.Struct dest) {
		if(this.getElemType()==dest) return true;
		return super.assignableTo(dest);
		
	}
	
	

}
