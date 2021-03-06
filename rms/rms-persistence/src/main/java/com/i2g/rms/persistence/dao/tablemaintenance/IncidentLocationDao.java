package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.IncidentLocation;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface IncidentLocationDao {
	
	public List<IncidentLocation> get();

	public IncidentLocation getByCode(final String code);

	public IncidentLocation create(final String code, final String description);

	public IncidentLocation update(final String code, final String description);

	public void delete(final String code);
	
}
