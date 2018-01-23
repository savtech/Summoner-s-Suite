package com.savagesoftworks.summonerssuite.runes;

import com.savagesoftworks.summonerssuite.monsters.stats.Stat.Type;

public class Stat {

	private static enum Modifier { FLAT, PERCENTAGE }
	public static enum Slot { PRIMARY, SECONDARY, ONE, TWO, THREE, FOUR }
	
	Modifier modifier;
	Type type;
	int value;
	
	public Stat(Type type, Modifier modifier, int value) {
		
		this.modifier = modifier;
		this.type = type;
		this.modifier = modifier;
		this.value = value;
		
	}
	
}