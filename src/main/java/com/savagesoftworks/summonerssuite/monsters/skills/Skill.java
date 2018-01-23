package com.savagesoftworks.summonerssuite.monsters.skills;

import java.util.HashSet;
import java.util.Set;

import com.savagesoftworks.summonerssuite.monsters.effects.Effect;

public class Skill {

	public Set<Effect> effects;
	
	public Skill() {
		
		effects = new HashSet<>();
		
	}
	
	public void addEffect(Effect effect) {
		
		effects.add(effect);
		
	}
	
	
}