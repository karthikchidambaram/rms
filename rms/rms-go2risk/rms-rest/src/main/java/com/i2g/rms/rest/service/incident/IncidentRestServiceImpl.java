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
import com.i2g.rms.domain.model.Claim;
import com.i2g.rms.domain.model.Crime;
import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.domain.model.Equipment;
import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.Investigation;
import com.i2g.rms.domain.model.OfficeAddress;
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
import com.i2g.rms.rest.model.ClaimRO;
import com.i2g.rms.rest.model.CrimeRO;
import com.i2g.rms.rest.model.CrimeSuspectRO;
import com.i2g.rms.rest.model.EquipmentRO;
import com.i2g.rms.rest.model.InjuredPersonRO;
import com.i2g.rms.rest.model.InvestigationRO;
import com.i2g.rms.rest.model.ReportedLossRO;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.VehicleRO;
import com.i2g.rms.rest.model.WitnessRO;
import com.i2g.rms.rest.model.incident.AccidentDetailRO;
import com.i2g.rms.rest.model.incident.AssetDetailRO;
import com.i2g.rms.rest.model.incident.BaseIncidentDetailRO;
import com.i2g.rms.rest.model.incident.ClaimDetailRO;
import com.i2g.rms.rest.model.incident.CrimeDetailRO;
import com.i2g.rms.rest.model.incident.IncidentDetailRO;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.incident.InvestigationDetailRO;
import com.i2g.rms.rest.model.incident.LogIncidentRO;
import com.i2g.rms.rest.model.tablemaintenance.BodyPartRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureDetailRO;
import com.i2g.rms.rest.model.wrapper.IncidentWrapper;
import com.i2g.rms.rest.model.wrapper.SuspectWrapper;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.rest.service.RestMessage;
import com.i2g.rms.rest.service.SuspectRestService;
import com.i2g.rms.service.AccidentService;
import com.i2g.rms.service.AssetService;
import com.i2g.rms.service.BuildingService;
import com.i2g.rms.service.ClaimService;
import com.i2g.rms.service.CrimeService;
import com.i2g.rms.service.CrimeSuspectService;
import com.i2g.rms.service.EquipmentService;
import com.i2g.rms.service.InjuredPersonService;
import com.i2g.rms.service.InvestigationService;
import com.i2g.rms.service.OfficeAddressService;
import com.i2g.rms.service.ReportedLossService;
import com.i2g.rms.service.SuspectService;
import com.i2g.rms.service.UserService;
import com.i2g.rms.service.VehicleService;
import com.i2g.rms.service.WitnessService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotFoundException;
import com.i2g.rms.service.exception.ResourceNotRemovedException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
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
	private SuspectRestService _suspectRestService;
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
	@Autowired
	private OfficeAddressService _officeAddressService;
	@Autowired
	private ClaimService _claimService;
	@Autowired
	private InvestigationService _investigationService;
	@Autowired
	private BuildingService _buildingService;
	@Autowired
	private EquipmentService _equipmentService;
	@Autowired
	private VehicleService _vehicleService;
	
	/** get flows */
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<IncidentRO> get() {
		List<Incident> incidents = _incidentService.get();
		List<IncidentRO> incidentROs = incidents.isEmpty() ? Collections.emptyList() : _mapperService.map(incidents, IncidentRO.class);
		return incidentROs;
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public IncidentRO getIncidentByIncidentId(final Long incidentId) {
		validateKeyId(incidentId);
		final Incident incident = _incidentService.get(incidentId);
		validateGenericObject(incident);
		return _mapperService.map(incident, IncidentRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public IncidentRO getIncidentByUniqueIncidentId(final String uniqueIncidentId) {
		validateUniqueIncidentId(uniqueIncidentId);
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId);
		validateGenericObject(incident);
		return _mapperService.map(incident, IncidentRO.class);
	}
	
	/** add incident selected in UI */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	public UserRO addIncident() {
		return _mapperService.map(getUserFromContext(), UserRO.class);
	}
	
	/** create incident */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO logIncident(final LogIncidentRO logIncidentRO) {
		// Validate input param (object)
		validateObject(logIncidentRO);

		// Validate user object from context
		final User user = getUserFromContext();
		validateUserObject(user);

		// Set yes or no type fields
		YesNoType assetDamage = YesNoType.N; 
		if (logIncidentRO.getAssetDamage() != null && logIncidentRO.getAssetDamage().name().equals("Y")) {
			assetDamage = YesNoType.Y;
		}
		
		YesNoType criminalAttack = YesNoType.N;
		if (logIncidentRO.getCriminalAttack() != null && logIncidentRO.getCriminalAttack().name().equals("Y")) {
			criminalAttack = YesNoType.Y;
		}
		
		YesNoType accidentDamage = YesNoType.N;
		if (logIncidentRO.getAccidentDamage() != null && logIncidentRO.getAccidentDamage().name().equals("Y")) {
			accidentDamage = YesNoType.Y;
		}
		
		// Construct incident record first with not null columns
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

		// Validate the newly created object
		validateGenericObject(incident);	
		
		// Save the newly constructed incident
		final Incident newIncident = _incidentService.logIncident(setOtherValuesForIncident(incident, logIncidentRO));
		
		if (newIncident != null) {
			return _mapperService.map(newIncident, IncidentRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}
	
	/** update incident */
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO updateLogIncident(final LogIncidentRO logIncidentRO) {
		// Validate input param (object)
		validateObject(logIncidentRO);
		final Incident incident = getIncident(logIncidentRO);
		validateGenericObject(incident);
		// Save the newly constructed incident
		final Incident newIncident = _incidentService.updateIncident(setOtherValuesForIncident(incident, logIncidentRO));
		if (newIncident != null) {
			return _mapperService.map(newIncident, IncidentRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}
	
	/** Add or update incident */
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addOrUpdateLogIncident(final LogIncidentRO logIncidentRO) {
		// Validate input param (object)
		validateObject(logIncidentRO);
		if (addOrUpdateCheckForIncident(logIncidentRO)) {
			//update incident
			return updateLogIncident(logIncidentRO);
		} else {
			//add incident
			return logIncident(logIncidentRO);
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addIncidentDetail(final IncidentDetailRO incidentDetailRO) {
		// Validate input param (object)
		validateObject(incidentDetailRO);
		// Construct the incident object for update
		final Incident incident = getIncident(incidentDetailRO);
		validateGenericObject(incident);

		// Construct suspects and reported losses, if any..
		final Set<Suspect> newSuspects = constructNewSuspects(incidentDetailRO.getNewSuspects());
		final Set<Suspect> existingSuspects = new HashSet<Suspect>(0);
		final Set<User> employeeSuspects = new HashSet<User>(0);
		final Set<ReportedLoss> reportedLosses = constructReportedLosses(incidentDetailRO.getReportedLosses(), incident);

		// Create and add new suspects to the main incident.
		if (newSuspects != null && !newSuspects.isEmpty()) {
			final Set<Suspect> newlyCreatedSuspects = new HashSet<Suspect>(0);
			newlyCreatedSuspects.addAll(_suspectService.createNewSuspects(newSuspects));
			if (newlyCreatedSuspects != null && !newlyCreatedSuspects.isEmpty()) {
				// Add newly created suspects to main incident
				incident.getSuspects().addAll(newlyCreatedSuspects);
			}
		}
		// Construction of existing suspects
		if (incidentDetailRO.getExistingSuspects() != null && !incidentDetailRO.getExistingSuspects().isEmpty()) {
			for (SuspectRO suspectRO : incidentDetailRO.getExistingSuspects()) {
				if (suspectRO.getId() > 0) {
					existingSuspects.add(_suspectService.get(suspectRO.getId()));
				}
			}
			// Add existing suspects to main incident
			incident.getSuspects().addAll(existingSuspects);
		}
		// Construction of employee suspects
		if (incidentDetailRO.getEmployeeSuspects() != null && !incidentDetailRO.getEmployeeSuspects().isEmpty()) {
			for (UserRO userRO : incidentDetailRO.getEmployeeSuspects()) {
				if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty()) {
					employeeSuspects.add(_userService.getUserByUserLoginId(userRO.getLoginId().trim()));
				}
			}
			incident.getEmployeeSuspects().addAll(employeeSuspects);
		}
		// Create the reported losses in database and add it to the main incident.
		if (reportedLosses != null && !reportedLosses.isEmpty()) {
			final Set<ReportedLoss> newlyCreatedReportedLosses = new HashSet<ReportedLoss>(0);
			newlyCreatedReportedLosses.addAll(_reportedLossService.createReportedLosses(reportedLosses));
			if (newlyCreatedReportedLosses != null && !newlyCreatedReportedLosses.isEmpty()) {
				// Add newly created reported losses to main incident
				incident.getReportedLosses().addAll(newlyCreatedReportedLosses);
			}
		}

		// Update the incident with new suspects, existing suspects, employee
		// suspects and reported losses..
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
		// Validate input param (object)
		validateObject(accidentDetailRO);
		// Validate input main object accident.
		validateObject(accidentDetailRO.getAccident());
		final Incident incident = getIncident(accidentDetailRO);
		validateGenericObject(incident);

		// Instantiate the accident object
		final Accident accident = constructAccident(accidentDetailRO.getAccident(), incident);
		validateGenericObject(accident);

		// Instantiate objects
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

		// Holder variables for newly created objects
		final Set<InjuredPerson> newInjuredPersons = constructNewInjuredPersons(accidentDetailRO.getNewInjuredPersons());
		final Set<InjuredPerson> existingInjuredPersons = new HashSet<InjuredPerson>(0);
		final Set<User> employeeInjuredPersons = new HashSet<User>(0);
		final Set<Witness> newWitnesses = constructNewWitnesses(accidentDetailRO.getNewWitnesses());
		final Set<Witness> existingWitnesses = new HashSet<Witness>(0);
		final Set<User> employeeWitnesses = new HashSet<User>(0);

		// Save new injured persons to database and assign them to accident
		// record.
		if (newInjuredPersons != null && !newInjuredPersons.isEmpty()) {
			Set<InjuredPerson> newlyCreatedInjuredPersons = _injuredPersonService.createInjuredPersons(newInjuredPersons);
			if (newlyCreatedInjuredPersons != null && !newlyCreatedInjuredPersons.isEmpty()) {
				accident.getInjuredPersons().addAll(newlyCreatedInjuredPersons);
			}
		}
		// Construct and add existing injured persons if any..
		if (accidentDetailRO.getExistingInjuredPersons() != null && !accidentDetailRO.getExistingInjuredPersons().isEmpty()) {
			for (InjuredPersonRO injuredPersonRO : accidentDetailRO.getExistingInjuredPersons()) {
				if (injuredPersonRO.getId() > 0) {
					existingInjuredPersons.add(_injuredPersonService.get(injuredPersonRO.getId()));
				}
			}
			// Add existing suspects to accident
			accident.getInjuredPersons().addAll(existingInjuredPersons);
		}
		// Construct employee injured persons and add to accident.
		if (accidentDetailRO.getEmployeeInjuredPersons() != null && !accidentDetailRO.getEmployeeInjuredPersons().isEmpty()) {
			for (UserRO userRO : accidentDetailRO.getEmployeeInjuredPersons()) {
				if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty()) {
					employeeInjuredPersons.add(_userService.getUserByUserLoginId(userRO.getLoginId().trim()));
				}
			}
			accident.getEmployeeInjuredPersons().addAll(employeeInjuredPersons);
		}
		// End of construction of injured persons

		// construction of existing witnesses
		if (accidentDetailRO.getExistingWitnesses() != null && !accidentDetailRO.getExistingWitnesses().isEmpty()) {
			for (WitnessRO witnessRO : accidentDetailRO.getExistingWitnesses()) {
				if (witnessRO.getId() > 0) {
					existingWitnesses.add(_witnessService.get(witnessRO.getId()));
				}
			}
			// add existing witness to the accident.
			accident.getWitnesses().addAll(existingWitnesses);
		}
		// construction of employee witness if any and add to accident.
		if (accidentDetailRO.getEmployeeWitnesses() != null && !accidentDetailRO.getEmployeeWitnesses().isEmpty()) {
			for (UserRO userRO : accidentDetailRO.getEmployeeWitnesses()) {
				if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty()) {
					employeeWitnesses.add(_userService.getUserByUserLoginId(userRO.getLoginId().trim()));
				}
			}
			accident.getEmployeeWitnesses().addAll(employeeWitnesses);
		}
		// validate witness flag at this point before saving to database
		validateAnyWitness(accident.getAnyWitness(), newWitnesses, existingWitnesses, employeeWitnesses);
		// create the new witness in the backend and add it to the accident.
		if (newWitnesses != null && !newWitnesses.isEmpty()) {
			Set<Witness> newlyCreatedWitnesses = _witnessService.createNewWitnesses(newWitnesses);
			if (newlyCreatedWitnesses != null && !newlyCreatedWitnesses.isEmpty()) {
				// Add newly created suspects to main accident
				accident.getWitnesses().addAll(newlyCreatedWitnesses);
			}
		}
		// end of construction of witnesses

		// Create the accident record
		final Accident newAccident = _accidentService.createAccident(accident);
		// Throw exception if unable to create
		if (newAccident == null) {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		} else {
			// Set the accident to incident.
			incident.setAccident(accident);
		}
		// Throw the incident back with the newly created accident details
		return _mapperService.map(incident, IncidentRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addAssetDetail(final AssetDetailRO assetDetailRO) {
		// Validate input param (object)
		validateObject(assetDetailRO);
		// Validate input main object accident.
		validateObject(assetDetailRO.getAsset());
		final Incident incident = getIncident(assetDetailRO);
		validateGenericObject(incident);

		// Instantiate the asset object
		final Asset asset = constructAsset(assetDetailRO.getAsset(), incident);
		validateGenericObject(asset);
		
		final Set<Building> newBuildings = constructBuildings(assetDetailRO.getBuildings());
		final Set<Building> existingBuildings = new HashSet<Building>(0);
		final Set<Equipment> newEquipments = constructEquipments(assetDetailRO.getEquipments());
		final Set<Equipment> existingEquipments = new HashSet<Equipment>(0);
		final Set<Vehicle> newVehicles = constructVehicles(assetDetailRO.getVehicles());
		final Set<Vehicle> existingVehicles = new HashSet<Vehicle>(0);
		
		//Create new buildings if any
		if (newBuildings != null && !newBuildings.isEmpty()) {
			final Set<Building> newlyCreatedBuildings = new HashSet<Building>(0);
			newlyCreatedBuildings.addAll(_buildingService.createBuildings(newBuildings));
			if (newlyCreatedBuildings != null && !newlyCreatedBuildings.isEmpty()) {
				// Add newly created suspects to main incident
				asset.getBuildings().addAll(newlyCreatedBuildings);
			}
		}
		//Add existing buildings if any
		if (assetDetailRO.getExistingBuildings() != null && !assetDetailRO.getExistingBuildings().isEmpty()) {
			for (BuildingRO buildingRO : assetDetailRO.getExistingBuildings()) {
				if (buildingRO != null) {
					if (buildingRO.getId() > 0) {
						existingBuildings.add(_buildingService.get(buildingRO.getId()));
					}
				}
			}
			// Add existing buildings to main asset
			if (existingBuildings != null && !existingBuildings.isEmpty()) {
				asset.getBuildings().addAll(existingBuildings);
			}
		}
		
		//Create new equipments if any
		if (newEquipments != null && !newEquipments.isEmpty()) {
			final Set<Equipment> newlyCreatedEquipments = new HashSet<Equipment>(0);
			newlyCreatedEquipments.addAll(_equipmentService.createEquipments(newEquipments));
			if (newlyCreatedEquipments != null && !newlyCreatedEquipments.isEmpty()) {
				// Add newly created suspects to main incident
				asset.getEquipments().addAll(newlyCreatedEquipments);
			}
		}
		//Add existing equipments if any
		if (assetDetailRO.getExistingEquipments() != null && !assetDetailRO.getExistingEquipments().isEmpty()) {
			for (EquipmentRO equipmentRO : assetDetailRO.getExistingEquipments()) {
				if (equipmentRO != null) {
					if (equipmentRO.getId() > 0) {
						existingEquipments.add(_equipmentService.get(equipmentRO.getId()));
					}
				}
			}
			// Add existing equipments to main asset
			if (existingEquipments != null && !existingEquipments.isEmpty()) {
				asset.getEquipments().addAll(existingEquipments);
			}
		}
		
		//Create new vehicles if any
		if (newVehicles != null && !newVehicles.isEmpty()) {
			final Set<Vehicle> newlyCreatedVehicles = new HashSet<Vehicle>(0);
			newlyCreatedVehicles.addAll(_vehicleService.createVehicles(newVehicles));
			if (newlyCreatedVehicles != null && !newlyCreatedVehicles.isEmpty()) {
				// Add newly created suspects to main incident
				asset.getVehicles().addAll(newlyCreatedVehicles);
			}
		}
		//Add existing vehicles if any
		if (assetDetailRO.getExistingVehicles() != null && !assetDetailRO.getExistingVehicles().isEmpty()) {
			for (VehicleRO vehicleRO : assetDetailRO.getExistingVehicles()) {
				if (vehicleRO != null) {
					if (vehicleRO.getId() > 0) {
						existingVehicles.add(_vehicleService.get(vehicleRO.getId()));
					}
				}
			}
			// Add existing vehicles to main asset
			if (existingVehicles != null && !existingVehicles.isEmpty()) {
				asset.getVehicles().addAll(existingVehicles);
			}
		}

		// create the asset record
		final Asset newAsset = _assetService.createAsset(asset);
		if (newAsset != null) {
			// Set the accident to incident.
			incident.setAsset(asset);
			// Throw the incident back with the newly created asset details
			return _mapperService.map(incident, IncidentRO.class);
		} else {
			// Throw exception if unable to create
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addCrimeDetail(final CrimeDetailRO crimeDetailRO) {
		// Validate input param (object)
		validateObject(crimeDetailRO);
		// Validate crime object
		validateObject(crimeDetailRO.getCrime());
		// Construct the incident object for update
		final Incident incident = getIncident(crimeDetailRO);
		validateGenericObject(incident);

		// Construct and create the crime object.
		final Crime crime = constructCrime(crimeDetailRO.getCrime(), incident);
		validateGenericObject(crime);
		// At this point the crime object is created in the database.

		// Instantiate suspects and witness list
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

		final Set<CrimeSuspect> newCrimeSuspects = constructNewCrimeSuspects(crimeDetailRO.getNewCrimeSuspects());
		final Set<CrimeSuspect> existingCrimeSuspects = new HashSet<CrimeSuspect>(0);
		final Set<User> employeeCrimeSuspects = new HashSet<User>(0);

		final Set<Witness> newWitnesses = constructNewWitnesses(crimeDetailRO.getNewWitnesses());
		final Set<Witness> existingWitnesses = new HashSet<Witness>(0);
		final Set<User> employeeWitnesses = new HashSet<User>(0);

		// Save new crime suspects to database and assign them to accident record.
		if (newCrimeSuspects != null && !newCrimeSuspects.isEmpty()) {
			Set<CrimeSuspect> newlyCreatedCrimeSuspects = _crimeSuspectService.createNewCrimeSuspects(newCrimeSuspects);
			if (newlyCreatedCrimeSuspects != null && !newlyCreatedCrimeSuspects.isEmpty()) {
				crime.getCrimeSuspects().addAll(newlyCreatedCrimeSuspects);
			}
		}
		// Construct and add existing crime suspects if any to the crime record.
		if (crimeDetailRO.getExistingCrimeSuspects() != null && !crimeDetailRO.getExistingCrimeSuspects().isEmpty()) {
			for (CrimeSuspectRO crimeSuspectRO : crimeDetailRO.getExistingCrimeSuspects()) {
				if (crimeSuspectRO.getId() > 0) {
					existingCrimeSuspects.add(_crimeSuspectService.get(crimeSuspectRO.getId()));
				}
			}
			// Add existing suspects to crime
			crime.getCrimeSuspects().addAll(existingCrimeSuspects);
		}
		// Construct employee crime suspects and add to crime.
		if (crimeDetailRO.getEmployeeCrimeSuspects() != null && !crimeDetailRO.getEmployeeCrimeSuspects().isEmpty()) {
			for (UserRO userRO : crimeDetailRO.getEmployeeCrimeSuspects()) {
				if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty()) {
					employeeCrimeSuspects.add(_userService.getUserByUserLoginId(userRO.getLoginId().trim()));
				}
			}
			crime.getEmployeeCrimeSuspects().addAll(employeeCrimeSuspects);
		}

		// construction of existing witnesses
		if (crimeDetailRO.getExistingWitnesses() != null && !crimeDetailRO.getExistingWitnesses().isEmpty()) {
			for (WitnessRO witnessRO : crimeDetailRO.getExistingWitnesses()) {
				if (witnessRO.getId() > 0) {
					existingWitnesses.add(_witnessService.get(witnessRO.getId()));
				}
			}
			// add existing witness to the crime.
			crime.getWitnesses().addAll(existingWitnesses);
		}
		// construction of employee witness if any and add to asset.
		if (crimeDetailRO.getEmployeeWitnesses() != null && !crimeDetailRO.getEmployeeWitnesses().isEmpty()) {
			for (UserRO userRO : crimeDetailRO.getEmployeeWitnesses()) {
				if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty()) {
					employeeWitnesses.add(_userService.getUserByUserLoginId(userRO.getLoginId().trim()));
				}
			}
			crime.getEmployeeWitnesses().addAll(employeeWitnesses);
		}
		// validate witness flag at this point before saving to database
		validateAnyWitness(crime.getAnyWitness(), newWitnesses, existingWitnesses, employeeWitnesses);
		// create the new witness in the backend and add it to the crime.
		if (newWitnesses != null && !newWitnesses.isEmpty()) {
			Set<Witness> newlyCreatedWitnesses = _witnessService.createNewWitnesses(newWitnesses);
			if (newlyCreatedWitnesses != null && !newlyCreatedWitnesses.isEmpty()) {
				// Add newly created suspects to main accident
				crime.getWitnesses().addAll(newlyCreatedWitnesses);
			}
		}

		// Create the new crime record
		final Crime newCrime = _crimeService.createCrime(crime);
		if (newCrime != null) {
			// Set the updated crime to incident.
			incident.setCrime(newCrime);
			return _mapperService.map(incident, IncidentRO.class);
		} else {
			// Throw exception if unable to update record
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public IncidentRO addClaimDetail(final ClaimDetailRO claimDetailRO) {
		// Validate input param (object)
		validateObject(claimDetailRO);
		// Validate claim object
		validateObject(claimDetailRO.getClaim());
		// Construct the incident object for update
		final Incident incident = getIncident(claimDetailRO);
		validateGenericObject(incident);

		// Construct and create the claim object.
		final Claim claim = constructClaim(claimDetailRO.getClaim(), incident);
		validateGenericObject(claim);
		
		// create the asset record
		final Claim newClaim = _claimService.createClaim(claim);
		if (newClaim != null) {
			// Set the claim to incident.
			incident.setClaim(newClaim);
			// Throw the incident back with the newly created claim details
			return _mapperService.map(incident, IncidentRO.class);
		} else {
			// Throw exception if unable to create
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public IncidentRO addInvestigationDetail(final InvestigationDetailRO investigationDetailRO) {
		// Validate input param (object)
		validateObject(investigationDetailRO);
		// Validate investigation object
		validateObject(investigationDetailRO.getInvestigation());
		// Construct the incident object for update
		final Incident incident = getIncident(investigationDetailRO);
		validateGenericObject(incident);

		// Construct and create the investigation object.
		final Investigation investigation = constructNewInvestigation(investigationDetailRO.getInvestigation(), incident);
		validateGenericObject(investigation);
		// create the investigation record
		final Investigation newInvestigation = _investigationService.createInvestigation(investigation);
		// Throw exception if unable to create
		if (newInvestigation == null) {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		} else {
			// Set the new investigation to incident.
			incident.setInvestigation(newInvestigation);
		}
		// Throw the incident back with the newly created investigation details
		return _mapperService.map(incident, IncidentRO.class);		
	}	
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO submitIncident(final IncidentWrapper incidentWrapper) {
		validateObject(incidentWrapper);
		final Incident incident = getIncident(incidentWrapper);
		validateGenericObject(incident);
		if (!incident.getIncidentStatus().name().equals("DRAFT")) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.INCIDENT_ALREADY_SUBMITTED));
		}
		incident.setIncidentStatus(IncidentStatus.NEW);
		final Incident updatedIncident = _incidentService.updateIncident(incident);
		if (updatedIncident != null) {
			return _mapperService.map(updatedIncident, IncidentRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}
	
	/** alternate flows */
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addSuspectForIncident(final String uniqueIncidentId, final SuspectRO suspectRO) {
		validateUniqueIncidentId(uniqueIncidentId);
		validateObject(suspectRO);
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId);
		validateGenericObject(incident);
		final Suspect suspect = _suspectRestService.constructNewSuspect(suspectRO);
		validateGenericObject(suspect);
		final Suspect newSuspect = _suspectService.createNewSuspect(suspect);
		if (newSuspect != null) {
			incident.getSuspects().add(newSuspect);
			final Incident updatedIncident = _incidentService.updateIncident(incident);
				if (updatedIncident != null) {
					return _mapperService.map(updatedIncident, IncidentRO.class);
				} else {
					throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_ADD_SUSPECT_TO_INCIDENT));
				}
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_ADD_SUSPECT_TO_INCIDENT));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addSuspectsForIncident(final SuspectWrapper suspectWrapper) {
		validateObject(suspectWrapper);
		validateCollectionObject(suspectWrapper.getSuspects());
		final Incident incident = getIncident(suspectWrapper);
		validateGenericObject(incident);
		
		final Set<Suspect> suspects = constructNewSuspects(new HashSet<SuspectRO>(suspectWrapper.getSuspects()));
		if (suspects == null || suspects.isEmpty()) {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CONSTRUCT_OBJECT_FOR_RECORD_CREATION));
		}
		
		final List<Suspect> newSuspects = _suspectService.createNewSuspects(suspects);
		if (newSuspects != null && !newSuspects.isEmpty()) {
			incident.getSuspects().addAll(newSuspects);
			final Incident updatedIncident = _incidentService.updateIncident(incident);		
			if (updatedIncident != null) {
				return _mapperService.map(updatedIncident, IncidentRO.class);
			} else {
				throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_ADD_SUSPECT_TO_INCIDENT));
			}
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_ADD_SUSPECT_TO_INCIDENT));
		}	
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addExistingSuspectForIncident(final String uniqueIncidentId, final Long suspectId) {
		validateUniqueIncidentId(uniqueIncidentId);
		if (suspectId == null || suspectId <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId.trim());
		validateGenericObject(incident);
		final Suspect existingSuspect = _suspectService.get(suspectId);
		validateGenericObject(existingSuspect);
		incident.getSuspects().add(existingSuspect);
		final Incident updatedIncident = _incidentService.updateIncident(incident);
		if (updatedIncident != null) {
			return _mapperService.map(updatedIncident, IncidentRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_ADD_SUSPECT_TO_INCIDENT));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addExistingSuspectsForIncident(final SuspectWrapper suspectWrapper) {
		validateObject(suspectWrapper);
		validateCollectionObject(suspectWrapper.getSuspects());
		final Incident incident = getIncident(suspectWrapper);
		validateGenericObject(incident);
		
		final Set<Suspect> suspects = new HashSet<Suspect>(0);
		for (SuspectRO suspectRO : suspectWrapper.getSuspects()) {
			if (suspectRO != null) {
				if (suspectRO.getId() > 0) {
					final Suspect suspect = _suspectService.get(suspectRO.getId());
					if (suspect != null) {
						suspects.add(suspect);
					}
				}
			}
		}
		
		if (suspects != null && !suspects.isEmpty()) {
			incident.getSuspects().addAll(suspects);
			final Incident updatedIncident = _incidentService.updateIncident(incident);
			if (updatedIncident != null) {
				return _mapperService.map(updatedIncident, IncidentRO.class);
			} else {
				throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_ADD_SUSPECT_TO_INCIDENT));
			}
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CONSTRUCT_OBJECT_FOR_RECORD_CREATION));
		}		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addEmployeeSuspectForIncidentById(final String uniqueIncidentId, final Long employeeId) {
		validateUniqueIncidentId(uniqueIncidentId);
		if (employeeId == null || employeeId <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId.trim());
		validateGenericObject(incident);
		final User employee = _userService.get(employeeId);
		validateGenericObject(employee);
		incident.getEmployeeSuspects().add(employee);
		final Incident updatedIncident = _incidentService.updateIncident(incident);
		if (updatedIncident != null) {
			return _mapperService.map(updatedIncident, IncidentRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_ADD_SUSPECT_TO_INCIDENT));
		}		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addEmployeeSuspectForIncidentByLoginId(final String uniqueIncidentId, final String employeeLoginId) {
		validateUniqueIncidentId(uniqueIncidentId);
		validateUsername(employeeLoginId);
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId.trim());
		validateGenericObject(incident);
		final User employee = _userService.getUserByUserLoginId(employeeLoginId.trim());
		validateGenericObject(employee);
		incident.getEmployeeSuspects().add(employee);
		final Incident updatedIncident = _incidentService.updateIncident(incident);
		if (updatedIncident != null) {
			return _mapperService.map(updatedIncident, IncidentRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_ADD_SUSPECT_TO_INCIDENT));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addEmployeeSuspectsForIncidentByIds(final SuspectWrapper suspectWrapper) {
		validateObject(suspectWrapper);
		validateUniqueIncidentId(suspectWrapper.getUniqueIncidentId());
		final Long[] employeeIds = suspectWrapper.getEmployeeIds(); 
		if (employeeIds == null || employeeIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(suspectWrapper.getUniqueIncidentId().trim());
		validateGenericObject(incident);
		final Set<User> employeeSuspects = new HashSet<User>(0);
		for (int i = 0; i < employeeIds.length; i++) {
			final Long employeeId = employeeIds[i];
			if (employeeId != null && employeeId > 0) {
				final User employeeSuspect = _userService.get(employeeId);
				if (employeeSuspect != null) {
					employeeSuspects.add(employeeSuspect);
				}
			}
		}
		if (employeeSuspects != null && !employeeSuspects.isEmpty()) {
			incident.getEmployeeSuspects().addAll(employeeSuspects);
			final Incident updatedIncident = _incidentService.updateIncident(incident);
			if (updatedIncident != null) {
				return _mapperService.map(updatedIncident, IncidentRO.class);
			} else {
				throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_ADD_SUSPECT_TO_INCIDENT));
			}
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CONSTRUCT_OBJECT_FOR_RECORD_CREATION));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO addEmployeeSuspectsForIncidentByLoginIds(final SuspectWrapper suspectWrapper) {
		validateObject(suspectWrapper);
		validateUniqueIncidentId(suspectWrapper.getUniqueIncidentId());
		final String[] employeeLoginIds = suspectWrapper.getEmployeeLoginIds(); 
		if (employeeLoginIds == null || employeeLoginIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(suspectWrapper.getUniqueIncidentId().trim());
		validateGenericObject(incident);
		final Set<User> employeeSuspects = new HashSet<User>(0);
		for (int i = 0; i < employeeLoginIds.length; i++) {
			final String employeeLoginId = employeeLoginIds[i];
			if (employeeLoginId != null && !employeeLoginId.trim().isEmpty()) {
				final User employeeSuspect = _userService.getUserByUserLoginId(employeeLoginId.trim());
				if (employeeSuspect != null) {
					employeeSuspects.add(employeeSuspect);
				}
			}
		}
		if (employeeSuspects != null && !employeeSuspects.isEmpty()) {
			incident.getEmployeeSuspects().addAll(employeeSuspects);
			final Incident updatedIncident = _incidentService.updateIncident(incident);
			if (updatedIncident != null) {
				return _mapperService.map(updatedIncident, IncidentRO.class);
			} else {
				throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_ADD_SUSPECT_TO_INCIDENT));
			}
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CONSTRUCT_OBJECT_FOR_RECORD_CREATION));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO removeSuspectFromIncident(final String uniqueIncidentId, final Long suspectId) {
		validateUniqueIncidentId(uniqueIncidentId);
		if (suspectId == null || suspectId <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.INVALID_SUSPECT_ID_PASSED_AS_PARAMETER));
		}
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId);
		validateGenericObject(incident);
		final Suspect suspect = _suspectService.get(suspectId);
		validateGenericObject(suspect);
		if (!incident.getSuspects().contains(suspect)) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.UNIQUE_INCIDENT_ID_AND_SUSPECT_ID_DOES_NOT_MATCH));
		}
		incident.getSuspects().remove(suspect);
		final Incident updatedIncident = _incidentService.updateIncident(incident);
		if (updatedIncident != null) {
			return _mapperService.map(updatedIncident, IncidentRO.class);
		} else {
			throw new ResourceNotRemovedException(_messageBuilder.build(RestMessage.DELETE_OPERATION_FAILED));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO removeSuspectsFromIncident(final SuspectWrapper suspectWrapper) {
		validateObject(suspectWrapper);
		validateUniqueIncidentId(suspectWrapper.getUniqueIncidentId());
		validateCollectionObject(suspectWrapper.getSuspects());
		
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(suspectWrapper.getUniqueIncidentId().trim());
		validateGenericObject(incident);
		
		final Set<Suspect> suspects = getSuspects(new HashSet<SuspectRO>(suspectWrapper.getSuspects()));
		if (suspects == null || suspects.isEmpty()) {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		incident.getSuspects().removeAll(suspects);
		final Incident updatedIncident = _incidentService.updateIncident(incident);		
		if (updatedIncident != null) {
			return _mapperService.map(updatedIncident, IncidentRO.class);
		} else {
			throw new ResourceNotRemovedException(_messageBuilder.build(RestMessage.DELETE_OPERATION_FAILED));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO removeEmployeeSuspectFromIncidentById(final String uniqueIncidentId, final Long employeeId) {
		validateUniqueIncidentId(uniqueIncidentId);
		if (employeeId == null || employeeId <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.INVALID_KEY_PASSED_FOR_DELETE));
		}
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId.trim());
		validateGenericObject(incident);
		final User employeeSuspect = _userService.get(employeeId);
		validateGenericObject(employeeSuspect);
		incident.getEmployeeSuspects().remove(employeeSuspect);
		final Incident updatedIncident = _incidentService.updateIncident(incident);
		if (updatedIncident != null) {
			return _mapperService.map(updatedIncident, IncidentRO.class);
		} else {
			throw new ResourceNotRemovedException(_messageBuilder.build(RestMessage.DELETE_OPERATION_FAILED));	
		}		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO removeEmployeeSuspectFromIncidentByLoginId(final String uniqueIncidentId, final String employeeLoginId) {
		validateUniqueIncidentId(uniqueIncidentId);
		validateUsername(employeeLoginId);
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId.trim());
		validateGenericObject(incident);
		final User employeeSuspect = _userService.getUserByUserLoginId(employeeLoginId);
		validateGenericObject(employeeSuspect);
		incident.getEmployeeSuspects().remove(employeeSuspect);
		final Incident updatedIncident = _incidentService.updateIncident(incident);
		if (updatedIncident != null) {
			return _mapperService.map(updatedIncident, IncidentRO.class);
		} else {
			throw new ResourceNotRemovedException(_messageBuilder.build(RestMessage.DELETE_OPERATION_FAILED));	
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO removeEmployeeSuspectsFromIncidentByIds(final SuspectWrapper suspectWrapper) {
		validateObject(suspectWrapper);
		validateUniqueIncidentId(suspectWrapper.getUniqueIncidentId());
		final Long[] employeeIds = suspectWrapper.getEmployeeIds();
		if (employeeIds == null || employeeIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.NOTHING_TO_DELETE));
		}
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(suspectWrapper.getUniqueIncidentId().trim());
		validateGenericObject(incident);
		Set<User> employeeSuspects = new HashSet<User>(0);
		for (int i = 0; i < employeeIds.length; i++) {
			final Long employeeId = employeeIds[i];
			if (employeeId != null && employeeId > 0) {
				final User employeeSuspect = _userService.get(employeeId);
				if (employeeSuspect != null) {
					employeeSuspects.add(employeeSuspect);
				}
			}
		}
		if (employeeSuspects != null && !employeeSuspects.isEmpty()) {
			incident.getEmployeeSuspects().removeAll(employeeSuspects);
			final Incident updatedIncident = _incidentService.updateIncident(incident);
			if (updatedIncident != null) {
				return _mapperService.map(updatedIncident, IncidentRO.class);
			} else {
				throw new ResourceNotRemovedException(_messageBuilder.build(RestMessage.DELETE_OPERATION_FAILED));	
			}
		} else {
			throw new ResourceNotRemovedException(_messageBuilder.build(RestMessage.NO_RECORDS_WERE_DELETED));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO removeEmployeeSuspectsFromIncidentByLoginIds(final SuspectWrapper suspectWrapper) {
		validateObject(suspectWrapper);
		validateUniqueIncidentId(suspectWrapper.getUniqueIncidentId());
		final String[] employeeLoginIds = suspectWrapper.getEmployeeLoginIds();
		if (employeeLoginIds == null || employeeLoginIds.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.NOTHING_TO_DELETE));
		}
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(suspectWrapper.getUniqueIncidentId().trim());
		validateGenericObject(incident);
		Set<User> employeeSuspects = new HashSet<User>(0);
		for (int i = 0; i < employeeLoginIds.length; i++) {
			final String employeeLoginId = employeeLoginIds[i];
			if (employeeLoginId != null) {
				final User employeeSuspect = _userService.getUserByUserLoginId(employeeLoginId);
				if (employeeSuspect != null) {
					employeeSuspects.add(employeeSuspect);
				}
			}
		}
		if (employeeSuspects != null && !employeeSuspects.isEmpty()) {
			incident.getEmployeeSuspects().removeAll(employeeSuspects);
			final Incident updatedIncident = _incidentService.updateIncident(incident);
			if (updatedIncident != null) {
				return _mapperService.map(updatedIncident, IncidentRO.class);
			} else {
				throw new ResourceNotRemovedException(_messageBuilder.build(RestMessage.DELETE_OPERATION_FAILED));	
			}
		} else {
			throw new ResourceNotRemovedException(_messageBuilder.build(RestMessage.NO_RECORDS_WERE_DELETED));
		}
	}

	private Set<Suspect> constructNewSuspects(final Set<SuspectRO> suspectROs) {
		final Set<Suspect> suspects = new HashSet<Suspect>(0);
		// Construction of new suspects
		if (suspectROs != null && !suspectROs.isEmpty()) {
			for (SuspectRO suspectRO : suspectROs) {
				Suspect suspect = new Suspect.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
				// Set other values
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
				// Gender type
				if (suspectRO.getGenderType() != null) {
					if (suspectRO.getGenderType().getId() != null && !suspectRO.getGenderType().getId().trim().isEmpty()) {
						suspect.setGenderType(_tableMaintenanceService.getGenderTypeByCode(suspectRO.getGenderType().getId().trim()));
					}
				}
				// gender type other
				if (suspectRO.getGenderTypeOther() != null && !suspectRO.getGenderTypeOther().trim().isEmpty()) {
					suspect.setGenderTypeOther(suspectRO.getGenderTypeOther().trim());
				}
				if (suspectRO.getAge() != null && suspectRO.getAge() > 0) {
					suspect.setAge(suspectRO.getAge());
				}
				if (suspectRO.getDateOfBirth() != null) {
					suspect.setDateOfBirth(suspectRO.getDateOfBirth());
				}
				// Was there a weapon involved?
				YesNoType weaponInvolved = YesNoType.N;
				if (suspectRO.getWeaponInvolved() != null && suspectRO.getWeaponInvolved().name().equals("Y")) {
					weaponInvolved = YesNoType.Y;
				}
				// Weapon type
				WeaponType weaponType = null;
				if (suspectRO.getWeaponType() != null) {
					if (suspectRO.getWeaponType().getId() != null && !suspectRO.getWeaponType().getId().trim().isEmpty()) {
						weaponType = _tableMaintenanceService.getWeaponTypeByCode(suspectRO.getWeaponType().getId().trim());
					}
				}
				validateWeaponInvolvedAndType(weaponInvolved, weaponType);
				suspect.setWeaponInvolved(weaponInvolved);
				suspect.setWeaponType(weaponType);
				// weapon type other
				if (suspectRO.getWeaponTypeOther() != null && !suspectRO.getWeaponTypeOther().trim().isEmpty()) {
					suspect.setWeaponTypeOther(suspectRO.getWeaponTypeOther().trim());
				}
				// Suspect Type
				if (suspectRO.getSuspectType() != null) {
					if (suspectRO.getSuspectType().getId() != null && !suspectRO.getSuspectType().getId().trim().isEmpty()) {
						suspect.setSuspectType(_tableMaintenanceService.getSuspectTypeByCode(suspectRO.getSuspectType().getId().trim()));
					}
				}
				// suspect addresses if any
				if (suspectRO.getAddresses() != null && !suspectRO.getAddresses().isEmpty()) {
					suspect.setAddresses(constructAddresses(suspectRO.getAddresses(), null, suspect, null, null, null, null));
				}
				// other comments for suspect type
				if (suspectRO.getSuspectTypeOther() != null && !suspectRO.getSuspectTypeOther().trim().isEmpty()) {
					suspect.setSuspectTypeOther(suspectRO.getSuspectTypeOther().trim());
				}
				// construct and set distinguishing feature details
				if (suspectRO.getDistinguishingFeatureDetails() != null && !suspectRO.getDistinguishingFeatureDetails().isEmpty()) {
					suspect.setDistinguishingFeatureDetails(constructDistinguishingFeatureDetails(suspectRO.getDistinguishingFeatureDetails()));
				}
				// other comments for distinguishing feature
				if (suspectRO.getDistinguishingFeatureOther() != null && !suspectRO.getDistinguishingFeatureOther().trim().isEmpty()) {
					suspect.setDistinguishingFeatureOther(suspectRO.getDistinguishingFeatureOther().trim());
				}
				// add to the list
				suspects.add(suspect);
			}
		}
		return suspects;
	}
	
	@Override
	public Set<Address> constructAddresses(final Set<AddressRO> addressROs, 
											final User user, 
											final Suspect suspect,
											final InjuredPerson injuredPerson, 
											final Witness witness, 
											final CrimeSuspect crimeSuspect,
											final Building building) {
		final Set<Address> addresses = new HashSet<Address>(0);
		// Address
		if (addressROs != null && !addressROs.isEmpty()) {
			for (AddressRO addressRO : addressROs) {
				if (addressRO != null) {
					final Address address = new Address.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
					if (address != null) {
						// This step determines to which entity the addresses are
						// constructed. It can be suspect, injured person, witness etc.
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
						// other address fields
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
						if (addressRO.getDoorNumber() != null && !addressRO.getDoorNumber().trim().isEmpty()) {
							address.setDoorNumber(addressRO.getDoorNumber().trim());
						}
						if (addressRO.getBlockNumber() != null && !addressRO.getBlockNumber().trim().isEmpty()) {
							address.setBlockNumber(addressRO.getBlockNumber().trim());
						}
						if (addressRO.getApartmentNumber() != null && !addressRO.getApartmentNumber().trim().isEmpty()) {
							address.setApartmentNumber(addressRO.getApartmentNumber().trim());
						}
						addresses.add(address);
					}
				}
			}
		}
		return addresses;
	}	

	@Override
	public Set<ReportedLoss> constructReportedLosses(final Set<ReportedLossRO> reportedLossROs, final Incident incident) {
		final Set<ReportedLoss> reportedLosses = new HashSet<ReportedLoss>(0);
		// Construction of reported loss
		if (reportedLossROs != null && !reportedLossROs.isEmpty()) {
			for (ReportedLossRO reportedLossRO : reportedLossROs) {
				if (reportedLossRO != null) {
					final ReportedLoss reportedLoss = new ReportedLoss.Builder().setStatusFlag(StatusFlag.ACTIVE).setIncident(incident).build();
					// Loss type
					if (reportedLossRO.getLossType() != null) {
						if (reportedLossRO.getLossType().getId() != null&& !reportedLossRO.getLossType().getId().trim().isEmpty()) {
							reportedLoss.setLossType(_tableMaintenanceService.getLossTypeByCode(reportedLossRO.getLossType().getId().trim()));
						}
					}
					// loss type other
					if (reportedLossRO.getLossTypeOther() != null && !reportedLossRO.getLossTypeOther().trim().isEmpty()) {
						reportedLoss.setLossTypeOther(reportedLossRO.getLossTypeOther().trim());
					}
					// Loss value
					if (reportedLossRO.getLossValue() != null) {
						reportedLoss.setLossValue(reportedLossRO.getLossValue());
					}
					// External agency contacted flag
					YesNoType externalAgencyContacted = YesNoType.N;
					if (reportedLossRO.getExternalAgencyContacted() != null
							&& reportedLossRO.getExternalAgencyContacted().name().equals("Y")) {
						externalAgencyContacted = YesNoType.Y;
					}
					// External agency
					ExternalAgency externalAgency = null;
					if (reportedLossRO.getExternalAgency() != null) {
						if (reportedLossRO.getExternalAgency().getId() != null && !reportedLossRO.getExternalAgency().getId().trim().isEmpty()) {
							externalAgency = _tableMaintenanceService.getExternalAgencyByCode(reportedLossRO.getExternalAgency().getId().trim());
						}
					}
					// external agency other
					if (reportedLossRO.getExternalAgencyTypeOther() != null && !reportedLossRO.getExternalAgencyTypeOther().trim().isEmpty()) {
						reportedLoss.setExternalAgencyTypeOther(reportedLossRO.getExternalAgencyTypeOther().trim());
					}
					// Date-time contacted
					LocalDateTime dateTimeContacted = null;
					if (reportedLossRO.getDateTimeContacted() != null) {
						dateTimeContacted = reportedLossRO.getDateTimeContacted();
					}
					validateExternalAgencyAndType(externalAgencyContacted, externalAgency, dateTimeContacted);
					reportedLoss.setExternalAgencyContacted(externalAgencyContacted);
					reportedLoss.setExternalAgency(externalAgency);
					reportedLoss.setDateTimeContacted(dateTimeContacted);
	
					// Cost estimation
					if (reportedLossRO.getCostEstimation() != null) {
						reportedLoss.setCostEstimation(reportedLossRO.getCostEstimation());
					}
					reportedLosses.add(reportedLoss);
				}
			}
		}
		return reportedLosses;
	}

	private Set<InjuredPerson> constructNewInjuredPersons(final Set<InjuredPersonRO> injuredPersonROs) {
		final Set<InjuredPerson> injuredPersons = new HashSet<InjuredPerson>(0);
		if (injuredPersonROs != null && !injuredPersonROs.isEmpty()) {
			for (InjuredPersonRO injuredPersonRO : injuredPersonROs) {
				final InjuredPerson injuredPerson = new InjuredPerson.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
				// Set other values for (new) injured person(s)
				// Injured person type
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
				// Gender type
				if (injuredPersonRO.getGenderType() != null) {
					if (injuredPersonRO.getGenderType().getId() != null && !injuredPersonRO.getGenderType().getId().trim().isEmpty()) {
						injuredPerson.setGenderType(_tableMaintenanceService.getGenderTypeByCode(injuredPersonRO.getGenderType().getId().trim()));
					}
				}
				// gender type other
				if (injuredPersonRO.getGenderTypeOther() != null && !injuredPersonRO.getGenderTypeOther().trim().isEmpty()) {
					injuredPerson.setGenderTypeOther(injuredPersonRO.getGenderTypeOther().trim());
				}
				// Injury Cause
				if (injuredPersonRO.getInjuryCause() != null) {
					if (injuredPersonRO.getInjuryCause().getId() != null && !injuredPersonRO.getInjuryCause().getId().trim().isEmpty()) {
						injuredPerson.setInjuryCause(_tableMaintenanceService.getInjuryCauseByCode(injuredPersonRO.getInjuryCause().getId().trim()));
					}
				}
				// injury cause other
				if (injuredPersonRO.getInjuryCauseOther() != null && injuredPersonRO.getInjuryCauseOther().trim().isEmpty()) {
					injuredPerson.setInjuryCauseOther(injuredPersonRO.getInjuryCauseOther());
				}
				// Injury type
				if (injuredPersonRO.getInjuryType() != null) {
					if (injuredPersonRO.getInjuryType().getId() != null && !injuredPersonRO.getInjuryType().getId().trim().isEmpty()) {
						injuredPerson.setInjuryType(_tableMaintenanceService.getInjuryTypeByCode(injuredPersonRO.getInjuryType().getId().trim()));
					}
				}
				// Injury type detail
				if (injuredPersonRO.getInjuryTypeDetail() != null) {
					if (injuredPersonRO.getInjuryTypeDetail().getId() != null && !injuredPersonRO.getInjuryTypeDetail().getId().trim().isEmpty()) {
						injuredPerson.setInjuryTypeDetail(_tableMaintenanceService.getInjuryTypeDetailByCode(injuredPersonRO.getInjuryTypeDetail().getId().trim()));
					}
				}
				// Injury type detail spec
				if (injuredPersonRO.getInjuryTypeDetailSpec() != null) {
					if (injuredPersonRO.getInjuryTypeDetailSpec().getId() != null && !injuredPersonRO.getInjuryTypeDetailSpec().getId().trim().isEmpty()) {
						injuredPerson.setInjuryTypeDetailSpec(_tableMaintenanceService.getInjuryTypeDetailSpecByCode(injuredPersonRO.getInjuryTypeDetailSpec().getId().trim()));
					}
				}
				// injury type other
				if (injuredPersonRO.getInjuryTypeOther() != null && !injuredPersonRO.getInjuryTypeOther().trim().isEmpty()) {
					injuredPerson.setInjuryTypeOther(injuredPersonRO.getInjuryTypeOther().trim());
				}
				// Age
				if (injuredPersonRO.getAge() != null && injuredPersonRO.getAge() > 0) {
					injuredPerson.setAge(injuredPersonRO.getAge());
				}
				// Date of Birth
				if (injuredPersonRO.getDateOfBirth() != null) {
					injuredPerson.setDateOfBirth(injuredPersonRO.getDateOfBirth());
				}
				// Any first aid given?
				YesNoType firstAidGiven = YesNoType.N;
				if (injuredPersonRO.getFirstAidGiven() != null
						&& injuredPersonRO.getFirstAidGiven().name().equals("Y")) {
					firstAidGiven = YesNoType.Y;
				}
				injuredPerson.setFirstAidGiven(firstAidGiven);
				// add addresses of the injured person
				if (injuredPersonRO.getAddresses() != null && !injuredPersonRO.getAddresses().isEmpty()) {
					injuredPerson.setAddresses(constructAddresses(injuredPersonRO.getAddresses(), null, null, injuredPerson, null, null, null));
				}
				// body parts
				if (injuredPersonRO.getBodyParts() != null && !injuredPersonRO.getBodyParts().isEmpty()) {
					injuredPerson.setBodyParts(constructBodyParts(injuredPersonRO.getBodyParts()));
				}
				// other comments for injured person type
				if (injuredPersonRO.getInjuredPersonTypeOther() != null && !injuredPersonRO.getInjuredPersonTypeOther().trim().isEmpty()) {
					injuredPerson.setInjuredPersonTypeOther(injuredPersonRO.getInjuredPersonTypeOther().trim());
				}
				// construct and set distinguishing feature details
				if (injuredPerson.getDistinguishingFeatureDetails() == null || injuredPerson.getDistinguishingFeatureDetails().isEmpty()) {
					injuredPerson.setDistinguishingFeatureDetails(new HashSet<DistinguishingFeatureDetail>(0));
				}
				injuredPerson.getDistinguishingFeatureDetails().addAll(constructDistinguishingFeatureDetails(injuredPersonRO.getDistinguishingFeatureDetails()));
				// other comments for distinguishing feature
				if (injuredPersonRO.getDistinguishingFeatureOther() != null && !injuredPersonRO.getDistinguishingFeatureOther().trim().isEmpty()) {
					injuredPerson.setDistinguishingFeatureOther(injuredPersonRO.getDistinguishingFeatureOther().trim());
				}
				//add address of the injured person if any
				if (injuredPersonRO.getAddresses() != null && !injuredPersonRO.getAddresses().isEmpty()) {
					injuredPerson.setAddresses(constructAddresses(injuredPersonRO.getAddresses(), null, null, injuredPerson, null, null, null));
				}
				// add the injured person to the list
				injuredPersons.add(injuredPerson);
			}
		}
		return injuredPersons;
	}

	private Accident constructAccident(final AccidentRO accidentRO, final Incident incident) {
		final Accident accident = new Accident.Builder().setStatusFlag(StatusFlag.ACTIVE).setIncident(incident).build();
		// Construct the accident object from the RO
		if (accidentRO != null) {
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
		}
		return accident;
	}

	private Set<Witness> constructNewWitnesses(final Set<WitnessRO> witnessROs) {
		final Set<Witness> witnesses = new HashSet<Witness>(0);
		// Construct new witnesses and add to accident.
		if (witnessROs != null && !witnessROs.isEmpty()) {
			for (WitnessRO witnessRO : witnessROs) {
				final Witness witness = new Witness.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
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
					witness.setAddresses(constructAddresses(witnessRO.getAddresses(), null, null, null, witness, null, null));
				}
				// other comments for witness type
				if (witnessRO.getWitnessTypeOther() != null && !witnessRO.getWitnessTypeOther().trim().isEmpty()) {
					witness.setWitnessTypeOther(witnessRO.getWitnessTypeOther().trim());
				}
				// construct and set distinguishing feature details
				if (witness.getDistinguishingFeatureDetails() == null || witness.getDistinguishingFeatureDetails().isEmpty()) {
					witness.setDistinguishingFeatureDetails(new HashSet<DistinguishingFeatureDetail>(0));
				}
				witness.getDistinguishingFeatureDetails().addAll(constructDistinguishingFeatureDetails(witnessRO.getDistinguishingFeatureDetails()));
				// other comments for distinguishing feature
				if (witnessRO.getDistinguishingFeatureOther() != null && !witnessRO.getDistinguishingFeatureOther().trim().isEmpty()) {
					witness.setDistinguishingFeatureOther(witnessRO.getDistinguishingFeatureOther().trim());
				}
				witnesses.add(witness);
			}
		}
		return witnesses;
	}

	private Asset constructAsset(final AssetRO assetRO, final Incident incident) {
		final Asset asset = new Asset.Builder().setStatusFlag(StatusFlag.ACTIVE).setIncident(incident).build();
		if (assetRO != null) {
			// asset category
			if (assetRO.getAssetCategory() != null) {
				if (assetRO.getAssetCategory().getId() != null && !assetRO.getAssetCategory().getId().trim().isEmpty()) {
					asset.setAssetCategory(_tableMaintenanceService.getAssetCategoryByCode(assetRO.getAssetCategory().getId().trim()));
				}
			}
			// other desc
			if (assetRO.getOtherDescription() != null && !assetRO.getOtherDescription().trim().isEmpty()) {
				asset.setOtherDescription(assetRO.getOtherDescription().trim());
			}
		}
		return asset;
	}

	private Set<Building> constructBuildings(final List<BuildingRO> buildingROs) {
		final Set<Building> buildings = new HashSet<Building>(0);
		if (buildingROs != null && !buildingROs.isEmpty()) {
			for (BuildingRO buildingRO : buildingROs) {
				final Building building = new Building.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
				// building id
				if (buildingRO.getBuildingId() != null && !buildingRO.getBuildingId().trim().isEmpty()) {
					building.setBuildingId(buildingRO.getBuildingId().trim());
				}
				// building description
				if (buildingRO.getBuildingDescription() != null && !buildingRO.getBuildingDescription().trim().isEmpty()) {
					building.setBuildingDescription(buildingRO.getBuildingDescription().trim());
				}
				// building name
				if (buildingRO.getBuildingName() != null && !buildingRO.getBuildingName().trim().isEmpty()) {
					building.setBuildingName(buildingRO.getBuildingName().trim());
				}
				// asset category
				if (buildingRO.getAssetCategory() != null) {
					if (buildingRO.getAssetCategory().getId() != null && !buildingRO.getAssetCategory().getId().trim().isEmpty()) {
						building.setAssetCategory(_tableMaintenanceService.getAssetCategoryByCode(buildingRO.getAssetCategory().getId().trim()));
					}
				}
				// building address if any
				if (buildingRO.getAddresses() != null) {
					building.setAddresses(constructAddresses(buildingRO.getAddresses(), null, null, null, null, null, building));
				}
				buildings.add(building);
			}
		}
		return buildings;
	}

	private Set<Equipment> constructEquipments(final List<EquipmentRO> equipmentROs) {
		final Set<Equipment> equipments = new HashSet<Equipment>(0);
		if (equipmentROs != null && !equipmentROs.isEmpty()) {
			for (EquipmentRO equipmentRO : equipmentROs) {
				final Equipment equipment = new Equipment.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
				// equipment id
				if (equipmentRO.getEquipmentId() != null && !equipmentRO.getEquipmentId().trim().isEmpty()) {
					equipment.setEquipmentId(equipmentRO.getEquipmentId().trim());
				}
				// equipment details
				if (equipmentRO.getEquipmentDetails() != null && !equipmentRO.getEquipmentDetails().trim().isEmpty()) {
					equipment.setEquipmentDetails(equipmentRO.getEquipmentDetails().trim());
				}
				// serial number
				if (equipmentRO.getSerialNumber() != null && !equipmentRO.getSerialNumber().trim().isEmpty()) {
					equipment.setSerialNumber(equipmentRO.getSerialNumber().trim());
				}
				// asset category
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

	private Set<Vehicle> constructVehicles(final List<VehicleRO> vehicleROs) {
		final Set<Vehicle> vehicles = new HashSet<Vehicle>(0);
		if (vehicleROs != null && !vehicleROs.isEmpty()) {
			for (VehicleRO vehicleRO : vehicleROs) {
				final Vehicle vehicle = new Vehicle.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
				// vehicle registration id
				if (nullOrEmptySafeCheck(vehicleRO.getVehicleRegistrationId())) {
					vehicle.setVehicleRegistrationId(stringTrimmer(vehicleRO.getVehicleRegistrationId()));
				}
				// engine number
				if (nullOrEmptySafeCheck(vehicleRO.getEngineNumber())) {
					vehicle.setEngineNumber(stringTrimmer(vehicleRO.getEngineNumber()));
				}
				// chasis number
				if (nullOrEmptySafeCheck(vehicleRO.getChasisNumber())) {
					vehicle.setChasisNumber(stringTrimmer(vehicleRO.getChasisNumber()));
				}
				// make
				if (nullOrEmptySafeCheck(vehicleRO.getMake())) {
					vehicle.setMake(stringTrimmer(vehicleRO.getMake()));
				}
				// model
				if (nullOrEmptySafeCheck(vehicleRO.getModel())) {
					vehicle.setModel(stringTrimmer(vehicleRO.getModel()));
				}
				// comment description
				if (nullOrEmptySafeCheck(vehicleRO.getCommentDescription())) {
					vehicle.setCommentDescription(stringTrimmer(vehicleRO.getCommentDescription()));
				}
				// Vehicle damage type
				if (vehicleRO.getVehicleDamageType() != null) {
					if (nullOrEmptySafeCheck(vehicleRO.getVehicleDamageType().getId())) {
						vehicle.setVehicleDamageType(_tableMaintenanceService.getVehicleDamageTypeByCode(stringTrimmer(vehicleRO.getVehicleDamageType().getId())));
					}
				}
				// asset category
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
	
	@Override
	public Set<BodyPart> constructBodyParts(final Set<BodyPartRO> bodyPartROs) {
		final Set<BodyPart> bodyParts = new HashSet<BodyPart>(0);
		if (bodyPartROs != null && !bodyPartROs.isEmpty()) {
			for (BodyPartRO bodyPartRO : bodyPartROs) {
				if (bodyPartRO.getId() != null && !bodyPartRO.getId().trim().isEmpty()) {
					final BodyPart bodyPart = _tableMaintenanceService.getBodyPartByCode(bodyPartRO.getId().trim());
					if (bodyPart != null) {
						bodyParts.add(bodyPart);
					}
				}
			}
		}
		return bodyParts;
	}

	private Crime constructCrime(final CrimeRO crimeRO, final Incident incident) {
		final Crime crime = new Crime.Builder().setStatusFlag(StatusFlag.ACTIVE).setIncident(incident).build();
		// set other values
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
	}
	
	private Claim constructClaim(final ClaimRO claimRO, final Incident incident) {
		final Claim claim = new Claim.Builder().setStatusFlag(StatusFlag.ACTIVE).setIncident(incident).build();
		if (claim != null && claimRO != null) {
			//claim status
			if (claimRO.getClaimStatus() != null) {
				if (claimRO.getClaimStatus().getId() != null && !claimRO.getClaimStatus().getId().trim().isEmpty()) {
					claim.setClaimStatus(_tableMaintenanceService.getClaimStatusByCode(claimRO.getClaimStatus().getId().trim()));
				}				
			}
			//claim type
			if (claimRO.getClaimType() != null) {
				if (claimRO.getClaimType().getId() != null && !claimRO.getClaimType().getId().trim().isEmpty()) {
					claim.setClaimType(_tableMaintenanceService.getClaimTypeByCode(claimRO.getClaimType().getId().trim()));
				}				
			}
			//claim request registration type
			if (claimRO.getClaimRequestRegistrationType() != null) {
				if (claimRO.getClaimRequestRegistrationType().getId() != null && !claimRO.getClaimRequestRegistrationType().getId().trim().isEmpty()) {
					claim.setClaimRequestRegistrationType(_tableMaintenanceService.getClaimRequestRegistrationTypeByCode(claimRO.getClaimRequestRegistrationType().getId().trim()));
				}				
			}
			//claimant name
			if (claimRO.getClaimantName() != null && !claimRO.getClaimantName().trim().isEmpty()) {
				claim.setClaimantName(claimRO.getClaimantName().trim());
			}
			//claim id (auto generated)
			claim.setClaimId(ApplicationUtilService.getUniqueClaimId());
			//insured name
			if (claimRO.getInsuredName() != null && !claimRO.getInsuredName().trim().isEmpty()) {
				claim.setInsuredName(claimRO.getInsuredName().trim());
			}
			//policy number
			if (claimRO.getPolicyNumber() != null && !claimRO.getPolicyNumber().trim().isEmpty()) {
				claim.setPolicyNumber(claimRO.getPolicyNumber().trim());
			}
			//policy holder name
			if (claimRO.getPolicyHolderName() != null && !claimRO.getPolicyHolderName().trim().isEmpty()) {
				claim.setPolicyHolderName(claimRO.getPolicyHolderName().trim());
			}
			//policy type
			if (claimRO.getPolicyType() != null) {
				if (claimRO.getPolicyType().getId() != null && !claimRO.getPolicyType().getId().trim().isEmpty()) {
					claim.setPolicyType(_tableMaintenanceService.getPolicyTypeByCode(claimRO.getPolicyType().getId().trim()));
				}				
			}
			//security requested
			YesNoType securityRequested = YesNoType.N;
			if (claimRO.getSecurityRequested() != null && claimRO.getSecurityRequested().name().equals("Y")) {
				securityRequested = YesNoType.Y;
			}
			claim.setSecurityRequested(securityRequested);			
			//training requested
			YesNoType trainingRequested = YesNoType.N;
			if (claimRO.getTrainingRequested() != null && claimRO.getTrainingRequested().name().equals("Y")) {
				trainingRequested = YesNoType.Y;
			}
			claim.setTrainingRequested(trainingRequested);
			//claim handler is automatically assigned by the batch program.
			//set other values for claim history
			//amount
			if (claimRO.getClaimRequestedAmount() != null) {
				claim.setClaimRequestedAmount(claimRO.getClaimRequestedAmount());
			}
			if (claimRO.getClaimApprovedAmount() != null) {
				claim.setClaimApprovedAmount(claimRO.getClaimApprovedAmount());
			}
			if (claimRO.getClaimSettlementAmount() != null) {
				claim.setClaimSettlementAmount(claimRO.getClaimSettlementAmount());
			}
			//date
			if (claimRO.getClaimRequestedDate() != null) {
				claim.setClaimRequestedDate(claimRO.getClaimRequestedDate());
			}
			if (claimRO.getClaimApprovedDate() != null) {
				claim.setClaimApprovedDate(claimRO.getClaimApprovedDate());
			}
			if (claimRO.getClaimSettlementDate() != null) {
				claim.setClaimSettlementDate(claimRO.getClaimSettlementDate());
			}
			if (claimRO.getClaimDeclinedDate() != null) {
				claim.setClaimDeclinedDate(claimRO.getClaimDeclinedDate());
			}
			if (claimRO.getClaimReopenedDate() != null) {
				claim.setClaimReopenedDate(claimRO.getClaimReopenedDate());
			}
			//requested by
			if (claimRO.getClaimRequestedBy() != null && !claimRO.getClaimRequestedBy().trim().isEmpty()) {
				claim.setClaimRequestedBy(claimRO.getClaimRequestedBy().trim());
			}			
			if (claimRO.getClaimApprovedBy() != null && !claimRO.getClaimApprovedBy().trim().isEmpty()) {
				claim.setClaimApprovedBy(claimRO.getClaimApprovedBy().trim());
			}
			if (claimRO.getClaimSettlementBy() != null && !claimRO.getClaimSettlementBy().trim().isEmpty()) {
				claim.setClaimSettlementBy(claimRO.getClaimSettlementBy().trim());
			}
			if (claimRO.getClaimDeclinedBy() != null && !claimRO.getClaimDeclinedBy().trim().isEmpty()) {
				claim.setClaimDeclinedBy(claimRO.getClaimDeclinedBy().trim());
			}
			if (claimRO.getClaimReopenedBy() != null && !claimRO.getClaimReopenedBy().trim().isEmpty()) {
				claim.setClaimReopenedBy(claimRO.getClaimReopenedBy().trim());
			}
			//comments
			if (claimRO.getClaimRequestedComments() != null && !claimRO.getClaimRequestedComments().trim().isEmpty()) {
				claim.setClaimRequestedComments(claimRO.getClaimRequestedComments().trim());
			}
			if (claimRO.getClaimApprovedComments() != null && !claimRO.getClaimApprovedComments().trim().isEmpty()) {
				claim.setClaimApprovedComments(claimRO.getClaimApprovedComments().trim());
			}
			if (claimRO.getClaimSettlementComments() != null && !claimRO.getClaimSettlementComments().trim().isEmpty()) {
				claim.setClaimSettlementComments(claimRO.getClaimSettlementComments().trim());
			}
			if (claimRO.getClaimDeclinedComments() != null && !claimRO.getClaimDeclinedComments().trim().isEmpty()) {
				claim.setClaimDeclinedComments(claimRO.getClaimDeclinedComments().trim());
			}
			if (claimRO.getClaimReopenedComments() != null && !claimRO.getClaimReopenedComments().trim().isEmpty()) {
				claim.setClaimReopenedComments(claimRO.getClaimReopenedComments().trim());
			}
			//fields for others
			if (claimRO.getClaimTypeOther() != null && !claimRO.getClaimTypeOther().trim().isEmpty()) {
				claim.setClaimTypeOther(claimRO.getClaimTypeOther().trim());
			}
			if (claimRO.getClaimRequestRegistrationTypeOther() != null && !claimRO.getClaimRequestRegistrationTypeOther().trim().isEmpty()) {
				claim.setClaimRequestRegistrationTypeOther(claimRO.getClaimRequestRegistrationTypeOther().trim());
			}
			if (claimRO.getPolicyTypeOther() != null && !claimRO.getPolicyTypeOther().trim().isEmpty()) {
				claim.setPolicyTypeOther(claimRO.getPolicyTypeOther());
			}
			return claim;
		} else {
			return null;
		}
	}
	
	private Investigation constructNewInvestigation(final InvestigationRO investigationRO, final Incident incident) {
		final Investigation investigation = new Investigation.Builder().setStatusFlag(StatusFlag.ACTIVE).setIncident(incident).build();
		if (investigationRO != null) {
			
			YesNoType securityRequested = YesNoType.N;
			YesNoType trainingRequested = YesNoType.N;
			YesNoType reviewedInvestigationRecords = YesNoType.N;
			YesNoType reviewedCCTV = YesNoType.N;
			YesNoType reviewedPictures = YesNoType.N;
			YesNoType reviewedWitnessStatement = YesNoType.N;
			YesNoType reviewedLearnerRecords = YesNoType.N;
			YesNoType reviewedAssetRecords = YesNoType.N;
			YesNoType reviewedComplianceRecords = YesNoType.N;
			
			if (investigationRO.getSecurityRequested() != null && investigationRO.getSecurityRequested().name().equals("Y")) {
				securityRequested = YesNoType.Y;
			}
			if (investigationRO.getTrainingRequested() != null && investigationRO.getTrainingRequested().name().equals("Y")) {
				trainingRequested = YesNoType.Y;
			}
			if (investigationRO.getReviewedInvestigationRecords() != null && investigationRO.getReviewedInvestigationRecords().name().equals("Y")) {
				reviewedInvestigationRecords = YesNoType.Y;
			}
			if (investigationRO.getReviewedCCTV() != null && investigationRO.getReviewedCCTV().name().equals("Y")) {
				reviewedCCTV = YesNoType.Y;
			}
			if (investigationRO.getReviewedPictures() != null && investigationRO.getReviewedPictures().name().equals("Y")) {
				reviewedPictures = YesNoType.Y;
			}
			if (investigationRO.getReviewedWitnessStatement() != null && investigationRO.getReviewedWitnessStatement().name().equals("Y")) {
				reviewedWitnessStatement = YesNoType.Y;
			}
			if (investigationRO.getReviewedLearnerRecords() != null && investigationRO.getReviewedLearnerRecords().name().equals("Y")) {
				reviewedLearnerRecords = YesNoType.Y;
			}
			if (investigationRO.getReviewedAssetRecords() != null && investigationRO.getReviewedAssetRecords().name().equals("Y")) {
				reviewedAssetRecords = YesNoType.Y;
			}
			if (investigationRO.getReviewedComplianceRecords() != null && investigationRO.getReviewedComplianceRecords().name().equals("Y")) {
				reviewedComplianceRecords = YesNoType.Y;
			}
			
			investigation.setSecurityRequested(securityRequested);
			investigation.setTrainingRequested(trainingRequested);
			investigation.setReviewedInvestigationRecords(reviewedInvestigationRecords);
			investigation.setReviewedCCTV(reviewedCCTV);
			investigation.setReviewedPictures(reviewedPictures);
			investigation.setReviewedWitnessStatement(reviewedWitnessStatement);
			investigation.setReviewedLearnerRecords(reviewedLearnerRecords);
			investigation.setReviewedAssetRecords(reviewedAssetRecords);
			investigation.setReviewedComplianceRecords(reviewedComplianceRecords);
			
			if (investigationRO.getInvestigator() != null) {
				if (investigationRO.getInvestigator().getLoginId() != null && !investigationRO.getInvestigator().getLoginId().trim().isEmpty()) {
					final User investigator = _userService.getUserByUserLoginId(investigationRO.getInvestigator().getLoginId().trim());
					if (investigator != null) {
						investigation.setInvestigator(investigator);
					}
				}
			}
			
			if (investigationRO.getInvestigatorStatement() != null && !investigationRO.getInvestigatorStatement().trim().isEmpty()) {
				investigation.setInvestigatorStatement(investigationRO.getInvestigatorStatement().trim());
			}
		}
		
		return investigation;
	}	

	private Set<CrimeSuspect> constructNewCrimeSuspects(final Set<CrimeSuspectRO> crimeSuspectROs) {
		final Set<CrimeSuspect> crimeSuspects = new HashSet<CrimeSuspect>(0);
		if (crimeSuspectROs != null && !crimeSuspectROs.isEmpty()) {
			for (CrimeSuspectRO crimeSuspectRO : crimeSuspectROs) {
				final CrimeSuspect crimeSuspect = new CrimeSuspect.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
				// title
				if (nullOrEmptySafeCheck(crimeSuspectRO.getTitle())) {
					crimeSuspect.setTitle(stringTrimmer(crimeSuspectRO.getTitle()));
				}
				// first name
				if (nullOrEmptySafeCheck(crimeSuspectRO.getFirstName())) {
					crimeSuspect.setFirstName(stringTrimmer(crimeSuspectRO.getFirstName()));
				}
				// middle name
				if (nullOrEmptySafeCheck(crimeSuspectRO.getMiddleName())) {
					crimeSuspect.setMiddleName(stringTrimmer(crimeSuspectRO.getMiddleName()));
				}
				// last name
				if (nullOrEmptySafeCheck(crimeSuspectRO.getLastName())) {
					crimeSuspect.setLastName(stringTrimmer(crimeSuspectRO.getLastName()));
				}
				// name suffix
				if (nullOrEmptySafeCheck(crimeSuspectRO.getNameSuffix())) {
					crimeSuspect.setNameSuffix(stringTrimmer(crimeSuspectRO.getNameSuffix()));
				}
				// gender type
				if (crimeSuspectRO.getGenderType() != null) {
					if (nullOrEmptySafeCheck(crimeSuspectRO.getGenderType().getId())) {
						crimeSuspect.setGenderType(_tableMaintenanceService.getGenderTypeByCode(stringTrimmer(crimeSuspectRO.getGenderType().getId())));
					}
				}
				// gender type other
				if (crimeSuspectRO.getGenderTypeOther() != null && !crimeSuspectRO.getGenderTypeOther().trim().isEmpty()) {
					crimeSuspect.setGenderTypeOther(crimeSuspectRO.getGenderTypeOther().trim());
				}
				// Crime Suspect Type
				if (crimeSuspectRO.getCrimeSuspectType() != null) {
					if (crimeSuspectRO.getCrimeSuspectType().getId() != null && !crimeSuspectRO.getCrimeSuspectType().getId().trim().isEmpty()) {
						crimeSuspect.setCrimeSuspectType(_tableMaintenanceService.getSuspectTypeByCode(crimeSuspectRO.getCrimeSuspectType().getId().trim()));
					}
				}
				// other comments for crime suspect type
				if (crimeSuspectRO.getCrimeSuspectTypeOther() != null && !crimeSuspectRO.getCrimeSuspectTypeOther().trim().isEmpty()) {
					crimeSuspect.setCrimeSuspectTypeOther(crimeSuspectRO.getCrimeSuspectTypeOther().trim());
				}
				// date of birth
				if (crimeSuspectRO.getDateOfBirth() != null) {
					crimeSuspect.setDateOfBirth(crimeSuspectRO.getDateOfBirth());
				}
				// age
				if (crimeSuspectRO.getAge() != null && crimeSuspectRO.getAge() > 0) {
					crimeSuspect.setAge(crimeSuspectRO.getAge());
				}
				// phone
				if (nullOrEmptySafeCheck(crimeSuspectRO.getPhone())) {
					crimeSuspect.setPhone(stringTrimmer(crimeSuspectRO.getPhone()));
				}
				// alternate phone
				if (nullOrEmptySafeCheck(crimeSuspectRO.getAlternatePhone())) {
					crimeSuspect.setAlternatePhone(stringTrimmer(crimeSuspectRO.getAlternatePhone()));
				}
				// email
				if (nullOrEmptySafeCheck(crimeSuspectRO.getEmail())) {
					crimeSuspect.setEmail(stringTrimmer(crimeSuspectRO.getEmail()));
				}
				// website
				if (nullOrEmptySafeCheck(crimeSuspectRO.getWebsite())) {
					crimeSuspect.setWebsite(stringTrimmer(crimeSuspectRO.getWebsite()));
				}
				// crime addresses if any
				if (crimeSuspectRO.getAddresses() != null) {
					crimeSuspect.setAddresses(constructAddresses(crimeSuspectRO.getAddresses(), null, null, null, null, crimeSuspect, null));
				}
				// construct and set distinguishing feature details
				if (crimeSuspect.getDistinguishingFeatureDetails() == null || crimeSuspect.getDistinguishingFeatureDetails().isEmpty()) {
					crimeSuspect.setDistinguishingFeatureDetails(new HashSet<DistinguishingFeatureDetail>(0));
				}
				crimeSuspect.getDistinguishingFeatureDetails().addAll(constructDistinguishingFeatureDetails(crimeSuspectRO.getDistinguishingFeatureDetails()));
				// other comments for distinguishing feature
				if (crimeSuspectRO.getDistinguishingFeatureOther() != null && !crimeSuspectRO.getDistinguishingFeatureOther().trim().isEmpty()) {
					crimeSuspect.setDistinguishingFeatureOther(crimeSuspectRO.getDistinguishingFeatureOther().trim());
				}
				crimeSuspects.add(crimeSuspect);
			}
		}
		return crimeSuspects;
	}
	
	@Override
	public Set<Suspect> getSuspects(final Set<SuspectRO> suspectROs) {
		final Set<Suspect> suspects = new HashSet<Suspect>(0);
		if (suspectROs != null && !suspectROs.isEmpty()) {
			for (SuspectRO suspectRO : suspectROs) {
				if (suspectRO != null && suspectRO.getId() > 0) {
					final Suspect suspect = _suspectService.get(suspectRO.getId());
					if (suspect != null) {
						suspects.add(suspect);
					}
				}
			}
		}
		return suspects;
	}
	
	@Override
	public Set<DistinguishingFeatureDetail> constructDistinguishingFeatureDetails(final Set<DistinguishingFeatureDetailRO> distinguishingFeatureDetailROs) {
		final Set<DistinguishingFeatureDetail> distinguishingFeatureDetails = new HashSet<DistinguishingFeatureDetail>(0);
		if (distinguishingFeatureDetailROs != null && !distinguishingFeatureDetailROs.isEmpty()) {
			for (DistinguishingFeatureDetailRO distinguishingFeatureDetailRO : distinguishingFeatureDetailROs) {
				if (distinguishingFeatureDetailRO.getId() != null && !distinguishingFeatureDetailRO.getId().trim().isEmpty()) {
					final DistinguishingFeatureDetail distinguishingFeatureDetail = _tableMaintenanceService.getDistinguishingFeatureDetailByCode(distinguishingFeatureDetailRO.getId().trim());
					if (distinguishingFeatureDetail != null) {
						distinguishingFeatureDetails.add(distinguishingFeatureDetail);
					}
				}
			}
		}
		return distinguishingFeatureDetails;
	}
	
	@Override
	public void validateWeaponInvolvedAndType(final YesNoType weaponInvolved, final WeaponType weaponType) {
		if (weaponInvolved.name().equals(YesNoType.Y)) {
			// When there was a weapon involved, weapon type should be
			// specified.
			if (weaponType == null) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.WEAPON_TYPE_MUST_BE_SPECIFIED_WHEN_WEAPON_INVOLVED));
			}
		} else if (weaponInvolved.name().equals(YesNoType.N)) {
			// Weapon type is only applicable if there was any weapon involved.
			if (weaponType != null) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.WEAPON_TYPE_NOT_APPLICABLE_WITHOUT_WEAPON_INVOLVED));
			}
		}
	}
	
	@Override
	public void validateExternalAgencyAndType(final YesNoType externalAgencyContacted,
			final ExternalAgency externalAgency, final LocalDateTime dateTimeContacted) {
		if (externalAgencyContacted.name().equals(YesNoType.Y)) {
			// External agency should be specified when an agency was contacted
			if (externalAgency == null) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.EXTERNAL_AGENCY_MUST_BE_SPECIFIED_WHEN_AGENCY_CONTACTED));
			}
			if (dateTimeContacted == null) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.EXTERNAL_AGENCY_DATE_TIME_MUST_BE_SPECIFIED_WHEN_AGENCY_CONTACTED));
			}
		} else if (externalAgencyContacted.name().equals(YesNoType.N)) {
			// External agency is only applicable if there was any external
			// agency contacted.
			if (externalAgency != null) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.EXTERNAL_AGENCY_NOT_APPLICABLE_WITHOUT_AGENCY_CONTACTED));
			}
			if (dateTimeContacted != null) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.EXTERNAL_AGENCY_DATE_TIME_NOT_APPLICABLE_WITHOUT_AGENCY_CONTACTED));
			}
		}
	}
	
	@Override
	public void validateAnyWitness(final YesNoType anyWitness, 
									final Set<Witness> newWitnesses,
									final Set<Witness> existingWitnesses, 
									final Set<User> employeeWitnesses) {
		boolean witnessesPresent = false;

		if (newWitnesses != null && !newWitnesses.isEmpty()) {
			witnessesPresent = true;
		}
		if (existingWitnesses != null && !existingWitnesses.isEmpty()) {
			witnessesPresent = true;
		}
		if (employeeWitnesses != null && !employeeWitnesses.isEmpty()) {
			witnessesPresent = true;
		}

		if (anyWitness.name().equals(YesNoType.Y)) {
			// Please mark the witness flag to No when there are no witnesses.
			if (!witnessesPresent) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.WITNESS_FLAG_MUST_BE_NO));
			}
		} else if (anyWitness.name().equals(YesNoType.N)) {
			// Please mark the witness flag to Yes when witness details are
			// added.
			if (witnessesPresent) {
				throw new ResourceNotValidException(_messageBuilder.build(RestMessage.WITNESS_FLAG_MUST_BE_YES));
			}
		}
	}
	
	@Override
	public Incident getIncident(final BaseIncidentDetailRO baseIncidentDetailRO) {
		Incident incident = null;
		if (baseIncidentDetailRO != null) {
			if (baseIncidentDetailRO.getIncidentId() != null && baseIncidentDetailRO.getIncidentId() > 0) {
				incident = _incidentService.get(baseIncidentDetailRO.getIncidentId());
			} else if (baseIncidentDetailRO.getUniqueIncidentId() != null && !baseIncidentDetailRO.getUniqueIncidentId().trim().isEmpty()) {
				incident = _incidentService.getIncidentByUniqueIncidentId(baseIncidentDetailRO.getUniqueIncidentId().trim());
			}
		}
		return incident;
	}
	
	private boolean addOrUpdateCheckForIncident(final LogIncidentRO logIncidentRO) {
		boolean result = false;
		if (logIncidentRO != null) {
			if (logIncidentRO.getIncidentId() != null && logIncidentRO.getIncidentId() > 0) {
				result = true;
			} else if (logIncidentRO.getUniqueIncidentId() != null && !logIncidentRO.getUniqueIncidentId().trim().isEmpty()) {
				result = true;
			}
		}
		return result;
	}
	
	private Incident setOtherValuesForIncident(final Incident incident, final LogIncidentRO logIncidentRO) {
		if (incident != null && logIncidentRO != null) {
			// Set other values
			if (logIncidentRO.getPlaceOfIncident() != null && !logIncidentRO.getPlaceOfIncident().trim().isEmpty()) {
				incident.setPlaceOfIncident(logIncidentRO.getPlaceOfIncident());
			}
			if (logIncidentRO.getIncidentDescription() != null
					&& !logIncidentRO.getIncidentDescription().trim().isEmpty()) {
				incident.setIncidentDescription(logIncidentRO.getIncidentDescription());
			}
			// incident type
			if (logIncidentRO.getIncidentType() != null) {
				if (logIncidentRO.getIncidentType().getId() != null && !logIncidentRO.getIncidentType().getId().trim().isEmpty()) {
					final IncidentType incidentType = _tableMaintenanceService.getIncidentTypeByCode(logIncidentRO.getIncidentType().getId().trim());
					if (incidentType != null) {
						incident.setIncidentType(incidentType);
					}
				}
			}
			// incident type other
			if (logIncidentRO.getIncidentTypeOther() != null && !logIncidentRO.getIncidentTypeOther().trim().isEmpty()) {
				incident.setIncidentTypeOther(logIncidentRO.getIncidentTypeOther().trim());
			}
			// entry point
			if (logIncidentRO.getEntryPoint() != null) {
				if (logIncidentRO.getEntryPoint().getId() != null && !logIncidentRO.getEntryPoint().getId().trim().isEmpty()) {
					final EntryPoint entryPoint = _tableMaintenanceService.getEntryPointByCode(logIncidentRO.getEntryPoint().getId().trim());
					if (entryPoint != null) {
						incident.setEntryPoint(entryPoint);
					}
				}
			}
			// entry point other
			if (logIncidentRO.getEntryPointOther() != null && !logIncidentRO.getEntryPointOther().trim().isEmpty()) {
				incident.setEntryPointOther(logIncidentRO.getEntryPointOther().trim());
			}
			// Incident location
			if (logIncidentRO.getIncidentLocation() != null) {
				if (logIncidentRO.getIncidentLocation().getId() != null && !logIncidentRO.getIncidentLocation().getId().trim().isEmpty()) {
					final IncidentLocation incidentLocation = _tableMaintenanceService.getIncidentLocationByCode(logIncidentRO.getIncidentLocation().getId().trim());
					if (incidentLocation != null) {
						incident.setIncidentLocation(incidentLocation);
					}
				}
			}
			// Incident location detail
			if (logIncidentRO.getIncidentLocationDetail() != null) {
				if (logIncidentRO.getIncidentLocationDetail().getId() != null && !logIncidentRO.getIncidentLocationDetail().getId().trim().isEmpty()) {
					final IncidentLocationDetail incidentLocationDetail = _tableMaintenanceService .getIncidentLocationDetailByCode(logIncidentRO.getIncidentLocationDetail().getId().trim());
					if (incidentLocationDetail != null) {
						incident.setIncidentLocationDetail(incidentLocationDetail);
					}
				}
			}
			// incident location other
			if (logIncidentRO.getIncidentLocationOther() != null && !logIncidentRO.getIncidentLocationOther().trim().isEmpty()) {
				incident.setIncidentLocationOther(logIncidentRO.getIncidentLocationOther().trim());
			}
			// set office address (obtained from lookup)
			if (logIncidentRO.getOfficeAddress() != null) {
				if (logIncidentRO.getOfficeAddress().getId() > 0) {
					final OfficeAddress officeAddress = _officeAddressService.get(logIncidentRO.getOfficeAddress().getId());
					if (officeAddress != null) {
						incident.setOfficeAddress(officeAddress);
					}
				}
			}
			// Set yes or no type fields
			YesNoType assetDamage = YesNoType.N; 
			if (logIncidentRO.getAssetDamage() != null && logIncidentRO.getAssetDamage().name().equals("Y")) {
				assetDamage = YesNoType.Y;
			}
			incident.setAssetDamage(assetDamage);
			
			YesNoType criminalAttack = YesNoType.N;
			if (logIncidentRO.getCriminalAttack() != null && logIncidentRO.getCriminalAttack().name().equals("Y")) {
				criminalAttack = YesNoType.Y;
			}
			incident.setCriminalAttack(criminalAttack);
			
			YesNoType accidentDamage = YesNoType.N;
			if (logIncidentRO.getAccidentDamage() != null && logIncidentRO.getAccidentDamage().name().equals("Y")) {
				accidentDamage = YesNoType.Y;
			}
			incident.setAccidentDamage(accidentDamage);
			
			YesNoType notifyClaimsHandler = YesNoType.N;
			if (logIncidentRO.getNotifyClaimsHandler() != null && logIncidentRO.getNotifyClaimsHandler().name().equals("Y")) {
				notifyClaimsHandler = YesNoType.Y;
			}
			incident.setNotifyClaimsHandler(notifyClaimsHandler);
			
			YesNoType showClaims = YesNoType.N;
			if (logIncidentRO.getShowClaims() != null && logIncidentRO.getShowClaims().name().equals("Y")) {
				showClaims = YesNoType.Y;
			}
			incident.setShowClaims(showClaims);
			
			YesNoType showInvestigation = YesNoType.N;
			if (logIncidentRO.getShowInvestigation() != null && logIncidentRO.getShowInvestigation().name().equals("Y")) {
				showInvestigation = YesNoType.Y;
			}
			incident.setShowInvestigation(showInvestigation);			
		} 
		return incident;		
	}
}
