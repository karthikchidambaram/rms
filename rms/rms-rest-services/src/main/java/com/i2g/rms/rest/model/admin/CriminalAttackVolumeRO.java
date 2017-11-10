package com.i2g.rms.rest.model.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i2g.rms.rest.model.AbstractEntityRO;

/**
 * REST Object for Admin dashboard criminal attack incident volume RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CriminalAttackVolumeRO extends AbstractEntityRO {

	private String _id;
	private long _incidentCount;
	
	@JsonProperty("monthYear")
	public String getId() {
		return _id;
	}
	
	@JsonProperty("monthYear")
	public void setId(final String id) {
		_id = id;
	}

	public long getIncidentCount() {
		return _incidentCount;
	}

	public void setIncidentCount(final long incidentCount) {
		_incidentCount = incidentCount;
	}	
}
