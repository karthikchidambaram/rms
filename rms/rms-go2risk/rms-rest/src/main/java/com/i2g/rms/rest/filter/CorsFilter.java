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

import com.i2g.rms.util.security.RMSSecurityProperties;

/**
 * {@link Filter} implementation to provide CORS (Cross-Origin Resource Sharing)
 * which relaxes same origin policy restrictions allowing cross-domain
 * JavaScript XMLHttpRequests. This is required for supporting a modern REST API
 * in which the client browser sends pre-flight OPTIONS requests to determine
 * which actions are allowed.
 * 
 * <p>
 * The following headers are set for each response:
 * <ul>
 * <li>{@code Access-Control-Allow-Origin} - set to the {@code Origin} value in
 * the HTTP request
 * <li>{@code Access-Control-Allow-Methods} - set to a hard-coded list of the
 * supported HTTP methods: POST, PUT, GET, OPTIONS, DELETE, PATCH
 * <li>{@code Access-Control-Max-Age} - set to one hour
 * <li>{@code Access-Control-Allow-Headers} - set to a hard-coded list of the
 * following headers: ACCEPT, CONTENT-TYPE
 * <li>{@code Access-Control-Allow-Credentials} - set to "true" to enable the
 * client to end authentication cookies with requests (e.g. OAM SSO)
 * </ul>
 * </p>
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 */
public class CorsFilter implements Filter {
	
	/** {@code Logger} instance. */
	private final static Logger _logger = LoggerFactory.getLogger(CorsFilter.class);

	@Override
	public void init(final FilterConfig fc) throws ServletException {
	}

	@Override
	public void destroy() {
	}
	
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		// Retrieve the HTTP request and log details at debug level for support
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		_logger.debug("Request URI: {} Method: {} origin: {}", httpRequest.getRequestURI(), httpRequest.getMethod(), httpRequest.getHeader("Origin"));
		
		// For OPTIONS requests, set the CORS response headers
		// During production implementation the following line will be uncommented.
		// String clientOrigin = httpRequest.getHeader("Origin");
		// To enable testing, currently setting the value as "*" to allow all origins. 
		String clientOrigin = "*";
		
		if(clientOrigin != null && !"null".equals(clientOrigin)) {
			final HttpServletResponse modifiedResponse = (HttpServletResponse) response;
			modifiedResponse.setHeader("Access-Control-Allow-Origin", clientOrigin);
			modifiedResponse.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE, PATCH, API, UPDATE, HEAD");
			modifiedResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Search, PageResult, Accept, Origin, X-AUTH-TOKEN, X-Requested-With, Content-Type, X-Codingpedia, location, info");
			modifiedResponse.setHeader("Access-Control-Expose-Headers", "Authorization, Search, PageResult, Accept, Origin, X-AUTH-TOKEN, X-Requested-With, Content-Type, X-Codingpedia, location, info");
			modifiedResponse.setHeader("Access-Control-Allow-Credentials", "true");
			modifiedResponse.setHeader("Access-Control-Max-Age", RMSSecurityProperties.ONE_DAY_IN_SECONDS);
		}
		
		// Pass request/response down chain for processing
		chain.doFilter(request, response);
	}
}
