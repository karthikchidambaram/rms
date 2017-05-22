package com.i2g.rms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.test.TestAbstractRestController;
import com.i2g.rms.rest.model.PermissionRO;
import com.i2g.rms.rest.service.PermissionRestService;

/**
 * Rest controller for Permissions read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class PermissionController extends TestAbstractRestController {
	@Autowired
	private PermissionRestService _permissionRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ALL_PERMISSIONS, method = RequestMethod.GET)
	public List<PermissionRO> getPermissions() {
		return _permissionRestService.getPermissions();
	}
}
