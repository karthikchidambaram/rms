package com.i2g.rms.persistence.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SearchContext {

	/** Page criteria to apply to search (may be null if no pagination). */
	private final PageCriteria _pageCriteria;
	/** List of filters to apply for search (may be empty). */
	private final List<FilterCriteria> _filterCriteria;
	/** List of sorts to apply for search (may be empty). */
	private final List<SortCriteria> _sortCriteria;
	/** Result from pagination (may be null if no pagination). */
	private PageResult _pageResult;

	/**
	 * Creates a new instance of {@code SearchContext} from the specified
	 * {@code builder}.
	 * 
	 * <p>
	 * <strong>Note:</strong> Constructor must remain private to prevent direct
	 * instantiation to support builder pattern.
	 * </p>
	 * 
	 * @param builder
	 */
	private SearchContext(final Builder builder) {
		_pageCriteria = builder._pageCriteria;
		_filterCriteria = builder._filterCriteria != null && !builder._filterCriteria.isEmpty()
				? Collections.unmodifiableList(new ArrayList<>(builder._filterCriteria))
				: Collections.<FilterCriteria>emptyList();
		_sortCriteria = builder._sortCriteria != null && !builder._sortCriteria.isEmpty()
				? Collections.unmodifiableList(new ArrayList<>(builder._sortCriteria))
				: Collections.<SortCriteria>emptyList();
	}

	/**
	 * Returns the page criteria for the search or {@code null} if no pagination
	 * is required.
	 * 
	 * @return page criteria or {@code null} if no pagination
	 */
	public PageCriteria getPageCriteria() {
		return _pageCriteria;
	}

	/**
	 * Returns an immutable list of the filter criteria for the search; may be
	 * empty if no filters.
	 * 
	 * @return list of filters (never null)
	 */
	public List<FilterCriteria> getFilterCriteria() {
		return _filterCriteria;
	}

	/**
	 * Returns an immutable list of the sort criteria for the search; may be
	 * empty if no sorts.
	 * 
	 * @return list of sorts (never null)
	 */
	public List<SortCriteria> getSortCriteria() {
		return _sortCriteria;
	}

	/**
	 * Returns the result of pagination or {@code null} if no pagination.
	 * 
	 * @return result of pagination or {@code null} if no paging
	 */
	public PageResult getPageResult() {
		return _pageResult;
	}

	void setPageResult(final PageResult result) {
		_pageResult = result;
	}

	@Override
	public String toString() {
		return "SearchContext filters: " + _filterCriteria + " sorts: " + _sortCriteria + " paging: " + _pageCriteria
				+ " pagingResult: " + _pageResult;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@code SearchContext}.
	 */
	public final static class Builder {

		/** Page criteria for search. */
		private PageCriteria _pageCriteria;
		/** List of filter criteria for search. */
		private List<FilterCriteria> _filterCriteria;
		/** List of sort criteria for search. */
		private List<SortCriteria> _sortCriteria;

		/**
		 * Builds a new immutable instance of {@code SearchContext}.
		 * 
		 * @return new instance of search context
		 */
		public SearchContext build() {
			return new SearchContext(this);
		}

		/**
		 * Sets the specified page {@code criteria}.
		 * 
		 * @param criteria
		 * @return this builder
		 */
		public Builder setPageCriteria(final PageCriteria criteria) {
			_pageCriteria = criteria;
			return this;
		}

		/**
		 * Adds the specified filter {@code criteria}.
		 * 
		 * @param criteria
		 *            to add (non-null)
		 * @return this builder
		 */
		public Builder addFilterCriteria(final FilterCriteria criteria) {
			if (_filterCriteria == null) {
				_filterCriteria = new ArrayList<>();
			}
			_filterCriteria.add(Objects.requireNonNull(criteria, "Filter criteria cannot be null"));
			return this;
		}

		/**
		 * Sets the specified {@code filterCriteria}.
		 * 
		 * @param filterCriteria
		 *            to set
		 * @return this builder
		 */
		public Builder setFilterCriteria(final List<FilterCriteria> filterCriteria) {
			_filterCriteria = filterCriteria;
			return this;
		}

		/**
		 * Adds the specified sort {@code criteria}.
		 * 
		 * @param criteria
		 *            to add (non-null)
		 * @return this builder
		 */
		public Builder addSortCriteria(final SortCriteria criteria) {
			if (_sortCriteria == null) {
				_sortCriteria = new ArrayList<>();
			}
			_sortCriteria.add(Objects.requireNonNull(criteria, "Sort criteria cannot be null"));
			return this;
		}

		/**
		 * Sets the specified {@code sortCriteria}.
		 * 
		 * @param sortCriteria
		 *            to set
		 * @return this builder
		 */
		public Builder setSortCriteria(final List<SortCriteria> sortCriteria) {
			_sortCriteria = sortCriteria;
			return this;
		}

	}

}
