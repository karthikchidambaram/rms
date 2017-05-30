package com.i2g.rms.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.persistence.dao.test.LoginDao;

/**
 * Back-end service for group related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class TestLoginServiceImpl extends TestAbstractService implements TestLoginService {

	@Autowired
	private LoginDao _loginDao;

	private TestLoginServiceImpl() {
	}

	@Override
	public User doLogin(final String loginId, final String password) {
		return _loginDao.doLogin(loginId, password);
	}
}
