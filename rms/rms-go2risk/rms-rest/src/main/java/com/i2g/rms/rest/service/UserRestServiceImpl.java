package com.i2g.rms.rest.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.service.UserService;

/**
 * Rest services for user rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class UserRestServiceImpl extends AbstractRestService implements UserRestService {
	
	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(UserRestServiceImpl.class);
	
	@Autowired
	private UserService _userService;
	
	@Override
	@Transactional(readOnly = true)
	public List<UserRO> getUsers() {
		List<User> users = _userService.getUsers();
		List<UserRO> userROs = (users == null || users.isEmpty()) ? Collections.emptyList() : _mapperService.map(users, UserRO.class);
		return userROs;
	}

	@Override
	@Transactional(readOnly = true)
	public UserRO getUserByUserLoginId(final String loginId) {
		validateUsername(loginId);
		final User user = _userService.getUserByUserLoginId(loginId);
		validateGenericObject(user);
		return _mapperService.map(user, UserRO.class);
	}
}
