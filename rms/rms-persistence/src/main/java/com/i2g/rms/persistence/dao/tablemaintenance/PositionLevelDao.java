package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.PositionLevel;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface PositionLevelDao {
	
	public List<PositionLevel> get();

	public PositionLevel getByCode(final String code);

	public PositionLevel create(final String code, final String description);

	public PositionLevel update(final String code, final String description);

	public void delete(final String code);
	
}
