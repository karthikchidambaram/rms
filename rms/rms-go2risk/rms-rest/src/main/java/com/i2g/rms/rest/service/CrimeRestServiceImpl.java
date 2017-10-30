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

import com.i2g.rms.domain.model.Crime;
import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.domain.model.YesNoType;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.model.CrimeRO;
import com.i2g.rms.rest.model.CrimeSuspectRO;
import com.i2g.rms.rest.model.WitnessRO;
import com.i2g.rms.rest.model.wrapper.CrimeSuspectWrapper;
import com.i2g.rms.rest.model.wrapper.WitnessWrapper;
import com.i2g.rms.service.CrimeService;
import com.i2g.rms.service.CrimeSuspectService;
import com.i2g.rms.service.UserService;
import com.i2g.rms.service.WitnessService;
import com.i2g.rms.service.exception.ResourceConflictException;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotFoundException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.incident.IncidentService;

/**
 * Rest services for Crime rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class CrimeRestServiceImpl extends AbstractRestService implements CrimeRestService {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(CrimeRestServiceImpl.class);
	
	@Autowired
	private IncidentService _incidentService;
	@Autowired
	private CrimeService _crimeService;
	@Autowired
	private UserService _userService;
	@Autowired
	private CrimeSuspectRestService _crimeSuspectRestService;
	@Autowired
	private CrimeSuspectService _crimeSuspectService;
	@Autowired
	private WitnessRestService _witnessRestService;
	@Autowired
	private WitnessService _witnessService;
		
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<CrimeRO> get() {
		List<Crime> crimes = _crimeService.get();
		List<CrimeRO> crimeROs = crimes.isEmpty() ? Collections.emptyList() : _mapperService.map(crimes, CrimeRO.class);
		return crimeROs;
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public CrimeRO get(final Long crimeId) {
		validateKeyId(crimeId);
		final Crime crime = _crimeService.get(crimeId);
		if (crime != null) {
			return _mapperService.map(crime, CrimeRO.class);
		} else {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public CrimeRO getCrimeByIncidentId(final Long incidentId) {
		validateKeyId(incidentId);
		final Incident incident = _incidentService.get(incidentId);
		validateGenericObject(incident);
		final Crime crime = _crimeService.get(incident);
		if (crime != null) {
			return _mapperService.map(crime, CrimeRO.class);
		} else {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public CrimeRO getCrimeByUniqueIncidentId(final String uniqueIncidentId) {
		validateUniqueIncidentId(uniqueIncidentId);
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId);
		validateGenericObject(incident);
		final Crime crime = _crimeService.get(incident);
		if (crime != null) {
			return _mapperService.map(crime, CrimeRO.class);
		} else {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO createCrime(final CrimeRO crimeRO) {
		validateObject(crimeRO);
		final Incident incident = getIncident(crimeRO);
		validateGenericObject(incident);
		
		//Validate if the incident already has an crime record.
		final Crime existingCrimeRecord = _crimeService.get(incident);
		if (existingCrimeRecord != null) {
			throw new ResourceConflictException(_messageBuilder.build(RestMessage.INCIDENT_ALREADY_HAS_EXISTING_ASSET_RECORD));
		}
		
		final Crime crime = constructNewCrime(crimeRO, incident);
		if (crime != null) {
			final Crime newCrime = _crimeService.createCrime(crime);
			if (newCrime != null) {
				return _mapperService.map(newCrime, CrimeRO.class);
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
	public CrimeRO updateCrime(final CrimeRO crimeRO) {
		validateObject(crimeRO);
		final Crime crime = constructCrime(crimeRO);
		if (crime != null) {
			final Crime updatedCrime = _crimeService.updateCrime(crime);
			if (updatedCrime != null)  {
				return _mapperService.map(updatedCrime, CrimeRO.class);
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
	public CrimeRO addOrUpdateCrime(final CrimeRO crimeRO) {
		validateObject(crimeRO);
		if (crimeRO.getId() > 0) {
			return updateCrime(crimeRO);
		} else {
			return createCrime(crimeRO);
		}
	}
	
	//add and remove crime suspect to a crime record
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO addCrimeSuspectForCrime(final Long crimeId, final CrimeSuspectRO crimeSuspectRO) {
		validateKeyId(crimeId);
		validateObject(crimeSuspectRO);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final CrimeSuspect crimeSuspect = _crimeSuspectRestService.constructNewCrimeSuspect(crimeSuspectRO);
		validateGenericObject(crimeSuspect);
		final CrimeSuspect newCrimeSuspect = _crimeSuspectService.createNewCrimeSuspect(crimeSuspect);
		if (newCrimeSuspect != null) {
			crime.getCrimeSuspects().add(newCrimeSuspect);
			final Crime updatedCrime = _crimeService.updateCrime(crime);
			if (updatedCrime != null) {
				return _mapperService.map(updatedCrime, CrimeRO.class); 
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
	public CrimeRO addCrimeSuspectsForCrime(final CrimeSuspectWrapper crimeSuspectWrapper) {
		validateObject(crimeSuspectWrapper);
		validateKeyId(crimeSuspectWrapper.getCrimeId());
		validateCollectionObject(crimeSuspectWrapper.getCrimeSuspects());
		final Crime crime = _crimeService.get(crimeSuspectWrapper.getCrimeId());
		validateGenericObject(crime);
		
		final Set<CrimeSuspect> crimeSuspects = new HashSet<CrimeSuspect>(0);
		for (CrimeSuspectRO crimeSuspectRO : crimeSuspectWrapper.getCrimeSuspects()) {
			if (crimeSuspectRO != null) {
				final CrimeSuspect crimeSuspect = _crimeSuspectRestService.constructNewCrimeSuspect(crimeSuspectRO);
				if (crimeSuspect != null) {
					crimeSuspects.add(crimeSuspect);
				}
			}
		}
		validateCollectionObject(crimeSuspects);
		
		final Set<CrimeSuspect> newCrimeSuspects = _crimeSuspectService.createNewCrimeSuspects(crimeSuspects);
		if (newCrimeSuspects != null && !newCrimeSuspects.isEmpty()) {
			crime.getCrimeSuspects().addAll(newCrimeSuspects);
			final Crime updatedCrime = _crimeService.updateCrime(crime);
			if (updatedCrime != null) {
				return _mapperService.map(updatedCrime, CrimeRO.class); 
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
	public CrimeRO addExistingCrimeSuspectForCrime(final Long crimeId, final Long crimeSuspectId) {
		validateKeyId(crimeId);
		validateKeyId(crimeSuspectId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final CrimeSuspect existingCrimeSuspect = _crimeSuspectService.get(crimeSuspectId);
		validateGenericObject(existingCrimeSuspect);
		crime.getCrimeSuspects().add(existingCrimeSuspect);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO addExistingCrimeSuspectsForCrime(final CrimeSuspectWrapper crimeSuspectWrapper) {
		validateObject(crimeSuspectWrapper);
		validateKeyId(crimeSuspectWrapper.getCrimeId());
		validateCollectionObject(crimeSuspectWrapper.getCrimeSuspects());
		final Crime crime = _crimeService.get(crimeSuspectWrapper.getCrimeId());
		validateGenericObject(crime);
		
		final Set<CrimeSuspect> crimeSuspects = new HashSet<CrimeSuspect>(0);
		for (CrimeSuspectRO crimeSuspectRO : crimeSuspectWrapper.getCrimeSuspects()) {
			if (crimeSuspectRO != null) {
				if (crimeSuspectRO.getId() > 0) {
					final CrimeSuspect crimeSuspect = _crimeSuspectService.get(crimeSuspectRO.getId());
					if (crimeSuspect != null) {
						crimeSuspects.add(crimeSuspect);
					}
				}
			}
		}
		validateCollectionObject(crimeSuspects);
		crime.getCrimeSuspects().addAll(crimeSuspects);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO addEmployeeCrimeSuspectForCrimeById(final Long crimeId, final Long employeeId) {
		validateKeyId(crimeId);
		validateKeyId(employeeId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final User employeeCrimeSuspect = _userService.get(employeeId);
		validateGenericObject(employeeCrimeSuspect);
		crime.getEmployeeCrimeSuspects().add(employeeCrimeSuspect);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO addEmployeeCrimeSuspectForCrimeByLoginId(final Long crimeId, final String employeeLoginId) {
		validateKeyId(crimeId);
		validateUsername(employeeLoginId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final User employeeCrimeSuspect = _userService.getUserByUserLoginId(employeeLoginId.trim());
		validateGenericObject(employeeCrimeSuspect);
		crime.getEmployeeCrimeSuspects().add(employeeCrimeSuspect);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO addEmployeeCrimeSuspectsForCrimeByIds(final CrimeSuspectWrapper crimeSuspectWrapper) {
		validateObject(crimeSuspectWrapper);
		validateKeyId(crimeSuspectWrapper.getCrimeId());
		final Long[] employeeIds = crimeSuspectWrapper.getEmployeeIds(); 
		if (employeeIds == null || employeeIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final Crime crime = _crimeService.get(crimeSuspectWrapper.getCrimeId());
		validateGenericObject(crime);
		
		final Set<User> employeeCrimeSuspects = new HashSet<User>(0);
		for (int i = 0; i < employeeIds.length; i++) {
			final Long employeeId = employeeIds[i];
			if (employeeId != null && employeeId > 0) {
				final User employeeCrimeSuspect = _userService.get(employeeId);
				if (employeeCrimeSuspect != null) {
					employeeCrimeSuspects.add(employeeCrimeSuspect);
				}
			}
		}
		validateCollectionObject(employeeCrimeSuspects);
		crime.getEmployeeCrimeSuspects().addAll(employeeCrimeSuspects);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO addEmployeeCrimeSuspectsForCrimeByLoginIds(final CrimeSuspectWrapper crimeSuspectWrapper) {
		validateObject(crimeSuspectWrapper);
		validateKeyId(crimeSuspectWrapper.getCrimeId());
		final String[] employeeLoginIds = crimeSuspectWrapper.getEmployeeLoginIds(); 
		if (employeeLoginIds == null || employeeLoginIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final Crime crime = _crimeService.get(crimeSuspectWrapper.getCrimeId());
		validateGenericObject(crime);
		
		final Set<User> employeeCrimeSuspects = new HashSet<User>(0);
		for (int i = 0; i < employeeLoginIds.length; i++) {
			final String employeeLoginId = employeeLoginIds[i];
			if (employeeLoginId != null && !employeeLoginId.trim().isEmpty()) {
				final User employeeCrimeSuspect = _userService.getUserByUserLoginId(employeeLoginId.trim());
				if (employeeCrimeSuspect != null) {
					employeeCrimeSuspects.add(employeeCrimeSuspect);
				}
			}
		}
		validateCollectionObject(employeeCrimeSuspects);
		crime.getEmployeeCrimeSuspects().addAll(employeeCrimeSuspects);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO removeCrimeSuspectFromCrime(final Long crimeId, final Long crimeSuspectId) {
		validateKeyId(crimeId);
		validateKeyId(crimeSuspectId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final CrimeSuspect crimeSuspect = _crimeSuspectService.get(crimeSuspectId);
		validateGenericObject(crimeSuspect);
		if (!crime.getCrimeSuspects().contains(crimeSuspect)) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.CRIME_ID_AND_CRIME_SUSPECT_ID_DOES_NOT_MATCH));
		}
		crime.getCrimeSuspects().remove(crimeSuspect);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO removeCrimeSuspectsFromCrime(final CrimeSuspectWrapper crimeSuspectWrapper) {
		validateObject(crimeSuspectWrapper);
		validateKeyId(crimeSuspectWrapper.getCrimeId());
		validateCollectionObject(crimeSuspectWrapper.getCrimeSuspects());
		
		final Crime crime = _crimeService.get(crimeSuspectWrapper.getCrimeId());
		validateGenericObject(crime);
		
		final Set<CrimeSuspect> crimeSuspects = new HashSet<CrimeSuspect>(0);
		for (CrimeSuspectRO crimeSuspectRO : crimeSuspectWrapper.getCrimeSuspects()) {
			if (crimeSuspectRO != null) {
				if (crimeSuspectRO.getId() > 0) {
					final CrimeSuspect crimeSuspect = _crimeSuspectService.get(crimeSuspectRO.getId());
					if (crimeSuspect != null) {
						crimeSuspects.add(crimeSuspect);
					}
				}
			}			
		}
		validateCollectionObject(crimeSuspects);
		crime.getCrimeSuspects().removeAll(crimeSuspects);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO removeEmployeeCrimeSuspectFromCrimeById(final Long crimeId, final Long employeeId) {
		validateKeyId(crimeId);
		validateKeyId(employeeId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final User employeeCrimeSuspect = _userService.get(employeeId);
		validateGenericObject(employeeCrimeSuspect);
		crime.getEmployeeCrimeSuspects().remove(employeeCrimeSuspect);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO removeEmployeeCrimeSuspectFromCrimeByLoginId(final Long crimeId, final String employeeLoginId) {
		validateKeyId(crimeId);
		validateUsername(employeeLoginId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final User employeeCrimeSuspect = _userService.getUserByUserLoginId(employeeLoginId.trim());
		validateGenericObject(employeeCrimeSuspect);
		crime.getEmployeeCrimeSuspects().remove(employeeCrimeSuspect);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO removeEmployeeCrimeSuspectsFromCrimeByIds(final CrimeSuspectWrapper crimeSuspectWrapper) {
		validateObject(crimeSuspectWrapper);
		validateKeyId(crimeSuspectWrapper.getCrimeId());
		
		final Long[] employeeIds = crimeSuspectWrapper.getEmployeeIds();
		if (employeeIds == null || employeeIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.NOTHING_TO_DELETE));
		}
		
		final Crime crime = _crimeService.get(crimeSuspectWrapper.getCrimeId());
		validateGenericObject(crime);
		
		final Set<User> employeeCrimeSuspects = new HashSet<User>(0);
		for (int i = 0; i < employeeIds.length; i++) {
			final Long employeeId = employeeIds[i];
			if (employeeId != null && employeeId > 0) {
				final User employeeCrimeSuspect = _userService.get(employeeId);
				if (employeeCrimeSuspect != null) {
					employeeCrimeSuspects.add(employeeCrimeSuspect);
				}
			}
		}
		validateCollectionObject(employeeCrimeSuspects);
		crime.getEmployeeCrimeSuspects().removeAll(employeeCrimeSuspects);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO removeEmployeeCrimeSuspectsFromCrimeByLoginIds(final CrimeSuspectWrapper crimeSuspectWrapper) {
		validateObject(crimeSuspectWrapper);
		validateKeyId(crimeSuspectWrapper.getCrimeId());
		
		final String[] employeeLoginIds = crimeSuspectWrapper.getEmployeeLoginIds();
		if (employeeLoginIds == null || employeeLoginIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.NOTHING_TO_DELETE));
		}
		
		final Crime crime = _crimeService.get(crimeSuspectWrapper.getCrimeId());
		validateGenericObject(crime);
		
		final Set<User> employeeCrimeSuspects = new HashSet<User>(0);
		for (int i = 0; i < employeeLoginIds.length; i++) {
			final String employeeLoginId = employeeLoginIds[i];
			if (employeeLoginId != null && !employeeLoginId.trim().isEmpty()) {
				final User employeeCrimeSuspect = _userService.getUserByUserLoginId(employeeLoginId.trim());
				if (employeeCrimeSuspect != null) {
					employeeCrimeSuspects.add(employeeCrimeSuspect);
				}
			}
		}
		validateCollectionObject(employeeCrimeSuspects);
		crime.getEmployeeCrimeSuspects().removeAll(employeeCrimeSuspects);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}		
	}
	
	//add and remove witness to crime record
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO addWitnessToCrime(final Long crimeId, final WitnessRO witnessRO) {
		validateKeyId(crimeId);
		validateObject(witnessRO);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final Witness witness = _witnessRestService.constructNewWitness(witnessRO);
		validateGenericObject(witness);
		final Witness newWitness = _witnessService.createNewWitness(witness);
		if (newWitness != null) {
			crime.getWitnesses().add(newWitness);
			final Crime updatedCrime = _crimeService.updateCrime(crime);
			if (updatedCrime != null) {
				return _mapperService.map(updatedCrime, CrimeRO.class); 
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
	public CrimeRO addWitnessesToCrime(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getCrimeId());
		validateCollectionObject(witnessWrapper.getWitnesses());
		final Crime crime = _crimeService.get(witnessWrapper.getCrimeId());
		validateGenericObject(crime);
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
		crime.getWitnesses().addAll(newWitnesses);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO addExistingWitnessToCrime(final Long crimeId, final Long witnessId) {
		validateKeyId(crimeId);
		validateKeyId(witnessId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final Witness witness = _witnessService.get(witnessId);
		validateGenericObject(witness);
		crime.getWitnesses().add(witness);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO addExistingWitnessesToCrime(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getCrimeId());
		validateCollectionObject(witnessWrapper.getWitnesses());
		final Crime crime = _crimeService.get(witnessWrapper.getCrimeId());
		validateGenericObject(crime);
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
		crime.getWitnesses().addAll(witnesses);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO addEmployeeWitnessToCrimeById(final Long crimeId, final Long employeeId) {
		validateKeyId(crimeId);
		validateKeyId(employeeId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final User employeeWitness = _userService.get(employeeId);
		validateGenericObject(employeeWitness);
		crime.getEmployeeWitnesses().add(employeeWitness);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO addEmployeeWitnessToCrimeByLoginId(final Long crimeId, final String employeeLoginId) {
		validateKeyId(crimeId);
		validateUsername(employeeLoginId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final User employeeWitness = _userService.getUserByUserLoginId(employeeLoginId.trim());
		validateGenericObject(employeeWitness);
		crime.getEmployeeWitnesses().add(employeeWitness);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO addEmployeeWitnessesToCrimeByIds(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getCrimeId());
		final Long[] employeeIds = witnessWrapper.getEmployeeIds();
		if (employeeIds == null || employeeIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.OBJECT_NULL_OR_EMPTY));
		}
		final Crime crime = _crimeService.get(witnessWrapper.getCrimeId());
		validateGenericObject(crime);
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
		crime.getEmployeeWitnesses().addAll(employeeWitnesses);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO addEmployeeWitnessesToCrimeByLoginIds(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getCrimeId());
		final String[] employeeLoginIds = witnessWrapper.getEmployeeLoginIds();
		if (employeeLoginIds == null || employeeLoginIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.OBJECT_NULL_OR_EMPTY));
		}
		final Crime crime = _crimeService.get(witnessWrapper.getCrimeId());
		validateGenericObject(crime);
		final Set<User> employeeWitnesses = new HashSet<User>(0);
		for (int i = 0; i < employeeLoginIds.length; i++) {
			final String employeeLoginId = employeeLoginIds[i];
			if (employeeLoginId != null && !employeeLoginId.trim().isEmpty()) {
				final User employeeWitness = _userService.getUserByUserLoginId(employeeLoginId.trim());
				if (employeeWitness != null) {
					employeeWitnesses.add(employeeWitness);
				}
			}
		}
		validateCollectionObject(employeeWitnesses);
		crime.getEmployeeWitnesses().addAll(employeeWitnesses);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO removeWitnessFromCrime(final Long crimeId, final Long witnessId) {
		validateKeyId(crimeId);
		validateKeyId(witnessId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final Witness witness = _witnessService.get(witnessId);
		validateGenericObject(witness);
		crime.getWitnesses().remove(witness);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO removeWitnessesFromCrime(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getCrimeId());
		validateCollectionObject(witnessWrapper.getWitnesses());
		final Crime crime = _crimeService.get(witnessWrapper.getCrimeId());
		validateGenericObject(crime);
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
		crime.getWitnesses().removeAll(witnesses);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO removeEmployeeWitnessFromCrimeById(final Long crimeId, final Long employeeId) {
		validateKeyId(crimeId);
		validateKeyId(employeeId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final User employeeWitness = _userService.get(employeeId);
		validateGenericObject(employeeWitness);
		crime.getEmployeeWitnesses().remove(employeeWitness);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO removeEmployeeWitnessFromCrimeByLoginId(final Long crimeId, final String employeeLoginId) {
		validateKeyId(crimeId);
		validateUsername(employeeLoginId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final User employeeWitness = _userService.getUserByUserLoginId(employeeLoginId.trim());
		validateGenericObject(employeeWitness);
		crime.getEmployeeWitnesses().remove(employeeWitness);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO removeEmployeeWitnessesFromCrimeByIds(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getCrimeId());
		final Long[] employeeIds = witnessWrapper.getEmployeeIds();
		if (employeeIds == null || employeeIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.NOTHING_TO_DELETE));
		}
		final Crime crime = _crimeService.get(witnessWrapper.getCrimeId());
		validateGenericObject(crime);
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
		crime.getEmployeeWitnesses().removeAll(employeeWitnesses);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeRO removeEmployeeWitnessesFromCrimeByLoginIds(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateKeyId(witnessWrapper.getCrimeId());
		final String[] employeeLoginIds = witnessWrapper.getEmployeeLoginIds();
		if (employeeLoginIds == null || employeeLoginIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.NOTHING_TO_DELETE));
		}
		final Crime crime = _crimeService.get(witnessWrapper.getCrimeId());
		validateGenericObject(crime);
		final Set<User> employeeWitnesses = new HashSet<User>(0);
		for (int i = 0; i < employeeLoginIds.length; i++) {
			final String employeeLoginId = employeeLoginIds[i];
			if (employeeLoginId != null && !employeeLoginId.trim().isEmpty()) {
				final User employeeWitness = _userService.getUserByUserLoginId(employeeLoginId.trim());
				if (employeeWitness != null) {
					employeeWitnesses.add(employeeWitness);
				}
			}
		}
		validateCollectionObject(employeeWitnesses);
		crime.getEmployeeWitnesses().removeAll(employeeWitnesses);
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		if (updatedCrime != null) {
			return _mapperService.map(updatedCrime, CrimeRO.class); 
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}
	
	private Incident getIncident(final CrimeRO crimeRO) {
		Incident incident = null;
		if (crimeRO != null) {
			if (crimeRO.getIncident() != null) {
				if (crimeRO.getIncident().getId() > 0) {
					incident = _incidentService.get(crimeRO.getIncident().getId());
				} else if (crimeRO.getIncident().getUniqueIncidentId() != null && !crimeRO.getIncident().getUniqueIncidentId().trim().isEmpty()) {
					incident = _incidentService.getIncidentByUniqueIncidentId(crimeRO.getIncident().getUniqueIncidentId().trim());
				}
			}
		}
		return incident;
	}
	
	private Crime constructNewCrime(final CrimeRO crimeRO, final Incident incident) {
		if (crimeRO != null && incident != null) {
			final Crime crime = new Crime.Builder().setIncident(incident).setStatusFlag(StatusFlag.ACTIVE).build();
			return setOtherFieldsForCrime(crime, crimeRO);
		} else {
			return null;
		}
	}
	
	private Crime constructCrime(final CrimeRO crimeRO) {
		if (crimeRO != null && crimeRO.getId() > 0) {
			final Crime crime = _crimeService.get(crimeRO.getId());
			return setOtherFieldsForCrime(crime, crimeRO);
		} else {
			return null;
		}
	}
	
	private Crime setOtherFieldsForCrime(final Crime crime, final CrimeRO crimeRO) {
		if (crime != null && crimeRO != null) {
			// set other values for crime
			// crime date and time
			if (crimeRO.getCrimeDateTime() != null) {
				crime.setCrimeDateTime(crimeRO.getCrimeDateTime());
			}
			// crime description
			if (crimeRO.getCrimeDescription() != null && !crimeRO.getCrimeDescription().trim().isEmpty()) {
				crime.setCrimeDescription(crimeRO.getCrimeDescription().trim());
			}
			//
			YesNoType anyWitness = YesNoType.N;
			if (crimeRO.getAnyWitness() != null && crimeRO.getAnyWitness().name().equals("Y")) {
				anyWitness = YesNoType.Y;
			}
			crime.setAnyWitness(anyWitness);
			return crime;
		} else {
			return null;
		}
	}	
}
