package com.i2g.rms.rest.service.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.rest.model.UserContextRO;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.util.security.RMSSecurityProperties;

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
	public UserContextRO doLogin(final HttpServletRequest request, final HttpServletResponse response) {
		UserContextRO userContextRO = new UserContextRO();
		User user = (User) getUserDetailsFromContext();
		String token = response.getHeader(RMSSecurityProperties.AUTH_HEADER_NAME);
		
		if (user != null) {			
			UserRO userRO = _mapperService.map(user, UserRO.class);
			userContextRO.setFirstName(userRO.getFirstName());
			userContextRO.setLastName(userRO.getLastName());
			userContextRO.setUserId(userRO.getLoginId());
			userContextRO.setRoles(getRolesOfAuthenticatedUser(userRO.getRoles()));
			if (token != null && !token.isEmpty()) {
				userContextRO.setXAuthToken(token);
			}
		}
		return userContextRO;
	}
}
