package com.i2g.rms.service.test;

import java.util.List;

import com.i2g.rms.domain.model.test.Department;
import com.i2g.rms.domain.model.test.MyDepartment;
import com.i2g.rms.domain.model.test.MyEmployee;
import com.i2g.rms.domain.model.test.Pagination;

/**
 * Base service interface for testing purpose.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TestService {

	public String greetCustomer();

	public Department getDeptByDeptNo(final Long deptNo);

	public List<Department> getAllDepartments();
	
	public List<Pagination> getPaginationRecords();
	
	public List<MyDepartment> getMyDepartments();
	
	public List<MyEmployee> getMyEmployees();
	
	public String testSecondLevelCache();
	
	public Department createDepartment(final Long deptNo, final String dname, final String loc);
	
	public Department updateDepartment(final Department department);

	public void deleteDepartment(final Department department);
	
	public MyDepartment getMyDeptByDeptNo(final Long deptNo);
	
	public MyDepartment createMyDepartment(final Long deptNo, final String dname, final String loc);
	
	public MyDepartment updateMyDepartment(final MyDepartment myDepartment);

	public void deleteMyDepartment(final MyDepartment myDepartment);
	
}
