package com.i2g.rms.rest.model;

public enum YesNoTypeRO {
	
	Y("Yes"), 
	N("No");
	
	public String value;
	
	private YesNoTypeRO(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
