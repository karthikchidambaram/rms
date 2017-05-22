package com.i2g.rms.rest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.i2g.rms.rest.controller.test.TestAbstractRestController;
import com.i2g.rms.util.security.RMSSecurityProperties;

public class SessionValidator extends HandlerInterceptorAdapter {
	
	/** {@code Logger} instance. */
	private final Logger _logger = LoggerFactory.getLogger(SessionValidator.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		_logger.info("Inside session validator..");
		HttpSession session = request.getSession();
		
		if (!(((HandlerMethod) handler).getBean() instanceof TestAbstractRestController)) {
			if (session == null || session.getAttribute(RMSSecurityProperties.RMS_USER_CONTEXT) == null) {
				_logger.info("SessionValidator: Invalid session! Please login to proceed.");
				throw new Exception("Invalid session! Please login to proceed.");
			}
		}
		_logger.info("Test controllers, session validation not required.");
		return true;
	}
}
