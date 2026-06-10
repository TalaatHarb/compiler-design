package net.talaatharb.compiler.lexical.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OperatorEnum {

	PLUS("+"),
	MINUS("-"),
	MULTIPLY("*"),
	DIVIDE("/"),
	LESS_THAN_OR_EQUAL("=<"),
	EQUALS("=="),
	ASSIGNMENT("="),
	GREATER_THAN_OR_EQUAL(">="),
	GREATER_THAN(">"),
	LESS_THAN("<"),
	NOT_EQUAL("!="),
	AND("&&"),
	OR("||"),
	NOT("!"), 
	;
	
	private static final Map<String, OperatorEnum> dictionary = buildDictionary();
	
	@Getter
	private final String representation;
	
	@Override
	public String toString() {
		return representation;
	}
	
	private static final Map<String, OperatorEnum> buildDictionary(){
		Map<String, OperatorEnum> map = new HashMap<>();
		for(var keyword : values()) {
			map.put(keyword.getRepresentation(), keyword);
		}
		return map;
	}
	
	public static final OperatorEnum whichOperator(String string) {
		return dictionary.get(string);
	}
}
