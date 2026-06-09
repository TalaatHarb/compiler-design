package net.talaatharb.compiler.symboltable;

public interface SymbolTableInterface {

	void insert(Symbol symbol);
	
	Symbol lookup(String name);
	
	void set(Symbol symbol);
	
	void reset (String name);
}
