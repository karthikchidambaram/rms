package com.i2g.rms.rest.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Vehicle;
import com.i2g.rms.rest.model.VehicleRO;
import com.i2g.rms.rest.model.wrapper.VehicleWrapper;

/**
 * Rest Service Interface for vehicle rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface VehicleRestService {

	public List<VehicleRO> get();

	public VehicleRO get(final Long vehicleId);
	
	public VehicleRO get(final String vehicleRegistrationId);

	public VehicleRO createVehicle(final VehicleRO vehicleRO);

	public Set<VehicleRO> createVehicles(final VehicleWrapper vehicleWrapper);

	public VehicleRO updateVehicle(final VehicleRO vehicleRO);

	public Set<VehicleRO> updateVehicles(final VehicleWrapper vehicleWrapper);

	public Vehicle constructNewVehicle(final VehicleRO vehicleRO);

	public Vehicle constructVehicle(final VehicleRO vehicleRO);

	public Vehicle setOtherFieldsForVehicle(final Vehicle vehicle, final VehicleRO vehicleRO);

}
