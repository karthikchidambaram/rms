package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.Permission;

/**
 * Back-end service for permissions related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface PermissionService {
	
	public List<Permission> getPermissions();
	
	public Permission getPermissionById(final long id);
	
	public Permission getPermissionByPermissionName(final String permissionName);
	
}
