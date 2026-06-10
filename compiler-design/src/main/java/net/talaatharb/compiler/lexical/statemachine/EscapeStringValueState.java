package net.talaatharb.compiler.lexical.statemachine;

import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.Token;

/**
 * Intermediate state that handles a single escape character inside a string literal.
 * After consuming the escaped character it transitions back to a normal StringValueState.
 */
public class EscapeStringValueState implements LexicalState {

	private final String accumulated;

	public EscapeStringValueState(String accumulated) {
		this.accumulated = accumulated;
	}

	@Override
	public LexicalState nextState(char c) {
		// Append the escaped character (e.g. \n, \t, \\, \")
		return new StringValueState(accumulated + '\\' + c, false);
	}

	@Override
	public boolean isFinal() {
		return false;
	}

	@Override
	public boolean isError() {
		return false;
	}

	@Override
	public Token buildToken(SourceCodeLocation startLocation) {
		return null; // always mid-escape — not a complete token
	}

}
