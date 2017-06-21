package com.i2g.rms.rest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Rest services for login related activities.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class LoginRestServiceImpl extends AbstractRestService implements LoginRestService {

	private final Logger _logger = LoggerFactory.getLogger(LoginRestServiceImpl.class);

	@Autowired
	private PasswordRelatedRestService _passwordRelatedRestService;

	public String getRMSEncryptedPasswordTest(final HttpServletRequest request, final HttpServletResponse response) {
		// Check if the request is valid
		validateRequest(request);

		// Parse the credentials from the request
		final String[] credentials = _passwordRelatedRestService.getCredentialsFromRequest(request);
		validateCredentials(credentials);

		final String username = credentials[0];
		final String password = credentials[1];

		// Validate input parameters
		validateUsername(username);
		validatePassword(password);

		// Get the encrypted password from service
		final String saltedPassword = _passwordRelatedRestService.getRMSSaltedPassword(password);
		final String hashedPassword = _passwordRelatedRestService.getRMSHashedPassword(saltedPassword);

		return hashedPassword;
	}

	@Override
	public String getRMSBCryptPasswordTest(HttpServletRequest request, HttpServletResponse response) {
		// Check if the request is valid
		validateRequest(request);

		// Parse the credentials from the request
		final String[] credentials = _passwordRelatedRestService.getCredentialsFromRequest(request);
		validateCredentials(credentials);

		final String username = credentials[0];
		final String password = credentials[1];

		// Validate input parameters
		validateUsername(username);
		validatePassword(password);

		final String encryptedPassword = _passwordRelatedRestService.getRMSBCryptPassword(password);

		return encryptedPassword;
	}
}
