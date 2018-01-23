package com.savagesoftworks.summonerssuite.components;

import org.json.JSONArray;
import org.json.JSONObject;

import com.savagesoftworks.summonerssuite.account.MonsterPool;
import com.savagesoftworks.summonerssuite.account.Player;

public class AccountManager extends Component {

	private final Player player;
	
	public AccountManager() {
		
		player = new Player();
		
	}
	
	public Player getPlayer() {
		
		return player;
		
	}
	
	public void setPlayerData(JSONObject playerData) {
		
		player.updateData(playerData);
		
	}
	
	public void setMonsterData(JSONArray monsterData) {
		
		MonsterPool monsters = player.getMonsters();
		monsters.updateData(monsterData);
		
	}

	@Override
	public void shutdown() {}
	
}