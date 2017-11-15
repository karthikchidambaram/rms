package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Asset;
import com.i2g.rms.domain.model.Building;
import com.i2g.rms.persistence.dao.BuildingDao;

/**
 * Back-end service for Building related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class BuildingServiceImpl extends AbstractService implements BuildingService {

	@Autowired
	private BuildingDao _buildingDao;

	private BuildingServiceImpl() {
	}

	@Override
	public List<Building> get() {
		return _buildingDao.get();
	}

	@Override
	public Building get(final long id) {
		return _buildingDao.get(id);
	}
	
	@Override
	public Building get(final String buildingId) {
		return _buildingDao.get(buildingId);
	}

	@Override
	public Building createBuilding(final Building building) {
		return _buildingDao.createBuilding(building);
	}

	@Override
	public Set<Building> createBuildings(final Set<Building> buildings) {
		return _buildingDao.createBuildings(buildings);
	}

	@Override
	public Building updateBuilding(final Building building) {
		return _buildingDao.updateBuilding(building);
	}

	@Override
	public Set<Building> updateBuildings(final Set<Building> buildings) {
		return _buildingDao.updateBuildings(buildings);
	}

	@Override
	public List<Building> get(final Asset asset) {
		return _buildingDao.get(asset);
	}	
}
