package com.i2g.rms.rest.search;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.i2g.rms.persistence.search.SearchContext;
import com.i2g.rms.persistence.search.SearchContextManager;

/**
 * AOP method interceptor which is applied to REST controller method endpoints
 * to bind the search request information to the current thread and update the
 * response with metadata about the search.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Service
public class SearchMethodInterceptor implements MethodInterceptor {
	
	/** {@code Logger} instance. */
	private final Logger _logger = LoggerFactory.getLogger(SearchMethodInterceptor.class);
	
	/** Service for parsing search criteria and creating search request. */
	@Autowired
	private SearchRequestProcessor _searchRequestProcessor;
	/** Service for adding the search result in the response header. */
	@Autowired
	private SearchResponseProcessor _searchResponseProcessor;
	
	/** 
	 * Cache of searchable REST controller methods, storing the parsed search
	 * context attributes from the associated annotations.  The cache is lazy
	 * loaded as each endpoint is invoked.
	 */
	private final ConcurrentMap<Method, SearchMethodContext> _methodContexts = new ConcurrentHashMap<>();
	
	
	@Override
	public Object invoke(final MethodInvocation invocation) throws Throwable {
		_logger.trace("Intercepted method [{}] to retrieve search criteria", invocation.getMethod());
		
		// First retrieve the search method context annotation information
		final SearchMethodContext methodContext = getMethodContext(invocation.getMethod());
		_logger.trace("Search method context: {}", methodContext);
		
		// Parse search context from request and bind to the current thread
		final SearchContext context = _searchRequestProcessor.parse(getRequest(), methodContext);
		_logger.trace("Search context from request: {}", context);
		SearchContextManager.setContext(methodContext.getEntityType(), context);
		
		try {
			// Invoke method and update response with any context
			final Object returnValue = invocation.proceed();
			_searchResponseProcessor.addResponse(context, getResponse());
			return returnValue;
		} finally {
			SearchContextManager.clear();	// Always clear thread local context
		}
	}
	
	/**
	 * Returns the HTTP servlet request bound to the current thread or throws an
	 * exception if not invoked from a web request (no HTTP request bound).
	 * 
	 * @return HTTP servlet request
	 */
	private HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**
	 * Returns the HTTP servlet response bound tot he current thread or throws
	 * an exception if not invoked from a web request (no HTTP response bound).
	 * 
	 * @return HTTP servlet response
	 */
	private HttpServletResponse getResponse() {
		return ResponseContextHolder.getResponse();
	}
	
	/**
	 * Returns the search context of the specified {@code method} as defined by
	 * its associated annotations.  The method lazily creates the context if it
	 * does not yet exist and locally caches it for future calls.
	 * 
	 * @param method to retrieve search context for
	 * @return method search context
	 */
	private SearchMethodContext getMethodContext(final Method method) {
		SearchMethodContext context = _methodContexts.get(method);
		if( context == null ) {
			_methodContexts.put(method, context = new SearchMethodContext(method));
		}
		return context;
	}
	
}
