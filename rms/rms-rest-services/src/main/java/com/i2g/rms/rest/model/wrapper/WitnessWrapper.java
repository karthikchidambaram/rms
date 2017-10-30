package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.WitnessRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WitnessWrapper {

	private Long crimeId;
	private Long accidentId;
	private Long[] employeeIds;
	private String[] employeeLoginIds;
	private Set<WitnessRO> witnesses = new HashSet<WitnessRO>(0);

	public Long getAccidentId() {
		return accidentId;
	}

	public void setAccidentId(final Long accidentId) {
		if (accidentId != null) {
			this.accidentId = accidentId;
		} else {
			this.accidentId = 0l;
		}
	}

	public Set<WitnessRO> getWitnesses() {
		return witnesses;
	}

	public void setWitnesses(final Set<WitnessRO> witnesses) {
		this.witnesses = witnesses;
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
