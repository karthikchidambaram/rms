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

import com.i2g.rms.domain.model.Asset;
import com.i2g.rms.domain.model.Building;
import com.i2g.rms.domain.model.Equipment;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.Vehicle;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.model.AssetRO;
import com.i2g.rms.rest.model.BuildingRO;
import com.i2g.rms.rest.model.EquipmentRO;
import com.i2g.rms.rest.model.VehicleRO;
import com.i2g.rms.rest.model.wrapper.BuildingWrapper;
import com.i2g.rms.rest.model.wrapper.EquipmentWrapper;
import com.i2g.rms.rest.model.wrapper.VehicleWrapper;
import com.i2g.rms.service.AssetService;
import com.i2g.rms.service.BuildingService;
import com.i2g.rms.service.EquipmentService;
import com.i2g.rms.service.VehicleService;
import com.i2g.rms.service.exception.ResourceConflictException;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotFoundException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.incident.IncidentService;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;

/**
 * Rest services for Asset rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AssetRestServiceImpl extends AbstractRestService implements AssetRestService {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(AssetRestServiceImpl.class);
	
	@Autowired
	private IncidentService _incidentService;
	@Autowired
	private AssetService _assetService;
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;
	@Autowired
	private BuildingRestService _buildingRestService;
	@Autowired
	private BuildingService _buildingService;
	@Autowired
	private EquipmentRestService _equipmentRestService;
	@Autowired
	private EquipmentService _equipmentService;
	@Autowired
	private VehicleRestService _vehicleRestService;
	@Autowired
	private VehicleService _vehicleService;
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<AssetRO> get() {
		List<Asset> assets = _assetService.get();
		List<AssetRO> assetROs = assets.isEmpty() ? Collections.emptyList() : _mapperService.map(assets, AssetRO.class);
		return assetROs;
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public AssetRO get(final Long assetId) {
		validateKeyId(assetId);
		final Asset asset = _assetService.get(assetId);
		if (asset != null) {
			return _mapperService.map(asset, AssetRO.class);
		} else {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public AssetRO getAssetByIncidentId(final Long incidentId) {
		validateKeyId(incidentId);
		final Incident incident = _incidentService.get(incidentId);
		validateGenericObject(incident);
		final Asset asset = _assetService.get(incident);
		if (asset != null) {
			return _mapperService.map(asset, AssetRO.class);
		} else {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public AssetRO getAssetByUniqueIncidentId(final String uniqueIncidentId) {
		validateUniqueIncidentId(uniqueIncidentId);
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId);
		validateGenericObject(incident);
		final Asset asset = _assetService.get(incident);
		if (asset != null) {
			return _mapperService.map(asset, AssetRO.class);
		} else {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO createAsset(final AssetRO assetRO) {
		validateObject(assetRO);
		final Incident incident = getIncident(assetRO);
		validateGenericObject(incident);
		
		//Validate if the incident already has an asset record.
		final Asset existingAssetRecord = _assetService.get(incident);
		if (existingAssetRecord != null) {
			throw new ResourceConflictException(_messageBuilder.build(RestMessage.INCIDENT_ALREADY_HAS_EXISTING_ASSET_RECORD));
		}
		
		final Asset asset = constructNewAsset(assetRO, incident);
		if (asset != null) {
			final Asset newAsset = _assetService.createAsset(asset);
			if (newAsset != null) {
				return _mapperService.map(newAsset, AssetRO.class);
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
	public AssetRO updateAsset(final AssetRO assetRO) {
		validateObject(assetRO);
		final Asset asset = constructAsset(assetRO);
		if (asset != null) {
			final Asset updatedAsset = _assetService.updateAsset(asset);
			if (updatedAsset != null)  {
				return _mapperService.map(updatedAsset, AssetRO.class);
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
	public AssetRO addOrUpdateAsset(final AssetRO assetRO) {
		validateObject(assetRO);
		if (assetRO.getId() > 0) {
			return updateAsset(assetRO);
		} else {
			return createAsset(assetRO);
		}
	}
	
	//BUILDING
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO addBuildingToAsset(final Long assetId, final BuildingRO buildingRO) {
		validateKeyId(assetId);
		validateObject(buildingRO);
		final Asset asset = _assetService.get(assetId);
		validateGenericObject(asset);
		final Building building = _buildingRestService.constructNewBuilding(buildingRO);
		validateGenericObject(building);
		final Building newBuilding = _buildingService.createBuilding(building);
		if (newBuilding != null) {
			final Set<Building> buildings = new HashSet<Building>(0);
			buildings.add(newBuilding);
			asset.getBuildings().addAll(buildings);
			final Asset updatedAsset = _assetService.updateAsset(asset);
			if (updatedAsset != null) {
				return _mapperService.map(updatedAsset, AssetRO.class);
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
	public AssetRO addBuildingsToAsset(final BuildingWrapper buildingWrapper) {
		validateObject(buildingWrapper);
		validateKeyId(buildingWrapper.getAssetId());
		validateCollectionObject(buildingWrapper.getBuildings());
		final Asset asset = _assetService.get(buildingWrapper.getAssetId());
		validateGenericObject(asset);
		final Set<Building> buildings = new HashSet<Building>(0);
		for (BuildingRO buildingRO : buildingWrapper.getBuildings()) {
			if (buildingRO != null) {
				final Building building = _buildingRestService.constructNewBuilding(buildingRO);
				if (building != null) {
					buildings.add(building);
				}
			}
		}
		validateCollectionObject(buildings);
		final Set<Building> newBuildings = _buildingService.createBuildings(buildings);
		validateCollectionObject(newBuildings);
		asset.getBuildings().addAll(newBuildings);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO addExistingBuildingToAsset(final Long assetId, final Long buildingId) {
		validateKeyId(assetId);
		validateKeyId(buildingId);
		final Asset asset = _assetService.get(assetId);
		validateGenericObject(asset);
		final Building building = _buildingService.get(buildingId);
		validateGenericObject(building);
		final Set<Building> buildings = new HashSet<Building>(0);
		buildings.add(building);
		asset.getBuildings().addAll(buildings);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO addExistingBuildingsToAsset(final BuildingWrapper buildingWrapper) {
		validateObject(buildingWrapper);
		validateKeyId(buildingWrapper.getAssetId());
		validateCollectionObject(buildingWrapper.getBuildings());
		final Asset asset = _assetService.get(buildingWrapper.getAssetId());
		validateGenericObject(asset);
		final Set<Building> buildings = new HashSet<Building>(0);
		for (BuildingRO buildingRO : buildingWrapper.getBuildings()) {
			if (buildingRO != null) {
				if (buildingRO.getId() > 0) {
					final Building building = _buildingService.get(buildingRO.getId());
					if (building != null) {
						buildings.add(building);
					}
				}
			}			
		}
		validateCollectionObject(buildings);
		asset.getBuildings().addAll(buildings);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO removeBuildingFromAsset(final Long assetId, final Long buildingId) {
		validateKeyId(assetId);
		validateKeyId(buildingId);
		final Asset asset = _assetService.get(assetId);
		validateGenericObject(asset);
		final Building building = _buildingService.get(buildingId);
		validateGenericObject(building);
		asset.getBuildings().remove(building);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO removeBuildingsFromAsset(final BuildingWrapper buildingWrapper) {
		validateObject(buildingWrapper);
		validateKeyId(buildingWrapper.getAssetId());
		validateCollectionObject(buildingWrapper.getBuildings());
		final Asset asset = _assetService.get(buildingWrapper.getAssetId());
		validateGenericObject(asset);
		final Set<Building> buildings = new HashSet<Building>(0);
		for (BuildingRO buildingRO : buildingWrapper.getBuildings()) {
			if (buildingRO != null) {
				if (buildingRO.getId() > 0) {
					final Building building = _buildingService.get(buildingRO.getId());
					if (building != null) {
						buildings.add(building);
					}
				}
			}			
		}
		validateCollectionObject(buildings);
		asset.getBuildings().removeAll(buildings);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);		
	}
	
	//EQUIPMENT
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO addEquipmentToAsset(final Long assetId, final EquipmentRO equipmentRO) {
		validateKeyId(assetId);
		validateObject(equipmentRO);
		final Asset asset = _assetService.get(assetId);
		validateGenericObject(asset);
		final Equipment equipment = _equipmentRestService.constructNewEquipment(equipmentRO);
		validateGenericObject(equipment);
		final Equipment newEquipment = _equipmentService.createEquipment(equipment);
		if (newEquipment != null) {
			final Set<Equipment> equipments = new HashSet<Equipment>(0);
			equipments.add(newEquipment);
			asset.getEquipments().addAll(equipments);
			final Asset updatedAsset = _assetService.updateAsset(asset);
			if (updatedAsset != null) {
				return _mapperService.map(updatedAsset, AssetRO.class);
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
	public AssetRO addEquipmentsToAsset(final EquipmentWrapper equipmentWrapper) {
		validateObject(equipmentWrapper);
		validateKeyId(equipmentWrapper.getAssetId());
		validateCollectionObject(equipmentWrapper.getEquipments());
		final Asset asset = _assetService.get(equipmentWrapper.getAssetId());
		validateGenericObject(asset);
		final Set<Equipment> equipments = new HashSet<Equipment>(0);
		for (EquipmentRO equipmentRO : equipmentWrapper.getEquipments()) {
			if (equipmentRO != null) {
				final Equipment equipment = _equipmentRestService.constructNewEquipment(equipmentRO);
				if (equipment != null) {
					equipments.add(equipment);
				}
			}
		}
		validateCollectionObject(equipments);
		final Set<Equipment> newEquipments = _equipmentService.createEquipments(equipments);
		validateCollectionObject(newEquipments);
		asset.getEquipments().addAll(newEquipments);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO addExistingEquipmentToAsset(final Long assetId, final Long equipmentId) {
		validateKeyId(assetId);
		validateKeyId(equipmentId);
		final Asset asset = _assetService.get(assetId);
		validateGenericObject(asset);
		final Equipment equipment = _equipmentService.get(equipmentId);
		validateGenericObject(equipment);
		final Set<Equipment> equipments = new HashSet<Equipment>(0);
		equipments.add(equipment);
		asset.getEquipments().addAll(equipments);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO addExistingEquipmentsToAsset(final EquipmentWrapper equipmentWrapper) {
		validateObject(equipmentWrapper);
		validateKeyId(equipmentWrapper.getAssetId());
		validateCollectionObject(equipmentWrapper.getEquipments());
		final Asset asset = _assetService.get(equipmentWrapper.getAssetId());
		validateGenericObject(asset);
		final Set<Equipment> equipments = new HashSet<Equipment>(0);
		for (EquipmentRO equipmentRO : equipmentWrapper.getEquipments()) {
			if (equipmentRO != null) {
				if (equipmentRO.getId() > 0) {
					final Equipment equipment = _equipmentService.get(equipmentRO.getId());
					if (equipment != null) {
						equipments.add(equipment);
					}
				}
			}			
		}
		validateCollectionObject(equipments);
		asset.getEquipments().addAll(equipments);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO removeEquipmentFromAsset(final Long assetId, final Long equipmentId) {
		validateKeyId(assetId);
		validateKeyId(equipmentId);
		final Asset asset = _assetService.get(assetId);
		validateGenericObject(asset);
		final Equipment equipment = _equipmentService.get(equipmentId);
		validateGenericObject(equipment);
		asset.getEquipments().remove(equipment);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO removeEquipmentsFromAsset(final EquipmentWrapper equipmentWrapper) {
		validateObject(equipmentWrapper);
		validateKeyId(equipmentWrapper.getAssetId());
		validateCollectionObject(equipmentWrapper.getEquipments());
		final Asset asset = _assetService.get(equipmentWrapper.getAssetId());
		validateGenericObject(asset);
		final Set<Equipment> equipments = new HashSet<Equipment>(0);
		for (EquipmentRO equipmentRO : equipmentWrapper.getEquipments()) {
			if (equipmentRO != null) {
				if (equipmentRO.getId() > 0) {
					final Equipment equipment = _equipmentService.get(equipmentRO.getId());
					if (equipment != null) {
						equipments.add(equipment);
					}
				}
			}			
		}
		validateCollectionObject(equipments);
		asset.getEquipments().removeAll(equipments);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);		
	}
	
	//VEHICLE
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO addVehicleToAsset(final Long assetId, final VehicleRO vehicleRO) {
		validateKeyId(assetId);
		validateObject(vehicleRO);
		final Asset asset = _assetService.get(assetId);
		validateGenericObject(asset);
		final Vehicle vehicle = _vehicleRestService.constructNewVehicle(vehicleRO);
		validateGenericObject(vehicle);
		final Vehicle newVehicle = _vehicleService.createVehicle(vehicle);
		if (newVehicle != null) {
			final Set<Vehicle> vehicles = new HashSet<Vehicle>(0);
			vehicles.add(newVehicle);
			asset.getVehicles().addAll(vehicles);
			final Asset updatedAsset = _assetService.updateAsset(asset);
			if (updatedAsset != null) {
				return _mapperService.map(updatedAsset, AssetRO.class);
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
	public AssetRO addVehiclesToAsset(final VehicleWrapper vehicleWrapper) {
		validateObject(vehicleWrapper);
		validateKeyId(vehicleWrapper.getAssetId());
		validateCollectionObject(vehicleWrapper.getVehicles());
		final Asset asset = _assetService.get(vehicleWrapper.getAssetId());
		validateGenericObject(asset);
		final Set<Vehicle> vehicles = new HashSet<Vehicle>(0);
		for (VehicleRO vehicleRO : vehicleWrapper.getVehicles()) {
			if (vehicleRO != null) {
				final Vehicle vehicle = _vehicleRestService.constructNewVehicle(vehicleRO);
				if (vehicle != null) {
					vehicles.add(vehicle);
				}
			}
		}
		validateCollectionObject(vehicles);
		final Set<Vehicle> newVehicles = _vehicleService.createVehicles(vehicles);
		validateCollectionObject(newVehicles);
		asset.getVehicles().addAll(newVehicles);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO addExistingVehicleToAsset(final Long assetId, final Long vehicleId) {
		validateKeyId(assetId);
		validateKeyId(vehicleId);
		final Asset asset = _assetService.get(assetId);
		validateGenericObject(asset);
		final Vehicle vehicle = _vehicleService.get(vehicleId);
		validateGenericObject(vehicle);
		final Set<Vehicle> vehicles = new HashSet<Vehicle>(0);
		vehicles.add(vehicle);
		asset.getVehicles().addAll(vehicles);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO addExistingVehiclesToAsset(final VehicleWrapper vehicleWrapper) {
		validateObject(vehicleWrapper);
		validateKeyId(vehicleWrapper.getAssetId());
		validateCollectionObject(vehicleWrapper.getVehicles());
		final Asset asset = _assetService.get(vehicleWrapper.getAssetId());
		validateGenericObject(asset);
		final Set<Vehicle> vehicles = new HashSet<Vehicle>(0);
		for (VehicleRO vehicleRO : vehicleWrapper.getVehicles()) {
			if (vehicleRO != null) {
				if (vehicleRO.getId() > 0) {
					final Vehicle vehicle = _vehicleService.get(vehicleRO.getId());
					if (vehicle != null) {
						vehicles.add(vehicle);
					}
				}
			}			
		}
		validateCollectionObject(vehicles);
		asset.getVehicles().addAll(vehicles);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO removeVehicleFromAsset(final Long assetId, final Long vehicleId) {
		validateKeyId(assetId);
		validateKeyId(vehicleId);
		final Asset asset = _assetService.get(assetId);
		validateGenericObject(asset);
		final Vehicle vehicle = _vehicleService.get(vehicleId);
		validateGenericObject(vehicle);
		asset.getVehicles().remove(vehicle);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AssetRO removeVehiclesFromAsset(final VehicleWrapper vehicleWrapper) {
		validateObject(vehicleWrapper);
		validateKeyId(vehicleWrapper.getAssetId());
		validateCollectionObject(vehicleWrapper.getVehicles());
		final Asset asset = _assetService.get(vehicleWrapper.getAssetId());
		validateGenericObject(asset);
		final Set<Vehicle> vehicles = new HashSet<Vehicle>(0);
		for (VehicleRO vehicleRO : vehicleWrapper.getVehicles()) {
			if (vehicleRO != null) {
				if (vehicleRO.getId() > 0) {
					final Vehicle vehicle = _vehicleService.get(vehicleRO.getId());
					if (vehicle != null) {
						vehicles.add(vehicle);
					}
				}
			}			
		}
		validateCollectionObject(vehicles);
		asset.getVehicles().removeAll(vehicles);
		final Asset updatedAsset = _assetService.updateAsset(asset);
		validateGenericObject(updatedAsset);
		return _mapperService.map(updatedAsset, AssetRO.class);		
	}
	
	private Incident getIncident(final AssetRO assetRO) {
		Incident incident = null;
		if (assetRO != null) {
			if (assetRO.getIncident() != null) {
				if (assetRO.getIncident().getId() > 0) {
					incident = _incidentService.get(assetRO.getIncident().getId());
				} else if (assetRO.getIncident().getUniqueIncidentId() != null && !assetRO.getIncident().getUniqueIncidentId().trim().isEmpty()) {
					incident = _incidentService.getIncidentByUniqueIncidentId(assetRO.getIncident().getUniqueIncidentId().trim());
				}
			}
		}
		return incident;
	}
	
	private Asset constructNewAsset(final AssetRO assetRO, final Incident incident) {
		if (assetRO != null && incident != null) {
			final Asset asset = new Asset.Builder().setIncident(incident).setStatusFlag(StatusFlag.ACTIVE).build();
			return setOtherFieldsForAsset(asset, assetRO);
		} else {
			return null;
		}
	}
	
	private Asset constructAsset(final AssetRO assetRO) {
		if (assetRO != null && assetRO.getId() > 0) {
			final Asset asset = _assetService.get(assetRO.getId());
			return setOtherFieldsForAsset(asset, assetRO);
		} else {
			return null;
		}
	}
	
	private Asset setOtherFieldsForAsset(final Asset asset, final AssetRO assetRO) {
		if (asset != null && assetRO != null) {
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
			return asset;
		} else {
			return null;
		}
	}
}
