package com.i2g.rms.rest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Rest Service Interface for login related activities.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface LoginRestService {
	
	public String getEncryptedPassword(final HttpServletRequest request, final HttpServletResponse response);
	
}
