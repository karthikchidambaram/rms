package com.i2g.rms.util.test.search;

public class SortRO {
	/**
	 * Enum constants to represent the available sort orders.
	 */
	public enum Order {
		DESC, ASC, NONE;
	}
	
	/** Field name of entity by which to sort. */
	private String field;
	/** Direction of the sort. */
	private Order order;
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
}
