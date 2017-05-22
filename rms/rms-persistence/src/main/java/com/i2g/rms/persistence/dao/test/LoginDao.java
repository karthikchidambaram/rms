package com.i2g.rms.persistence.dao.test;

import com.i2g.rms.domain.model.User;

/**
 * Back-end DAO for group related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface LoginDao {

	public User doLogin(final String loginId, final String password);

}
