package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.User;

/**
 * Service interface for all user related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface UserService {
	
	public List<User> getUsers();
	
	public User getUserByUserLoginId(final String loginId);
	
}
