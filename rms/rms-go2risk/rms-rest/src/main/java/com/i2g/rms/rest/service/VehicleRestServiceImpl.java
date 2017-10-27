package com.i2g.rms.rest.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.Vehicle;
import com.i2g.rms.rest.model.VehicleRO;
import com.i2g.rms.rest.model.wrapper.VehicleWrapper;
import com.i2g.rms.service.VehicleService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;

/**
 * Rest services for vehicle rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class VehicleRestServiceImpl extends AbstractRestService implements VehicleRestService {
	
	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(VehicleRestServiceImpl.class);
	
	@Autowired
	private VehicleService _vehicleService;
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<VehicleRO> get() {
		List<Vehicle> vehicles = _vehicleService.get();
		List<VehicleRO> vehicleROs = vehicles.isEmpty() ? Collections.emptyList() : _mapperService.map(vehicles, VehicleRO.class);
		return vehicleROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public VehicleRO get(final Long vehicleId) {
		validateKeyId(vehicleId);
		final Vehicle vehicle = _vehicleService.get(vehicleId);
		validateGenericObject(vehicle);
		return _mapperService.map(vehicle, VehicleRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public VehicleRO get(final String vehicleRegistrationId) {
		validateStringObject(vehicleRegistrationId);
		final Vehicle vehicle = _vehicleService.get(vehicleRegistrationId);
		validateGenericObject(vehicle);
		return _mapperService.map(vehicle, VehicleRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public VehicleRO createVehicle(final VehicleRO vehicleRO) {
		validateObject(vehicleRO);
		final Vehicle vehicle = constructNewVehicle(vehicleRO);
		validateGenericObject(vehicle);		
		final Vehicle newVehicle = _vehicleService.createVehicle(vehicle);
		if (newVehicle != null) {
			return _mapperService.map(newVehicle, VehicleRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public Set<VehicleRO> createVehicles(final VehicleWrapper vehicleWrapper) {
		validateObject(vehicleWrapper);
		validateCollectionObject(vehicleWrapper.getVehicles());
		final Set<Vehicle> vehicles = new HashSet<Vehicle>(0);
		if (vehicleWrapper.getVehicles() != null && !vehicleWrapper.getVehicles().isEmpty()) {
			for (VehicleRO vehicleRO : vehicleWrapper.getVehicles()) {
				if (vehicleRO != null) {
					final Vehicle vehicle = constructNewVehicle(vehicleRO);
					if (vehicle != null) {
						vehicles.add(vehicle);
					}
				}
			}
		}
		if (vehicles != null && !vehicles.isEmpty()) {
			final Set<Vehicle> newVehicles = _vehicleService.createVehicles(vehicles);
			if (newVehicles != null && !newVehicles.isEmpty()) {
				return _mapperService.map(newVehicles, VehicleRO.class);
			} else {
				throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
			}
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CONSTRUCT_OBJECT_FOR_RECORD_CREATION));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public VehicleRO updateVehicle(final VehicleRO vehicleRO) {
		validateObject(vehicleRO);
		final Vehicle vehicle = constructVehicle(vehicleRO);
		validateGenericObject(vehicle);
		final Vehicle updatedVehicle = _vehicleService.updateVehicle(vehicle);
		if (updatedVehicle != null) {
			return _mapperService.map(updatedVehicle, VehicleRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public Set<VehicleRO> updateVehicles(final VehicleWrapper vehicleWrapper) {
		validateObject(vehicleWrapper);
		validateCollectionObject(vehicleWrapper.getVehicles());
		final Set<Vehicle> vehicles = new HashSet<Vehicle>(0);
		if (vehicleWrapper.getVehicles() != null && !vehicleWrapper.getVehicles().isEmpty()) {
			for (VehicleRO vehicleRO : vehicleWrapper.getVehicles()) {
				if (vehicleRO != null) {
					final Vehicle vehicle = constructVehicle(vehicleRO);
					if (vehicle != null) {
						vehicles.add(vehicle);
					}
				}
			}
		}		
		if (vehicles != null && !vehicles.isEmpty()) {
			final Set<Vehicle> updatedVehicles = _vehicleService.updateVehicles(vehicles);
			if (updatedVehicles != null && !updatedVehicles.isEmpty()) {
				return _mapperService.map(updatedVehicles, VehicleRO.class);
			} else {
				throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
			}
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CONSTRUCT_OBJECT_FOR_RECORD_CREATION));
		}
	}

	@Override
	public Vehicle constructNewVehicle(final VehicleRO vehicleRO) {
		validateObject(vehicleRO);
		final Vehicle vehicle = new Vehicle.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
		validateGenericObject(vehicle);
		return setOtherFieldsForVehicle(vehicle, vehicleRO);
	}
	
	@Override
	public Vehicle constructVehicle(final VehicleRO vehicleRO) {
		validateObject(vehicleRO);
		final Vehicle vehicle = _vehicleService.get(vehicleRO.getId());
		validateGenericObject(vehicle);
		return setOtherFieldsForVehicle(vehicle, vehicleRO);
	}
	
	@Override
	public Vehicle setOtherFieldsForVehicle(final Vehicle vehicle, final VehicleRO vehicleRO) {
		if (vehicle != null && vehicleRO != null) {
			// Set other values for vehicle
			// vehicle registration id
			if (nullOrEmptySafeCheck(vehicleRO.getVehicleRegistrationId())) {
				vehicle.setVehicleRegistrationId(stringTrimmer(vehicleRO.getVehicleRegistrationId()));
			}
			// engine number
			if (nullOrEmptySafeCheck(vehicleRO.getEngineNumber())) {
				vehicle.setEngineNumber(stringTrimmer(vehicleRO.getEngineNumber()));
			}
			// chasis number
			if (nullOrEmptySafeCheck(vehicleRO.getChasisNumber())) {
				vehicle.setChasisNumber(stringTrimmer(vehicleRO.getChasisNumber()));
			}
			// make
			if (nullOrEmptySafeCheck(vehicleRO.getMake())) {
				vehicle.setMake(stringTrimmer(vehicleRO.getMake()));
			}
			// model
			if (nullOrEmptySafeCheck(vehicleRO.getModel())) {
				vehicle.setModel(stringTrimmer(vehicleRO.getModel()));
			}
			// comment description
			if (nullOrEmptySafeCheck(vehicleRO.getCommentDescription())) {
				vehicle.setCommentDescription(stringTrimmer(vehicleRO.getCommentDescription()));
			}
			// Vehicle damage type
			if (vehicleRO.getVehicleDamageType() != null) {
				if (nullOrEmptySafeCheck(vehicleRO.getVehicleDamageType().getId())) {
					vehicle.setVehicleDamageType(_tableMaintenanceService.getVehicleDamageTypeByCode(stringTrimmer(vehicleRO.getVehicleDamageType().getId())));
				}
			}
			// asset category
			if (vehicleRO.getAssetCategory() != null) {
				if (nullOrEmptySafeCheck(vehicleRO.getAssetCategory().getId())) {
					vehicle.setAssetCategory(_tableMaintenanceService.getAssetCategoryByCode(stringTrimmer(vehicleRO.getAssetCategory().getId().trim())));
				}
			}
			return vehicle;
		} else {
			return null;
		}
	}	
}
