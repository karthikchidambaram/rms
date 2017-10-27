package com.i2g.rms.rest.model.incident;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.AssetRO;
import com.i2g.rms.rest.model.BuildingRO;
import com.i2g.rms.rest.model.EquipmentRO;
import com.i2g.rms.rest.model.VehicleRO;

/**
 * Search Asset Details RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetDetailRO extends AbstractEntityRO {

	private long _incidentId;
	private String _uniqueIncidentId;
	private AssetRO _asset;
	private List<EquipmentRO> _equipments = new ArrayList<EquipmentRO>(0);
	private List<EquipmentRO> _existingEquipments = new ArrayList<EquipmentRO>(0);
	private List<BuildingRO> _buildings = new ArrayList<BuildingRO>(0);
	private List<BuildingRO> _existingBuildings = new ArrayList<BuildingRO>(0);
	private List<VehicleRO> _vehicles = new ArrayList<VehicleRO>(0);
	private List<VehicleRO> _existingVehicles = new ArrayList<VehicleRO>(0);

	public long getIncidentId() {
		return _incidentId;
	}

	public void setIncidentId(final long incidentId) {
		_incidentId = incidentId;
	}

	public String getUniqueIncidentId() {
		return _uniqueIncidentId;
	}

	public void setUniqueIncidentId(final String uniqueIncidentId) {
		_uniqueIncidentId = uniqueIncidentId;
	}

	/**
	 * @return the asset
	 */
	public AssetRO getAsset() {
		return _asset;
	}

	/**
	 * @param asset
	 *            the asset to set
	 */
	public void setAsset(final AssetRO asset) {
		_asset = asset;
	}

	/**
	 * @return the equipments
	 */
	public List<EquipmentRO> getEquipments() {
		return _equipments;
	}

	/**
	 * @param equipments
	 *            the equipments to set
	 */
	public void setEquipments(final List<EquipmentRO> equipments) {
		_equipments = equipments;
	}

	/**
	 * @return the buildings
	 */
	public List<BuildingRO> getBuildings() {
		return _buildings;
	}

	/**
	 * @param buildings
	 *            the buildings to set
	 */
	public void setBuildings(final List<BuildingRO> buildings) {
		_buildings = buildings;
	}

	/**
	 * @return the vehicles
	 */
	public List<VehicleRO> getVehicles() {
		return _vehicles;
	}

	/**
	 * @param vehicles
	 *            the vehicles to set
	 */
	public void setVehicles(final List<VehicleRO> vehicles) {
		_vehicles = vehicles;
	}

	public List<EquipmentRO> getExistingEquipments() {
		return _existingEquipments;
	}

	public void setExistingEquipments(final List<EquipmentRO> existingEquipments) {
		_existingEquipments = existingEquipments;
	}

	public List<BuildingRO> getExistingBuildings() {
		return _existingBuildings;
	}

	public void setExistingBuildings(final List<BuildingRO> existingBuildings) {
		_existingBuildings = existingBuildings;
	}

	public List<VehicleRO> getExistingVehicles() {
		return _existingVehicles;
	}

	public void setExistingVehicles(final List<VehicleRO> existingVehicles) {
		_existingVehicles = existingVehicles;
	}	
}
