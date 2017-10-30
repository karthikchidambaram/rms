package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.SuspectRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuspectWrapper {
	
	private Long incidentId;
	private String uniqueIncidentId;
	private Long[] employeeIds;
	private String[] employeeLoginIds;
	private Set<SuspectRO> suspects = new HashSet<SuspectRO>(0);
	
	public String getUniqueIncidentId() {
		return uniqueIncidentId;
	}

	public void setUniqueIncidentId(String uniqueIncidentId) {
		this.uniqueIncidentId = uniqueIncidentId;
	}

	public Set<SuspectRO> getSuspects() {
		return suspects;
	}

	public void setSuspects(final Set<SuspectRO> suspects) {
		this.suspects = suspects;
	}

	public Long[] getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(final Long[] employeeIds) {
		this.employeeIds = employeeIds;
	}

	public String[] getEmployeeLoginIds() {
		return employeeLoginIds;
	}

	public void setEmployeeLoginIds(final String[] employeeLoginIds) {
		this.employeeLoginIds = employeeLoginIds;
	}

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
}
