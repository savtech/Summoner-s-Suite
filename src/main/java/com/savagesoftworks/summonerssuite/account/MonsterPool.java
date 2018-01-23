package com.savagesoftworks.summonerssuite.account;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.savagesoftworks.summonerssuite.monsters.Monster;

public class MonsterPool {
	
	private List<Monster> monsters;
	
	public MonsterPool() {
		
		monsters = new ArrayList<>();
		
	}
	
	public Integer size() {
		
		return monsters.size();
		
	}
	
	public void updateData(JSONArray data) {

		monsters.clear();
		parseData(data);
		
	}
	
	private void parseData(JSONArray data) {

		data.forEach( rawData -> {
			
			JSONObject monsterData = (JSONObject) rawData;
			Monster monster = Monster.fromJSON(monsterData);
			
			monsters.add(monster);
			
		});
	
	}
	
	public List<Monster> getMonsters() {
		
		return monsters;
		
	}
	
}