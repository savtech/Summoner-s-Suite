package com.savagesoftworks.summonerssuite.ui.controllers.modal;

import com.savagesoftworks.summonerssuite.components.ComponentManager;
import com.savagesoftworks.summonerssuite.components.SceneManager;
import com.savagesoftworks.summonerssuite.components.ComponentManager.ComponentType;
import com.savagesoftworks.summonerssuite.components.SceneManager.SceneType;
import com.savagesoftworks.summonerssuite.ui.controllers.Controller;
import com.savagesoftworks.summonerssuite.utility.Utilities;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public abstract class ModalController extends Controller {

	@FXML Label exitLabel;
	
	private final Modal modal;
	protected SceneType sceneType;
	
	protected ModalController() {
		
		SceneManager sceneManager = (SceneManager) ComponentManager.getComponent(ComponentType.SCENEMANAGER);
		modal = new Modal(sceneManager.getRootStage());
		
	}
	
	@FXML
	protected void initialize() {
		
		Utilities.setAsExitPoint(modal.getStage(), exitLabel);
		
	}
	
	public void displayModal() {
		
		SceneManager sceneManager = (SceneManager) ComponentManager.getComponent(ComponentType.SCENEMANAGER);
		modal.buildModal(sceneManager.getScene(sceneType));
		modal.display();
		
	}
	
}