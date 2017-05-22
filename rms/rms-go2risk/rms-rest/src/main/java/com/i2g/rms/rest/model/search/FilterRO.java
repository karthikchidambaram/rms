package com.i2g.rms.rest.model.search;

/**
 *
 * @author Karthikeyan Chidambaram
 */
public class FilterRO {

	public enum Operator {
		EQ,
		NEQ,
		STARTSWITH,
		ENDSWITH,
		CONTAINS,
		DOESNOTCONTAIN
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
