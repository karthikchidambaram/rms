package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.AccidentLocation;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AccidentLocationDao {
	
	public List<AccidentLocation> get();

	public AccidentLocation getByCode(final String code);

	public AccidentLocation create(final String code, final String description);

	public AccidentLocation update(final String code, final String description);

	public void delete(final String code);
	
}
