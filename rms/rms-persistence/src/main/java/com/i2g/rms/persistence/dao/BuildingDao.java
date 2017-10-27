package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Building;

/**
 * Back-end DAO for Building related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface BuildingDao {
	
	public List<Building> get();
	
	public Building get(final long id);
	
	public Building get(final String buildingId);
	
	public Building createBuilding(final Building building);
	
	public Set<Building> createBuildings(final Set<Building> buildings);
	
	public Building updateBuilding(final Building building);
	
	public Set<Building> updateBuildings(final Set<Building> buildings);	
	
}
