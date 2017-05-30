package com.i2g.rms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.RoleRO;
import com.i2g.rms.rest.service.RoleRestService;

/**
 * Rest controller for Role read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class RoleController extends AbstractRestController {
	@Autowired
	private RoleRestService _roleRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ALL_ROLES, method = RequestMethod.GET)
	public List<RoleRO> getRoles() {
		return _roleRestService.getRoles();
	}
}
