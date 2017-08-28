package com.i2g.rms.rest.search.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collections;
import java.util.List;

/**
 * REST object for representing the search criteria for a request.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchRO {
	
	/** Paging information. */
	private PageRO paging;
	/** Filter information. */
	private List<FilterRO> filters;
	/** Sort information. */
	private List<SortRO> sorts;
	
	
	/**
	 * Returns the paging request.
	 * 
	 * @return paging request
	 */
	public PageRO getPaging() {
		return paging;
	}
	
	/**
	 * Sets the specified {@code paging} request.
	 * 
	 * @param paging 
	 */
	public void setPaging(final PageRO paging) {
		this.paging = paging;
	}
	
	/**
	 * Returns the sorting requests.
	 * 
	 * @return sorting requests
	 */
	public List<SortRO> getSorts() {
		return sorts != null ? sorts : Collections.emptyList();
	}
	
	/**
	 * Sets the specified {@code sorts}.
	 * 
	 * @param sorts 
	 */
	public void setSorts(List<SortRO> sorts) {
		this.sorts = sorts;
	}
	
	/**
	 * Returns the filtering requests.
	 * 
	 * @return filtering requests
	 */
	public List<FilterRO> getFilters() {
		return filters != null ? filters : Collections.emptyList();
	}
	
	/**
	 * Sets the specified {@code filters}.
	 * 
	 * @param filters 
	 */
	public void setFilters(List<FilterRO> filters) {
		this.filters = filters;
	}
	
}
