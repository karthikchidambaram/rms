package com.i2g.rms.rest.search;

import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

/**
 * Context holder for enabling static access to the {@link HttpServletResponse}
 * bound to the current thread.  The context is only populated for web requests
 * processed through the Spring DispatchServlet assuming the associated
 * {@link ResponseHandlerInterceptor} is registered in the Spring handler stack.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public final class ResponseContextHolder {
	
	/** ThreadLocal for storing the servlet response with the thread. */
	private static final ThreadLocal<HttpServletResponse> RESPONSE_HOLDER = new ThreadLocal<>();
	
	
	/**
	 * Private constructor to prevent instantiation of static class.
	 */
	private ResponseContextHolder() { }
	
	/**
	 * Returns the {@link HttpServletResponse} bound to the current thread or
	 * throws an {@link IllegalStateException} if no response is bound.  Callers
	 * to this method should ensure they are invoked from within a web context.
	 * 
	 * @return http servlet response bound to current thread
	 */
	public static HttpServletResponse getResponse() {
		final HttpServletResponse response = RESPONSE_HOLDER.get();
		if( response == null ) {
			throw new IllegalStateException("HttpServletResponse not set in context");
		}
		return response;
	}
	
	/**
	 * Sets the specified {@code response} on the current thread.
	 * 
	 * @param response (non-null)
	 */
	static void setResponse(final HttpServletResponse response) {
		RESPONSE_HOLDER.set(Objects.requireNonNull(response, "HttpServletResponse cannot be null"));
	}
	
	/**
	 * Clears the currently associated response from the thread.
	 */
	static void clear() {
		RESPONSE_HOLDER.remove();
	}
	
}
