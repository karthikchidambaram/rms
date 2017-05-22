package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.EntryPoint;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface EntryPointDao {
	
	public List<EntryPoint> get();

	public EntryPoint getByCode(final String code);

	public EntryPoint create(final String code, final String description);

	public EntryPoint update(final String code, final String description);

	public void delete(final String code);
	
}
