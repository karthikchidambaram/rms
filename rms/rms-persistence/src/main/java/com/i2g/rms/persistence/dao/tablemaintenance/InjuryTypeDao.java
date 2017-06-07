package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.InjuryType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InjuryTypeDao {
	
	public List<InjuryType> get();

	public InjuryType getByCode(final String code);

	public InjuryType create(final String code, final String description);

	public InjuryType update(final String code, final String description);

	public void delete(final String code);
	
}
