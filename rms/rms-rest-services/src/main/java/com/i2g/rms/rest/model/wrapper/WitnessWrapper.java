package com.i2g.rms.rest.model.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.WitnessRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WitnessWrapper {

	private Long accidentId;
	private Long[] employeeIds;
	private String[] employeeLoginIds;
	private List<WitnessRO> witnesses = new ArrayList<WitnessRO>(0);

	public Long getAccidentId() {
		return accidentId;
	}

	public void setAccidentId(final Long accidentId) {
		this.accidentId = accidentId;
	}

	public List<WitnessRO> getWitnesses() {
		return witnesses;
	}

	public void setWitnesses(final List<WitnessRO> witnesses) {
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
}
