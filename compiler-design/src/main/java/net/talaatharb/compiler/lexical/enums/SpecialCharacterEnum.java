package net.talaatharb.compiler.lexical.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SpecialCharacterEnum {

	SEMICOLON(";"),
	COMMA(","),
	OPEN_BRACKET("{"),
	CLOSE_BRACKET("}"),
	OPEN_PARENTHESIS("("),
	CLOSE_PARENTHESIS(")"),
	;
	
	@Getter
	private final String representation;
	
	@Override
	public String toString() {
		return representation;
	}
}
