package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.VehicleRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleWrapper {

	private Long assetId;
	private Long[] vehicleIds;
	private String[] vehicleRegistrationIds;
	private Set<VehicleRO> vehicles = new HashSet<VehicleRO>(0);

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(final Long assetId) {
		if (assetId != null) {
			this.assetId = assetId;
		} else {
			this.assetId = 0l;
		}
	}

	public Long[] getVehicleIds() {
		return vehicleIds;
	}

	public void setVehicleIds(final Long[] vehicleIds) {
		this.vehicleIds = vehicleIds;
	}

	public String[] getVehicleRegistrationIds() {
		return vehicleRegistrationIds;
	}

	public void setVehicleRegistrationIds(final String[] vehicleRegistrationIds) {
		this.vehicleRegistrationIds = vehicleRegistrationIds;
	}

	public Set<VehicleRO> getVehicles() {
		return vehicles;
	}

	public void setVehicles(final Set<VehicleRO> vehicles) {
		this.vehicles = vehicles;
	}
}