package net.talaatharb.compiler.lexical;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.talaatharb.compiler.common.SourceCodeFile;
import net.talaatharb.compiler.lexical.errors.LexicalException;

@Getter
@RequiredArgsConstructor
@Builder
public class LexicalOutput {

	private final SourceCodeFile code;
	
	private final List<LexicalException> errors = new ArrayList<>();
	
	private final List<Token> tokens = new ArrayList<>();
}
