package com.i2g.rms.rest.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.incident.IncidentRO;

/**
 * REST Object for Crime RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrimeRO extends AbstractEntityRO {

	private long _id;
	private IncidentRO _incident;
	private StatusFlagRO _statusFlag;
	private LocalDateTime _crimeDateTime;
	private String _crimeDescription;
	private YesNoTypeRO _anyWitness;
	private Set<CrimeSuspectRO> _crimeSuspects = new HashSet<CrimeSuspectRO>(0);
	private Set<UserRO> _employeeCrimeSuspects = new HashSet<UserRO>(0);
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
	 * @return the crimeDateTime
	 */
	public LocalDateTime getCrimeDateTime() {
		return _crimeDateTime;
	}

	/**
	 * @param crimeDateTime
	 *            the crimeDateTime to set
	 */
	public void setCrimeDateTime(final LocalDateTime crimeDateTime) {
		_crimeDateTime = crimeDateTime;
	}

	/**
	 * @return the crimeDescription
	 */
	public String getCrimeDescription() {
		return _crimeDescription;
	}

	/**
	 * @param crimeDescription
	 *            the crimeDescription to set
	 */
	public void setCrimeDescription(final String crimeDescription) {
		_crimeDescription = crimeDescription;
	}

	/**
	 * @return the anyWitness
	 */
	public YesNoTypeRO getAnyWitness() {
		return _anyWitness;
	}

	/**
	 * @param anyWitness
	 *            the anyWitness to set
	 */
	public void setAnyWitness(final YesNoTypeRO anyWitness) {
		_anyWitness = anyWitness;
	}

	/**
	 * @return the crimeSuspects
	 */
	public Set<CrimeSuspectRO> getCrimeSuspects() {
		return _crimeSuspects;
	}

	/**
	 * @param crimeSuspects
	 *            the crimeSuspects to set
	 */
	public void setCrimeSuspects(final Set<CrimeSuspectRO> crimeSuspects) {
		_crimeSuspects = crimeSuspects;
	}

	/**
	 * @return the employeeCrimeSuspects
	 */
	public Set<UserRO> getEmployeeCrimeSuspects() {
		return _employeeCrimeSuspects;
	}

	/**
	 * @param employeeCrimeSuspects
	 *            the employeeCrimeSuspects to set
	 */
	public void setEmployeeCrimeSuspects(final Set<UserRO> employeeCrimeSuspects) {
		_employeeCrimeSuspects = employeeCrimeSuspects;
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
	 * @return the employeeWitnesses
	 */
	public Set<UserRO> getEmployeeWitnesses() {
		return _employeeWitnesses;
	}

	/**
	 * @param employeeWitnesses
	 *            the employeeWitnesses to set
	 */
	public void setEmployeeWitnesses(final Set<UserRO> employeeWitnesses) {
		_employeeWitnesses = employeeWitnesses;
	}
}
