package com.i2g.rms.rest.service.test;

import java.util.List;

import com.i2g.rms.rest.model.UserContextRO;
import com.i2g.rms.rest.model.test.MyDepartmentRO;
import com.i2g.rms.rest.model.test.MyEmployeeRO;
import com.i2g.rms.rest.model.test.PaginationRO;
import com.i2g.rms.rest.model.test.TestDepartmentRO;
import com.i2g.rms.rest.model.test.TestMessageRO;

/**
 * Interface for test rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TestRestService {
	
	public String sayHello(final String username);

	public TestMessageRO greetCustomer();
	
	public TestDepartmentRO getDeptByDeptNo(final Long deptNo);
	
	public List<TestDepartmentRO> getAllDepartments();
	
	public String testErrorMessage();
	
	public void testThrowResourceNotFoundException();
	
	public List<PaginationRO> getPaginationRecords();
	
	public List<MyDepartmentRO> getMyDepartments();
	
	public List<MyEmployeeRO> getMyEmployees();
	
	public String testSecondLevelCache();
	
	public TestDepartmentRO createDepartment(final TestDepartmentRO departmentRO);
	
	public TestDepartmentRO updateDepartment(final TestDepartmentRO departmentRO);

	public void deleteDepartment(final Long deptNo);
	
	public MyDepartmentRO createMyDepartment(final MyDepartmentRO myDepartmentRO);
	
	public MyDepartmentRO updateMyDepartment(final MyDepartmentRO myDepartmentRO);
	
	public void deleteMyDepartment(final Long deptNo);
	
	public UserContextRO testAuthentication();
	
}
