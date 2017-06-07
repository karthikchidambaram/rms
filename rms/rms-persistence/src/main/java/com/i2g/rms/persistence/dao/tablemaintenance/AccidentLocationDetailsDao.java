package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.AccidentLocationDetails;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AccidentLocationDetailsDao {
	
	public List<AccidentLocationDetails> get();

	public AccidentLocationDetails getByCode(final String code);

	public AccidentLocationDetails create(final String code, final String description);

	public AccidentLocationDetails update(final String code, final String description);

	public void delete(final String code);
	
}
