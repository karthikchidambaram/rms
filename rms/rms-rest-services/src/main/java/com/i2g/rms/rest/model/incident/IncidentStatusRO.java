package com.i2g.rms.rest.model.incident;

public enum IncidentStatusRO {
	
	DRAFT("Draft"),
	NEW("New"),
	ASSIGNED("Assigned"),
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
