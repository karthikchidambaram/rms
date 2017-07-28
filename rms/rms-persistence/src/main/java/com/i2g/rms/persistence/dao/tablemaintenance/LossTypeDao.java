package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.LossType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface LossTypeDao {
	
	public List<LossType> get();

	public LossType getByCode(final String code);

	public LossType create(final String code, final String description);

	public LossType update(final String code, final String description);

	public void delete(final String code);
	
}
