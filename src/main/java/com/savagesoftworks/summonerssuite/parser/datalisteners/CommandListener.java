package com.savagesoftworks.summonerssuite.parser.datalisteners;

import org.json.JSONObject;

public class CommandListener implements DataListener {
	
	@Override
	public void update(JSONObject data) {
		
		if(data.has("command")) {
			
			//System.out.println(data.get("command"));

		}
		
	}

}