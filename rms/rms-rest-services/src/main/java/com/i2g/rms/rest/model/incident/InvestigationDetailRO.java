package com.i2g.rms.rest.model.incident;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.InvestigationRO;

/**
 * Search Claim Details RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvestigationDetailRO extends BaseIncidentDetailRO {

	private InvestigationRO investigation;

	public InvestigationRO getInvestigation() {
		return investigation;
	}

	public void setInvestigation(final InvestigationRO investigation) {
		this.investigation = investigation;
	}
}
