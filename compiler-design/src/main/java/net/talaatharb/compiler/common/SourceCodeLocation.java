package net.talaatharb.compiler.common;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SourceCodeLocation implements Serializable{

	private static final long serialVersionUID = -7114751395967961874L;
	
	private final String fileFullName;
	private final int row;
	private final int column;
	
	@Override
	public String toString() {
		return String.format("%s:%d:%d", fileFullName, row, column);
	}
}
