package com.i2g.rms.rest.model.incident;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentDetailRO extends BaseIncidentDetailRO {

	private List<FileRO> files;

	public List<FileRO> getFiles() {
		return files;
	}

	public void setFiles(final List<FileRO> files) {
		this.files = files;
	}
}
