package com.savagesoftworks.summonerssuite.ui.controllers.modal;

import com.savagesoftworks.summonerssuite.components.SceneManager;
import com.savagesoftworks.summonerssuite.utility.Utilities;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Modal {

	private final Stage parentStage;
	Stage modalStage;

	public Modal(Stage parentStage) {

		this.parentStage = parentStage;
		modalStage = new Stage();
		SceneManager.initializeStage(modalStage);
		modalStage.initModality(Modality.APPLICATION_MODAL);
		modalStage.initOwner(parentStage);
		
	}
	
	public  void buildModal(Scene scene) {
		
		Utilities.makeDraggable(modalStage, scene.getRoot());
		modalStage.setScene(scene);
		
	}
	
	public void close() {
		
		modalStage.close();
		
	}
	
	public void display() {
		
		Pane parentRoot = (Pane) parentStage.getScene().getRoot();
		Pane overlayPane = new Pane();
		
		overlayPane.setStyle("-fx-background-color: rgba(21, 21, 21, 0.95);");
		overlayPane.setPrefHeight(parentRoot.getHeight());
		overlayPane.setPrefWidth(parentRoot.getWidth());
		
		parentRoot.getChildren().add(overlayPane);
		
		modalStage.showAndWait();
		
		parentRoot.getChildren().remove(overlayPane);
		
	}
	
	public Stage getStage() {
		
		return modalStage;
		
	}
	
}