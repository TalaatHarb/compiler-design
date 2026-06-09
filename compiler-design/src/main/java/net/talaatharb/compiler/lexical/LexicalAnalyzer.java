package net.talaatharb.compiler.lexical;

import java.util.List;

import lombok.NoArgsConstructor;
import net.talaatharb.compiler.common.SourceCodeFile;
import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.enums.KeywordEnum;
import net.talaatharb.compiler.lexical.enums.OperatorEnum;
import net.talaatharb.compiler.lexical.enums.SpecialCharacterEnum;
import net.talaatharb.compiler.lexical.tokens.IdentifierToken;
import net.talaatharb.compiler.lexical.tokens.KeywordToken;
import net.talaatharb.compiler.lexical.tokens.NumericToken;
import net.talaatharb.compiler.lexical.tokens.OperatorToken;
import net.talaatharb.compiler.lexical.tokens.SpecialCharacterToken;

@NoArgsConstructor
public class LexicalAnalyzer {

	public LexicalOutput analyzeFile(SourceCodeFile code) {
		var output = LexicalOutput.builder().code(code).build();

		var tokens = output.getTokens();

		fakeTokens(tokens);
		
		return output;
	}

	private void fakeTokens(List<Token> tokens) {
		tokens.add(new KeywordToken(new SourceCodeLocation(1, 1), KeywordEnum.INT));
		tokens.add(new IdentifierToken(new SourceCodeLocation(1, 4), "calculateX"));
		tokens.add(new SpecialCharacterToken(new SourceCodeLocation(1, 5), SpecialCharacterEnum.OPEN_PARENTHESIS));
		tokens.add(new SpecialCharacterToken(new SourceCodeLocation(1, 6), SpecialCharacterEnum.CLOSE_PARENTHESIS));
		tokens.add(new SpecialCharacterToken(new SourceCodeLocation(1, 8), SpecialCharacterEnum.OPEN_BRACKET));
		
		tokens.add(new KeywordToken(new SourceCodeLocation(2, 1), KeywordEnum.INT));
		tokens.add(new IdentifierToken(new SourceCodeLocation(2, 4), "x"));
		tokens.add(new OperatorToken(new SourceCodeLocation(2, 6), OperatorEnum.ASSIGNMENT));
		tokens.add(new NumericToken(new SourceCodeLocation(2, 8), "1"));
		tokens.add(new OperatorToken(new SourceCodeLocation(2, 10), OperatorEnum.PLUS));
		tokens.add(new NumericToken(new SourceCodeLocation(2, 12), "2"));
		tokens.add(new SpecialCharacterToken(new SourceCodeLocation(2, 13), SpecialCharacterEnum.SEMICOLON));
		
		tokens.add(new KeywordToken(new SourceCodeLocation(3, 1), KeywordEnum.RETURN));
		tokens.add(new IdentifierToken(new SourceCodeLocation(3, 8), "x"));
		tokens.add(new SpecialCharacterToken(new SourceCodeLocation(2, 13), SpecialCharacterEnum.SEMICOLON));
		
		tokens.add(new SpecialCharacterToken(new SourceCodeLocation(1, 8), SpecialCharacterEnum.CLOSE_BRACKET));
	}
}
