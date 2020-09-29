package controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Competence;

public class ObjectToJson {
	
	public static void save(List<Competence> data) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			  String json = mapper.writeValueAsString(data);
		
			  System.out.println("ResultingJSONstring = " + json);

		} catch (JsonProcessingException e) {
			   e.printStackTrace();
			}
	}
	
}
