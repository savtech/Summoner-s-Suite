package com.savagesoftworks.summonerssuite.monsters.effects;

import com.savagesoftworks.summonerssuite.monsters.Monster;
import com.savagesoftworks.summonerssuite.monsters.stats.Stat;

public final class DefenseBreak extends StatModifier {
	
	public DefenseBreak(Monster monster, int duration) {
		
		super(monster, Effect.Type.DEFENSEBREAK, Stat.Type.DEFENSE, 0.5, duration);
		
	}
	
}