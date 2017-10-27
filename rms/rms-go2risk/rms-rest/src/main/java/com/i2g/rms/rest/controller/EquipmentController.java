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

import com.i2g.rms.domain.model.Equipment;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.EquipmentRO;
import com.i2g.rms.rest.model.wrapper.EquipmentWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.EquipmentRestService;

/**
 * Rest controller for Equipment read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class EquipmentController extends AbstractRestController {
	
	@Autowired
	private EquipmentRestService _equipmentRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_EQUIPMENTS, method = RequestMethod.GET)
	@Searchable(sourceType = EquipmentRO.class, value = Equipment.class)
	public List<EquipmentRO> get() {
		return _equipmentRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_EQUIPMENT_BY_EQUIPMENT_ID, method = RequestMethod.GET)
	public EquipmentRO get(@PathVariable final Long equipmentId) {
		return _equipmentRestService.get(equipmentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_EQUIPMENT_BY_UNIQUE_EQUIPMENT_ID, method = RequestMethod.GET)
	public EquipmentRO get(@PathVariable final String uniqueEquipmentId) {
		return _equipmentRestService.get(uniqueEquipmentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_EQUIPMENT, method = RequestMethod.POST)
	public EquipmentRO createEquipment(@Valid @RequestBody final EquipmentRO equipmentRO) {
		return _equipmentRestService.createEquipment(equipmentRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_EQUIPMENTS, method = RequestMethod.POST)
	public Set<EquipmentRO> createEquipments(@Valid @RequestBody final EquipmentWrapper equipmentWrapper) {
		return _equipmentRestService.createEquipments(equipmentWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_EQUIPMENT, method = RequestMethod.PUT)
	public EquipmentRO updateEquipment(@Valid @RequestBody final EquipmentRO equipmentRO) {
		return _equipmentRestService.updateEquipment(equipmentRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_EQUIPMENTS, method = RequestMethod.PUT)
	public Set<EquipmentRO> updateEquipments(@Valid @RequestBody final EquipmentWrapper equipmentWrapper) {
		return _equipmentRestService.updateEquipments(equipmentWrapper);
	}		
}
