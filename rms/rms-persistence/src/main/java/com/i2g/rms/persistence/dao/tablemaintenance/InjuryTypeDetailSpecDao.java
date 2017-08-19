package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetail;
import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetailSpec;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InjuryTypeDetailSpecDao {
	
	public List<InjuryTypeDetailSpec> get();

	public InjuryTypeDetailSpec getByCode(final String code);

	public InjuryTypeDetailSpec create(final String code, final String description, final InjuryTypeDetail injuryTypeDetail);

	public InjuryTypeDetailSpec update(final String code, final String description);

	public void delete(final String code);
	
}
