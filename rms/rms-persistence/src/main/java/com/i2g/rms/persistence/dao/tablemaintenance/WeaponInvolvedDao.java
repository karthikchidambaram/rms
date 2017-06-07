package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.WeaponInvolved;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface WeaponInvolvedDao {
	
	public List<WeaponInvolved> get();

	public WeaponInvolved getByCode(final String code);

	public WeaponInvolved create(final String code, final String description);

	public WeaponInvolved update(final String code, final String description);

	public void delete(final String code);
	
}
