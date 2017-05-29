package com.i2g.rms.service.auditor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.i2g.rms.domain.support.Auditor;

/**
 * Implementation of JEE {@link Filter} for setting the username in the domain
 * layer's {@link Auditor} to allow auditing of data store updates.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class AuditorFilter implements Filter {

	private final Logger _logger = LoggerFactory.getLogger(AuditorFilter.class);

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		// OPTIONS requests (pre-flight for RESTful calls) do not require an
		// authenticated user; only if it's not an OPTIONS request do we need
		// to verify a valid user exists in context and set it accordingly
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (!HttpMethod.OPTIONS.name().equals(httpRequest.getMethod())) {
			final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth.getPrincipal() instanceof UserDetails)) {
				throw new IllegalStateException("AuditorFilter: Security configuration error");
			}

			// Set username in auditing contexts
			final UserDetails user = (UserDetails) auth.getPrincipal();
			Auditor.setName(user.getUsername());
			_logger.info("Auditor name has been set: " + Auditor.getName());
		}

		try {
			chain.doFilter(request, response);
		} finally {
			// Always clear user from auditing context
			Auditor.clear();
		}
	}

}
