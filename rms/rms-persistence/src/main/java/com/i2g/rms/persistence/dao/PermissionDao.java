package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.Permission;

/**
 * Back-end DAO for permissions related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface PermissionDao {
	
	public List<Permission> getPermissions();
	
}
