package com.savagesoftworks.summonerssuite.monsters.effects;

import com.savagesoftworks.summonerssuite.monsters.Monster;
import com.savagesoftworks.summonerssuite.monsters.stats.Stat;

public final class DefenseBuff extends StatModifier {
	
	public DefenseBuff(Monster monster, int duration) {
		
		super(monster, Effect.Type.DEFENSEBUFF, Stat.Type.DEFENSE, 1.5, duration);
		
	}
	
}