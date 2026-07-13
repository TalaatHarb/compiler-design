package net.talaatharb.compiler.syntax;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.talaatharb.compiler.lexical.LexicalOutput;

@RequiredArgsConstructor
public class ParserOutput {
	
	@Getter
	@Setter
	private LexicalOutput lexicalOutput;
	
	@Getter
	@Setter
	private ParseTree parseTree;

}
