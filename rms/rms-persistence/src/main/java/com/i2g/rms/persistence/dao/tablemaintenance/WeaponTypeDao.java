package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.WeaponType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface WeaponTypeDao {
	
	public List<WeaponType> get();

	public WeaponType getByCode(final String code);

	public WeaponType create(final String code, final String description);

	public WeaponType update(final String code, final String description);

	public void delete(final String code);
	
}
