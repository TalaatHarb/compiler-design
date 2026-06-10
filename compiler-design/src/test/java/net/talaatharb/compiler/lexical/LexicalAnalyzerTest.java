package net.talaatharb.compiler.lexical;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import net.talaatharb.compiler.common.SourceCodeFile;

class LexicalAnalyzerTest {

	// -----------------------------------------------------------------------
	// Helpers
	// -----------------------------------------------------------------------

	private LexicalOutput analyze(String source) {
		return new LexicalAnalyzer().analyzeFile(new SourceCodeFile(source));
	}

	// -----------------------------------------------------------------------
	// Existing tests
	// -----------------------------------------------------------------------

	@Test
	void testAnalyzeFile1() {
		var fileContents = """
				int calculateX() {
					int x = 1 + 2;
					return x;
				}
				""";
		var output = analyze(fileContents);
		assertEquals(16, output.getTokens().size());
		assertEquals(0, output.getErrors().size());
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
		var output = analyze(fileContents);
		assertEquals(31, output.getTokens().size());
		assertEquals(0, output.getErrors().size());
	}

	// -----------------------------------------------------------------------
	// Parameterized — all tokenization cases
	// -----------------------------------------------------------------------

	static Stream<Arguments> tokenizationCases() {
		return Stream.of(
			// description                          source                         tokens  errors
			// -- string literals --
			Arguments.of("simple string",           "int msg = \"hello world\";",  5,      0),
			Arguments.of("escape sequence",         "\"say \\\"hi\\\"\"",          1,      0),
			Arguments.of("empty string",            "\"\"",                         1,      0),
			// -- lexical errors --
			Arguments.of("unterminated string",     "\"hello",                      0,      1),
			Arguments.of("unterminated block cmt",  "/* hello world",               0,      1),
			Arguments.of("unrecognized char @",     "@variable",                    1,      1),
			Arguments.of("invalid operator &",      "int x & y;",                  4,      1),
			Arguments.of("multiple errors @x #y",  "@x # y",                       2,      2)
		);
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("tokenizationCases")
	void testTokenization(String description, String source, int expectedTokens, int expectedErrors) {
		var output = analyze(source);
		assertEquals(expectedTokens, output.getTokens().size(), "token count — " + description);
		assertEquals(expectedErrors, output.getErrors().size(), "error count — " + description);
	}

}