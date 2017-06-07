package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.InjuryCause;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InjuryCauseDao {
	
	public List<InjuryCause> get();

	public InjuryCause getByCode(final String code);

	public InjuryCause create(final String code, final String description);

	public InjuryCause update(final String code, final String description);

	public void delete(final String code);
	
}
