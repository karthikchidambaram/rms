package com.i2g.rms.rest.model;

public enum StatusFlagRO {
	
	ACTIVE("Active"), 
	INACTIVE("Inactive"); 
	
	public String value;
	
	private StatusFlagRO(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
