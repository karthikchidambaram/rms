package com.i2g.rms.rest.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.i2g.rms.service.security.stateless.TokenAuthenticationService;

/**
 * Rest services for statless authentication approach.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class TestStatelessAuthenticationRestServiceImpl extends TestAbstractRestService
		implements TestStatelessAuthenticationRestService {
	
	private final Logger _logger = LoggerFactory.getLogger(TestStatelessAuthenticationRestServiceImpl.class);
	@Autowired
	private TokenAuthenticationService _tokenAuthenticationService;
	@Autowired
	private UserDetailsService _userDetailsService;
	
	@Override
	public String checkLoginStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}
