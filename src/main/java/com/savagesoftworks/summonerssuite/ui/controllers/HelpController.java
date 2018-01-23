package com.savagesoftworks.summonerssuite.ui.controllers;

import com.savagesoftworks.summonerssuite.components.ComponentManager;
import com.savagesoftworks.summonerssuite.components.FileManager;
import com.savagesoftworks.summonerssuite.components.ComponentManager.ComponentType;
import com.savagesoftworks.summonerssuite.components.FileManager.ResourceType;
import com.savagesoftworks.summonerssuite.components.SceneManager.SceneType;
import com.savagesoftworks.summonerssuite.proxy.SSProxy;
import com.savagesoftworks.summonerssuite.ui.controllers.modal.ModalController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelpController extends ModalController {

	@FXML ImageView androidImageViewOne;
	@FXML ImageView androidImageViewTwo;
	@FXML ImageView androidImageViewThree;
	@FXML ImageView androidImageViewFour;
	@FXML ImageView androidImageViewFive;
	@FXML ImageView androidImageViewSix;
	@FXML ImageView androidImageViewSeven;
	
	@FXML ImageView appleImageViewOne;
	@FXML ImageView appleImageViewTwo;
	@FXML ImageView appleImageViewThree;
	@FXML ImageView appleImageViewFour;
	@FXML ImageView appleImageViewFive;
	
	@FXML Label androidIpLabel;
	@FXML Label androidPortLabel;
	@FXML Label appleIpLabel;
	@FXML Label applePortLabel;
	
	public HelpController() {
		
		name = "Help";
		sceneType = SceneType.HELP;
		
	}
	
	@Override
	@FXML
	public void initialize() {
		
		super.initialize();
		
		FileManager fileHandler = (FileManager) ComponentManager.getComponent(ComponentType.FILEHANDLER);
		
		loadAndroidTutorial(fileHandler);
		loadAppleTutorial(fileHandler);
		
		SSProxy proxy = (SSProxy) ComponentManager.getComponent(ComponentType.PROXY);
		androidIpLabel.setText("Set 'Proxy host name' to: " + proxy.getIP());
		androidPortLabel.setText("Set 'Proxy port' to: " + proxy.getPort());
		appleIpLabel.setText("Set 'Server' to: " + proxy.getIP());
		applePortLabel.setText("Set 'Port' to: " + proxy.getPort());
		
	}
	
	private void loadAndroidTutorial(FileManager fileHandler) {
		
		String androidImageOne = fileHandler.getResource("tutorial/android/stepOne.png", ResourceType.IMAGE).toExternalForm();
		androidImageViewOne.setImage(new Image(androidImageOne));
		
		String androidImageTwo = fileHandler.getResource("tutorial/android/stepTwo.png", ResourceType.IMAGE).toExternalForm();
		androidImageViewTwo.setImage(new Image(androidImageTwo));
		
		String androidImageThree = fileHandler.getResource("tutorial/android/stepThree.png", ResourceType.IMAGE).toExternalForm();
		androidImageViewThree.setImage(new Image(androidImageThree));
		
		String androidImageFour = fileHandler.getResource("tutorial/android/stepFour.png", ResourceType.IMAGE).toExternalForm();
		androidImageViewFour.setImage(new Image(androidImageFour));
		
		String androidImageFive = fileHandler.getResource("tutorial/android/stepFive.png", ResourceType.IMAGE).toExternalForm();
		androidImageViewFive.setImage(new Image(androidImageFive));
		
		String androidImageSix = fileHandler.getResource("tutorial/android/stepSix.png", ResourceType.IMAGE).toExternalForm();
		androidImageViewSix.setImage(new Image(androidImageSix));
		
		String androidImageSeven = fileHandler.getResource("tutorial/swIcon.png", ResourceType.IMAGE).toExternalForm();
		androidImageViewSeven.setImage(new Image(androidImageSeven));
		
	}
	
	private void loadAppleTutorial(FileManager fileHandler) {

		String appleImageOne = fileHandler.getResource("tutorial/apple/stepOne.png", ResourceType.IMAGE).toExternalForm();
		appleImageViewOne.setImage(new Image(appleImageOne));
		
		String appleImageTwo = fileHandler.getResource("tutorial/apple/stepTwo.png", ResourceType.IMAGE).toExternalForm();
		appleImageViewTwo.setImage(new Image(appleImageTwo));
		
		String appleImageThree = fileHandler.getResource("tutorial/apple/stepThree.png", ResourceType.IMAGE).toExternalForm();
		appleImageViewThree.setImage(new Image(appleImageThree));
		
		String appleImageFour = fileHandler.getResource("tutorial/apple/stepFour.png", ResourceType.IMAGE).toExternalForm();
		appleImageViewFour.setImage(new Image(appleImageFour));
		
		String appleImageFive = fileHandler.getResource("tutorial/swIcon.png", ResourceType.IMAGE).toExternalForm();
		appleImageViewFive.setImage(new Image(appleImageFive));
		
	}

	@Override
	public void shutdown() {}

}