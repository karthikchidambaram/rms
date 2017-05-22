package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.UserDetails;

/**
 * Back-end DAO for user details related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface UserDetailsDao {

	public List<UserDetails> getUserDetails();

}
