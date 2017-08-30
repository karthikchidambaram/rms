package com.i2g.rms.rest.model.incident;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.StatusFlagRO;
import com.i2g.rms.rest.model.YesNoTypeRO;

/**
 * Search Incident RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchIncidentRO extends AbstractEntityRO {

	private long id;
	private String uniqueIncidentId;
	private LocalDateTime openedDateTime;
	private StatusFlagRO incidentStatusFlag;
	private IncidentStatusRO incidentStatus;
	private LocalDateTime closedDateTime;
	private String description;	
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
	private YesNoTypeRO propertyDamage;
	private YesNoTypeRO criminalAttack;
	private YesNoTypeRO accidentDamage;
	private YesNoTypeRO vehicleOrAssetDamage;

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
	public void setId(final long id) {
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
	public void setUniqueIncidentId(final String uniqueIncidentId) {
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
	public void setOpenedDateTime(final LocalDateTime openedDateTime) {
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
	public void setIncidentStatusFlag(final StatusFlagRO incidentStatusFlag) {
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
	public void setIncidentStatus(final IncidentStatusRO incidentStatus) {
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
	public void setClosedDateTime(final LocalDateTime closedDateTime) {
		this.closedDateTime = closedDateTime;
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
	public void setDescription(final String description) {
		this.description = description;
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
	public void setTypeCode(final String typeCode) {
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
	public void setTypeDescription(final String typeDescription) {
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
	public void setLocationCode(final String locationCode) {
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
	public void setLocationDescription(final String locationDescription) {
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
	public void setLocationDetailCode(final String locationDetailCode) {
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
	public void setLocationDetailDescription(final String locationDetailDescription) {
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
	public void setUserId(final long userId) {
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
	public void setUserLoginId(final String userLoginId) {
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
	public void setReportedBy(final String reportedBy) {
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
	public void setUserStatusFlag(final StatusFlagRO userStatusFlag) {
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
	public void setOrganizationCode(final String organizationCode) {
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
	public void setOrganizationDescription(final String organizationDescription) {
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
	public void setDepartmentCode(final String departmentCode) {
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
	public void setDepartmentDescription(final String departmentDescription) {
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
	public void setPositionCode(final String positionCode) {
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
	public void setPositionDescription(final String positionDescription) {
		this.positionDescription = positionDescription;
	}

	/**
	 * @return the propertyDamage
	 */
	public YesNoTypeRO getPropertyDamage() {
		return propertyDamage;
	}

	/**
	 * @param propertyDamage the propertyDamage to set
	 */
	public void setPropertyDamage(final YesNoTypeRO propertyDamage) {
		this.propertyDamage = propertyDamage;
	}

	/**
	 * @return the criminalAttack
	 */
	public YesNoTypeRO getCriminalAttack() {
		return criminalAttack;
	}

	/**
	 * @param criminalAttack the criminalAttack to set
	 */
	public void setCriminalAttack(final YesNoTypeRO criminalAttack) {
		this.criminalAttack = criminalAttack;
	}

	/**
	 * @return the accidentDamage
	 */
	public YesNoTypeRO getAccidentDamage() {
		return accidentDamage;
	}

	/**
	 * @param accidentDamage the accidentDamage to set
	 */
	public void setAccidentDamage(final YesNoTypeRO accidentDamage) {
		this.accidentDamage = accidentDamage;
	}

	/**
	 * @return the vehicleOrAssetDamage
	 */
	public YesNoTypeRO getVehicleOrAssetDamage() {
		return vehicleOrAssetDamage;
	}

	/**
	 * @param vehicleOrAssetDamage the vehicleOrAssetDamage to set
	 */
	public void setVehicleOrAssetDamage(final YesNoTypeRO vehicleOrAssetDamage) {
		this.vehicleOrAssetDamage = vehicleOrAssetDamage;
	}	
}
