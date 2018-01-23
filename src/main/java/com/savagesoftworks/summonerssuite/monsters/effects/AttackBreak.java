package com.savagesoftworks.summonerssuite.monsters.effects;

import com.savagesoftworks.summonerssuite.monsters.Monster;
import com.savagesoftworks.summonerssuite.monsters.stats.Stat;

public final class AttackBreak extends StatModifier {

	public AttackBreak(Monster monster, int duration) {
		
		super(monster, Effect.Type.ATTACKBREAK, Stat.Type.ATTACK, 0.5, duration);
		
	}
	
}