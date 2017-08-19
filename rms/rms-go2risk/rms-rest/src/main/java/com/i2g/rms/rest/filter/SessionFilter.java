package com.i2g.rms.rest.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.rest.security.stateless.UserAuthentication;

/**
 * Implementation of JEE {@link Filter} for checking if the user is
 * authenticated before accessing any secured resources.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class SessionFilter implements Filter {

	private final Logger _logger = LoggerFactory.getLogger(SessionFilter.class);

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		_logger.info("*************** Inside Session Filter ****************");
		// OPTIONS requests (pre-flight for RESTful calls) do not require an
		// authenticated user; only if it's not an OPTIONS request do we need
		// to verify a valid user exists in context.
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		if (!HttpMethod.OPTIONS.name().equals(httpRequest.getMethod())) {
			_logger.info("*************** Inside Session Filter: Under Not OPTIONS method ****************");
			boolean authenticated = false;
			final Authentication auth = SecurityContextHolder.getContext().getAuthentication();			
			
			if (auth != null) {				
				if (auth instanceof UserAuthentication) {					
					final User user = (User) auth.getDetails();
					if (user != null) {
						if ((user.getUsername() != null) && !(user.getUsername().isEmpty()) && !(user.getUsername().equalsIgnoreCase("anonymousUser"))) {
							// User is authenticated
							authenticated = true;
						}
					}
				} else {
					if (auth.getPrincipal() instanceof User) {
						final User user = (User) auth.getPrincipal();
						if (user != null) {
							if ((user.getUsername() != null) && !(user.getUsername().isEmpty()) && !(user.getUsername().equalsIgnoreCase("anonymousUser"))) {
								// User is authenticated
								authenticated = true;
							}
						}
					}
				}
			}
			
			if (!authenticated) {
				_logger.error("Exception at SessionFilter: Unauthorized user tried to access secured resource.");
				throw new AccessDeniedException("SessionFilter: Full authentication is required to access this resource.");
			}
		}
		// Always continue
		chain.doFilter(request, response);
	}
}
