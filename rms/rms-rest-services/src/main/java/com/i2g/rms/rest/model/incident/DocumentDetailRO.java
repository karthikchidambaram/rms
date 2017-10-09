package com.i2g.rms.rest.model.incident;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentDetailRO {

	private long incidentId;
	private String uniqueIncidentId;
	private List<FileRO> files;

	public long getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(long incidentId) {
		this.incidentId = incidentId;
	}

	public String getUniqueIncidentId() {
		return uniqueIncidentId;
	}

	public void setUniqueIncidentId(final String uniqueIncidentId) {
		this.uniqueIncidentId = uniqueIncidentId;
	}

	public List<FileRO> getFiles() {
		return files;
	}

	public void setFiles(final List<FileRO> files) {
		this.files = files;
	}
}
