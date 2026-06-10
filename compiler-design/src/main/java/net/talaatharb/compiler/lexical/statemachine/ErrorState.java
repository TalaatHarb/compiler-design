package net.talaatharb.compiler.lexical.statemachine;

public class ErrorState implements LexicalState {

	@Override
	public LexicalState nextState(char c) {
		return null;
	}

	@Override
	public boolean isFinal() {
		return true;
	}

	@Override
	public boolean isError() {
		return true;
	}

}
