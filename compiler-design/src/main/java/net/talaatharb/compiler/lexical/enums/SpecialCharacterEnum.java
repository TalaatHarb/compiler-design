package net.talaatharb.compiler.lexical.enums;

import java.util.HashMap;
import java.util.Map;

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
	
	private static final Map<String, SpecialCharacterEnum> dictionary = buildDictionary();
	
	@Getter
	private final String representation;
	
	@Override
	public String toString() {
		return representation;
	}
	
	private static final Map<String, SpecialCharacterEnum> buildDictionary(){
		Map<String, SpecialCharacterEnum> map = new HashMap<>();
		for(var keyword : values()) {
			map.put(keyword.getRepresentation(), keyword);
		}
		return map;
	}
	
	public static final SpecialCharacterEnum whichSpecialCharacter(String string) {
		return dictionary.get(string);
	}
	
	
}
