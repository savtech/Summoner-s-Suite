package com.savagesoftworks.summonerssuite.ui.controllers;

import com.savagesoftworks.summonerssuite.components.ComponentManager;
import com.savagesoftworks.summonerssuite.components.FileManager;
import com.savagesoftworks.summonerssuite.components.SceneManager;
import com.savagesoftworks.summonerssuite.components.ComponentManager.ComponentType;
import com.savagesoftworks.summonerssuite.components.FileManager.ResourceType;
import com.savagesoftworks.summonerssuite.components.SceneManager.SceneType;
import com.savagesoftworks.summonerssuite.proxy.SSProxy;
import com.savagesoftworks.summonerssuite.utility.Utilities;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SplashController extends Controller {
	
	@FXML private Label exitLabel;
	@FXML private Label helpLabel;
	@FXML private Label statusLabel;
	@FXML private Label versionLabel;
	@FXML private Label connectionLabel;
	@FXML private ImageView monsterLogoView;
	@FXML private ImageView textLogoView;
	
	public SplashController() {
		
		name = "Splash";
		
	}
	
	@FXML
	private void initialize() {
		
		
		versionLabel.setText(Utilities.PROGRAM_VERSION);
		
		SSProxy proxy = (SSProxy) ComponentManager.getComponent(ComponentType.PROXY);
		statusLabel.textProperty().bind(proxy.getProxyInformation());

		FileManager fileHandler = (FileManager) ComponentManager.getComponent(ComponentType.FILEHANDLER);
		
		String monsterLogoPath = fileHandler.getResource("monsterlogo.png", ResourceType.IMAGE).toExternalForm();
		monsterLogoView.setImage(new Image(monsterLogoPath));
		
		String textLogoPath = fileHandler.getResource("textlogo.png", ResourceType.IMAGE).toExternalForm();
		textLogoView.setImage(new Image(textLogoPath));
		
		
		Utilities.styleAsClickable(helpLabel);
		helpLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				
				SceneManager sceneManager = (SceneManager) ComponentManager.getComponent(ComponentType.SCENEMANAGER);
				HelpController helpController = (HelpController) sceneManager.getController(SceneType.HELP);
				helpController.displayModal();
				
			}
			
		});
		
		SceneManager sceneManager = (SceneManager) ComponentManager.getComponent(ComponentType.SCENEMANAGER);
		Utilities.setAsExitPoint(sceneManager.getRootStage(), exitLabel);
		
	}
	
	@Override
	public void shutdown() {
		
	}
	
}