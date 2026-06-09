package net.talaatharb.compiler.symboltable;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SymbolTable implements SymbolTableInterface {

	private final Map<String, Symbol> table = new HashMap<>();
	private final Map<String, Stack<Symbol>> shadowValues = new HashMap<>();

	@Override
	public void insert(Symbol symbol) {
		table.put(symbol.getName(), symbol);
	}

	@Override
	public Symbol lookup(String symbolName) {
		return table.get(symbolName);
	}

	@Override
	public void set(Symbol symbol) {
		String symbolName = symbol.getName();
		var oldValue = lookup(symbolName);
		if (oldValue != null) {
			var oldValuesStack = shadowValues.computeIfAbsent(symbolName, _ -> new Stack<>());
			oldValuesStack.push(oldValue);
		}
		insert(symbol);
	}

	@Override
	public void reset(String symbolName) {
		var oldValuesStack = shadowValues.get(symbolName);
		if (oldValuesStack == null || oldValuesStack.isEmpty()) {
			return;
		}

		insert(oldValuesStack.pop());
	}

}
