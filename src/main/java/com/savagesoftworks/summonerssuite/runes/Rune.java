package com.savagesoftworks.summonerssuite.runes;

import java.util.EnumMap;
import java.util.Map;

public class Rune {

	public static enum Slot { ONE, TWO, THREE, FOUR, FIVE, SIX }
	private static enum Quality { WHITE, BLUE, PURPLE, ORANGE }
	public static enum Type { 
		ENERGY, FATAL, BLADE, SWIFT, FOCUS, 
		GUARD, ENDURE, SHIELD, REVENGE, WILL, 
		NEMESIS, VAMPIRE, DESTROY, DESPAIR, VIOLENT, 
		RAGE 
	}
	
	private Type type;
	private Quality quality;
	private Map<Stat.Slot, Stat> stats;
	
	public Rune(Rune.Type type) {

		this.type = type;

		quality = Quality.WHITE;
		stats = new EnumMap<>(Stat.Slot.class);

	}
	
	public Rune(Rune.Type type, Quality quality, Map<Stat.Slot, Stat> stats) {
		
		this.type = type;
		this.quality = quality;
		this.stats = stats;
		
	}
	
	public void addStat(Stat.Slot slot, Stat stat) {
		
		stats.put(slot, stat);
		
	}
	
	public void setQuality(Quality quality) {
		
		this.quality = quality;
		
	}
	
	public Quality getQuality() {
		
		return quality;
		
	}
	
	public Rune.Type getType() {
		
		return type;
		
	}
	
}