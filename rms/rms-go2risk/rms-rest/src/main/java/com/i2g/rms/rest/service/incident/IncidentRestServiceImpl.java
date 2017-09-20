package com.i2g.rms.rest.service.incident;

import java.time.LocalDateTime;
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
import com.i2g.rms.domain.model.Asset;
import com.i2g.rms.domain.model.Building;
import com.i2g.rms.domain.model.Crime;
import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.domain.model.Equipment;
import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.ReportedLoss;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.Vehicle;
import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.domain.model.YesNoType;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.incident.IncidentStatus;
import com.i2g.rms.domain.model.tablemaintenance.BodyPart;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.domain.model.tablemaintenance.EntryPoint;
import com.i2g.rms.domain.model.tablemaintenance.ExternalAgency;
import com.i2g.rms.domain.model.tablemaintenance.IncidentLocation;
import com.i2g.rms.domain.model.tablemaintenance.IncidentLocationDetail;
import com.i2g.rms.domain.model.tablemaintenance.IncidentType;
import com.i2g.rms.domain.model.tablemaintenance.WeaponType;
import com.i2g.rms.rest.model.AccidentRO;
import com.i2g.rms.rest.model.AddressRO;
import com.i2g.rms.rest.model.AssetRO;
import com.i2g.rms.rest.model.BuildingRO;
import com.i2g.rms.rest.model.CrimeRO;
import com.i2g.rms.rest.model.CrimeSuspectRO;
import com.i2g.rms.rest.model.EquipmentRO;
import com.i2g.rms.rest.model.InjuredPersonRO;
import com.i2g.rms.rest.model.ReportedLossRO;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.VehicleRO;
import com.i2g.rms.rest.model.WitnessRO;
import com.i2g.rms.rest.model.incident.AccidentDetailRO;
import com.i2g.rms.rest.model.incident.AssetDetailRO;
import com.i2g.rms.rest.model.incident.CrimeDetailRO;
import com.i2g.rms.rest.model.incident.IncidentDetailRO;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.incident.LogIncidentRO;
import com.i2g.rms.rest.model.tablemaintenance.BodyPartRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureDetailRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.rest.service.RestMessage;
import com.i2g.rms.service.AccidentService;
import com.i2g.rms.service.AssetService;
import com.i2g.rms.service.CrimeService;
import com.i2g.rms.service.CrimeSuspectService;
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
	@Autowired
	private AssetService _assetService;
	@Autowired
	private CrimeService _crimeService;
	@Autowired
	private CrimeSuspectService _crimeSuspectService;

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
		final YesNoType assetDamage = (logIncidentRO.getAssetDamage().name().equals("Y")) ? YesNoType.Y : YesNoType.N; 
		final YesNoType criminalAttack = (logIncidentRO.getCriminalAttack().name().equals("Y")) ? YesNoType.Y : YesNoType.N;
		final YesNoType accidentDamage = (logIncidentRO.getAccidentDamage().name().equals("Y")) ? YesNoType.Y : YesNoType.N;
				
		//Construct incident record first with not null columns
		final Incident incident = new Incident.Builder()
									.setUniqueIncidentId(ApplicationUtilService.getUniqueIncidentId())
									.setIncidentReportedBy(user)
									.setIncidentStatus(IncidentStatus.DRAFT)
									.setStatusFlag(StatusFlag.ACTIVE)
									.setAssetDamage(assetDamage)
									.setCriminalAttack(criminalAttack)
									.setAccidentDamage(accidentDamage)
									.setIncidentOpenedDateTime(ApplicationUtilService.getCurrentTimestamp())
									.build();
		
		//Validate the newly created object
		validateGenericObject(incident);
		
		//Set other values
		if (logIncidentRO.getPlaceOfIncident() != null && !logIncidentRO.getPlaceOfIncident().trim().isEmpty()) {
			incident.setPlaceOfIncident(logIncidentRO.getPlaceOfIncident());
		}
		if (logIncidentRO.getIncidentDescription() != null && !logIncidentRO.getIncidentDescription().trim().isEmpty()) {
			incident.setIncidentDescription(logIncidentRO.getIncidentDescription());
		}
		
		//Construct other objects
		//incident type
		if (logIncidentRO.getIncidentType() != null) {
			if (logIncidentRO.getIncidentType().getId() != null && !logIncidentRO.getIncidentType().getId().trim().isEmpty()) {
				final IncidentType incidentType = _tableMaintenanceService.getIncidentTypeByCode(logIncidentRO.getIncidentType().getId().trim());
				incident.setIncidentType(incidentType);
			}
		}
		//incident type other
		if (logIncidentRO.getIncidentTypeOther() != null && !logIncidentRO.getIncidentTypeOther().trim().isEmpty()) {
			incident.setIncidentTypeOther(logIncidentRO.getIncidentTypeOther().trim());
		}
		//entry point
		if (logIncidentRO.getEntryPoint() != null) {
			if (logIncidentRO.getEntryPoint().getId() != null && !logIncidentRO.getEntryPoint().getId().trim().isEmpty()) {
				final EntryPoint entryPoint = _tableMaintenanceService.getEntryPointByCode(logIncidentRO.getEntryPoint().getId().trim());
				incident.setEntryPoint(entryPoint);
			}
		}
		//entry point other
		if (logIncidentRO.getEntryPointOther() != null && !logIncidentRO.getEntryPointOther().trim().isEmpty()) {
			incident.setEntryPointOther(logIncidentRO.getEntryPointOther().trim());
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
		//incident location other
		if (logIncidentRO.getIncidentLocationOther() != null && !logIncidentRO.getIncidentLocationOther().trim().isEmpty()) {
			incident.setIncidentLocationOther(logIncidentRO.getIncidentLocationOther().trim());
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
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(incidentDetailRO.getUniqueIncidentId().trim());
		validateGenericObject(incident);
		
		//Construct suspects and reported losses, if any..
		final Set<Suspect> newSuspects = constructNewSuspects(incidentDetailRO.getNewSuspects());
		final Set<Suspect> existingSuspects = new HashSet<Suspect>(0);		
		final Set<User> employeeSuspects = new HashSet<User>(0);
		final Set<ReportedLoss> reportedLosses = constructReportedLosses(incidentDetailRO.getReportedLosses(), incident);				
		
		//Create and add new suspects to the main incident.
		if (newSuspects != null && !newSuspects.isEmpty()) {
			final Set<Suspect> newlyCreatedSuspects = new HashSet<Suspect>(0);
			newlyCreatedSuspects.addAll(_suspectService.createNewSuspects(newSuspects));
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
			incident.getSuspects().addAll(existingSuspects);
		}		
		//Construction of employee suspects
		if (incidentDetailRO.getEmployeeSuspects() != null && !incidentDetailRO.getEmployeeSuspects().isEmpty()) {
			for (UserRO userRO : incidentDetailRO.getEmployeeSuspects()) {
				if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty()) {
					employeeSuspects.add(_userService.getUserByUserLoginId(userRO.getLoginId().trim()));
				}
			}
			incident.getEmployeeSuspects().addAll(employeeSuspects);
		}		
		//Create the reported losses in database and add it to the main incident. 
		if (reportedLosses != null && !reportedLosses.isEmpty()) {
			final Set<ReportedLoss> newlyCreatedReportedLosses = new HashSet<ReportedLoss>(0);
			newlyCreatedReportedLosses.addAll(_reportedLossService.createNewReportedLosses(reportedLosses));
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
		Accident accident = constructAccident(accidentDetailRO.getAccident(), incident);
		validateGenericObject(accident);
		
		//Instantiate objects
		if (accident.getInjuredPersons() == null || accident.getInjuredPersons().isEmpty()) {
			accident.setInjuredPersons(new HashSet<InjuredPerson>(0));
		}
		if (accident.getEmployeeInjuredPersons() == null || accident.getEmployeeInjuredPersons().isEmpty()) {
			accident.setEmployeeInjuredPersons(new HashSet<User>(0));
		}
		if (accident.getWitnesses() == null || accident.getWitnesses().isEmpty()) {
			accident.setWitnesses(new HashSet<Witness>(0));
		}		
		if (accident.getEmployeeWitnesses() == null || accident.getEmployeeWitnesses().isEmpty()) {
			accident.setEmployeeWitnesses(new HashSet<User>(0));
		}
		
		//Holder variables for newly created objects
		final Set<InjuredPerson> newInjuredPersons = constructNewInjuredPersons(accidentDetailRO.getNewInjuredPersons());
		final Set<InjuredPerson> existingInjuredPersons = new HashSet<InjuredPerson>(0);
		final Set<User> employeeInjuredPersons = new HashSet<User>(0);
		final Set<Witness> newWitnesses = constructNewWitnesses(accidentDetailRO.getNewWitnesses());
		final Set<Witness> existingWitnesses = new HashSet<Witness>(0);
		final Set<User> employeeWitnesses = new HashSet<User>(0);
		
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
			accident.getInjuredPersons().addAll(existingInjuredPersons);
		}
		//Construct employee injured persons and add to accident.
		if (accidentDetailRO.getEmployeeInjuredPersons() != null && !accidentDetailRO.getEmployeeInjuredPersons().isEmpty()) {
			for (UserRO userRO : accidentDetailRO.getEmployeeInjuredPersons()) {
				if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty()) {
					employeeInjuredPersons.add(_userService.getUserByUserLoginId(userRO.getLoginId().trim()));
				}
			}
			accident.getEmployeeInjuredPersons().addAll(employeeInjuredPersons);				
		}
		//End of construction of injured persons
		
		//construction of existing witnesses
		if (accidentDetailRO.getExistingWitnesses() != null && !accidentDetailRO.getExistingWitnesses().isEmpty()) {
			for (WitnessRO witnessRO : accidentDetailRO.getExistingWitnesses()) {
				if (witnessRO.getId() > 0) {
					existingWitnesses.add(_witnessService.get(witnessRO.getId()));
				}
			}
			//add existing witness to the accident.
			accident.getWitnesses().addAll(existingWitnesses);
		}
		//construction of employee witness if any and add to accident.
		if (accidentDetailRO.getEmployeeWitnesses() != null && !accidentDetailRO.getEmployeeWitnesses().isEmpty()) {
			for (UserRO userRO : accidentDetailRO.getEmployeeWitnesses()) {
				if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty()) {
					employeeWitnesses.add(_userService.getUserByUserLoginId(userRO.getLoginId().trim()));
				}
			}
			accident.getEmployeeWitnesses().addAll(employeeWitnesses);
		}
		//validate witness flag at this point before saving to database
		validateAnyWitness(accident.getAnyWitness(), newWitnesses, existingWitnesses, employeeWitnesses);
		//create the new witness in the backend and add it to the accident.
		if (newWitnesses != null && !newWitnesses.isEmpty()) {
			Set<Witness> newlyCreatedWitnesses = _witnessService.createNewWitnesses(newWitnesses);
			if (newlyCreatedWitnesses != null && !newlyCreatedWitnesses.isEmpty()) {
				//Add newly created suspects to main accident
				accident.getWitnesses().addAll(newlyCreatedWitnesses);										
			}
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
		//Throw the incident back with the newly created accident details
		return _mapperService.map(incident, IncidentRO.class);		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addAssetDetail(final AssetDetailRO assetDetailRO) {
		//Validate input param (object)
		validateObject(assetDetailRO);
		//Validate input main object accident.
		validateObject(assetDetailRO.getAsset());		
		//Validate unique incident id
		validateUniqueIncidentId(assetDetailRO.getUniqueIncidentId());
		
		//Construct the incident object for update
		Incident incident = _incidentService.getIncidentByUniqueIncidentId(assetDetailRO.getUniqueIncidentId().trim());
		validateGenericObject(incident);
		
		//Instantiate the asset object
		Asset asset = constructAsset(assetDetailRO.getAsset(), incident);
		validateGenericObject(asset);
		
		//Construct buildings, equipments and vehicles if any..
		asset.setBuildings(constructBuilding(assetDetailRO.getBuildings(), asset));
		asset.setEquipments(constructEquipment(assetDetailRO.getEquipments(), asset));
		asset.setVehicles(constructVehicle(assetDetailRO.getVehicles(), asset));
		
		//create the asset record
		final Asset newAsset = _assetService.create(asset);
		//Throw exception if unable to create
		if (newAsset == null) {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		} else {
			//Set the accident to incident.
			incident.setAsset(asset);
		}	
		//Throw the incident back with the newly created accident details
		return _mapperService.map(incident, IncidentRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addCrimeDetail(final CrimeDetailRO crimeDetailRO) {
		//Validate input param (object)
		validateObject(crimeDetailRO);
		//Validate unique incident id
		validateUniqueIncidentId(crimeDetailRO.getUniqueIncidentId());
		//Validate crime object
		validateObject(crimeDetailRO.getCrime());
		//Construct the incident object for update
		Incident incident = _incidentService.getIncidentByUniqueIncidentId(crimeDetailRO.getUniqueIncidentId().trim());
		validateGenericObject(incident);
		
		//Construct and create the crime object. 
		Crime crime = constructCrime(crimeDetailRO.getCrime(), incident);
		validateGenericObject(crime);
		//At this point the crime object is created in the database.
		
		//Instantiate suspects and witness list
		if (crime.getCrimeSuspects() == null || crime.getCrimeSuspects().isEmpty()) {
			crime.setCrimeSuspects(new HashSet<CrimeSuspect>(0));
		}
		if (crime.getEmployeeCrimeSuspects() == null || crime.getEmployeeCrimeSuspects().isEmpty()) {
			crime.setEmployeeCrimeSuspects(new HashSet<User>(0));
		}		
		if (crime.getWitnesses() == null || crime.getWitnesses().isEmpty()) {
			crime.setWitnesses(new HashSet<Witness>(0));
		}		
		if (crime.getEmployeeWitnesses() == null || crime.getEmployeeWitnesses().isEmpty()) {
			crime.setEmployeeWitnesses(new HashSet<User>(0));
		}
		
		final Set<CrimeSuspect> newCrimeSuspects = constructNewCrimeSuspects(crimeDetailRO.getNewCrimeSuspects(), crime);
		final Set<CrimeSuspect> existingCrimeSuspects = new HashSet<CrimeSuspect>(0);
		final Set<User> employeeCrimeSuspects = new HashSet<User>(0);
		
		final Set<Witness> newWitnesses = constructNewWitnesses(crimeDetailRO.getNewWitnesses());
		final Set<Witness> existingWitnesses = new HashSet<Witness>(0);
		final Set<User> employeeWitnesses = new HashSet<User>(0);
		
		//Save new crime suspects to database and assign them to accident record.
		if (newCrimeSuspects != null && !newCrimeSuspects.isEmpty()) {
			Set<CrimeSuspect> newlyCreatedCrimeSuspects = _crimeSuspectService.createNewCrimeSuspects(newCrimeSuspects);
			if (newlyCreatedCrimeSuspects != null && !newlyCreatedCrimeSuspects.isEmpty()) {
				crime.getCrimeSuspects().addAll(newlyCreatedCrimeSuspects);
			}
		}
		//Construct and add existing crime suspects if any to the crime record.
		if (crimeDetailRO.getExistingCrimeSuspects() != null && !crimeDetailRO.getExistingCrimeSuspects().isEmpty()) {
			for (CrimeSuspectRO crimeSuspectRO : crimeDetailRO.getExistingCrimeSuspects()) {
				if (crimeSuspectRO.getId() > 0) {
					existingCrimeSuspects.add(_crimeSuspectService.get(crimeSuspectRO.getId()));
				}
			}
			//Add existing suspects to crime
			crime.getCrimeSuspects().addAll(existingCrimeSuspects);
		}
		//Construct employee crime suspects and add to crime.
		if (crimeDetailRO.getEmployeeCrimeSuspects() != null && !crimeDetailRO.getEmployeeCrimeSuspects().isEmpty()) {
			for (UserRO userRO : crimeDetailRO.getEmployeeCrimeSuspects()) {
				if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty()) {
					employeeCrimeSuspects.add(_userService.getUserByUserLoginId(userRO.getLoginId().trim()));
				}
			}
			crime.getEmployeeCrimeSuspects().addAll(employeeCrimeSuspects);				
		}
		
		//construction of existing witnesses
		if (crimeDetailRO.getExistingWitnesses() != null && !crimeDetailRO.getExistingWitnesses().isEmpty()) {
			for (WitnessRO witnessRO : crimeDetailRO.getExistingWitnesses()) {
				if (witnessRO.getId() > 0) {
					existingWitnesses.add(_witnessService.get(witnessRO.getId()));
				}
			}
			//add existing witness to the crime.
			crime.getWitnesses().addAll(existingWitnesses);
		}
		//construction of employee witness if any and add to asset.
		if (crimeDetailRO.getEmployeeWitnesses() != null && !crimeDetailRO.getEmployeeWitnesses().isEmpty()) {
			for (UserRO userRO : crimeDetailRO.getEmployeeWitnesses()) {
				if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty()) {
					employeeWitnesses.add(_userService.getUserByUserLoginId(userRO.getLoginId().trim()));
				}
			}
			crime.getEmployeeWitnesses().addAll(employeeWitnesses);
		}
		//validate witness flag at this point before saving to database
		validateAnyWitness(crime.getAnyWitness(), newWitnesses, existingWitnesses, employeeWitnesses);
		//create the new witness in the backend and add it to the crime.
		if (newWitnesses != null && !newWitnesses.isEmpty()) {
			Set<Witness> newlyCreatedWitnesses = _witnessService.createNewWitnesses(newWitnesses);
			if (newlyCreatedWitnesses != null && !newlyCreatedWitnesses.isEmpty()) {
				//Add newly created suspects to main accident
				crime.getWitnesses().addAll(newlyCreatedWitnesses);										
			}
		}
		
		//Update the crime record
		final Crime updatedCrime = _crimeService.updateCrime(crime);
		//Throw exception if unable to update record
		if (updatedCrime == null) {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		} else {
			//Set the updated crime to incident.
			incident.setCrime(updatedCrime);
		}	
		//Throw the incident back with the newly created crime details
		return _mapperService.map(incident, IncidentRO.class);		
	}
	
	private Set<Suspect> constructNewSuspects(final Set<SuspectRO> suspectROs) {
		final Set<Suspect> suspects = new HashSet<Suspect>(0);		
		//Construction of new suspects
		if (suspectROs != null && !suspectROs.isEmpty()) {
			for (SuspectRO suspectRO : suspectROs) {
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
						suspect.setGenderType(_tableMaintenanceService.getGenderTypeByCode(suspectRO.getGenderType().getId().trim()));
					}
				}
				//gender type other
				if (suspectRO.getGenderTypeOther() != null && !suspectRO.getGenderTypeOther().trim().isEmpty()) {
					suspect.setGenderTypeOther(suspectRO.getGenderTypeOther().trim());
				}
				if (suspectRO.getAge() != null && suspectRO.getAge() > 0) {
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
				//weapon type other
				if (suspectRO.getWeaponTypeOther() != null && !suspectRO.getWeaponTypeOther().trim().isEmpty()) {
					suspect.setWeaponTypeOther(suspectRO.getWeaponTypeOther().trim());
				}
				//Suspect Type
				if (suspectRO.getSuspectType() != null) {
					if (suspectRO.getSuspectType().getId() != null && !suspectRO.getSuspectType().getId().trim().isEmpty()) {
						suspect.setSuspectType(_tableMaintenanceService.getSuspectTypeByCode(suspectRO.getSuspectType().getId().trim()));
					}
				}
				//suspect addresses if any
				if (suspectRO.getAddresses() != null) {
					suspect.setAddresses(constructAddresses(suspectRO.getAddresses(), null, suspect, null, null, null, null));
				}
				//other comments for suspect type
				if (suspectRO.getSuspectTypeOther() != null && !suspectRO.getSuspectTypeOther().trim().isEmpty()) {
					suspect.setSuspectTypeOther(suspectRO.getSuspectTypeOther().trim());
				}
				//construct and set distinguishing feature details
				if (suspect.getDistinguishingFeatureDetails() == null || suspect.getDistinguishingFeatureDetails().isEmpty()) {
					suspect.setDistinguishingFeatureDetails(new HashSet<DistinguishingFeatureDetail>(0));
				}
				suspect.getDistinguishingFeatureDetails().addAll(constructDistinguishingFeatureDetails(suspectRO.getDistinguishingFeatureDetails()));
				//other comments for distinguishing feature
				if (suspectRO.getDistinguishingFeatureOther() != null && !suspectRO.getDistinguishingFeatureOther().trim().isEmpty()) {
					suspect.setDistinguishingFeatureOther(suspectRO.getDistinguishingFeatureOther().trim());
				}
				//add to the list
				suspects.add(suspect);
			}
		}
		return suspects;
	}
	
	private Set<Address> constructAddresses(final Set<AddressRO> addressROs,
											final User user,
											final Suspect suspect, 
											final InjuredPerson injuredPerson, 
											final Witness witness,
											final CrimeSuspect crimeSuspect,
											final Building building) {
		final Set<Address> addresses = new HashSet<Address>(0);
		//Address
		if (addressROs != null && !addressROs.isEmpty()) {			
			for (AddressRO addressRO : addressROs) {
				final Address address = new Address.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
				//This step determines to which entity the addresses are constructed. It can be suspect, injured person, witness etc.
				if (user != null) {
					address.setUser(user);
				} else if (suspect != null) {
					address.setSuspect(suspect);
				} else if (injuredPerson != null) {
					address.setInjuredPerson(injuredPerson);
				} else if (witness != null) {
					address.setWitness(witness);
				} else if (crimeSuspect != null) {
					address.setCrimeSuspect(crimeSuspect);
				} else if (building != null) {
					address.setBuilding(building);
				}
				//other address fields
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
		}
		return addresses;
	}
	
	private Set<ReportedLoss> constructReportedLosses(final Set<ReportedLossRO> reportedLossROs, final Incident incident) {
		final Set<ReportedLoss> reportedLosses = new HashSet<ReportedLoss>(0);
		//Construction of reported loss
		if (reportedLossROs != null && !reportedLossROs.isEmpty()) {
			for (ReportedLossRO reportedLossRO: reportedLossROs) {				
				final ReportedLoss reportedLoss = new ReportedLoss.Builder()
												.setStatusFlag(StatusFlag.ACTIVE)
												.setIncident(incident)
												.build();
				//Loss type
				if (reportedLossRO.getLossType() != null) {
					if (reportedLossRO.getLossType().getId() != null && !reportedLossRO.getLossType().getId().trim().isEmpty()) {
						reportedLoss.setLossType(_tableMaintenanceService.getLossTypeByCode(reportedLossRO.getLossType().getId().trim()));
					}
				}
				//loss type other
				if (reportedLossRO.getLossTypeOther() != null && !reportedLossRO.getLossTypeOther().trim().isEmpty()) {
					reportedLoss.setLossTypeOther(reportedLossRO.getLossTypeOther().trim());
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
				//external agency other
				if (reportedLossRO.getExternalAgencyTypeOther() != null && !reportedLossRO.getExternalAgencyTypeOther().trim().isEmpty()) {
					reportedLoss.setExternalAgencyTypeOther(reportedLossRO.getExternalAgencyTypeOther().trim());
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
		return reportedLosses;
	}
	
	private Set<InjuredPerson> constructNewInjuredPersons(final Set<InjuredPersonRO> injuredPersonROs) {
		final Set<InjuredPerson> injuredPersons = new HashSet<InjuredPerson>(0);
		if (injuredPersonROs != null && !injuredPersonROs.isEmpty()) {
			for (InjuredPersonRO injuredPersonRO : injuredPersonROs) {
				InjuredPerson injuredPerson = new InjuredPerson.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
				//Set other values for (new) injured person(s)
				//Injured person type
				if (injuredPersonRO.getInjuredPersonType() != null) {
					if (injuredPersonRO.getInjuredPersonType().getId() != null && !injuredPersonRO.getInjuredPersonType().getId().trim().isEmpty()) {
						injuredPerson.setInjuredPersonType(_tableMaintenanceService.getInjuredPersonTypeByCode(injuredPersonRO.getInjuredPersonType().getId().trim()));
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
						injuredPerson.setGenderType(_tableMaintenanceService.getGenderTypeByCode(injuredPersonRO.getGenderType().getId().trim()));
					}
				}
				//gender type other
				if (injuredPersonRO.getGenderTypeOther() != null && !injuredPersonRO.getGenderTypeOther().trim().isEmpty()) {
					injuredPerson.setGenderTypeOther(injuredPersonRO.getGenderTypeOther().trim());
				}
				//Injury Cause
				if (injuredPersonRO.getInjuryCause() != null) {
					if (injuredPersonRO.getInjuryCause().getId() != null && !injuredPersonRO.getInjuryCause().getId().trim().isEmpty()) {
						injuredPerson.setInjuryCause(_tableMaintenanceService.getInjuryCauseByCode(injuredPersonRO.getInjuryCause().getId().trim()));
					}
				}
				//injury cause other
				if (injuredPersonRO.getInjuryCauseOther() != null && injuredPersonRO.getInjuryCauseOther().trim().isEmpty()) {
					injuredPerson.setInjuryCauseOther(injuredPersonRO.getInjuryCauseOther());
				}
				//Injury type
				if (injuredPersonRO.getInjuryType() != null) {
					if (injuredPersonRO.getInjuryType().getId() != null && !injuredPersonRO.getInjuryType().getId().trim().isEmpty()) {
						injuredPerson.setInjuryType(_tableMaintenanceService.getInjuryTypeByCode(injuredPersonRO.getInjuryType().getId().trim()));
					}
				}
				//Injury type detail
				if (injuredPersonRO.getInjuryTypeDetail() != null) {
					if (injuredPersonRO.getInjuryTypeDetail().getId() != null && !injuredPersonRO.getInjuryTypeDetail().getId().trim().isEmpty()) {
						injuredPerson.setInjuryTypeDetail(_tableMaintenanceService.getInjuryTypeDetailByCode(injuredPersonRO.getInjuryTypeDetail().getId().trim()));
					}
				}					
				//Injury type detail spec
				if (injuredPersonRO.getInjuryTypeDetailSpec() != null) {
					if (injuredPersonRO.getInjuryTypeDetailSpec().getId() != null && !injuredPersonRO.getInjuryTypeDetailSpec().getId().trim().isEmpty()) {
						injuredPerson.setInjuryTypeDetailSpec(_tableMaintenanceService.getInjuryTypeDetailSpecByCode(injuredPersonRO.getInjuryTypeDetailSpec().getId().trim()));							
					}
				}
				//injury type other
				if (injuredPersonRO.getInjuryTypeOther() != null && !injuredPersonRO.getInjuryTypeOther().trim().isEmpty()) {
					injuredPerson.setInjuryTypeOther(injuredPersonRO.getInjuryTypeOther().trim());
				}
				//Age
				if (injuredPersonRO.getAge() != null && injuredPersonRO.getAge() > 0) {
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
				//add addresses of the injured person
				if (injuredPersonRO.getAddresses() != null && !injuredPersonRO.getAddresses().isEmpty()) {
					injuredPerson.setAddresses(constructAddresses(injuredPersonRO.getAddresses(), null, null, injuredPerson, null, null, null));
				}
				//body parts
				if (injuredPersonRO.getBodyParts() != null && !injuredPersonRO.getBodyParts().isEmpty()) {
					injuredPerson.setBodyParts(constructBodyParts(injuredPersonRO.getBodyParts()));
				}
				//other comments for injured person type
				if (injuredPersonRO.getInjuredPersonTypeOther() != null && !injuredPersonRO.getInjuredPersonTypeOther().trim().isEmpty()) {
					injuredPerson.setInjuredPersonTypeOther(injuredPersonRO.getInjuredPersonTypeOther().trim());
				}
				//construct and set distinguishing feature details
				if (injuredPerson.getDistinguishingFeatureDetails() == null || injuredPerson.getDistinguishingFeatureDetails().isEmpty()) {
					injuredPerson.setDistinguishingFeatureDetails(new HashSet<DistinguishingFeatureDetail>(0));
				}
				injuredPerson.getDistinguishingFeatureDetails().addAll(constructDistinguishingFeatureDetails(injuredPersonRO.getDistinguishingFeatureDetails()));
				//other comments for distinguishing feature
				if (injuredPersonRO.getDistinguishingFeatureOther() != null && !injuredPersonRO.getDistinguishingFeatureOther().trim().isEmpty()) {
					injuredPerson.setDistinguishingFeatureOther(injuredPersonRO.getDistinguishingFeatureOther().trim());
				}
				//add the injured person to the list
				injuredPersons.add(injuredPerson);
			}
		}		
		return injuredPersons;
	}
	
	private Accident constructAccident(final AccidentRO accidentRO, final Incident incident) {
		final Accident accident = new Accident.Builder()
				.setStatusFlag(StatusFlag.ACTIVE)
				.setIncident(incident)
				.build();
		//Construct the accident object from the RO
		if (accidentRO != null) {
			//Accident Description
			if (accidentRO.getAccidentDescription() != null && !accidentRO.getAccidentDescription().trim().isEmpty()) {
				accident.setAccidentDescription(accidentRO.getAccidentDescription().trim());
			}
			//Accident date and time
			if (accidentRO.getAccidentDateTime() != null) {
				accident.setAccidentDateTime(accidentRO.getAccidentDateTime());
			}
			//place of accident
			if (accidentRO.getAccidentPlace() != null && !accidentRO.getAccidentPlace().trim().isEmpty()) {
				accident.setAccidentPlace(accidentRO.getAccidentPlace().trim());
			}
			//Accident type
			if (accidentRO.getAccidentType() != null) {
				if (accidentRO.getAccidentType().getId() != null && !accidentRO.getAccidentType().getId().trim().isEmpty()) {
					accident.setAccidentType(_tableMaintenanceService.getAccidentTypeByCode(accidentRO.getAccidentType().getId().trim()));					
				}				
			}
			//Set accident location
			if (accidentRO.getAccidentLocation() != null) {
				if (accidentRO.getAccidentLocation().getId() != null && !accidentRO.getAccidentLocation().getId().trim().isEmpty()) {
					accident.setAccidentLocation(_tableMaintenanceService.getAccidentLocationByCode(accidentRO.getAccidentLocation().getId().trim()));
				}
			}
			//set accident location detail
			if (accidentRO.getAccidentLocationDetails() != null) {
				if (accidentRO.getAccidentLocationDetails().getId() != null && !accidentRO.getAccidentLocationDetails().getId().trim().isEmpty()) {
					accident.setAccidentLocationDetails(_tableMaintenanceService.getAccidentLocationDetailByCode(accidentRO.getAccidentLocationDetails().getId().trim()));							
				}
			}
			//Set accident location other if any
			if (accidentRO.getAccidentLocationOther() != null && !accidentRO.getAccidentLocationOther().trim().isEmpty()) {
				accident.setAccidentLocationOther(accidentRO.getAccidentLocationOther().trim());
			}
			//Any witness to the accident?
			YesNoType anyWitness = YesNoType.N;
			if (accidentRO.getAnyWitness() != null && accidentRO.getAnyWitness().name().equals("Y")) {
				anyWitness = YesNoType.Y;
			}
			accident.setAnyWitness(anyWitness);
		}
		return accident;
	}
	
	private Set<Witness> constructNewWitnesses(final Set<WitnessRO> witnessROs) {
		final Set<Witness> witnesses = new HashSet<Witness>(0);
		//Construct new witnesses and add to accident.			
		if (witnessROs != null && !witnessROs.isEmpty()) {
			for (WitnessRO witnessRO : witnessROs) {
				final Witness witness = new Witness.Builder()
										.setStatusFlag(StatusFlag.ACTIVE)
										.build();
				//Set other values for (new) witnesses
				//Witness type
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
				//Gender type
				if (witnessRO.getGenderType() != null) {
					if (witnessRO.getGenderType().getId() != null && !witnessRO.getGenderType().getId().trim().isEmpty()) {
						witness.setGenderType(_tableMaintenanceService.getGenderTypeByCode(witnessRO.getGenderType().getId().trim()));
					}
				}
				//gender type other
				if (witnessRO.getGenderTypeOther() != null && !witnessRO.getGenderTypeOther().trim().isEmpty()) {
					witness.setGenderTypeOther(witnessRO.getGenderTypeOther().trim());
				}
				//Age
				if (witnessRO.getAge() != null && witnessRO.getAge() > 0) {
					witness.setAge(witnessRO.getAge());
				}
				//Date of Birth
				if (witnessRO.getDateOfBirth() != null) {
					witness.setDateOfBirth(witnessRO.getDateOfBirth());
				}
				//Address of the witness person
				if (witnessRO.getAddresses() != null && !witnessRO.getAddresses().isEmpty()) {
					witness.setAddresses(constructAddresses(witnessRO.getAddresses(), null, null, null, witness, null, null));
				}
				//other comments for witness type
				if (witnessRO.getWitnessTypeOther() != null && !witnessRO.getWitnessTypeOther().trim().isEmpty()) {
					witness.setWitnessTypeOther(witnessRO.getWitnessTypeOther().trim());
				}
				//construct and set distinguishing feature details
				if (witness.getDistinguishingFeatureDetails() == null || witness.getDistinguishingFeatureDetails().isEmpty()) {
					witness.setDistinguishingFeatureDetails(new HashSet<DistinguishingFeatureDetail>(0));
				}
				witness.getDistinguishingFeatureDetails().addAll(constructDistinguishingFeatureDetails(witnessRO.getDistinguishingFeatureDetails()));
				//other comments for distinguishing feature
				if (witnessRO.getDistinguishingFeatureOther() != null && !witnessRO.getDistinguishingFeatureOther().trim().isEmpty()) {
					witness.setDistinguishingFeatureOther(witnessRO.getDistinguishingFeatureOther().trim());
				}
				witnesses.add(witness);	
			}
		}		
		return witnesses;
	}
	
	private Asset constructAsset(final AssetRO assetRO, final Incident incident) {
		final Asset asset = new Asset.Builder()
						.setStatusFlag(StatusFlag.ACTIVE)
						.setIncident(incident)
						.build();
		if (assetRO != null) {
			//asset category
			if (assetRO.getAssetCategory() != null) {
				if (assetRO.getAssetCategory().getId() != null && !assetRO.getAssetCategory().getId().trim().isEmpty()) {
					asset.setAssetCategory(_tableMaintenanceService.getAssetCategoryByCode(assetRO.getAssetCategory().getId().trim())); 
				}
			}
			//other desc
			if (assetRO.getOtherDescription() != null && !assetRO.getOtherDescription().trim().isEmpty()) {
				asset.setOtherDescription(assetRO.getOtherDescription().trim());
			}
		}		
		return asset;
	}
	
	private Set<Building> constructBuilding(final List<BuildingRO> buildingROs, final Asset asset) {
		final Set<Building> buildings = new HashSet<Building>(0);
		if (buildingROs != null && !buildingROs.isEmpty()) {
			for (BuildingRO buildingRO : buildingROs) {
				final Building building = new Building.Builder()
									.setStatusFlag(StatusFlag.ACTIVE)
									.setAsset(asset)
									.build();
				//building id
				if (buildingRO.getBuildingId() != null && !buildingRO.getBuildingId().trim().isEmpty()) {
					building.setBuildingId(buildingRO.getBuildingId().trim());
				}
				//building description
				if (buildingRO.getBuildingDescription() != null && !buildingRO.getBuildingDescription().trim().isEmpty()) {
					building.setBuildingDescription(buildingRO.getBuildingDescription().trim());
				}
				//building name
				if (buildingRO.getBuildingName() != null && !buildingRO.getBuildingName().trim().isEmpty()) {
					building.setBuildingName(buildingRO.getBuildingName().trim());
				}
				//asset category
				if (buildingRO.getAssetCategory() != null) {
					if (buildingRO.getAssetCategory().getId() != null && !buildingRO.getAssetCategory().getId().trim().isEmpty()) {
						building.setAssetCategory(_tableMaintenanceService.getAssetCategoryByCode(buildingRO.getAssetCategory().getId().trim())); 
					}
				}
				//building address if any
				if (buildingRO.getAddresses() != null) {
					building.setAddresses(constructAddresses(buildingRO.getAddresses(), null, null, null, null, null, building));
				}
				buildings.add(building);
			}
		}
		return buildings;
	}
	
	private Set<Equipment> constructEquipment(final List<EquipmentRO> equipmentROs, final Asset asset) {
		final Set<Equipment> equipments = new HashSet<Equipment>(0);
		if (equipmentROs != null && !equipmentROs.isEmpty()) {
			for (EquipmentRO equipmentRO : equipmentROs) {
				final Equipment equipment = new Equipment.Builder()
											.setStatusFlag(StatusFlag.ACTIVE)
											.setAsset(asset).build();
				//equipment id
				if (equipmentRO.getEquipmentId() != null && !equipmentRO.getEquipmentId().trim().isEmpty()) {
					equipment.setEquipmentId(equipmentRO.getEquipmentId().trim());
				}
				//equipment details
				if (equipmentRO.getEquipmentDetails() != null && !equipmentRO.getEquipmentDetails().trim().isEmpty()) {
					equipment.setEquipmentDetails(equipmentRO.getEquipmentDetails().trim());
				}
				//serial number
				if (equipmentRO.getSerialNumber() != null && !equipmentRO.getSerialNumber().trim().isEmpty()) {
					equipment.setSerialNumber(equipmentRO.getSerialNumber().trim());
				}
				//asset category
				if (equipmentRO.getAssetCategory() != null) {
					if (equipmentRO.getAssetCategory().getId() != null && !equipmentRO.getAssetCategory().getId().trim().isEmpty()) {
						equipment.setAssetCategory(_tableMaintenanceService.getAssetCategoryByCode(equipmentRO.getAssetCategory().getId().trim())); 
					}
				}
				equipments.add(equipment);
			}
		}
		return equipments;
	}
	
	private Set<Vehicle> constructVehicle(final List<VehicleRO> vehicleROs, final Asset asset) {
		final Set<Vehicle> vehicles = new HashSet<Vehicle>(0);
		if (vehicleROs != null && !vehicleROs.isEmpty()) {
			for (VehicleRO vehicleRO : vehicleROs) {
				final Vehicle vehicle = new Vehicle.Builder()
										.setStatusFlag(StatusFlag.ACTIVE)
										.setAsset(asset)
										.build();
				//vehicle registration id
				if (nullOrEmptySafeCheck(vehicleRO.getVehicleRegistrationId())) {
					vehicle.setVehicleRegistrationId(stringTrimmer(vehicleRO.getVehicleRegistrationId()));
				}
				//engine number
				if (nullOrEmptySafeCheck(vehicleRO.getEngineNumber())) {
					vehicle.setEngineNumber(stringTrimmer(vehicleRO.getEngineNumber()));
				}
				//chasis number
				if (nullOrEmptySafeCheck(vehicleRO.getChasisNumber())) {
					vehicle.setChasisNumber(stringTrimmer(vehicleRO.getChasisNumber()));
				}
				//make
				if (nullOrEmptySafeCheck(vehicleRO.getMake())) {
					vehicle.setMake(stringTrimmer(vehicleRO.getMake()));
				}
				//model
				if (nullOrEmptySafeCheck(vehicleRO.getModel())) {
					vehicle.setModel(stringTrimmer(vehicleRO.getModel()));
				}
				//comment description
				if (nullOrEmptySafeCheck(vehicleRO.getCommentDescription())) {
					vehicle.setCommentDescription(stringTrimmer(vehicleRO.getCommentDescription()));
				}
				//Vehicle damage type
				if (vehicleRO.getVehicleDamageType() != null) {
					if (nullOrEmptySafeCheck(vehicleRO.getVehicleDamageType().getId())) {
						vehicle.setVehicleDamageType(_tableMaintenanceService.getVehicleDamageTypeByCode(stringTrimmer(vehicleRO.getVehicleDamageType().getId())));
					}
				}
				//asset category
				if (vehicleRO.getAssetCategory() != null) {
					if (nullOrEmptySafeCheck(vehicleRO.getAssetCategory().getId())) {
						vehicle.setAssetCategory(_tableMaintenanceService.getAssetCategoryByCode(stringTrimmer(vehicleRO.getAssetCategory().getId().trim()))); 
					}
				}
				vehicles.add(vehicle);
			}
		}
		return vehicles;
	}
	
	private Set<BodyPart> constructBodyParts(final Set<BodyPartRO> bodyPartROs) {
		final Set<BodyPart> bodyParts = new HashSet<BodyPart>(0);
		if (bodyPartROs != null && !bodyPartROs.isEmpty()) {
			for (BodyPartRO bodyPartRO : bodyPartROs) {
				if (bodyPartRO.getId() != null && !bodyPartRO.getId().trim().isEmpty()) {
					bodyParts.add(_tableMaintenanceService.getBodyPartByCode(bodyPartRO.getId().trim()));
				}
			}
		}
		return bodyParts;
	}
	
	private Crime constructCrime(final CrimeRO crimeRO, Incident incident) {
		final Crime crime = new Crime.Builder().setStatusFlag(StatusFlag.ACTIVE).setIncident(incident).build();
		//set other values
		//crime date and time
		if (crimeRO.getCrimeDateTime() != null) {
			crime.setCrimeDateTime(crimeRO.getCrimeDateTime());
		}
		//crime description
		if (crimeRO.getCrimeDescription() != null && !crimeRO.getCrimeDescription().trim().isEmpty()) {
			crimeRO.setCrimeDescription(crimeRO.getCrimeDescription().trim());
		}
		//
		YesNoType anyWitness = YesNoType.N;
		if (crimeRO.getAnyWitness() != null && crimeRO.getAnyWitness().name().equals("Y")) {
			anyWitness = YesNoType.Y;
		}
		crime.setAnyWitness(anyWitness);
		
		//Create the crime record
		final Crime newCrime = _crimeService.createCrime(crime);
		//Throw exception if unable to create record
		if (newCrime == null) {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
		
		return newCrime;
	}
	
	private Set<CrimeSuspect> constructNewCrimeSuspects(final Set<CrimeSuspectRO> crimeSuspectROs, final Crime crime) {
		final Set<CrimeSuspect> crimeSuspects = new HashSet<CrimeSuspect>(0);
		if (crimeSuspectROs != null && !crimeSuspectROs.isEmpty()) {
			for (CrimeSuspectRO crimeSuspectRO : crimeSuspectROs) {
				final CrimeSuspect crimeSuspect = new CrimeSuspect.Builder()
													.setStatusFlag(StatusFlag.ACTIVE)
													.setCrime(crime)
													.build();	
				//title
				if (nullOrEmptySafeCheck(crimeSuspectRO.getTitle())) {
					crimeSuspect.setTitle(stringTrimmer(crimeSuspectRO.getTitle()));
				}
				//first name
				if (nullOrEmptySafeCheck(crimeSuspectRO.getFirstName())) {
					crimeSuspect.setFirstName(stringTrimmer(crimeSuspectRO.getFirstName()));
				}
				//middle name
				if (nullOrEmptySafeCheck(crimeSuspectRO.getMiddleName())) {
					crimeSuspect.setMiddleName(stringTrimmer(crimeSuspectRO.getMiddleName()));
				}
				//last name
				if (nullOrEmptySafeCheck(crimeSuspectRO.getLastName())) {
					crimeSuspect.setLastName(stringTrimmer(crimeSuspectRO.getLastName()));
				}
				//name suffix
				if (nullOrEmptySafeCheck(crimeSuspectRO.getNameSuffix())) {
					crimeSuspect.setNameSuffix(stringTrimmer(crimeSuspectRO.getNameSuffix()));
				}
				//gender type
				if (crimeSuspectRO.getGenderType() != null) {
					if (nullOrEmptySafeCheck(crimeSuspectRO.getGenderType().getId())) {
						crimeSuspect.setGenderType(_tableMaintenanceService.getGenderTypeByCode(stringTrimmer(crimeSuspectRO.getGenderType().getId())));
					}
				}
				//gender type other
				if (crimeSuspectRO.getGenderTypeOther() != null && !crimeSuspectRO.getGenderTypeOther().trim().isEmpty()) {
					crimeSuspect.setGenderTypeOther(crimeSuspectRO.getGenderTypeOther().trim());
				}
				//Crime Suspect Type
				if (crimeSuspectRO.getCrimeSuspectType() != null) {
					if (crimeSuspectRO.getCrimeSuspectType().getId() != null && !crimeSuspectRO.getCrimeSuspectType().getId().trim().isEmpty()) {
						crimeSuspect.setCrimeSuspectType(_tableMaintenanceService.getSuspectTypeByCode(crimeSuspectRO.getCrimeSuspectType().getId().trim()));
					}
				}
				//other comments for crime suspect type
				if (crimeSuspectRO.getCrimeSuspectTypeOther() != null && !crimeSuspectRO.getCrimeSuspectTypeOther().trim().isEmpty()) {
					crimeSuspect.setCrimeSuspectTypeOther(crimeSuspectRO.getCrimeSuspectTypeOther().trim());
				}
				//date of birth
				if (crimeSuspectRO.getDateOfBirth() != null) {
					crimeSuspect.setDateOfBirth(crimeSuspectRO.getDateOfBirth());
				}
				//age
				if (crimeSuspectRO.getAge() != null && crimeSuspectRO.getAge() > 0) {
					crimeSuspect.setAge(crimeSuspectRO.getAge());
				}
				//phone
				if (nullOrEmptySafeCheck(crimeSuspectRO.getPhone())) {
					crimeSuspect.setPhone(stringTrimmer(crimeSuspectRO.getPhone()));
				}
				//alternate phone
				if (nullOrEmptySafeCheck(crimeSuspectRO.getAlternatePhone())) {
					crimeSuspect.setAlternatePhone(stringTrimmer(crimeSuspectRO.getAlternatePhone()));
				}
				//email
				if (nullOrEmptySafeCheck(crimeSuspectRO.getEmail())) {
					crimeSuspect.setEmail(stringTrimmer(crimeSuspectRO.getEmail()));
				}
				//website
				if (nullOrEmptySafeCheck(crimeSuspectRO.getWebsite())) {
					crimeSuspect.setWebsite(stringTrimmer(crimeSuspectRO.getWebsite()));
				}
				//crime addresses if any
				if (crimeSuspectRO.getAddresses() != null) {
					crimeSuspect.setAddresses(constructAddresses(crimeSuspectRO.getAddresses(), null, null, null, null, crimeSuspect, null));
				}
				//construct and set distinguishing feature details
				if (crimeSuspect.getDistinguishingFeatureDetails() == null || crimeSuspect.getDistinguishingFeatureDetails().isEmpty()) {
					crimeSuspect.setDistinguishingFeatureDetails(new HashSet<DistinguishingFeatureDetail>(0));
				}
				crimeSuspect.getDistinguishingFeatureDetails().addAll(constructDistinguishingFeatureDetails(crimeSuspectRO.getDistinguishingFeatureDetails()));
				//other comments for distinguishing feature
				if (crimeSuspectRO.getDistinguishingFeatureOther() != null && !crimeSuspectRO.getDistinguishingFeatureOther().trim().isEmpty()) {
					crimeSuspect.setDistinguishingFeatureOther(crimeSuspectRO.getDistinguishingFeatureOther().trim());
				}
				crimeSuspects.add(crimeSuspect);
			}
		}
		return crimeSuspects;
	}
	
	private Set<DistinguishingFeatureDetail> constructDistinguishingFeatureDetails(final Set<DistinguishingFeatureDetailRO> distinguishingFeatureDetailROs) {
		final Set<DistinguishingFeatureDetail> distinguishingFeatureDetails = new HashSet<DistinguishingFeatureDetail>(0);
		if (distinguishingFeatureDetailROs != null && !distinguishingFeatureDetailROs.isEmpty()) {
			for (DistinguishingFeatureDetailRO distinguishingFeatureDetailRO : distinguishingFeatureDetailROs) {
				if (distinguishingFeatureDetailRO.getId() != null && !distinguishingFeatureDetailRO.getId().trim().isEmpty()) {
					final DistinguishingFeatureDetail distinguishingFeatureDetail = _tableMaintenanceService.getDistinguishingFeatureDetailByCode(distinguishingFeatureDetailRO.getId().trim()); 
					distinguishingFeatureDetails.add(distinguishingFeatureDetail);
				}
			}
		}		
		return distinguishingFeatureDetails;
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
	
	private void validateAnyWitness(final YesNoType anyWitness, final Set<Witness> newWitnesses, final Set<Witness> existingWitnesses, final Set<User> employeeWitnesses) {
		boolean witnessesPresent = false;
		
		if (newWitnesses != null  && !newWitnesses.isEmpty()) {
			witnessesPresent = true;
		}
		if (existingWitnesses != null && !existingWitnesses.isEmpty()) {
			witnessesPresent = true;
		}
		if (employeeWitnesses != null && !employeeWitnesses.isEmpty()) {
			witnessesPresent = true;
		}
		
		if (anyWitness.name().equals(YesNoType.Y)) {
			//Please mark the witness flag to No when there are no witnesses.
			if (!witnessesPresent) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.WITNESS_FLAG_MUST_BE_NO));
			}			
		} else if (anyWitness.name().equals(YesNoType.N)) {
			//Please mark the witness flag to Yes when witness details are added.
			if (witnessesPresent) {			
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.WITNESS_FLAG_MUST_BE_YES));
			}
		}
	}		
}
