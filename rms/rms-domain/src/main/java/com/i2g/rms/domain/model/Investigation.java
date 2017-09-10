package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

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
	private InvestigationTeam _investigationTeam;
	@Transient
	private Set<InvestigationLeadDetails> _investigationTeamLeadDetails = new HashSet<InvestigationLeadDetails>(0);
		
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
	@Size(max = 1, message = "Security requested is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
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
	@Size(max = 1, message = "Training requested is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
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
	@Size(max = 1, message = "Reviewed investigation record(s) is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
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
	@Size(max = 1, message = "Reviewed CCTV footage(s) is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
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
	@Size(max = 1, message = "Reviewed picture(s) is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
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
	@Size(max = 1, message = "Reviewed witness statement is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
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
	@Size(max = 1, message = "Reviewed learner record(s) is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
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
	@Size(max = 1, message = "Reviewed asset record(s) is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
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
	@Size(max = 1, message = "Reviewed compliance record(s) is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
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

	/**
	 * @return the investigationTeam
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INVST_TEAM_ID")
	public InvestigationTeam getInvestigationTeam() {
		return _investigationTeam;
	}

	/**
	 * @param investigationTeam the investigationTeam to set
	 */
	public void setInvestigationTeam(final InvestigationTeam investigationTeam) {
		_investigationTeam = investigationTeam;
	}

	/**
	 * @return the investigationTeamLeadDetails
	 */
	@Transient
	public Set<InvestigationLeadDetails> getInvestigationTeamLeadDetails() {
		return constructInvestigationLeadDetailsFromUsers(getInvestigationTeam().getUsers());
	}

	/**
	 * @param investigationTeamLeadDetails the investigationTeamLeadDetails to set
	 */
	@Transient
	public void setInvestigationTeamLeadDetails(final Set<InvestigationLeadDetails> investigationTeamLeadDetails) {
		_investigationTeamLeadDetails = investigationTeamLeadDetails;
	}
	
	@Transient
	private Set<InvestigationLeadDetails> constructInvestigationLeadDetailsFromUsers(final Set<User> users) {
		Set<InvestigationLeadDetails> investigationLeadDetailsList = new HashSet<InvestigationLeadDetails>(0);
		if (users != null && !users.isEmpty()) {
			for (User user : users) {
				InvestigationLeadDetails investigationLeadDetails = new InvestigationLeadDetails();
				investigationLeadDetails.setInvestigationTeamLeadId(user.getLoginId());
				investigationLeadDetails.setInvestigationTeamLeadTitle(user.getTitle());
				investigationLeadDetails.setInvestigationTeamLeadFirstName(user.getFirstName());
				investigationLeadDetails.setInvestigationTeamLeadMiddleName(user.getMiddleName());
				investigationLeadDetails.setInvestigationTeamLeadLastName(user.getLastName());
				investigationLeadDetailsList.add(investigationLeadDetails);
			}			
		}
		return investigationLeadDetailsList;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link Investigation}.
	 */
	public final static class Builder {

		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Investigation.
		 * 
		 * @return new instance of Investigation
		 */
		public Investigation build() {
			return new Investigation(this);
		}

		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}
}
