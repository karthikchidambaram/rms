package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.IncidentCategory;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface IncidentCategoryDao {
	
	public List<IncidentCategory> get();

	public IncidentCategory getByCode(final String code);

	public IncidentCategory create(final String code, final String description);

	public IncidentCategory update(final String code, final String description);

	public void delete(final String code);
	
}
