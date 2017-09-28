package com.i2g.rms.domain.model;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.incident.Incident;

/**
 * Entity representation of Investigation.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_INVST")
@JsonIgnoreProperties({"incident"})
public class Investigation extends AbstractDataModel<Long> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID */
	private long _id;
	private Incident _incident;
	private StatusFlag _statusFlag;	
	private YesNoType _securityRequested;
	private YesNoType _trainingRequested;
	private YesNoType _reviewedInvestigationRecords;
	private YesNoType _reviewedCCTV;
	private YesNoType _reviewedPictures;
	private YesNoType _reviewedWitnessStatement;
	private YesNoType _reviewedLearnerRecords;
	private YesNoType _reviewedAssetRecords;
	private YesNoType _reviewedComplianceRecords;
	private User _investigator;
	private String _investigatorStatement;
		
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Investigation() {
	}
	
	/**
	 * Creates a new immutable instance of {@link Investigation} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private Investigation(final Builder builder) {
		_incident = Objects.requireNonNull(builder._incident, "Incident object cannot be null.");
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");
	}
	
	/**
	 * Return the Investigation primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_invst_id_seq")
	@SequenceGenerator(name = "rms_invst_id_seq", sequenceName = "RMS_INVST_ID_SEQ", allocationSize = 1)
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
	 * @return the securityRequested
	 */
	@Column(name = "SEC_REQ")
	@Enumerated(EnumType.STRING)
	public YesNoType getSecurityRequested() {
		return _securityRequested;
	}

	/**
	 * @param securityRequested the securityRequested to set
	 */
	public void setSecurityRequested(final YesNoType securityRequested) {
		_securityRequested = securityRequested;
	}

	/**
	 * @return the trainingRequested
	 */
	@Column(name = "TRA_REQ")
	@Enumerated(EnumType.STRING)
	public YesNoType getTrainingRequested() {
		return _trainingRequested;
	}

	/**
	 * @param trainingRequested the trainingRequested to set
	 */
	public void setTrainingRequested(final YesNoType trainingRequested) {
		_trainingRequested = trainingRequested;
	}

	/**
	 * @return the reviewedInvestigationRecords
	 */
	@Column(name = "RVWD_INVST_REC")
	@Enumerated(EnumType.STRING)
	public YesNoType getReviewedInvestigationRecords() {
		return _reviewedInvestigationRecords;
	}

	/**
	 * @param reviewedInvestigationRecords the reviewedInvestigationRecords to set
	 */
	public void setReviewedInvestigationRecords(final YesNoType reviewedInvestigationRecords) {
		_reviewedInvestigationRecords = reviewedInvestigationRecords;
	}

	/**
	 * @return the reviewedCCTV
	 */
	@Column(name = "RVWD_CCTV")
	@Enumerated(EnumType.STRING)
	public YesNoType getReviewedCCTV() {
		return _reviewedCCTV;
	}

	/**
	 * @param reviewedCCTV the reviewedCCTV to set
	 */
	public void setReviewedCCTV(final YesNoType reviewedCCTV) {
		_reviewedCCTV = reviewedCCTV;
	}

	/**
	 * @return the reviewedPictures
	 */
	@Column(name = "RVWD_PIC")
	@Enumerated(EnumType.STRING)
	public YesNoType getReviewedPictures() {
		return _reviewedPictures;
	}

	/**
	 * @param reviewedPictures the reviewedPictures to set
	 */
	public void setReviewedPictures(final YesNoType reviewedPictures) {
		_reviewedPictures = reviewedPictures;
	}

	/**
	 * @return the reviewedWitnessStatement
	 */
	@Column(name = "RVWD_WITNS_STMT")
	@Enumerated(EnumType.STRING)
	public YesNoType getReviewedWitnessStatement() {
		return _reviewedWitnessStatement;
	}

	/**
	 * @param reviewedWitnessStatement the reviewedWitnessStatement to set
	 */
	public void setReviewedWitnessStatement(final YesNoType reviewedWitnessStatement) {
		_reviewedWitnessStatement = reviewedWitnessStatement;
	}

	/**
	 * @return the reviewedLearnerRecords
	 */
	@Column(name = "RVWD_LRNR_REC")
	@Enumerated(EnumType.STRING)
	public YesNoType getReviewedLearnerRecords() {
		return _reviewedLearnerRecords;
	}

	/**
	 * @param reviewedLearnerRecords the reviewedLearnerRecords to set
	 */
	public void setReviewedLearnerRecords(final YesNoType reviewedLearnerRecords) {
		_reviewedLearnerRecords = reviewedLearnerRecords;
	}

	/**
	 * @return the reviewedAssetRecords
	 */
	@Column(name = "RVWD_ASST_REC")
	@Enumerated(EnumType.STRING)
	public YesNoType getReviewedAssetRecords() {
		return _reviewedAssetRecords;
	}

	/**
	 * @param reviewedAssetRecords the reviewedAssetRecords to set
	 */
	public void setReviewedAssetRecords(final YesNoType reviewedAssetRecords) {
		_reviewedAssetRecords = reviewedAssetRecords;
	}

	/**
	 * @return the reviewedComplianceRecords
	 */
	@Column(name = "RVWD_CMPL_REC")
	@Enumerated(EnumType.STRING)
	public YesNoType getReviewedComplianceRecords() {
		return _reviewedComplianceRecords;
	}

	/**
	 * @param reviewedComplianceRecords the reviewedComplianceRecords to set
	 */
	public void setReviewedComplianceRecords(final YesNoType reviewedComplianceRecords) {
		_reviewedComplianceRecords = reviewedComplianceRecords;
	}
	
	@Column(name = "STS_FLG", nullable = false)
	@Enumerated(EnumType.STRING)
	public StatusFlag getStatusFlag() {
		return _statusFlag;
	}

	public void setStatusFlag(final StatusFlag statusFlag) {
		_statusFlag = statusFlag;
	}
	
	@ManyToOne
	@JoinColumn(name = "USR_ID")
	public User getInvestigator() {
		return _investigator;
	}

	public void setInvestigator(final User investigator) {
		_investigator = investigator;
	}
	
	@Column(name = "INVST_STMT", length = 256)
	public String getInvestigatorStatement() {
		return _investigatorStatement;
	}

	public void setInvestigatorStatement(final String investigatorStatement) {
		_investigatorStatement = investigatorStatement;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link Investigation}.
	 */
	public final static class Builder {
		
		private Incident _incident;
		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Investigation.
		 * 
		 * @return new instance of Investigation
		 */
		public Investigation build() {
			return new Investigation(this);
		}
		
		public Builder setIncident(final Incident incident) {
			_incident = incident;
			return this;
		}

		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}
}
