package net.talaatharb.compiler.syntax;

import lombok.RequiredArgsConstructor;
import net.talaatharb.compiler.lexical.LexicalOutput;

@RequiredArgsConstructor
public class SyntaxParser {

	public ParserOutput parse(LexicalOutput lexicalOutput) {
		ParserOutput parserOutput = new ParserOutput();
		parserOutput.setLexicalOutput(lexicalOutput);
		
		var parseTree = createParseTree(lexicalOutput);
		
		parserOutput.setParseTree(parseTree);
		return parserOutput;
	}

	private ParseTree createParseTree(LexicalOutput lexicalOutput) {
		return new ParseTree(lexicalOutput.getTokens());
	}
}
