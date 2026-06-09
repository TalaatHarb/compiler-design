package net.talaatharb.compiler.lexical.tokens;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.enums.OperatorEnum;
import net.talaatharb.compiler.lexical.enums.TokenEnum;

@EqualsAndHashCode(callSuper = true)
@Getter
public class OperatorToken extends AbstractToken {

	private final OperatorEnum operator;

	public OperatorToken(SourceCodeLocation location, OperatorEnum operator) {
		super(location);
		this.operator = operator;
	}

	@Override
	public TokenEnum getTokenType() {
		return TokenEnum.OPERATOR;
	}
	
	@Override
	public String toString() {
		return "< Operator, " + operator.toString() +" >";
	}

}
