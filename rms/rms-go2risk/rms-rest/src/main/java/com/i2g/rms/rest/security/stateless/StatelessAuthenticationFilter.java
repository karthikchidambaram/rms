package com.i2g.rms.rest.security.stateless;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class StatelessAuthenticationFilter extends GenericFilterBean {
	
	private final Logger _logger = LoggerFactory.getLogger(StatelessAuthenticationFilter.class);

	private final TokenAuthenticationService tokenAuthenticationService;

	protected StatelessAuthenticationFilter(TokenAuthenticationService taService) {
		this.tokenAuthenticationService = taService;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		_logger.info("************ Inside StatelessAuthenticationFilter ***************");
		
		final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		final HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		
		// Authorize (allow) all domains to consume the content
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods", "API, UPDATE, GET, OPTIONS, HEAD, PUT, POST, DELETE");
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");

		if (!HttpMethod.OPTIONS.name().equals(httpRequest.getMethod())) {			
			_logger.info("************ Inside NOT Options Method ***************");
			SecurityContextHolder.getContext().setAuthentication(tokenAuthenticationService.getAuthentication(httpRequest));			
		} else {
			// For HTTP OPTIONS verb/method reply with ACCEPTED status code per CORS handshake
			((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}
		chain.doFilter(servletRequest, servletResponse); // always continue
	}
}