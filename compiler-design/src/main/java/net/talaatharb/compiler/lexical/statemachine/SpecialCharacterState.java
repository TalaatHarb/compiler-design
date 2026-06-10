package net.talaatharb.compiler.lexical.statemachine;

import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.Token;
import net.talaatharb.compiler.lexical.enums.SpecialCharacterEnum;
import net.talaatharb.compiler.lexical.tokens.SpecialCharacterToken;

public class SpecialCharacterState implements LexicalState {

	private final SpecialCharacterEnum character;

	public SpecialCharacterState(SpecialCharacterEnum specialCharacter) {
		this.character = specialCharacter;
	}

	@Override
	public LexicalState nextState(char c) {
		// Already a single-character token; no further transition
		return null;
	}

	@Override
	public boolean isFinal() {
		return true;
	}

	@Override
	public boolean isError() {
		return false;
	}

	@Override
	public Token buildToken(SourceCodeLocation startLocation) {
		return new SpecialCharacterToken(startLocation, character);
	}

}