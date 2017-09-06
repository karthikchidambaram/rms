package com.i2g.rms.rest.service.incident;

import java.time.LocalDateTime;
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
import com.i2g.rms.domain.model.Address;
import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.ReportedLoss;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.domain.model.YesNoType;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.incident.IncidentStatus;
import com.i2g.rms.domain.model.tablemaintenance.AccidentLocation;
import com.i2g.rms.domain.model.tablemaintenance.AccidentLocationDetail;
import com.i2g.rms.domain.model.tablemaintenance.AccidentType;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeature;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.domain.model.tablemaintenance.EntryPoint;
import com.i2g.rms.domain.model.tablemaintenance.ExternalAgency;
import com.i2g.rms.domain.model.tablemaintenance.GenderType;
import com.i2g.rms.domain.model.tablemaintenance.IncidentLocation;
import com.i2g.rms.domain.model.tablemaintenance.IncidentLocationDetail;
import com.i2g.rms.domain.model.tablemaintenance.IncidentType;
import com.i2g.rms.domain.model.tablemaintenance.InjuredPersonType;
import com.i2g.rms.domain.model.tablemaintenance.InjuryCause;
import com.i2g.rms.domain.model.tablemaintenance.InjuryType;
import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetail;
import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetailSpec;
import com.i2g.rms.domain.model.tablemaintenance.LossType;
import com.i2g.rms.domain.model.tablemaintenance.SuspectType;
import com.i2g.rms.domain.model.tablemaintenance.WeaponType;
import com.i2g.rms.domain.model.tablemaintenance.WitnessType;
import com.i2g.rms.rest.model.AddressRO;
import com.i2g.rms.rest.model.InjuredPersonRO;
import com.i2g.rms.rest.model.ReportedLossRO;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.WitnessRO;
import com.i2g.rms.rest.model.incident.AccidentDetailRO;
import com.i2g.rms.rest.model.incident.IncidentDetailRO;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.incident.LogIncidentRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.rest.service.RestMessage;
import com.i2g.rms.service.AccidentService;
import com.i2g.rms.service.InjuredPersonService;
import com.i2g.rms.service.ReportedLossService;
import com.i2g.rms.service.SuspectService;
import com.i2g.rms.service.UserService;
import com.i2g.rms.service.WitnessService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.incident.IncidentService;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;
import com.i2g.rms.util.ApplicationUtilService;

