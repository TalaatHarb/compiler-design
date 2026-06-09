package net.talaatharb.compiler.symboltable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class SymbolTableTest {

	@Test
	void testInsertLookup() {
		var symbolTable = new SymbolTable();
		var symbol = Symbol.builder().name("x").type(SymbolTypeEnum.VARIABLE_INTEGER).size(new SymbolSize(4))
				.dimension(new SymbolDiminsion(0)).build();
		
		symbolTable.insert(symbol);

		assertEquals(symbol, symbolTable.lookup(symbol.getName()));

	}

	@Test
	void testSetReset() {
		var symbolTable = new SymbolTable();
		String symbolName = "x";
		var symbol = Symbol.builder().name(symbolName).type(SymbolTypeEnum.VARIABLE_INTEGER).size(new SymbolSize(4))
				.dimension(new SymbolDiminsion(0)).build();
		
		symbolTable.insert(symbol);
		
		var newSymbol = Symbol.builder().name(symbolName).type(SymbolTypeEnum.VARIABLE_STRING).size(new SymbolSize(12))
				.dimension(new SymbolDiminsion(0)).build();
		
		symbolTable.set(newSymbol);
		
		assertEquals(newSymbol, symbolTable.lookup(symbolName));
		assertNotEquals(symbol, symbolTable.lookup(symbolName));
		
		symbolTable.reset(symbolName);
		
		assertNotEquals(newSymbol, symbolTable.lookup(symbolName));
		assertEquals(symbol, symbolTable.lookup(symbolName));
	}
	
	@Test
	void testSetResetNoPreviousValue() {
		var symbolTable = new SymbolTable();
		String symbolName = "x";
		var symbol = Symbol.builder().name(symbolName).type(SymbolTypeEnum.VARIABLE_INTEGER).size(new SymbolSize(4))
				.dimension(new SymbolDiminsion(0)).build();
		
		symbolTable.set(symbol);
		symbolTable.reset(symbolName);

		assertEquals(symbol, symbolTable.lookup(symbolName));
		

	}

}
