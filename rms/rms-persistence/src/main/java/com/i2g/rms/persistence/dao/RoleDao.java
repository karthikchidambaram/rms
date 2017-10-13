package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.Permission;
import com.i2g.rms.domain.model.Role;

/**
 * Back-end DAO for role related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface RoleDao {
	
	public List<Role> getRoles();
	
	public Role getRoleById(final long id);
	
	public Role getRoleByRoleName(final String roleName);
	
	public void deleteRole(final Role role);
	
	public void deleteRoles(final List<Role> roles);
	
	public void deletePermissionFromRole(final Role role, final Permission permission);
	
	public void deletePermissionsFromRole(final Role role, final List<Permission> permissions);
	
}
