package com.i2g.rms.persistence.search;

import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class FilterCriteria {
	/**
	 * Enum constants for the available operators to apply in a filter.
	 */
	public enum Operator implements Filter {
		EQ() {
			@Override
			public Criteria apply(final Criteria c, final String f, final Object v) {
				return c.add(Restrictions.eq(f, v));
			}
		},
		NEQ() {
			@Override
			public Criteria apply(final Criteria c, final String f, final Object v) {
				return c.add(Restrictions.ne(f, v));
			}
		},
		STARTS_WITH() {
			@Override
			public Criteria apply(final Criteria c, final String f, final Object v) {
				return c.add(Restrictions.ilike(f, v + "%"));
			}
		},
		ENDS_WITH() {
			@Override
			public Criteria apply(final Criteria c, final String f, final Object v) {
				return c.add(Restrictions.ilike(f, "%" + v));
			}
		},
		CONTAINS() {
			@Override
			public Criteria apply(final Criteria c, final String f, final Object v) {
				return c.add(Restrictions.ilike(f, "%" + v + "%"));
			}
		},
		NOT_CONTAINS() {
			@Override
			public Criteria apply(final Criteria c, final String f, final Object v) {
				return c.add(Restrictions.not(Restrictions.ilike(f, "%" + v + "%")));
			}
		},
		GT() {
			@Override
			public Criteria apply(final Criteria c, final String f, final Object v) {
				return c.add(Restrictions.gt(f, v));
			}
		},
		LT() {
			@Override
			public Criteria apply(final Criteria c, final String f, final Object v) {
				return c.add(Restrictions.lt(f, v));
			}
		};
	}

	/** Field name to apply filter to. */
	private final String _fieldName;
	/** Operator defining type of filter to apply. */
	private final Operator _operator;
	/** Search value to use in filter. */
	private final Object _searchValue;

	/**
	 * Creates a new instance of {@code FilterCriteria} for the specified
	 * {@code fieldName}, {@code operator} and {@code searchValue}.
	 * 
	 * @param fieldName
	 *            (non-null)
	 * @param operator
	 *            (non-null)
	 * @param searchValue
	 *            (non-null)
	 */
	public FilterCriteria(final String fieldName, final Operator operator, final Object searchValue) {
		_fieldName = Objects.requireNonNull(fieldName, "Field name cannot be null");
		_operator = Objects.requireNonNull(operator, "Filter operator cannot be null");
		_searchValue = Objects.requireNonNull(searchValue, "Search value cannot be null");
	}

	/**
	 * Applies the filter to the specified {@code criteria}.
	 * 
	 * @param criteria
	 * @return updated criteria with filter applied
	 */
	public Criteria applyFilter(final Criteria criteria) {
		return _operator.apply(criteria, _fieldName, _searchValue);
	}

	/**
	 * Returns the field name to filter.
	 * 
	 * @return field name (never null)
	 */
	public String getFieldName() {
		return _fieldName;
	}

	/**
	 * Returns the filter operator to apply.
	 * 
	 * @return filter operator (never null)
	 */
	public Operator getOperator() {
		return _operator;
	}

	/**
	 * Returns the search value to filter by.
	 * 
	 * @return search value (never null)
	 */
	public Object getSearchValue() {
		return _searchValue;
	}

	@Override
	public String toString() {
		return "FilterCriteria fieldName: " + _fieldName + " operator: " + _operator + " searchValue: " + _searchValue;
	}
}
