package com.i2g.rms.rest.model.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.BuildingRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildingWrapper {

	private Long assetId;
	private Long[] buildingIds;
	private String[] uniqueBuildingIds;
	private List<BuildingRO> buildings = new ArrayList<BuildingRO>(0);

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(final Long assetId) {
		this.assetId = assetId;
	}

	public Long[] getBuildingIds() {
		return buildingIds;
	}

	public void setBuildingIds(final Long[] buildingIds) {
		this.buildingIds = buildingIds;
	}

	public String[] getUniqueBuildingIds() {
		return uniqueBuildingIds;
	}

	public void setUniqueBuildingIds(final String[] uniqueBuildingIds) {
		this.uniqueBuildingIds = uniqueBuildingIds;
	}

	public List<BuildingRO> getBuildings() {
		return buildings;
	}

	public void setBuildings(final List<BuildingRO> buildings) {
		this.buildings = buildings;
	}
}
