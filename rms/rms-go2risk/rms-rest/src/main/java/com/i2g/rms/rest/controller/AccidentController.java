package com.i2g.rms.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.Accident;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.AccidentRO;
import com.i2g.rms.rest.model.InjuredPersonRO;
import com.i2g.rms.rest.model.WitnessRO;
import com.i2g.rms.rest.model.wrapper.InjuredPersonWrapper;
import com.i2g.rms.rest.model.wrapper.WitnessWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.AccidentRestService;

/**
 * Rest controller for accident read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class AccidentController extends AbstractRestController {
	
	@Autowired
	private AccidentRestService _accidentRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ALL_ACCIDENTS, method = RequestMethod.GET)
	@Searchable(sourceType = AccidentRO.class, value = Accident.class)
	public List<AccidentRO> get() {
		return _accidentRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ACCIDENT_BY_ACCIDENT_ID, method = RequestMethod.GET)
	public AccidentRO get(@PathVariable final Long accidentId) {
		return _accidentRestService.get(accidentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_ACCIDENT, method = RequestMethod.POST)
	public AccidentRO createAccident(@Valid @RequestBody final AccidentRO accidentRO) {
		return _accidentRestService.createAccident(accidentRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_ACCIDENT, method = RequestMethod.PUT)
	public AccidentRO updateAccident(@Valid @RequestBody final AccidentRO accidentRO) {
		return _accidentRestService.updateAccident(accidentRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_OR_UPDATE_ACCIDENT, method = RequestMethod.POST)
	public AccidentRO addOrUpdateAccident(@Valid @RequestBody final AccidentRO accidentRO) {
		return _accidentRestService.addOrUpdateAccident(accidentRO);
	}
	
	//INJURED PERSON
	@RequestMapping(value = RequestMappingConstants.ADD_INJURED_PERSON_TO_ACCIDENT, method = RequestMethod.PUT)
	public AccidentRO addInjuredPersonToAccident(@PathVariable final Long accidentId, @Valid @RequestBody final InjuredPersonRO injuredPersonRO) {
		return _accidentRestService.addInjuredPersonToAccident(accidentId, injuredPersonRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_INJURED_PERSONS_TO_ACCIDENT, method = RequestMethod.PUT)
	public AccidentRO addInjuredPersonsToAccident(@Valid @RequestBody final InjuredPersonWrapper injuredPersonWrapper) {
		return _accidentRestService.addInjuredPersonsToAccident(injuredPersonWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_INJURED_PERSON_TO_ACCIDENT, method = RequestMethod.PUT)
	public AccidentRO addExistingInjuredPersonToAccident(@PathVariable final Long accidentId, @PathVariable final Long injuredPersonId) {
		return _accidentRestService.addExistingInjuredPersonToAccident(accidentId, injuredPersonId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_INJURED_PERSONS_TO_ACCIDENT, method = RequestMethod.PUT)
	public AccidentRO addExistingInjuredPersonsToAccident(@Valid @RequestBody final InjuredPersonWrapper injuredPersonWrapper) {
		return _accidentRestService.addExistingInjuredPersonsToAccident(injuredPersonWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_INJURED_PERSON_TO_ACCIDENT_BY_ID, method = RequestMethod.PUT)
	public AccidentRO addEmployeeInjuredPersonToAccidentById(@PathVariable final Long accidentId, @PathVariable final Long employeeId) {
		return _accidentRestService.addEmployeeInjuredPersonToAccidentById(accidentId, employeeId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_INJURED_PERSON_TO_ACCIDENT_BY_LOGIN_ID, method = RequestMethod.PUT)
	public AccidentRO addEmployeeInjuredPersonToAccidentByLoginId(@PathVariable final Long accidentId, @PathVariable final String employeeLoginId) {
		return _accidentRestService.addEmployeeInjuredPersonToAccidentByLoginId(accidentId, employeeLoginId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_INJURED_PERSONS_TO_ACCIDENT_BY_IDS, method = RequestMethod.PUT)
	public AccidentRO addEmployeeInjuredPersonsToAccidentByIds(@Valid @RequestBody final InjuredPersonWrapper injuredPersonWrapper) {
		return _accidentRestService.addEmployeeInjuredPersonsToAccidentByIds(injuredPersonWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_INJURED_PERSONS_TO_ACCIDENT_BY_LOGIN_IDS, method = RequestMethod.PUT)
	public AccidentRO addEmployeeInjuredPersonsToAccidentByLoginIds(@Valid @RequestBody final InjuredPersonWrapper injuredPersonWrapper) {
		return _accidentRestService.addEmployeeInjuredPersonsToAccidentByLoginIds(injuredPersonWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_INJURED_PERSON_FROM_ACCIDENT, method = RequestMethod.DELETE)
	public AccidentRO removeInjuredPersonFromAccident(@PathVariable final Long accidentId, @PathVariable final Long injuredPersonId) {
		return _accidentRestService.removeInjuredPersonFromAccident(accidentId, injuredPersonId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_INJURED_PERSONS_FROM_ACCIDENT, method = RequestMethod.DELETE)
	public AccidentRO removeInjuredPersonsFromAccident(@Valid @RequestBody final InjuredPersonWrapper injuredPersonWrapper) {
		return _accidentRestService.removeInjuredPersonsFromAccident(injuredPersonWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_INJURED_PERSON_FROM_ACCIDENT_BY_ID, method = RequestMethod.DELETE)
	public AccidentRO removeEmployeeInjuredPersonFromAccidentById(@PathVariable final Long accidentId, @PathVariable final Long employeeId) {
		return _accidentRestService.removeEmployeeInjuredPersonFromAccidentById(accidentId, employeeId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_INJURED_PERSON_FROM_ACCIDENT_BY_LOGIN_ID, method = RequestMethod.DELETE)
	public AccidentRO removeEmployeeInjuredPersonFromAccidentByLoginId(@PathVariable final Long accidentId, @PathVariable final String employeeLoginId) {
		return _accidentRestService.removeEmployeeInjuredPersonFromAccidentByLoginId(accidentId, employeeLoginId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_INJURED_PERSONS_FROM_ACCIDENT_BY_IDS, method = RequestMethod.DELETE)
	public AccidentRO removeEmployeeInjuredPersonsFromAccidentByIds(@Valid @RequestBody final InjuredPersonWrapper injuredPersonWrapper) {
		return _accidentRestService.removeEmployeeInjuredPersonsFromAccidentByIds(injuredPersonWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_INJURED_PERSONS_FROM_ACCIDENT_BY_LOGIN_IDS, method = RequestMethod.DELETE)
	public AccidentRO removeEmployeeInjuredPersonsFromAccidentByLoginIds(@Valid @RequestBody final InjuredPersonWrapper injuredPersonWrapper) {
		return _accidentRestService.removeEmployeeInjuredPersonsFromAccidentByLoginIds(injuredPersonWrapper);
	}
	
	//WITNESS
	@RequestMapping(value = RequestMappingConstants.ADD_WITNESS_TO_ACCIDENT, method = RequestMethod.PUT)
	public AccidentRO addWitnessToAccident(@PathVariable final Long accidentId, @Valid @RequestBody final WitnessRO witnessRO) {
		return _accidentRestService.addWitnessToAccident(accidentId, witnessRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_WITNESSES_TO_ACCIDENT, method = RequestMethod.PUT)
	public AccidentRO addWitnessesToAccident(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _accidentRestService.addWitnessesToAccident(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_WITNESS_TO_ACCIDENT, method = RequestMethod.PUT)
	public AccidentRO addExistingWitnessToAccident(@PathVariable final Long accidentId, @PathVariable final Long witnessId) {
		return _accidentRestService.addExistingWitnessToAccident(accidentId, witnessId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_WITNESSES_TO_ACCIDENT, method = RequestMethod.PUT)
	public AccidentRO addExistingWitnessesToAccident(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _accidentRestService.addExistingWitnessesToAccident(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_WITNESS_TO_ACCIDENT_BY_ID, method = RequestMethod.PUT)
	public AccidentRO addEmployeeWitnessToAccidentById(@PathVariable final Long accidentId, @PathVariable final Long employeeId) {
		return _accidentRestService.addEmployeeWitnessToAccidentById(accidentId, employeeId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_WITNESS_TO_ACCIDENT_BY_LOGIN_ID, method = RequestMethod.PUT)
	public AccidentRO addEmployeeWitnessToAccidentByLoginId(@PathVariable final Long accidentId, @PathVariable final String employeeLoginId) {
		return _accidentRestService.addEmployeeWitnessToAccidentByLoginId(accidentId, employeeLoginId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_WITNESSES_TO_ACCIDENT_BY_IDS, method = RequestMethod.PUT)
	public AccidentRO addEmployeeWitnessesToAccidentByIds(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _accidentRestService.addEmployeeWitnessesToAccidentByIds(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_WITNESSES_TO_ACCIDENT_BY_LOGIN_IDS, method = RequestMethod.PUT)
	public AccidentRO addEmployeeWitnessesToAccidentByLoginIds(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _accidentRestService.addEmployeeWitnessesToAccidentByLoginIds(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_WITNESS_FROM_ACCIDENT, method = RequestMethod.DELETE)
	public AccidentRO removeWitnessFromAccident(@PathVariable final Long accidentId, @PathVariable final Long witnessId) {
		return _accidentRestService.removeWitnessFromAccident(accidentId, witnessId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_WITNESSES_FROM_ACCIDENT, method = RequestMethod.DELETE)
	public AccidentRO removeWitnessesFromAccident(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _accidentRestService.removeWitnessesFromAccident(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_WITNESS_FROM_ACCIDENT_BY_ID, method = RequestMethod.DELETE)
	public AccidentRO removeEmployeeWitnessFromAccidentById(@PathVariable final Long accidentId, @PathVariable final Long employeeId) {
		return _accidentRestService.removeEmployeeWitnessFromAccidentById(accidentId, employeeId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_WITNESS_FROM_ACCIDENT_BY_LOGIN_ID, method = RequestMethod.DELETE)
	public AccidentRO removeEmployeeWitnessFromAccidentByLoginId(@PathVariable final Long accidentId, @PathVariable final String employeeLoginId) {
		return _accidentRestService.removeEmployeeWitnessFromAccidentByLoginId(accidentId, employeeLoginId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_WITNESSES_FROM_ACCIDENT_BY_IDS, method = RequestMethod.DELETE)
	public AccidentRO removeEmployeeWitnessesFromAccidentByIds(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _accidentRestService.removeEmployeeWitnessesFromAccidentByIds(witnessWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_WITNESSES_FROM_ACCIDENT_BY_LOGIN_IDS, method = RequestMethod.DELETE)
	public AccidentRO removeEmployeeWitnessesFromAccidentByLoginIds(@Valid @RequestBody final WitnessWrapper witnessWrapper) {
		return _accidentRestService.removeEmployeeWitnessesFromAccidentByLoginIds(witnessWrapper);
	}
}
