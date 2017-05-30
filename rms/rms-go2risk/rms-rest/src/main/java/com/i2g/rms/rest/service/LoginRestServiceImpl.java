package com.i2g.rms.rest.service;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import com.i2g.rms.service.LoginService;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.util.security.RMSPasswordHashingService;

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
	private LoginService _loginService;	
	@Autowired
	private RMSPasswordHashingService _rMSPasswordHashingService;
	
	public String getEncryptedPassword(final HttpServletRequest request, final HttpServletResponse response) {
		// Check if the request is valid
		if (request == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.REQUEST_OBJECT_NULL_OR_EMPTY));
		}
		
		// Parse the credentials from the request
		final String[] credentials = getCredentials(request);
		if (credentials == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.CREDENTIALS_NOT_PRESENT_IN_REQUEST));
		}
		
		final String username = credentials[0];
		final String password = credentials[1];
		//validate input parameters
		validateUsername(username);
		validatePassword(password);
		
		//Get the encrypted password from service
		final String saltedPassword = _rMSPasswordHashingService.getSaltedPassword(password);
		final String hashedPassword = _rMSPasswordHashingService.getHashedPassword(saltedPassword);
		
		return hashedPassword;
	}
	
	/**
	 * Returns the username/password from the specified {@code request}.  The 
	 * method first attempts to pull credentials from a standard Basic
	 * Authentication header, then falls back to simple header fields.
	 * 
	 * @param request (non-null)
	 * @return array of username and password
	 */
	private String[] getCredentials(final HttpServletRequest request) {
		// First attempt to pull out Basic Authentication credentials
		final String basicAuthHeader = request.getHeader("Authorization");
		if( basicAuthHeader != null && basicAuthHeader.startsWith("Basic ") ) {
			return getBasicAuthHeader(basicAuthHeader);
		}
		
		// Otherwise fall back to explicit username/password headers
		final String username = request.getHeader("username");
		final String password = request.getHeader("password");
		return new String[]{username, password};
	}
	
	/**
	 * Decodes the specified Basic Authentication {@code header} into a username 
	 * and password.
	 * 
	 * @return username/password or {@code null} if not specified
	 * @throws BadCredentialsException if the Basic header is invalid
	 */
	private String[] getBasicAuthHeader(final String header) {
		// Base64 decode the username/password portion of header
		final byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
		final byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		} catch( IllegalArgumentException e ) {
			throw new BadCredentialsException("Failed to decode basic authentication token", e);
		}
		
		// Parse the username/password from the decoded token
		final String token = new String(decoded, StandardCharsets.UTF_8);
		final int delim = token.indexOf(":");
		if( delim == -1 ) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[]{token.substring(0, delim), token.substring(delim + 1)};
	}
	
	private void validateUsername(final String username) {
		if (username == null || username.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.USER_LOGIN_ID_NULL_OR_EMPTY));
		}
	}
	
	private void validatePassword(final String password) {
		if (password == null || password.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.PASSWORD_NULL_OR_EMPTY));
		}
	}
}
