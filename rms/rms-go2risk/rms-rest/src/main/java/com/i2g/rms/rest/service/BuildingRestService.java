package com.i2g.rms.rest.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Building;
import com.i2g.rms.rest.model.BuildingRO;
import com.i2g.rms.rest.model.wrapper.BuildingWrapper;

/**
 * Rest Service Interface for building rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface BuildingRestService {

	public List<BuildingRO> get();
	
	public List<BuildingRO> getBuildingTableByAssetId(final Long assetId);

	public BuildingRO get(final Long buildingId);
	
	public BuildingRO get(final String uniqueBuildingId);

	public BuildingRO createBuilding(final BuildingRO buildingRO);

	public Set<BuildingRO> createBuildings(final BuildingWrapper buildingWrapper);

	public BuildingRO updateBuilding(final BuildingRO buildingRO);

	public Set<BuildingRO> updateBuildings(final BuildingWrapper buildingWrapper);

	public Building constructNewBuilding(final BuildingRO buildingRO);

	public Building constructBuilding(final BuildingRO buildingRO);

	public Building setOtherFieldsForBuilding(final Building building, final BuildingRO buildingRO);

}
