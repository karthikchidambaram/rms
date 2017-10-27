package com.i2g.rms.rest.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.Vehicle;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.VehicleRO;
import com.i2g.rms.rest.model.wrapper.VehicleWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.VehicleRestService;

/**
 * Rest controller for Vehicle read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class VehicleController extends AbstractRestController {
	
	@Autowired
	private VehicleRestService _vehicleRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_VEHICLES, method = RequestMethod.GET)
	@Searchable(sourceType = VehicleRO.class, value = Vehicle.class)
	public List<VehicleRO> get() {
		return _vehicleRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_VEHICLE_BY_VEHICLE_ID, method = RequestMethod.GET)
	public VehicleRO get(@PathVariable final Long vehicleId) {
		return _vehicleRestService.get(vehicleId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_VEHICLE_BY_VEHICLE_REGISTRATION_ID, method = RequestMethod.GET)
	public VehicleRO get(@PathVariable final String vehicleRegistrationId) {
		return _vehicleRestService.get(vehicleRegistrationId);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_VEHICLE, method = RequestMethod.POST)
	public VehicleRO createVehicle(@Valid @RequestBody final VehicleRO vehicleRO) {
		return _vehicleRestService.createVehicle(vehicleRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_VEHICLES, method = RequestMethod.POST)
	public Set<VehicleRO> createVehicles(@Valid @RequestBody final VehicleWrapper vehicleWrapper) {
		return _vehicleRestService.createVehicles(vehicleWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_VEHICLE, method = RequestMethod.PUT)
	public VehicleRO updateVehicle(@Valid @RequestBody final VehicleRO vehicleRO) {
		return _vehicleRestService.updateVehicle(vehicleRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_VEHICLES, method = RequestMethod.PUT)
	public Set<VehicleRO> updateVehicles(@Valid @RequestBody final VehicleWrapper vehicleWrapper) {
		return _vehicleRestService.updateVehicles(vehicleWrapper);
	}		
}
