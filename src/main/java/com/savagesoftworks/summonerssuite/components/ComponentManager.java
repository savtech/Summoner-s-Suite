package com.savagesoftworks.summonerssuite.components;

import java.util.EnumMap;
import java.util.Map;

import com.savagesoftworks.summonerssuite.proxy.SSProxy;

public final class ComponentManager {
	
	public static enum ComponentType { ACCOUNT, BESTIARY, FILEHANDLER, PROXY, SCENEMANAGER };
	
	private static final Map<ComponentType, Component> components = new EnumMap<>(ComponentType.class);

	private ComponentManager() {};
	
	public static Map<ComponentType, Component> getComponents() {
		
		return components;
		
	}
	
	public static void shutdown() {
		
		components.values().forEach(component -> {
						
			component.shutdown();
						
		});
		
	}
	
	public static Component getComponent(ComponentType componentType) {
		
		if(!components.containsKey(componentType)) {
			
			addComponent(componentType);
			
		}
		
		return components.get(componentType);
		
	}
	
	private static void addComponent(ComponentType componentType) {
		
		Component component = null;
		
		switch(componentType) {
	
		case ACCOUNT:
			component = new AccountManager();
			break;
		case BESTIARY:
			component = new Bestiary();
			break;
		case FILEHANDLER:
			component = new FileManager();
			break;		
		case PROXY:
			component = new SSProxy();
			break;
		case SCENEMANAGER:
			component = new SceneManager();
			break;
		
		}
		
		components.put(componentType, component);
		
	}
	
}