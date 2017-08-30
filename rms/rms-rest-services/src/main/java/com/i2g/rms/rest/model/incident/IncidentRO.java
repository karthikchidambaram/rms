package com.i2g.rms.rest.model.incident;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.StatusFlagRO;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.YesNoTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.EntryPointRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentLocationDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentTypeRO;

/**
 * REST Object for returning password history details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentRO extends AbstractEntityRO {

	private long _id;
	private String _uniqueIncidentId;
	private LocalDateTime _incidentOpenedDateTime;
	private IncidentTypeRO _incidentType;
	private String _placeOfIncident;
	private String _landmark;
	private EntryPointRO _entryPoint;
	private IncidentStatusRO _incidentStatus;
	private IncidentLocationDetailRO _incidentLocationDetails;
	private String _incidentDescription;
	private StatusFlagRO _statusFlag;
	private UserRO _incidentReportedBy;
	private LocalDateTime _incidentClosedDateTime;
	private Set<SuspectRO> _suspects = new HashSet<SuspectRO>(0);
	private Set<UserRO> _employeeSuspects = new HashSet<UserRO>(0);
	private YesNoTypeRO _propertyDamage;
	private YesNoTypeRO _criminalAttack;
	private YesNoTypeRO _accidentDamage;
	private YesNoTypeRO _vehicleOrAssetDamage;
	
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
	 * @return the incidentOpenedDateTime
	 */
	public LocalDateTime getIncidentOpenedDateTime() {
		return _incidentOpenedDateTime;
	}

	/**
	 * @param incidentOpenedDateTime
	 *            the incidentOpenedDateTime to set
	 */
	public void setIncidentOpenedDateTime(final LocalDateTime incidentOpenedDateTime) {
		_incidentOpenedDateTime = incidentOpenedDateTime;
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
	 * @return the user
	 */
	public UserRO getIncidentReportedBy() {
		return _incidentReportedBy;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setIncidentReportedBy(final UserRO incidentReportedBy) {
		_incidentReportedBy = incidentReportedBy;
	}

	/**
	 * @return the incidentClosedDateTime
	 */
	public LocalDateTime getIncidentClosedDateTime() {
		return _incidentClosedDateTime;
	}

	/**
	 * @param incidentClosedDateTime
	 *            the incidentClosedDateTime to set
	 */
	public void setIncidentClosedDateTime(final LocalDateTime incidentClosedDateTime) {
		_incidentClosedDateTime = incidentClosedDateTime;
	}

	/**
	 * @return the suspects
	 */
	public Set<SuspectRO> getSuspects() {
		return _suspects;
	}

	/**
	 * @param suspects
	 *            the suspects to set
	 */
	public void setSuspects(final Set<SuspectRO> suspects) {
		_suspects = suspects;
	}

	/**
	 * @return the employeeSuspects
	 */
	public Set<UserRO> getEmployeeSuspects() {
		return _employeeSuspects;
	}

	/**
	 * @param employeeSuspects the employeeSuspects to set
	 */
	public void setEmployeeSuspects(final Set<UserRO> employeeSuspects) {
		_employeeSuspects = employeeSuspects;
	}

	public YesNoTypeRO getPropertyDamage() {
		return _propertyDamage;
	}

	public YesNoTypeRO getCriminalAttack() {
		return _criminalAttack;
	}

	public YesNoTypeRO getAccidentDamage() {
		return _accidentDamage;
	}

	public YesNoTypeRO getVehicleOrAssetDamage() {
		return _vehicleOrAssetDamage;
	}

	public void setPropertyDamage(final YesNoTypeRO propertyDamage) {
		_propertyDamage = propertyDamage;
	}

	public void setCriminalAttack(final YesNoTypeRO criminalAttack) {
		_criminalAttack = criminalAttack;
	}

	public void setAccidentDamage(final YesNoTypeRO accidentDamage) {
		_accidentDamage = accidentDamage;
	}

	public void setVehicleOrAssetDamage(final YesNoTypeRO vehicleOrAssetDamage) {
		_vehicleOrAssetDamage = vehicleOrAssetDamage;
	}	
}
