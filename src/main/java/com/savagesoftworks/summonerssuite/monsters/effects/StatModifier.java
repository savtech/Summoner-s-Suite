package com.savagesoftworks.summonerssuite.monsters.effects;

import com.savagesoftworks.summonerssuite.monsters.Monster;
import com.savagesoftworks.summonerssuite.monsters.stats.Stat;

abstract class StatModifier extends Effect {

	protected final Monster monster;
	protected final Stat originalStat;
	protected final Stat.Type statType;
	protected final double modifier;
	
	private int duration;
	
	public StatModifier(Monster monster, Effect.Type effectType, Stat.Type statType, double modifier, int duration) {
		
		super(effectType);
		
		this.monster = monster;
		this.statType = statType;
		originalStat = monster.getStat(statType);
		this.modifier = modifier;
		this.duration = duration;
		
	}
	
	@Override
	public void apply() {

		final int augmentedBaseStatValue = (int) Math.ceil(originalStat.getAugmentedStat() * modifier);
		final Stat stat = new Stat(augmentedBaseStatValue, 0, 0);
		monster.setStat(statType, stat);
		
	}

	@Override
	public void expire() {
		
		monster.setStat(statType, originalStat);
		
	}
	
	@Override
	public void update() {
		
		duration--;
		
		if(duration <= 0) {
			
			expire();
			
		}
		
	}

}