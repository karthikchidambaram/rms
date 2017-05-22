package com.i2g.rms.rest.controller.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.test.LoginRO;
import com.i2g.rms.rest.service.test.TestLoginRestService;

/**
 * Rest controller for authentication.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class TestLoginRestController extends TestAbstractRestController {
	
	@Autowired
	TestLoginRestService _testLoginRestService;
	
	@RequestMapping(value = RequestMappingConstants.LOGIN_ACTION, method = RequestMethod.POST)
	public ResponseEntity<UserRO> doLogin(final @Valid @RequestBody LoginRO loginRO, HttpSession session) {
		return _testLoginRestService.doLogin(loginRO, session);
	}
	
	@RequestMapping(value = RequestMappingConstants.LOGIN_ACTION_FROM_HTML, method = RequestMethod.POST)
	public String doLoginHtml(HttpServletRequest request, HttpServletResponse response) {
		return _testLoginRestService.doLoginHtml(request, response);
	}
	
	@RequestMapping(value = RequestMappingConstants.LOGIN_STATUS_CHECK, method = RequestMethod.POST)
	public String checkLoginStatus(@PathVariable final String loginId, HttpServletRequest request, HttpServletResponse response) {
		return _testLoginRestService.checkLoginStatus(loginId, request, response);
	}
	
	@RequestMapping(value = RequestMappingConstants.LOGOUT_ACTION, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void doLogout(@PathVariable final String loginId, HttpServletRequest request, HttpServletResponse response) {
		_testLoginRestService.doLogout(loginId, request, response);
	}
	
	@RequestMapping(value = RequestMappingConstants.LOGIN_ACTION_BASIC, method = RequestMethod.POST)
	public ResponseEntity<UserRO> doLogin(HttpServletRequest request, HttpServletResponse response) {
		return _testLoginRestService.doLogin(request, response);
	}
}
