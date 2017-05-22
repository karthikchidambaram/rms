package com.i2g.rms.persistence.search;

import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

	/** {@code Logger} instance. */
	private final Logger _logger = LoggerFactory.getLogger(SearchService.class);

	/**
	 * Applies the filters (if any) defined in the search {@code context} to the
	 * specified {@code criteria}.
	 * 
	 * @param criteria
	 *            to apply filters to (non-null)
	 * @param context
	 *            of search
	 * @return updated criteria with filters applied
	 */
	public Criteria applyFilters(final Criteria criteria, final SearchContext context) {
		Objects.requireNonNull(criteria, "Criteria cannot be null");
		Objects.requireNonNull(context, "Search context cannot be null");
		context.getFilterCriteria().stream().forEachOrdered((filter) -> {
			filter.applyFilter(criteria);
			_logger.trace("Applied filter: {}", filter);
		});
		return criteria;
	}

	/**
	 * Applies the sorts (if any) defined in the search {@code context} to the
	 * specified {@code criteria}.
	 * 
	 * @param criteria
	 *            to apply sorts to (non-null)
	 * @param context
	 *            of search
	 * @return updated criteria with sorts applied
	 */
	public Criteria applySorts(final Criteria criteria, final SearchContext context) {
		Objects.requireNonNull(criteria, "Criteria cannot be null");
		Objects.requireNonNull(context, "Search context cannot be null");
		context.getSortCriteria().stream().forEachOrdered((sort) -> {
			sort.applySort(criteria);
			_logger.trace("Applied sort: {}", sort);
		});
		return criteria;
	}

	/**
	 * Applies the sorts defined in the search {@code context} to the specified
	 * {@code criteria} or the {@code defaultSorts} if no sorts are defined.
	 * 
	 * @param criteria
	 *            to apply sorts to (non-null)
	 * @param context
	 *            of search
	 * @param defaultSorts
	 *            to apply if no sorts defined in context
	 * @return updated criteria with sorts applied
	 */
	public Criteria applySorts(final Criteria criteria, final SearchContext context, final Order... defaultSorts) {
		Objects.requireNonNull(criteria, "Criteria cannot be null");
		Objects.requireNonNull(context, "Search context cannot be null");
		if (context.getSortCriteria().isEmpty()) {
			if (defaultSorts != null) {
				for (final Order defaultSort : defaultSorts) {
					criteria.addOrder(defaultSort);
					_logger.trace("Applied default sort: {}", defaultSort);
				}
			}
			return criteria;
		}
		return applySorts(criteria, context);
	}

	/**
	 * Applies pagination to the specified {@code criteria} if defined in the
	 * search {@code context} and updates the context with a {@code PageResult}
	 * capturing the results of the pagination.
	 * 
	 * <p>
	 * <strong>Warning:</strong> Please note pagination can only be applied
	 * after any filters and/or sorts applied to the criteria to ensure the
	 * paging is applied to the appropriate results.
	 * </p>
	 * 
	 * @param criteria
	 *            to apply paging to (non-null)
	 * @param context
	 *            of search
	 * @return updated criteria with paging applied
	 */
	public Criteria applyPaging(final Criteria criteria, final SearchContext context) {
		Objects.requireNonNull(criteria, "Criteria cannot be null");
		Objects.requireNonNull(context, "Search context cannot be null");
		if (context.getPageCriteria() == null) {
			return criteria;
		}

		// Retrieve and set the total row count in search result
		criteria.setProjection(Projections.rowCount());
		final int count = ((Number) criteria.uniqueResult()).intValue();
		context.setPageResult(new PageResult(context.getPageCriteria(), count));

		// Restore the criteria (remove the row count from total size)
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.ROOT_ENTITY);

		// Set current page and page size on criteria
		criteria.setFirstResult(context.getPageCriteria().getCurrentPage() * context.getPageCriteria().getPageSize());
		criteria.setMaxResults(context.getPageCriteria().getPageSize());
		return criteria;
	}

}
