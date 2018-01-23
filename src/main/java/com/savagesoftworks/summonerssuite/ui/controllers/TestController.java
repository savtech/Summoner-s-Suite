package com.savagesoftworks.summonerssuite.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class TestController extends Controller {

	@FXML private ImageView waterImageView;
	@FXML private ImageView fireImageView;
	@FXML private ImageView windImageView;
	@FXML private ImageView lightImageView;
	@FXML private ImageView darkImageView;
	@FXML private ImageView waterAImageView;
	@FXML private ImageView fireAImageView;
	@FXML private ImageView windAImageView;
	@FXML private ImageView lightAImageView;
	@FXML private ImageView darkAImageView;
	
	public TestController() {
		
		name = "Test";
		
	}
	
	
	@FXML
	private void initialize() {
	/*
		ArrayList<BufferedImage> images = ImageGrabber.getImage("rakshasa");

		Image waterImage = SwingFXUtils.toFXImage(images.get(0), null);
		waterImageView.setImage(waterImage);
		Image fireImage = SwingFXUtils.toFXImage(images.get(1), null);
		fireImageView.setImage(fireImage);
		Image windImage = SwingFXUtils.toFXImage(images.get(2), null);
		windImageView.setImage(windImage);
		Image lightImage = SwingFXUtils.toFXImage(images.get(3), null);
		lightImageView.setImage(lightImage);
		Image darkImage = SwingFXUtils.toFXImage(images.get(4), null);
		darkImageView.setImage(darkImage);
		Image waterAImage = SwingFXUtils.toFXImage(images.get(5), null);
		waterAImageView.setImage(waterAImage);
		Image fireAImage = SwingFXUtils.toFXImage(images.get(6), null);
		fireAImageView.setImage(fireAImage);
		Image windAImage = SwingFXUtils.toFXImage(images.get(7), null);
		windAImageView.setImage(windAImage);
		Image lightAImage = SwingFXUtils.toFXImage(images.get(8), null);
		lightAImageView.setImage(lightAImage);
		Image darkAImage = SwingFXUtils.toFXImage(images.get(9), null);
		darkAImageView.setImage(darkAImage);
	*/
	}
	
	@Override
	public void shutdown() {}

}