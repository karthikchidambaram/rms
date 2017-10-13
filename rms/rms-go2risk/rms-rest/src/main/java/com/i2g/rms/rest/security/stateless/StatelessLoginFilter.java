package com.i2g.rms.rest.security.stateless;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.rest.constants.RequestConstants;
import com.i2g.rms.rest.service.PasswordRelatedRestService;
import com.i2g.rms.util.security.RMSSecurityProperties;

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
		
		if (response.getHeader("Access-Control-Allow-Origin") == null || response.getHeader("Access-Control-Allow-Origin").isEmpty()) {
			// Authorize (allow) all domains to consume the content
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods", "API, UPDATE, GET, OPTIONS, HEAD, PUT, POST, DELETE, PATCH");
			response.addHeader("Access-Control-Allow-Headers", "Authorization, Search, Accept, Origin, X-AUTH-TOKEN, X-Requested-With, Content-Type, X-Codingpedia, location, info");
			response.addHeader("Access-Control-Expose-Headers", "Authorization, Search, Accept, Origin, X-AUTH-TOKEN, X-Requested-With, Content-Type, X-Codingpedia, location, info");
			response.addHeader("Access-Control-Allow-Credentials", "true");
			response.addHeader("Access-Control-Max-Age", RMSSecurityProperties.ONE_DAY_IN_SECONDS);
		}
		
		if (!HttpMethod.OPTIONS.name().equals(request.getMethod())) {
			// Parse the credentials from the request
			final String[] credentials = passwordRelatedRestService.getCredentialsFromRequest(request);
			final String username = credentials[0];
			final String password = credentials[1];
			UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(username, password);
			return getAuthenticationManager().authenticate(loginToken);
		} else {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			UserAuthentication userAuthentication = new UserAuthentication(new User(RequestConstants.ANONYMOUS_USER));
			return userAuthentication;
		}		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		
		if (response.getHeader("Access-Control-Allow-Origin") == null || response.getHeader("Access-Control-Allow-Origin").isEmpty()) {
			// Authorize (allow) all domains to consume the content
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods", "API, UPDATE, GET, OPTIONS, HEAD, PUT, POST, DELETE, PATCH");
			response.addHeader("Access-Control-Allow-Headers", "Authorization, Search, Accept, Origin, X-AUTH-TOKEN, X-Requested-With, Content-Type, X-Codingpedia, location, info");
			response.addHeader("Access-Control-Expose-Headers", "Authorization, Search, Accept, Origin, X-AUTH-TOKEN, X-Requested-With, Content-Type, X-Codingpedia, location, info");
			response.addHeader("Access-Control-Allow-Credentials", "true");
			response.addHeader("Access-Control-Max-Age", RMSSecurityProperties.ONE_DAY_IN_SECONDS);
		}
				
		if (!HttpMethod.OPTIONS.name().equals(request.getMethod())) {			
			// Lookup the complete User object from the database and create an
			// Authentication for it
			final User authenticatedUser = userDetailsService.loadUserByUsername(authentication.getName());
			final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);	
			// Add the custom token as HTTP header to the response
			tokenAuthenticationService.addAuthentication(response, userAuthentication);	
			// Add the authentication to the Security context
			SecurityContextHolder.getContext().setAuthentication(userAuthentication);
		} else {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}
		chain.doFilter(request, response);
	}
}
