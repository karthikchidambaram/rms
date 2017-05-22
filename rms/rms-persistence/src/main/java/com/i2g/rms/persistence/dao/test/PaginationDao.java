package com.i2g.rms.persistence.dao.test;

import java.util.List;

import com.i2g.rms.domain.model.test.Pagination;

/**
 * Data Access Object for Testing of pagination functionality.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface PaginationDao {
	
	public List<Pagination> getPaginationRecords();
	
}
