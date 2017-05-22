package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.RoleRO;

/**
 * Rest Service Interface for role rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface RoleRestService {
	
	public List<RoleRO> getRoles();
	
}
