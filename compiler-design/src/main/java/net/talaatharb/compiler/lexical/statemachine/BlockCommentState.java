package net.talaatharb.compiler.lexical.statemachine;

import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.Token;
import net.talaatharb.compiler.lexical.tokens.CommentToken;

public class BlockCommentState implements LexicalState {

	private final String accumulated;
	private final boolean pendingStar;
	private final boolean complete;

	public BlockCommentState() {
		this("", false, false);
	}

	public BlockCommentState(String accumulated, boolean pendingStar, boolean complete) {
		this.accumulated = accumulated;
		this.pendingStar = pendingStar;
		this.complete = complete;
	}

	@Override
	public LexicalState nextState(char c) {
		if (complete) {
			// Already closed — replay this character
			return null;
		}
		if (pendingStar) {
			if (c == '/') {
				// The pending '*' was the closing '*' — comment is done
				return new BlockCommentState(accumulated, false, true);
			}
			if (c == '*') {
				// Another star — keep the previous star in accumulated, stay pending
				return new BlockCommentState(accumulated + '*', true, false);
			}
			// The pending star was just content
			return new BlockCommentState(accumulated + '*' + c, false, false);
		}
		if (c == '*') {
			// Might be the start of '*/' — hold off on adding it
			return new BlockCommentState(accumulated, true, false);
		}
		return new BlockCommentState(accumulated + c, false, false);
	}

	@Override
	public boolean isFinal() {
		return complete;
	}

	@Override
	public boolean isError() {
		return false;
	}

	@Override
	public Token buildToken(SourceCodeLocation startLocation) {
		if (!complete) return null; // unterminated block comment — caller should treat as error
		return new CommentToken(startLocation, accumulated);
	}

}