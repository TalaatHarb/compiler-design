package net.talaatharb.compiler.lexical.tokens;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.enums.TokenEnum;

@EqualsAndHashCode(callSuper = true)
@Getter
public class CommentToken extends AbstractToken {

	private final String value;

	public CommentToken(SourceCodeLocation location, String value) {
		super(location);
		this.value = value;
	}

	@Override
	public TokenEnum getTokenType() {
		return TokenEnum.COMMENT;
	}
	
	@Override
	public String toString() {
		return "< Comment, " + value + " > @ " + getLocation().toString();
	}

}
