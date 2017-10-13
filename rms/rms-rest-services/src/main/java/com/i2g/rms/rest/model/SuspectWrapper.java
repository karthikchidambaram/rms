package com.i2g.rms.rest.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuspectWrapper {
	
	private String uniqueIncidentId;
	
	private List<SuspectRO> suspects = new ArrayList<SuspectRO>(0);

	public List<SuspectRO> getSuspects() {
		return suspects;
	}

	public void setSuspects(final List<SuspectRO> suspects) {
		this.suspects = suspects;
	}	

	public String getUniqueIncidentId() {
		return uniqueIncidentId;
	}

	public void setUniqueIncidentId(final String uniqueIncidentId) {
		this.uniqueIncidentId = uniqueIncidentId;
	}	
}
