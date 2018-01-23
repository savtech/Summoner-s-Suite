package com.savagesoftworks.summonerssuite.monsters.effects;

import com.savagesoftworks.summonerssuite.monsters.Monster;
import com.savagesoftworks.summonerssuite.monsters.stats.Stat;

public final class AttackBuff extends StatModifier {

	public AttackBuff(Monster monster, int duration) {
		
		super(monster, Effect.Type.ATTACKBUFF, Stat.Type.ATTACK, 1.5 ,duration);
		
	}
	
}