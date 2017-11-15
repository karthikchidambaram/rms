package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Asset;
import com.i2g.rms.domain.model.Equipment;

/**
 * Back-end DAO for Equipment related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface EquipmentDao {
	
	public List<Equipment> get();
	
	public List<Equipment> get(final Asset asset);
	
	public Equipment get(final long id);
	
	public Equipment get(final String equipmentId);
	
	public Equipment createEquipment(final Equipment equipment);
	
	public Set<Equipment> createEquipments(final Set<Equipment> equipments);
	
	public Equipment updateEquipment(final Equipment equipment);
	
	public Set<Equipment> updateEquipments(final Set<Equipment> equipments);	
	
}
