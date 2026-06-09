package net.talaatharb.compiler.lexical.errors;

public abstract class LexicalException extends RuntimeException {
	private static final long serialVersionUID = 4087370033134709983L;
	
	LexicalException(String message) {
		super("[Lexical Error] " + message);
	}
}
