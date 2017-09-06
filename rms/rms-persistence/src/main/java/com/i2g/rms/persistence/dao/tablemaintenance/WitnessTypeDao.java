package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.WitnessType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface WitnessTypeDao {
	
	public List<WitnessType> get();

	public WitnessType getByCode(final String code);

	public WitnessType create(final String code, final String description);

	public WitnessType update(final String code, final String description);

	public void delete(final String code);
	
}
