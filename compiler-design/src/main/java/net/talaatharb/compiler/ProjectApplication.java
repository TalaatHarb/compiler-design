package net.talaatharb.compiler;

import lombok.extern.slf4j.Slf4j;
import net.talaatharb.compiler.common.SourceCodeFile;
import net.talaatharb.compiler.lexical.LexicalAnalyzer;

@Slf4j
public class ProjectApplication {
	public static void main(String[] args) {
		log.info("Application Started");

		var fileContents = """
				int calculateX() {
					int x = 1 + 2;
					return x;
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