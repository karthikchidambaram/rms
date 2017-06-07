package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.IncidentLocationDetails;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface IncidentLocationDetailsDao {
	
	public List<IncidentLocationDetails> get();

	public IncidentLocationDetails getByCode(final String code);

	public IncidentLocationDetails create(final String code, final String description);

	public IncidentLocationDetails update(final String code, final String description);

	public void delete(final String code);
	
}
