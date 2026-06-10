package net.talaatharb.compiler.lexical.tokens;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.enums.SpecialCharacterEnum;
import net.talaatharb.compiler.lexical.enums.TokenEnum;

@EqualsAndHashCode(callSuper = true)
@Getter
public class SpecialCharacterToken extends AbstractToken {
	
	private final SpecialCharacterEnum character;

	public SpecialCharacterToken(SourceCodeLocation location, SpecialCharacterEnum character) {
		super(location);
		this.character = character;
	}
	
	@Override
	public TokenEnum getTokenType() {
		return TokenEnum.SPECIAL_CHARACTER;
	}
	
	@Override
	public String toString() {
		return "< Special Character, " + character.toString() + " > @ " + getLocation().toString();
	}

}
