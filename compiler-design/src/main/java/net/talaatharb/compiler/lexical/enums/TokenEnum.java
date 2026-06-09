package net.talaatharb.compiler.lexical.enums;

public enum TokenEnum {
	/**
	 * if, while, for ...
	 */
	KEYWORD,
	
	/**
	 * Declared by programmer
	 */
	IDENTIFIER,
	
	/**
	 * +, -, =, /, ==, ...
	 */
	OPERATOR,
	
	/**
	 * Numbers such as 12, 35.5, 9E-23, ...
	 */
	NUMERIC_CONSTANT,
	
	/**
	 * Single strings of characters enclosed in quotes
	 */
	STRING_CONSTANT,
	
	/**
	 * Characters used as delimiters like (, ), ;, :, ...
	 */
	SPECIAL_CHARACTER,
	
	/**
	 * Ignored by subsequent phases, used by the programmer to explain the code
	 */
	COMMENT
}
