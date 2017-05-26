package com.i2g.rms.rest.service.tablemaintenance;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.rest.model.tablemaintenance.EntryPointRO;
import com.i2g.rms.rest.model.tablemaintenance.TableMaintenanceRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.rest.service.RestMessage;
import com.i2g.rms.service.exception.ResourceNotFoundException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;
import com.i2g.rms.util.tablemaintenance.TableMaintenanceOperations;

/**
 * Rest services for all table maintenance objects.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class TableMaintenanceRestServiceImpl extends AbstractRestService implements TableMaintenanceRestService {

	private final Logger _logger = LoggerFactory.getLogger(TableMaintenanceRestServiceImpl.class);

	/** Generic service for all table maintenance related operations. */
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;

	/**
	 * Default constructor
	 */
	public TableMaintenanceRestServiceImpl() {
	}

	/** Methods related to Entry Point */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'TESTER', 'ADMIN')")
	public List<EntryPointRO> getEntryPoints() {
		return _mapperService.map(_tableMaintenanceService.getEntryPoints(), EntryPointRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'TESTER', 'ADMIN')")
	public EntryPointRO getEntryPointByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getEntryPointByCode(code), EntryPointRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('TESTER', 'ADMIN')")
	public EntryPointRO createEntryPoint(final EntryPointRO entryPointRO) {
		validateObject(entryPointRO);
		validateCode(entryPointRO.getId());
		validateDescription(entryPointRO.getDescription());
		return _mapperService.map(
				_tableMaintenanceService.createEntryPoint(entryPointRO.getId(), entryPointRO.getDescription()),
				EntryPointRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('TESTER', 'ADMIN')")
	public EntryPointRO updateEntryPoint(final EntryPointRO entryPointRO) {
		validateObject(entryPointRO);
		validateCode(entryPointRO.getId());
		validateDescription(entryPointRO.getDescription());
		return _mapperService.map(
				_tableMaintenanceService.updateEntryPoint(entryPointRO.getId(), entryPointRO.getDescription()),
				EntryPointRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('TESTER', 'ADMIN')")
	public void deleteEntryPoint(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteEntryPoint(code);
	}

	@Override
	public List<TableMaintenanceRO> get(final String tableName, final String operation) {
		validateTableName(tableName);
		validateOperation(operation);
		List<TableMaintenanceRO> tableMaintenanceROs = new ArrayList<>();
		List<Object[]> objects = _tableMaintenanceService.get(tableName, operation);
		if (objects != null && !objects.isEmpty()) {
			for (Object[] object : objects) {
				TableMaintenanceRO tableMaintenanceRO = new TableMaintenanceRO();
				tableMaintenanceRO.setCode(object[0].toString());
				tableMaintenanceRO.setDescription(object[1].toString());
				tableMaintenanceROs.add(tableMaintenanceRO);
			}
		}		
		return tableMaintenanceROs;
	}	

	@Override
	public TableMaintenanceRO getByCode(String tableName, String operation, String code) {
		validateTableName(tableName);
		validateOperation(operation);
		validateCode(code);
		TableMaintenanceRO tableMaintenanceRO = new TableMaintenanceRO();
		List<Object[]> objects = _tableMaintenanceService.getByCode(tableName, operation, code);
		if (objects != null) {
			Object[] object = objects.get(0);
			tableMaintenanceRO.setCode(object[0].toString());
			tableMaintenanceRO.setDescription(object[1].toString());
		} else {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.OBJECT_ARRAY_NULL_OR_EMPTY));
		}
		return tableMaintenanceRO;
	}

	@Override
	@Transactional
	public TableMaintenanceRO create(TableMaintenanceRO tableMaintenanceRO, String tableName, String operation) {
		TableMaintenanceRO tableMaintenanceRONew = null;
		validateTableName(tableName);
		validateOperation(operation);
		validateObject(tableMaintenanceRO);
		String code = tableMaintenanceRO.getCode();
		String description = tableMaintenanceRO.getDescription();
		validateCode(code);
		validateDescription(description);
		int result = _tableMaintenanceService.create(tableName, operation, code, description);
		if (result > 0) {
			List<Object[]> objects = _tableMaintenanceService.getByCode(tableName, TableMaintenanceOperations.GET_BY_CODE.value, code);
			if (objects != null) {
				tableMaintenanceRONew = new TableMaintenanceRO();
				Object[] object = objects.get(0);
				tableMaintenanceRONew.setCode(object[0].toString());
				tableMaintenanceRONew.setDescription(object[1].toString());
			} else {
				throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.OBJECT_ARRAY_NULL_OR_EMPTY));
			}
		} else {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.CREATE_TABLE_MAINTENANCE_RECORD_FAILED));
		}
		return tableMaintenanceRONew;
	}

	@Override
	@Transactional
	public TableMaintenanceRO update(TableMaintenanceRO tableMaintenanceRO, String tableName, String operation) {
		TableMaintenanceRO tableMaintenanceRONew = null;
		validateTableName(tableName);
		validateOperation(operation);
		validateObject(tableMaintenanceRO);
		String code = tableMaintenanceRO.getCode();
		String description = tableMaintenanceRO.getDescription();
		validateCode(code);
		validateDescription(description);
		int result = _tableMaintenanceService.update(tableName, operation, code, description);
		if (result > 0) {
			List<Object[]> objects = _tableMaintenanceService.getByCode(tableName, TableMaintenanceOperations.GET_BY_CODE.value, code);
			if (objects != null) {
				tableMaintenanceRONew = new TableMaintenanceRO();
				Object[] object = objects.get(0);
				tableMaintenanceRONew.setCode(object[0].toString());
				tableMaintenanceRONew.setDescription(object[1].toString());
			} else {
				throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.OBJECT_ARRAY_NULL_OR_EMPTY));
			}
		} else {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.CREATE_TABLE_MAINTENANCE_RECORD_FAILED));
		}
		return tableMaintenanceRONew;
	}

	@Override
	@Transactional
	public void delete(TableMaintenanceRO tableMaintenanceRO, String tableName, String operation) {
		validateTableName(tableName);
		validateOperation(operation);
		validateObject(tableMaintenanceRO);
		List<String> codes = tableMaintenanceRO.getCodes();		
		if (codes == null || codes.isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.CODE_NULL_OR_EMPTY));
		}
		int result = _tableMaintenanceService.delete(tableName, operation, codes);
		if (result <= 0 ) {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.DELETE_OPERATION_FAILED));
		}
		if (result != codes.size()) {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.NOT_ALL_RECORDS_WERE_DELETED));
		}		
	}	
}
