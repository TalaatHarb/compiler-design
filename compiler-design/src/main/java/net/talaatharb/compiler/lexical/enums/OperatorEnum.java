package net.talaatharb.compiler.lexical.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OperatorEnum {

	PLUS("+"),
	MINUS("-"),
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
	
	@Getter
	private final String representation;
	
	@Override
	public String toString() {
		return representation;
	}
}
