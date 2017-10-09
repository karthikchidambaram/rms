package com.i2g.rms.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * REST Object for Document.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentViewRO extends AbstractEntityRO {

	private long _id;
	private Long _incidentId;
	private StatusFlagRO _statusFlag;
	private String _fileName;
	private String _originalFileName;
	private String _fileDescription;
	private String _fileContentType;
	private Long _fileSize;	

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
	 * @return the incidentId
	 */
	public Long getIncidentId() {
		return _incidentId;
	}

	/**
	 * @param incidentId
	 *            the incidentId to set
	 */
	public void setIncidentId(final Long incidentId) {
		if (incidentId != null) {
			_incidentId = incidentId;
		} else {
			_incidentId = 0l;
		}
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
}