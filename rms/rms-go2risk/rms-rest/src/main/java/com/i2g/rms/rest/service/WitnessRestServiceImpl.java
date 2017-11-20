package com.i2g.rms.rest.service;

import java.util.ArrayList;
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
import com.i2g.rms.domain.model.Crime;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.rest.model.WitnessRO;
import com.i2g.rms.rest.model.lookup.WitnessTableRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureDetailRO;
import com.i2g.rms.rest.model.wrapper.DistinguishingFeatureDetailWrapper;
import com.i2g.rms.rest.model.wrapper.WitnessWrapper;
import com.i2g.rms.rest.service.incident.IncidentRestService;
import com.i2g.rms.service.AccidentService;
import com.i2g.rms.service.CrimeService;
import com.i2g.rms.service.WitnessService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;

/**
 * Rest services for suspect rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class WitnessRestServiceImpl extends AbstractRestService implements WitnessRestService {
	
	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(WitnessRestServiceImpl.class);
	
	@Autowired
	private SuspectRestService _suspectRestService;
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;
	@Autowired
	private IncidentRestService _incidentRestService; 
	@Autowired
	private WitnessService _witnessService;
	@Autowired
	private AccidentService _accidentService;
	@Autowired
	private CrimeService _crimeService;
		
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<WitnessRO> get() {
		List<Witness> witnesses = _witnessService.get();
		List<WitnessRO> witnessROs = (witnesses == null || witnesses.isEmpty()) ? Collections.emptyList() : _mapperService.map(witnesses, WitnessRO.class);
		return witnessROs;
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public Set<WitnessRO> getWitnessesByAccidentId(final Long accidentId) {
		validateKeyId(accidentId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		Set<Witness> witnesses = accident.getWitnesses();
		Set<WitnessRO> witnessROs = (witnesses == null || witnesses.isEmpty()) ? Collections.emptySet() : _mapperService.map(witnesses, WitnessRO.class);
		return witnessROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public Set<WitnessRO> getWitnessesByCrimeId(final Long crimeId) {
		validateKeyId(crimeId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		Set<Witness> witnesses = crime.getWitnesses();
		Set<WitnessRO> witnessROs = (witnesses == null || witnesses.isEmpty()) ? Collections.emptySet() : _mapperService.map(witnesses, WitnessRO.class);
		return witnessROs;		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<WitnessTableRO> getWitnessTableByAccidentId(final Long accidentId) {
		validateKeyId(accidentId);
		final Accident accident = _accidentService.get(accidentId);
		validateGenericObject(accident);
		final List<WitnessTableRO> witnessTableROs = getWitnessTable(accident);
		return (witnessTableROs == null || witnessTableROs.isEmpty()) ? Collections.emptyList() : _mapperService.map(witnessTableROs, WitnessTableRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<WitnessTableRO> getWitnessTableByCrimeId(final Long crimeId) {
		validateKeyId(crimeId);
		final Crime crime = _crimeService.get(crimeId);
		validateGenericObject(crime);
		final List<WitnessTableRO> witnessTableROs = getWitnessTable(crime);
		return (witnessTableROs == null || witnessTableROs.isEmpty()) ? Collections.emptyList() : _mapperService.map(witnessTableROs, WitnessTableRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public WitnessRO get(final long witnessId) {
		if (witnessId > 0) {
			final Witness witness = _witnessService.get(witnessId);
			validateGenericObject(witness);
			return _mapperService.map(witness, WitnessRO.class);
		} else {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public WitnessRO createWitness(final WitnessRO witnessRO) {
		validateObject(witnessRO);
		final Witness witness = constructNewWitness(witnessRO);
		validateGenericObject(witness);		
		final Witness newWitness = _witnessService.createNewWitness(witness);
		if (newWitness != null) {
			return _mapperService.map(newWitness, WitnessRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public Set<WitnessRO> createWitnesses(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		validateCollectionObject(witnessWrapper.getWitnesses());
		final Set<Witness> witnesses = new HashSet<Witness>(0);
		if (witnessWrapper.getWitnesses() != null && !witnessWrapper.getWitnesses().isEmpty()) {
			for (WitnessRO witnessRO : witnessWrapper.getWitnesses()) {
				if (witnessRO != null) {
					final Witness witness = constructNewWitness(witnessRO);
					if (witness != null) {
						witnesses.add(witness);
					}
				}
			}
		}
		final Set<Witness> newWitnesses = _witnessService.createNewWitnesses(witnesses);
		if (newWitnesses != null && !newWitnesses.isEmpty()) {
			return _mapperService.map(newWitnesses, WitnessRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public WitnessRO udpateWitness(final WitnessRO witnessRO) {
		validateObject(witnessRO);
		if (witnessRO.getId() <= 0 ) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.RECORD_FETCH_FAILED_FOR_UPDATE));
		}
		final Witness witness = constructWitness(witnessRO);
		validateGenericObject(witness);
		final Witness updatedWitness = _witnessService.updateWitness(witness);
		if (updatedWitness != null) {
			return _mapperService.map(updatedWitness, WitnessRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public Set<WitnessRO> udpateWitnesses(final WitnessWrapper witnessWrapper) {
		validateObject(witnessWrapper);
		Set<Witness> witnesses = new HashSet<Witness>(0);
		for (WitnessRO witnessRO : witnessWrapper.getWitnesses()) {
			if (witnessRO != null) {
				final Witness witness = constructWitness(witnessRO);
				if (witness != null) {
					witnesses.add(witness);
				}
			}
		}
		final Set<Witness> updatedWitnesses = _witnessService.updateWitnesses(witnesses);		
		if (updatedWitnesses != null && !updatedWitnesses.isEmpty()) {
			return _mapperService.map(updatedWitnesses, WitnessRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public void removeDistinguishingFeatureDetailsFromWitness(final DistinguishingFeatureDetailWrapper distinguishingFeatureDetailWrapper) {
		validateObject(distinguishingFeatureDetailWrapper);
		validateCollectionObject(distinguishingFeatureDetailWrapper.getDistinguishingFeatureDetails());
		if (distinguishingFeatureDetailWrapper.getWitnessId() == null || distinguishingFeatureDetailWrapper.getWitnessId() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		Witness witness = _witnessService.get(distinguishingFeatureDetailWrapper.getWitnessId());
		validateGenericObject(witness);
		Set<DistinguishingFeatureDetail> distinguishingFeatureDetails = new HashSet<DistinguishingFeatureDetail>(0);
		for (DistinguishingFeatureDetailRO distinguishingFeatureDetailRO : distinguishingFeatureDetailWrapper.getDistinguishingFeatureDetails()) {
			if (distinguishingFeatureDetailRO != null) {
				if (distinguishingFeatureDetailRO.getId() != null && !distinguishingFeatureDetailRO.getId().trim().isEmpty()) {
					final DistinguishingFeatureDetail distinguishingFeatureDetail = _tableMaintenanceService.getDistinguishingFeatureDetailByCode(distinguishingFeatureDetailRO.getId().trim());
					if (distinguishingFeatureDetail != null) {
						distinguishingFeatureDetails.add(distinguishingFeatureDetail);
					}
				}
			}
		}
		_witnessService.removeDistinguishingFeatureDetailsFromWitness(witness, distinguishingFeatureDetails);		
	}
	
	@Override
	public Witness constructNewWitness(final WitnessRO witnessRO) {
		validateObject(witnessRO);
		// Construction of new witness
		final Witness witness = new Witness.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
		validateGenericObject(witness);
		// Set other values
		return setOtherFieldsForWitness(witness, witnessRO);
	}
	
	@Override
	public Witness constructWitness(final WitnessRO witnessRO) {
		validateObject(witnessRO);
		if (witnessRO.getId() <= 0 ) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.RECORD_FETCH_FAILED_FOR_UPDATE));
		}
		final Witness witness = _witnessService.get(witnessRO.getId());
		validateGenericObject(witness);
		return setOtherFieldsForWitness(witness, witnessRO);
	}
	
	@Override
	public Witness setOtherFieldsForWitness(final Witness witness, final WitnessRO witnessRO) {
		// Set other values for Witness
		if (witness != null && witnessRO != null) {
			// Set other values for (new) witnesses
			// Witness type
			if (witnessRO.getWitnessType() != null) {
				if (witnessRO.getWitnessType().getId() != null && !witnessRO.getWitnessType().getId().trim().isEmpty()) {
					witness.setWitnessType(_tableMaintenanceService.getWitnessTypeByCode(witnessRO.getWitnessType().getId().trim()));
				}
			}
			if (witnessRO.getTitle() != null && !witnessRO.getTitle().trim().isEmpty()) {
				witness.setTitle(witnessRO.getTitle().trim());
			}
			if (witnessRO.getFirstName() != null && !witnessRO.getFirstName().trim().isEmpty()) {
				witness.setFirstName(witnessRO.getFirstName().trim());
			}
			if (witnessRO.getMiddleName() != null && !witnessRO.getMiddleName().trim().isEmpty()) {
				witness.setMiddleName(witnessRO.getMiddleName().trim());
			}
			if (witnessRO.getLastName() != null && !witnessRO.getLastName().trim().isEmpty()) {
				witness.setLastName(witnessRO.getLastName().trim());
			}
			if (witnessRO.getNameSuffix() != null && !witnessRO.getNameSuffix().trim().isEmpty()) {
				witness.setNameSuffix(witnessRO.getNameSuffix());
			}
			if (witnessRO.getPhone() != null && !witnessRO.getPhone().trim().isEmpty()) {
				witness.setPhone(witnessRO.getPhone().trim());
			}
			if (witnessRO.getFax() != null && !witnessRO.getFax().trim().isEmpty()) {
				witness.setFax(witnessRO.getFax().trim());
			}
			if (witnessRO.getAlternatePhone() != null && !witnessRO.getAlternatePhone().trim().isEmpty()) {
				witness.setAlternatePhone(witnessRO.getAlternatePhone().trim());
			}
			if (witnessRO.getEmail() != null && !witnessRO.getEmail().trim().isEmpty()) {
				witness.setEmail(witnessRO.getEmail().trim());
			}
			if (witnessRO.getWebsite() != null && !witnessRO.getWebsite().trim().isEmpty()) {
				witness.setWebsite(witnessRO.getWebsite().trim());
			}
			// Gender type
			if (witnessRO.getGenderType() != null) {
				if (witnessRO.getGenderType().getId() != null && !witnessRO.getGenderType().getId().trim().isEmpty()) {
					witness.setGenderType(_tableMaintenanceService.getGenderTypeByCode(witnessRO.getGenderType().getId().trim()));
				}
			}
			// gender type other
			if (witnessRO.getGenderTypeOther() != null && !witnessRO.getGenderTypeOther().trim().isEmpty()) {
				witness.setGenderTypeOther(witnessRO.getGenderTypeOther().trim());
			}
			// Age
			if (witnessRO.getAge() != null && witnessRO.getAge() > 0) {
				witness.setAge(witnessRO.getAge());
			}
			// Date of Birth
			if (witnessRO.getDateOfBirth() != null) {
				witness.setDateOfBirth(witnessRO.getDateOfBirth());
			}
			// Address of the witness person
			if (witnessRO.getAddresses() != null && !witnessRO.getAddresses().isEmpty()) {
				witness.setAddresses(_suspectRestService.createOrUpdateAddresses(witnessRO.getAddresses(), null, null, null, witness, null, null));
			}
			// other comments for witness type
			if (witnessRO.getWitnessTypeOther() != null && !witnessRO.getWitnessTypeOther().trim().isEmpty()) {
				witness.setWitnessTypeOther(witnessRO.getWitnessTypeOther().trim());
			}
			// construct and set distinguishing feature details
			if (witness.getDistinguishingFeatureDetails() == null || witness.getDistinguishingFeatureDetails().isEmpty()) {
				witness.setDistinguishingFeatureDetails(new HashSet<DistinguishingFeatureDetail>(0));
			}
			witness.getDistinguishingFeatureDetails().addAll(_incidentRestService.constructDistinguishingFeatureDetails(witnessRO.getDistinguishingFeatureDetails()));
			// other comments for distinguishing feature
			if (witnessRO.getDistinguishingFeatureOther() != null && !witnessRO.getDistinguishingFeatureOther().trim().isEmpty()) {
				witness.setDistinguishingFeatureOther(witnessRO.getDistinguishingFeatureOther().trim());
			}			
		}
		return witness;
	}
	
	private List<WitnessTableRO> getWitnessTable(final Accident accident) {
		final List<WitnessTableRO> witnessTableROs = new ArrayList<WitnessTableRO>(0);
		if (accident != null) {
			//Populate the non-employee witnesses first
			if (accident.getWitnesses() != null && !accident.getWitnesses().isEmpty()) {
				for (Witness witness : accident.getWitnesses()) {
					if (witness != null) {
						final WitnessTableRO witnessTableRO = new WitnessTableRO();
						
						//Set the Witness category as non-employee						
						witnessTableRO.setWitnessCategory("NON-EMPLOYEE");
						witnessTableRO.setWitnessId(witness.getId());
						
						if (accident.getIncident() != null) {
							witnessTableRO.setIncidentId(accident.getIncident().getId());
							witnessTableRO.setUniqueIncidentId(accident.getIncident().getUniqueIncidentId());
						}
						
						//crime id will be null for accident witness
						witnessTableRO.setCrimeId(0l);						
						witnessTableRO.setAccidentId(accident.getId());						
						//employee id and employee login id will be null for non-employee witnesses
						witnessTableRO.setEmployeeId(0l);
						witnessTableRO.setEmployeeLoginId(null);
						
						witnessTableRO.setTitle(witness.getTitle());
						
						String firstName = null;
						String lastName = null;
						String fullName = null;
						
						if (witness.getFirstName() != null && !witness.getFirstName().trim().isEmpty()) {
							firstName = witness.getFirstName().trim();
						} else {
							firstName = "No firstname";
						}
						
						if (witness.getLastName() != null && !witness.getLastName().trim().isEmpty()) {
							lastName = witness.getLastName().trim();
						} else {
							lastName = "No lastname";
						}
						fullName = lastName + ", " + firstName;
						
						witnessTableRO.setFirstName(firstName);
						witnessTableRO.setMiddleName(witness.getMiddleName());
						witnessTableRO.setLastName(lastName);
						witnessTableRO.setNameSuffix(witness.getNameSuffix());
						witnessTableRO.setFullName(fullName);
						witnessTableRO.setDateOfBirth(witness.getDateOfBirth());
						witnessTableRO.setAge(witness.getAge());
						witnessTableRO.setPhone(witness.getPhone());
						witnessTableRO.setAlternatePhone(witness.getAlternatePhone());
						witnessTableRO.setEmail(witness.getEmail());
						if (witness.getStatusFlag() != null) {
							witnessTableRO.setStatusFlag(witness.getStatusFlag().name());
						}
						if (witness.getGenderType() != null) {
							witnessTableRO.setGenderTypeCode(witness.getGenderType().getId());
							witnessTableRO.setGenderTypeDescription(witness.getGenderType().getDescription());
						}
						if (witness.getWitnessType() != null) {	
							witnessTableRO.setTypeCode(witness.getWitnessType().getId());
							witnessTableRO.setTypeDescription(witness.getWitnessType().getDescription());
						}
						witnessTableRO.setTypeOtherDescription(witness.getWitnessTypeOther());
						witnessTableROs.add(witnessTableRO);
					}
				}
			}
			//Populate the employee witnesses if any
			if (accident.getEmployeeWitnesses() != null && !accident.getEmployeeWitnesses().isEmpty()) {
				for (User employeeWitness : accident.getEmployeeWitnesses()) {
					if (employeeWitness != null) {
						final WitnessTableRO witnessTableRO = new WitnessTableRO();
						
						//Set the witness category as employee
						witnessTableRO.setWitnessCategory("EMPLOYEE");						
						//Witness id will be null for employee type Witnesses.
						witnessTableRO.setWitnessId(0l);
						//crime id will be null for accident witness
						witnessTableRO.setCrimeId(0l);
						witnessTableRO.setAccidentId(accident.getId());
						
						if (accident.getIncident() != null) {
							witnessTableRO.setIncidentId(accident.getIncident().getId());
							witnessTableRO.setUniqueIncidentId(accident.getIncident().getUniqueIncidentId());
						}
						
						witnessTableRO.setEmployeeId(employeeWitness.getId());
						witnessTableRO.setEmployeeLoginId(employeeWitness.getLoginId());
						witnessTableRO.setTitle(employeeWitness.getTitle());
						
						String firstName = null;
						String lastName = null;
						String fullName = null;
						
						if (employeeWitness.getFirstName() != null && !employeeWitness.getFirstName().trim().isEmpty()) {
							firstName = employeeWitness.getFirstName().trim();
						} else {
							firstName = "No firstname";
						}
						
						if (employeeWitness.getLastName() != null && !employeeWitness.getLastName().trim().isEmpty()) {
							lastName = employeeWitness.getLastName().trim();
						} else {
							lastName = "No lastname";
						}
						fullName = lastName + ", " + firstName;
						
						witnessTableRO.setFirstName(firstName);
						witnessTableRO.setMiddleName(employeeWitness.getMiddleName());
						witnessTableRO.setLastName(lastName);
						witnessTableRO.setNameSuffix(employeeWitness.getNameSuffix());
						witnessTableRO.setFullName(fullName);
						witnessTableRO.setDateOfBirth(employeeWitness.getDateOfBirth());
						witnessTableRO.setAge(employeeWitness.getAge());
						witnessTableRO.setPhone(employeeWitness.getPhone());
						witnessTableRO.setAlternatePhone(employeeWitness.getAlternatePhone());
						witnessTableRO.setEmail(employeeWitness.getEmail());
						if (employeeWitness.getStatusFlag() != null) {
							witnessTableRO.setStatusFlag(employeeWitness.getStatusFlag().name());
						}
						if (employeeWitness.getGenderType() != null) {
							witnessTableRO.setGenderTypeCode(employeeWitness.getGenderType().getId());
							witnessTableRO.setGenderTypeDescription(employeeWitness.getGenderType().getDescription());
						}
						witnessTableRO.setTypeCode("EMP");
						witnessTableRO.setTypeDescription("Employee");
						witnessTableRO.setTypeOtherDescription(null);
						witnessTableROs.add(witnessTableRO);
					}
				}
			}
		}		
		return witnessTableROs;
	}
	
	private List<WitnessTableRO> getWitnessTable(final Crime crime) {
		final List<WitnessTableRO> witnessTableROs = new ArrayList<WitnessTableRO>(0);
		if (crime != null) {
			//Populate the non-employee witnesses first
			if (crime.getWitnesses() != null && !crime.getWitnesses().isEmpty()) {
				for (Witness witness : crime.getWitnesses()) {
					if (witness != null) {
						final WitnessTableRO witnessTableRO = new WitnessTableRO();
						
						//Set the Witness category as non-employee
						witnessTableRO.setWitnessCategory("NON-EMPLOYEE");
						witnessTableRO.setWitnessId(witness.getId());
						
						if (crime.getIncident() != null) {
							witnessTableRO.setIncidentId(crime.getIncident().getId());
							witnessTableRO.setUniqueIncidentId(crime.getIncident().getUniqueIncidentId());
						}
						witnessTableRO.setCrimeId(crime.getId());
						//accident id will be null or zero for crime witnesses
						witnessTableRO.setAccidentId(0l);
						//employee id and employee login id will be null for non-employee witnesses
						witnessTableRO.setEmployeeId(0l);
						witnessTableRO.setEmployeeLoginId(null);
						
						witnessTableRO.setTitle(witness.getTitle());
						
						String firstName = null;
						String lastName = null;
						String fullName = null;
						
						if (witness.getFirstName() != null && !witness.getFirstName().trim().isEmpty()) {
							firstName = witness.getFirstName().trim();
						} else {
							firstName = "No firstname";
						}
						
						if (witness.getLastName() != null && !witness.getLastName().trim().isEmpty()) {
							lastName = witness.getLastName().trim();
						} else {
							lastName = "No lastname";
						}
						fullName = lastName + ", " + firstName;
						
						witnessTableRO.setFirstName(firstName);
						witnessTableRO.setMiddleName(witness.getMiddleName());
						witnessTableRO.setLastName(lastName);
						witnessTableRO.setNameSuffix(witness.getNameSuffix());
						witnessTableRO.setFullName(fullName);
						witnessTableRO.setDateOfBirth(witness.getDateOfBirth());
						witnessTableRO.setAge(witness.getAge());
						witnessTableRO.setPhone(witness.getPhone());
						witnessTableRO.setAlternatePhone(witness.getAlternatePhone());
						witnessTableRO.setEmail(witness.getEmail());
						if (witness.getStatusFlag() != null) {
							witnessTableRO.setStatusFlag(witness.getStatusFlag().name());
						}
						if (witness.getGenderType() != null) {
							witnessTableRO.setGenderTypeCode(witness.getGenderType().getId());
							witnessTableRO.setGenderTypeDescription(witness.getGenderType().getDescription());
						}
						if (witness.getWitnessType() != null) {	
							witnessTableRO.setTypeCode(witness.getWitnessType().getId());
							witnessTableRO.setTypeDescription(witness.getWitnessType().getDescription());
						}
						witnessTableRO.setTypeOtherDescription(witness.getWitnessTypeOther());
						witnessTableROs.add(witnessTableRO);
					}
				}
			}
			//Populate the employee witnesses if any
			if (crime.getEmployeeWitnesses() != null && !crime.getEmployeeWitnesses().isEmpty()) {
				for (User employeeWitness : crime.getEmployeeWitnesses()) {
					if (employeeWitness != null) {
						final WitnessTableRO witnessTableRO = new WitnessTableRO();
						
						//Set the witness category as employee
						witnessTableRO.setWitnessCategory("EMPLOYEE");
						//Witness id will be null for employee type Witnesses.
						witnessTableRO.setWitnessId(0l);
						//crime suspect id will be null for accident witness
						witnessTableRO.setCrimeId(crime.getId());
						//accident id will be null for crime witnesses
						witnessTableRO.setAccidentId(0l);
						
						if (crime.getIncident() != null) {
							witnessTableRO.setIncidentId(crime.getIncident().getId());
							witnessTableRO.setUniqueIncidentId(crime.getIncident().getUniqueIncidentId());
						}
						
						witnessTableRO.setEmployeeId(employeeWitness.getId());
						witnessTableRO.setEmployeeLoginId(employeeWitness.getLoginId());
						witnessTableRO.setTitle(employeeWitness.getTitle());
						
						String firstName = null;
						String lastName = null;
						String fullName = null;
						
						if (employeeWitness.getFirstName() != null && !employeeWitness.getFirstName().trim().isEmpty()) {
							firstName = employeeWitness.getFirstName().trim();
						} else {
							firstName = "No firstname";
						}
						
						if (employeeWitness.getLastName() != null && !employeeWitness.getLastName().trim().isEmpty()) {
							lastName = employeeWitness.getLastName().trim();
						} else {
							lastName = "No lastname";
						}
						fullName = lastName + ", " + firstName;
						
						witnessTableRO.setFirstName(firstName);
						witnessTableRO.setMiddleName(employeeWitness.getMiddleName());
						witnessTableRO.setLastName(lastName);
						witnessTableRO.setNameSuffix(employeeWitness.getNameSuffix());
						witnessTableRO.setFullName(fullName);
						witnessTableRO.setDateOfBirth(employeeWitness.getDateOfBirth());
						witnessTableRO.setAge(employeeWitness.getAge());
						witnessTableRO.setPhone(employeeWitness.getPhone());
						witnessTableRO.setAlternatePhone(employeeWitness.getAlternatePhone());
						witnessTableRO.setEmail(employeeWitness.getEmail());
						if (employeeWitness.getStatusFlag() != null) {
							witnessTableRO.setStatusFlag(employeeWitness.getStatusFlag().name());
						}
						if (employeeWitness.getGenderType() != null) {
							witnessTableRO.setGenderTypeCode(employeeWitness.getGenderType().getId());
							witnessTableRO.setGenderTypeDescription(employeeWitness.getGenderType().getDescription());
						}
						witnessTableRO.setTypeCode("EMP");
						witnessTableRO.setTypeDescription("Employee");
						witnessTableRO.setTypeOtherDescription(null);
						witnessTableROs.add(witnessTableRO);
					}
				}
			}
		}		
		return witnessTableROs;
	}	
}
