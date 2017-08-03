package com.i2g.rms.util.test.model.tablemaintenance;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.i2g.rms.util.test.model.AbstractEntityRO;

/**
 * REST Object for returning table maintenance details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InjuryTypeRO extends AbstractEntityRO {

	private String _id;
	private String _description;
	private Set<InjuryTypeDetailRO> _injuryTypeDetails = new HashSet<InjuryTypeDetailRO>(0);

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		_id = id;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}
	
	public Set<InjuryTypeDetailRO> getInjuryTypeDetails() {
		return _injuryTypeDetails;
	}

	public void setInjuryTypeDetails(Set<InjuryTypeDetailRO> injuryTypeDetails) {
		_injuryTypeDetails = injuryTypeDetails;
	}
}
