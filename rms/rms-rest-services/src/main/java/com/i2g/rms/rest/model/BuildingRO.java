package com.i2g.rms.rest.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.tablemaintenance.AssetCategoryRO;

/**
 * REST Object for Building RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildingRO extends AbstractEntityRO {

	private long _id;
	private Set<AssetRO> _assets;
	private StatusFlagRO _statusFlag;
	private String _buildingId;
	private String _buildingDescription;
	private String _buildingName;
	private AssetCategoryRO _assetCategory;
	private Set<AddressRO> _addresses = new HashSet<AddressRO>(0);

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
	 * @return the buildingId
	 */
	public String getBuildingId() {
		return _buildingId;
	}

	/**
	 * @param buildingId
	 *            the buildingId to set
	 */
	public void setBuildingId(final String buildingId) {
		_buildingId = buildingId;
	}

	/**
	 * @return the buildingDescription
	 */
	public String getBuildingDescription() {
		return _buildingDescription;
	}

	/**
	 * @param buildingDescription
	 *            the buildingDescription to set
	 */
	public void setBuildingDescription(final String buildingDescription) {
		_buildingDescription = buildingDescription;
	}

	/**
	 * @return the buildingName
	 */
	public String getBuildingName() {
		return _buildingName;
	}

	/**
	 * @param buildingName
	 *            the buildingName to set
	 */
	public void setBuildingName(final String buildingName) {
		_buildingName = buildingName;
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
	 * @return the addresses
	 */
	public Set<AddressRO> getAddresses() {
		return _addresses;
	}

	/**
	 * @param addresses
	 *            the addresses to set
	 */
	public void setAddresses(final Set<AddressRO> addresses) {
		_addresses = addresses;
	}
}
