package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.AccidentType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AccidentTypeDao {
	
	public List<AccidentType> get();

	public AccidentType getByCode(final String code);

	public AccidentType create(final String code, final String description);

	public AccidentType update(final String code, final String description);

	public void delete(final String code);
	
}
