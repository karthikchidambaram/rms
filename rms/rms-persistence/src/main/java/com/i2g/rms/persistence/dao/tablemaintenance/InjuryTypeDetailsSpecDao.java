package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetailsSpec;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InjuryTypeDetailsSpecDao {
	
	public List<InjuryTypeDetailsSpec> get();

	public InjuryTypeDetailsSpec getByCode(final String code);

	public InjuryTypeDetailsSpec create(final String code, final String description);

	public InjuryTypeDetailsSpec update(final String code, final String description);

	public void delete(final String code);
	
}
