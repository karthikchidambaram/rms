package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.InjuryType;
import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetail;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InjuryTypeDetailDao {
	
	public List<InjuryTypeDetail> get();
	
	public List<InjuryTypeDetail> get(final InjuryType injuryType);

	public InjuryTypeDetail getByCode(final String code);

	public InjuryTypeDetail create(final String code, final String description, final InjuryType injuryType);

	public InjuryTypeDetail update(final String code, final String description);

	public void delete(final String code);
	
}
