package net.talaatharb.compiler.lexical;

import net.talaatharb.compiler.common.SourceCodeLocation;
import net.talaatharb.compiler.lexical.enums.TokenEnum;

public interface Token {

	/**
	 * Get the type of the token from the possible values of {@code TokenEnum}
	 * @return Token type
	 */
	TokenEnum getTokenType();
	
	/**
	 * Get the location of the token in the source code
	 * @return source code location of the token
	 */
	SourceCodeLocation getLocation();
}
