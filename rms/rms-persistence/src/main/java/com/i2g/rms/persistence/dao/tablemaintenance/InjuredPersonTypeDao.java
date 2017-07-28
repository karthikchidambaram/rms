package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.InjuredPersonType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InjuredPersonTypeDao {
	
	public List<InjuredPersonType> get();

	public InjuredPersonType getByCode(final String code);

	public InjuredPersonType create(final String code, final String description);

	public InjuredPersonType update(final String code, final String description);

	public void delete(final String code);
	
}
