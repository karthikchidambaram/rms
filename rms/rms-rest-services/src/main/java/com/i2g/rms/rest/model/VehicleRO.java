package com.i2g.rms.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.tablemaintenance.VehicleDamageTypeRO;

/**
 * REST Object for Vehicle RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleRO extends AbstractEntityRO {

	private long _id;
	private AssetRO _asset;
	private StatusFlagRO _statusFlag;
	private String _vehicleRegistrationId;
	private String _engineNumber;
	private String _chasisNumber;
	private String _make;
	private String _model;
	private String _commentDescription;
	private VehicleDamageTypeRO _vehicleDamageType;

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
	 * @return the vehicleRegistrationId
	 */
	public String getVehicleRegistrationId() {
		return _vehicleRegistrationId;
	}

	/**
	 * @param vehicleRegistrationId
	 *            the vehicleRegistrationId to set
	 */
	public void setVehicleRegistrationId(final String vehicleRegistrationId) {
		_vehicleRegistrationId = vehicleRegistrationId;
	}

	/**
	 * @return the engineNumber
	 */
	public String getEngineNumber() {
		return _engineNumber;
	}

	/**
	 * @param engineNumber
	 *            the engineNumber to set
	 */
	public void setEngineNumber(final String engineNumber) {
		_engineNumber = engineNumber;
	}

	/**
	 * @return the chasisNumber
	 */
	public String getChasisNumber() {
		return _chasisNumber;
	}

	/**
	 * @param chasisNumber
	 *            the chasisNumber to set
	 */
	public void setChasisNumber(final String chasisNumber) {
		_chasisNumber = chasisNumber;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return _make;
	}

	/**
	 * @param make
	 *            the make to set
	 */
	public void setMake(final String make) {
		_make = make;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return _model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(final String model) {
		_model = model;
	}

	/**
	 * @return the commentDescription
	 */
	public String getCommentDescription() {
		return _commentDescription;
	}

	/**
	 * @param commentDescription
	 *            the commentDescription to set
	 */
	public void setCommentDescription(final String commentDescription) {
		_commentDescription = commentDescription;
	}

	/**
	 * @return the vehicleDamageType
	 */
	public VehicleDamageTypeRO getVehicleDamageType() {
		return _vehicleDamageType;
	}

	/**
	 * @param vehicleDamageType
	 *            the vehicleDamageType to set
	 */
	public void setVehicleDamageType(final VehicleDamageTypeRO vehicleDamageType) {
		_vehicleDamageType = vehicleDamageType;
	}

}
