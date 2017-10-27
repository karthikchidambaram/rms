package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Equipment;

/**
 * Service interface for all Equipment related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface EquipmentService {
	
	public List<Equipment> get();
	
	public Equipment get(final long id);
	
	public Equipment get(final String equipmentId);
	
	public Equipment createEquipment(final Equipment equipment);
	
	public Set<Equipment> createEquipments(final Set<Equipment> equipments);
	
	public Equipment updateEquipment(final Equipment equipment);
	
	public Set<Equipment> updateEquipments(final Set<Equipment> equipments);
	
}
