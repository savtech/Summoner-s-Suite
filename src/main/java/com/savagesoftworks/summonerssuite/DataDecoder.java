package com.savagesoftworks.summonerssuite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.savagesoftworks.summonerssuite.components.Bestiary;
import com.savagesoftworks.summonerssuite.components.ComponentManager;
import com.savagesoftworks.summonerssuite.components.ComponentManager.ComponentType;
import com.savagesoftworks.summonerssuite.components.FileManager.ResourceType;
import com.savagesoftworks.summonerssuite.components.FileManager;
import com.savagesoftworks.summonerssuite.parser.Decryptor;
import com.savagesoftworks.summonerssuite.utility.Utilities;

public class DataDecoder {

	public static void monster() {
		
		JSONObject monsters = null;
		FileManager fileHandler = (FileManager) ComponentManager.getComponent(ComponentType.FILEHANDLER);
		
		try (
			final InputStream inputStream = fileHandler.getResource("login.txt", ResourceType.DATA).openStream();
			final Reader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		){

			final StringBuilder stringBuilder = new StringBuilder();
			String nextLine;
			
			while((nextLine = bufferedReader.readLine()) != null) {
				
				stringBuilder.append(nextLine);
				
			}
			
			String rawData = stringBuilder.toString();
			monsters = new JSONObject(rawData);
			
			inputStreamReader.close();
			bufferedReader.close();
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
			
		}
		
		try {

		JSONObject monsterNames = Utilities.loadJSON("bestiary/names");
		JSONArray monsterData = monsters.getJSONArray("unit_list");
		
		Bestiary b = (Bestiary) ComponentManager.getComponent(ComponentType.BESTIARY);
		
		monsterData.forEach(m -> {
			
			JSONObject o = (JSONObject) m;
			Integer id = o.getInt("unit_master_id");
			String name = b.getNameFromID(id.toString());
			
			System.out.println(o.toString(4));
			
		});
		
		} catch (JSONException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void run() {
		
		FileManager fileHandler = (FileManager) ComponentManager.getComponent(ComponentType.FILEHANDLER);

		try {
		
			Path in = Paths.get(new URI(fileHandler.getResource("data.txt", ResourceType.DATA).toString()));
		    byte[] input = Files.readAllBytes(in);
		    
		    tryDecryptResponse(input);
		    tryDecryptRequest(input);
		    tryBase64Decode(input);

		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
	    
	}

	private static void tryBase64Decode(byte[] input) {

		byte[] output = Base64.getDecoder().decode(input);
		File outFile = new File("src/main/resources/base64.txt");
		
	    try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)))){
	    	
	    	writer.write(new String(output, StandardCharsets.UTF_8));
	    	
	    }catch(Exception e) {
	    	
	    	e.printStackTrace();
	    	
	    }
		
	}

	private static void tryDecryptRequest(byte[] input) {

			byte[] output = Decryptor.decryptRequest(input);
			File outFile = new File("src/main/resources/decryptrequest.txt");
			
		    try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)))){
		    	
		    	writer.write(new String(output, StandardCharsets.UTF_8));
		    	
		    }catch(Exception e) {
		    	
		    	e.printStackTrace();
		    	
		    }
			
		}

	private static void tryDecryptResponse(byte[] input) {

		byte[] output = Decryptor.decryptResponse(input);
		File outFile = new File("src/main/resources/decryptresponse.txt");
		
	    try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)))){
	    	
	    	writer.write(new String(output, StandardCharsets.UTF_8));
	    	
	    }catch(Exception e) {
	    	
	    	e.printStackTrace();
	    	
	    }
		
	}
	
}