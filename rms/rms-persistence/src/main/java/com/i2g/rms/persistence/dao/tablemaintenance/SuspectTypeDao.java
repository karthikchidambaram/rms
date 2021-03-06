package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.SuspectType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface SuspectTypeDao {
	
	public List<SuspectType> get();

	public SuspectType getByCode(final String code);

	public SuspectType create(final String code, final String description);

	public SuspectType update(final String code, final String description);

	public void delete(final String code);
	
}
