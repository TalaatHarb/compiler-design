package net.talaatharb.compiler.lexical;

import lombok.NoArgsConstructor;
import net.talaatharb.compiler.common.SourceCodeFile;
import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.errors.MalformedTokenException;
import net.talaatharb.compiler.lexical.statemachine.LexicalState;
import net.talaatharb.compiler.lexical.statemachine.StartState;

@NoArgsConstructor
public class LexicalAnalyzer {

	public LexicalOutput analyzeFile(SourceCodeFile code) {
		var fileName = code.getFileName();
		var output = LexicalOutput.builder().code(code).build();

		var tokens = output.getTokens();
		var errors = output.getErrors();

		String source = code.getContents();
		LexicalState state = new StartState();

		int row = 1;
		int col = 1;
		int startRow = 1;
		int startCol = 1;

		int i = 0;
		int length = source.length();
		while (i < length) {
			char c = source.charAt(i);

			// While in the start state, continuously update the token start position
			// so it points to the first character of the upcoming token
			if (state.isStart()) {
				startRow = row;
				startCol = col;
			}

			LexicalState next = state.nextState(c);

			if (next == null) {
				// The current state could not consume 'c' — finalize the current token (if any)
				// and replay 'c'
				if (!state.isStart()) {
					Token t = state.buildToken(new SourceCodeLocation(fileName, startRow, startCol));
					if (t != null) {
						tokens.add(t);
					} else {
						errors.add(new MalformedTokenException(new SourceCodeLocation(fileName, startRow, startCol)));
					}
				}
				state = new StartState();
				// Do NOT advance i — replay 'c' from the start state

			} else if (next.isError()) {
				// Unrecognized / malformed character sequence
				errors.add(new MalformedTokenException(new SourceCodeLocation(fileName, startRow, startCol)));
				state = new StartState();
				// Advance past the bad character
				if (c == '\n') {
					row++;
					col = 1;
				} else {
					col++;
				}
				i++;

			} else if (next.isFinal()) {
				// 'c' was consumed and immediately completes a single-char token (e.g.
				// SpecialCharacterState)
				Token t = next.buildToken(new SourceCodeLocation(fileName, startRow, startCol));
				if (t != null) {
					tokens.add(t);
				}
				state = new StartState();
				if (c == '\n') {
					row++;
					col = 1;
				} else {
					col++;
				}
				i++;

			} else {
				// Still accumulating
				state = next;
				if (c == '\n') {
					row++;
					col = 1;
				} else {
					col++;
				}
				i++;
			}
		}

		// EOF flush — finalize any token still being accumulated
		if (!state.isStart()) {
			Token t = state.buildToken(new SourceCodeLocation(fileName, startRow, startCol));
			if (t != null) {
				tokens.add(t);
			} else {
				// Unterminated string or block comment
				errors.add(new MalformedTokenException(new SourceCodeLocation(fileName, startRow, startCol)));
			}
		}

		return output;
	}
}