package com.savagesoftworks.summonerssuite.runes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {

	private final List<Rune> runes;
	
	public Inventory() {
		
		runes = new ArrayList<>();
		
	}
	
	public void addRune(Rune rune) {
		
		runes.add(rune);
		
	}
	
	public List<Rune> getRunes() {
		
		return runes;
		
	}
	
	public List<Rune> getRunesByType(Rune.Type type) {
		
		return runes.parallelStream()
			        .filter(rune -> rune.getType() == type)
		            .collect(Collectors.toList());
		
	}
	
	public List<Rune> getRunesByType(Rune.Type... types) {
		
		List<Rune.Type> runeTypes = Arrays.asList(types);
		
		return runes.parallelStream()
				    .filter(rune -> runeTypes.contains(rune.getType()))
				    .collect(Collectors.toList());
		
	}
	
}