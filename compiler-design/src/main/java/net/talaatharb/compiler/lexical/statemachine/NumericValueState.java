package net.talaatharb.compiler.lexical.statemachine;

import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.Token;
import net.talaatharb.compiler.lexical.enums.NumericTypesEnum;
import net.talaatharb.compiler.lexical.tokens.NumericToken;

public class NumericValueState implements LexicalState {

	private final String accumulated;
	private final NumericTypesEnum numericType;

	public NumericValueState(String initial) {
		this(initial, NumericTypesEnum.INTEGER);
	}

	public NumericValueState(String initial, NumericTypesEnum numericType) {
		this.accumulated = initial;
		this.numericType = numericType;
	}

	@Override
	public LexicalState nextState(char c) {
		if (Character.isDigit(c)) {
			return new NumericValueState(accumulated + c, numericType);
		}
		if (c == '.' && numericType == NumericTypesEnum.INTEGER) {
			return new NumericValueState(accumulated + c, NumericTypesEnum.FLOAT);
		}
		// Terminating character — replay it
		return null;
	}

	@Override
	public boolean isFinal() {
		// Numerics finalize via the null-replay path; EOF flush uses buildToken() != null.
		return false;
	}

	@Override
	public boolean isError() {
		return false;
	}

	@Override
	public Token buildToken(SourceCodeLocation startLocation) {
		return new NumericToken(startLocation, accumulated, numericType);
	}

}