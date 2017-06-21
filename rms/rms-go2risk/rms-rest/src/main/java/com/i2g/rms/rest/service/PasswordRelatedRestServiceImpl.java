package com.i2g.rms.rest.service;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import com.i2g.rms.util.security.RMSBCryptPasswordEncoderService;
import com.i2g.rms.util.security.RMSPasswordHashingService;

/**
 * Back-end rest service for all password related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class PasswordRelatedRestServiceImpl extends AbstractRestService implements PasswordRelatedRestService {
	
	private final Logger _logger = LoggerFactory.getLogger(PasswordRelatedRestServiceImpl.class);
	
	@Autowired
	private RMSPasswordHashingService _rMSPasswordHashingService;
	@Autowired
	private RMSBCryptPasswordEncoderService _rMSBCryptPasswordEncoderService;
	
	
	private PasswordRelatedRestServiceImpl() {
	}

	/**
	 * Returns the username/password from the specified {@code request}.  The 
	 * method first attempts to pull credentials from a standard Basic
	 * Authentication header, then falls back to simple header fields.
	 * 
	 * @param request (non-null)
	 * @return array of username and password
	 */
	@Override
	public String[] getCredentialsFromRequest(final HttpServletRequest request) {
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
			throw new BadCredentialsException("Failed to decode basic authentication token.", e);
		}
		
		// Parse the username/password from the decoded token
		final String token = new String(decoded, StandardCharsets.UTF_8);
		final int delim = token.indexOf(":");
		if( delim == -1 ) {
			throw new BadCredentialsException("Invalid basic authentication token.");
		}
		return new String[]{token.substring(0, delim), token.substring(delim + 1)};
	}

	@Override
	public String getRMSSaltedPassword(String password) {
		return _rMSPasswordHashingService.getSaltedPassword(password);
	}

	@Override
	public String getRMSHashedPassword(String saltedPassword) {
		return _rMSPasswordHashingService.getHashedPassword(saltedPassword);
	}

	@Override
	public String getRMSBCryptPassword(String password) {
		return _rMSBCryptPasswordEncoderService.getBCryptPassword(password);
	}	
}
