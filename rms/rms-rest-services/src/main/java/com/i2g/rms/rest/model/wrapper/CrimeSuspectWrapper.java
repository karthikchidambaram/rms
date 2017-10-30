package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.CrimeSuspectRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CrimeSuspectWrapper {
	
	private Long crimeId;
	private Long[] employeeIds;
	private String[] employeeLoginIds;
	private Set<CrimeSuspectRO> crimeSuspects = new HashSet<CrimeSuspectRO>(0);
	
	public Set<CrimeSuspectRO> getCrimeSuspects() {
		return crimeSuspects;
	}

	public void setCrimeSuspects(final Set<CrimeSuspectRO> crimeSuspects) {
		this.crimeSuspects = crimeSuspects;
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

	public Long getCrimeId() {
		return crimeId;
	}

	public void setCrimeId(final Long crimeId) {
		if (crimeId != null) {
			this.crimeId = crimeId;
		} else {
			this.crimeId = 0l;
		}
	}	
}
