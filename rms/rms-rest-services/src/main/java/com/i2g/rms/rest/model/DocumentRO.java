package com.i2g.rms.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.incident.IncidentRO;

/**
 * REST Object for Document.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentRO extends AbstractEntityRO {

	private long _id;
	private IncidentRO _incident;
	private StatusFlagRO _statusFlag;
	private String _fileName;
	private String _originalFileName;
	private String _fileDescription;
	private String _fileContentType;
	private Long _fileSize;
	private byte[] _fileContent;

	/**
	 * @return the id
	 */
	public long getId() {
		return _id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final long id) {
		_id = id;
	}

	/**
	 * @return the incident
	 */
	public IncidentRO getIncident() {
		return _incident;
	}

	/**
	 * @param incident
	 *            the incident to set
	 */
	public void setIncident(final IncidentRO incident) {
		_incident = incident;
	}

	/**
	 * @return the statusFlag
	 */
	public StatusFlagRO getStatusFlag() {
		return _statusFlag;
	}

	/**
	 * @param statusFlag
	 *            the statusFlag to set
	 */
	public void setStatusFlag(final StatusFlagRO statusFlag) {
		_statusFlag = statusFlag;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(final String fileName) {
		_fileName = fileName;
	}

	public String getOriginalFileName() {
		return _originalFileName;
	}

	public void setOriginalFileName(final String originalFileName) {
		_originalFileName = originalFileName;
	}

	public String getFileDescription() {
		return _fileDescription;
	}

	public void setFileDescription(final String fileDescription) {
		_fileDescription = fileDescription;
	}

	public String getFileContentType() {
		return _fileContentType;
	}

	public void setFileContentType(final String fileContentType) {
		_fileContentType = fileContentType;
	}

	public Long getFileSize() {
		return _fileSize;
	}

	public void setFileSize(final Long fileSize) {
		if (fileSize != null) {
			_fileSize = fileSize;
		} else {
			_fileSize = 0l;
		}
	}

	public byte[] getFileContent() {
		return _fileContent;
	}

	public void setFileContent(final byte[] fileContent) {
		_fileContent = fileContent;
	}
}