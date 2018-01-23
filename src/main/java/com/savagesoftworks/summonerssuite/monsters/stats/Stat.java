package com.savagesoftworks.summonerssuite.monsters.stats;

import com.savagesoftworks.summonerssuite.monsters.Monster;

public class Stat {

	public static enum Type { NONE, HP, ATTACK, DEFENSE, SPEED, CRITRATE, CRITDAMAGE, ACCURACY, RESISTANCE }
	public static double[][] statMultipliers = new double[Monster.Grade.values().length][2];
	
	static {
		
		statMultipliers[0][0] = 1.000000000;
		statMultipliers[0][1] = 1.995800000;
		statMultipliers[1][0] = 1.596600000;
		statMultipliers[1][1] = 3.030506406;
		statMultipliers[2][0] = 2.424277400;
		statMultipliers[2][1] = 4.364426603;
		statMultipliers[3][0] = 3.491444400;
		statMultipliers[3][1] = 5.941390935;
		statMultipliers[4][0] = 4.752903200;
		statMultipliers[4][1] = 8.072330795;
		statMultipliers[5][0] = 6.458244900;
		statMultipliers[5][1] = 10.97901633;
		
	}

	private Integer base;
	private Integer flatAugment;
	private Integer percentAugment;
	
	public Stat() {

		base = 0;
		flatAugment = 0;
		percentAugment = 0;
		
	}
	
	public Stat(Integer base) {
		
		this.base = base;
		flatAugment = 0;
		percentAugment = 0;
		
	}
	
	public Stat(Integer base, Integer flatAugment, Integer percentAugment) {
		
		this.base = base;
		this.flatAugment = flatAugment;
		this.percentAugment = percentAugment;
		
	}
	
	public Integer getBase() {
		
		return base;
		
	}
	
	public Integer getFlatAugment() {
		
		return flatAugment;
		
	}
	
	public Integer getPercentAugment() {
		
		return percentAugment;
		
	}
	
	public Integer getPercentageModifier() {
		
		return (int) Math.ceil(base * (percentAugment / 100.0));
	}
	
	public Integer getAugmentTotal() {
		
		return flatAugment + getPercentageModifier();
		
	}
	
	public Integer getAugmentedStat() {
		
		return base + getAugmentTotal();
				
	}
	
	public void setFlatModifier(Integer value) {
		
		flatAugment = value;
		
	}
	
	public void setPercentAugment(Integer value) {
		
		percentAugment = value;
		
	}
	
	public void setBase(Integer base) {
		
		this.base = base;
		
	}
	
	private static void calculateStat(Monster monster, Stat.Type type) {
		
		Stat stat = monster.getStat(type);
		
		Integer maxLevel = monster.getMaxLevel();
		Integer baseGrade = monster.getBaseGrade().toInteger();
		Integer currentGrade = monster.getCurrentGrade().toInteger();

		Integer baseHP = monster.getBaseStat(Stat.Type.HP);
		Integer baseAttack = monster.getBaseStat(Stat.Type.ATTACK);
		Integer baseDefense = monster.getBaseStat(Stat.Type.DEFENSE);
		
		Double weight = (baseHP / 15.0) + baseAttack + baseDefense;
		Integer base = monster.getBaseStat(type);
 	   
 	   if(type == Stat.Type.HP) { 
 		   
 		   base /= 15; 
 		   
 	   }

 	   Double baseValue = (double) Math.round((base * (105 + (15 * baseGrade))) / weight);
 	   Double minimumStat = (double) Math.round(baseValue * statMultipliers[currentGrade - 1][0]);
 	   Double maximumStat = (double) Math.round(baseValue * statMultipliers[currentGrade - 1][1]);
        
        Integer level = monster.getLevel();
        Integer calculatedBase;
        
        if(level == 1) {

        	calculatedBase = minimumStat.intValue();
     	   
        } else if(level == maxLevel) {

        	calculatedBase = maximumStat.intValue();
     	   
        } else {
     	   
     	   Double coefficient = Math.log(maximumStat / minimumStat) / (maxLevel - 1);
     	  calculatedBase = (int) Math.round((minimumStat * Math.exp(-coefficient)) * Math.exp(coefficient * level));

     	   
        }
        
        if(type == Stat.Type.HP) {
     	   
        	calculatedBase *= 15;
     	   
        }

        stat.setBase(calculatedBase);
		
	}
	
	public static void calculateStats(Monster monster) {
		
		calculateStat(monster, Stat.Type.HP);
		calculateStat(monster, Stat.Type.ATTACK);
		calculateStat(monster, Stat.Type.DEFENSE);

	}
	
	@Override
	public String toString() {
		
		return
			   "Base: " + getBase() + "\n" +
			   "Flat Augment: " + getFlatAugment() + "\n" +
			   "Percent Augment: " + getPercentAugment() + "\n" +
			   "Total Augment: " + getAugmentTotal() + "\n" +
			   "Augmented Stat: " + getAugmentedStat();
		
	}
	
}
