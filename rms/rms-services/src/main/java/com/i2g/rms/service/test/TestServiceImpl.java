package com.i2g.rms.service.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.test.TestDepartment;
import com.i2g.rms.domain.model.test.MyDepartment;
import com.i2g.rms.domain.model.test.MyEmployee;
import com.i2g.rms.domain.model.test.Pagination;
import com.i2g.rms.persistence.dao.test.TestDepartmentDao;
import com.i2g.rms.persistence.dao.test.HibernateEHCacheMainTest;
import com.i2g.rms.persistence.dao.test.MyDepartmentDao;
import com.i2g.rms.persistence.dao.test.PaginationDao;

/**
 * Back-end test service layer which will work with domain objects.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class TestServiceImpl extends TestAbstractService implements TestService {
	
	private final Logger _logger = LoggerFactory.getLogger(TestServiceImpl.class);

	@Autowired
	private TestDepartmentDao _testDepartmentDao;
	@Autowired
	private PaginationDao _paginationDao;
	@Autowired
	private HibernateEHCacheMainTest _hibernateEHCacheMainTest;
	@Autowired
	private MyDepartmentDao _myDepartmentDao;
	
	private TestServiceImpl() {}

	@Override
	public String greetCustomer() {
		return _testDepartmentDao.greetCustomer();
	}
	
	@Override
	@Transactional(readOnly = true)
	public TestDepartment getDeptByDeptNo(final Long deptNo) {
		return _testDepartmentDao.getDeptByDeptNo(deptNo);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TestDepartment> getAllDepartments() {
		return _testDepartmentDao.getAllDepartments();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Pagination> getPaginationRecords() {
		return _paginationDao.getPaginationRecords();
	}

	@Override
	public List<MyDepartment> getMyDepartments() {
		return _myDepartmentDao.getMyDepartments();
	}
	
	@Override
	public List<MyEmployee> getMyEmployees() {
		return _myDepartmentDao.getMyEmployees();
	}
	
	@Override
	@Transactional
	public String testSecondLevelCache() {
		return _hibernateEHCacheMainTest.testSecondLevelCache();
	}
	
	@Override
	@Transactional
	public TestDepartment createDepartment(final Long deptNo, final String dname, final String loc) {
		return _testDepartmentDao.createDepartment(deptNo, dname, loc);
	}
	
	@Override
	@Transactional
	public TestDepartment updateDepartment(TestDepartment department) {
		return _testDepartmentDao.updateDepartment(department);
	}

	@Override
	@Transactional
	public void deleteDepartment(final TestDepartment department) {
		_testDepartmentDao.deleteDepartment(department);
	}
	
	@Override
	@Transactional(readOnly = true)
	public MyDepartment getMyDeptByDeptNo(final Long deptNo) {
		return _myDepartmentDao.getMyDeptByDeptNo(deptNo);
	}

	@Override
	@Transactional
	public MyDepartment createMyDepartment(Long deptNo, String dname, String loc) {
		return _myDepartmentDao.createMyDepartment(deptNo, dname, loc);
	}

	@Override
	public MyDepartment updateMyDepartment(MyDepartment myDepartment) {
		return _myDepartmentDao.updateMyDepartment(myDepartment);
	}

	@Override
	public void deleteMyDepartment(MyDepartment myDepartment) {
		_myDepartmentDao.deleteMyDepartment(myDepartment);		
	}
}
