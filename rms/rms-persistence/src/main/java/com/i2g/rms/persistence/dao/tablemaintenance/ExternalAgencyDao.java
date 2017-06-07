package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.ExternalAgency;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface ExternalAgencyDao {
	
	public List<ExternalAgency> get();

	public ExternalAgency getByCode(final String code);

	public ExternalAgency create(final String code, final String description);

	public ExternalAgency update(final String code, final String description);

	public void delete(final String code);
	
}
