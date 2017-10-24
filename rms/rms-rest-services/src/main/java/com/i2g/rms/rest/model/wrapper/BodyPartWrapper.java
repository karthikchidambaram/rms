package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.tablemaintenance.BodyPartRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BodyPartWrapper {

	private Long injuredPersonId;
	private Set<BodyPartRO> bodyParts = new HashSet<BodyPartRO>(0);

	public Long getInjuredPersonId() {
		return injuredPersonId;
	}

	public void setInjuredPersonId(final Long injuredPersonId) {
		if (injuredPersonId != null) {
			this.injuredPersonId = injuredPersonId;
		} else {
			this.injuredPersonId = 0l;
		}
	}

	public Set<BodyPartRO> getBodyParts() {
		return bodyParts;
	}

	public void setBodyParts(final Set<BodyPartRO> bodyParts) {
		this.bodyParts = bodyParts;
	}	
}
