package com.savagesoftworks.summonerssuite.parser.datalisteners;

import org.json.JSONArray;
import org.json.JSONObject;

import com.savagesoftworks.summonerssuite.components.AccountManager;
import com.savagesoftworks.summonerssuite.components.ComponentManager;
import com.savagesoftworks.summonerssuite.components.ComponentManager.ComponentType;
import com.savagesoftworks.summonerssuite.components.SceneManager;
import com.savagesoftworks.summonerssuite.components.SceneManager.SceneType;
import javafx.application.Platform;

public class LoginListener implements DataListener {

	public LoginListener() {
		
		System.out.println("LoginListener listening");
		
	}
	
	@Override
	public void update(JSONObject data) {
		
		if(data.has("command") && data.get("command").equals("HubUserLogin")) {

			System.out.println("Valid LoginListener data");
			//JSONObject runeData = data.getJSONObject("runes");
			
			JSONObject playerData = data.getJSONObject("wizard_info");
			JSONArray monsterData = data.getJSONArray("unit_list");

			
			Platform.runLater(() -> {

				AccountManager accountManager = (AccountManager) ComponentManager.getComponent(ComponentType.ACCOUNT);
				accountManager.setPlayerData(playerData);
				accountManager.setMonsterData(monsterData);
				
				SceneManager sceneManager = (SceneManager) ComponentManager.getComponent(ComponentType.SCENEMANAGER);
				sceneManager.setScene(SceneType.MONSTERS);
				
            });
		
		}
		
	}

}
