package com.i2g.rms.rest.search.model;

import javax.validation.constraints.NotNull;

import com.i2g.rms.persistence.search.SortCriteria.Order;

/**
 * REST object for defining the parameters to a sort criteria.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class SortRO {
	
	/** Field name of entity by which to sort. */
	private String field;
	/** Direction of the sort. */
	private Order order;
	
	
	/**
	 * Returns the field by which to sort.
	 * 
	 * @return field to sort by (not null)
	 */
	@NotNull
	public String getField() {
		return field;
	}
	
	/**
	 * Sets the specified {@code field} to sort by.
	 * 
	 * @param field (not null)
	 */
	public void setField(final String field) {
		this.field = field;
	}
	
	/**
	 * Returns the order to sort by.
	 * 
	 * @return order of sort (not null)
	 */
	@NotNull
	public Order getOrder() {
		return order;
	}
	
	/**
	 * Sets the specified sort {@code order}.
	 * 
	 * @param order of sort
	 */
	public void setOrder(final Order order) {
		this.order = order;
	}
	
}
