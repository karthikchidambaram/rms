package com.i2g.rms.rest.model.search;

/**
 *
 * @author Karthikeyan Chidambaram
 */
public class FilterRO {
	
	/**
	 * Enum constants for the available operators to apply in a filter.
	 */
	public enum Operator {
		EQ, NEQ, STARTS_WITH, ENDS_WITH, CONTAINS, NOT_CONTAINS, GT, LT
	}

	private String field;
	private Operator operator;
	private String value;
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
