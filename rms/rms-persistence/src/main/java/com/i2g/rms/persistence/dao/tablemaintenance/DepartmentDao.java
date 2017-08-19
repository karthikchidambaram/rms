package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.Department;
import com.i2g.rms.domain.model.tablemaintenance.Organization;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface DepartmentDao {
	
	public List<Department> get();

	public Department getByCode(final String code);

	public Department create(final String code, final String description, final Organization organization);

	public Department update(final String code, final String description);

	public void delete(final String code);
	
}
