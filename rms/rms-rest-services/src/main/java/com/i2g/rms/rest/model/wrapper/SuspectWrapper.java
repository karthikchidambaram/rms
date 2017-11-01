package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.incident.BaseIncidentDetailRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuspectWrapper extends BaseIncidentDetailRO {
	
	private Long[] employeeIds;
	private String[] employeeLoginIds;
	private Set<SuspectRO> suspects = new HashSet<SuspectRO>(0);
	
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
}
