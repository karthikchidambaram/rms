package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.User;

/**
 * Back-end DAO for user related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface UserDao {

	public List<User> getUsers();
	
	public User getUserByUserLoginId(final String loginId);
	
}
