package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.ClaimRequestRegistrationType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface ClaimRequestRegistrationTypeDao {
	
	public List<ClaimRequestRegistrationType> get();

	public ClaimRequestRegistrationType getByCode(final String code);

	public ClaimRequestRegistrationType create(final String code, final String description);

	public ClaimRequestRegistrationType update(final String code, final String description);

	public void delete(final String code);
	
}
