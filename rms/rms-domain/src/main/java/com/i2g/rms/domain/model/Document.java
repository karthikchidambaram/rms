package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.incident.Incident;

/**
 * Entity representation of Document.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_DOC")
@JsonIgnoreProperties({ "incident" })
public class Document extends AbstractDataModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary surrogate key ID */
	private long _id;
	private Incident _incident;
	private StatusFlag _statusFlag;
	private String _fileName;
	private String _originalFileName;
	private String _fileDescription;
	private String _fileContentType;
	private Long _fileSize;
	private Blob _fileContent;
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Document() {
	}

	/**
	 * Creates a new immutable instance of {@link Document} from the specified
	 * {@code builder}.
	 * 
	 * @param builder
	 */
	private Document(final Builder builder) {
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");
		_incident = Objects.requireNonNull(builder._incident, "Incident cannot be null.");
	}

	/**
	 * Return the ReportedLoss primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_doc_id_seq")
	@SequenceGenerator(name = "rms_doc_id_seq", sequenceName = "RMS_DOC_ID_SEQ", allocationSize = 1)
	@Override
	public Long getId() {
		return _id;
	}

	/**
	 * Sets the primary surrogate key ID.
	 * 
	 * <p>
	 * <strong>Note:</strong> This method has protected access to prevent
	 * callers from manually setting the auto-generated primary key ID;
	 * Hibernate has access to invoke this method when populating an entity.
	 * </p>
	 * 
	 * @param id
	 */
	protected void setId(final long id) {
		_id = id;
	}

	/**
	 * @return the incidentId
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INC_ID")
	public Incident getIncident() {
		return _incident;
	}

	/**
	 * @param incident
	 *            the incident to set
	 */
	public void setIncident(final Incident incident) {
		_incident = incident;
	}

	/**
	 * @return the statusFlag
	 */
	@Column(name = "STS_FLG", nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull
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
	
	@Column(name = "FILE_NAME", length = 128)
	public String getFileName() {
		return _fileName;
	}

	public void setFileName(final String fileName) {
		_fileName = fileName;
	}
	
	@Column(name = "ORG_FILE_NAME", length = 128)
	public String getOriginalFileName() {
		return _originalFileName;
	}

	public void setOriginalFileName(final String originalFileName) {
		_originalFileName = originalFileName;
	}
	
	@Column(name = "FILE_DESC", length = 256)
	public String getFileDescription() {
		return _fileDescription;
	}

	public void setFileDescription(final String fileDescription) {
		_fileDescription = fileDescription;
	}
	
	@Column(name = "FILE_CONTENT_TYPE", length = 128)
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
	
	@Lob
	@Column(name = "FILE_CONTENT")
	@Basic(fetch = FetchType.LAZY)
	public Blob getFileContent() {
		return _fileContent;
	}

	public void setFileContent(final Blob fileContent) {
		_fileContent = fileContent;
	}

	/**
	 * Builder pattern for constructing immutable instances of {@link Document}.
	 */
	public final static class Builder {

		private StatusFlag _statusFlag;
		private Incident _incident;

		/**
		 * Builds a new immutable instance of Document.
		 * 
		 * @return new instance of Document
		 */
		public Document build() {
			return new Document(this);
		}

		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}

		public Builder setIncident(final Incident incident) {
			_incident = incident;
			return this;
		}
	}
}
