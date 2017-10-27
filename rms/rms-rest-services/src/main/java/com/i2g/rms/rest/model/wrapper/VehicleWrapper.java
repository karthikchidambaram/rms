package com.i2g.rms.rest.model.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.VehicleRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleWrapper {

	private Long assetId;
	private Long[] vehicleIds;
	private String[] vehicleRegistrationIds;
	private List<VehicleRO> vehicles = new ArrayList<VehicleRO>(0);

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(final Long assetId) {
		this.assetId = assetId;
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

	public List<VehicleRO> getVehicles() {
		return vehicles;
	}

	public void setVehicles(final List<VehicleRO> vehicles) {
		this.vehicles = vehicles;
	}
}