package com.i2g.rms.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private AssetRO _asset;
	private StatusFlagRO _statusFlag;
	private String _buildingId;
	private String _buildingDescription;
	private String _incidentDescription;

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
	 * @return the incidentDescription
	 */
	public String getIncidentDescription() {
		return _incidentDescription;
	}

	/**
	 * @param incidentDescription
	 *            the incidentDescription to set
	 */
	public void setIncidentDescription(final String incidentDescription) {
		_incidentDescription = incidentDescription;
	}

}
