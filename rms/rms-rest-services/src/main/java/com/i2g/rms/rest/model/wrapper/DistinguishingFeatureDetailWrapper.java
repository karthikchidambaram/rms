package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureDetailRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistinguishingFeatureDetailWrapper {

	private Long suspectId;
	private Long witnessId;
	private Long crimeSuspectId;
	private Long injuredPersonId;
	private Set<DistinguishingFeatureDetailRO> distinguishingFeatureDetails = new HashSet<DistinguishingFeatureDetailRO>(0);

	public Set<DistinguishingFeatureDetailRO> getDistinguishingFeatureDetails() {
		return distinguishingFeatureDetails;
	}

	public void setDistinguishingFeatureDetails(final Set<DistinguishingFeatureDetailRO> distinguishingFeatureDetails) {
		this.distinguishingFeatureDetails = distinguishingFeatureDetails;
	}

	public Long getSuspectId() {
		return suspectId;
	}

	public void setSuspectId(final Long suspectId) {
		if (suspectId != null) {
			this.suspectId = suspectId;
		} else {
			this.suspectId = 0l;
		}
	}

	public Long getWitnessId() {
		return witnessId;
	}

	public void setWitnessId(final Long witnessId) {
		if (witnessId != null) {
			this.witnessId = witnessId;
		} else {
			this.witnessId = 0l;
		}
	}

	public Long getCrimeSuspectId() {
		return crimeSuspectId;
	}

	public void setCrimeSuspectId(final Long crimeSuspectId) {
		if (crimeSuspectId != null) {
			this.crimeSuspectId = crimeSuspectId;
		} else {
			this.crimeSuspectId = 0l;
		}
	}

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
}
