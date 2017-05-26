package com.i2g.rms.util.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2g.rms.util.model.TableMaintenanceRO;

public class JavaToJSONUtility {

	private void run() {

		ObjectMapper mapper = new ObjectMapper();
		Object object = createObject();

		try {
			// Convert object to JSON string and save into a file directly
			mapper.writeValue(new File("C:\\Karthik\\Temp\\java\\object.json"), object);

			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(object);
			System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Object createObject() {
		
		TableMaintenanceRO tableMaintenanceRO = new TableMaintenanceRO();
		
		tableMaintenanceRO.setCode("Code");
		tableMaintenanceRO.setDescription("Description");
		tableMaintenanceRO.setOperation("Operation");
		tableMaintenanceRO.setTableName("Table Name");
		
		List<String> codes = new ArrayList<>();
		String s1 = "Code 1";
		String s2 = "Code 2";
		String s3 = "Code 3";
		codes.add(s1);
		codes.add(s2);
		codes.add(s3);
		
		tableMaintenanceRO.setCodes(codes);
		
		return tableMaintenanceRO;
	}

	public static void main(String args[]) {
		JavaToJSONUtility javaToJSON = new JavaToJSONUtility();
		javaToJSON.run();
	}
}
