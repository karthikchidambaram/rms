package com.i2g.rms.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * REST Object for Address RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressRO extends AbstractEntityRO {

	private long id;
	private String organizationName;
	private String buildingName;
	private String streetName;
	private String localityName;
	private String postTown;
	private String county;
	private String city;
	private String postcode;
	private String country;
	private StatusFlagRO statusFlag;
	private UserRO user;
	private BuildingRO building;
	private AssetRO asset;
	private SuspectRO suspect;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(final String buildingName) {
		this.buildingName = buildingName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(final String streetName) {
		this.streetName = streetName;
	}

	public String getLocalityName() {
		return localityName;
	}

	public void setLocalityName(final String localityName) {
		this.localityName = localityName;
	}

	public String getPostTown() {
		return postTown;
	}

	public void setPostTown(final String postTown) {
		this.postTown = postTown;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(final String county) {
		this.county = county;
	}

	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(final String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public StatusFlagRO getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(final StatusFlagRO statusFlag) {
		this.statusFlag = statusFlag;
	}

	public UserRO getUser() {
		return user;
	}

	public void setUser(final UserRO user) {
		this.user = user;
	}

	public SuspectRO getSuspect() {
		return suspect;
	}

	public void setSuspect(final SuspectRO suspect) {
		this.suspect = suspect;
	}

	public BuildingRO getBuilding() {
		return building;
	}

	public void setBuilding(final BuildingRO building) {
		this.building = building;
	}

	public AssetRO getAsset() {
		return asset;
	}

	public void setAsset(final AssetRO asset) {
		this.asset = asset;
	}
}