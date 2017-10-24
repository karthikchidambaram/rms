package com.i2g.rms.rest.model.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.SuspectRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuspectWrapper {
	
	private String uniqueIncidentId;
	private Long[] employeeIds;
	private String[] employeeLoginIds;
	private List<SuspectRO> suspects = new ArrayList<SuspectRO>(0);
	
	public String getUniqueIncidentId() {
		return uniqueIncidentId;
	}

	public void setUniqueIncidentId(String uniqueIncidentId) {
		this.uniqueIncidentId = uniqueIncidentId;
	}

	public List<SuspectRO> getSuspects() {
		return suspects;
	}

	public void setSuspects(final List<SuspectRO> suspects) {
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
