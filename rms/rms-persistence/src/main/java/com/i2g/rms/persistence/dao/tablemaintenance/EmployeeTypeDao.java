package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.EmployeeType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface EmployeeTypeDao {
	
	public List<EmployeeType> get();

	public EmployeeType getByCode(final String code);

	public EmployeeType create(final String code, final String description);

	public EmployeeType update(final String code, final String description);

	public void delete(final String code);
	
}
