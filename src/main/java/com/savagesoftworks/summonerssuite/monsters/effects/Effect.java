package com.savagesoftworks.summonerssuite.monsters.effects;

import com.savagesoftworks.summonerssuite.monsters.Monster;

public abstract class Effect {
	
	public static enum Type { NIL, ATTACKBUFF, ATTACKBREAK, DEFENSEBUFF, DEFENSEBREAK }
	
	private Effect.Type type;
	
	public Effect(Effect.Type type) {
		
		this.type = type;
		
	}
	
	public abstract void apply();
	public abstract void expire();
	public abstract void update();
	
	public static Effect newEffect(Effect.Type type, int duration, Monster monster) {
		
		Effect effect;
		
		switch(type) {
		default:
		case NIL:
			effect = new NilEffect();
			break;
		case ATTACKBUFF:
			effect = new AttackBuff(monster, duration);
			break;
		case ATTACKBREAK:
			effect = new AttackBreak(monster, duration);
			break;
		case DEFENSEBUFF:
			effect = new DefenseBuff(monster, duration);
			break;
		case DEFENSEBREAK:
			effect = new DefenseBreak(monster, duration);
			break;
		}
		
		return effect;
		
	}
	
	public Effect.Type getType() {
		
		return type;
		
	}
	
}