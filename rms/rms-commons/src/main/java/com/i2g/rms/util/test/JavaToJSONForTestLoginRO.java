package com.i2g.rms.util.test;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2g.rms.util.test.model.TestLoginRO;

public class JavaToJSONForTestLoginRO {

	private void run() {

		ObjectMapper mapper = new ObjectMapper();
		TestLoginRO testLoginRO = createTestLoginRO();

		try {
			// Convert object to JSON string and save into a file directly
			mapper.writeValue(new File("C:\\Karthik\\Temp\\java\\LoginRO.json"), testLoginRO);

			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(testLoginRO);
			System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testLoginRO);
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private TestLoginRO createTestLoginRO() {
		TestLoginRO testLoginRO = new TestLoginRO();
		return testLoginRO;
	}

	public static void main(String args[]) {
		JavaToJSONForTestLoginRO javaToJSONForTestLoginRO = new JavaToJSONForTestLoginRO();
		javaToJSONForTestLoginRO.run();
	}
}
