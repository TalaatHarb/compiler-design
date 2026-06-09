package net.talaatharb.compiler.lexical.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public enum KeywordEnum {

	/**
	 * True literal
	 */
	TRUE("true"),
	
	/**
	 * False literal
	 */
	FALSE("false"),
	
	/**
	 * IF keyword
	 */
	IF("if"),
	
	/**
	 * ELSE keyword
	 */
	ELSE("else"),
	
	/**
	 * WHILE keyword
	 */
	WHILE("while"),
	
	/**
	 * FOR keyword
	 */
	FOR("for"),
	
	/**
	 * VOID keyword
	 */
	VOID("void"),
	
	/**
	 * INT keyword
	 */
	INT("int"),
	
	/**
	 * CONST keyword
	 */
	CONST("const"),
	
	/**
	 * RETURN keyword
	 */
	RETURN("return");

	private static final Map<String, KeywordEnum> dictionary = buildDictionary();

	@Getter
	private final String representation;

	@Override
	public String toString() {
		return representation;
	}
	
	private static final Map<String, KeywordEnum> buildDictionary(){
		Map<String, KeywordEnum> map = new HashMap<>();
		for(var keyword : values()) {
			map.put(keyword.getRepresentation(), keyword);
		}
		return map;
	}

	public static final KeywordEnum whichKeyword(String string) {
		return dictionary.get(string);
	}
}
