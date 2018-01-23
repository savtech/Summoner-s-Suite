package com.savagesoftworks.summonerssuite.parser.datalisteners;

import org.json.JSONObject;

public class BestiaryListener implements DataListener {

	@Override
	public void update(JSONObject data) {

		if(data.has("command") && data.get("command").equals("GetUnitCollection")) {

			System.out.println(data.toString());
		
		}

	}

}
