package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.UserRO;

/**
 * Rest Service Interface for user rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface UserRestService {
	public List<UserRO> getUsers();
}
