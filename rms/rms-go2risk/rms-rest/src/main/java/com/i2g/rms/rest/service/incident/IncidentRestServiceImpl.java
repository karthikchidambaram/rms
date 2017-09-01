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

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.domain.model.ReportedLoss;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.YesNoType;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.incident.IncidentStatus;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeature;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.domain.model.tablemaintenance.EntryPoint;
import com.i2g.rms.domain.model.tablemaintenance.ExternalAgency;
import com.i2g.rms.domain.model.tablemaintenance.GenderType;
import com.i2g.rms.domain.model.tablemaintenance.IncidentLocation;
import com.i2g.rms.domain.model.tablemaintenance.IncidentLocationDetail;
import com.i2g.rms.domain.model.tablemaintenance.IncidentType;
import com.i2g.rms.domain.model.tablemaintenance.LossType;
import com.i2g.rms.domain.model.tablemaintenance.SuspectType;
import com.i2g.rms.domain.model.tablemaintenance.WeaponType;
import com.i2g.rms.rest.model.AddressRO;
import com.i2g.rms.rest.model.ReportedLossRO;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.incident.IncidentDetailRO;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.incident.LogIncidentRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.rest.service.RestMessage;
import com.i2g.rms.service.ReportedLossService;
import com.i2g.rms.service.SuspectService;
import com.i2g.rms.service.UserService;
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
		if (logIncidentRO.getIncidentLocation() != null) {
			if (logIncidentRO.getIncidentLocation().getId() != null && !logIncidentRO.getIncidentLocation().getId().trim().isEmpty()) {
				final IncidentLocation incidentLocation = _tableMaintenanceService.getIncidentLocationByCode(logIncidentRO.getIncidentLocation().getId().trim());
				if (logIncidentRO.getIncidentLocationDetail() != null) {
					if (logIncidentRO.getIncidentLocationDetail().getId() != null && !logIncidentRO.getIncidentLocationDetail().getId().trim().isEmpty()) {
						final IncidentLocationDetail incidentLocationDetail = _tableMaintenanceService.getIncidentLocationDetailByCode(logIncidentRO.getIncidentLocationDetail().getId().trim());
						incidentLocationDetail.setIncidentLocation(incidentLocation);
						incident.setIncidentLocationDetails(incidentLocationDetail);
					}
				}
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
					suspect.setTitle(suspectRO.getTitle());
				}
				if (suspectRO.getFirstName() != null && !suspectRO.getFirstName().trim().isEmpty()) {
					suspect.setFirstName(suspectRO.getFirstName());
				}
				if (suspectRO.getMiddleName() != null && !suspectRO.getMiddleName().trim().isEmpty()) {
					suspect.setMiddleName(suspectRO.getMiddleName());
				}
				if (suspectRO.getLastName() != null && !suspectRO.getLastName().trim().isEmpty()) {
					suspect.setLastName(suspectRO.getLastName());
				}
				if (suspectRO.getNameSuffix() != null && !suspectRO.getNameSuffix().trim().isEmpty()) {
					suspect.setNameSuffix(suspectRO.getNameSuffix());
				}
				if (suspectRO.getPhone() != null && !suspectRO.getPhone().trim().isEmpty()) {
					suspect.setPhone(suspectRO.getPhone());
				}
				if (suspectRO.getFax() != null && !suspectRO.getFax().trim().isEmpty()) {
					suspect.setFax(suspectRO.getFax());
				}
				if (suspectRO.getAlternatePhone() != null && !suspectRO.getAlternatePhone().trim().isEmpty()) {
					suspect.setAlternatePhone(suspectRO.getAlternatePhone());
				}
				if (suspectRO.getEmail() != null && !suspectRO.getEmail().trim().isEmpty()) {
					suspect.setEmail(suspectRO.getEmail());
				}
				if (suspectRO.getWebsite() != null && !suspectRO.getWebsite().trim().isEmpty()) {
					suspect.setWebsite(suspectRO.getWebsite());
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
						if (suspectRO.getDistinguishingFeatureDetail() != null) {
							if (suspectRO.getDistinguishingFeatureDetail().getId() != null && !suspectRO.getDistinguishingFeatureDetail().getId().trim().isEmpty()) {
								final DistinguishingFeatureDetail distinguishingFeatureDetail = _tableMaintenanceService.getDistinguishingFeatureDetailByCode(suspectRO.getDistinguishingFeatureDetail().getId().trim());
								distinguishingFeatureDetail.setDistinguishingFeature(distinguishingFeature);
								suspect.setDistinguishingFeatureDetail(distinguishingFeatureDetail);
							}
						}
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
				if (suspectRO.getAddress() != null && !suspectRO.getAddress().isEmpty()) {
					
					Set<Address> addresses = new HashSet<Address>(0);
					
					for (AddressRO addressRO : suspectRO.getAddress()) {
						final Address address = new Address.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
						//Create the link between this new address and suspect.
						address.setSuspect(suspect);
						if (addressRO.getOrganizationName() != null && !addressRO.getOrganizationName().trim().isEmpty()) {
							address.setOrganizationName(addressRO.getOrganizationName());							
						}
						if (addressRO.getBuildingName() != null && !addressRO.getBuildingName().trim().isEmpty()) {
							address.setBuildingName(addressRO.getBuildingName());							
						}
						if (addressRO.getStreetName() != null && !addressRO.getStreetName().trim().isEmpty()) {
							address.setStreetName(addressRO.getStreetName());							
						}
						if (addressRO.getLocalityName() != null && !addressRO.getLocalityName().trim().isEmpty()) {
							address.setLocalityName(addressRO.getLocalityName());							
						}
						if (addressRO.getPostTown() != null && !addressRO.getPostTown().trim().isEmpty()) {
							address.setPostTown(addressRO.getPostTown());							
						}
						if (addressRO.getCounty() != null && !addressRO.getCounty().trim().isEmpty()) {
							address.setCounty(addressRO.getCounty());							
						}
						if (addressRO.getCity() != null && !addressRO.getCity().trim().isEmpty()) {
							address.setCity(addressRO.getCity());							
						}
						if (addressRO.getPostcode() != null && !addressRO.getPostcode().trim().isEmpty()) {
							address.setPostcode(addressRO.getPostcode());							
						}
						if (addressRO.getCountry() != null && !addressRO.getCountry().trim().isEmpty()) {
							address.setCountry(addressRO.getCountry());							
						}
						addresses.add(address);
					}					
					suspect.setAddress(addresses);
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
}
