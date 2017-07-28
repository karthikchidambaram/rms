package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.AccidentLocationDetail;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AccidentLocationDetailDao {
	
	public List<AccidentLocationDetail> get();

	public AccidentLocationDetail getByCode(final String code);

	public AccidentLocationDetail create(final String code, final String description);

	public AccidentLocationDetail update(final String code, final String description);

	public void delete(final String code);
	
}
