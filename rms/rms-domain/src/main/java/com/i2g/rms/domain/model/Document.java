package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.tablemaintenance.DocumentCategory;
import com.i2g.rms.domain.model.tablemaintenance.DocumentType;

/**
 * Entity representation of Document.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_DOC")
public class Document extends AbstractDataModel<Long> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Primary surrogate key ID */
	private long _id;
	private Incident _incident;
	private StatusFlag _statusFlag;
	private DocumentType _documentType;
	private String _documentName;
	private Blob _document;
	private DocumentCategory _documentCategory;
		
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Document() {
	}
	
	/**
	 * Creates a new immutable instance of {@link Document} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private Document(final Builder builder) {
		_id = Objects.requireNonNull(builder._id, "Document Id cannot be null.");		
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");
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
	 * @param incident the incident to set
	 */
	public void setIncident(final Incident incident) {
		_incident = incident;
	}
	
	/**
	 * @return the statusFlag
	 */
	@Column(name = "STS_FLG", nullable = false)
	@Size(min = 1, max = 16, message = "Status flag code must be between {min} and {max} characters")
	@NotNull
	@Enumerated(EnumType.STRING)
	public StatusFlag getStatusFlag() {
		return _statusFlag;
	}

	/**
	 * @param statusFlag the statusFlag to set
	 */
	public void setStatusFlag(final StatusFlag statusFlag) {
		_statusFlag = statusFlag;
	}
	
	/**
	 * @return the documentType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOC_TYP_CDE")
	@Size(min = 1, max = 16, message = "Document type code must be between {min} and {max} characters")
	public DocumentType getDocumentType() {
		return _documentType;
	}

	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(final DocumentType documentType) {
		_documentType = documentType;
	}

	/**
	 * @return the documentName
	 */
	@Column(name = "DOC_NAM")
	@Size(min = 1, max = 128, message = "Document name must be between {min} and {max} characters")
	public String getDocumentName() {
		return _documentName;
	}

	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(final String documentName) {
		_documentName = documentName;
	}

	/**
	 * @return the document
	 */
	@Column(name = "DOC")
	public Blob getDocument() {
		return _document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(final Blob document) {
		_document = document;
	}

	/**
	 * @return the documentCategory
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOC_CTGRY_CDE")
	@Size(min = 1, max = 16, message = "Document category code must be between {min} and {max} characters")
	public DocumentCategory getDocumentCategory() {
		return _documentCategory;
	}

	/**
	 * @param documentCategory the documentCategory to set
	 */
	public void setDocumentCategory(final DocumentCategory documentCategory) {
		_documentCategory = documentCategory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _statusFlag);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Document) {
			final Document other = (Document) obj;
			return Objects.equals(_id, other._id) 
					&& Objects.equals(_statusFlag, other._statusFlag);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Id: " + _id + ", "		
		+ "Status Flag: " + _statusFlag;
	}
	
	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link Document}.
	 */
	public final static class Builder {

		private Long _id;
		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Document.
		 * 
		 * @return new instance of Document
		 */
		public Document build() {
			return new Document(this);
		}

		/**
		 * Sets the specified {@code id}.
		 * 
		 * @param id
		 * @return this builder
		 */
		public Builder setId(final Long id) {
			_id = id;
			return this;
		}

		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}
}
