package com.i2g.rms.domain.model;

public enum YesNoType {
	
	Y("Yes"), 
	N("No");
	
	public String value;
	
	private YesNoType(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
