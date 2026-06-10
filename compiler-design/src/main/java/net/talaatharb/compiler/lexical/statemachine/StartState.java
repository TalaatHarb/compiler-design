package net.talaatharb.compiler.lexical.statemachine;

import net.talaatharb.compiler.lexical.enums.OperatorEnum;
import net.talaatharb.compiler.lexical.enums.SpecialCharacterEnum;

public class StartState implements LexicalState {

	@Override
	public LexicalState nextState(char c) {
		String asAString = c + "";
		
		// is the character whitespace?
		if(Character.isWhitespace(c)) {
			return this;
		}
		
		// is the character a special character?
		SpecialCharacterEnum specialCharacter = SpecialCharacterEnum.whichSpecialCharacter(asAString);
		if(specialCharacter != null) {
			return new SpecialCharacterState(specialCharacter);
		}
		
		// is the character the start of an operator?
		OperatorEnum operator = OperatorEnum.whichOperator(asAString);
		if(operator != null) {
			return new OperatorState(asAString);
		}
		
		// is the character the start of a number?
		if(Character.isDigit(c)) {
			return new NumericValueState(asAString);
		}
		
		// is the character '"'?
		if(c == '"') {
			return new StringValueState();
		}
		
		// is the character the start of an identifier or a keyword? an ascii letter or an underscore can start an identifier or a keyword
		if(Character.isLetter(c) || c == '_') {
			return new NameBuildingState(asAString);
		}
		
		return new ErrorState();
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
	public boolean isStart() {
		return true;
	}

}
