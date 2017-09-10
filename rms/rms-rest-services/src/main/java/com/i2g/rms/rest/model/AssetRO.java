package com.i2g.rms.rest.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.tablemaintenance.AssetCategoryRO;

/**
 * REST Object for Asset RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetRO extends AbstractEntityRO {

	private long _id;
	private IncidentRO _incident;
	private StatusFlagRO _statusFlag;
	private AssetCategoryRO _assetCategory;
	private String _statementDescription;
	private YesNoTypeRO _anyWitness;
	private String _otherDescription;
	private Set<EquipmentRO> _equipments = new HashSet<EquipmentRO>(0);
	private Set<BuildingRO> _buildings = new HashSet<BuildingRO>(0);
	private Set<VehicleRO> _vehicles = new HashSet<VehicleRO>(0);
	private Set<WitnessRO> _witnesses = new HashSet<WitnessRO>(0);
	private Set<UserRO> _employeeWitnesses = new HashSet<UserRO>(0);

	/**
	 * @return the id
	 */
	public long getId() {
		return _id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final long id) {
		_id = id;
	}

	/**
	 * @return the incident
	 */
	public IncidentRO getIncident() {
		return _incident;
	}

	/**
	 * @param incident
	 *            the incident to set
	 */
	public void setIncident(final IncidentRO incident) {
		_incident = incident;
	}

	/**
	 * @return the statusFlag
	 */
	public StatusFlagRO getStatusFlag() {
		return _statusFlag;
	}

	/**
	 * @param statusFlag
	 *            the statusFlag to set
	 */
	public void setStatusFlag(final StatusFlagRO statusFlag) {
		_statusFlag = statusFlag;
	}

	/**
	 * @return the assetCategory
	 */
	public AssetCategoryRO getAssetCategory() {
		return _assetCategory;
	}

	/**
	 * @param assetCategory
	 *            the assetCategory to set
	 */
	public void setAssetCategory(final AssetCategoryRO assetCategory) {
		_assetCategory = assetCategory;
	}

	/**
	 * @return the statementDescription
	 */
	public String getStatementDescription() {
		return _statementDescription;
	}

	/**
	 * @param statementDescription
	 *            the statementDescription to set
	 */
	public void setStatementDescription(final String statementDescription) {
		_statementDescription = statementDescription;
	}

	/**
	 * @return the anyWitness
	 */
	public YesNoTypeRO getAnyWitness() {
		return _anyWitness;
	}

	/**
	 * @param anyWitness
	 *            the anyWitness to set
	 */
	public void setAnyWitness(final YesNoTypeRO anyWitness) {
		_anyWitness = anyWitness;
	}

	/**
	 * @return the otherDescription
	 */
	public String getOtherDescription() {
		return _otherDescription;
	}

	/**
	 * @param otherDescription
	 *            the otherDescription to set
	 */
	public void setOtherDescription(final String otherDescription) {
		_otherDescription = otherDescription;
	}

	/**
	 * @return the equipments
	 */
	public Set<EquipmentRO> getEquipments() {
		return _equipments;
	}

	/**
	 * @param equipments
	 *            the equipments to set
	 */
	public void setEquipments(final Set<EquipmentRO> equipments) {
		_equipments = equipments;
	}

	/**
	 * @return the buildings
	 */
	public Set<BuildingRO> getBuildings() {
		return _buildings;
	}

	/**
	 * @param buildings
	 *            the buildings to set
	 */
	public void setBuildings(final Set<BuildingRO> buildings) {
		_buildings = buildings;
	}

	/**
	 * @return the vehicles
	 */
	public Set<VehicleRO> getVehicles() {
		return _vehicles;
	}

	/**
	 * @param vehicles
	 *            the vehicles to set
	 */
	public void setVehicles(final Set<VehicleRO> vehicles) {
		_vehicles = vehicles;
	}

	/**
	 * @return the witnesses
	 */
	public Set<WitnessRO> getWitnesses() {
		return _witnesses;
	}

	/**
	 * @param witnesses
	 *            the witnesses to set
	 */
	public void setWitnesses(final Set<WitnessRO> witnesses) {
		_witnesses = witnesses;
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
}
