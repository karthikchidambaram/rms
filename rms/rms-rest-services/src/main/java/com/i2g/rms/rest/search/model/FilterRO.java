package com.i2g.rms.rest.search.model;

import javax.validation.constraints.NotNull;

import com.i2g.rms.persistence.search.FilterCriteria.Operator;

/**
 * REST object for defining the filter criteria for searching on an entity.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class FilterRO {
	
	/** Field to filter by. */
	private String field;
	/** Operator to filter with. */
	private Operator operator;
	/** Search value to apply. */
	private String value;
	
	
	/**
	 * Returns the field by which to filter.
	 * 
	 * @return field (never null)
	 */
	@NotNull
	public String getField() {
		return field;
	}
	
	/**
	 * Sets the specified {@code field} to filter by.
	 * 
	 * @param field (not null)
	 */
	public void setField(final String field) {
		this.field = field;
	}
	
	/**
	 * Returns the filter operator to apply.
	 * 
	 * @return operator (not null)
	 */
	@NotNull
	public Operator getOperator() {
		return operator;
	}
	
	/**
	 * Sets the specified filter {@code operator} to apply.
	 * 
	 * @param operator 
	 */
	public void setOperator(final Operator operator) {
		this.operator = operator;
	}
	
	/**
	 * Returns the search value to apply using the operator.
	 * 
	 * @return search value
	 */
	@NotNull
	public String getValue() {
		return value;
	}
	
	/**
	 * Sets the specified search {@code value}.
	 * 
	 * @param value 
	 */
	public void setValue(final String value) {
		this.value = value;
	}
	
}
