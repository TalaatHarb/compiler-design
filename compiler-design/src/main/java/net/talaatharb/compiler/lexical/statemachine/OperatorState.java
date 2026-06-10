package net.talaatharb.compiler.lexical.statemachine;

import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.Token;
import net.talaatharb.compiler.lexical.enums.OperatorEnum;
import net.talaatharb.compiler.lexical.tokens.OperatorToken;

public class OperatorState implements LexicalState {

	private final String accumulated;

	public OperatorState(String initial) {
		this.accumulated = initial;
	}

	@Override
	public LexicalState nextState(char c) {
		// '/' followed by '/' starts an inline comment
		if (accumulated.equals("/") && c == '/') {
			return new InlineCommentState();
		}
		// '/' followed by '*' starts a block comment
		if (accumulated.equals("/") && c == '*') {
			return new BlockCommentState();
		}
		// Try to extend the operator
		String extended = accumulated + c;
		if (OperatorEnum.whichOperator(extended) != null) {
			return new OperatorState(extended);
		}
		// Current accumulated is a valid operator — replay c
		if (OperatorEnum.whichOperator(accumulated) != null) {
			return null;
		}
		// Accumulated is not a valid operator (e.g. lone '&') — error
		return new ErrorState();
	}

	@Override
	public boolean isFinal() {
		// Operators always finalize via the null-replay path, not by being immediately final.
		// isFinal() is only used for the EOF flush, handled via buildToken() != null.
		return false;
	}

	@Override
	public boolean isError() {
		return false;
	}

	@Override
	public Token buildToken(SourceCodeLocation startLocation) {
		OperatorEnum op = OperatorEnum.whichOperator(accumulated);
		if (op == null) return null;
		return new OperatorToken(startLocation, op);
	}

}