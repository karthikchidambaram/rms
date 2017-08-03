package com.i2g.rms.domain.model.incident;

public enum IncidentStatus {
	
	DRAFT("Draft"),
	NEW("New"),
	ASSIGNED("Assigned"),
	IN_PROGRESS("In-progress"), 
	SUSPENDED("Suspended"), 
	CLOSED("Closed");

	public String value;

	private IncidentStatus(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
