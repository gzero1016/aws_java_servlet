package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import com.google.gson.Gson;

public class JsonParseUtil {
	
	public static Map<String, Object> toMap(InputStream inputStream) {
		
		StringBuilder jsonData = new StringBuilder("");
		
		BufferedReader bufferedReader = null;
		
		if(inputStream == null) {
			return null;
		}
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		while(true) {
			try {
				String data = bufferedReader.readLine();	//메소드로 빼는거라 try/catch 사용
				if(data == null) {
					break;
				}
				jsonData.append(data);
				
			} catch (IOException e) {
				return null;
			}
		}
		
		Gson gson = new Gson();
		
		return gson.fromJson(jsonData.toString(), Map.class);
	}
	
	
	public static String toJson(Object object) {
		Gson gson = new Gson();
		
		return gson.toJson(object);	
	}	
}