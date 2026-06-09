package net.talaatharb.compiler.lexical.errors;

import lombok.Getter;
import net.talaatharb.compiler.common.SourceCodeLocation;

public class TokenNameTooLongException extends LexicalException {
	private static final long serialVersionUID = 5302867449186033001L;
	
	@Getter
	private final SourceCodeLocation location;
		
	public TokenNameTooLongException(SourceCodeLocation location) {
		super("Token with too long of a name at location: " + location.toString());
		this.location = location;
	}
}
