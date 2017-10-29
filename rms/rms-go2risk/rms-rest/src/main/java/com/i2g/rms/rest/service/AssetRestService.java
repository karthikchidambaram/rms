package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.AssetRO;
import com.i2g.rms.rest.model.BuildingRO;
import com.i2g.rms.rest.model.EquipmentRO;
import com.i2g.rms.rest.model.VehicleRO;
import com.i2g.rms.rest.model.wrapper.BuildingWrapper;
import com.i2g.rms.rest.model.wrapper.EquipmentWrapper;
import com.i2g.rms.rest.model.wrapper.VehicleWrapper;

/**
 * Rest Service Interface for Asset rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AssetRestService {
	
	public List<AssetRO> get();

	public AssetRO get(final Long assetId);
	
	public AssetRO getAssetByIncidentId(final Long incidentId);
	
	public AssetRO getAssetByUniqueIncidentId(final String uniqueIncidentId);
	
	public AssetRO createAsset(final AssetRO assetRO);
	
	public AssetRO updateAsset(final AssetRO assetRO);
	
	public AssetRO addOrUpdateAsset(final AssetRO assetRO);
	
	public AssetRO addBuildingToAsset(final Long assetId, final BuildingRO buildingRO);
	public AssetRO addBuildingsToAsset(final BuildingWrapper buildingWrapper);
	public AssetRO addExistingBuildingToAsset(final Long assetId, final Long buildingId);
	public AssetRO addExistingBuildingsToAsset(final BuildingWrapper buildingWrapper);
	public AssetRO removeBuildingFromAsset(final Long assetId, final Long buildingId);
	public AssetRO removeBuildingsFromAsset(final BuildingWrapper buildingWrapper);
	
	public AssetRO addEquipmentToAsset(final Long assetId, final EquipmentRO equipmentRO);
	public AssetRO addEquipmentsToAsset(final EquipmentWrapper equipmentWrapper);
	public AssetRO addExistingEquipmentToAsset(final Long assetId, final Long equipmentId);
	public AssetRO addExistingEquipmentsToAsset(final EquipmentWrapper equipmentWrapper);
	public AssetRO removeEquipmentFromAsset(final Long assetId, final Long equipmentId);
	public AssetRO removeEquipmentsFromAsset(final EquipmentWrapper equipmentWrapper);
	
	public AssetRO addVehicleToAsset(final Long assetId, final VehicleRO vehicleRO);
	public AssetRO addVehiclesToAsset(final VehicleWrapper vehicleWrapper);
	public AssetRO addExistingVehicleToAsset(final Long assetId, final Long vehicleId);
	public AssetRO addExistingVehiclesToAsset(final VehicleWrapper vehicleWrapper);
	public AssetRO removeVehicleFromAsset(final Long assetId, final Long vehicleId);
	public AssetRO removeVehiclesFromAsset(final VehicleWrapper vehicleWrapper);

}
