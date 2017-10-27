package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.AccidentRO;
import com.i2g.rms.rest.model.InjuredPersonRO;
import com.i2g.rms.rest.model.WitnessRO;
import com.i2g.rms.rest.model.wrapper.InjuredPersonWrapper;
import com.i2g.rms.rest.model.wrapper.WitnessWrapper;

/**
 * Rest Service Interface for Accident rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AccidentRestService {

	public List<AccidentRO> get();

	public AccidentRO get(final Long accidentId);
	
	public AccidentRO createAccident(final AccidentRO accidentRO);
	
	public AccidentRO updateAccident(final AccidentRO accidentRO);
	
	public AccidentRO addOrUpdateAccident(final AccidentRO accidentRO);
	
	public AccidentRO addInjuredPersonToAccident(final Long accidentId, final InjuredPersonRO injuredPersonRO);
	public AccidentRO addInjuredPersonsToAccident(final InjuredPersonWrapper injuredPersonWrapper);
	public AccidentRO addExistingInjuredPersonToAccident(final Long accidentId, final Long injuredPersonId);
	public AccidentRO addExistingInjuredPersonsToAccident(final InjuredPersonWrapper injuredPersonWrapper);
	public AccidentRO addEmployeeInjuredPersonToAccidentById(final Long accidentId, final Long employeeId);
	public AccidentRO addEmployeeInjuredPersonToAccidentByLoginId(final Long accidentId, final String employeeLoginId);
	public AccidentRO addEmployeeInjuredPersonsToAccidentByIds(final InjuredPersonWrapper injuredPersonWrapper);
	public AccidentRO addEmployeeInjuredPersonsToAccidentByLoginIds(final InjuredPersonWrapper injuredPersonWrapper);
	
	public AccidentRO removeInjuredPersonFromAccident(final Long accidentId, final Long injuredPersonId);
	public AccidentRO removeInjuredPersonsFromAccident(final InjuredPersonWrapper injuredPersonWrapper);
	public AccidentRO removeEmployeeInjuredPersonFromAccidentById(final Long accidentId, final Long employeeId);
	public AccidentRO removeEmployeeInjuredPersonFromAccidentByLoginId(final Long accidentId, final String employeeLoginId);
	public AccidentRO removeEmployeeInjuredPersonsFromAccidentByIds(final InjuredPersonWrapper injuredPersonWrapper);
	public AccidentRO removeEmployeeInjuredPersonsFromAccidentByLoginIds(final InjuredPersonWrapper injuredPersonWrapper);
	
	public AccidentRO addWitnessToAccident(final Long accidentId, final WitnessRO witnessRO);
	public AccidentRO addWitnessesToAccident(final WitnessWrapper witnessWrapper);
	public AccidentRO addExistingWitnessToAccident(final Long accidentId, final Long witnessId);
	public AccidentRO addExistingWitnessesToAccident(final WitnessWrapper witnessWrapper);
	public AccidentRO addEmployeeWitnessToAccidentById(final Long accidentId, final Long employeeId);
	public AccidentRO addEmployeeWitnessToAccidentByLoginId(final Long accidentId, final String employeeLoginId);
	public AccidentRO addEmployeeWitnessesToAccidentByIds(final WitnessWrapper witnessWrapper);
	public AccidentRO addEmployeeWitnessesToAccidentByLoginIds(final WitnessWrapper witnessWrapper);
	
	public AccidentRO removeWitnessFromAccident(final Long accidentId, final Long witnessId);
	public AccidentRO removeWitnessesFromAccident(final WitnessWrapper witnessWrapper);
	public AccidentRO removeEmployeeWitnessFromAccidentById(final Long accidentId, final Long employeeId);
	public AccidentRO removeEmployeeWitnessFromAccidentByLoginId(final Long accidentId, final String employeeLoginId);
	public AccidentRO removeEmployeeWitnessesFromAccidentByIds(final WitnessWrapper witnessWrapper);
	public AccidentRO removeEmployeeWitnessesFromAccidentByLoginIds(final WitnessWrapper witnessWrapper);
	
}
