package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.UserDetailsRO;

/**
 * Rest Service Interface for user details rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface UserDetailsRestService {
	public List<UserDetailsRO> getUserDetails();
}
