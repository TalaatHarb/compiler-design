package net.talaatharb.compiler.lexical.statemachine;

import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.Token;
import net.talaatharb.compiler.lexical.tokens.CommentToken;

public class InlineCommentState implements LexicalState {

	private final String accumulated;

	public InlineCommentState() {
		this("");
	}

	public InlineCommentState(String accumulated) {
		this.accumulated = accumulated;
	}

	@Override
	public LexicalState nextState(char c) {
		if (c == '\n') {
			// Newline ends the comment — replay it so the main loop can update the row counter
			return null;
		}
		return new InlineCommentState(accumulated + c);
	}

	@Override
	public boolean isFinal() {
		// Finalizes via the null-replay path on newline; EOF flush uses buildToken() != null.
		return false;
	}

	@Override
	public boolean isError() {
		return false;
	}

	@Override
	public Token buildToken(SourceCodeLocation startLocation) {
		return new CommentToken(startLocation, accumulated);
	}

}