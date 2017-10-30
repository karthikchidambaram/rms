package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AddressRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressWrapper {

	private Long suspectId;
	private Long witnessId;
	private Long crimeSuspectId;
	private Long injuredPersonId;
	private Long userId;
	private Long buildingId;
	private Set<AddressRO> addresses = new HashSet<AddressRO>(0);

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(final Long userId) {
		if (userId != null) {
			this.userId = userId;
		} else {
			this.userId = 0l;
		}
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(final Long buildingId) {
		if (buildingId != null) {
			this.buildingId = buildingId;
		} else {
			this.buildingId = 0l;
		}
	}

	public Set<AddressRO> getAddresses() {
		return addresses;
	}

	public void setAddresses(final Set<AddressRO> addresses) {
		this.addresses = addresses;
	}
}
