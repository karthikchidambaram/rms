package com.i2g.rms.rest.mapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;

/* Custom Object Mapper to handle default JSON serialization/deserialization of all objects
*
* @since 1.0.0
* @author Karthikeyan Chidambaram
* @author RMS Development Team
*/
@Service("customObjectMapper")
public class CustomObjectMapper extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomObjectMapper() {
		SimpleModule module = new SimpleModule("JSONModule", new Version(1, 0, 0, null, null, null));
		module.addSerializer(LocalDateTime.class, new JsonDateSerializer());
		module.addDeserializer(LocalDateTime.class, new JsonDateDeserializer());
		module.addSerializer(LocalDate.class, new JsonLocalDateSerializer());
		module.addDeserializer(LocalDate.class, new JsonLocalDateDeserializer());
		registerModule(module);
		// registering Hibernate5Moudle to support lazy object mappings, still
		// allowing fields marked transient (such as IDs) to be serialized.
		Hibernate5Module hibernateModule = new Hibernate5Module();
		hibernateModule.disable(Feature.USE_TRANSIENT_ANNOTATION);
		registerModule(hibernateModule);
	}

}
