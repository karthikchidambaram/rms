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

	public String getRMSEncryptedPasswordTest(final HttpServletRequest request, final HttpServletResponse response);

	public String getRMSBCryptPasswordTest(final HttpServletRequest request, final HttpServletResponse response);
}
