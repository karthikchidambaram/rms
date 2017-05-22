package com.i2g.rms.persistence.search;

import java.util.Objects;

import org.hibernate.Criteria;

public class SortCriteria {
	
	/**
	 * Enum constants to represent the available sort orders.
	 */
	public enum Order {
		DESC, ASC, NONE;
	}

	/** Field name to sort by. */
	private final String _fieldName;
	/** Order of the sort. */
	private final Order _order;

	/**
	 * Creates a new instance of {@code SortCriteria} for the specified
	 * {@code fieldName} with a default sort order of descending.
	 * 
	 * @param fieldName
	 *            to sort by
	 */
	public SortCriteria(final String fieldName) {
		this(fieldName, Order.DESC);
	}

	/**
	 * Creates a new instance of {@code SortCriteria} for the specified
	 * {@code fieldName} and {@code order}.
	 * 
	 * @param fieldName
	 *            to sort by (non-null)
	 * @param order
	 *            or sort (non-null)
	 */
	public SortCriteria(final String fieldName, final Order order) {
		_fieldName = Objects.requireNonNull(fieldName, "Field name cannot be null");
		_order = order != null ? order : Order.DESC;
	}

	/**
	 * Applies the sort criteria to the specified {@code criteria}.
	 * 
	 * @param criteria
	 *            to apply sort to
	 * @return updated criteria with sort applied
	 */
	public Criteria applySort(final Criteria criteria) {
		switch (_order) {
		case DESC:
			return criteria.addOrder(org.hibernate.criterion.Order.desc(_fieldName));
		case ASC:
			return criteria.addOrder(org.hibernate.criterion.Order.asc(_fieldName));
		case NONE:
		default:
			return criteria; // No order to apply
		}
	}

	/**
	 * Returns the field name to sort by.
	 * 
	 * @return field name to sort by
	 */
	public String getFieldName() {
		return _fieldName;
	}

	/**
	 * Returns the order of the sort.
	 * 
	 * @return order of the sort
	 */
	public Order getOrder() {
		return _order;
	}

	@Override
	public String toString() {
		return "SortCriteria fieldName: " + _fieldName + " order: " + _order;
	}

}
