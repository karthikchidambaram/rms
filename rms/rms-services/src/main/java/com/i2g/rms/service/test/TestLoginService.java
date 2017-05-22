package com.i2g.rms.service.test;

import com.i2g.rms.domain.model.User;

/**
 * Service interface for all group related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TestLoginService {
	
	public User doLogin(final String loginId, final String password);
	
}
