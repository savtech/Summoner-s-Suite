package com.savagesoftworks.summonerssuite.components;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.savagesoftworks.summonerssuite.components.ComponentManager.ComponentType;
import com.savagesoftworks.summonerssuite.components.FileManager.ResourceType;
import com.savagesoftworks.summonerssuite.utility.Utilities;

public class Bestiary extends Component {
	
	private static final String RESOURCE_DIRECTORY = "bestiary/";
	private static final String BESTIARY_FILE = RESOURCE_DIRECTORY + "bestiary";
	private static final String NAMES_FILE = RESOURCE_DIRECTORY + "names";
	
	private JSONObject bestiary;
	private JSONObject names;
	
	public Bestiary() {

		bestiary = Utilities.loadJSON(BESTIARY_FILE);
		names = Utilities.loadJSON(NAMES_FILE);
		
	}
	
	public String getBaseMonsterNameFromID(String id) {
		
		String name = "Unknown";
		
		try {
			
			if(names.has(id.substring(0, 3))) {
				
				return names.getString(id.substring(0, 3));
				
			}
			
		} catch(JSONException e) {
			
			e.printStackTrace();
			
		}
		
		return name;
		
	}
	
	public String getNameFromID(String id) {
		
		String name = "Unknown";
		
		try {

			if(names.has(id)) {
				
				return names.getString(id);
				
			}
			
			if(names.has(id.substring(0, 3))) {
				
				return names.getString(id.substring(0, 3));
				
			}
			
		} catch(JSONException e) {
			
			e.printStackTrace();
			
		}
		
		return name;
		
	}
	
	public void printNames() {

		System.out.println(names.toString(4));
		
	}
	
	public long getEntryCount() {
		
		return bestiary.length();
		
	}	
	
	public void printMonsters() {
		
		JSONArray monsters = bestiary.getJSONArray("monsters");
		monsters.forEach(monster -> {

			JSONObject monsterData = (JSONObject) monster;
			System.out.println(monsterData.getString("name"));
			
		});
		
	}
	
	public ArrayList<String> getAllMonsterNames() {
		
		ArrayList<String> allNames = new ArrayList<>();
		
		Iterator<?> keys = names.keys();
		while(keys.hasNext()) {
			
			String key = (String) keys.next();

			if(key.length() == 3) {
				
				String name = names.getString(key);
				name = name.replace(" ", "_");
				name = name.toLowerCase();
				
				allNames.add(name);
				
			}
			
		}
		
		return allNames;
		
	}
	
	public static BufferedImage getMonsterImage(String monsterName) {
		
		BufferedImage image = null;
		String imagePath = RESOURCE_DIRECTORY + monsterName + ".png";
		imagePath = imagePath.replace(" ", "_");
		imagePath = imagePath.toLowerCase();
		FileManager fileManager = (FileManager) ComponentManager.getComponent(ComponentType.FILEHANDLER);
		
		try {
			
			URL url = fileManager.getResource(imagePath, ResourceType.IMAGE);
			if(url != null) {
				
				image = ImageIO.read(url);
				
			} else {
				
				return new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
			}
			
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		
		return image;
		
	}
	
/*
	private Monster getMonster(String name, Element element) {
		
		Optional<Monster> optional = 
		monsters.parallelStream()
				.filter(monster -> 
					monster.getName().equals(name) && 
				    monster.getElement() == element
				)
				.findFirst();
		
		return optional.get();
		
	}
	
	public List<Monster> getMonsters(String name) {
		
		return monsters.parallelStream()
					   .filter(monster -> monster.getName().equals(name))
					   .collect(Collectors.toList());
		
	}
	
	public List<Monster> getMonsters(Element element) {
		
		return monsters.parallelStream()
				       .filter(monster -> monster.getElement() == element)
				       .collect(Collectors.toList());	
		
	}
	
	public Monster getMonster(String name, Element element, boolean awakened) {

		if(awakened) {
			
			Optional<Monster> optional = 
			monsters.parallelStream()
					.filter(monster -> 
						monster.getAwakenedName().equals(name) && 
						monster.getElement() == element
					)
					.findFirst();
			
			return optional.get();
			
		}
			
		return getMonster(name, element);
		
	}
	*/

	@Override
	public void shutdown() {}
}