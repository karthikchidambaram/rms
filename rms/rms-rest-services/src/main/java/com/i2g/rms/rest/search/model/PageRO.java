package com.i2g.rms.rest.search.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * REST object for defining the paging criteria for an entity.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class PageRO {
	
	/** Current page to retrieve (zero based). */
	private Integer currentPage = 0;
	/** Page size defining max number of records to return. */
	private Integer pageSize;
	
	
	/**
	 * Returns the current page to retrieve (zero based).
	 * 
	 * @return current page
	 */
	@NotNull @Min(0)
	public Integer getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * Sets the specified {@code currentPage} to retrieve (zero based).
	 * 
	 * @param currentPage 
	 */
	public void setCurrentPage(final Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * Returns the page size (number of records to return in a page).
	 * 
	 * @return number of records in page
	 */
	@Min(1)
	public Integer getPageSize() {
		return pageSize;
	}
	
	/**
	 * Sets the specified {@code pageSize} to retrieve.
	 * 
	 * @param pageSize 
	 */
	public void setPageSize(final Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
