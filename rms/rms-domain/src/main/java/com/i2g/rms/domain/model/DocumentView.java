package com.i2g.rms.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity representation of Document View. Since the original table has BLOB to
 * improve performance this view doesnt include the BLOB column.
 * 
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_DOC_VW")
public class DocumentView extends AbstractDataModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary surrogate key ID */
	private long _id;
	private Long _incidentId;
	private StatusFlag _statusFlag;
	private String _fileName;
	private String _originalFileName;
	private String _fileDescription;
	private String _fileContentType;
	private Long _fileSize;

	@Id
	@Column(name = "ID")
	public Long getId() {
		return _id;
	}

	public void setId(final long id) {
		_id = id;
	}

	@Column(name = "INC_ID")
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
	@Column(name = "STS_FLG")
	@Enumerated(EnumType.STRING)
	public StatusFlag getStatusFlag() {
		return _statusFlag;
	}

	/**
	 * @param statusFlag
	 *            the statusFlag to set
	 */
	public void setStatusFlag(final StatusFlag statusFlag) {
		_statusFlag = statusFlag;
	}

	@Column(name = "FILE_NAME")
	public String getFileName() {
		return _fileName;
	}

	public void setFileName(final String fileName) {
		_fileName = fileName;
	}

	@Column(name = "ORG_FILE_NAME")
	public String getOriginalFileName() {
		return _originalFileName;
	}

	public void setOriginalFileName(final String originalFileName) {
		_originalFileName = originalFileName;
	}

	@Column(name = "FILE_DESC")
	public String getFileDescription() {
		return _fileDescription;
	}

	public void setFileDescription(final String fileDescription) {
		_fileDescription = fileDescription;
	}

	@Column(name = "FILE_CONTENT_TYPE")
	public String getFileContentType() {
		return _fileContentType;
	}

	public void setFileContentType(final String fileContentType) {
		_fileContentType = fileContentType;
	}

	@Column(name = "FILE_SIZE")
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
