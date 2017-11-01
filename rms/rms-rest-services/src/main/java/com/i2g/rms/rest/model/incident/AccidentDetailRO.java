package com.i2g.rms.rest.model.incident;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AccidentRO;
import com.i2g.rms.rest.model.InjuredPersonRO;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.WitnessRO;

/**
 * Search Accident Details RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccidentDetailRO extends BaseIncidentDetailRO {

	private AccidentRO accident;
	private Set<InjuredPersonRO> newInjuredPersons = new HashSet<InjuredPersonRO>(0);
	private Set<InjuredPersonRO> existingInjuredPersons = new HashSet<InjuredPersonRO>(0);
	private Set<UserRO> employeeInjuredPersons = new HashSet<UserRO>(0);
	private Set<WitnessRO> newWitnesses = new HashSet<WitnessRO>(0);
	private Set<WitnessRO> existingWitnesses = new HashSet<WitnessRO>(0);
	private Set<UserRO> employeeWitnesses = new HashSet<UserRO>(0);

	/**
	 * @return the accident
	 */
	public AccidentRO getAccident() {
		return accident;
	}

	/**
	 * @param accident
	 *            the accident to set
	 */
	public void setAccident(final AccidentRO accident) {
		this.accident = accident;
	}

	/**
	 * @return the newInjuredPersons
	 */
	public Set<InjuredPersonRO> getNewInjuredPersons() {
		return newInjuredPersons;
	}

	/**
	 * @param newInjuredPersons
	 *            the newInjuredPersons to set
	 */
	public void setNewInjuredPersons(final Set<InjuredPersonRO> newInjuredPersons) {
		this.newInjuredPersons = newInjuredPersons;
	}

	/**
	 * @return the newWitnesses
	 */
	public Set<WitnessRO> getNewWitnesses() {
		return newWitnesses;
	}

	/**
	 * @param newWitnesses
	 *            the newWitnesses to set
	 */
	public void setNewWitnesses(final Set<WitnessRO> newWitnesses) {
		this.newWitnesses = newWitnesses;
	}

	/**
	 * @return the existingInjuredPersons
	 */
	public Set<InjuredPersonRO> getExistingInjuredPersons() {
		return existingInjuredPersons;
	}

	/**
	 * @param existingInjuredPersons
	 *            the existingInjuredPersons to set
	 */
	public void setExistingInjuredPersons(final Set<InjuredPersonRO> existingInjuredPersons) {
		this.existingInjuredPersons = existingInjuredPersons;
	}

	/**
	 * @return the employeeInjuredPersons
	 */
	public Set<UserRO> getEmployeeInjuredPersons() {
		return employeeInjuredPersons;
	}

	/**
	 * @param employeeInjuredPersons
	 *            the employeeInjuredPersons to set
	 */
	public void setEmployeeInjuredPersons(final Set<UserRO> employeeInjuredPersons) {
		this.employeeInjuredPersons = employeeInjuredPersons;
	}

	/**
	 * @return the existingWitnesses
	 */
	public Set<WitnessRO> getExistingWitnesses() {
		return existingWitnesses;
	}

	/**
	 * @param existingWitnesses
	 *            the existingWitnesses to set
	 */
	public void setExistingWitnesses(final Set<WitnessRO> existingWitnesses) {
		this.existingWitnesses = existingWitnesses;
	}

	/**
	 * @return the employeeWitnesses
	 */
	public Set<UserRO> getEmployeeWitnesses() {
		return employeeWitnesses;
	}

	/**
	 * @param employeeWitnesses
	 *            the employeeWitnesses to set
	 */
	public void setEmployeeWitnesses(final Set<UserRO> employeeWitnesses) {
		this.employeeWitnesses = employeeWitnesses;
	}

}
