package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.IncidentType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface IncidentTypeDao {
	
	public List<IncidentType> get();

	public IncidentType getByCode(final String code);

	public IncidentType create(final String code, final String description);

	public IncidentType update(final String code, final String description);

	public void delete(final String code);
	
}
