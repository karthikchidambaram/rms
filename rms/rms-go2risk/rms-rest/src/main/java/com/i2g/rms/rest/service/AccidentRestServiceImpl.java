package com.i2g.rms.rest.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.Accident;
import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.domain.model.YesNoType;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.model.AccidentRO;
import com.i2g.rms.rest.model.InjuredPersonRO;
import com.i2g.rms.rest.model.WitnessRO;
import com.i2g.rms.rest.model.wrapper.InjuredPersonWrapper;
import com.i2g.rms.rest.model.wrapper.WitnessWrapper;
import com.i2g.rms.service.AccidentService;
import com.i2g.rms.service.InjuredPersonService;
import com.i2g.rms.service.UserService;
import com.i2g.rms.service.WitnessService;
import com.i2g.rms.service.exception.ResourceConflictException;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.incident.IncidentService;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;

/**
 * Rest services for accident rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AccidentRestServiceImpl extends AbstractRestService implements AccidentRestService {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(AccidentRestServiceImpl.class);

	@Autowired
	private IncidentService _incidentService;
	@Autowired
	private AccidentService _accidentService;
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;
	@Autowired
	private InjuredPersonRestService _injuredPersonRestService;
	@Autowired
	private InjuredPersonService _injuredPersonService;
	@Autowired
	private WitnessRestService _witnessRestService;
	@Autowired
	private WitnessService _witnessService;
	@Autowired
	private UserService _userService;
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<AccidentRO> get() {
		List<Accident> accidents = _accidentService.get();
		List<AccidentRO> accidentROs = accidents.isEmpty() ? Collections.emptyList() : _mapperService.map(accidents, AccidentRO.class);
		return accidentROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public AccidentRO get(final Long accidentId) {
		if (accidentId == null || accidentId <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final Accident accident = _accidentService.get(accidentId);
		if (accident != null) {
			return _mapperService.map(accident, AccidentRO.class);
		} else {
			return null;
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO createAccident(final AccidentRO accidentRO) {
		validateObject(accidentRO);
		final Incident incident = getIncident(accidentRO); 
		validateGenericObject(incident);
		
		//Validate if the incident already has an accident record.
		final Accident existingAccidentRecord = _accidentService.get(incident);
		if (existingAccidentRecord != null) {
			throw new ResourceConflictException(_messageBuilder.build(RestMessage.INCIDENT_ALREADY_HAS_EXISTING_ACCIDENT_RECORD));
		}
		
		final Accident accident = constructNewAccident(accidentRO, incident);
		if (accident != null) {
			final Accident newAccident = _accidentService.createAccident(accident);
			if (newAccident != null)  {
				return _mapperService.map(newAccident, AccidentRO.class);
			} else {
				throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
			}
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CONSTRUCT_OBJECT_FOR_RECORD_CREATION));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO updateAccident(final AccidentRO accidentRO) {
		validateObject(accidentRO);
		final Accident accident = constructAccident(accidentRO);
		if (accident != null) {
			final Accident updatedAccident = _accidentService.updateAccident(accident);
			if (updatedAccident != null)  {
				return _mapperService.map(updatedAccident, AccidentRO.class);
			} else {
				throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
			}
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.RECORD_FETCH_FAILED_FOR_UPDATE));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addOrUpdateAccident(final AccidentRO accidentRO) {
		validateObject(accidentRO);
		if (accidentRO.getId() > 0) {
			return updateAccident(accidentRO);
		} else {
			return createAccident(accidentRO);
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addInjuredPersonToAccident(final Long accidentId, final InjuredPersonRO injuredPersonRO) {
		validateKeyId(accidentId);
		validateObject(injuredPersonRO);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final InjuredPerson injuredPerson = _injuredPersonRestService.constructNewInjuredPerson(injuredPersonRO);
		validateGenericObject(injuredPerson);
		final InjuredPerson newInjuredPerson = _injuredPersonService.createInjuredPerson(injuredPerson);
		if (newInjuredPerson != null) {
			final Set<InjuredPerson> injuredPersons = new HashSet<InjuredPerson>(0);
			injuredPersons.add(newInjuredPerson);
			accident.getInjuredPersons().addAll(injuredPersons);
			final Accident updatedAccident = _accidentService.updateAccident(accident);
			if (updatedAccident != null) {
				return _mapperService.map(updatedAccident, AccidentRO.class);
			} else {
				throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
			}
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addInjuredPersonsToAccident(final InjuredPersonWrapper injuredPersonWrapper) {
		validateObject(injuredPersonWrapper);
		validateKeyId(injuredPersonWrapper.getAccidentId());
		validateCollectionObject(injuredPersonWrapper.getInjuredPersons());
		final Accident accident = _accidentService.get(injuredPersonWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<InjuredPerson> injuredPersons = new HashSet<InjuredPerson>(0);
		for (InjuredPersonRO injuredPersonRO : injuredPersonWrapper.getInjuredPersons()) {
			if (injuredPersonRO != null) {
				final InjuredPerson injuredPerson = _injuredPersonRestService.constructNewInjuredPerson(injuredPersonRO);
				if (injuredPerson != null) {
					injuredPersons.add(injuredPerson);
				}
			}
		}
		validateCollectionObject(injuredPersons);
		final Set<InjuredPerson> newInjuredPersons = _injuredPersonService.createInjuredPersons(injuredPersons);
		validateCollectionObject(newInjuredPersons);
		accident.getInjuredPersons().addAll(newInjuredPersons);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addExistingInjuredPersonToAccident(final Long accidentId, final Long injuredPersonId) {
		validateKeyId(accidentId);
		validateKeyId(injuredPersonId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final InjuredPerson injuredPerson = _injuredPersonService.get(injuredPersonId);
		validateGenericObject(injuredPerson);
		final Set<InjuredPerson> injuredPersons = new HashSet<InjuredPerson>(0);
		injuredPersons.add(injuredPerson);
		accident.getInjuredPersons().addAll(injuredPersons);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addExistingInjuredPersonsToAccident(final InjuredPersonWrapper injuredPersonWrapper) {
		validateObject(injuredPersonWrapper);
		validateKeyId(injuredPersonWrapper.getAccidentId());
		validateCollectionObject(injuredPersonWrapper.getInjuredPersons());
		final Accident accident = _accidentService.get(injuredPersonWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<InjuredPerson> injuredPersons = new HashSet<InjuredPerson>(0);
		for (InjuredPersonRO injuredPersonRO : injuredPersonWrapper.getInjuredPersons()) {
			if (injuredPersonRO != null) {
				if (injuredPersonRO.getId() > 0) {
					final InjuredPerson injuredPerson = _injuredPersonService.get(injuredPersonRO.getId());
					if (injuredPerson != null) {
						injuredPersons.add(injuredPerson);
					}
				}
			}			
		}
		validateCollectionObject(injuredPersons);
		accident.getInjuredPersons().addAll(injuredPersons);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addEmployeeInjuredPersonToAccidentById(final Long accidentId, final Long employeeId) {
		validateKeyId(accidentId);
		validateKeyId(employeeId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final User employeeInjuredPerson = _userService.get(employeeId);
		validateGenericObject(employeeInjuredPerson);
		final Set<User> employeeInjuredPersons = new HashSet<User>(0);
		employeeInjuredPersons.add(employeeInjuredPerson);
		accident.getEmployeeInjuredPersons().addAll(employeeInjuredPersons);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addEmployeeInjuredPersonToAccidentByLoginId(final Long accidentId, final String employeeLoginId) {
		validateKeyId(accidentId);
		validateUsername(employeeLoginId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final User employeeInjuredPerson = _userService.getUserByUserLoginId(employeeLoginId);
		validateGenericObject(employeeInjuredPerson);
		final Set<User> employeeInjuredPersons = new HashSet<User>(0);
		employeeInjuredPersons.add(employeeInjuredPerson);
		accident.getEmployeeInjuredPersons().addAll(employeeInjuredPersons);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addEmployeeInjuredPersonsToAccidentByIds(final InjuredPersonWrapper injuredPersonWrapper) {
		validateObject(injuredPersonWrapper);
		validateKeyId(injuredPersonWrapper.getAccidentId());
		final Long[] employeeIds = injuredPersonWrapper.getEmployeeIds();
		if (employeeIds == null || employeeIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.OBJECT_NULL_OR_EMPTY));
		}
		final Accident accident = _accidentService.get(injuredPersonWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<User> employeeInjuredPersons = new HashSet<User>(0);
		for (int i = 0; i < employeeIds.length; i++) {
			final Long employeeId = employeeIds[i];
			if (employeeId != null && employeeId > 0) {
				final User employeeInjuredPerson = _userService.get(employeeId);
				if (employeeInjuredPerson != null) {
					employeeInjuredPersons.add(employeeInjuredPerson);
				}
			}
		}
		validateCollectionObject(employeeInjuredPersons);
		accident.getEmployeeInjuredPersons().addAll(employeeInjuredPersons);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addEmployeeInjuredPersonsToAccidentByLoginIds(final InjuredPersonWrapper injuredPersonWrapper) {
		validateObject(injuredPersonWrapper);
		validateKeyId(injuredPersonWrapper.getAccidentId());
		final String[] employeeLoginIds = injuredPersonWrapper.getEmployeeLoginIds();
		if (employeeLoginIds == null || employeeLoginIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.OBJECT_NULL_OR_EMPTY));
		}
		final Accident accident = _accidentService.get(injuredPersonWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<User> employeeInjuredPersons = new HashSet<User>(0);
		for (int i = 0; i < employeeLoginIds.length; i++) {
			final String employeeLoginId = employeeLoginIds[i];
			if (employeeLoginId != null) {
				final User employeeInjuredPerson = _userService.getUserByUserLoginId(employeeLoginId);
				if (employeeInjuredPerson != null) {
					employeeInjuredPersons.add(employeeInjuredPerson);
				}
			}
		}
		validateCollectionObject(employeeInjuredPersons);
		accident.getEmployeeInjuredPersons().addAll(employeeInjuredPersons);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO removeInjuredPersonFromAccident(final Long accidentId, final Long injuredPersonId) {
		validateKeyId(accidentId);
		validateKeyId(injuredPersonId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final InjuredPerson injuredPerson = _injuredPersonService.get(injuredPersonId);
		validateGenericObject(injuredPerson);
		accident.getInjuredPersons().remove(injuredPerson);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO removeInjuredPersonsFromAccident(final InjuredPersonWrapper injuredPersonWrapper) {
		validateObject(injuredPersonWrapper);
		validateKeyId(injuredPersonWrapper.getAccidentId());
		validateCollectionObject(injuredPersonWrapper.getInjuredPersons());
		final Accident accident = _accidentService.get(injuredPersonWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<InjuredPerson> injuredPersons = new HashSet<InjuredPerson>(0);
		for (InjuredPersonRO injuredPersonRO : injuredPersonWrapper.getInjuredPersons()) {
			if (injuredPersonRO != null) {
				if (injuredPersonRO.getId() > 0) {
					final InjuredPerson injuredPerson = _injuredPersonService.get(injuredPersonRO.getId());
					if (injuredPerson != null) {
						injuredPersons.add(injuredPerson);
					}
				}
			}			
		}
		validateCollectionObject(injuredPersons);
		accident.getInjuredPersons().removeAll(injuredPersons);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO removeEmployeeInjuredPersonFromAccidentById(final Long accidentId, final Long employeeId) {
		validateKeyId(accidentId);
		validateKeyId(employeeId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final User employeeInjuredPerson = _userService.get(employeeId);
		validateGenericObject(employeeInjuredPerson);
		accident.getEmployeeInjuredPersons().remove(employeeInjuredPerson);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO removeEmployeeInjuredPersonFromAccidentByLoginId(final Long accidentId, final String employeeLoginId) {
		validateKeyId(accidentId);
		validateUsername(employeeLoginId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final User employeeInjuredPerson = _userService.getUserByUserLoginId(employeeLoginId);
		validateGenericObject(employeeInjuredPerson);
		accident.getEmployeeInjuredPersons().remove(employeeInjuredPerson);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO removeEmployeeInjuredPersonsFromAccidentByIds(final InjuredPersonWrapper injuredPersonWrapper) {
		validateObject(injuredPersonWrapper);
		validateKeyId(injuredPersonWrapper.getAccidentId());
		final Long[] employeeIds = injuredPersonWrapper.getEmployeeIds();
		if (employeeIds == null || employeeIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.NOTHING_TO_DELETE));
		}
		final Accident accident = _accidentService.get(injuredPersonWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<User> employeeInjuredPersons = new HashSet<User>(0);
		for (int i = 0; i < employeeIds.length; i++) {
			final Long employeeId = employeeIds[i];
			if (employeeId != null && employeeId > 0) {
				final User employeeInjuredPerson = _userService.get(employeeId);
				if (employeeInjuredPerson != null) {
					employeeInjuredPersons.add(employeeInjuredPerson);
				}
			}
		}
		validateCollectionObject(employeeInjuredPersons);
		accident.getEmployeeInjuredPersons().removeAll(employeeInjuredPersons);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO removeEmployeeInjuredPersonsFromAccidentByLoginIds(final InjuredPersonWrapper injuredPersonWrapper) {
		validateObject(injuredPersonWrapper);
		validateKeyId(injuredPersonWrapper.getAccidentId());
		final String[] employeeLoginIds = injuredPersonWrapper.getEmployeeLoginIds();
		if (employeeLoginIds == null || employeeLoginIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.NOTHING_TO_DELETE));
		}
		final Accident accident = _accidentService.get(injuredPersonWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<User> employeeInjuredPersons = new HashSet<User>(0);
		for (int i = 0; i < employeeLoginIds.length; i++) {
			final String employeeLoginId = employeeLoginIds[i];
			if (employeeLoginId != null) {
				final User employeeInjuredPerson = _userService.getUserByUserLoginId(employeeLoginId);
				if (employeeInjuredPerson != null) {
					employeeInjuredPersons.add(employeeInjuredPerson);
				}
			}
		}
		validateCollectionObject(employeeInjuredPersons);
		accident.getEmployeeInjuredPersons().removeAll(employeeInjuredPersons);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addWitnessToAccident(final Long accidentId, final WitnessRO witnessRO) {
		validateKeyId(accidentId);
		validateObject(witnessRO);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final Witness witness = _witnessRestService.constructNewWitness(witnessRO);
		validateGenericObject(witness);
		final Witness newWitness = _witnessService.createNewWitness(witness);
		if (newWitness != null) {
			final Set<Witness> witnesses = new HashSet<Witness>(0);
			witnesses.add(newWitness);
			accident.getWitnesses().addAll(witnesses);
			final Accident updatedAccident = _accidentService.updateAccident(accident);
			if (updatedAccident != null) {
				return _mapperService.map(updatedAccident, AccidentRO.class);
			} else {
				throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
			}
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addWitnessesToAccident(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getAccidentId());
		validateCollectionObject(witnessWrapper.getWitnesses());
		final Accident accident = _accidentService.get(witnessWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<Witness> witnesses = new HashSet<Witness>(0);
		for (WitnessRO witnessRO : witnessWrapper.getWitnesses()) {
			if (witnessRO != null) {
				final Witness witness = _witnessRestService.constructNewWitness(witnessRO);
				if (witness != null) {
					witnesses.add(witness);
				}
			}
		}
		validateCollectionObject(witnesses);
		final Set<Witness> newWitnesses = _witnessService.createNewWitnesses(witnesses);
		validateCollectionObject(newWitnesses);
		accident.getWitnesses().addAll(newWitnesses);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addExistingWitnessToAccident(final Long accidentId, final Long witnessId) {
		validateKeyId(accidentId);
		validateKeyId(witnessId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final Witness witness = _witnessService.get(witnessId);
		validateGenericObject(witness);
		final Set<Witness> witnesses = new HashSet<Witness>(0);
		witnesses.add(witness);
		accident.getWitnesses().addAll(witnesses);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addExistingWitnessesToAccident(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getAccidentId());
		validateCollectionObject(witnessWrapper.getWitnesses());
		final Accident accident = _accidentService.get(witnessWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<Witness> witnesses = new HashSet<Witness>(0);
		for (WitnessRO witnessRO : witnessWrapper.getWitnesses()) {
			if (witnessRO != null) {
				if (witnessRO.getId() > 0) {
					final Witness witness = _witnessService.get(witnessRO.getId());
					if (witness != null) {
						witnesses.add(witness);
					}
				}
			}			
		}
		validateCollectionObject(witnesses);
		accident.getWitnesses().addAll(witnesses);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addEmployeeWitnessToAccidentById(final Long accidentId, final Long employeeId) {
		validateKeyId(accidentId);
		validateKeyId(employeeId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final User employeeWitness = _userService.get(employeeId);
		validateGenericObject(employeeWitness);
		final Set<User> employeeWitnesses = new HashSet<User>(0);
		employeeWitnesses.add(employeeWitness);
		accident.getEmployeeWitnesses().addAll(employeeWitnesses);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addEmployeeWitnessToAccidentByLoginId(final Long accidentId, final String employeeLoginId) {
		validateKeyId(accidentId);
		validateUsername(employeeLoginId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final User employeeWitness = _userService.getUserByUserLoginId(employeeLoginId);
		validateGenericObject(employeeWitness);
		final Set<User> employeeWitnesses = new HashSet<User>(0);
		employeeWitnesses.add(employeeWitness);
		accident.getEmployeeWitnesses().addAll(employeeWitnesses);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addEmployeeWitnessesToAccidentByIds(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getAccidentId());
		final Long[] employeeIds = witnessWrapper.getEmployeeIds();
		if (employeeIds == null || employeeIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.OBJECT_NULL_OR_EMPTY));
		}
		final Accident accident = _accidentService.get(witnessWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<User> employeeWitnesses = new HashSet<User>(0);
		for (int i = 0; i < employeeIds.length; i++) {
			final Long employeeId = employeeIds[i];
			if (employeeId != null && employeeId > 0) {
				final User employeeWitness = _userService.get(employeeId);
				if (employeeWitness != null) {
					employeeWitnesses.add(employeeWitness);
				}
			}
		}
		validateCollectionObject(employeeWitnesses);
		accident.getEmployeeWitnesses().addAll(employeeWitnesses);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO addEmployeeWitnessesToAccidentByLoginIds(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getAccidentId());
		final String[] employeeLoginIds = witnessWrapper.getEmployeeLoginIds();
		if (employeeLoginIds == null || employeeLoginIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.OBJECT_NULL_OR_EMPTY));
		}
		final Accident accident = _accidentService.get(witnessWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<User> employeeWitnesses = new HashSet<User>(0);
		for (int i = 0; i < employeeLoginIds.length; i++) {
			final String employeeLoginId = employeeLoginIds[i];
			if (employeeLoginId != null) {
				final User employeeWitness = _userService.getUserByUserLoginId(employeeLoginId);
				if (employeeWitness != null) {
					employeeWitnesses.add(employeeWitness);
				}
			}
		}
		validateCollectionObject(employeeWitnesses);
		accident.getEmployeeWitnesses().addAll(employeeWitnesses);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO removeWitnessFromAccident(final Long accidentId, final Long witnessId) {
		validateKeyId(accidentId);
		validateKeyId(witnessId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final Witness witness = _witnessService.get(witnessId);
		validateGenericObject(witness);
		accident.getWitnesses().remove(witness);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO removeWitnessesFromAccident(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getAccidentId());
		validateCollectionObject(witnessWrapper.getWitnesses());
		final Accident accident = _accidentService.get(witnessWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<Witness> witnesses = new HashSet<Witness>(0);
		for (WitnessRO witnessRO : witnessWrapper.getWitnesses()) {
			if (witnessRO != null) {
				if (witnessRO.getId() > 0) {
					final Witness witness = _witnessService.get(witnessRO.getId());
					if (witness != null) {
						witnesses.add(witness);
					}
				}
			}			
		}
		validateCollectionObject(witnesses);
		accident.getWitnesses().removeAll(witnesses);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO removeEmployeeWitnessFromAccidentById(final Long accidentId, final Long employeeId) {
		validateKeyId(accidentId);
		validateKeyId(employeeId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final User employeeWitness = _userService.get(employeeId);
		validateGenericObject(employeeWitness);
		accident.getEmployeeWitnesses().remove(employeeWitness);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO removeEmployeeWitnessFromAccidentByLoginId(final Long accidentId, final String employeeLoginId) {
		validateKeyId(accidentId);
		validateUsername(employeeLoginId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final User employeeWitness = _userService.getUserByUserLoginId(employeeLoginId);
		validateGenericObject(employeeWitness);
		accident.getEmployeeWitnesses().remove(employeeWitness);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO removeEmployeeWitnessesFromAccidentByIds(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getAccidentId());
		final Long[] employeeIds = witnessWrapper.getEmployeeIds();
		if (employeeIds == null || employeeIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.NOTHING_TO_DELETE));
		}
		final Accident accident = _accidentService.get(witnessWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<User> employeeWitnesses = new HashSet<User>(0);
		for (int i = 0; i < employeeIds.length; i++) {
			final Long employeeId = employeeIds[i];
			if (employeeId != null && employeeId > 0) {
				final User employeeWitness = _userService.get(employeeId);
				if (employeeWitness != null) {
					employeeWitnesses.add(employeeWitness);
				}
			}
		}
		validateCollectionObject(employeeWitnesses);
		accident.getEmployeeWitnesses().removeAll(employeeWitnesses);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AccidentRO removeEmployeeWitnessesFromAccidentByLoginIds(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getAccidentId());
		final String[] employeeLoginIds = witnessWrapper.getEmployeeLoginIds();
		if (employeeLoginIds == null || employeeLoginIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.NOTHING_TO_DELETE));
		}
		final Accident accident = _accidentService.get(witnessWrapper.getAccidentId());
		validateGenericObject(accident);
		final Set<User> employeeWitnesses = new HashSet<User>(0);
		for (int i = 0; i < employeeLoginIds.length; i++) {
			final String employeeLoginId = employeeLoginIds[i];
			if (employeeLoginId != null) {
				final User employeeWitness = _userService.getUserByUserLoginId(employeeLoginId);
				if (employeeWitness != null) {
					employeeWitnesses.add(employeeWitness);
				}
			}
		}
		validateCollectionObject(employeeWitnesses);
		accident.getEmployeeWitnesses().removeAll(employeeWitnesses);
		final Accident updatedAccident = _accidentService.updateAccident(accident);
		validateGenericObject(updatedAccident);
		return _mapperService.map(updatedAccident, AccidentRO.class);
	}
	
	private Incident getIncident(final AccidentRO accidentRO) {
		Incident incident = null;
		if (accidentRO != null) {
			if (accidentRO.getIncident() != null) {
				if (accidentRO.getIncident().getId() > 0) {
					incident = _incidentService.get(accidentRO.getIncident().getId());
				} else if (accidentRO.getIncident().getUniqueIncidentId() != null && !accidentRO.getIncident().getUniqueIncidentId().trim().isEmpty()) {
					incident = _incidentService.getIncidentByUniqueIncidentId(accidentRO.getIncident().getUniqueIncidentId().trim());
				}
			}
		}
		return incident;
	}
	
	private Accident constructNewAccident(final AccidentRO accidentRO, final Incident incident) {
		if (accidentRO != null && incident != null) {
			final Accident accident = new Accident.Builder().setIncident(incident).setStatusFlag(StatusFlag.ACTIVE).build();
			return setOtherFieldsForAccident(accident, accidentRO);
		} else {
			return null;
		}
	}
	
	private Accident constructAccident(final AccidentRO accidentRO) {
		if (accidentRO != null && accidentRO.getId() > 0) {
			final Accident accident = _accidentService.get(accidentRO.getId());
			return setOtherFieldsForAccident(accident, accidentRO);
		} else {
			return null;
		}
	}
	
	private Accident setOtherFieldsForAccident(final Accident accident, final AccidentRO accidentRO) {
		if (accident != null && accidentRO != null) {
			//set other fields for accident
			// Accident Description
			if (accidentRO.getAccidentDescription() != null && !accidentRO.getAccidentDescription().trim().isEmpty()) {
				accident.setAccidentDescription(accidentRO.getAccidentDescription().trim());
			}
			// Accident date and time
			if (accidentRO.getAccidentDateTime() != null) {
				accident.setAccidentDateTime(accidentRO.getAccidentDateTime());
			}
			// place of accident
			if (accidentRO.getAccidentPlace() != null && !accidentRO.getAccidentPlace().trim().isEmpty()) {
				accident.setAccidentPlace(accidentRO.getAccidentPlace().trim());
			}
			// Accident type
			if (accidentRO.getAccidentType() != null) {
				if (accidentRO.getAccidentType().getId() != null && !accidentRO.getAccidentType().getId().trim().isEmpty()) {
					accident.setAccidentType(_tableMaintenanceService.getAccidentTypeByCode(accidentRO.getAccidentType().getId().trim()));
				}
			}
			// Set accident location
			if (accidentRO.getAccidentLocation() != null) {
				if (accidentRO.getAccidentLocation().getId() != null && !accidentRO.getAccidentLocation().getId().trim().isEmpty()) {
					accident.setAccidentLocation(_tableMaintenanceService.getAccidentLocationByCode(accidentRO.getAccidentLocation().getId().trim()));
				}
			}
			// set accident location detail
			if (accidentRO.getAccidentLocationDetails() != null) {
				if (accidentRO.getAccidentLocationDetails().getId() != null && !accidentRO.getAccidentLocationDetails().getId().trim().isEmpty()) {
					accident.setAccidentLocationDetails(_tableMaintenanceService.getAccidentLocationDetailByCode(accidentRO.getAccidentLocationDetails().getId().trim()));
				}
			}
			// Set accident location other if any
			if (accidentRO.getAccidentLocationOther() != null && !accidentRO.getAccidentLocationOther().trim().isEmpty()) {
				accident.setAccidentLocationOther(accidentRO.getAccidentLocationOther().trim());
			}
			// Any witness to the accident?
			YesNoType anyWitness = YesNoType.N;
			if (accidentRO.getAnyWitness() != null && accidentRO.getAnyWitness().name().equals("Y")) {
				anyWitness = YesNoType.Y;
			}
			accident.setAnyWitness(anyWitness);
			return accident;
		} else {
			return null;
		}
	}	
}
