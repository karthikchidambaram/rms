package com.i2g.rms.rest.model;

public enum IncidentStatusRO {
	
	NEW("New"), 
	OPEN("Open"), 
	IN_PROGRESS("In-progress"), 
	SUSPENDED("Suspended"), 
	CLOSED("Closed");

	public String value;

	private IncidentStatusRO(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
