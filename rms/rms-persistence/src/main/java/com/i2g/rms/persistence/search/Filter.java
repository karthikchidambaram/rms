package com.i2g.rms.persistence.search;

import org.hibernate.Criteria;

public interface Filter {
	/**
	 * Applies the filter to the specified {@code criteria} using the 
	 * {@code fieldName} and {@code searchValue}.
	 * 
	 * @param criteria to apply filter to
	 * @param fieldName to filter by
	 * @param searchValue to filter with
	 * @return updated criteria with filter applied
	 */
	Criteria apply(final Criteria criteria, final String fieldName, final Object searchValue);
}
