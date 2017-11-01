package com.i2g.rms.rest.model.incident;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.CrimeRO;
import com.i2g.rms.rest.model.CrimeSuspectRO;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.WitnessRO;

/**
 * Search Crime Details RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrimeDetailRO extends BaseIncidentDetailRO {

	private CrimeRO _crime;
	private Set<CrimeSuspectRO> _newCrimeSuspects = new HashSet<CrimeSuspectRO>(0);
	private Set<CrimeSuspectRO> _existingCrimeSuspects = new HashSet<CrimeSuspectRO>(0);
	private Set<UserRO> _employeeCrimeSuspects = new HashSet<UserRO>(0);
	private Set<WitnessRO> _newWitnesses = new HashSet<WitnessRO>(0);
	private Set<WitnessRO> _existingWitnesses = new HashSet<WitnessRO>(0);
	private Set<UserRO> _employeeWitnesses = new HashSet<UserRO>(0);

	/**
	 * @return the newWitnesses
	 */
	public Set<WitnessRO> getNewWitnesses() {
		return _newWitnesses;
	}

	/**
	 * @param newWitnesses
	 *            the newWitnesses to set
	 */
	public void setNewWitnesses(final Set<WitnessRO> newWitnesses) {
		_newWitnesses = newWitnesses;
	}

	/**
	 * @return the existingWitnesses
	 */
	public Set<WitnessRO> getExistingWitnesses() {
		return _existingWitnesses;
	}

	/**
	 * @param existingWitnesses
	 *            the existingWitnesses to set
	 */
	public void setExistingWitnesses(final Set<WitnessRO> existingWitnesses) {
		_existingWitnesses = existingWitnesses;
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

	/**
	 * @return the crime
	 */
	public CrimeRO getCrime() {
		return _crime;
	}

	/**
	 * @param crime
	 *            the crime to set
	 */
	public void setCrime(final CrimeRO crime) {
		_crime = crime;
	}

	/**
	 * @return the newCrimeSuspects
	 */
	public Set<CrimeSuspectRO> getNewCrimeSuspects() {
		return _newCrimeSuspects;
	}

	/**
	 * @param newCrimeSuspects
	 *            the newCrimeSuspects to set
	 */
	public void setNewCrimeSuspects(final Set<CrimeSuspectRO> newCrimeSuspects) {
		_newCrimeSuspects = newCrimeSuspects;
	}

	/**
	 * @return the existingCrimeSuspects
	 */
	public Set<CrimeSuspectRO> getExistingCrimeSuspects() {
		return _existingCrimeSuspects;
	}

	/**
	 * @param existingCrimeSuspects
	 *            the existingCrimeSuspects to set
	 */
	public void setExistingCrimeSuspects(final Set<CrimeSuspectRO> existingCrimeSuspects) {
		_existingCrimeSuspects = existingCrimeSuspects;
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
}
