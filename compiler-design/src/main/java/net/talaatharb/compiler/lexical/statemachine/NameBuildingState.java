package net.talaatharb.compiler.lexical.statemachine;

import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.Token;
import net.talaatharb.compiler.lexical.enums.KeywordEnum;
import net.talaatharb.compiler.lexical.tokens.IdentifierToken;
import net.talaatharb.compiler.lexical.tokens.KeywordToken;

public class NameBuildingState implements LexicalState {

	private final String accumulated;

	public NameBuildingState(String initial) {
		this.accumulated = initial;
	}

	@Override
	public LexicalState nextState(char c) {
		if (Character.isLetterOrDigit(c) || c == '_') {
			return new NameBuildingState(accumulated + c);
		}
		// Terminating character — replay it
		return null;
	}

	@Override
	public boolean isFinal() {
		// Names finalize via the null-replay path; EOF flush uses buildToken() != null.
		return false;
	}

	@Override
	public boolean isError() {
		return false;
	}

	@Override
	public Token buildToken(SourceCodeLocation startLocation) {
		KeywordEnum keyword = KeywordEnum.whichKeyword(accumulated);
		if (keyword != null) {
			return new KeywordToken(startLocation, keyword);
		}
		return new IdentifierToken(startLocation, accumulated);
	}

}