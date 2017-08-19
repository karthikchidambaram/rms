package com.i2g.rms.rest.security.stateless;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.rest.service.PasswordRelatedRestService;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final Logger _logger = LoggerFactory.getLogger(StatelessLoginFilter.class);

	private final TokenAuthenticationService tokenAuthenticationService;
	private final SpringSecurityUserDetailsServiceImpl userDetailsService;
	private final PasswordRelatedRestService passwordRelatedRestService;

	protected StatelessLoginFilter(final String urlMapping, final TokenAuthenticationService tokenAuthenticationService,
			final SpringSecurityUserDetailsServiceImpl userDetailsService,
			final PasswordRelatedRestService passwordRelatedRestService, final AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(urlMapping));
		this.userDetailsService = userDetailsService;
		this.tokenAuthenticationService = tokenAuthenticationService;
		this.passwordRelatedRestService = passwordRelatedRestService;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		_logger.info("StatelessLoginFilter.attemptAuthentication");
		
		// Parse the credentials from the request
		final String[] credentials = passwordRelatedRestService.getCredentialsFromRequest(request);
		final String username = credentials[0];
		final String password = credentials[1];
		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(username, password);

		return getAuthenticationManager().authenticate(loginToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		_logger.info("StatelessLoginFilter.successfulAuthentication");
		// Lookup the complete User object from the database and create an
		// Authentication for it
		final User authenticatedUser = userDetailsService.loadUserByUsername(authentication.getName());
		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

		// Add the custom token as HTTP header to the response
		tokenAuthenticationService.addAuthentication(response, userAuthentication);

		// Add the authentication to the Security context
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
		chain.doFilter(request, response);
	}
}
