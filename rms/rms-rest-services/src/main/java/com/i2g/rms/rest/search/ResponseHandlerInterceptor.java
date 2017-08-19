package com.i2g.rms.rest.search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Implementation of Spring's {@link HandlerInteceptor} for setting and clearing
 * the {@link HttpServletResponse} in the {@link ResponseContextHolder} to allow
 * for static thread-local access to the response.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class ResponseHandlerInterceptor implements HandlerInterceptor {
	
	/** {@code Logger} instance. */
	private final Logger _logger = LoggerFactory.getLogger(ResponseHandlerInterceptor.class);
	
	
	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
		// Set the response in the context holder
		ResponseContextHolder.setResponse(response);
		_logger.info("Response Handler Interceptor: Set HttpServletResponse in thread local context.");
		return true;
	}
	
	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
		// Do nothing
	}
	
	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception error) throws Exception {
		// Always clear the thread-local context to prevent carry over
		ResponseContextHolder.clear();
		_logger.trace("Cleared HttpServletResponse from thread local context");
	}
	
}
