package com.i2g.rms.rest.service.test;

import com.i2g.rms.rest.model.UserContextRO;

/**
 * Rest Service Interface for stateless and JWT based login approach.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TestStatelessAuthenticationRestService {

	public UserContextRO doLogin();
	
}
