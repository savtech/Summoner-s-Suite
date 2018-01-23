package com.savagesoftworks.summonerssuite.account;

import org.json.JSONObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {

	private final StringProperty name;
	
	private final MonsterPool monsters;
	
	public Player() {

		name = new SimpleStringProperty("Unknown");
		
		monsters = new MonsterPool();
		
	}
	
	public String getName() {
		
		return name.get();
		
	}
	
	public MonsterPool getMonsters() {
		
		return monsters;
		
	}
	
	public StringProperty getNameProperty() {
		
		return name;
		
	}
	
	public void setName(String name) {

		this.name.set(name);
		
	}

	public void updateData(JSONObject data) {
		
		name.set(data.getString("wizard_name"));
		
	}
	
}