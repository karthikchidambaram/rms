package com.i2g.rms.rest.model.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 *
 * @author Karthikeyan Chidambaram
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchRO {

	private Integer currentPage;
	private Integer pageSize;
	private List<SortRO> sorts;
	private List<FilterRO> filters;
	private long totalResults;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<SortRO> getSorts() {
		return sorts;
	}

	public void setSorts(List<SortRO> sorts) {
		this.sorts = sorts;
	}

	public List<FilterRO> getFilters() {
		return filters;
	}

	public void setFilters(List<FilterRO> filters) {
		this.filters = filters;
	}

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}

}
