package com.i2g.rms.rest.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.UserDetails;
import com.i2g.rms.rest.model.UserDetailsRO;
import com.i2g.rms.service.RMSUserDetailsService;

/**
 * Rest services for user details rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class UserDetailsRestServiceImpl extends AbstractRestService implements UserDetailsRestService {
	
	private final Logger _logger = LoggerFactory.getLogger(UserDetailsRestServiceImpl.class);
	
	@Autowired
	private RMSUserDetailsService _rMSUserDetailsService;
	
	@Transactional(readOnly = true)
	public List<UserDetailsRO> getUserDetails() {
		List<UserDetails> userDetails = _rMSUserDetailsService.getUserDetails();
		List<UserDetailsRO> userDetailROs = userDetails.isEmpty() ? Collections.emptyList() : _mapperService.map(userDetails, UserDetailsRO.class);
		return userDetailROs;
	}
}
