package com.i2g.rms.service.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.EntryPoint;

/**
 * Service interface for all table maintenance operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TableMaintenanceService {

	/** Generic methods */
	public List<Object[]> get(final String tableName, final String operation);
	public List<Object[]> getByCode(final String tableName, final String operation, final String code);
	public int create(final String tableName, final String operation, final String code, final String description);
	public int update(final String tableName, final String operation, final String code, final String description);
	public int delete(final String tableName, final String operation, final List<String> codes);

	/** Methods related to Entry Point */
	public List<EntryPoint> getEntryPoints();

	public EntryPoint getEntryPointByCode(final String code);

	public EntryPoint createEntryPoint(final String code, final String description);

	public EntryPoint updateEntryPoint(final String code, final String description);

	public void deleteEntryPoint(final String code);

}
