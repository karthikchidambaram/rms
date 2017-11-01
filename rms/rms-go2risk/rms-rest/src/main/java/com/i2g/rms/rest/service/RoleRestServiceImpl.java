package com.i2g.rms.rest.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.Permission;
import com.i2g.rms.domain.model.Role;
import com.i2g.rms.rest.model.RoleRO;
import com.i2g.rms.service.PermissionService;
import com.i2g.rms.service.RoleService;
import com.i2g.rms.service.exception.ResourceNotFoundException;

/**
 * Rest services for role rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class RoleRestServiceImpl extends AbstractRestService implements RoleRestService {
	
	private final Logger _logger = LoggerFactory.getLogger(RoleRestServiceImpl.class);
	
	@Autowired
	private RoleService _roleService;
	@Autowired
	private PermissionService _permissionService;
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<RoleRO> getRoles() {
		List<Role> roles = _roleService.getRoles();
		List<RoleRO> roleROs = (roles == null || roles.isEmpty()) ? Collections.emptyList() : _mapperService.map(roles, RoleRO.class);
		return roleROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public RoleRO getRoleById(final long id) {
		if (id > 0) {
			final Role role = _roleService.getRoleById(id);
			validateGenericObject(role);
			return _mapperService.map(_roleService.getRoleById(id), RoleRO.class);
		} else {
			return null;
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public RoleRO getRoleByRoleName(final String roleName) {
		validateStringObject(roleName);
		final Role role = _roleService.getRoleByRoleName(roleName.trim());
		if (role != null) {
			return _mapperService.map(role, RoleRO.class);
		} else {
			return null;
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public void deletePermissionFromRole(final String roleName, final String permissionName) {
		validateStringObject(roleName);
		validateStringObject(permissionName);
		final Role role = _roleService.getRoleByRoleName(roleName.trim());
		final Permission permission = _permissionService.getPermissionByPermissionName(permissionName);
		if (role != null && permission != null) {
			_roleService.deletePermissionFromRole(role, permission);
		} else {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.DELETE_OPERATION_FAILED));
		}
	}
	
}
