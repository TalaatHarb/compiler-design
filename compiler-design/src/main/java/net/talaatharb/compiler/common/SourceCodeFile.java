package net.talaatharb.compiler.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SourceCodeFile {
	
	@Getter
	private final String fileName;
	
	@Getter
	private final String contents;
	
	
	public SourceCodeFile(String contents) {
		this("unknown", contents);
	}

}
