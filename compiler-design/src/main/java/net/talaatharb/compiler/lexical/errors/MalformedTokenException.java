package net.talaatharb.compiler.lexical.errors;

import lombok.Getter;
import net.talaatharb.compiler.common.SourceCodeLocation;

public class MalformedTokenException extends LexicalException {

	private static final long serialVersionUID = -5039502122805392772L;
	
	@Getter
	private final SourceCodeLocation location;

	public MalformedTokenException(SourceCodeLocation location) {
		super("Malformed token at location: " + location.toString());
		this.location = location;
	}

}
