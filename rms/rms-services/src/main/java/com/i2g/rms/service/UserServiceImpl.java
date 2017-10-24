package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.Address;
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
	public User getUserByUserLoginId(final String loginId) {
		return _userDao.getUserByUserLoginId(loginId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getSubordinates(final User manager) {
		return _userDao.getSubordinates(manager);
	}

	@Override
	public List<User> get() {
		return _userDao.get();
	}

	@Override
	public User get(final long id) {
		return _userDao.get(id);
	}

	@Override
	public User updateUser(final User user) {
		return _userDao.updateUser(user);
	}

	@Override
	public void deleteAddresses(final User user, final Set<Address> addresses) {		
		_userDao.deleteAddresses(user, addresses);
	}
}
