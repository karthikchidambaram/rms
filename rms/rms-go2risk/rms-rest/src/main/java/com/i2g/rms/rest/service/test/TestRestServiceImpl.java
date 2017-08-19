package com.i2g.rms.rest.service.test;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.test.MyDepartment;
import com.i2g.rms.domain.model.test.TestDepartment;
import com.i2g.rms.rest.model.UserContextRO;
import com.i2g.rms.rest.model.test.MyDepartmentRO;
import com.i2g.rms.rest.model.test.MyEmployeeRO;
import com.i2g.rms.rest.model.test.PaginationRO;
import com.i2g.rms.rest.model.test.TestDepartmentRO;
import com.i2g.rms.rest.model.test.TestMessageRO;
import com.i2g.rms.rest.service.RestMessage;
import com.i2g.rms.service.exception.ResourceNotFoundException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.test.TestService;

/**
 * Rest services for all rest controllers.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class TestRestServiceImpl extends TestAbstractRestService implements TestRestService {

	private final Logger _logger = LoggerFactory.getLogger(TestRestServiceImpl.class);

	@Autowired
	private TestService _testService;

	@Override
	public String sayHello(final String username) {
		if (username == null || username.trim().isEmpty()) {
			return "Hello Guest, welcome to RMS Rest Module!";
		} else {
			return "Hello " + username + ", welcome to RMS Rest Module!";
		}
	}

	@Override
	public TestMessageRO greetCustomer() {
		TestMessageRO testMessageRO = new TestMessageRO();
		testMessageRO.setMessage(_testService.greetCustomer());
		return testMessageRO;
	}

	@Override
	public TestDepartmentRO getDeptByDeptNo(final Long deptNo) {
		TestDepartment department = _testService.getDeptByDeptNo(deptNo);
		return _mapperService.map(department, TestDepartmentRO.class);
	}

	@Override
	public List<TestDepartmentRO> getAllDepartments() {
		List<TestDepartment> departments = _testService.getAllDepartments();
		List<TestDepartmentRO> departmentROs = departments.isEmpty() ? Collections.emptyList()
				: _mapperService.map(departments, TestDepartmentRO.class);
		return departmentROs;
	}

	@Override
	public String testErrorMessage() {
		String errorMessage = "";
		errorMessage = _messageBuilder.build(RestMessage.TEST_ERROR_MESSAGE);
		return errorMessage;
	}

	@Override
	public void testThrowResourceNotFoundException() {
		_logger.error("RMS Custom Exception Handling: {}", RestMessage.TEST_ERROR_MESSAGE);
		throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.TEST_ERROR_MESSAGE));
	}

	@Override
	public List<PaginationRO> getPaginationRecords() {
		return _mapperService.map(_testService.getPaginationRecords(), PaginationRO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MyDepartmentRO> getMyDepartments() {

		return _mapperService.map(_testService.getMyDepartments(), MyDepartmentRO.class);

		/*
		 * List<MyDepartment> myDepartments = new ArrayList<>();
		 * List<MyDepartmentRO> myDepartmentROs = new ArrayList<>();
		 * 
		 * myDepartments = _testService.getMyDepartments();
		 * 
		 * for (MyDepartment myDepartment : myDepartments) {
		 * 
		 * MyDepartmentRO myDepartmentRO = new MyDepartmentRO();
		 * 
		 * myDepartmentRO.setDeptNo(myDepartment.getDeptNo());
		 * myDepartmentRO.setDName(myDepartment.getDName());
		 * myDepartmentRO.setLoc(myDepartment.getLoc());
		 * 
		 * Set<MyEmployee> myEmployees = new HashSet<MyEmployee>(0);
		 * Set<MyEmployeeRO> myEmployeeROs = new HashSet<MyEmployeeRO>(0);
		 * myEmployees = myDepartment.getEmployees();
		 * 
		 * for (MyEmployee myEmployee : myEmployees) {
		 * 
		 * MyEmployeeRO myEmployeeRO = new MyEmployeeRO();
		 * 
		 * myEmployeeRO.setEmpNo(myEmployee.getEmpNo());
		 * myEmployeeRO.setEName(myEmployee.getEName());
		 * myEmployeeRO.setJob(myEmployee.getJob());
		 * myEmployeeRO.setMgr(myEmployee.getMgr());
		 * myEmployeeRO.setHireDate(myEmployee.getHireDate());
		 * myEmployeeRO.setSal(myEmployee.getSal());
		 * myEmployeeRO.setComm(myEmployee.getComm());
		 * 
		 * myEmployeeROs.add(myEmployeeRO); }
		 * myDepartmentRO.setEmployees(myEmployeeROs);
		 * myDepartmentROs.add(myDepartmentRO); } return myDepartmentROs;
		 */
	}

	@Override
	@Transactional(readOnly = true)
	public List<MyEmployeeRO> getMyEmployees() {

		return _mapperService.map(_testService.getMyEmployees(), MyEmployeeRO.class);

		/*
		 * List<MyEmployee> myEmployees = new ArrayList<>(); List<MyEmployeeRO>
		 * myEmployeeROs = new ArrayList<>(); myEmployees =
		 * _testService.getMyEmployees();
		 * 
		 * for (MyEmployee myEmployee : myEmployees) {
		 * 
		 * MyEmployeeRO myEmployeeRO = new MyEmployeeRO();
		 * 
		 * myEmployeeRO.setEmpNo(myEmployee.getEmpNo());
		 * myEmployeeRO.setEName(myEmployee.getEName());
		 * myEmployeeRO.setJob(myEmployee.getJob());
		 * myEmployeeRO.setMgr(myEmployee.getMgr());
		 * myEmployeeRO.setSal(myEmployee.getSal());
		 * myEmployeeRO.setComm(myEmployee.getComm());
		 * 
		 * MyDepartment myDepartment = myEmployee.getDepartment();
		 * MyDepartmentRO myDepartmentRO = new MyDepartmentRO();
		 * myDepartmentRO.setDeptNo(myDepartment.getDeptNo());
		 * myDepartmentRO.setDName(myDepartment.getDName());
		 * myDepartmentRO.setLoc(myDepartment.getLoc());
		 * 
		 * myEmployeeRO.setDepartment(myDepartmentRO);
		 * 
		 * myEmployeeROs.add(myEmployeeRO); } return myEmployeeROs;
		 */
	}

	@Override
	public String testSecondLevelCache() {
		return _testService.testSecondLevelCache();
	}

	@Override
	public TestDepartmentRO createDepartment(final TestDepartmentRO departmentRO) {
		//Validate if the input parameters are valid
		validateDepartmentParams(departmentRO);
		return _mapperService.map(
				_testService.createDepartment(departmentRO.getDeptNo(), departmentRO.getDname(), departmentRO.getLoc()),
				TestDepartmentRO.class);
	}
	
	@Override
	public TestDepartmentRO updateDepartment(final TestDepartmentRO departmentRO) {
		//Validate input parameters
		validateDepartmentParams(departmentRO);
		//Check if there is an existing department before the update.
		//We can search by deptNo because it is unique.
		TestDepartment department = _testService.getDeptByDeptNo(departmentRO.getDeptNo());
		validateDepartment(department);
		//Set the new values
		department.setDname(departmentRO.getDname());
		department.setLoc(departmentRO.getLoc());
		return _mapperService.map(_testService.updateDepartment(department), TestDepartmentRO.class);
	}
	
	@Override
	public void deleteDepartment(final Long deptNo) {
		//Validate input parameters
		if (deptNo == null || deptNo <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.DEPT_NO_NULL_OR_EMPTY));
		}
		//Check if there is an existing department before the delete.
		//We can search by deptNo because it is unique.
		TestDepartment department = _testService.getDeptByDeptNo(deptNo);
		validateDepartment(department);
		_testService.deleteDepartment(department);
	}
	
	private void validateDepartmentParams(final TestDepartmentRO departmentRO) {
		if (departmentRO == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.INVALID_REQUEST_BODY));
		}

		if (departmentRO.getDeptNo() == null || departmentRO.getDeptNo() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.DEPT_NO_NULL_OR_EMPTY));
		}

		if (departmentRO.getDname() == null || departmentRO.getDname().trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.DEPT_NAME_NULL_OR_EMPTY));
		}

		if (departmentRO.getLoc() == null || departmentRO.getLoc().trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.DEPT_LOC_NULL_OR_EMPTY));
		}
	}
	
	private void validateMyDepartmentParams(final MyDepartmentRO myDepartmentRO) {
		if (myDepartmentRO == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.INVALID_REQUEST_BODY));
		}

		if (myDepartmentRO.getDeptNo() == null || myDepartmentRO.getDeptNo() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.DEPT_NO_NULL_OR_EMPTY));
		}

		if (myDepartmentRO.getDname() == null || myDepartmentRO.getDname().trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.DEPT_NAME_NULL_OR_EMPTY));
		}

		if (myDepartmentRO.getLoc() == null || myDepartmentRO.getLoc().trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.DEPT_LOC_NULL_OR_EMPTY));
		}
	}
	
	private void validateDepartment(final TestDepartment department) {
		if (department == null) {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.DEPT_DOES_NOT_EXIST));
		}
	}
	
	private void validateMyDepartment(final MyDepartment myDepartment) {
		if (myDepartment == null) {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.DEPT_DOES_NOT_EXIST));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public MyDepartmentRO createMyDepartment(MyDepartmentRO myDepartmentRO) {
		
		UserContextRO userContextRO = new UserContextRO();
		userContextRO.setUserId(getPrincipalFromContext());
		userContextRO.setRoles(getUserRolesFromContext());
		
		if (userContextRO.getUserId() != null) {
			System.out.println("User ID: " + userContextRO.getUserId());
		} else {
			System.out.println("User ID is returning null from context.");
		}
		
		
		if (userContextRO.getRoles() != null) {
			List<String> roles = userContextRO.getRoles();
			if (!roles.isEmpty()) {
				System.out.println("User has following roles: ");
				for(String role : roles) {
					System.out.println(role);
				}
			}
		}
		
		//Validate if the input parameters are valid
		validateMyDepartmentParams(myDepartmentRO);
		return _mapperService.map(
				_testService.createMyDepartment(myDepartmentRO.getDeptNo(), myDepartmentRO.getDname(), myDepartmentRO.getLoc()),
				MyDepartmentRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public MyDepartmentRO updateMyDepartment(MyDepartmentRO myDepartmentRO) {
		//Validate input parameters
		validateMyDepartmentParams(myDepartmentRO);
		
		MyDepartment myDepartment = new MyDepartment ();
		myDepartment.setDeptNo(myDepartmentRO.getDeptNo());
		myDepartment.setDname(myDepartmentRO.getDname());
		myDepartment.setLoc(myDepartmentRO.getLoc());
		
		return _mapperService.map(_testService.updateMyDepartment(myDepartment), MyDepartmentRO.class);
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	@Transactional
	public void deleteMyDepartment(Long deptNo) {
		//Validate input parameters
		if (deptNo == null || deptNo <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.DEPT_NO_NULL_OR_EMPTY));
		}
		//Check if there is an existing department before the delete.
		//We can search by deptNo because it is unique.
		MyDepartment myDepartment = _testService.getMyDeptByDeptNo(deptNo);
		validateMyDepartment(myDepartment);
		_testService.deleteMyDepartment(myDepartment);		
	}

	@Override
	public UserContextRO testAuthentication() {
		UserContextRO userContextRO = new UserContextRO();
		userContextRO.setUserId(getPrincipalFromContext());
		userContextRO.setRoles(getUserRolesFromContext());
		return userContextRO;
	}
}
