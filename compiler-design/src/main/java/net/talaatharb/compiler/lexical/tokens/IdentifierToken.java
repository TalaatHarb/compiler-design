package net.talaatharb.compiler.lexical.tokens;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.enums.TokenEnum;

@EqualsAndHashCode(callSuper = true)
@Getter
public class IdentifierToken extends AbstractToken {
	
	private final String name;

	public IdentifierToken(SourceCodeLocation location, String name) {
		super(location);
		this.name = name;
	}
	
	@Override
	public TokenEnum getTokenType() {
		return TokenEnum.IDENTIFIER;
	}
	
	@Override
	public String toString() {
		return "< Identifier, " + name + " >";
	}

}
