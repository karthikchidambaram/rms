package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.InvestigationRO;
import com.i2g.rms.rest.model.incident.BaseIncidentDetailRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InvestigationWrapper extends BaseIncidentDetailRO {

	private Long investigationId;
	private String investigatorLoginId;
	private Set<InvestigationRO> investigations = new HashSet<InvestigationRO>(0);

	public Long getInvestigationId() {
		return investigationId;
	}

	public void setInvestigationId(final Long investigationId) {
		if (investigationId != null) {
			this.investigationId = investigationId;
		} else {
			this.investigationId = 0l;
		}
	}

	public String getInvestigatorLoginId() {
		return investigatorLoginId;
	}

	public void setInvestigatorLoginId(final String investigatorLoginId) {
		this.investigatorLoginId = investigatorLoginId;
	}

	public Set<InvestigationRO> getInvestigations() {
		return investigations;
	}

	public void setInvestigations(final Set<InvestigationRO> investigations) {
		this.investigations = investigations;
	}
}
