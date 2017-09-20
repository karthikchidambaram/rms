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
	private SuspectRO suspect;
	private InjuredPersonRO injuredPerson;
	private WitnessRO witness;
	private CrimeSuspectRO crimeSuspect;
	private String doorNumber;
	private String blockNumber;
	private String apartmentNumber;

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

	/**
	 * @return the injuredPerson
	 */
	public InjuredPersonRO getInjuredPerson() {
		return injuredPerson;
	}

	/**
	 * @param injuredPerson
	 *            the injuredPerson to set
	 */
	public void setInjuredPerson(final InjuredPersonRO injuredPerson) {
		this.injuredPerson = injuredPerson;
	}

	/**
	 * @return the witness
	 */
	public WitnessRO getWitness() {
		return witness;
	}

	/**
	 * @param witness
	 *            the witness to set
	 */
	public void setWitness(final WitnessRO witness) {
		this.witness = witness;
	}

	/**
	 * @return the crimeSuspect
	 */
	public CrimeSuspectRO getCrimeSuspect() {
		return crimeSuspect;
	}

	/**
	 * @param crimeSuspect
	 *            the crimeSuspect to set
	 */
	public void setCrimeSuspect(final CrimeSuspectRO crimeSuspect) {
		this.crimeSuspect = crimeSuspect;
	}

	public String getDoorNumber() {
		return doorNumber;
	}

	public void setDoorNumber(final String doorNumber) {
		this.doorNumber = doorNumber;
	}

	public String getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(final String blockNumber) {
		this.blockNumber = blockNumber;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(final String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}	
}
