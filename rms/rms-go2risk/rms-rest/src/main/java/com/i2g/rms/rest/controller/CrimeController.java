package com.i2g.rms.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.Crime;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.CrimeRO;
import com.i2g.rms.rest.model.CrimeSuspectRO;
import com.i2g.rms.rest.model.WitnessRO;
import com.i2g.rms.rest.model.wrapper.CrimeSuspectWrapper;
import com.i2g.rms.rest.model.wrapper.WitnessWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.CrimeRestService;

/**
 * Rest controller for crime read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class CrimeController extends AbstractRestController {
	
	@Autowired
	private CrimeRestService _crimeRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ALL_CRIMES, method = RequestMethod.GET)
	@Searchable(sourceType = CrimeRO.class, value = Crime.class)
	public List<CrimeRO> get() {
		return _crimeRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_CRIME_BY_CRIME_ID, method = RequestMethod.GET)
	public CrimeRO get(@PathVariable final Long crimeId) {
		return _crimeRestService.get(crimeId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_CRIME_BY_INCIDENT_ID, method = RequestMethod.GET)
	public CrimeRO getCrimeByIncidentId(@PathVariable final Long incidentId) {
		return _crimeRestService.getCrimeByIncidentId(incidentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_CRIME_BY_UNIQUE_INCIDENT_ID, method = RequestMethod.GET)
	public CrimeRO getCrimeByUniqueIncidentId(@PathVariable final String uniqueIncidentId) {
		return _crimeRestService.getCrimeByUniqueIncidentId(uniqueIncidentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_CRIME, method = RequestMethod.POST)
	public CrimeRO createCrime(@Valid @RequestBody final CrimeRO crimeRO) {
		return _crimeRestService.createCrime(crimeRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_CRIME, method = RequestMethod.PUT)
	public CrimeRO updateCrime(@Valid @RequestBody final CrimeRO crimeRO) {
		return _crimeRestService.updateCrime(crimeRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_OR_UPDATE_CRIME, method = RequestMethod.POST)
	public CrimeRO addOrUpdateCrime(@Valid @RequestBody final CrimeRO crimeRO) {
		return _crimeRestService.addOrUpdateCrime(crimeRO);
	}
	
	//Add crime suspect to crime
	/** alternate flows */
	@RequestMapping(value = RequestMappingConstants.ADD_CRIME_SUSPECT_FOR_CRIME, method = RequestMethod.PUT)
	public CrimeRO addCrimeSuspectForCrime(@PathVariable final Long crimeId, @Valid @RequestBody final CrimeSuspectRO crimeSuspectRO) {
		return _crimeRestService.addCrimeSuspectForCrime(crimeId, crimeSuspectRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_CRIME_SUSPECTS_FOR_CRIME, method = RequestMethod.PUT)
	public CrimeRO addSuspectsForCrime(@Valid @RequestBody final CrimeSuspectWrapper crimeSuspectWrapper) {
		return _crimeRestService.addCrimeSuspectsForCrime(crimeSuspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_CRIME_SUSPECT_FOR_CRIME, method = RequestMethod.PUT)
	public CrimeRO addExistingSuspectForCrime(@PathVariable final Long crimeId, @PathVariable final Long crimeSuspectId) {
		return _crimeRestService.addExistingCrimeSuspectForCrime(crimeId, crimeSuspectId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_CRIME_SUSPECTS_FOR_CRIME, method = RequestMethod.PUT)
	public CrimeRO addExistingCrimeSuspectsForCrime(@Valid @RequestBody final CrimeSuspectWrapper crimeSuspectWrapper) {
		return _crimeRestService.addExistingCrimeSuspectsForCrime(crimeSuspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_CRIME_SUSPECT_FOR_CRIME_BY_ID, method = RequestMethod.PUT)
	public CrimeRO addEmployeeCrimeSuspectForCrimeById(@PathVariable final Long crimeId, @PathVariable final Long employeeId) {
		return _crimeRestService.addEmployeeCrimeSuspectForCrimeById(crimeId, employeeId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_CRIME_SUSPECT_FOR_CRIME_BY_LOGIN_ID, method = RequestMethod.PUT)
	public CrimeRO addEmployeeCrimeSuspectForCrimeByLoginId(@PathVariable final Long crimeId, @PathVariable final String employeeLoginId) {
		return _crimeRestService.addEmployeeCrimeSuspectForCrimeByLoginId(crimeId, employeeLoginId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_CRIME_SUSPECTS_FOR_CRIME_BY_IDS, method = RequestMethod.PUT)
	public CrimeRO addEmployeeCrimeSuspectsForCrimeByIds(@Valid @RequestBody final CrimeSuspectWrapper crimeSuspectWrapper) {
		return _crimeRestService.addEmployeeCrimeSuspectsForCrimeByIds(crimeSuspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_CRIME_SUSPECTS_FOR_CRIME_BY_LOGIN_IDS, method = RequestMethod.PUT)
	public CrimeRO addEmployeeCrimeSuspectsForCrimeByLoginIds(@Valid @RequestBody final CrimeSuspectWrapper crimeSuspectWrapper) {
		return _crimeRestService.addEmployeeCrimeSuspectsForCrimeByLoginIds(crimeSuspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_CRIME_SUSPECT_FROM_CRIME, method = RequestMethod.DELETE)
	public CrimeRO removeCrimeSuspectFromCrime(@PathVariable final Long crimeId, @PathVariable final Long crimeSuspectId) {
		return _crimeRestService.removeCrimeSuspectFromCrime(crimeId, crimeSuspectId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_CRIME_SUSPECTS_FROM_CRIME, method = RequestMethod.DELETE)
	public CrimeRO removeSuspectsFromCrime(@Valid @RequestBody final CrimeSuspectWrapper crimeSuspectWrapper) {
		return _crimeRestService.removeCrimeSuspectsFromCrime(crimeSuspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_CRIME_SUSPECT_FROM_CRIME_BY_ID, method = RequestMethod.DELETE)
	public CrimeRO removeEmployeeCrimeSuspectFromCrimeById(@PathVariable final Long crimeId, @PathVariable final Long employeeId) {
		return _crimeRestService.removeEmployeeCrimeSuspectFromCrimeById(crimeId, employeeId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_CRIME_SUSPECT_FROM_CRIME_BY_LOGIN_ID, method = RequestMethod.DELETE)
	public CrimeRO removeEmployeeCrimeSuspectFromCrimeByLoginId(@PathVariable final Long crimeId, @PathVariable final String employeeLoginId) {
		return _crimeRestService.removeEmployeeCrimeSuspectFromCrimeByLoginId(crimeId, employeeLoginId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_CRIME_SUSPECTS_FROM_CRIME_BY_IDS, method = RequestMethod.DELETE)
	public CrimeRO removeEmployeeCrimeSuspectsFromCrimeByIds(@Valid @RequestBody final CrimeSuspectWrapper crimeSuspectWrapper) {
		return _crimeRestService.removeEmployeeCrimeSuspectsFromCrimeByIds(crimeSuspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_CRIME_SUSPECTS_FROM_CRIME_BY_LOGIN_IDS, method = RequestMethod.DELETE)
	public CrimeRO removeEmployeeCrimeSuspectsFromCrimeByLoginIds(@Valid @RequestBody final CrimeSuspectWrapper crimeSuspectWrapper) {
		return _crimeRestService.removeEmployeeCrimeSuspectsFromCrimeByLoginIds(crimeSuspectWrapper);
	}
	
	//WITNESS
	@RequestMapping(value = RequestMappingConstants.ADD_WITNESS_TO_CRIME, method = RequestMethod.PUT)
	public CrimeRO addWitnessToCrime(@PathVariable final Long crimeId, @Valid @RequestBody final WitnessRO witnessRO) {
		return _crimeRestService.addWitnessToCrime(crimeId, witnessRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_WITNESSES_TO_CRIME, method = RequestMethod.PUT)
	public CrimeRO addWitnessesToCrime(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _crimeRestService.addWitnessesToCrime(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_WITNESS_TO_CRIME, method = RequestMethod.PUT)
	public CrimeRO addExistingWitnessToCrime(@PathVariable final Long crimeId, @PathVariable final Long witnessId) {
		return _crimeRestService.addExistingWitnessToCrime(crimeId, witnessId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_WITNESSES_TO_CRIME, method = RequestMethod.PUT)
	public CrimeRO addExistingWitnessesToCrime(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _crimeRestService.addExistingWitnessesToCrime(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_WITNESS_TO_CRIME_BY_ID, method = RequestMethod.PUT)
	public CrimeRO addEmployeeWitnessToCrimeById(@PathVariable final Long crimeId, @PathVariable final Long employeeId) {
		return _crimeRestService.addEmployeeWitnessToCrimeById(crimeId, employeeId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_WITNESS_TO_CRIME_BY_LOGIN_ID, method = RequestMethod.PUT)
	public CrimeRO addEmployeeWitnessToCrimeByLoginId(@PathVariable final Long crimeId, @PathVariable final String employeeLoginId) {
		return _crimeRestService.addEmployeeWitnessToCrimeByLoginId(crimeId, employeeLoginId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_WITNESSES_TO_CRIME_BY_IDS, method = RequestMethod.PUT)
	public CrimeRO addEmployeeWitnessesToCrimeByIds(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _crimeRestService.addEmployeeWitnessesToCrimeByIds(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_WITNESSES_TO_CRIME_BY_LOGIN_IDS, method = RequestMethod.PUT)
	public CrimeRO addEmployeeWitnessesToCrimeByLoginIds(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _crimeRestService.addEmployeeWitnessesToCrimeByLoginIds(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_WITNESS_FROM_CRIME, method = RequestMethod.DELETE)
	public CrimeRO removeWitnessFromCrime(@PathVariable final Long crimeId, @PathVariable final Long witnessId) {
		return _crimeRestService.removeWitnessFromCrime(crimeId, witnessId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_WITNESSES_FROM_CRIME, method = RequestMethod.DELETE)
	public CrimeRO removeWitnessesFromCrime(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _crimeRestService.removeWitnessesFromCrime(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_WITNESS_FROM_CRIME_BY_ID, method = RequestMethod.DELETE)
	public CrimeRO removeEmployeeWitnessFromCrimeById(@PathVariable final Long crimeId, @PathVariable final Long employeeId) {
		return _crimeRestService.removeEmployeeWitnessFromCrimeById(crimeId, employeeId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_WITNESS_FROM_CRIME_BY_LOGIN_ID, method = RequestMethod.DELETE)
	public CrimeRO removeEmployeeWitnessFromCrimeByLoginId(@PathVariable final Long crimeId, @PathVariable final String employeeLoginId) {
		return _crimeRestService.removeEmployeeWitnessFromCrimeByLoginId(crimeId, employeeLoginId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_WITNESSES_FROM_CRIME_BY_IDS, method = RequestMethod.DELETE)
	public CrimeRO removeEmployeeWitnessesFromCrimeByIds(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _crimeRestService.removeEmployeeWitnessesFromCrimeByIds(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_WITNESSES_FROM_CRIME_BY_LOGIN_IDS, method = RequestMethod.DELETE)
	public CrimeRO removeEmployeeWitnessesFromCrimeByLoginIds(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _crimeRestService.removeEmployeeWitnessesFromCrimeByLoginIds(witnessWrapper);
	}
}
