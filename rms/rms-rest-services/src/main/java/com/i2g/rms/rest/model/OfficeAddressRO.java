package com.i2g.rms.rest.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.tablemaintenance.OrganizationRO;

/**
 * REST Object for Address RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficeAddressRO extends AbstractEntityRO {

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
	private OrganizationRO organization;
	private Set<IncidentRO> incidents = new HashSet<IncidentRO>(0);
	private String doorNumber;
	private String blockNumber;

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

	public OrganizationRO getOrganization() {
		return organization;
	}

	public void setOrganization(final OrganizationRO organization) {
		this.organization = organization;
	}

	public Set<IncidentRO> getIncidents() {
		return incidents;
	}

	public void setIncidents(Set<IncidentRO> incidents) {
		this.incidents = incidents;
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
}
