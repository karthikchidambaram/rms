package com.i2g.rms.rest.controller.test;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.test.Pagination;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.UserContextRO;
import com.i2g.rms.rest.model.test.MyDepartmentRO;
import com.i2g.rms.rest.model.test.MyEmployeeRO;
import com.i2g.rms.rest.model.test.PaginationRO;
import com.i2g.rms.rest.model.test.TestDepartmentRO;
import com.i2g.rms.rest.model.test.TestMessageRO;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.test.TestRestService;

/**
 * Rest controller for testing purpose. Initial Base Version added on
 * 15-May-2017 to local SVN.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 */
@RestController
public class TestRestController extends TestAbstractRestController {

	@Autowired
	private TestRestService _testRestService;

	@RequestMapping(value = RequestMappingConstants.TEST_SAY_HELLO, method = RequestMethod.GET)
	public String sayHello(@PathVariable final String username) {
		return _testRestService.sayHello(username);
	}

	@RequestMapping(value = RequestMappingConstants.TEST_GREET_CUSTOMER, method = RequestMethod.GET)
	public TestMessageRO greetCustomer() {
		return _testRestService.greetCustomer();
	}

	@RequestMapping(value = RequestMappingConstants.TEST_GET_DEPT_BY_DEPTNO, method = RequestMethod.GET)
	public TestDepartmentRO getDeptByDeptNo(@PathVariable final Long deptNo) {
		return _testRestService.getDeptByDeptNo(deptNo);
	}

	@RequestMapping(value = RequestMappingConstants.TEST_GET_ALL_DEPARTMENTS, method = RequestMethod.GET)
	public List<TestDepartmentRO> getAllDepartments() {
		return _testRestService.getAllDepartments();
	}

	@RequestMapping(value = RequestMappingConstants.TEST_ERROR_MESSAGE, method = RequestMethod.GET)
	public String testErrorMessage() {
		return _testRestService.testErrorMessage();
	}

	@RequestMapping(value = RequestMappingConstants.TEST_THROW_EXCEPTION, method = RequestMethod.GET)
	public void testThrowResourceNotFoundException() {
		_testRestService.testThrowResourceNotFoundException();
	}

	@RequestMapping(value = RequestMappingConstants.TEST_PAGINATION, method = RequestMethod.GET)
	@Searchable(sourceType = PaginationRO.class, value = Pagination.class)
	public List<PaginationRO> getPaginationRecords() {
		return _testRestService.getPaginationRecords();
	}

	@RequestMapping(value = RequestMappingConstants.TEST_GET_ALL_MY_DEPARTMENTS, method = RequestMethod.GET)
	public List<MyDepartmentRO> getMyDepartments() {
		return _testRestService.getMyDepartments();
	}

	@RequestMapping(value = RequestMappingConstants.TEST_GET_ALL_MY_EMPLOYEES, method = RequestMethod.GET)
	public List<MyEmployeeRO> getMyEmployees() {
		return _testRestService.getMyEmployees();
	}

	@RequestMapping(value = RequestMappingConstants.TEST_HIBERNATE_SECOND_LEVEL_CACHE, method = RequestMethod.GET)
	public String testSecondLevelCache() {
		return _testRestService.testSecondLevelCache();
	}

	@RequestMapping(value = RequestMappingConstants.TEST_CREATE_DEPARTMENT, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public TestDepartmentRO createDepartment(final @Valid @RequestBody TestDepartmentRO departmentRO) {
		return _testRestService.createDepartment(departmentRO);
	}

	@RequestMapping(value = RequestMappingConstants.TEST_UPDATE_DEPARTMENT, method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public TestDepartmentRO updateDepartment(final @Valid @RequestBody TestDepartmentRO departmentRO) {
		return _testRestService.updateDepartment(departmentRO);
	}

	@RequestMapping(value = RequestMappingConstants.TEST_DELETE_DEPARTMENT, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDepartment(final @PathVariable Long deptNo) {
		_testRestService.deleteDepartment(deptNo);
	}

	@RequestMapping(value = RequestMappingConstants.TEST_AUTHENTICATION, method = RequestMethod.POST)
	public UserContextRO testAuthentication() {
		return _testRestService.testAuthentication();
	}

	@RequestMapping(value = RequestMappingConstants.TEST_CREATE_MY_DEPARTMENT, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public MyDepartmentRO createMyDepartment(final @Valid @RequestBody MyDepartmentRO myDepartmentRO) {
		return _testRestService.createMyDepartment(myDepartmentRO);
	}

	@RequestMapping(value = RequestMappingConstants.TEST_UPDATE_MY_DEPARTMENT, method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public MyDepartmentRO updateMyDepartment(final @Valid @RequestBody MyDepartmentRO myDepartmentRO) {
		return _testRestService.updateMyDepartment(myDepartmentRO);
	}

	@RequestMapping(value = RequestMappingConstants.TEST_DELETE_MY_DEPARTMENT, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMyDepartment(final @PathVariable Long deptNo) {
		_testRestService.deleteMyDepartment(deptNo);
	}
}
