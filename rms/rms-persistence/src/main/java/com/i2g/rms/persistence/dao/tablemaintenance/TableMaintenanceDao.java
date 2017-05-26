package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TableMaintenanceDao {

	public List<Object[]> get(final String tableName, final String operation);

	public List<Object[]> getByCode(final String tableName, final String operation, final String code);

	public int create(final String tableName, final String operation, final String code, final String description);
	
	public int update(final String tableName, final String operation, final String code, final String description);
	
	public int delete(final String tableName, final String operation, final List<String> codes);

}
