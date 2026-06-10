package net.talaatharb.compiler.lexical.statemachine;

import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.Token;

public interface LexicalState {
	
	/**
	 * @return the next state to transition to, or null if no transition is possible
	 *         (null means the current char should be replayed and the current state is done)
	 */
	LexicalState nextState(char c);
	
	/**
	 * @return true if the current state is a final state, false otherwise
	 */
	boolean isFinal();
	
	/**
	 * @return true if the current state is an error state, false otherwise
	 */
	boolean isError();

	/**
	 * Build the token for this final state given the start location tracked by the analyzer.
	 * @return the produced Token, or null if this state does not produce a token (e.g. whitespace)
	 */
	default Token buildToken(SourceCodeLocation startLocation) {
		return null;
	}

	/**
	 * @return true if this is the start state (no token is being accumulated)
	 */
	default boolean isStart() {
		return false;
	}

}
