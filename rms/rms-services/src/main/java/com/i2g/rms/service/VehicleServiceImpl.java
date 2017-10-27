package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Vehicle;
import com.i2g.rms.persistence.dao.VehicleDao;

/**
 * Back-end service for Vehicle related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class VehicleServiceImpl extends AbstractService implements VehicleService {

	@Autowired
	private VehicleDao _vehicleDao;

	private VehicleServiceImpl() {
	}

	@Override
	public List<Vehicle> get() {
		return _vehicleDao.get();
	}

	@Override
	public Vehicle get(final long id) {
		return _vehicleDao.get(id);
	}
	
	@Override
	public Vehicle get(final String vehicleRegistrationId) {
		return _vehicleDao.get(vehicleRegistrationId);
	}

	@Override
	public Vehicle createVehicle(final Vehicle vehicle) {
		return _vehicleDao.createVehicle(vehicle);
	}

	@Override
	public Set<Vehicle> createVehicles(final Set<Vehicle> vehicles) {
		return _vehicleDao.createVehicles(vehicles);
	}

	@Override
	public Vehicle updateVehicle(final Vehicle vehicle) {
		return _vehicleDao.updateVehicle(vehicle);
	}

	@Override
	public Set<Vehicle> updateVehicles(final Set<Vehicle> vehicles) {
		return _vehicleDao.updateVehicles(vehicles);
	}	
}
