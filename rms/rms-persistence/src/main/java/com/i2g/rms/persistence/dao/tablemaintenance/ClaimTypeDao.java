package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.ClaimType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface ClaimTypeDao {
	
	public List<ClaimType> get();

	public ClaimType getByCode(final String code);

	public ClaimType create(final String code, final String description);

	public ClaimType update(final String code, final String description);

	public void delete(final String code);
	
}
