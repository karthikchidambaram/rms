package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.IncidentLocation;
import com.i2g.rms.domain.model.tablemaintenance.IncidentLocationDetail;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface IncidentLocationDetailDao {
	
	public List<IncidentLocationDetail> get();

	public IncidentLocationDetail getByCode(final String code);

	public IncidentLocationDetail create(final String code, final String description, final IncidentLocation incidentLocation);

	public IncidentLocationDetail update(final String code, final String description);

	public void delete(final String code);
	
}
