package com.i2g.rms.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.Asset;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.AssetRO;
import com.i2g.rms.rest.model.BuildingRO;
import com.i2g.rms.rest.model.EquipmentRO;
import com.i2g.rms.rest.model.VehicleRO;
import com.i2g.rms.rest.model.wrapper.BuildingWrapper;
import com.i2g.rms.rest.model.wrapper.EquipmentWrapper;
import com.i2g.rms.rest.model.wrapper.VehicleWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.AssetRestService;

/**
 * Rest controller for asset read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class AssetController extends AbstractRestController {
	
	@Autowired
	private AssetRestService _assetRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ALL_ASSETS, method = RequestMethod.GET)
	@Searchable(sourceType = AssetRO.class, value = Asset.class)
	public List<AssetRO> get() {
		return _assetRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ASSET_BY_ASSET_ID, method = RequestMethod.GET)
	public AssetRO get(@PathVariable final Long assetId) {
		return _assetRestService.get(assetId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ASSET_BY_INCIDENT_ID, method = RequestMethod.GET)
	public AssetRO getAssetByIncidentId(@PathVariable final Long incidentId) {
		return _assetRestService.getAssetByIncidentId(incidentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ASSET_BY_UNIQUE_INCIDENT_ID, method = RequestMethod.GET)
	public AssetRO getAssetByUniqueIncidentId(@PathVariable final String uniqueIncidentId) {
		return _assetRestService.getAssetByUniqueIncidentId(uniqueIncidentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_ASSET, method = RequestMethod.POST)
	public AssetRO createAsset(@Valid @RequestBody final AssetRO assetRO) {
		return _assetRestService.createAsset(assetRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_ASSET, method = RequestMethod.PUT)
	public AssetRO updateAsset(@Valid @RequestBody final AssetRO assetRO) {
		return _assetRestService.updateAsset(assetRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_OR_UPDATE_ASSET, method = RequestMethod.POST)
	public AssetRO addOrUpdateAsset(@Valid @RequestBody final AssetRO assetRO) {
		return _assetRestService.addOrUpdateAsset(assetRO);
	}
	
	//BUILDING
	@RequestMapping(value = RequestMappingConstants.ADD_BUILDING_TO_ASSET, method = RequestMethod.PUT)
	public AssetRO addBuildingToAsset(@PathVariable final Long assetId, @Valid @RequestBody final BuildingRO buildingRO) {
		return _assetRestService.addBuildingToAsset(assetId, buildingRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_BUILDINGS_TO_ASSET, method = RequestMethod.PUT)
	public AssetRO addBuildingsToAsset(@Valid @RequestBody final BuildingWrapper buildingWrapper) {
		return _assetRestService.addBuildingsToAsset(buildingWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_BUILDING_TO_ASSET, method = RequestMethod.PUT)
	public AssetRO addExistingBuildingToAsset(@PathVariable final Long assetId, @PathVariable final Long buildingId) {
		return _assetRestService.addExistingBuildingToAsset(assetId, buildingId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_BUILDINGS_TO_ASSET, method = RequestMethod.PUT)
	public AssetRO addExistingBuildingsToAsset(@Valid @RequestBody final BuildingWrapper buildingWrapper) {
		return _assetRestService.addExistingBuildingsToAsset(buildingWrapper);
	}	
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_BUILDING_FROM_ASSET, method = RequestMethod.DELETE)
	public AssetRO removeBuildingFromAsset(@PathVariable final Long assetId, @PathVariable final Long buildingId) {
		return _assetRestService.removeBuildingFromAsset(assetId, buildingId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_BUILDINGS_FROM_ASSET, method = RequestMethod.DELETE)
	public AssetRO removeBuildingsFromAsset(@Valid @RequestBody final BuildingWrapper buildingWrapper) {
		return _assetRestService.removeBuildingsFromAsset(buildingWrapper);
	}
	
	//EQUIPMENT
	@RequestMapping(value = RequestMappingConstants.ADD_EQUIPMENT_TO_ASSET, method = RequestMethod.PUT)
	public AssetRO addEquipmentToAsset(@PathVariable final Long assetId, @Valid @RequestBody final EquipmentRO equipmentRO) {
		return _assetRestService.addEquipmentToAsset(assetId, equipmentRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EQUIPMENTS_TO_ASSET, method = RequestMethod.PUT)
	public AssetRO addEquipmentsToAsset(@Valid @RequestBody final EquipmentWrapper equipmentWrapper) {
		return _assetRestService.addEquipmentsToAsset(equipmentWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_EQUIPMENT_TO_ASSET, method = RequestMethod.PUT)
	public AssetRO addExistingEquipmentToAsset(@PathVariable final Long assetId, @PathVariable final Long equipmentId) {
		return _assetRestService.addExistingEquipmentToAsset(assetId, equipmentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_EQUIPMENTS_TO_ASSET, method = RequestMethod.PUT)
	public AssetRO addExistingEquipmentsToAsset(@Valid @RequestBody final EquipmentWrapper equipmentWrapper) {
		return _assetRestService.addExistingEquipmentsToAsset(equipmentWrapper);
	}	
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EQUIPMENT_FROM_ASSET, method = RequestMethod.DELETE)
	public AssetRO removeEquipmentFromAsset(@PathVariable final Long assetId, @PathVariable final Long equipmentId) {
		return _assetRestService.removeEquipmentFromAsset(assetId, equipmentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EQUIPMENTS_FROM_ASSET, method = RequestMethod.DELETE)
	public AssetRO removeEquipmentsFromAsset(@Valid @RequestBody final EquipmentWrapper equipmentWrapper) {
		return _assetRestService.removeEquipmentsFromAsset(equipmentWrapper);
	}
		
	//VEHICLE
	@RequestMapping(value = RequestMappingConstants.ADD_VEHICLE_TO_ASSET, method = RequestMethod.PUT)
	public AssetRO addVehicleToAsset(@PathVariable final Long assetId, @Valid @RequestBody final VehicleRO vehicleRO) {
		return _assetRestService.addVehicleToAsset(assetId, vehicleRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_VEHICLES_TO_ASSET, method = RequestMethod.PUT)
	public AssetRO addVehiclesToAsset(@Valid @RequestBody final VehicleWrapper vehicleWrapper) {
		return _assetRestService.addVehiclesToAsset(vehicleWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_VEHICLE_TO_ASSET, method = RequestMethod.PUT)
	public AssetRO addExistingVehicleToAsset(@PathVariable final Long assetId, @PathVariable final Long vehicleId) {
		return _assetRestService.addExistingVehicleToAsset(assetId, vehicleId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_VEHICLES_TO_ASSET, method = RequestMethod.PUT)
	public AssetRO addExistingVehiclesToAsset(@Valid @RequestBody final VehicleWrapper vehicleWrapper) {
		return _assetRestService.addExistingVehiclesToAsset(vehicleWrapper);		
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_VEHICLE_FROM_ASSET, method = RequestMethod.DELETE)
	public AssetRO removeVehicleFromAsset(@PathVariable final Long assetId, @PathVariable final Long vehicleId) {
		return _assetRestService.removeVehicleFromAsset(assetId, vehicleId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_VEHICLES_FROM_ASSET, method = RequestMethod.DELETE)
	public AssetRO removeVehiclesFromAsset(@Valid @RequestBody final VehicleWrapper vehicleWrapper) {
		return _assetRestService.removeVehiclesFromAsset(vehicleWrapper);
	}	
}
