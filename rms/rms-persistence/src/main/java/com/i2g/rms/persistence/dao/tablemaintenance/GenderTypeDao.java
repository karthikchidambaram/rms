package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.GenderType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface GenderTypeDao {
	
	public List<GenderType> get();

	public GenderType getByCode(final String code);

	public GenderType create(final String code, final String description);

	public GenderType update(final String code, final String description);

	public void delete(final String code);
	
}
