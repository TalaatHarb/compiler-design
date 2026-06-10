package net.talaatharb.compiler;

import lombok.extern.slf4j.Slf4j;
import net.talaatharb.compiler.common.SourceCodeFile;
import net.talaatharb.compiler.lexical.LexicalAnalyzer;

@Slf4j
public class ProjectApplication {
	public static void main(String[] args) {
		log.info("Application Started");

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
		log.info("Source code file: {} \n{}", sourceCodeFile.getFileName(), sourceCodeFile.getContents());

		LexicalAnalyzer analyzer = new LexicalAnalyzer();
		var output = analyzer.analyzeFile(sourceCodeFile);

		var tokens = output.getTokens();
		log.info("Tokens:");
		tokens.forEach(token -> log.info("{}", token.toString()));
	}
}