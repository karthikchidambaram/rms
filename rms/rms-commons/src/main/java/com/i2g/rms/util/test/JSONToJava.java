package com.i2g.rms.util.test;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2g.rms.util.test.search.SearchRO;

/**
 * Class that will convert JSON to a Java Object
 * 
 * @author Karthikeyan Chidambaram
 *
 */
public class JSONToJava {

	private void run() {
		ObjectMapper mapper = new ObjectMapper();

		try {

			// Convert JSON string from file to Object
			SearchRO searchRO1 = mapper.readValue(new File("C:\\Karthik\\Temp\\java\\SearchRO.json"), SearchRO.class);
			System.out.println(searchRO1);

			// Convert JSON string to Object
			String jsonInString = "{\"paging\":{\"currentPage\":1,\"pageSize\":10},\"filters\":[{\"field\":\"filterField 1\",\"operator\":\"EQ\",\"value\":\"value 1\"},{\"field\":\"filterField 2\",\"operator\":\"NEQ\",\"value\":\"value 2\"},{\"field\":\"filterField 3\",\"operator\":\"STARTS_WITH\",\"value\":\"value 3\"}],\"sorts\":[{\"field\":\"sortField 1\",\"order\":\"ASC\"},{\"field\":\"sortField 2\",\"order\":\"DESC\"},{\"field\":\"sortField 3\",\"order\":\"NONE\"}]}";
			SearchRO searchRO2 = mapper.readValue(jsonInString, SearchRO.class);
			System.out.println(searchRO2);

			// Pretty print
			String prettySearchRO = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(searchRO1);
			System.out.println(prettySearchRO);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		JSONToJava jSONToJava = new JSONToJava();
		jSONToJava.run();
	}
}
