package com.savagesoftworks.summonerssuite.battle;

public final class Formulas {

	public static int calculateDefenseReduction(int defense) {

		return (int) (1000.0 / (1000.0 + (3.0 * defense)));
		
	}
	
}