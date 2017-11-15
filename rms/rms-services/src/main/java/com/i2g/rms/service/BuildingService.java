package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Asset;
import com.i2g.rms.domain.model.Building;

/**
 * Service interface for all Building related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface BuildingService {
	
	public List<Building> get();
	
	public List<Building> get(final Asset asset);
	
	public Building get(final long id);
	
	public Building get(final String buildingId);
	
	public Building createBuilding(final Building building);
	
	public Set<Building> createBuildings(final Set<Building> buildings);
	
	public Building updateBuilding(final Building building);
	
	public Set<Building> updateBuildings(final Set<Building> buildings);
	
}
