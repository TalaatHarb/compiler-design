package net.talaatharb.compiler;

import lombok.extern.slf4j.Slf4j;
import net.talaatharb.compiler.common.SourceCodeFile;
import net.talaatharb.compiler.lexical.LexicalAnalyzer;
import net.talaatharb.compiler.syntax.SyntaxParser;

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

		var lexicalAnalyzer = new LexicalAnalyzer();
		var lexicalOutput = lexicalAnalyzer.analyzeFile(sourceCodeFile);

		var tokens = lexicalOutput.getTokens();
		log.info("Tokens:");
		tokens.forEach(token -> log.info("{}", token.toString()));
		
		log.info("");
		
		var parser = new SyntaxParser();
		var parserOutput = parser.parse(lexicalOutput);
		
		log.info("Parse tree: {}", parserOutput.getParseTree());
		
	}
}