package net.talaatharb.compiler.lexical.tokens;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.enums.NumericTypesEnum;
import net.talaatharb.compiler.lexical.enums.TokenEnum;

@EqualsAndHashCode(callSuper = true)
@Getter
public class NumericToken extends AbstractToken {

	private final NumericTypesEnum type;

	private final String value;

	public NumericToken(SourceCodeLocation location) {
		this(location, "0");
	}
	
	public NumericToken(SourceCodeLocation location, String value) {
		this(location, value, NumericTypesEnum.INTEGER);
	}
	
	public NumericToken(SourceCodeLocation location, String value, NumericTypesEnum type) {
		super(location);
		this.type = type;
		this.value = value;
	}

	@Override
	public TokenEnum getTokenType() {
		return TokenEnum.NUMERIC_CONSTANT;
	}
	
	@Override
	public String toString() {
		return "< Number, " + type + ", " + value + " >";
	}

}
