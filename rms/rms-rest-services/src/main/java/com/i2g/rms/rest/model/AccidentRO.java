package com.i2g.rms.rest.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.tablemaintenance.AccidentLocationDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.AccidentLocationRO;
import com.i2g.rms.rest.model.tablemaintenance.AccidentTypeRO;

/**
 * REST Object for Accident.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccidentRO extends AbstractEntityRO {

	private long _id;
	private IncidentRO _incident;
	private String _accidentDescription;
	private StatusFlagRO _statusFlag;
	private LocalDateTime _accidentDateTime;
	private AccidentLocationRO _accidentLocation;
	private AccidentLocationDetailRO _accidentLocationDetails;
	private String _landmark;
	private String _accidentPlace;	
	private AccidentTypeRO _accidentType;
	private Set<InjuredPersonRO> _injuredPersons = new HashSet<InjuredPersonRO>(0);
	private Set<UserRO> _employeeInjuredPersons = new HashSet<UserRO>(0);
	private Set<WitnessRO> _witnesses = new HashSet<WitnessRO>(0);
	private Set<UserRO> _employeeWitnesses = new HashSet<UserRO>(0);

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
	 * @return the incident
	 */
	public IncidentRO getIncident() {
		return _incident;
	}

	/**
	 * @param incident
	 *            the incident to set
	 */
	public void setIncident(final IncidentRO incident) {
		_incident = incident;
	}

	/**
	 * @return the accidentDescription
	 */
	public String getAccidentDescription() {
		return _accidentDescription;
	}

	/**
	 * @param accidentDescription
	 *            the accidentDescription to set
	 */
	public void setAccidentDescription(final String accidentDescription) {
		_accidentDescription = accidentDescription;
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
	 * @return the accidentDateTime
	 */
	public LocalDateTime getAccidentDateTime() {
		return _accidentDateTime;
	}

	/**
	 * @param accidentDateTime
	 *            the accidentDateTime to set
	 */
	public void setAccidentDateTime(final LocalDateTime accidentDateTime) {
		_accidentDateTime = accidentDateTime;
	}

	/**
	 * @return the accidentLocationDetails
	 */
	public AccidentLocationDetailRO getAccidentLocationDetails() {
		return _accidentLocationDetails;
	}

	/**
	 * @param accidentLocationDetails
	 *            the accidentLocationDetails to set
	 */
	public void setAccidentLocationDetails(final AccidentLocationDetailRO accidentLocationDetails) {
		_accidentLocationDetails = accidentLocationDetails;
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
	 * @return the accidentType
	 */
	public AccidentTypeRO getAccidentType() {
		return _accidentType;
	}

	/**
	 * @param accidentType
	 *            the accidentType to set
	 */
	public void setAccidentType(final AccidentTypeRO accidentType) {
		_accidentType = accidentType;
	}

	/**
	 * @return the injuredPersons
	 */
	public Set<InjuredPersonRO> getInjuredPersons() {
		return _injuredPersons;
	}

	/**
	 * @param injuredPersons
	 *            the injuredPersons to set
	 */
	public void setInjuredPersons(final Set<InjuredPersonRO> injuredPersons) {
		_injuredPersons = injuredPersons;
	}

	/**
	 * @return the employeeInjuredPersons
	 */
	public Set<UserRO> getEmployeeInjuredPersons() {
		return _employeeInjuredPersons;
	}

	/**
	 * @param employeeInjuredPersons
	 *            the employeeInjuredPersons to set
	 */
	public void setEmployeeInjuredPersons(final Set<UserRO> employeeInjuredPersons) {
		_employeeInjuredPersons = employeeInjuredPersons;
	}

	/**
	 * @return the witnesses
	 */
	public Set<WitnessRO> getWitnesses() {
		return _witnesses;
	}

	/**
	 * @param witnesses
	 *            the witnesses to set
	 */
	public void setWitnesses(final Set<WitnessRO> witnesses) {
		_witnesses = witnesses;
	}

	/**
	 * @return the accidentLocation
	 */
	public AccidentLocationRO getAccidentLocation() {
		return _accidentLocation;
	}

	/**
	 * @param accidentLocation
	 *            the accidentLocation to set
	 */
	public void setAccidentLocation(final AccidentLocationRO accidentLocation) {
		_accidentLocation = accidentLocation;
	}

	/**
	 * @return the employeeWitnesses
	 */
	public Set<UserRO> getEmployeeWitnesses() {
		return _employeeWitnesses;
	}

	/**
	 * @param employeeWitnesses the employeeWitnesses to set
	 */
	public void setEmployeeWitnesses(final Set<UserRO> employeeWitnesses) {
		_employeeWitnesses = employeeWitnesses;
	}

	/**
	 * @return the accidentPlace
	 */
	public String getAccidentPlace() {
		return _accidentPlace;
	}

	/**
	 * @param accidentPlace the accidentPlace to set
	 */
	public void setAccidentPlace(final String accidentPlace) {
		_accidentPlace = accidentPlace;
	}	
}