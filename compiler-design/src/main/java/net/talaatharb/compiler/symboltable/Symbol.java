package net.talaatharb.compiler.symboltable;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.talaatharb.compiler.common.SourceCodeLocation;


@EqualsAndHashCode
@Getter
@Builder
@AllArgsConstructor
public class Symbol {

	@Getter
	private final String name;
	
	@Getter
	private final SymbolTypeEnum type;
	
	@Getter
	private final SymbolSize size;
	
	@Getter
	private final SymbolDiminsion dimension;
	
	private final List<SourceCodeLocation> linesOfUsage = new ArrayList<>();
	
	@Getter
	@Setter
	private SourceCodeLocation lineOfDeclaration;
	
	@Getter
	@Setter
	private SymbolAddress address;
	
	
	public void addLineOfUsage(SourceCodeLocation newLocation) {
		linesOfUsage.add(newLocation);
	}
	
	public void removeLineOfUsage(SourceCodeLocation location) {
		linesOfUsage.remove(location);
	}
	
	public void clearLinesOfUsage() {
		linesOfUsage.clear();
	}
	
	public List<SourceCodeLocation> getLinesOfUsage(){
		return List.copyOf(linesOfUsage);
	}
}
