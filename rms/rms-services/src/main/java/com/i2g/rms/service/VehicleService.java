package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Asset;
import com.i2g.rms.domain.model.Vehicle;

/**
 * Service interface for all Vehicle related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface VehicleService {
	
	public List<Vehicle> get();
	
	public List<Vehicle> get(final Asset asset);
	
	public Vehicle get(final long id);
	
	public Vehicle get(final String vehicleRegistrationId);
	
	public Vehicle createVehicle(final Vehicle vehicle);
	
	public Set<Vehicle> createVehicles(final Set<Vehicle> vehicles);
	
	public Vehicle updateVehicle(final Vehicle vehicle);
	
	public Set<Vehicle> updateVehicles(final Set<Vehicle> vehicles);
	
}
