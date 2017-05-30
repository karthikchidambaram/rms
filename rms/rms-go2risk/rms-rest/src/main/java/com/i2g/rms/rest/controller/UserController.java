package com.i2g.rms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.service.UserRestService;

/**
 * Rest controller for user read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class UserController extends AbstractRestController {

	@Autowired
	private UserRestService _userRestService;

	@RequestMapping(value = RequestMappingConstants.GET_ALL_USERS, method = RequestMethod.GET)
	public List<UserRO> getUsers() {
		return _userRestService.getUsers();
	}
}
