package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetails;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InjuryTypeDetailsDao {
	
	public List<InjuryTypeDetails> get();

	public InjuryTypeDetails getByCode(final String code);

	public InjuryTypeDetails create(final String code, final String description);

	public InjuryTypeDetails update(final String code, final String description);

	public void delete(final String code);
	
}
