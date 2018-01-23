package com.savagesoftworks.summonerssuite;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

import com.savagesoftworks.summonerssuite.components.AccountManager;
import com.savagesoftworks.summonerssuite.components.ComponentManager;
import com.savagesoftworks.summonerssuite.components.FileManager;
import com.savagesoftworks.summonerssuite.components.ComponentManager.ComponentType;
import com.savagesoftworks.summonerssuite.components.FileManager.ResourceType;
import com.savagesoftworks.summonerssuite.components.SceneManager;
import com.savagesoftworks.summonerssuite.components.SceneManager.SceneType;
import com.savagesoftworks.summonerssuite.utility.Utilities;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws IOException {

		JSONObject data = null;
		FileManager fileHandler = (FileManager) ComponentManager.getComponent(ComponentType.FILEHANDLER);
		
		try (
			final InputStream inputStream = fileHandler.getResource("login.txt", ResourceType.DATA).openStream();
			final Reader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		){

			final StringBuilder stringBuilder = new StringBuilder();
			String nextLine;
			
			while((nextLine = bufferedReader.readLine()) != null) {
				
				stringBuilder.append(nextLine);
				
			}
			
			String rawData = stringBuilder.toString();
			data = new JSONObject(rawData);
			
			inputStreamReader.close();
			bufferedReader.close();
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
			
		}

		
		JSONObject playerData = data.getJSONObject("wizard_info");
		JSONArray monsterData = data.getJSONArray("unit_list");
		
		AccountManager ac = (AccountManager) ComponentManager.getComponent(ComponentType.ACCOUNT);
		ac.setPlayerData(playerData);
		ac.setMonsterData(monsterData);		
		
		SceneManager sceneManager = (SceneManager) ComponentManager.getComponent(ComponentType.SCENEMANAGER);
		sceneManager.setRootStage(stage);
		sceneManager.setScene(SceneType.MONSTERS);
		sceneManager.getRootStage().show();

		
		//ImageGrabber.buildImageMaps();
		
	}
	
	@Override
	public void stop() throws Exception {
		
		super.stop();
		
		ComponentManager.shutdown();
		
		Platform.exit();
		System.exit(0);
		
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
}