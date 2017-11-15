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

import com.i2g.rms.domain.model.Asset;
import com.i2g.rms.domain.model.Building;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.rest.model.BuildingRO;
import com.i2g.rms.rest.model.wrapper.BuildingWrapper;
import com.i2g.rms.service.AssetService;
import com.i2g.rms.service.BuildingService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;

/**
 * Rest services for building rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class BuildingRestServiceImpl extends AbstractRestService implements BuildingRestService {
	
	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(BuildingRestServiceImpl.class);
	
	@Autowired
	private AssetService _assetService;
	@Autowired
	private BuildingService _buildingService;
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;
	@Autowired
	private SuspectRestService _suspectRestService;
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<BuildingRO> get() {
		List<Building> buildings = _buildingService.get();
		List<BuildingRO> buildingROs = (buildings == null || buildings.isEmpty()) ? Collections.emptyList() : _mapperService.map(buildings, BuildingRO.class);
		return buildingROs;
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<BuildingRO> getBuildingTableByAssetId(final Long assetId) {
		validateKeyId(assetId);
		final Asset asset = _assetService.get(assetId);
		validateGenericObject(asset);
		List<Building> buildings = new ArrayList<Building>(asset.getBuildings());
		List<BuildingRO> buildingROs = (buildings == null || buildings.isEmpty()) ? Collections.emptyList() : _mapperService.map(buildings, BuildingRO.class);
		return buildingROs;		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public BuildingRO get(final Long buildingId) {
		validateKeyId(buildingId);
		final Building building = _buildingService.get(buildingId);
		validateGenericObject(building);
		return _mapperService.map(building, BuildingRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public BuildingRO get(final String uniqueBuildingId) {
		validateStringObject(uniqueBuildingId);
		final Building building = _buildingService.get(uniqueBuildingId);
		validateGenericObject(building);
		return _mapperService.map(building, BuildingRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public BuildingRO createBuilding(final BuildingRO buildingRO) {
		validateObject(buildingRO);
		final Building building = constructNewBuilding(buildingRO);
		validateGenericObject(building);		
		final Building newBuilding = _buildingService.createBuilding(building);
		if (newBuilding != null) {
			return _mapperService.map(newBuilding, BuildingRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public Set<BuildingRO> createBuildings(final BuildingWrapper buildingWrapper) {
		validateObject(buildingWrapper);
		validateCollectionObject(buildingWrapper.getBuildings());
		final Set<Building> buildings = new HashSet<Building>(0);
		if (buildingWrapper.getBuildings() != null && !buildingWrapper.getBuildings().isEmpty()) {
			for (BuildingRO buildingRO : buildingWrapper.getBuildings()) {
				if (buildingRO != null) {
					final Building building = constructNewBuilding(buildingRO);
					if (building != null) {
						buildings.add(building);
					}
				}
			}
		}
		if (buildings != null && !buildings.isEmpty()) {
			final Set<Building> newBuildings = _buildingService.createBuildings(buildings);
			if (newBuildings != null && !newBuildings.isEmpty()) {
				return _mapperService.map(newBuildings, BuildingRO.class);
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
	public BuildingRO updateBuilding(final BuildingRO buildingRO) {
		validateObject(buildingRO);
		final Building building = constructBuilding(buildingRO);
		validateGenericObject(building);
		final Building updatedBuilding = _buildingService.updateBuilding(building);
		if (updatedBuilding != null) {
			return _mapperService.map(updatedBuilding, BuildingRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public Set<BuildingRO> updateBuildings(final BuildingWrapper buildingWrapper) {
		validateObject(buildingWrapper);
		validateCollectionObject(buildingWrapper.getBuildings());
		final Set<Building> buildings = new HashSet<Building>(0);
		if (buildingWrapper.getBuildings() != null && !buildingWrapper.getBuildings().isEmpty()) {
			for (BuildingRO buildingRO : buildingWrapper.getBuildings()) {
				if (buildingRO != null) {
					final Building building = constructBuilding(buildingRO);
					if (building != null) {
						buildings.add(building);
					}
				}
			}
		}		
		if (buildings != null && !buildings.isEmpty()) {
			final Set<Building> updatedBuildings = _buildingService.updateBuildings(buildings);
			if (updatedBuildings != null && !updatedBuildings.isEmpty()) {
				return _mapperService.map(updatedBuildings, BuildingRO.class);
			} else {
				throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
			}
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CONSTRUCT_OBJECT_FOR_RECORD_CREATION));
		}
	}

	@Override
	public Building constructNewBuilding(final BuildingRO buildingRO) {
		validateObject(buildingRO);
		final Building building = new Building.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
		validateGenericObject(building);
		return setOtherFieldsForBuilding(building, buildingRO);
	}
	
	@Override
	public Building constructBuilding(final BuildingRO buildingRO) {
		validateObject(buildingRO);
		final Building building = _buildingService.get(buildingRO.getId());
		validateGenericObject(building);
		return setOtherFieldsForBuilding(building, buildingRO);
	}
	
	@Override
	public Building setOtherFieldsForBuilding(final Building building, final BuildingRO buildingRO) {
		if (building != null && buildingRO != null) {
			// Set other values for building
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
				building.setAddresses(_suspectRestService.createOrUpdateAddresses(buildingRO.getAddresses(), null, null, null, null, null, building));
			}			
		}
		return building;
	}		
}
