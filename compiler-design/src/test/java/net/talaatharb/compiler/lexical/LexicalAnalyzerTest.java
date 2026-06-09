package net.talaatharb.compiler.lexical;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.talaatharb.compiler.common.SourceCodeFile;

class LexicalAnalyzerTest {

	@Test
	void testAnalyzeFile() {
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

}
