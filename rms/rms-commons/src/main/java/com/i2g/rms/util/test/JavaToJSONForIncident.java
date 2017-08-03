package com.i2g.rms.util.test;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2g.rms.util.ApplicationUtils;
import com.i2g.rms.util.test.model.IncidentRO;
import com.i2g.rms.util.test.model.IncidentStatusRO;
import com.i2g.rms.util.test.model.StatusFlagRO;
import com.i2g.rms.util.test.model.UserRO;
import com.i2g.rms.util.test.model.YesNoTypeRO;
import com.i2g.rms.util.test.model.tablemaintenance.EntryPointRO;
import com.i2g.rms.util.test.model.tablemaintenance.IncidentCategoryRO;
import com.i2g.rms.util.test.model.tablemaintenance.IncidentLocationDetailRO;
import com.i2g.rms.util.test.model.tablemaintenance.IncidentLocationRO;
import com.i2g.rms.util.test.model.tablemaintenance.IncidentTypeRO;

public class JavaToJSONForIncident {

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
		
		IncidentRO obj = new IncidentRO();
		obj.setUniqueIncidentId(ApplicationUtils.getUniqueIncidentId());
		obj.setIncidentTime(ApplicationUtils.getCurrentTimeAsLong());
		obj.setIncidentType(getIncidentTypeRO());
		obj.setPlaceOfIncident("Inside office premises");
		obj.setLandmark("Near parking lot");
		obj.setEntryPoint(getEntryPointRO());
		obj.setIncidentStatus(IncidentStatusRO.DRAFT);
		obj.setIncidentLocationDetails(getIncidentLocationDetailRO());
		obj.setIncidentDescription("Incident reported in office.");
		obj.setStatusFlag(StatusFlagRO.ACTIVE);
		obj.setPersonInjured(YesNoTypeRO.Y);
		obj.setPropertyDamage(YesNoTypeRO.Y);
		obj.setCrimeInvolved(YesNoTypeRO.Y);
		obj.setIncidentCategory(getIncidentCategoryRO());
		return obj;
	}
	
	private IncidentTypeRO getIncidentTypeRO() {
		IncidentTypeRO obj = new IncidentTypeRO();
		obj.setId("ACCIDENT");
		obj.setDescription("Accident");
		return obj;
	}
	
	private EntryPointRO getEntryPointRO() {
		EntryPointRO obj = new EntryPointRO();
		obj.setId("ROOF");
		obj.setDescription("Roof");
		return obj;
	}
	
	private IncidentLocationRO getIncidentLocationRO() {
		IncidentLocationRO obj = new IncidentLocationRO();
		obj.setId("WAREHSE");
		obj.setDescription("Warehouse");
		return obj;
	}
	
	private IncidentLocationDetailRO getIncidentLocationDetailRO() {
		IncidentLocationDetailRO obj = new IncidentLocationDetailRO();
		obj.setId("CHILLER");
		obj.setDescription("Chiller");
		obj.setIncidentLocation(getIncidentLocationRO());
		return obj;
	}
	
	private IncidentCategoryRO getIncidentCategoryRO() {
		IncidentCategoryRO obj = new IncidentCategoryRO();
		obj.setId("PROP_DMGE");
		obj.setDescription("Property Damage");
		return obj;
	}
	
	private UserRO getUserRO() {
		UserRO obj = new UserRO();
		obj.setId(1);
		return obj;
	}

	public static void main(String args[]) {
		JavaToJSONForIncident javaToJSON = new JavaToJSONForIncident();
		javaToJSON.run();
	}	
}
