package com.i2g.rms.rest.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.rest.model.UserContextRO;
import com.i2g.rms.rest.model.UserRO;

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
	
	@Override
	public UserContextRO doLogin() {
		UserContextRO userContextRO = new UserContextRO();
		User user = (User) getUserDetailsFromContext();
		if (user != null) {			
			UserRO userRO = _mapperService.map(user, UserRO.class);
			userContextRO.setFirstName(userRO.getFirstName());
			userContextRO.setLastName(userRO.getLastName());
			userContextRO.setUserId(userRO.getLoginId());
			userContextRO.setRoles(getRolesOfAuthenticatedUser(userRO.getRoles()));
		}		
		return userContextRO;
	}
}
