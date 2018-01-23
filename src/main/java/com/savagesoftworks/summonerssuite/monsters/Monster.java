package com.savagesoftworks.summonerssuite.monsters;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.savagesoftworks.summonerssuite.components.Bestiary;
import com.savagesoftworks.summonerssuite.components.ComponentManager;
import com.savagesoftworks.summonerssuite.components.ComponentManager.ComponentType;
import com.savagesoftworks.summonerssuite.monsters.effects.Effect;
import com.savagesoftworks.summonerssuite.monsters.stats.Stat;

public class Monster {

	public static enum Element { 
		
		NONE(0), WATER(1), FIRE(2), WIND(3), LIGHT(4), DARK(5);
		
	    private static final Map<Integer, Element> intToTypeMap = new HashMap<>();
	    
	    static {
	    	
	        for (Element type : Element.values()) {
	        	
	            intToTypeMap.put(type.value, type);
	            
	        }
	        
	    }
		
		private final Integer value;
		
		private Element(Integer value) {
			
			this.value = value;
			
		}

	    public static Element fromInteger(int i) {
	    	
	    	Element type = intToTypeMap.get(Integer.valueOf(i));
	    	
	        if (type == null) {
	        	
	            return Element.NONE;
	        
	        }
	        
	        return type;
	        
	    }
	    
	    public Integer toInteger() {
	    	
	        return value;
	        
	    }
		
	}
	
	public static enum Grade { 
		
		NONE(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);
		
	    private static final Map<Integer, Grade> intToTypeMap = new HashMap<>();
	    
	    static {
	    	
	        for (Grade type : Grade.values()) {
	        	
	            intToTypeMap.put(type.value, type);
	            
	        }
	        
	    }
		
		private final Integer value;
		
		private Grade(Integer value) {
			
			this.value = value;
			
		}

	    public static Grade fromInteger(int i) {
	    	
	    	Grade type = intToTypeMap.get(Integer.valueOf(i));
	    	
	        if (type == null) {
	        	
	            return Grade.NONE;
	        
	        }
	        
	        return type;
	        
	    }
	    
	    public Integer toInteger() {
	    	
	        return value;
	        
	    }
		
	}
	
	public static enum Type { NONE, ATTACK, DEFENSE, HP, SUPPORT }
	
	private final Integer ID;
	
	private final Element element;
	private final Monster.Type type;
	
	private final String name;
	private final String awakenedName;

	private final Grade baseGrade;
	private Grade currentGrade;
	
	private final Map<Stat.Type, Integer> baseStats;
	private Map<Stat.Type, Stat> currentStats;
	
	private final Map<Effect.Type, Effect> effects;

	//private final Map<Rune.Slot, Rune> runes;
	
	private Integer level;
	
	public Monster(Integer ID, String name, Element element, Monster.Type type) {

		this.ID = ID;
		this.name = awakenedName = name;
		this.element = element;
		this.type = type;
		
		level = 1;
		baseGrade = currentGrade = Grade.ONE;
		
		baseStats = new EnumMap<>(Stat.Type.class);
		currentStats = new EnumMap<>(Stat.Type.class);
		effects = new EnumMap<>(Effect.Type.class);
		//runes = new EnumMap<>(Rune.Slot.class);
		
	}
	
	public Element getElement() {
		
		return element;
		
	}
	
	public Map<Stat.Type, Stat> getStats() {
		
		return currentStats;
		
	}
	
	public void setBaseStat(Stat.Type type, Integer value) {
		
		baseStats.put(type, value);
		
	}
	
	public Integer getBaseStat(Stat.Type type) {
		
		return baseStats.get(type);
		
	}
	
	public Integer getID() {
		
		return ID;
		
	}
	
	public String getAwakenedName() {
		
		return awakenedName;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public Grade getBaseGrade() {
		
		return baseGrade;
		
	}
	
	public Grade getCurrentGrade() {
		
		return currentGrade;
		
	}
	
	public int getLevel() {
		
		return level;
		
	}
	
	public void addEffect(Effect.Type type, int duration) {
		
		if(effects.containsKey(type)) {
			
			effects.get(type).expire();
			effects.remove(type);
			
		}

		final Effect effect = Effect.newEffect(type, duration, this);
		effect.apply();
		effects.put(effect.getType(), effect);
		
	}
	
	public void updateEffects() {
		
		effects.values()
		       .stream()
		       .forEach(Effect::update);
		
	}
	
	public void setLevel(Integer level) {

		this.level = level;
		updateStats();
		
	}
	
	public void updateStats() {
		
		Stat.calculateStats(this);
		
	}
	
	public Stat getStat(Stat.Type type) {

		return currentStats.get(type);
		
	}
	
	public void setStat(Stat.Type type, Stat stat) {
		
		currentStats.put(type, stat);
		
	}
	
	public int getMaxLevel() {
		
		return 10 + (currentGrade.toInteger() * 5);
		
	}
	
	public void setGrade(Monster.Grade grade) {
		
		currentGrade = grade;
		updateStats();
		
	}
	
	public static Monster fromJSON(JSONObject data) {
		
		Element element = Element.fromInteger(data.getInt("attribute"));
		Grade grade = Grade.fromInteger(data.getInt("class"));
		Integer level = data.getInt("unit_level");
		
		Bestiary b = (Bestiary) ComponentManager.getComponent(ComponentType.BESTIARY);
		Integer id = data.getInt("unit_master_id");
		String name = b.getNameFromID(id.toString());

		Monster monster = new Monster(id, name, element, Type.NONE);
		monster.setGrade(grade);
		monster.setLevel(level);
		
		return monster;
		
	}
	
	@Override
	public String toString() {
		
		Stat attack = getStat(Stat.Type.ATTACK);
		
		return "Name: " + name + " " + currentGrade.toInteger() + "* " + "Lvl " + level + "\n" +
		       "Element: " + element.toString() + "\n" +
			   "Type: " + type.toString() + "\n" +
		       "HP: " + getStat(Stat.Type.HP).getAugmentedStat() + "\n" +
		       "Attack: " + attack.getBase() + " + " + attack.getAugmentTotal() + " = " + attack.getAugmentedStat() + "\n" +
		       "Defense: " + getStat(Stat.Type.DEFENSE).getAugmentedStat() +"\n";
		
	}	
	
}