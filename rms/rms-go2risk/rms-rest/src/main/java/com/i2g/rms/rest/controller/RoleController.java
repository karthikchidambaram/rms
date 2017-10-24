package com.i2g.rms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.Role;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.RoleRO;
import com.i2g.rms.rest.search.Searchable;
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
	@Searchable(sourceType = RoleRO.class, value = Role.class)
	public List<RoleRO> getRoles() {
		return _roleRestService.getRoles();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ROLE_BY_ROLE_ID, method = RequestMethod.GET)
	public RoleRO getRoleById(@PathVariable final long id) {
		return _roleRestService.getRoleById(id);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ROLE_BY_ROLE_NAME, method = RequestMethod.GET)
	public RoleRO getRoleByRoleName(@PathVariable final String roleName) {
		return _roleRestService.getRoleByRoleName(roleName);
	}
	
	@RequestMapping(value = RequestMappingConstants.DELETE_PERMISSION_FROM_ROLE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePermissionFromRole(@PathVariable final String roleName, @PathVariable final String permissionName) {
		_roleRestService.deletePermissionFromRole(roleName, permissionName);
	}
}
