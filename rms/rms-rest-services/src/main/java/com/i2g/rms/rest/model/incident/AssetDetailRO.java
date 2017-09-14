package com.i2g.rms.rest.model.incident;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.AssetRO;
import com.i2g.rms.rest.model.BuildingRO;
import com.i2g.rms.rest.model.EquipmentRO;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.VehicleRO;
import com.i2g.rms.rest.model.WitnessRO;

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
	private Set<WitnessRO> _newWitnesses = new HashSet<WitnessRO>(0);
	private Set<WitnessRO> _existingWitnesses = new HashSet<WitnessRO>(0);
	private Set<UserRO> _employeeWitnesses = new HashSet<UserRO>(0);
	private List<EquipmentRO> _equipments = new ArrayList<EquipmentRO>(0);
	private List<BuildingRO> _buildings = new ArrayList<BuildingRO>(0);
	private List<VehicleRO> _vehicles = new ArrayList<VehicleRO>(0);

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
	 * @return the newWitnesses
	 */
	public Set<WitnessRO> getNewWitnesses() {
		return _newWitnesses;
	}

	/**
	 * @param newWitnesses
	 *            the newWitnesses to set
	 */
	public void setNewWitnesses(final Set<WitnessRO> newWitnesses) {
		_newWitnesses = newWitnesses;
	}

	/**
	 * @return the existingWitnesses
	 */
	public Set<WitnessRO> getExistingWitnesses() {
		return _existingWitnesses;
	}

	/**
	 * @param existingWitnesses
	 *            the existingWitnesses to set
	 */
	public void setExistingWitnesses(final Set<WitnessRO> existingWitnesses) {
		_existingWitnesses = existingWitnesses;
	}

	/**
	 * @return the employeeWitnesses
	 */
	public Set<UserRO> getEmployeeWitnesses() {
		return _employeeWitnesses;
	}

	/**
	 * @param employeeWitnesses
	 *            the employeeWitnesses to set
	 */
	public void setEmployeeWitnesses(final Set<UserRO> employeeWitnesses) {
		_employeeWitnesses = employeeWitnesses;
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
}