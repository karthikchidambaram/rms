package com.i2g.rms.rest.model.incident;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.InvestigationRO;

/**
 * Search Claim Details RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvestigationDetailRO extends AbstractEntityRO {

	private long incidentId;
	private String uniqueIncidentId;
	private InvestigationRO investigation;

	public long getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(final long incidentId) {
		this.incidentId = incidentId;
	}

	public String getUniqueIncidentId() {
		return uniqueIncidentId;
	}

	public void setUniqueIncidentId(final String uniqueIncidentId) {
		this.uniqueIncidentId = uniqueIncidentId;
	}

	public InvestigationRO getInvestigation() {
		return investigation;
	}

	public void setInvestigation(final InvestigationRO investigation) {
		this.investigation = investigation;
	}
}
