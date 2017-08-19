package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.Organization;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface OrganizationDao {
	
	public List<Organization> get();

	public Organization getByCode(final String code);

	public Organization create(final String code, final String description);

	public Organization update(final String code, final String description);

	public void delete(final String code);
	
}
