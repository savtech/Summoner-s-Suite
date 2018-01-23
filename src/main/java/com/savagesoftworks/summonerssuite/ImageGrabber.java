package com.savagesoftworks.summonerssuite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.savagesoftworks.summonerssuite.components.Bestiary;
import com.savagesoftworks.summonerssuite.components.ComponentManager;
import com.savagesoftworks.summonerssuite.components.ComponentManager.ComponentType;
import com.savagesoftworks.summonerssuite.monsters.Monster.Element;

public class ImageGrabber {

	public static final String IMAGE_URL = "https://swarfarm.com/static/herders/images/monsters/";
	private static final Integer MONSTER_IMAGE_WIDTH = 100;
	private static final Integer MONSTER_IMAGE_HEIGHT = 100;
	
	private static BufferedImage getImage(String monsterName) {

		BufferedImage imageMap = new BufferedImage(10 * MONSTER_IMAGE_WIDTH, MONSTER_IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D graphics = imageMap.createGraphics();
	    graphics.setPaint(Color.WHITE);
	    graphics.fillRect(0, 0, imageMap.getWidth(), imageMap.getHeight());
		
		for(Element element : Element.values()) {
			
			if(element == Element.NONE) {
				
				continue;
				
			}
			
			String elementAugment = "_" + element.toString();
			String unawakenedPath = (IMAGE_URL + monsterName + elementAugment + ".png").toLowerCase();
			String awakenedPath = (IMAGE_URL + monsterName + elementAugment + "_awakened.png").toLowerCase();
			
			try {
				
				URL unawakenedUrl = new URL(unawakenedPath);
				BufferedImage unawakened = ImageIO.read(unawakenedUrl);
				
				switch(element) {
				
				case WATER:
					graphics.drawImage(unawakened, null, 0 + (0 * MONSTER_IMAGE_WIDTH), 0);
					break;
				case FIRE:
					graphics.drawImage(unawakened, null, 0 + (1 * MONSTER_IMAGE_WIDTH), 0);				
					break;
				case WIND:
					graphics.drawImage(unawakened, null, 0 + (2 * MONSTER_IMAGE_WIDTH), 0);				
					break;
				case LIGHT:
					graphics.drawImage(unawakened, null, 0 + (3 * MONSTER_IMAGE_WIDTH), 0);
					break;
				case DARK:
					graphics.drawImage(unawakened, null, 0 + (4 * MONSTER_IMAGE_WIDTH), 0);
					break;
					
				}
				
			} catch(Exception e) {}
			
			try {
			
				URL awakenedUrl = new URL(awakenedPath);
				BufferedImage awakened = ImageIO.read(awakenedUrl);
				
				switch(element) {
				
				case WATER:
					graphics.drawImage(awakened, null, 0 + (5 * MONSTER_IMAGE_WIDTH), 0);
					break;
				case FIRE:
					graphics.drawImage(awakened, null, 0 + (6 * MONSTER_IMAGE_WIDTH), 0);					
					break;
				case WIND:
					graphics.drawImage(awakened, null, 0 + (7 * MONSTER_IMAGE_WIDTH), 0);					
					break;
				case LIGHT:
					graphics.drawImage(awakened, null, 0 + (8 * MONSTER_IMAGE_WIDTH), 0);					
					break;
				case DARK:
					graphics.drawImage(awakened, null, 0 + (9 * MONSTER_IMAGE_WIDTH), 0);					
					break;
					
				}
			
			} catch(Exception e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		graphics.dispose();
		
		return imageMap;
		
	}
	
	public static void buildImageMaps() {
		
		Bestiary b = (Bestiary) ComponentManager.getComponent(ComponentType.BESTIARY);
		ArrayList<String> names = b.getAllMonsterNames();

		names.forEach(ImageGrabber::buildImageMap);
		
	}

	private static void buildImageMap(String monsterName) {
		
		if(monsterName.equals("magical_archer_promo")) {
			
			return;
			
		}

		File imageFile = new File("src/main/resources/images/bestiary/" + monsterName + ".png");
		if(imageFile.exists()) {
			
			return;
			
		}
		
		BufferedImage imageMap = ImageGrabber.getImage(monsterName);

	    try {
	    	
			ImageIO.write(imageMap, "png", imageFile);
			
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}
	
}