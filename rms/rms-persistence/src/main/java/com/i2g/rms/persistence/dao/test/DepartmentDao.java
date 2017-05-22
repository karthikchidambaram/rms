package com.i2g.rms.persistence.dao.test;

import java.util.List;

import com.i2g.rms.domain.model.test.Department;

/**
 * Data Access Object for Testing purposes.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface DepartmentDao {

	public String greetCustomer();

	public Department getDeptByDeptNo(final Long deptNo);

	public List<Department> getAllDepartments();

	public Department createDepartment(final Long deptNo, final String dname, final String loc);

	public Department updateDepartment(final Department department);

	public void deleteDepartment(final Department department);

}
