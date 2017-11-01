package com.i2g.rms.rest.model.incident;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Search Incident Details RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseIncidentDetailRO {

	private Long incidentId;
	private String uniqueIncidentId;

	public Long getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(final Long incidentId) {
		if (incidentId != null) {
			this.incidentId = incidentId;
		} else {
			this.incidentId = 0l;
		}
	}

	public String getUniqueIncidentId() {
		return uniqueIncidentId;
	}

	public void setUniqueIncidentId(final String uniqueIncidentId) {
		this.uniqueIncidentId = uniqueIncidentId;
	}
}
