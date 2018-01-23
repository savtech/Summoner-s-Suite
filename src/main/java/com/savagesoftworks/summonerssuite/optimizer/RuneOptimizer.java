package com.savagesoftworks.summonerssuite.optimizer;

import com.savagesoftworks.summonerssuite.monsters.Monster;
import com.savagesoftworks.summonerssuite.monsters.stats.Stat;

public class RuneOptimizer implements Optimizer {

	Monster monster;
	
	public RuneOptimizer(Monster monster) {
		
		this.monster = monster;
		
	}
	
	@Override
	public void optimize() {
		
		getCritDamageToAttackRatio();
		
	}
	
	public void getCritDamageToAttackRatio() {
		
		Stat attack = monster.getStat(Stat.Type.ATTACK);
		Stat critDamage = monster.getStat(Stat.Type.CRITDAMAGE);
		
		attack.setFlatModifier(25);
		attack.setPercentAugment(51);

		int attackModifierPercentage = (int) Math.ceil(((double)attack.getAugmentTotal() / (double)attack.getBase()) * 100);
		double attackToCDRatio = Math.abs(attackModifierPercentage - critDamage.getAugmentedStat());
		
		System.out.println("Base attack: " + attack.getBase());
		System.out.println("Flat modifier: " + attack.getFlatAugment());
		System.out.println("Percent modifier: " + attack.getPercentageModifier());
		System.out.println("Total modifiers: " + attack.getAugmentTotal());

		System.out.println("Attack: " + attack.getBase() + "+" + attack.getFlatAugment() + "+" + attack.getPercentageModifier() +"% = " + attack.getAugmentedStat());

		System.out.println("Crit Damage: " + critDamage.getAugmentedStat());
		System.out.println("Attack modifier Percentage: " + attackModifierPercentage);
		System.out.println("Attack to Crit Damage ratio: " + attackToCDRatio);
		
	}
	
}