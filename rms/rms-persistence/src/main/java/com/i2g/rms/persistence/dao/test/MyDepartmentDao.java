package com.i2g.rms.persistence.dao.test;

import java.util.List;

import com.i2g.rms.domain.model.test.MyDepartment;
import com.i2g.rms.domain.model.test.MyEmployee;

/**
 * Data Access Object for Testing purposes.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface MyDepartmentDao {
	
	public MyDepartment getMyDeptByDeptNo(final Long deptNo);
	
	public List<MyDepartment> getMyDepartments();
	
	public List<MyEmployee> getMyEmployees();
	
	public MyDepartment createMyDepartment(final Long deptNo, final String dname, final String loc);

	public MyDepartment updateMyDepartment(final MyDepartment myDepartment);

	public void deleteMyDepartment(final MyDepartment myDepartment);
	
}
