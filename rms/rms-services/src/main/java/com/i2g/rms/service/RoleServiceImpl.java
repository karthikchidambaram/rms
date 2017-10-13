package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Permission;
import com.i2g.rms.domain.model.Role;
import com.i2g.rms.persistence.dao.RoleDao;

/**
 * Back-end service for role related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class RoleServiceImpl extends AbstractService implements RoleService {

	@Autowired
	private RoleDao _roleDao;

	private RoleServiceImpl() {
	}

	@Override
	public List<Role> getRoles() {
		return _roleDao.getRoles();
	}

	@Override
	public Role getRoleById(final long id) {
		return _roleDao.getRoleById(id);
	}

	@Override
	public void deletePermissionFromRole(final Role role, final Permission permission) {
		_roleDao.deletePermissionFromRole(role, permission);		
	}

	@Override
	public void deletePermissionsFromRole(final Role role, final List<Permission> permissions) {
		_roleDao.deletePermissionsFromRole(role, permissions);		
	}

	@Override
	public Role getRoleByRoleName(final String roleName) {
		return _roleDao.getRoleByRoleName(roleName);
	}
}
