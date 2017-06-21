package com.i2g.rms.rest.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Rest service interface for all password related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface PasswordRelatedRestService {

	public String[] getCredentialsFromRequest(final HttpServletRequest request);

	public String getRMSSaltedPassword(final String password);

	public String getRMSHashedPassword(final String saltedPassword);
	
	public String getRMSBCryptPassword(final String password);

}
