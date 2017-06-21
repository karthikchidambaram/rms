package com.i2g.rms.rest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.i2g.rms.rest.service.PasswordRelatedRestService;
import com.i2g.rms.rest.service.test.TestLoginRestService;

/**
 * Implementation of {@link OncePerRequestFilter} for intercepting all HTTP
 * requests to perform Basic Authentication against database.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
// TODO This is not being used, need to remove at last.
@Service
public class BasicAuthFilter extends OncePerRequestFilter {

	/** {@code Logger} instance. */
	private final Logger _logger = LoggerFactory.getLogger(BasicAuthFilter.class);

	@Autowired
	TestLoginRestService _loginRestService;
	@Autowired
	private PasswordRelatedRestService _passwordRelatedRestService;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws ServletException, IOException {
		_logger.info("Applying HTTP Basic Authentication Filter on servlet request");

		try {
			// Parse the credentials from the request
			final String[] credentials = _passwordRelatedRestService.getCredentialsFromRequest(request);
			_loginRestService.doLogin(credentials[0], credentials[1], request, response);
		} catch (RuntimeException e) {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "Authentication failed.");
			return;
		}
		_logger.info("BasicAuthFilter: Authentication successful.");
		// Always continue
		filterChain.doFilter(request, response);
	}
}
