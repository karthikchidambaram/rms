package com.i2g.rms.rest.search;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2g.rms.persistence.search.PageResult;
import com.i2g.rms.persistence.search.SearchContext;
import com.i2g.rms.rest.search.model.PageResultRO;

/**
 * Service for adding the paging result to the HTTP headers returned in the
 * response from a pageable REST controller method endpoint.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Component
public class SearchResponseProcessor {
	
	/** Constant HTTP response header {@value} for specifying paging result. */
	public final static String SEARCH_RESPONSE_HEADER = "PageResult";
	
	/** Jackson JSON mapper for serializing search response. */
	@Autowired
	private ObjectMapper _mapper;
	
	
	/**
	 * Adds the HTTP response header containing the paging result from the
	 * specified search {@code context} the servlet {@code response}.
	 * 
	 * @param context to retrieve paging result
	 * @param response to add header with paging result
	 */
	public void addResponse(final SearchContext context, final HttpServletResponse response) {
		final PageResult result = context.getPageResult();
		if( result == null ) {
			return;	// Nothing to process 
		}
		try {
			// Create the result object to sent to client
			final PageResultRO pageResult = new PageResultRO();
			pageResult.setCurrentPage(result.getCurrentPage());
			pageResult.setPageSize(result.getPageSize());
			pageResult.setTotalCount(result.getTotalCount());

			// Set the serialized JSON response as a header
			response.addHeader(SEARCH_RESPONSE_HEADER, _mapper.writeValueAsString(pageResult));
		} catch(Exception e) {
			throw new IllegalStateException("Failed to populate page result in HTTP response header: " + e, e);
		}
	}
	
}
