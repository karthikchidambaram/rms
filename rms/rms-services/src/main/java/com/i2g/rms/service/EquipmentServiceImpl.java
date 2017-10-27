package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Equipment;
import com.i2g.rms.persistence.dao.EquipmentDao;

/**
 * Back-end service for Equipment related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class EquipmentServiceImpl extends AbstractService implements EquipmentService {

	@Autowired
	private EquipmentDao _equipmentDao;

	private EquipmentServiceImpl() {
	}

	@Override
	public List<Equipment> get() {
		return _equipmentDao.get();
	}

	@Override
	public Equipment get(final long id) {
		return _equipmentDao.get(id);
	}
	
	@Override
	public Equipment get(final String equipmentId) {
		return _equipmentDao.get(equipmentId);
	}

	@Override
	public Equipment createEquipment(final Equipment equipment) {
		return _equipmentDao.createEquipment(equipment);
	}

	@Override
	public Set<Equipment> createEquipments(final Set<Equipment> equipments) {
		return _equipmentDao.createEquipments(equipments);
	}

	@Override
	public Equipment updateEquipment(final Equipment equipment) {
		return _equipmentDao.updateEquipment(equipment);
	}

	@Override
	public Set<Equipment> updateEquipments(final Set<Equipment> equipments) {
		return _equipmentDao.updateEquipments(equipments);
	}
}
