package com.i2g.rms.util.test.search;

import java.util.List;

public class SearchRO {
	/** Paging information. */
	private PageRO paging;
	/** Filter information. */
	private List<FilterRO> filters;
	/** Sort information. */
	private List<SortRO> sorts;
	
	public PageRO getPaging() {
		return paging;
	}
	
	public void setPaging(PageRO paging) {
		this.paging = paging;
	}
	
	public List<FilterRO> getFilters() {
		return filters;
	}
	
	public void setFilters(List<FilterRO> filters) {
		this.filters = filters;
	}
	
	public List<SortRO> getSorts() {
		return sorts;
	}
	
	public void setSorts(List<SortRO> sorts) {
		this.sorts = sorts;
	}
	
}
