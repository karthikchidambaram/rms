package com.i2g.rms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.test.TestAbstractRestController;
import com.i2g.rms.rest.model.UserDetailsRO;
import com.i2g.rms.rest.service.UserDetailsRestService;

/**
 * Rest controller for User Details table read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class UserDetailsController extends TestAbstractRestController {

	@Autowired
	private UserDetailsRestService _userDetailsRestService;

	@RequestMapping(value = RequestMappingConstants.GET_ALL_USER_DETAILS, method = RequestMethod.GET)
	public List<UserDetailsRO> getUserDetails() {
		return _userDetailsRestService.getUserDetails();
	}
}
