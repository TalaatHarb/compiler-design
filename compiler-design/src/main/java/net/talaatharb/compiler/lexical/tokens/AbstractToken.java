package net.talaatharb.compiler.lexical.tokens;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.Token;

@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class AbstractToken implements Token {
	
	@Getter
	private final SourceCodeLocation location;
}
