package com.i2g.rms.rest.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.i2g.rms.rest.service.test.TestLoginRestService;

/**
 * Implementation of {@link OncePerRequestFilter} for intercepting all HTTP 
 * requests to perform Basic Authentication against database.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Service
public class BasicAuthFilter extends OncePerRequestFilter {
	
	/** {@code Logger} instance. */
	private final Logger _logger = LoggerFactory.getLogger(BasicAuthFilter.class);
	
	@Autowired
	TestLoginRestService _loginRestService;
	
	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
		_logger.info("Applying HTTP Basic Authentication Filter on servlet request");
		
		try {
			// Parse the credentials from the request
			final String[] credentials = getCredentials(request);			
			_loginRestService.doLogin(credentials[0], credentials[1], request, response);			
		} catch( RuntimeException e ) {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "Authentication failed.");
			return;
		}
		_logger.info("BasicAuthFilter: Authentication successful.");
		filterChain.doFilter(request, response);
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
}
