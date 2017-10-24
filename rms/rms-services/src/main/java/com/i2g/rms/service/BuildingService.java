package com.i2g.rms.service;

import java.util.List;

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
	
	public Building get(final long id);
	
	public Building create(final Building building);
	
}
