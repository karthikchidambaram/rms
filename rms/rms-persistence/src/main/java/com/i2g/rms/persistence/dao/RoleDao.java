package com.i2g.rms.persistence.dao;

import java.util.List;

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
	
}
