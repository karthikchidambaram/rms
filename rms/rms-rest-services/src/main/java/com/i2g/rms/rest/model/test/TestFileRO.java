package com.i2g.rms.rest.model.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;

/**
 * RO for file upload test.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestFileRO extends AbstractEntityRO {

	private long _id;
	private String _fileName;
	private String _fileDescription;

	public long getId() {
		return _id;
	}

	public void setId(final long id) {
		_id = id;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(final String fileName) {
		_fileName = fileName;
	}

	public String getFileDescription() {
		return _fileDescription;
	}

	public void setFileDescription(final String fileDescription) {
		_fileDescription = fileDescription;
	}
}
