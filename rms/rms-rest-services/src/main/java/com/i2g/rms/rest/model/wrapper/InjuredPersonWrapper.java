package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.InjuredPersonRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InjuredPersonWrapper {

	private Long accidentId;
	private Long[] employeeIds;
	private String[] employeeLoginIds;
	private Set<InjuredPersonRO> injuredPersons = new HashSet<InjuredPersonRO>(0);

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

	public Set<InjuredPersonRO> getInjuredPersons() {
		return injuredPersons;
	}

	public void setInjuredPersons(final Set<InjuredPersonRO> injuredPersons) {
		this.injuredPersons = injuredPersons;
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
