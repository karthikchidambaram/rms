package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.CrimeRO;
import com.i2g.rms.rest.model.CrimeSuspectRO;
import com.i2g.rms.rest.model.WitnessRO;
import com.i2g.rms.rest.model.wrapper.CrimeSuspectWrapper;
import com.i2g.rms.rest.model.wrapper.WitnessWrapper;

/**
 * Rest Service Interface for Crime rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface CrimeRestService {
	
	public List<CrimeRO> get();
	public CrimeRO get(final Long crimeId);
	public CrimeRO getCrimeByIncidentId(final Long incidentId);
	public CrimeRO getCrimeByUniqueIncidentId(final String uniqueIncidentId);
	public CrimeRO createCrime(final CrimeRO crimeRO);
	public CrimeRO updateCrime(final CrimeRO crimeRO);
	public CrimeRO addOrUpdateCrime(final CrimeRO crimeRO);	
	
	//Crime suspects to crime
	public CrimeRO addCrimeSuspectForCrime(final Long crimeId, final CrimeSuspectRO crimeSuspectRO);
	public CrimeRO addCrimeSuspectsForCrime(final CrimeSuspectWrapper crimeSuspectWrapper);
	public CrimeRO addExistingCrimeSuspectForCrime(final Long crimeId, final Long crimeSuspectId);
	public CrimeRO addExistingCrimeSuspectsForCrime(final CrimeSuspectWrapper crimeSuspectWrapper);
	public CrimeRO addEmployeeCrimeSuspectForCrimeById(final Long crimeId, final Long employeeId);
	public CrimeRO addEmployeeCrimeSuspectForCrimeByLoginId(final Long crimeId, final String employeeLoginId);
	public CrimeRO addEmployeeCrimeSuspectsForCrimeByIds(final CrimeSuspectWrapper crimeSuspectWrapper);
	public CrimeRO addEmployeeCrimeSuspectsForCrimeByLoginIds(final CrimeSuspectWrapper crimeSuspectWrapper);
	
	public CrimeRO removeCrimeSuspectFromCrime(final Long crimeId, final Long crimeSuspectId);
	public CrimeRO removeCrimeSuspectsFromCrime(final CrimeSuspectWrapper crimeSuspectWrapper);
	public CrimeRO removeEmployeeCrimeSuspectFromCrimeById(final Long crimeId, final Long employeeId);
	public CrimeRO removeEmployeeCrimeSuspectFromCrimeByLoginId(final Long crimeId, final String employeeLoginId);
	public CrimeRO removeEmployeeCrimeSuspectsFromCrimeByIds(final CrimeSuspectWrapper crimeSuspectWrapper);
	public CrimeRO removeEmployeeCrimeSuspectsFromCrimeByLoginIds(final CrimeSuspectWrapper crimeSuspectWrapper);
	
	//Add witness to crime
	public CrimeRO addWitnessToCrime(final Long crimeId, final WitnessRO witnessRO);
	public CrimeRO addWitnessesToCrime(final WitnessWrapper witnessWrapper);
	public CrimeRO addExistingWitnessToCrime(final Long crimeId, final Long witnessId);
	public CrimeRO addExistingWitnessesToCrime(final WitnessWrapper witnessWrapper);
	public CrimeRO addEmployeeWitnessToCrimeById(final Long crimeId, final Long employeeId);
	public CrimeRO addEmployeeWitnessToCrimeByLoginId(final Long crimeId, final String employeeLoginId);
	public CrimeRO addEmployeeWitnessesToCrimeByIds(final WitnessWrapper witnessWrapper);
	public CrimeRO addEmployeeWitnessesToCrimeByLoginIds(final WitnessWrapper witnessWrapper);
	
	public CrimeRO removeWitnessFromCrime(final Long crimeId, final Long witnessId);
	public CrimeRO removeWitnessesFromCrime(final WitnessWrapper witnessWrapper);
	public CrimeRO removeEmployeeWitnessFromCrimeById(final Long crimeId, final Long employeeId);
	public CrimeRO removeEmployeeWitnessFromCrimeByLoginId(final Long crimeId, final String employeeLoginId);
	public CrimeRO removeEmployeeWitnessesFromCrimeByIds(final WitnessWrapper witnessWrapper);
	public CrimeRO removeEmployeeWitnessesFromCrimeByLoginIds(final WitnessWrapper witnessWrapper);
	
}
