package com.i2g.rms.domain.model.incident;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.i2g.rms.domain.model.AbstractDataModel;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.YesNoType;

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
	private StatusFlag incidentStatusFlag;
	private IncidentStatus incidentStatus;
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
	private StatusFlag userStatusFlag;
	private String organizationCode;
	private String organizationDescription;
	private String departmentCode;
	private String departmentDescription;
	private String positionCode;
	private String positionDescription;
	private YesNoType assetDamage;
	private YesNoType criminalAttack;
	private YesNoType accidentDamage;
		
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected SearchIncident() {
	}

	/**
	 * @return the id
	 */
	@Id
	@Column(name = "INC_ID")
	@Override
	public Long getId() {
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
	@Column(name = "UNQ_INC_ID")
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
	@Column(name = "INC_OPN_DTM")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateTimeType")
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
	 * @return the statusIncidentFlag
	 */
	@Column(name = "INC_STS_FLG")
	@Enumerated(EnumType.STRING)
	public StatusFlag getIncidentStatusFlag() {
		return incidentStatusFlag;
	}

	/**
	 * @param statusIncidentFlag
	 *            the statusIncidentFlag to set
	 */
	public void setIncidentStatusFlag(final StatusFlag incidentStatusFlag) {
		this.incidentStatusFlag = incidentStatusFlag;
	}

	/**
	 * @return the incidentStatus
	 */
	@Column(name = "INC_STS")
	@Enumerated(EnumType.STRING)
	public IncidentStatus getIncidentStatus() {
		return incidentStatus;
	}

	/**
	 * @param incidentStatus
	 *            the incidentStatus to set
	 */
	public void setIncidentStatus(final IncidentStatus incidentStatus) {
		this.incidentStatus = incidentStatus;
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
	public void setClosedDateTime(final LocalDateTime closedDateTime) {
		this.closedDateTime = closedDateTime;
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
	public void setDescription(final String description) {
		this.description = description;
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
	public void setTypeCode(final String typeCode) {
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
	public void setTypeDescription(final String typeDescription) {
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
	public void setLocationCode(final String locationCode) {
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
	public void setLocationDescription(final String locationDescription) {
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
	public void setLocationDetailCode(final String locationDetailCode) {
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
	public void setLocationDetailDescription(final String locationDetailDescription) {
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
	public void setUserId(final long userId) {
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
	public void setUserLoginId(final String userLoginId) {
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
	public void setReportedBy(final String reportedBy) {
		this.reportedBy = reportedBy;
	}

	/**
	 * @return the userStatusFlag
	 */
	@Column(name = "USR_STS_FLG")
	@Enumerated(EnumType.STRING)
	public StatusFlag getUserStatusFlag() {
		return userStatusFlag;
	}

	/**
	 * @param userStatusFlag
	 *            the userStatusFlag to set
	 */
	public void setUserStatusFlag(final StatusFlag userStatusFlag) {
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
	public void setOrganizationCode(final String organizationCode) {
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
	public void setOrganizationDescription(final String organizationDescription) {
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
	public void setDepartmentCode(final String departmentCode) {
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
	public void setDepartmentDescription(final String departmentDescription) {
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
	public void setPositionCode(final String positionCode) {
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
	public void setPositionDescription(final String positionDescription) {
		this.positionDescription = positionDescription;
	}

	/**
	 * @return the assetDamage
	 */
	@Column(name = "ASST_DMGE")
	@Enumerated(EnumType.STRING)
	public YesNoType getAssetDamage() {
		return assetDamage;
	}

	/**
	 * @param assetDamage the assetDamage to set
	 */
	public void setAssetDamage(final YesNoType assetDamage) {
		this.assetDamage = assetDamage;
	}

	/**
	 * @return the criminalAttack
	 */
	@Column(name = "CRIM_ATTK")
	@Enumerated(EnumType.STRING)
	public YesNoType getCriminalAttack() {
		return criminalAttack;
	}

	/**
	 * @param criminalAttack the criminalAttack to set
	 */
	public void setCriminalAttack(final YesNoType criminalAttack) {
		this.criminalAttack = criminalAttack;
	}

	/**
	 * @return the accidentDamage
	 */
	@Column(name = "ACCI_DMGE")
	@Enumerated(EnumType.STRING)
	public YesNoType getAccidentDamage() {
		return accidentDamage;
	}

	/**
	 * @param accidentDamage the accidentDamage to set
	 */
	public void setAccidentDamage(final YesNoType accidentDamage) {
		this.accidentDamage = accidentDamage;
	}	
}
