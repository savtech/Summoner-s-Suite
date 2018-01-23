package com.savagesoftworks.summonerssuite.ui.controllers;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import com.savagesoftworks.summonerssuite.components.AccountManager;
import com.savagesoftworks.summonerssuite.components.Bestiary;
import com.savagesoftworks.summonerssuite.components.ComponentManager;
import com.savagesoftworks.summonerssuite.components.ComponentManager.ComponentType;
import com.savagesoftworks.summonerssuite.monsters.Monster;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.TilePane;

public class MonstersController extends Controller {

	@FXML private TilePane monstersPane;
	
	public MonstersController() {
		
		name = "Monsters";
		
	}
	
	@FXML
	private void initialize() {
	
		Bestiary bestiary = (Bestiary) ComponentManager.getComponent(ComponentType.BESTIARY);
		AccountManager accountManager = (AccountManager) ComponentManager.getComponent(ComponentType.ACCOUNT);
		List<Monster> monsters = accountManager.getPlayer().getMonsters().getMonsters();

		System.out.println("Total monsters: " + monsters.size());
		
		int columns = 5;
		int rows = (int) Math.ceil(monsters.size() / columns);
		
		monstersPane.setPrefColumns(columns);
		monstersPane.setPrefRows(rows);
		
		Iterator<Monster> monster = monsters.iterator();

		while(monster.hasNext()) {
			
			Monster m = monster.next();
			String id = m.getID().toString();
			Boolean awakened = id.charAt(3) == '0' ? false : true;
			String name = bestiary.getBaseMonsterNameFromID(id);

			if(
				name.equals("Fairy Queen")
			) { continue; }
			
			BufferedImage rawImage = Bestiary.getMonsterImage(name);
			
			if(rawImage != null) {
				Image image = SwingFXUtils.toFXImage(rawImage, null);
				PixelReader reader = image.getPixelReader();
				int xOffset = ((m.getElement().toInteger()) * 100) - 100;
				if(awakened) xOffset += 500;
				WritableImage newImage = new WritableImage(reader, xOffset, 0, 100, 100);

				ImageView imageView = new ImageView();
				imageView.setPreserveRatio(true);
				imageView.setFitHeight(50);
				imageView.setFitWidth(50);
				imageView.setImage(newImage);
		
				monstersPane.getChildren().add(imageView);
				
			}
			
		}
		
	}

	@Override
	public void shutdown() {}
	
}