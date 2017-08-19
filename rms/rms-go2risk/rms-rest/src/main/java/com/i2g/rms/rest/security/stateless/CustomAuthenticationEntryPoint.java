package com.i2g.rms.rest.security.stateless;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.i2g.rms.util.security.RMSSecurityProperties;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private final Logger _logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {		
		_logger.info("Inside CustomAuthenticationEntryPoint.commence()");		
		response.setContentType(RMSSecurityProperties.APPLICATION_RESPONSE_TYPE);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		PrintWriter out = response.getWriter();
		out.print(RMSSecurityProperties.USER_NOT_AUTHENTICATED_MSG);
		out.flush();
		out.close();
	}
}