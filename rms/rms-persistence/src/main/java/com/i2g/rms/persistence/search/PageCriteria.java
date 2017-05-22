package com.i2g.rms.persistence.search;

public class PageCriteria {

	/** Current page to return (zero based). */
	private final int _currentPage;
	/** Number of records to return per page. */
	private final int _pageSize;

	/**
	 * Creates a new instance of {@code PageCriteria} with the specified
	 * {@code currentPage} and {@code pageSize}.
	 * 
	 * @param currentPage
	 *            (zero based)
	 * @param pageSize
	 *            (must be greater than zero)
	 */
	public PageCriteria(final int currentPage, final int pageSize) {
		if (currentPage < 0) {
			throw new IllegalArgumentException("Current page must be zero or greater; received: " + currentPage);
		} else if (pageSize < 1) {
			throw new IllegalArgumentException("Page size must be greater than zero; received: " + pageSize);
		}
		_currentPage = currentPage;
		_pageSize = pageSize;
	}

	/**
	 * Returns the current page (zero based).
	 * 
	 * @return current page
	 */
	public int getCurrentPage() {
		return _currentPage;
	}

	/**
	 * Returns the number of records per page.
	 * 
	 * @return number of records per page
	 */
	public int getPageSize() {
		return _pageSize;
	}

	@Override
	public String toString() {
		return "PageCriteria currentPage: " + _currentPage + " pageSize: " + _pageSize;
	}

}
