package com.i2g.rms.rest.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Equipment;
import com.i2g.rms.rest.model.EquipmentRO;
import com.i2g.rms.rest.model.wrapper.EquipmentWrapper;

/**
 * Rest Service Interface for equipment rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface EquipmentRestService {

	public List<EquipmentRO> get();
	
	public List<EquipmentRO> getEquipmentTableByAssetId(final Long assetId);

	public EquipmentRO get(final Long equipmentId);
	
	public EquipmentRO get(final String uniqueEquipmentId);

	public EquipmentRO createEquipment(final EquipmentRO equipmentRO);

	public Set<EquipmentRO> createEquipments(final EquipmentWrapper equipmentWrapper);

	public EquipmentRO updateEquipment(final EquipmentRO equipmentRO);

	public Set<EquipmentRO> updateEquipments(final EquipmentWrapper equipmentWrapper);

	public Equipment constructNewEquipment(final EquipmentRO equipmentRO);

	public Equipment constructEquipment(final EquipmentRO equipmentRO);

	public Equipment setOtherFieldsForEquipment(final Equipment equipment, final EquipmentRO equipmentRO);

}
