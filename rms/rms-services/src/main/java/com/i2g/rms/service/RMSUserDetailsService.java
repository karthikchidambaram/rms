package com.i2g.rms.service;

import java.util.List;

/**
 * Back-end service for user details related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
import com.i2g.rms.domain.model.UserDetails;

public interface RMSUserDetailsService {
	
	public List<UserDetails> getUserDetails();
	
}
