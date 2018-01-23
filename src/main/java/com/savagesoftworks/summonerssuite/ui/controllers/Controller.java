package com.savagesoftworks.summonerssuite.ui.controllers;

import com.savagesoftworks.summonerssuite.components.ComponentManager;
import com.savagesoftworks.summonerssuite.components.FileManager;
import com.savagesoftworks.summonerssuite.components.ComponentManager.ComponentType;
import com.savagesoftworks.summonerssuite.components.FileManager.ResourceType;

public abstract class Controller {
	
	protected String name;
	
	public abstract void shutdown();
	
	public String getStylesheet() {
		
		FileManager fileManager = (FileManager) ComponentManager.getComponent(ComponentType.FILEHANDLER);
		return fileManager.getResource(name + ".css", ResourceType.STYLESHEET).toExternalForm();
		
	}
	
}