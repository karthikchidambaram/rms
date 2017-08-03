package com.i2g.rms.util.test.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.i2g.rms.util.test.model.tablemaintenance.EntryPointRO;
import com.i2g.rms.util.test.model.tablemaintenance.IncidentCategoryRO;
import com.i2g.rms.util.test.model.tablemaintenance.IncidentLocationDetailRO;
import com.i2g.rms.util.test.model.tablemaintenance.IncidentTypeRO;

/**
 * REST Object for returning password history details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentRO extends AbstractEntityRO {

	private long _id;
	private String _uniqueIncidentId;
	private LocalDate _incidentDate;
	private Long _incidentTime;
	private IncidentTypeRO _incidentType;
	private String _placeOfIncident;
	private String _landmark;
	private EntryPointRO _entryPoint;
	private IncidentStatusRO _incidentStatus;
	private IncidentLocationDetailRO _incidentLocationDetails;
	private String _incidentDescription;
	private StatusFlagRO _statusFlag;
	private YesNoTypeRO _personInjured;
	private YesNoTypeRO _propertyDamage;
	private YesNoTypeRO _crimeInvolved;
	private UserRO _user;
	private IncidentCategoryRO _incidentCategory;

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
	public void setId(long id) {
		_id = id;
	}

	/**
	 * @return the uniqueIncidentId
	 */
	public String getUniqueIncidentId() {
		return _uniqueIncidentId;
	}

	/**
	 * @param uniqueIncidentId
	 *            the uniqueIncidentId to set
	 */
	public void setUniqueIncidentId(final String uniqueIncidentId) {
		_uniqueIncidentId = uniqueIncidentId;
	}

	/**
	 * @return the incidentDate
	 */
	public LocalDate getIncidentDate() {
		return _incidentDate;
	}

	/**
	 * @param incidentDate
	 *            the incidentDate to set
	 */
	public void setIncidentDate(final LocalDate incidentDate) {
		_incidentDate = incidentDate;
	}

	/**
	 * @return the incidentTime
	 */
	public Long getIncidentTime() {
		return _incidentTime;
	}

	/**
	 * @param incidentTime
	 *            the incidentTime to set
	 */
	public void setIncidentTime(final Long incidentTime) {
		_incidentTime = incidentTime;
	}

	/**
	 * @return the incidentType
	 */
	public IncidentTypeRO getIncidentType() {
		return _incidentType;
	}

	/**
	 * @param incidentType
	 *            the incidentType to set
	 */
	public void setIncidentType(final IncidentTypeRO incidentType) {
		_incidentType = incidentType;
	}

	/**
	 * @return the placeOfIncident
	 */
	public String getPlaceOfIncident() {
		return _placeOfIncident;
	}

	/**
	 * @param placeOfIncident
	 *            the placeOfIncident to set
	 */
	public void setPlaceOfIncident(final String placeOfIncident) {
		_placeOfIncident = placeOfIncident;
	}

	/**
	 * @return the landmark
	 */
	public String getLandmark() {
		return _landmark;
	}

	/**
	 * @param landmark
	 *            the landmark to set
	 */
	public void setLandmark(final String landmark) {
		_landmark = landmark;
	}

	/**
	 * @return the entryPoint
	 */
	public EntryPointRO getEntryPoint() {
		return _entryPoint;
	}

	/**
	 * @param entryPoint
	 *            the entryPoint to set
	 */
	public void setEntryPoint(final EntryPointRO entryPoint) {
		_entryPoint = entryPoint;
	}

	/**
	 * @return the incidentStatus
	 */
	public IncidentStatusRO getIncidentStatus() {
		return _incidentStatus;
	}

	/**
	 * @param incidentStatus
	 *            the incidentStatus to set
	 */
	public void setIncidentStatus(final IncidentStatusRO incidentStatus) {
		_incidentStatus = incidentStatus;
	}

	/**
	 * @return the incidentLocationDetails
	 */
	public IncidentLocationDetailRO getIncidentLocationDetails() {
		return _incidentLocationDetails;
	}

	/**
	 * @param incidentLocationDetails
	 *            the incidentLocationDetails to set
	 */
	public void setIncidentLocationDetails(final IncidentLocationDetailRO incidentLocationDetails) {
		_incidentLocationDetails = incidentLocationDetails;
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
	 * @return the personInjured
	 */
	public YesNoTypeRO getPersonInjured() {
		return _personInjured;
	}

	/**
	 * @param personInjured
	 *            the personInjured to set
	 */
	public void setPersonInjured(final YesNoTypeRO personInjured) {
		_personInjured = personInjured;
	}

	/**
	 * @return the propertyDamage
	 */
	public YesNoTypeRO getPropertyDamage() {
		return _propertyDamage;
	}

	/**
	 * @param propertyDamage
	 *            the propertyDamage to set
	 */
	public void setPropertyDamage(final YesNoTypeRO propertyDamage) {
		_propertyDamage = propertyDamage;
	}

	/**
	 * @return the crimeInvolved
	 */
	public YesNoTypeRO getCrimeInvolved() {
		return _crimeInvolved;
	}

	/**
	 * @param crimeInvolved
	 *            the crimeInvolved to set
	 */
	public void setCrimeInvolved(final YesNoTypeRO crimeInvolved) {
		_crimeInvolved = crimeInvolved;
	}

	/**
	 * @return the user
	 */
	public UserRO getUser() {
		return _user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(final UserRO user) {
		_user = user;
	}

	/**
	 * @return the incidentCategory
	 */
	public IncidentCategoryRO getIncidentCategory() {
		return _incidentCategory;
	}

	/**
	 * @param incidentCategory
	 *            the incidentCategory to set
	 */
	public void setIncidentCategory(final IncidentCategoryRO incidentCategory) {
		_incidentCategory = incidentCategory;
	}
}
