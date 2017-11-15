package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Asset;
import com.i2g.rms.domain.model.Vehicle;

/**
 * Back-end DAO for Vehicle related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface VehicleDao {
	
	public List<Vehicle> get();
	
	public List<Vehicle> get(final Asset asset);
	
	public Vehicle get(final long id);
	
	public Vehicle get(final String vehicleRegistrationId);
	
	public Vehicle createVehicle(final Vehicle vehicle);
	
	public Set<Vehicle> createVehicles(final Set<Vehicle> vehicles);
	
	public Vehicle updateVehicle(final Vehicle vehicle);
	
	public Set<Vehicle> updateVehicles(final Set<Vehicle> vehicles);	
	
}
