package com.i2g.rms.persistence.dao.test;

import java.util.List;

import com.i2g.rms.domain.model.test.TestDepartment;

/**
 * Data Access Object for Testing purposes.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TestDepartmentDao {

	public String greetCustomer();

	public TestDepartment getDeptByDeptNo(final Long deptNo);

	public List<TestDepartment> getAllDepartments();

	public TestDepartment createDepartment(final Long deptNo, final String dname, final String loc);

	public TestDepartment updateDepartment(final TestDepartment department);

	public void deleteDepartment(final TestDepartment department);

}
