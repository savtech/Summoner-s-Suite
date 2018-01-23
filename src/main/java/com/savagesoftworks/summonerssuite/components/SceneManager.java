package com.savagesoftworks.summonerssuite.components;

import java.util.EnumMap;
import java.util.Map;

import com.savagesoftworks.summonerssuite.components.ComponentManager.ComponentType;
import com.savagesoftworks.summonerssuite.components.FileManager.ResourceType;
import com.savagesoftworks.summonerssuite.components.SceneManager.SceneType;
import com.savagesoftworks.summonerssuite.ui.controllers.Controller;
import com.savagesoftworks.summonerssuite.ui.controllers.modal.ModalController;
import com.savagesoftworks.summonerssuite.utility.Utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneManager extends Component {

	public static enum SceneType { TEST, SPLASH, HELP, MONSTERS };
	
	private final Map<SceneType, Controller> controllers;
	private final Map<SceneType, Scene> scenes;
	
	private Stage rootStage;
	
	public SceneManager() {
		
		controllers = new EnumMap<>(SceneType.class);
		scenes = new EnumMap<>(SceneType.class);
		
	}
	
	public void setRootStage(Stage stage) {
		
		rootStage = stage;
		initializeStage(rootStage);
		
	}
	
	public Controller getController(SceneType sceneType) {
		
		if(!controllers.containsKey(sceneType)) {
			
			loadScene(sceneType);
			
		}
		
		return controllers.get(sceneType);
		
	}	

	public void loadScene(SceneType sceneType) {
		
		try {
		
			FXMLLoader loader = null;
			FileManager fileHandler = (FileManager) ComponentManager.getComponent(ComponentType.FILEHANDLER);
			
			switch(sceneType) {
	
			case SPLASH:
				loader = new FXMLLoader(fileHandler.getResource("Splash.fxml", ResourceType.FXML));
				break;
			case HELP:
				loader = new FXMLLoader(fileHandler.getResource("Help.fxml", ResourceType.FXML));
				break;
			case TEST:
				loader = new FXMLLoader(fileHandler.getResource("Test.fxml", ResourceType.FXML));
				break;
			case MONSTERS:
				loader = new FXMLLoader(fileHandler.getResource("Monsters.fxml", ResourceType.FXML));
				break;
				
			}
			
			Parent root = loader.load();

			Controller controller = (Controller) loader.getController();
			controllers.put(sceneType, controller);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(controller.getStylesheet());
			
			if(!(controller instanceof ModalController)) {
				
				Utilities.makeDraggable(rootStage, root);
				
			}
			
			scenes.put(sceneType, scene);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setScene(SceneType sceneType) {

		Scene scene = getScene(sceneType);
		rootStage.setScene(scene);
		
	}
	
	public static void initializeStage(Stage stage) {

		stage.setTitle(Utilities.PROGRAM_NAME + " " + Utilities.PROGRAM_VERSION);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setResizable(false);
		stage.centerOnScreen();

		FileManager fileHandler = (FileManager) ComponentManager.getComponent(ComponentType.FILEHANDLER);
		Image icon = new Image(fileHandler.getResource("icon.png", ResourceType.IMAGE).toExternalForm());
		stage.getIcons().add(icon);
		
	}

	public Stage getRootStage() {

		return rootStage;
		
	}

	@Override
	public void shutdown() {
		
		controllers.values()
				   .forEach(controller -> {
					   
					   controller.shutdown();
					   
				   });
		
	}

	public Scene getScene(SceneType sceneType) {

		if(!scenes.containsKey(sceneType)) {
			
			loadScene(sceneType);
			
		}
		
		return scenes.get(sceneType);
		
	}
	
	
}