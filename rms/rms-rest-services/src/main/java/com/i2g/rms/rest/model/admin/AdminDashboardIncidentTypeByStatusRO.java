package com.i2g.rms.rest.model.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i2g.rms.rest.model.AbstractEntityRO;

/**
 * REST Object for Admin dashboard incident type by status RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminDashboardIncidentTypeByStatusRO extends AbstractEntityRO {

	private String _id;
	private String _incidentStatus;
	private long _incidentCount;
	
	@JsonProperty("incidentType")
	public String getId() {
		return _id;
	}
	
	@JsonProperty("incidentType")
	public void setId(final String id) {
		_id = id;
	}

	public long getIncidentCount() {
		return _incidentCount;
	}

	public void setIncidentCount(final long incidentCount) {
		_incidentCount = incidentCount;
	}

	public String getIncidentStatus() {
		return _incidentStatus;
	}

	public void setIncidentStatus(final String incidentStatus) {
		_incidentStatus = incidentStatus;
	}	
}
