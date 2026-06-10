package net.talaatharb.compiler.lexical;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.talaatharb.compiler.common.SourceCodeFile;

class LexicalAnalyzerTest {

	@Test
	void testAnalyzeFile1() {
		var fileContents = """
				int calculateX() {
					int x = 1 + 2;
					return x;
				}
				""";
		SourceCodeFile sourceCodeFile = new SourceCodeFile("testFile.txt", fileContents);
		LexicalAnalyzer analyzer = new LexicalAnalyzer();
			var output = analyzer.analyzeFile(sourceCodeFile);
			var tokens = output.getTokens();
			assertEquals(16, tokens.size());

	}
	
	@Test
	void testAnalyzeFile2() {
		var fileContents = """
				/** Function to calculate the value of y based on some math
				 *  @param a the first number
				 *  @param b the second number
				 *  @return the value of y
				 */
				int calculateY(int a, int b) {
					int y = a * b + (a - b) / 2; // some math
					return y;
				}
				""";
		SourceCodeFile sourceCodeFile = new SourceCodeFile("testFile.txt", fileContents);
		LexicalAnalyzer analyzer = new LexicalAnalyzer();
			var output = analyzer.analyzeFile(sourceCodeFile);
			var tokens = output.getTokens();
			assertEquals(31, tokens.size());

	}

}
