package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.Investigation;

/**
 * Back-end DAO for Investigation related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InvestigationDao {

	public List<Investigation> get();

	public Investigation get(final long id);

	public Investigation create(final Investigation investigation);

}
