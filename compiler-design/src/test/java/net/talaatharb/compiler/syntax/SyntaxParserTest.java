package net.talaatharb.compiler.syntax;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import net.talaatharb.compiler.common.SourceCodeFile;
import net.talaatharb.compiler.lexical.LexicalAnalyzer;
import net.talaatharb.compiler.lexical.LexicalOutput;

class SyntaxParserTest {

	@Test
	void testBasicParse() {
		String fileContents = "int x = 1 + 2;";
		var lexicalOutput = lexicalAnalysis(fileContents);
		var parser = new SyntaxParser();
		var parserOutput = parser.parse(lexicalOutput);
		assertNotNull(parserOutput);
		assertNotNull(parserOutput.getParseTree());
		/*
		 *                 =
		 *                 /\
		 *                x  +
		 *                   /\
		 *                  1  2
		 */
		
	}
	
	private LexicalOutput lexicalAnalysis(String source) {
		return new LexicalAnalyzer().analyzeFile(new SourceCodeFile(source));
	}

}
