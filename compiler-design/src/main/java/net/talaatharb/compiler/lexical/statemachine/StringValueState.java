package net.talaatharb.compiler.lexical.statemachine;

import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.Token;
import net.talaatharb.compiler.lexical.tokens.StringToken;

public class StringValueState implements LexicalState {

	private final String accumulated;
	private final boolean closed;

	public StringValueState() {
		this("", false);
	}

	public StringValueState(String accumulated, boolean closed) {
		this.accumulated = accumulated;
		this.closed = closed;
	}

	@Override
	public LexicalState nextState(char c) {
		if (closed) {
			// Already complete — replay the character
			return null;
		}
		if (c == '"') {
			// Closing quote found — string is complete
			return new StringValueState(accumulated, true);
		}
		if (c == '\\') {
			// Start of escape sequence — include the backslash; the next char will be included too
			return new EscapeStringValueState(accumulated);
		}
		return new StringValueState(accumulated + c, false);
	}

	@Override
	public boolean isFinal() {
		return closed;
	}

	@Override
	public boolean isError() {
		return false;
	}

	@Override
	public Token buildToken(SourceCodeLocation startLocation) {
		if (!closed) return null; // unterminated string — caller should treat as error
		return new StringToken(startLocation, accumulated);
	}

}