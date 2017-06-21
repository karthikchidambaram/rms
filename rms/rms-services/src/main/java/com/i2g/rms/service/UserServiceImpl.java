package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.persistence.dao.UserDao;

/**
 * Back-end service for user related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {

	@Autowired
	private UserDao _userDao;

	private UserServiceImpl() {
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getUsers() {
		return _userDao.getUsers();
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserByUserLoginId(String loginId) {
		return _userDao.getUserByUserLoginId(loginId);
	}
}
