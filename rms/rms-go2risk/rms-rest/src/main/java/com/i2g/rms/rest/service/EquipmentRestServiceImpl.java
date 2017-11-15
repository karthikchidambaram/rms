package com.i2g.rms.rest.service;

import java.util.ArrayList;
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

import com.i2g.rms.domain.model.Asset;
import com.i2g.rms.domain.model.Equipment;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.rest.model.EquipmentRO;
import com.i2g.rms.rest.model.wrapper.EquipmentWrapper;
import com.i2g.rms.service.AssetService;
import com.i2g.rms.service.EquipmentService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;

/**
 * Rest services for equipment rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class EquipmentRestServiceImpl extends AbstractRestService implements EquipmentRestService {
	
	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(EquipmentRestServiceImpl.class);
	
	@Autowired
	private AssetService _assetService;
	@Autowired
	private EquipmentService _equipmentService;
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;
		
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<EquipmentRO> get() {
		List<Equipment> equipments = _equipmentService.get();
		List<EquipmentRO> equipmentROs = (equipments == null || equipments.isEmpty()) ? Collections.emptyList() : _mapperService.map(equipments, EquipmentRO.class);
		return equipmentROs;
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<EquipmentRO> getEquipmentTableByAssetId(final Long assetId) {
		validateKeyId(assetId);
		final Asset asset = _assetService.get(assetId);
		validateGenericObject(asset);
		List<Equipment> equipments = new ArrayList<Equipment>(asset.getEquipments());
		List<EquipmentRO> equipmentROs = (equipments == null || equipments.isEmpty()) ? Collections.emptyList() : _mapperService.map(equipments, EquipmentRO.class);
		return equipmentROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public EquipmentRO get(final Long equipmentId) {
		validateKeyId(equipmentId);
		final Equipment equipment = _equipmentService.get(equipmentId);
		validateGenericObject(equipment);
		return _mapperService.map(equipment, EquipmentRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public EquipmentRO get(final String uniqueEquipmentId) {
		validateStringObject(uniqueEquipmentId);
		final Equipment equipment = _equipmentService.get(uniqueEquipmentId);
		validateGenericObject(equipment);
		return _mapperService.map(equipment, EquipmentRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public EquipmentRO createEquipment(final EquipmentRO equipmentRO) {
		validateObject(equipmentRO);
		final Equipment equipment = constructNewEquipment(equipmentRO);
		validateGenericObject(equipment);		
		final Equipment newEquipment = _equipmentService.createEquipment(equipment);
		if (newEquipment != null) {
			return _mapperService.map(newEquipment, EquipmentRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public Set<EquipmentRO> createEquipments(final EquipmentWrapper equipmentWrapper) {
		validateObject(equipmentWrapper);
		validateCollectionObject(equipmentWrapper.getEquipments());
		final Set<Equipment> equipments = new HashSet<Equipment>(0);
		if (equipmentWrapper.getEquipments() != null && !equipmentWrapper.getEquipments().isEmpty()) {
			for (EquipmentRO equipmentRO : equipmentWrapper.getEquipments()) {
				if (equipmentRO != null) {
					final Equipment equipment = constructNewEquipment(equipmentRO);
					if (equipment != null) {
						equipments.add(equipment);
					}
				}
			}
		}
		if (equipments != null && !equipments.isEmpty()) {
			final Set<Equipment> newEquipments = _equipmentService.createEquipments(equipments);
			if (newEquipments != null && !newEquipments.isEmpty()) {
				return _mapperService.map(newEquipments, EquipmentRO.class);
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
	public EquipmentRO updateEquipment(final EquipmentRO equipmentRO) {
		validateObject(equipmentRO);
		final Equipment equipment = constructEquipment(equipmentRO);
		validateGenericObject(equipment);
		final Equipment updatedEquipment = _equipmentService.updateEquipment(equipment);
		if (updatedEquipment != null) {
			return _mapperService.map(updatedEquipment, EquipmentRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public Set<EquipmentRO> updateEquipments(final EquipmentWrapper equipmentWrapper) {
		validateObject(equipmentWrapper);
		validateCollectionObject(equipmentWrapper.getEquipments());
		final Set<Equipment> equipments = new HashSet<Equipment>(0);
		if (equipmentWrapper.getEquipments() != null && !equipmentWrapper.getEquipments().isEmpty()) {
			for (EquipmentRO equipmentRO : equipmentWrapper.getEquipments()) {
				if (equipmentRO != null) {
					final Equipment equipment = constructEquipment(equipmentRO);
					if (equipment != null) {
						equipments.add(equipment);
					}
				}
			}
		}		
		if (equipments != null && !equipments.isEmpty()) {
			final Set<Equipment> updatedEquipments = _equipmentService.updateEquipments(equipments);
			if (updatedEquipments != null && !updatedEquipments.isEmpty()) {
				return _mapperService.map(updatedEquipments, EquipmentRO.class);
			} else {
				throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
			}
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CONSTRUCT_OBJECT_FOR_RECORD_CREATION));
		}
	}

	@Override
	public Equipment constructNewEquipment(final EquipmentRO equipmentRO) {
		validateObject(equipmentRO);
		final Equipment equipment = new Equipment.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
		validateGenericObject(equipment);
		return setOtherFieldsForEquipment(equipment, equipmentRO);
	}
	
	@Override
	public Equipment constructEquipment(final EquipmentRO equipmentRO) {
		validateObject(equipmentRO);
		final Equipment equipment = _equipmentService.get(equipmentRO.getId());
		validateGenericObject(equipment);
		return setOtherFieldsForEquipment(equipment, equipmentRO);
	}
	
	@Override
	public Equipment setOtherFieldsForEquipment(final Equipment equipment, final EquipmentRO equipmentRO) {
		if (equipment != null && equipmentRO != null) {
			// Set other values for equipment
			// equipment id
			if (equipmentRO.getEquipmentId() != null && !equipmentRO.getEquipmentId().trim().isEmpty()) {
				equipment.setEquipmentId(equipmentRO.getEquipmentId().trim());
			}
			// equipment details
			if (equipmentRO.getEquipmentDetails() != null && !equipmentRO.getEquipmentDetails().trim().isEmpty()) {
				equipment.setEquipmentDetails(equipmentRO.getEquipmentDetails().trim());
			}
			// serial number
			if (equipmentRO.getSerialNumber() != null && !equipmentRO.getSerialNumber().trim().isEmpty()) {
				equipment.setSerialNumber(equipmentRO.getSerialNumber().trim());
			}
			// asset category
			if (equipmentRO.getAssetCategory() != null) {
				if (equipmentRO.getAssetCategory().getId() != null && !equipmentRO.getAssetCategory().getId().trim().isEmpty()) {
					equipment.setAssetCategory(_tableMaintenanceService.getAssetCategoryByCode(equipmentRO.getAssetCategory().getId().trim()));
				}
			}			
		}
		return equipment;
	}

		
}
