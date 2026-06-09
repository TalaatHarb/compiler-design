package net.talaatharb.compiler.lexical.tokens;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.enums.KeywordEnum;
import net.talaatharb.compiler.lexical.enums.TokenEnum;

@EqualsAndHashCode(callSuper = true)
@Getter
public class KeywordToken extends AbstractToken {
	
	private final KeywordEnum keyword;

	public KeywordToken(SourceCodeLocation location, KeywordEnum keyword) {
		super(location);
		this.keyword = keyword;
	}
	
	@Override
	public TokenEnum getTokenType() {
		return TokenEnum.KEYWORD;
	}
	
	@Override
	public String toString() {
		return "< Keyword, " + keyword.toString() + " >";
	}

}
