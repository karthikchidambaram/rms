package com.i2g.rms.rest.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.Building;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.BuildingRO;
import com.i2g.rms.rest.model.wrapper.BuildingWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.BuildingRestService;

/**
 * Rest controller for Building read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class BuildingController extends AbstractRestController {
	
	@Autowired
	private BuildingRestService _buildingRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_BUILDINGS, method = RequestMethod.GET)
	@Searchable(sourceType = BuildingRO.class, value = Building.class)
	public List<BuildingRO> get() {
		return _buildingRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_BUILDING_TABLE_BY_ASSET_ID, method = RequestMethod.GET)
	@Searchable(sourceType = BuildingRO.class, value = Building.class)
	public List<BuildingRO> getBuildingTableByAssetId(@PathVariable final Long assetId) {
		return _buildingRestService.getBuildingTableByAssetId(assetId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_BUILDING_BY_BUILDING_ID, method = RequestMethod.GET)
	public BuildingRO get(@PathVariable final Long buildingId) {
		return _buildingRestService.get(buildingId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_BUILDING_BY_UNIQUE_BUILDING_ID, method = RequestMethod.GET)
	public BuildingRO get(@PathVariable final String uniqueBuildingId) {
		return _buildingRestService.get(uniqueBuildingId);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_BUILDING, method = RequestMethod.POST)
	public BuildingRO createBuilding(@Valid @RequestBody final BuildingRO buildingRO) {
		return _buildingRestService.createBuilding(buildingRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_BUILDINGS, method = RequestMethod.POST)
	public Set<BuildingRO> createBuildings(@Valid @RequestBody final BuildingWrapper buildingWrapper) {
		return _buildingRestService.createBuildings(buildingWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_BUILDING, method = RequestMethod.PUT)
	public BuildingRO updateBuilding(@Valid @RequestBody final BuildingRO buildingRO) {
		return _buildingRestService.updateBuilding(buildingRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_BUILDINGS, method = RequestMethod.PUT)
	public Set<BuildingRO> updateBuildings(@Valid @RequestBody final BuildingWrapper buildingWrapper) {
		return _buildingRestService.updateBuildings(buildingWrapper);
	}		
}
