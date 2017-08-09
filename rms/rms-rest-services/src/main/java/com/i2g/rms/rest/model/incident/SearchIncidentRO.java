package com.i2g.rms.rest.model.incident;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.StatusFlagRO;

/**
 * Search Incident RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchIncidentRO extends AbstractEntityRO {

	private long id;
	private String uniqueIncidentId;
	private LocalDateTime openedDateTime;
	private StatusFlagRO incidentStatusFlag;
	private IncidentStatusRO incidentStatus;
	private LocalDateTime closedDateTime;
	private String personInjured;
	private String propertyDamage;
	private String crimeInvolved;
	private String description;
	private String categoryCode;
	private String categoryDescription;
	private String typeCode;
	private String typeDescription;
	private String locationCode;
	private String locationDescription;
	private String locationDetailCode;
	private String locationDetailDescription;
	private long userId;
	private String userLoginId;
	private String reportedBy;
	private StatusFlagRO userStatusFlag;
	private String organizationCode;
	private String organizationDescription;
	private String departmentCode;
	private String departmentDescription;
	private String positionCode;
	private String positionDescription;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the uniqueIncidentId
	 */
	public String getUniqueIncidentId() {
		return uniqueIncidentId;
	}

	/**
	 * @param uniqueIncidentId
	 *            the uniqueIncidentId to set
	 */
	public void setUniqueIncidentId(String uniqueIncidentId) {
		this.uniqueIncidentId = uniqueIncidentId;
	}

	/**
	 * @return the openedDateTime
	 */
	public LocalDateTime getOpenedDateTime() {
		return openedDateTime;
	}

	/**
	 * @param openedDateTime
	 *            the openedDateTime to set
	 */
	public void setOpenedDateTime(LocalDateTime openedDateTime) {
		this.openedDateTime = openedDateTime;
	}

	/**
	 * @return the incidentStatusFlag
	 */
	public StatusFlagRO getIncidentStatusFlag() {
		return incidentStatusFlag;
	}

	/**
	 * @param incidentStatusFlag
	 *            the incidentStatusFlag to set
	 */
	public void setIncidentStatusFlag(StatusFlagRO incidentStatusFlag) {
		this.incidentStatusFlag = incidentStatusFlag;
	}

	/**
	 * @return the incidentStatus
	 */
	public IncidentStatusRO getIncidentStatus() {
		return incidentStatus;
	}

	/**
	 * @param incidentStatus
	 *            the incidentStatus to set
	 */
	public void setIncidentStatus(IncidentStatusRO incidentStatus) {
		this.incidentStatus = incidentStatus;
	}

	/**
	 * @return the closedDateTime
	 */
	public LocalDateTime getClosedDateTime() {
		return closedDateTime;
	}

	/**
	 * @param closedDateTime
	 *            the closedDateTime to set
	 */
	public void setClosedDateTime(LocalDateTime closedDateTime) {
		this.closedDateTime = closedDateTime;
	}

	/**
	 * @return the personInjured
	 */
	public String getPersonInjured() {
		return personInjured;
	}

	/**
	 * @param personInjured
	 *            the personInjured to set
	 */
	public void setPersonInjured(String personInjured) {
		this.personInjured = personInjured;
	}

	/**
	 * @return the propertyDamage
	 */
	public String getPropertyDamage() {
		return propertyDamage;
	}

	/**
	 * @param propertyDamage
	 *            the propertyDamage to set
	 */
	public void setPropertyDamage(String propertyDamage) {
		this.propertyDamage = propertyDamage;
	}

	/**
	 * @return the crimeInvolved
	 */
	public String getCrimeInvolved() {
		return crimeInvolved;
	}

	/**
	 * @param crimeInvolved
	 *            the crimeInvolved to set
	 */
	public void setCrimeInvolved(String crimeInvolved) {
		this.crimeInvolved = crimeInvolved;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the categoryCode
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * @param categoryCode
	 *            the categoryCode to set
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * @return the categoryDescription
	 */
	public String getCategoryDescription() {
		return categoryDescription;
	}

	/**
	 * @param categoryDescription
	 *            the categoryDescription to set
	 */
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode
	 *            the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * @return the typeDescription
	 */
	public String getTypeDescription() {
		return typeDescription;
	}

	/**
	 * @param typeDescription
	 *            the typeDescription to set
	 */
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	/**
	 * @return the locationCode
	 */
	public String getLocationCode() {
		return locationCode;
	}

	/**
	 * @param locationCode
	 *            the locationCode to set
	 */
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	/**
	 * @return the locationDescription
	 */
	public String getLocationDescription() {
		return locationDescription;
	}

	/**
	 * @param locationDescription
	 *            the locationDescription to set
	 */
	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	/**
	 * @return the locationDetailCode
	 */
	public String getLocationDetailCode() {
		return locationDetailCode;
	}

	/**
	 * @param locationDetailCode
	 *            the locationDetailCode to set
	 */
	public void setLocationDetailCode(String locationDetailCode) {
		this.locationDetailCode = locationDetailCode;
	}

	/**
	 * @return the locationDetailDescription
	 */
	public String getLocationDetailDescription() {
		return locationDetailDescription;
	}

	/**
	 * @param locationDetailDescription
	 *            the locationDetailDescription to set
	 */
	public void setLocationDetailDescription(String locationDetailDescription) {
		this.locationDetailDescription = locationDetailDescription;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userLoginId
	 */
	public String getUserLoginId() {
		return userLoginId;
	}

	/**
	 * @param userLoginId
	 *            the userLoginId to set
	 */
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	/**
	 * @return the reportedBy
	 */
	public String getReportedBy() {
		return reportedBy;
	}

	/**
	 * @param reportedBy
	 *            the reportedBy to set
	 */
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	/**
	 * @return the userStatusFlag
	 */
	public StatusFlagRO getUserStatusFlag() {
		return userStatusFlag;
	}

	/**
	 * @param userStatusFlag
	 *            the userStatusFlag to set
	 */
	public void setUserStatusFlag(StatusFlagRO userStatusFlag) {
		this.userStatusFlag = userStatusFlag;
	}

	/**
	 * @return the organizationCode
	 */
	public String getOrganizationCode() {
		return organizationCode;
	}

	/**
	 * @param organizationCode
	 *            the organizationCode to set
	 */
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	/**
	 * @return the organizationDescription
	 */
	public String getOrganizationDescription() {
		return organizationDescription;
	}

	/**
	 * @param organizationDescription
	 *            the organizationDescription to set
	 */
	public void setOrganizationDescription(String organizationDescription) {
		this.organizationDescription = organizationDescription;
	}

	/**
	 * @return the departmentCode
	 */
	public String getDepartmentCode() {
		return departmentCode;
	}

	/**
	 * @param departmentCode
	 *            the departmentCode to set
	 */
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	/**
	 * @return the departmentDescription
	 */
	public String getDepartmentDescription() {
		return departmentDescription;
	}

	/**
	 * @param departmentDescription
	 *            the departmentDescription to set
	 */
	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

	/**
	 * @return the positionCode
	 */
	public String getPositionCode() {
		return positionCode;
	}

	/**
	 * @param positionCode
	 *            the positionCode to set
	 */
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	/**
	 * @return the positionDescription
	 */
	public String getPositionDescription() {
		return positionDescription;
	}

	/**
	 * @param positionDescription
	 *            the positionDescription to set
	 */
	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}
}
