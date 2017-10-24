package com.i2g.rms.rest.model.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.InjuredPersonRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InjuredPersonWrapper {

	private Long accidentId;
	private List<InjuredPersonRO> injuredPersons = new ArrayList<InjuredPersonRO>(0);

	public Long getAccidentId() {
		return accidentId;
	}

	public void setAccidentId(final Long accidentId) {
		this.accidentId = accidentId;
	}

	public List<InjuredPersonRO> getInjuredPersons() {
		return injuredPersons;
	}

	public void setInjuredPersons(final List<InjuredPersonRO> injuredPersons) {
		this.injuredPersons = injuredPersons;
	}
}
