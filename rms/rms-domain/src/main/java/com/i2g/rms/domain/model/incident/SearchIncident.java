package com.i2g.rms.domain.model.incident;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.i2g.rms.domain.model.AbstractDataModel;

/**
 * View for search incident functionality.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_SRCH_INC_VW")
public class SearchIncident extends AbstractDataModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String uniqueIncidentId;
	private LocalDateTime openedDateTime;
	private String statusFlag;
	private String status;
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
	private String userStatusFlag;
	private String organizationCode;
	private String organizationDescription;
	private String departmentCode;
	private String departmentDescription;
	private String positionCode;
	private String positionDescription;

	/**
	 * @return the id
	 */
	@Id
	@Column(name = "INC_ID")
	public Long getId() {
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
	@Column(name = "UNQ_INC_ID")
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
	@Column(name = "INC_OPN_DTM")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateTimeType")
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
	 * @return the statusFlag
	 */
	@Column(name = "INC_STS_FLG")
	public String getStatusFlag() {
		return statusFlag;
	}

	/**
	 * @param statusFlag
	 *            the statusFlag to set
	 */
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	/**
	 * @return the status
	 */
	@Column(name = "INC_STS")
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the closedDateTime
	 */
	@Column(name = "INC_CLSD_DTM")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateTimeType")
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
	@Column(name = "PRSN_INJRD")
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
	@Column(name = "PROP_DAMAGE")
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
	@Column(name = "CRIME_INVLD")
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
	@Column(name = "INC_DESC")
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
	@Column(name = "CTGRY_CDE")
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
	@Column(name = "CTGRY_DSCPTN")
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
	@Column(name = "TYP_CDE")
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
	@Column(name = "TYP_DSCPTN")
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
	@Column(name = "LOC_CDE")
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
	@Column(name = "LOC_DSCPTN")
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
	@Column(name = "LOC_CHLD_CDE")
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
	@Column(name = "LOC_CHLD_DSCPTN")
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
	@Column(name = "USR_ID")
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
	@Column(name = "USR_LGN_ID")
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
	@Column(name = "INC_RPT_BY")
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
	@Column(name = "USR_STS_FLG")
	public String getUserStatusFlag() {
		return userStatusFlag;
	}

	/**
	 * @param userStatusFlag
	 *            the userStatusFlag to set
	 */
	public void setUserStatusFlag(String userStatusFlag) {
		this.userStatusFlag = userStatusFlag;
	}

	/**
	 * @return the organizationCode
	 */
	@Column(name = "ORG_CDE")
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
	@Column(name = "ORG_DSCPTN")
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
	@Column(name = "DEPT_CDE")
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
	@Column(name = "DEPT_DSCPTN")
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
	@Column(name = "POSTN_CDE")
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
	@Column(name = "POSTN_DSCPTN")
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
