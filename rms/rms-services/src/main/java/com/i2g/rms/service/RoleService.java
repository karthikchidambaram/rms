package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.Role;

/**
 * Back-end service for role related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface RoleService {
	
	public List<Role> getRoles();
	
}
