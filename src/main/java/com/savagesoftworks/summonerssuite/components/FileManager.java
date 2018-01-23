package com.savagesoftworks.summonerssuite.components;

import java.net.URL;

public class FileManager extends Component {

	public static enum ResourceType { DATA, IMAGE, FXML, STYLESHEET }
	
	//Directory information
	public static final String ASSET_PATH = "/";
	public static final String DATA_PATH = "data/";
	public static final String IMAGES_PATH = "images/";
	public static final String FXML_PATH = "fxml/";
	public static final String STYLESHEETS_PATH = "stylesheets/";	
	
	public FileManager() {}
	
	public URL getResource(String fileName, ResourceType type) {
		
		StringBuilder stringBuilder = new StringBuilder(ASSET_PATH);
		
		switch(type) {
		
		default:
		case DATA:
			stringBuilder.append(DATA_PATH);
			break;
		case IMAGE:
			stringBuilder.append(IMAGES_PATH);
			break;
		case FXML:
			stringBuilder.append(FXML_PATH);
			break;
		case STYLESHEET:
			stringBuilder.append(STYLESHEETS_PATH);
			break;
		
		}
		
		stringBuilder.append(fileName);
		
		return getClass().getResource(stringBuilder.toString());
		
	}

	@Override
	public void shutdown() {}
	
}