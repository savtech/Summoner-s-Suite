package com.savagesoftworks.summonerssuite.parser;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.savagesoftworks.summonerssuite.parser.datalisteners.DataListener;

public class Parser {

	private static final List<DataListener> listeners = new ArrayList<>();;
	
	private Parser() {};
    
    public static void addListener(DataListener listener) {
    	
    	listeners.add(listener);
    	
    }
    
    public static void parseData(byte[] data) {

    	JSONObject jsonData = new JSONObject(new String(data, StandardCharsets.UTF_8));
    	
    	listeners.stream()
    			 .forEach(listener -> listener.update(jsonData));
    	
    }
	
}