package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.BuildingRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildingWrapper {

	private Long assetId;
	private Long[] buildingIds;
	private String[] uniqueBuildingIds;
	private Set<BuildingRO> buildings = new HashSet<BuildingRO>(0);

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(final Long assetId) {
		if (assetId != null) {
			this.assetId = assetId;
		} else {
			this.assetId = 0l;
		}
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

	public Set<BuildingRO> getBuildings() {
		return buildings;
	}

	public void setBuildings(final Set<BuildingRO> buildings) {
		this.buildings = buildings;
	}
}
