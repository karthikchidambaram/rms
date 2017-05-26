package com.i2g.rms.rest.controller.tablemaintenance;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.AbstractRestController;
import com.i2g.rms.rest.model.tablemaintenance.EntryPointRO;
import com.i2g.rms.rest.model.tablemaintenance.TableMaintenanceRO;
import com.i2g.rms.rest.service.tablemaintenance.TableMaintenanceRestService;

/**
 * Rest controller for all table maintenance objects read/create/update/delete
 * operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class TableMaintenanceController extends AbstractRestController {
	
	/** Generic rest service for all table maintenance related operations */
	@Autowired
	TableMaintenanceRestService _tableMaintenanceRestService;
	

	/** Methods related to Entry Point */
	@RequestMapping(value = RequestMappingConstants.TEST_GET_ENTRY_POINTS, method = RequestMethod.GET)
	public List<EntryPointRO> getEntryPoints() {
		return _tableMaintenanceRestService.getEntryPoints();
	}

	@RequestMapping(value = RequestMappingConstants.TEST_GET_ENTRY_POINT_BY_CODE, method = RequestMethod.GET)
	public EntryPointRO getEntryPointByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getEntryPointByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.TEST_CREATE_ENTRY_POINT, method = RequestMethod.POST)
	public EntryPointRO createEntryPoint(final @Valid @RequestBody EntryPointRO entryPointRO) {
		return _tableMaintenanceRestService.createEntryPoint(entryPointRO);
	}

	@RequestMapping(value = RequestMappingConstants.TEST_UPDATE_ENTRY_POINT, method = RequestMethod.PUT)
	public EntryPointRO updateEntryPoint(final @Valid @RequestBody EntryPointRO entryPointRO) {
		return _tableMaintenanceRestService.updateEntryPoint(entryPointRO);
	}

	@RequestMapping(value = RequestMappingConstants.TEST_DELETE_ENTRY_POINT, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEntryPoint(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteEntryPoint(code);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_TABLE_MAINTENANCE_DATA, method = RequestMethod.GET)
	public List<TableMaintenanceRO> get(@PathVariable final String tableName, @PathVariable final String operation) {
		return _tableMaintenanceRestService.get(tableName, operation);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_TABLE_MAINTENANCE_DATA_BY_CODE, method = RequestMethod.GET)
	public TableMaintenanceRO getByCode(@PathVariable final String tableName, @PathVariable final String operation, @PathVariable final String code) {
		return _tableMaintenanceRestService.getByCode(tableName, operation, code);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_TABLE_MAINTENANCE_DATA, method = RequestMethod.POST)
	public TableMaintenanceRO create(final @Valid @RequestBody TableMaintenanceRO tableMaintenanceRO, @PathVariable final String tableName, @PathVariable final String operation) {
		return _tableMaintenanceRestService.create(tableMaintenanceRO, tableName, operation);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_TABLE_MAINTENANCE_DATA, method = RequestMethod.PUT)
	public TableMaintenanceRO update(final @Valid @RequestBody TableMaintenanceRO tableMaintenanceRO, @PathVariable final String tableName, @PathVariable final String operation) {
		return _tableMaintenanceRestService.update(tableMaintenanceRO, tableName, operation);
	}
	
	@RequestMapping(value = RequestMappingConstants.DELETE_TABLE_MAINTENANCE_DATA, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(final @Valid @RequestBody TableMaintenanceRO tableMaintenanceRO, @PathVariable final String tableName, @PathVariable final String operation) {
		_tableMaintenanceRestService.delete(tableMaintenanceRO, tableName, operation);
	}
	
}
