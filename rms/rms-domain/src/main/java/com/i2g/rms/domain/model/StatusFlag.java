package com.i2g.rms.domain.model;

public enum StatusFlag {
	
	ACTIVE("Active"), 
	INACTIVE("Inactive"); 
	
	public String value;
	
	private StatusFlag(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
