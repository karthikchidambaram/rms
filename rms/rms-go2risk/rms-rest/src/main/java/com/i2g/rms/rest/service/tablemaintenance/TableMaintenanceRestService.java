package com.i2g.rms.rest.service.tablemaintenance;

import java.util.List;

import com.i2g.rms.rest.model.tablemaintenance.EntryPointRO;
import com.i2g.rms.rest.model.tablemaintenance.TableMaintenanceRO;

/**
 * Rest Service Interface for all table maintenance objects.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TableMaintenanceRestService {

	/** Generic method for table maintenance */
	public List<TableMaintenanceRO> get(final String tableName, final String operation);

	public TableMaintenanceRO getByCode(final String tableName, final String operation, final String code);

	public TableMaintenanceRO create(final TableMaintenanceRO tableMaintenanceRO, final String tableName, final String operation);

	public TableMaintenanceRO update(final TableMaintenanceRO tableMaintenanceRO, final String tableName, final String operation);

	public void delete(final TableMaintenanceRO tableMaintenanceRO, final String tableName, final String operation);

	/** Methods related to Entry Point */
	public List<EntryPointRO> getEntryPoints();

	public EntryPointRO getEntryPointByCode(final String code);

	public EntryPointRO createEntryPoint(final EntryPointRO entryPointRO);

	public EntryPointRO updateEntryPoint(final EntryPointRO entryPointRO);

	public void deleteEntryPoint(final String code);

}
