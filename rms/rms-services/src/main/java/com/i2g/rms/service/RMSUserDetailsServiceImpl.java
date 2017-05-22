package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.UserDetails;
import com.i2g.rms.persistence.dao.UserDetailsDao;

/**
 * Back-end service for user details related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class RMSUserDetailsServiceImpl extends AbstractService implements RMSUserDetailsService {

	@Autowired
	private UserDetailsDao _userDetailsDao;

	private RMSUserDetailsServiceImpl() {
	}

	@Override
	public List<UserDetails> getUserDetails() {
		return _userDetailsDao.getUserDetails();
	}
}