/**
 * Rest services for password history rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class IncidentRestServiceImpl extends AbstractRestService implements IncidentRestService {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(IncidentRestServiceImpl.class);

	@Autowired
	private IncidentService _incidentService;
	@Autowired
	private UserService _userService;
	@Autowired
	private ApplicationUtilService _applicationUtilService;
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;
	@Autowired
	private SuspectService _suspectService;	
	@Autowired
	private ReportedLossService _reportedLossService;
	@Autowired
	private InjuredPersonService _injuredPersonService;
	@Autowired
	private WitnessService _witnessService;
	@Autowired
	private AccidentService _accidentService;

	@Override
	@Transactional(readOnly = true)
	public List<IncidentRO> get() {
		List<Incident> incidents = _incidentService.get();
		List<IncidentRO> incidentROs = incidents.isEmpty() ? Collections.emptyList()
				: _mapperService.map(incidents, IncidentRO.class);
		return incidentROs;
	}

	@Override
	@Transactional(readOnly = true)
	public IncidentRO getIncidentByUniqueIncidentId(final String uniqueIncidentId) {
		validateUniqueIncidentId(uniqueIncidentId);
		return _mapperService.map(_incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId), IncidentRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO logIncident(final LogIncidentRO logIncidentRO) {
		//Validate input param (object)
		validateObject(logIncidentRO);
				
		//Validate user object from context
		final User user = getUserFromContext();
		validateUserObject(user);
		
		//Set yes or no type fields
		final YesNoType propertyDamage = (logIncidentRO.getPropertyDamage().name().equals("Y")) ? YesNoType.Y : YesNoType.N; 
		final YesNoType criminalAttack = (logIncidentRO.getCriminalAttack().name().equals("Y")) ? YesNoType.Y : YesNoType.N;
		final YesNoType accidentDamage = (logIncidentRO.getAccidentDamage().name().equals("Y")) ? YesNoType.Y : YesNoType.N;
		final YesNoType vehicleOrAssetDamage = (logIncidentRO.getVehicleOrAssetDamage().name().equals("Y")) ? YesNoType.Y : YesNoType.N;
		
		//Construct incident record first with not null columns
		final Incident incident = new Incident.Builder()
									.setUniqueIncidentId(ApplicationUtilService.getUniqueIncidentId())
									.setIncidentReportedBy(user)
									.setIncidentStatus(IncidentStatus.DRAFT)
									.setStatusFlag(StatusFlag.ACTIVE)
									.setPropertyDamage(propertyDamage)
									.setCriminalAttack(criminalAttack)
									.setAccidentDamage(accidentDamage)
									.setVehicleOrAssetDamage(vehicleOrAssetDamage)
									.setIncidentOpenedDateTime(ApplicationUtilService.getCurrentTimestamp())
									.build();
		
		//Validate the newly created object
		validateGenericObject(incident);
		
		//Set other values
		if (logIncidentRO.getPlaceOfIncident() != null && !logIncidentRO.getPlaceOfIncident().trim().isEmpty()) {
			incident.setPlaceOfIncident(logIncidentRO.getPlaceOfIncident());
		}
		if (logIncidentRO.getLandmark() != null && !logIncidentRO.getLandmark().trim().isEmpty()) {
			incident.setLandmark(logIncidentRO.getLandmark());
		}
		if (logIncidentRO.getIncidentDescription() != null && !logIncidentRO.getIncidentDescription().trim().isEmpty()) {
			incident.setIncidentDescription(logIncidentRO.getIncidentDescription());
		}
		
		//Construct other objects
		if (logIncidentRO.getIncidentType() != null) {
			if (logIncidentRO.getIncidentType().getId() != null && !logIncidentRO.getIncidentType().getId().trim().isEmpty()) {
				final IncidentType incidentType = _tableMaintenanceService.getIncidentTypeByCode(logIncidentRO.getIncidentType().getId().trim());
				incident.setIncidentType(incidentType);
			}
		}
		if (logIncidentRO.getEntryPoint() != null) {
			if (logIncidentRO.getEntryPoint().getId() != null && !logIncidentRO.getEntryPoint().getId().trim().isEmpty()) {
				final EntryPoint entryPoint = _tableMaintenanceService.getEntryPointByCode(logIncidentRO.getEntryPoint().getId().trim());
				incident.setEntryPoint(entryPoint);
			}
		}
		//Incident location
		if (logIncidentRO.getIncidentLocation() != null) {
			if (logIncidentRO.getIncidentLocation().getId() != null && !logIncidentRO.getIncidentLocation().getId().trim().isEmpty()) {
				final IncidentLocation incidentLocation = _tableMaintenanceService.getIncidentLocationByCode(logIncidentRO.getIncidentLocation().getId().trim());
				incident.setIncidentLocation(incidentLocation);
			}
		}
		//Incident location detail
		if (logIncidentRO.getIncidentLocationDetail() != null) {
			if (logIncidentRO.getIncidentLocationDetail().getId() != null && !logIncidentRO.getIncidentLocationDetail().getId().trim().isEmpty()) {
				final IncidentLocationDetail incidentLocationDetail = _tableMaintenanceService.getIncidentLocationDetailByCode(logIncidentRO.getIncidentLocationDetail().getId().trim());
				incident.setIncidentLocationDetail(incidentLocationDetail);
			}
		}
		
		//Save the newly constructed incident
		Incident newIncident = _incidentService.logIncident(incident);
		
		if (newIncident != null) {
			return _mapperService.map(newIncident, IncidentRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}

	@Override
	public UserRO addIncident() {
		return _mapperService.map(getUserFromContext(), UserRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addIncidentDetail(final IncidentDetailRO incidentDetailRO) {
		//Validate input param (object)
		validateObject(incidentDetailRO);
		//Validate unique incident id
		validateUniqueIncidentId(incidentDetailRO.getUniqueIncidentId());
		//Construct the incident object for update
		Incident incident = _incidentService.getIncidentByUniqueIncidentId(incidentDetailRO.getUniqueIncidentId().trim());
		validateGenericObject(incident);
		//Instantiate suspect and reported loss objects
		if (incident.getSuspects() == null) {
			incident.setSuspects(new HashSet<Suspect>(0));
		}
		if (incident.getEmployeeSuspects() == null) {
			incident.setEmployeeSuspects(new HashSet<User>(0));
		}
		if (incident.getReportedLosses() == null) {
			incident.setReportedLosses(new HashSet<ReportedLoss>(0));
		}
		
		//Construct suspects and reported losses, if any..
		final List<Suspect> newSuspects = new ArrayList<Suspect>(0);
		final List<Suspect> existingSuspects = new ArrayList<Suspect>(0);
		final List<User> employeeSuspects = new ArrayList<User>(0);
		final List<ReportedLoss> reportedLosses = new ArrayList<ReportedLoss>(0);
		
		//Construction of new suspects
		if (incidentDetailRO.getNewSuspects() != null && !incidentDetailRO.getNewSuspects().isEmpty()) {
			for (SuspectRO suspectRO : incidentDetailRO.getNewSuspects()) {
				Suspect suspect = new Suspect.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
				//Set other values
				if (suspectRO.getTitle() != null && !suspectRO.getTitle().trim().isEmpty()) {
					suspect.setTitle(suspectRO.getTitle().trim());
				}
				if (suspectRO.getFirstName() != null && !suspectRO.getFirstName().trim().isEmpty()) {
					suspect.setFirstName(suspectRO.getFirstName().trim());
				}
				if (suspectRO.getMiddleName() != null && !suspectRO.getMiddleName().trim().isEmpty()) {
					suspect.setMiddleName(suspectRO.getMiddleName().trim());
				}
				if (suspectRO.getLastName() != null && !suspectRO.getLastName().trim().isEmpty()) {
					suspect.setLastName(suspectRO.getLastName().trim());
				}
				if (suspectRO.getNameSuffix() != null && !suspectRO.getNameSuffix().trim().isEmpty()) {
					suspect.setNameSuffix(suspectRO.getNameSuffix().trim());
				}
				if (suspectRO.getPhone() != null && !suspectRO.getPhone().trim().isEmpty()) {
					suspect.setPhone(suspectRO.getPhone().trim());
				}
				if (suspectRO.getFax() != null && !suspectRO.getFax().trim().isEmpty()) {
					suspect.setFax(suspectRO.getFax().trim());
				}
				if (suspectRO.getAlternatePhone() != null && !suspectRO.getAlternatePhone().trim().isEmpty()) {
					suspect.setAlternatePhone(suspectRO.getAlternatePhone().trim());
				}
				if (suspectRO.getEmail() != null && !suspectRO.getEmail().trim().isEmpty()) {
					suspect.setEmail(suspectRO.getEmail().trim());
				}
				if (suspectRO.getWebsite() != null && !suspectRO.getWebsite().trim().isEmpty()) {
					suspect.setWebsite(suspectRO.getWebsite().trim());
				}
				//Gender type
				if (suspectRO.getGenderType() != null) {
					if (suspectRO.getGenderType().getId() != null && !suspectRO.getGenderType().getId().trim().isEmpty()) {
						final GenderType genderType = _tableMaintenanceService.getGenderTypeByCode(suspectRO.getGenderType().getId().trim());
						suspect.setGenderType(genderType);
					}
				}
				//Distinguishing feature
				if (suspectRO.getDistinguishingFeature() != null) {
					if (suspectRO.getDistinguishingFeature().getId() != null && !suspectRO.getDistinguishingFeature().getId().trim().isEmpty()) {
						final DistinguishingFeature distinguishingFeature = _tableMaintenanceService.getDistinguishingFeatureByCode(suspectRO.getDistinguishingFeature().getId().trim());
						suspect.setDistinguishingFeature(distinguishingFeature);
					}
				}
				//Distinguishing feature detail
				if (suspectRO.getDistinguishingFeatureDetail() != null) {
					if (suspectRO.getDistinguishingFeatureDetail().getId() != null && !suspectRO.getDistinguishingFeatureDetail().getId().trim().isEmpty()) {
						final DistinguishingFeatureDetail distinguishingFeatureDetail = _tableMaintenanceService.getDistinguishingFeatureDetailByCode(suspectRO.getDistinguishingFeatureDetail().getId().trim());
						suspect.setDistinguishingFeatureDetail(distinguishingFeatureDetail);
					}
				}
				if (suspectRO.getAge() > 0) {
					suspect.setAge(suspectRO.getAge());
				}
				if (suspectRO.getDateOfBirth() != null) {
					suspect.setDateOfBirth(suspectRO.getDateOfBirth());
				}
				//Was there a weapon involved?
				YesNoType weaponInvolved = YesNoType.N;
				if (suspectRO.getWeaponInvolved() != null && suspectRO.getWeaponInvolved().name().equals("Y")) {
					weaponInvolved = YesNoType.Y;					
				}				
				//Weapon type
				WeaponType weaponType = null;
				if (suspectRO.getWeaponType() != null) {
					if (suspectRO.getWeaponType().getId() != null && !suspectRO.getWeaponType().getId().trim().isEmpty()) {
						weaponType = _tableMaintenanceService.getWeaponTypeByCode(suspectRO.getWeaponType().getId().trim());						
					}
				}
				validateWeaponInvolvedAndType(weaponInvolved, weaponType);
				suspect.setWeaponInvolved(weaponInvolved);
				suspect.setWeaponType(weaponType);				
				//Suspect Type
				if (suspectRO.getSuspectType() != null) {
					if (suspectRO.getSuspectType().getId() != null && !suspectRO.getSuspectType().getId().trim().isEmpty()) {
						final SuspectType suspectType = _tableMaintenanceService.getSuspectTypeByCode(suspectRO.getSuspectType().getId().trim());
						suspect.setSuspectType(suspectType);
					}
				}
				//Address
				if (suspectRO.getAddresses() != null && !suspectRO.getAddresses().isEmpty()) {
					
					Set<Address> addresses = new HashSet<Address>(0);
					
					for (AddressRO addressRO : suspectRO.getAddresses()) {
						final Address address = new Address.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
						//Create the link between this new address and suspect.
						address.setSuspect(suspect);
						if (addressRO.getOrganizationName() != null && !addressRO.getOrganizationName().trim().isEmpty()) {
							address.setOrganizationName(addressRO.getOrganizationName().trim());							
						}
						if (addressRO.getBuildingName() != null && !addressRO.getBuildingName().trim().isEmpty()) {
							address.setBuildingName(addressRO.getBuildingName().trim());			
						}
						if (addressRO.getStreetName() != null && !addressRO.getStreetName().trim().isEmpty()) {
							address.setStreetName(addressRO.getStreetName().trim());							
						}
						if (addressRO.getLocalityName() != null && !addressRO.getLocalityName().trim().isEmpty()) {
							address.setLocalityName(addressRO.getLocalityName().trim());							
						}
						if (addressRO.getPostTown() != null && !addressRO.getPostTown().trim().isEmpty()) {
							address.setPostTown(addressRO.getPostTown().trim());						
						}
						if (addressRO.getCounty() != null && !addressRO.getCounty().trim().isEmpty()) {
							address.setCounty(addressRO.getCounty().trim());				
						}
						if (addressRO.getCity() != null && !addressRO.getCity().trim().isEmpty()) {
							address.setCity(addressRO.getCity().trim());				
						}
						if (addressRO.getPostcode() != null && !addressRO.getPostcode().trim().isEmpty()) {
							address.setPostcode(addressRO.getPostcode().trim());							
						}
						if (addressRO.getCountry() != null && !addressRO.getCountry().trim().isEmpty()) {
							address.setCountry(addressRO.getCountry().trim());				
						}
						addresses.add(address);
					}					
					suspect.setAddresses(addresses);
				}
				newSuspects.add(suspect);
			}
		}
		//Create and add new suspects to the main incident.
		if (newSuspects != null && !newSuspects.isEmpty()) {
			Set<Suspect> newlyCreatedSuspects = _suspectService.createNewSuspects(newSuspects);
			if (newlyCreatedSuspects != null && !newlyCreatedSuspects.isEmpty()) {
				//Add newly created suspects to main incident
				incident.getSuspects().addAll(newlyCreatedSuspects);												
			}
		}
		
		//Construction of existing suspects
		if (incidentDetailRO.getExistingSuspects() != null && !incidentDetailRO.getExistingSuspects().isEmpty()) {
			for (SuspectRO suspectRO : incidentDetailRO.getExistingSuspects()) {
				if (suspectRO.getId() > 0) {
					existingSuspects.add(_suspectService.get(suspectRO.getId()));
				}
			}
			//Add existing suspects to main incident
			incident.getSuspects().addAll(new HashSet<Suspect>(existingSuspects));
		}
		
		//Construction of employee suspects
		if (incidentDetailRO.getEmployeeSuspects() != null && !incidentDetailRO.getEmployeeSuspects().isEmpty()) {
			for (UserRO userRO : incidentDetailRO.getEmployeeSuspects()) {
				if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty()) {
					employeeSuspects.add(_userService.getUserByUserLoginId(userRO.getLoginId().trim()));
				}
			}
			incident.getEmployeeSuspects().addAll(new HashSet<User>(employeeSuspects));
		}
		
		//Construction of reported loss
		if (incidentDetailRO.getReportedLosses() != null && !incidentDetailRO.getReportedLosses().isEmpty()) {
			for (ReportedLossRO reportedLossRO: incidentDetailRO.getReportedLosses()) {				
				final ReportedLoss reportedLoss = new ReportedLoss.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
				//Assign the main incident id, because we are reporting loss to an incident.
				reportedLoss.setIncident(incident);				
				//Loss type
				if (reportedLossRO.getLossType() != null) {
					if (reportedLossRO.getLossType().getId() != null && !reportedLossRO.getLossType().getId().trim().isEmpty()) {
						final LossType lossType = _tableMaintenanceService.getLossTypeByCode(reportedLossRO.getLossType().getId().trim());
						reportedLoss.setLossType(lossType);
					}
				}
				//Loss value
				if (reportedLossRO.getLossValue() != null) {
					reportedLoss.setLossValue(reportedLossRO.getLossValue());
				}
				//External agency contacted flag
				YesNoType externalAgencyContacted = YesNoType.N;
				if (reportedLossRO.getExternalAgencyContacted() != null && reportedLossRO.getExternalAgencyContacted().name().equals("Y")) {
					externalAgencyContacted = YesNoType.Y;
				}
				//External agency
				ExternalAgency externalAgency = null;
				if (reportedLossRO.getExternalAgency() != null) {
					if (reportedLossRO.getExternalAgency().getId() != null && !reportedLossRO.getExternalAgency().getId().trim().isEmpty()) {
						externalAgency = _tableMaintenanceService.getExternalAgencyByCode(reportedLossRO.getExternalAgency().getId().trim());
					}					
				}
				//Date-time contacted
				LocalDateTime dateTimeContacted = null;
				if (reportedLossRO.getDateTimeContacted() != null) {
					dateTimeContacted = reportedLossRO.getDateTimeContacted();
				}				
				validateExternalAgencyAndType(externalAgencyContacted, externalAgency, dateTimeContacted);
				reportedLoss.setExternalAgencyContacted(externalAgencyContacted);
				reportedLoss.setExternalAgency(externalAgency);
				reportedLoss.setDateTimeContacted(dateTimeContacted);
				
				//Cost estimation
				if (reportedLossRO.getCostEstimation() != null) {
					reportedLoss.setCostEstimation(reportedLossRO.getCostEstimation());
				}
				reportedLosses.add(reportedLoss);
			}
		}
		//Create the reported losses in database and add it to the main incident. 
		if (reportedLosses != null && !reportedLosses.isEmpty()) {
			Set<ReportedLoss> newlyCreatedReportedLosses = _reportedLossService.createNewReportedLosses(reportedLosses);
			if (newlyCreatedReportedLosses != null && !newlyCreatedReportedLosses.isEmpty()) {
				//Add newly created reported losses to main incident
				incident.getReportedLosses().addAll(newlyCreatedReportedLosses);										
			}
		}
		
		//Update the incident with new suspects, existing suspects, employee suspects and reported losses..
		final Incident updatedIncident = _incidentService.updateIncident(incident);
		
		if (updatedIncident != null) {
			return _mapperService.map(updatedIncident, IncidentRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}
	
	private void validateWeaponInvolvedAndType(final YesNoType weaponInvolved, final WeaponType weaponType) {
		if (weaponInvolved.name().equals(YesNoType.Y)) {
			//When there was a weapon involved, weapon type should be specified.
			if (weaponType == null) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.WEAPON_TYPE_MUST_BE_SPECIFIED_WHEN_WEAPON_INVOLVED));
			}
		} else if (weaponInvolved.name().equals(YesNoType.N)) {
			//Weapon type is only applicable if there was any weapon involved.
			if (weaponType != null) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.WEAPON_TYPE_NOT_APPLICABLE_WITHOUT_WEAPON_INVOLVED));
			}
		}
	}
	
	private void validateExternalAgencyAndType(final YesNoType externalAgencyContacted, final ExternalAgency externalAgency, final LocalDateTime dateTimeContacted) {
		if (externalAgencyContacted.name().equals(YesNoType.Y)) {
			//External agency should be specified when an agency was contacted
			if (externalAgency == null) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.EXTERNAL_AGENCY_MUST_BE_SPECIFIED_WHEN_AGENCY_CONTACTED));
			}
			if (dateTimeContacted == null) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.EXTERNAL_AGENCY_DATE_TIME_MUST_BE_SPECIFIED_WHEN_AGENCY_CONTACTED));
			}
		} else if (externalAgencyContacted.name().equals(YesNoType.N)) {
			//External agency is only applicable if there was any external agency contacted.
			if (externalAgency != null) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.EXTERNAL_AGENCY_NOT_APPLICABLE_WITHOUT_AGENCY_CONTACTED));
			}
			if (dateTimeContacted != null) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.EXTERNAL_AGENCY_DATE_TIME_NOT_APPLICABLE_WITHOUT_AGENCY_CONTACTED));
			}
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addAccidentDetail(final AccidentDetailRO accidentDetailRO) {
		//Validate input param (object)
		validateObject(accidentDetailRO);
		//Validate input main object accident.
		validateObject(accidentDetailRO.getAccident());		
		//Validate unique incident id
		validateUniqueIncidentId(accidentDetailRO.getUniqueIncidentId());
		//Construct the incident object for update
		Incident incident = _incidentService.getIncidentByUniqueIncidentId(accidentDetailRO.getUniqueIncidentId().trim());
		validateGenericObject(incident);
		
		//Instantiate the accident object
		Accident accident = new Accident.Builder()
				.setStatusFlag(StatusFlag.ACTIVE)
				.setIncident(incident)
				.build();
		validateGenericObject(accident);		
		
		//Instantiate objects
		if (accident.getInjuredPersons() == null) {
			accident.setInjuredPersons(new HashSet<InjuredPerson>(0));
		}
		if (accident.getEmployeeInjuredPersons() == null) {
			accident.setEmployeeInjuredPersons(new HashSet<User>(0));
		}
		if (accident.getWitnesses() == null) {
			accident.setWitnesses(new HashSet<Witness>(0));
		}		
		if (accident.getEmployeeWitnesses() == null) {
			accident.setEmployeeWitnesses(new HashSet<User>(0));
		}
		
		//Holder variables for newly created objects
		final List<InjuredPerson> newInjuredPersons = new ArrayList<InjuredPerson>(0);
		final List<InjuredPerson> existingInjuredPersons = new ArrayList<InjuredPerson>(0);
		final List<User> employeeInjuredPersons = new ArrayList<User>(0);		
		final List<Witness> newWitnesses = new ArrayList<Witness>(0);
		final List<Witness> existingWitnesses = new ArrayList<Witness>(0);
		final List<User> employeeWitnesses = new ArrayList<User>(0);
		
		//Construct the new accident object
		if (accidentDetailRO.getAccident() != null) {
			//Accident Description
			if (accidentDetailRO.getAccident().getAccidentDescription() != null && !accidentDetailRO.getAccident().getAccidentDescription().trim().isEmpty()) {
				accident.setAccidentDescription(accidentDetailRO.getAccident().getAccidentDescription().trim());
			}
			//Accident date and time
			if (accidentDetailRO.getAccident().getAccidentDateTime() != null) {
				accident.setAccidentDateTime(accidentDetailRO.getAccident().getAccidentDateTime());
			}
			//landmark
			if (accidentDetailRO.getAccident().getLandmark() != null && !accidentDetailRO.getAccident().getLandmark().trim().isEmpty()) {
				accident.setLandmark(accidentDetailRO.getAccident().getLandmark().trim());
			}
			//place of accident
			if (accidentDetailRO.getAccident().getAccidentPlace() != null && !accidentDetailRO.getAccident().getAccidentPlace().trim().isEmpty()) {
				accident.setAccidentPlace(accidentDetailRO.getAccident().getAccidentPlace().trim());
			}
			//Accident type
			if (accidentDetailRO.getAccident().getAccidentType() != null) {
				if (accidentDetailRO.getAccident().getAccidentType().getId() != null && !accidentDetailRO.getAccident().getAccidentType().getId().trim().isEmpty()) {
					final AccidentType accidentType = _tableMaintenanceService.getAccidentTypeByCode(accidentDetailRO.getAccident().getAccidentType().getId().trim());
					accident.setAccidentType(accidentType);					
				}				
			}
			//Set accident location
			if (accidentDetailRO.getAccident().getAccidentLocation() != null) {
				if (accidentDetailRO.getAccident().getAccidentLocation().getId() != null && !accidentDetailRO.getAccident().getAccidentLocation().getId().trim().isEmpty()) {
					final AccidentLocation accidentLocation = _tableMaintenanceService.getAccidentLocationByCode(accidentDetailRO.getAccident().getAccidentLocation().getId().trim());
					accident.setAccidentLocation(accidentLocation);
				}
			}
			//set accident location detail
			if (accidentDetailRO.getAccident().getAccidentLocationDetails() != null) {
				if (accidentDetailRO.getAccident().getAccidentLocationDetails().getId() != null && !accidentDetailRO.getAccident().getAccidentLocationDetails().getId().trim().isEmpty()) {
					final AccidentLocationDetail accidentLocationDetail = _tableMaintenanceService.getAccidentLocationDetailByCode(accidentDetailRO.getAccident().getAccidentLocationDetails().getId().trim());
					accident.setAccidentLocationDetails(accidentLocationDetail);							
				}
			}
			//Start of construction of injured persons
			//Construct (new) injured person(s) if any
			if (accidentDetailRO.getNewInjuredPersons() != null && !accidentDetailRO.getNewInjuredPersons().isEmpty()) {
				for (InjuredPersonRO injuredPersonRO : accidentDetailRO.getNewInjuredPersons()) {
					InjuredPerson injuredPerson = new InjuredPerson.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
					//Set other values for (new) injured person(s)
					//Injured person type
					if (injuredPersonRO.getInjuredPersonType() != null) {
						if (injuredPersonRO.getInjuredPersonType().getId() != null && !injuredPersonRO.getInjuredPersonType().getId().trim().isEmpty()) {
							InjuredPersonType injuredPersonType = _tableMaintenanceService.getInjuredPersonTypeByCode(injuredPersonRO.getInjuredPersonType().getId().trim());
							injuredPerson.setInjuredPersonType(injuredPersonType);
						}
					}
					if (injuredPersonRO.getTitle() != null && !injuredPersonRO.getTitle().trim().isEmpty()) {
						injuredPerson.setTitle(injuredPersonRO.getTitle().trim());
					}
					if (injuredPersonRO.getFirstName() != null && !injuredPersonRO.getFirstName().trim().isEmpty()) {
						injuredPerson.setFirstName(injuredPersonRO.getFirstName().trim());
					}
					if (injuredPersonRO.getMiddleName() != null && !injuredPersonRO.getMiddleName().trim().isEmpty()) {
						injuredPerson.setMiddleName(injuredPersonRO.getMiddleName().trim());
					}
					if (injuredPersonRO.getLastName() != null && !injuredPersonRO.getLastName().trim().isEmpty()) {
						injuredPerson.setLastName(injuredPersonRO.getLastName().trim());
					}
					if (injuredPersonRO.getNameSuffix() != null && !injuredPersonRO.getNameSuffix().trim().isEmpty()) {
						injuredPerson.setNameSuffix(injuredPersonRO.getNameSuffix());
					}
					if (injuredPersonRO.getPhone() != null && !injuredPersonRO.getPhone().trim().isEmpty()) {
						injuredPerson.setPhone(injuredPersonRO.getPhone().trim());
					}
					if (injuredPersonRO.getFax() != null && !injuredPersonRO.getFax().trim().isEmpty()) {
						injuredPerson.setFax(injuredPersonRO.getFax().trim());
					}
					if (injuredPersonRO.getAlternatePhone() != null && !injuredPersonRO.getAlternatePhone().trim().isEmpty()) {
						injuredPerson.setAlternatePhone(injuredPersonRO.getAlternatePhone().trim());
					}
					if (injuredPersonRO.getEmail() != null && !injuredPersonRO.getEmail().trim().isEmpty()) {
						injuredPerson.setEmail(injuredPersonRO.getEmail().trim());
					}
					if (injuredPersonRO.getWebsite() != null && !injuredPersonRO.getWebsite().trim().isEmpty()) {
						injuredPerson.setWebsite(injuredPersonRO.getWebsite().trim());
					}
					//Gender type
					if (injuredPersonRO.getGenderType() != null) {
						if (injuredPersonRO.getGenderType().getId() != null && !injuredPersonRO.getGenderType().getId().trim().isEmpty()) {
							final GenderType genderType = _tableMaintenanceService.getGenderTypeByCode(injuredPersonRO.getGenderType().getId().trim());
							injuredPerson.setGenderType(genderType);
						}
					}
					//Distinguishing feature
					if (injuredPersonRO.getDistinguishingFeature() != null) {
						if (injuredPersonRO.getDistinguishingFeature().getId() != null && !injuredPersonRO.getDistinguishingFeature().getId().trim().isEmpty()) {
							final DistinguishingFeature distinguishingFeature = _tableMaintenanceService.getDistinguishingFeatureByCode(injuredPersonRO.getDistinguishingFeature().getId().trim());
							injuredPerson.setDistinguishingFeature(distinguishingFeature);
						}
					}
					//Distinguishing feature detail
					if (injuredPersonRO.getDistinguishingFeatureDetail() != null) {
						if (injuredPersonRO.getDistinguishingFeatureDetail().getId() != null && !injuredPersonRO.getDistinguishingFeatureDetail().getId().trim().isEmpty()) {
							final DistinguishingFeatureDetail distinguishingFeatureDetail = _tableMaintenanceService.getDistinguishingFeatureDetailByCode(injuredPersonRO.getDistinguishingFeatureDetail().getId().trim());
							injuredPerson.setDistinguishingFeatureDetail(distinguishingFeatureDetail);
						}
					}
					//Injury Cause
					if (injuredPersonRO.getInjuryCause() != null) {
						if (injuredPersonRO.getInjuryCause().getId() != null && !injuredPersonRO.getInjuryCause().getId().trim().isEmpty()) {
							final InjuryCause injuryCause = _tableMaintenanceService.getInjuryCauseByCode(injuredPersonRO.getInjuryCause().getId().trim());
							injuredPerson.setInjuryCause(injuryCause);
						}
					}
					//Injury type
					if (injuredPersonRO.getInjuryType() != null) {
						if (injuredPersonRO.getInjuryType().getId() != null && !injuredPersonRO.getInjuryType().getId().trim().isEmpty()) {
							final InjuryType injuryType = _tableMaintenanceService.getInjuryTypeByCode(injuredPersonRO.getInjuryType().getId().trim());
							injuredPerson.setInjuryType(injuryType);
						}
					}
					//Injury type detail
					if (injuredPersonRO.getInjuryTypeDetail() != null) {
						if (injuredPersonRO.getInjuryTypeDetail().getId() != null && !injuredPersonRO.getInjuryTypeDetail().getId().trim().isEmpty()) {
							final InjuryTypeDetail injuryTypeDetail = _tableMaintenanceService.getInjuryTypeDetailByCode(injuredPersonRO.getInjuryTypeDetail().getId().trim());
							injuredPerson.setInjuryTypeDetail(injuryTypeDetail);
						}
					}					
					//Injury type detail spec
					if (injuredPersonRO.getInjuryTypeDetailSpec() != null) {
						if (injuredPersonRO.getInjuryTypeDetailSpec().getId() != null && !injuredPersonRO.getInjuryTypeDetailSpec().getId().trim().isEmpty()) {
							final InjuryTypeDetailSpec injuryTypeDetailSpec =  _tableMaintenanceService.getInjuryTypeDetailSpecByCode(injuredPersonRO.getInjuryTypeDetailSpec().getId().trim());
							injuredPerson.setInjuryTypeDetailSpec(injuryTypeDetailSpec);							
						}
					}
					//Age
					if (injuredPersonRO.getAge() > 0) {
						injuredPerson.setAge(injuredPersonRO.getAge());
					}
					//Date of Birth
					if (injuredPersonRO.getDateOfBirth() != null) {
						injuredPerson.setDateOfBirth(injuredPersonRO.getDateOfBirth());
					}
					//Any first aid given?
					YesNoType firstAidGiven = YesNoType.N;
					if (injuredPersonRO.getFirstAidGiven() != null && injuredPersonRO.getFirstAidGiven().name().equals("Y")) {
						firstAidGiven = YesNoType.Y;
					}
					injuredPerson.setFirstAidGiven(firstAidGiven);
					//Any witness?
					YesNoType anyWitness = YesNoType.N;
					if (injuredPersonRO.getAnyWitness() != null && injuredPersonRO.getAnyWitness().name().equals("Y")) {
						anyWitness = YesNoType.Y;
					}
					injuredPerson.setAnyWitness(anyWitness);
					//Address of the injured person
					if (injuredPersonRO.getAddresses() != null && !injuredPersonRO.getAddresses().isEmpty()) {
						
						Set<Address> addresses = new HashSet<Address>(0);						
						
						for (AddressRO addressRO : injuredPersonRO.getAddresses()) {
							final Address address = new Address.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
							//Create the link between this new address and injured person.
							address.setInjuredPerson(injuredPerson);
							
							if (addressRO.getOrganizationName() != null && !addressRO.getOrganizationName().trim().isEmpty()) {
								address.setOrganizationName(addressRO.getOrganizationName().trim());					
							}
							if (addressRO.getBuildingName() != null && !addressRO.getBuildingName().trim().isEmpty()) {
								address.setBuildingName(addressRO.getBuildingName().trim());							
							}
							if (addressRO.getStreetName() != null && !addressRO.getStreetName().trim().isEmpty()) {
								address.setStreetName(addressRO.getStreetName().trim());							
							}
							if (addressRO.getLocalityName() != null && !addressRO.getLocalityName().trim().isEmpty()) {
								address.setLocalityName(addressRO.getLocalityName().trim());							
							}
							if (addressRO.getPostTown() != null && !addressRO.getPostTown().trim().isEmpty()) {
								address.setPostTown(addressRO.getPostTown().trim());							
							}
							if (addressRO.getCounty() != null && !addressRO.getCounty().trim().isEmpty()) {
								address.setCounty(addressRO.getCounty().trim());							
							}
							if (addressRO.getCity() != null && !addressRO.getCity().trim().isEmpty()) {
								address.setCity(addressRO.getCity().trim());							
							}
							if (addressRO.getPostcode() != null && !addressRO.getPostcode().trim().isEmpty()) {
								address.setPostcode(addressRO.getPostcode().trim());							
							}
							if (addressRO.getCountry() != null && !addressRO.getCountry().trim().isEmpty()) {
								address.setCountry(addressRO.getCountry().trim());							
							}
							addresses.add(address);
						}			
						injuredPerson.setAddresses(addresses);
					}
					//add the injured person to the list
					newInjuredPersons.add(injuredPerson);					
				}
			}
			//Save new injured persons to database and assign them to accident record.
			if (newInjuredPersons != null && !newInjuredPersons.isEmpty()) {
				Set<InjuredPerson> newlyCreatedInjuredPersons = _injuredPersonService.createNewInjuredPersons(newInjuredPersons);
				if (newlyCreatedInjuredPersons != null && !newlyCreatedInjuredPersons.isEmpty()) {
					accident.getInjuredPersons().addAll(newlyCreatedInjuredPersons);
				}
			}
			//Construct and add existing injured persons if any..
			if (accidentDetailRO.getExistingInjuredPersons() != null && !accidentDetailRO.getExistingInjuredPersons().isEmpty()) {
				for (InjuredPersonRO injuredPersonRO : accidentDetailRO.getExistingInjuredPersons()) {
					if (injuredPersonRO.getId() > 0) {
						existingInjuredPersons.add(_injuredPersonService.get(injuredPersonRO.getId()));
					}
				}
				//Add existing suspects to accident
				accident.getInjuredPersons().addAll(new HashSet<InjuredPerson>(existingInjuredPersons));
			}
			//Construct employee injured persons and add to accident.
			if (accidentDetailRO.getEmployeeInjuredPersons() != null && !accidentDetailRO.getEmployeeInjuredPersons().isEmpty()) {
				for (UserRO userRO : accidentDetailRO.getEmployeeInjuredPersons()) {
					if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty()) {
						employeeInjuredPersons.add(_userService.getUserByUserLoginId(userRO.getLoginId().trim()));
					}
				}
				accident.getEmployeeInjuredPersons().addAll(new HashSet<User>(employeeInjuredPersons));				
			}
			//End of construction of injured persons
			
			//Start of witness construction
			//Construct new witnesses and add to accident.			
			if (accidentDetailRO.getNewWitnesses() != null && !accidentDetailRO.getNewWitnesses().isEmpty()) {
				for (WitnessRO witnessRO : accidentDetailRO.getNewWitnesses()) {
					final Witness witness = new Witness.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
					//Set other values for (new) witnesses
					//Witness type
					if (witnessRO.getWitnessType() != null) {
						if (witnessRO.getWitnessType().getId() != null && !witnessRO.getWitnessType().getId().trim().isEmpty()) {
							WitnessType witnessType = _tableMaintenanceService.getWitnessTypeByCode(witnessRO.getWitnessType().getId().trim());
							witness.setWitnessType(witnessType);
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
					//Gender type
					if (witnessRO.getGenderType() != null) {
						if (witnessRO.getGenderType().getId() != null && !witnessRO.getGenderType().getId().trim().isEmpty()) {
							final GenderType genderType = _tableMaintenanceService.getGenderTypeByCode(witnessRO.getGenderType().getId().trim());
							witness.setGenderType(genderType);
						}
					}
					//Distinguishing feature
					if (witnessRO.getDistinguishingFeature() != null) {
						if (witnessRO.getDistinguishingFeature().getId() != null && !witnessRO.getDistinguishingFeature().getId().trim().isEmpty()) {
							final DistinguishingFeature distinguishingFeature = _tableMaintenanceService.getDistinguishingFeatureByCode(witnessRO.getDistinguishingFeature().getId().trim());
							witness.setDistinguishingFeature(distinguishingFeature);
						}
					}
					//Distinguishing feature detail
					if (witnessRO.getDistinguishingFeatureDetail() != null) {
						if (witnessRO.getDistinguishingFeatureDetail().getId() != null && !witnessRO.getDistinguishingFeatureDetail().getId().trim().isEmpty()) {
							final DistinguishingFeatureDetail distinguishingFeatureDetail = _tableMaintenanceService.getDistinguishingFeatureDetailByCode(witnessRO.getDistinguishingFeatureDetail().getId().trim());
							witness.setDistinguishingFeatureDetail(distinguishingFeatureDetail);
						}
					}
					//Age
					if (witnessRO.getAge() > 0) {
						witness.setAge(witnessRO.getAge());
					}
					//Date of Birth
					if (witnessRO.getDateOfBirth() != null) {
						witness.setDateOfBirth(witnessRO.getDateOfBirth());
					}
					//Address of the witness person
					if (witnessRO.getAddresses() != null && !witnessRO.getAddresses().isEmpty()) {
						
						Set<Address> addresses = new HashSet<Address>(0);						
						
						for (AddressRO addressRO : witnessRO.getAddresses()) {
							final Address address = new Address.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
							//Create the link between this new address and witness.
							address.setWitness(witness);
							
							if (addressRO.getOrganizationName() != null && !addressRO.getOrganizationName().trim().isEmpty()) {
								address.setOrganizationName(addressRO.getOrganizationName().trim());					
							}
							if (addressRO.getBuildingName() != null && !addressRO.getBuildingName().trim().isEmpty()) {
								address.setBuildingName(addressRO.getBuildingName().trim());							
							}
							if (addressRO.getStreetName() != null && !addressRO.getStreetName().trim().isEmpty()) {
								address.setStreetName(addressRO.getStreetName().trim());							
							}
							if (addressRO.getLocalityName() != null && !addressRO.getLocalityName().trim().isEmpty()) {
								address.setLocalityName(addressRO.getLocalityName().trim());							
							}
							if (addressRO.getPostTown() != null && !addressRO.getPostTown().trim().isEmpty()) {
								address.setPostTown(addressRO.getPostTown().trim());							
							}
							if (addressRO.getCounty() != null && !addressRO.getCounty().trim().isEmpty()) {
								address.setCounty(addressRO.getCounty().trim());							
							}
							if (addressRO.getCity() != null && !addressRO.getCity().trim().isEmpty()) {
								address.setCity(addressRO.getCity().trim());							
							}
							if (addressRO.getPostcode() != null && !addressRO.getPostcode().trim().isEmpty()) {
								address.setPostcode(addressRO.getPostcode().trim());							
							}
							if (addressRO.getCountry() != null && !addressRO.getCountry().trim().isEmpty()) {
								address.setCountry(addressRO.getCountry().trim());							
							}
							addresses.add(address);
						}			
						witness.setAddresses(addresses);
					}
					newWitnesses.add(witness);					
				}
			}
			//create the new witness in the backend and add it to the accident.
			if (newWitnesses != null && !newWitnesses.isEmpty()) {
				Set<Witness> newlyCreatedWitnesses = _witnessService.createNewWitnesses(newWitnesses);
				if (newlyCreatedWitnesses != null && !newlyCreatedWitnesses.isEmpty()) {
					//Add newly created suspects to main accident
					accident.getWitnesses().addAll(newlyCreatedWitnesses);										
				}
			}
			//construction of existing witnesses
			if (accidentDetailRO.getExistingWitnesses() != null && !accidentDetailRO.getExistingWitnesses().isEmpty()) {
				for (WitnessRO witnessRO : accidentDetailRO.getExistingWitnesses()) {
					if (witnessRO.getId() > 0) {
						existingWitnesses.add(_witnessService.get(witnessRO.getId()));
					}
				}
				//add existing witness to the accident.
				accident.getWitnesses().addAll(new HashSet<Witness>(existingWitnesses));
			}
			//construction of employee witness if any and add to accident.
			if (accidentDetailRO.getEmployeeWitnesses() != null && !accidentDetailRO.getEmployeeWitnesses().isEmpty()) {
				for (UserRO userRO : accidentDetailRO.getEmployeeWitnesses()) {
					if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty()) {
						employeeWitnesses.add(_userService.getUserByUserLoginId(userRO.getLoginId().trim()));
					}
				}
				accident.getEmployeeWitnesses().addAll(new HashSet<User>(employeeWitnesses));
			}
			//end of construction of witnesses
			
			//Create the accident record
			final Accident newAccident = _accidentService.create(accident);
			//Throw exception if unable to create
			if (newAccident == null) {
				throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
			} else {
				//Set the accident to incident.
				incident.setAccident(accident);
			}
		}
		//Throw the incident back with the newly created accident details
		return _mapperService.map(incident, IncidentRO.class);		
	}
}
