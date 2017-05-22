package com.i2g.rms.rest.service.tablemaintenance;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.i2g.rms.rest.model.tablemaintenance.EntryPointRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;

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
}
