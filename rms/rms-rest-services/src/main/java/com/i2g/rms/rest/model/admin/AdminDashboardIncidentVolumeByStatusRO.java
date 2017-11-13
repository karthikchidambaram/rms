package com.i2g.rms.rest.model.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;

/**
 * REST Object for Admin dashboard incident volume by status RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminDashboardIncidentVolumeByStatusRO extends AbstractEntityRO {

	private long _id;
	private String _monthYear;
	private String _incidentStatus;
	private long _incidentCount;
	
	public long getId() {
		return _id;
	}
	
	public void setId(final long id) {
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

	public String getMonthYear() {
		return _monthYear;
	}

	public void setMonthYear(final String monthYear) {
		_monthYear = monthYear;
	}	
}
