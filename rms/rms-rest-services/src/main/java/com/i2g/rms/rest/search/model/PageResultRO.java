package com.i2g.rms.rest.search.model;

/**
 * REST object for representing the result of paging sent in response.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class PageResultRO {
	
	/** Current page. */
	private Integer currentPage;
	/** Current page size. */
	private Integer pageSize;
	/** Total number of records available. */
	private Integer totalCount;
	
	
	/**
	 * Returns the current page (zero based).
	 * 
	 * @return current page
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * Sets the specified {@code currentPage}.
	 * 
	 * @param currentPage 
	 */
	public void setCurrentPage(final int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * Returns the number of records per page.
	 * 
	 * @return page size
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * Sets the specified {@code pageSize}.
	 * 
	 * @param pageSize 
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * Returns the total number of available records.
	 * 
	 * @return total number of available records
	 */
	public int getTotalCount() {
		return totalCount;
	}
	
	/**
	 * Sets the specified {@code totalCount}.
	 * 
	 * @param totalCount 
	 */
	public void setTotalCount(final int totalCount) {
		this.totalCount = totalCount;
	}
	
}
