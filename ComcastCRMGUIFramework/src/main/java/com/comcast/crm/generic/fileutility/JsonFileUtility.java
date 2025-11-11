package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileUtility {

	public String getDataFromJsonFile(String key) throws IOException, ParseException {

		FileReader fir = new FileReader("./configAppData/appCommonData.json");
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fir);
		JSONObject map=(JSONObject) obj;
		String data = map.get(key).toString();
		
		return data;
		
	}

}
