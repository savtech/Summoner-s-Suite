package com.savagesoftworks.summonerssuite.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

import com.savagesoftworks.summonerssuite.components.ComponentManager;
import com.savagesoftworks.summonerssuite.components.FileManager;
import com.savagesoftworks.summonerssuite.components.ComponentManager.ComponentType;
import com.savagesoftworks.summonerssuite.components.FileManager.ResourceType;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Utilities {
	
	//Program information
	public static final String PROGRAM_NAME = "Summoners Suite";
	public static final String PROGRAM_VERSION = "v0.0.1";
	
	private Utilities() {};
	
	public static void setAsExitPoint(Stage stage, final Node node) {
		
		styleAsClickable(node);
		node.setOnMouseClicked(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent event) {

				stage.close();
				
			}
			
		});
		
	}
	
	public static void styleAsClickable(final Node node) {	
		
		node.setOnMouseEntered(new EventHandler<MouseEvent>() {
	    	
	        @Override 
	        public void handle(MouseEvent mouseEvent) {
	        	
	        	if (!mouseEvent.isPrimaryButtonDown()) {
	        		
	        		node.setCursor(Cursor.HAND);
	        		
	        	}
	        }
	    });
	    
	    node.setOnMouseExited(new EventHandler<MouseEvent>() {
	    	
	        @Override 
	        public void handle(MouseEvent mouseEvent) {
	        	
	        	if (!mouseEvent.isPrimaryButtonDown()) {
	        		
	        		node.setCursor(Cursor.DEFAULT);
	        		
	        	}
	        	
	        }
	        
	    });
			
	}
	
	public static void makeDraggable(final Stage stage, final Node node) {
		
	    final Vector dragDelta = new Vector();
	    
	    node.setOnMousePressed(new EventHandler<MouseEvent>() {
	    	
	    	@Override 
	    	public void handle(MouseEvent mouseEvent) {
	        
	    		dragDelta.x = stage.getX() - mouseEvent.getScreenX();
	            dragDelta.y = stage.getY() - mouseEvent.getScreenY();
	            node.setCursor(Cursor.CLOSED_HAND);
	        
	        }
	      
	    });
	    
	    node.setOnMouseReleased(new EventHandler<MouseEvent>() {
	    	
	    	@Override
	    	public void handle(MouseEvent mouseEvent) {
	    	  
	    	    node.setCursor(Cursor.DEFAULT);
	    	  
	        }
	      
	    });
	    
	    node.setOnMouseDragged(new EventHandler<MouseEvent>() {
	    	
	    	@Override
	    	public void handle(MouseEvent mouseEvent) {
	    	  
	    	    stage.setX(mouseEvent.getScreenX() + dragDelta.x);
	            stage.setY(mouseEvent.getScreenY() + dragDelta.y);
	        
	        }
	      
	    });
	    
	    node.setOnMouseExited(new EventHandler<MouseEvent>() {
	    	
	        @Override 
	        public void handle(MouseEvent mouseEvent) {
	        	
	        	if (!mouseEvent.isPrimaryButtonDown()) {
	        		
	        		node.setCursor(Cursor.DEFAULT);
	        		
	        	}
	        	
	        }
	        
	    });
	    
	}
	
	public static class Vector {
		
		public double x, y;
		
	}
	
	public static JSONObject loadJSON(String fileName) {

		JSONObject json = null;
		FileManager fileHandler = (FileManager) ComponentManager.getComponent(ComponentType.FILEHANDLER);
		
		try (
			final InputStream inputStream = fileHandler.getResource(fileName + ".json", ResourceType.DATA).openStream();
			final Reader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		){

			final StringBuilder stringBuilder = new StringBuilder();
			String nextLine;
			
			while((nextLine = bufferedReader.readLine()) != null) {
				
				stringBuilder.append(nextLine);
				
			}
			
			String rawData = stringBuilder.toString();
			json = new JSONObject(rawData);
			
			inputStreamReader.close();
			bufferedReader.close();
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
			
		}
		
		return json;
		
	}
	
	public static void writeToFile(String fileName, String output) {
		
		File out = new File("src/main/resources/data/monsterData/" + fileName + ".txt");
		
		try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)))){
	    	
	    	writer.write(output);
	    	
	    }catch(Exception e) {
	    	
	    	e.printStackTrace();
	    	
	    }
		
	}
	
}