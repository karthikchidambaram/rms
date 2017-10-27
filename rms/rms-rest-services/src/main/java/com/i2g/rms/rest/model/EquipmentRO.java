package com.i2g.rms.rest.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.tablemaintenance.AssetCategoryRO;

/**
 * REST Object for Equipment RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentRO extends AbstractEntityRO {

	private long _id;
	private Set<AssetRO> _assets;
	private StatusFlagRO _statusFlag;
	private String _equipmentId;
	private String _equipmentDetails;
	private String _serialNumber;
	private AssetCategoryRO _assetCategory;

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
	 * @return the assets
	 */
	public Set<AssetRO> getAssets() {
		return _assets;
	}

	/**
	 * @param assets
	 *            the assets to set
	 */
	public void setAssets(final Set<AssetRO> assets) {
		_assets = assets;
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
	 * @return the equipmentId
	 */
	public String getEquipmentId() {
		return _equipmentId;
	}

	/**
	 * @param equipmentId
	 *            the equipmentId to set
	 */
	public void setEquipmentId(final String equipmentId) {
		_equipmentId = equipmentId;
	}

	/**
	 * @return the equipmentDetails
	 */
	public String getEquipmentDetails() {
		return _equipmentDetails;
	}

	/**
	 * @param equipmentDetails
	 *            the equipmentDetails to set
	 */
	public void setEquipmentDetails(final String equipmentDetails) {
		_equipmentDetails = equipmentDetails;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return _serialNumber;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(final String serialNumber) {
		_serialNumber = serialNumber;
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
}
