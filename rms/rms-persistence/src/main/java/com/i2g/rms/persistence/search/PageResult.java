package com.i2g.rms.persistence.search;

public class PageResult {
	/** Current page. */
	private final int _currentPage;
	/** Current page size. */
	private final int _pageSize;
	/** Total number of records available. */
	private final int _totalCount;

	/**
	 * Creates a new instance of {@code PageResult} with the specified
	 * {@code pageCriteria} and {@code totalCount} of records.
	 * 
	 * @param pageCriteria
	 * @param totalCount
	 */
	PageResult(final PageCriteria pageCriteria, final int totalCount) {
		_currentPage = pageCriteria.getCurrentPage();
		_pageSize = pageCriteria.getPageSize();
		_totalCount = totalCount;
	}

	/**
	 * Returns the current page of the query.
	 * 
	 * @return current page (zero based)
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

	/**
	 * Returns the total count of records available for the query.
	 * 
	 * @return total count of records available
	 */
	public int getTotalCount() {
		return _totalCount;
	}

	@Override
	public String toString() {
		return "PageResult currentPage: " + _currentPage + " pageSize: " + _pageSize + " totalCount: " + _totalCount;
	}
}
