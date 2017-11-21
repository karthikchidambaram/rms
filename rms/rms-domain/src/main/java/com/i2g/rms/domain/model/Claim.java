package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.tablemaintenance.ClaimRequestRegistrationType;
import com.i2g.rms.domain.model.tablemaintenance.ClaimStatus;
import com.i2g.rms.domain.model.tablemaintenance.ClaimType;
import com.i2g.rms.domain.model.tablemaintenance.PolicyType;

/**
 * Entity representation of Claim.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_CLIM")
@JsonIgnoreProperties({ "incident" })
public class Claim extends AbstractDataModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID */
	private long _id;
	private Incident _incident;
	private StatusFlag _statusFlag;
	private ClaimStatus _claimStatus;
	private ClaimType _claimType;
	private ClaimRequestRegistrationType _claimRequestRegistrationType;
	private String _claimantName;
	private String _claimId;
	private String _insuredName;
	private String _policyNumber;
	private String _policyHolderName;
	private PolicyType _policyType;
	private YesNoType _securityRequested;
	private YesNoType _trainingRequested;
	private User _claimHandler;
	private BigDecimal _claimRequestedAmount;
	private LocalDate _claimRequestedDate;
	private String _claimRequestedBy;
	private BigDecimal _claimApprovedAmount;
	private LocalDate _claimApprovedDate;
	private String _claimApprovedBy;
	private BigDecimal _claimSettlementAmount;
	private LocalDate _claimSettlementDate;
	private String _claimSettlementBy;
	private LocalDate _claimDeclinedDate;
	private String _claimDeclinedBy;
	private LocalDate _claimReopenedDate;
	private String _claimReopenedBy;
	private String _claimRequestedComments;
	private String _claimApprovedComments;
	private String _claimSettlementComments;
	private String _claimDeclinedComments;
	private String _claimReopenedComments;
	private String _claimTypeOther;
	private String _claimRequestRegistrationTypeOther;
	private String _policyTypeOther;

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Claim() {
	}

	/**
	 * Creates a new immutable instance of {@link Claim} from the specified
	 * {@code builder}.
	 * 
	 * @param builder
	 */
	private Claim(final Builder builder) {
		_incident = Objects.requireNonNull(builder._incident, "Incident object cannot be null when creating a claim record.");
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");
	}

	/**
	 * Return the Claim primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_clim_id_seq")
	@SequenceGenerator(name = "rms_clim_id_seq", sequenceName = "RMS_CLIM_ID_SEQ", allocationSize = 1)
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
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INC_ID")
	@NotNull
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
	 * @return the securityRequested
	 */
	@Column(name = "SEC_REQ")
	@Enumerated(EnumType.STRING)
	public YesNoType getSecurityRequested() {
		return _securityRequested;
	}

	/**
	 * @param securityRequested
	 *            the securityRequested to set
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
	 * @param trainingRequested
	 *            the trainingRequested to set
	 */
	public void setTrainingRequested(final YesNoType trainingRequested) {
		_trainingRequested = trainingRequested;
	}

	/**
	 * @return the claimStatus
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIM_STS_CDE")
	public ClaimStatus getClaimStatus() {
		return _claimStatus;
	}

	/**
	 * @param claimStatus
	 *            the claimStatus to set
	 */
	public void setClaimStatus(final ClaimStatus claimStatus) {
		_claimStatus = claimStatus;
	}

	/**
	 * @return the claimType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIM_TYP_CDE")
	public ClaimType getClaimType() {
		return _claimType;
	}

	/**
	 * @param claimType
	 *            the claimType to set
	 */
	public void setClaimType(final ClaimType claimType) {
		_claimType = claimType;
	}

	/**
	 * @return the claimRequestRegistrationType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLREQ_REG_TYP_CDE")
	public ClaimRequestRegistrationType getClaimRequestRegistrationType() {
		return _claimRequestRegistrationType;
	}

	/**
	 * @param claimRequestRegistrationType
	 *            the claimRequestRegistrationType to set
	 */
	public void setClaimRequestRegistrationType(final ClaimRequestRegistrationType claimRequestRegistrationType) {
		_claimRequestRegistrationType = claimRequestRegistrationType;
	}

	/**
	 * @return the claimantName
	 */
	@Column(name = "CLIMNT_NAM", length = 128)
	public String getClaimantName() {
		return _claimantName;
	}

	/**
	 * @param claimantName
	 *            the claimantName to set
	 */
	public void setClaimantName(final String claimantName) {
		_claimantName = claimantName;
	}

	/**
	 * @return the claimId
	 */
	@Column(name = "CLIM_ID", length = 32)
	public String getClaimId() {
		return _claimId;
	}

	/**
	 * @param claimId
	 *            the claimId to set
	 */
	public void setClaimId(final String claimId) {
		_claimId = claimId;
	}

	/**
	 * @return the insuredName
	 */
	@Column(name = "INSRD_NAM", length = 32)
	public String getInsuredName() {
		return _insuredName;
	}

	/**
	 * @param insuredName
	 *            the insuredName to set
	 */
	public void setInsuredName(final String insuredName) {
		_insuredName = insuredName;
	}

	/**
	 * @return the policyNumber
	 */
	@Column(name = "POL_NO", length = 32)
	public String getPolicyNumber() {
		return _policyNumber;
	}

	/**
	 * @param policyNumber
	 *            the policyNumber to set
	 */
	public void setPolicyNumber(final String policyNumber) {
		_policyNumber = policyNumber;
	}

	/**
	 * @return the policyHolderName
	 */
	@Column(name = "POL_HLDR_NAM", length = 128)
	public String getPolicyHolderName() {
		return _policyHolderName;
	}

	/**
	 * @param policyHolderName
	 *            the policyHolderName to set
	 */
	public void setPolicyHolderName(final String policyHolderName) {
		_policyHolderName = policyHolderName;
	}

	/**
	 * @return the policyType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POL_TYP_CDE")
	public PolicyType getPolicyType() {
		return _policyType;
	}

	/**
	 * @param policyType
	 *            the policyType to set
	 */
	public void setPolicyType(final PolicyType policyType) {
		_policyType = policyType;
	}

	/**
	 * @return the statusFlag
	 */
	@Column(name = "STS_FLG", nullable = false)
	@NotNull
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USR_ID")
	public User getClaimHandler() {
		return _claimHandler;
	}

	public void setClaimHandler(final User claimHandler) {
		_claimHandler = claimHandler;
	}
	
	/**
	 * @return the claimRequestedAmount
	 */
	@Column(name = "CLIM_REQ_AMT")
	public BigDecimal getClaimRequestedAmount() {
		return _claimRequestedAmount;
	}

	/**
	 * @param claimRequestedAmount
	 *            the claimRequestedAmount to set
	 */
	public void setClaimRequestedAmount(final BigDecimal claimRequestedAmount) {
		_claimRequestedAmount = claimRequestedAmount;
	}

	/**
	 * @return the claimRequestedDate
	 */
	@Column(name = "CLIM_REQ_DT")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateType")
	public LocalDate getClaimRequestedDate() {
		return _claimRequestedDate;
	}

	/**
	 * @param claimRequestedDate
	 *            the claimRequestedDate to set
	 */
	public void setClaimRequestedDate(final LocalDate claimRequestedDate) {
		_claimRequestedDate = claimRequestedDate;
	}

	/**
	 * @return the claimRequestedBy
	 */
	@Column(name = "CLIM_REQ_BY", length = 128)
	public String getClaimRequestedBy() {
		return _claimRequestedBy;
	}

	/**
	 * @param claimRequestedBy
	 *            the claimRequestedBy to set
	 */
	public void setClaimRequestedBy(final String claimRequestedBy) {
		_claimRequestedBy = claimRequestedBy;
	}

	/**
	 * @return the claimApprovedAmount
	 */
	@Column(name = "CLIM_APR_AMT")
	public BigDecimal getClaimApprovedAmount() {
		return _claimApprovedAmount;
	}

	/**
	 * @param claimApprovedAmount
	 *            the claimApprovedAmount to set
	 */
	public void setClaimApprovedAmount(final BigDecimal claimApprovedAmount) {
		_claimApprovedAmount = claimApprovedAmount;
	}

	/**
	 * @return the claimApprovedDate
	 */
	@Column(name = "CLIM_APR_DT")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateType")
	public LocalDate getClaimApprovedDate() {
		return _claimApprovedDate;
	}

	/**
	 * @param claimApprovedDate
	 *            the claimApprovedDate to set
	 */
	public void setClaimApprovedDate(final LocalDate claimApprovedDate) {
		_claimApprovedDate = claimApprovedDate;
	}

	/**
	 * @return the claimApprovedBy
	 */
	@Column(name = "CLIM_APR_BY", length = 128)
	public String getClaimApprovedBy() {
		return _claimApprovedBy;
	}

	/**
	 * @param claimApprovedBy
	 *            the claimApprovedBy to set
	 */
	public void setClaimApprovedBy(final String claimApprovedBy) {
		_claimApprovedBy = claimApprovedBy;
	}

	/**
	 * @return the claimSettlementAmount
	 */
	@Column(name = "CLIM_STLMT_AMT")
	public BigDecimal getClaimSettlementAmount() {
		return _claimSettlementAmount;
	}

	/**
	 * @param claimSettlementAmount
	 *            the claimSettlementAmount to set
	 */
	public void setClaimSettlementAmount(final BigDecimal claimSettlementAmount) {
		_claimSettlementAmount = claimSettlementAmount;
	}

	/**
	 * @return the claimSettlementDate
	 */
	@Column(name = "CLIM_STLMT_DT")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateType")
	public LocalDate getClaimSettlementDate() {
		return _claimSettlementDate;
	}

	/**
	 * @param claimSettlementDate
	 *            the claimSettlementDate to set
	 */
	public void setClaimSettlementDate(final LocalDate claimSettlementDate) {
		_claimSettlementDate = claimSettlementDate;
	}

	/**
	 * @return the claimSettlementBy
	 */
	@Column(name = "CLIM_STLMT_BY", length = 128)
	public String getClaimSettlementBy() {
		return _claimSettlementBy;
	}

	/**
	 * @param claimSettlementBy
	 *            the claimSettlementBy to set
	 */
	public void setClaimSettlementBy(final String claimSettlementBy) {
		_claimSettlementBy = claimSettlementBy;
	}

	/**
	 * @return the claimDeclinedDate
	 */
	@Column(name = "CLIM_DEC_DT")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateType")
	public LocalDate getClaimDeclinedDate() {
		return _claimDeclinedDate;
	}

	/**
	 * @param claimDeclinedDate
	 *            the claimDeclinedDate to set
	 */
	public void setClaimDeclinedDate(final LocalDate claimDeclinedDate) {
		_claimDeclinedDate = claimDeclinedDate;
	}

	/**
	 * @return the claimDeclinedBy
	 */
	@Column(name = "CLIM_DEC_BY", length = 128)
	public String getClaimDeclinedBy() {
		return _claimDeclinedBy;
	}

	/**
	 * @param claimDeclinedBy
	 *            the claimDeclinedBy to set
	 */
	public void setClaimDeclinedBy(final String claimDeclinedBy) {
		_claimDeclinedBy = claimDeclinedBy;
	}

	/**
	 * @return the claimReopenedDate
	 */
	@Column(name = "CLIM_REOPN_DT")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateType")
	public LocalDate getClaimReopenedDate() {
		return _claimReopenedDate;
	}

	/**
	 * @param claimReopenedDate
	 *            the claimReopenedDate to set
	 */
	public void setClaimReopenedDate(final LocalDate claimReopenedDate) {
		_claimReopenedDate = claimReopenedDate;
	}

	/**
	 * @return the claimReopenedBy
	 */
	@Column(name = "CLIM_REOPN_BY", length = 128)
	public String getClaimReopenedBy() {
		return _claimReopenedBy;
	}

	/**
	 * @param claimReopenedBy
	 *            the claimReopenedBy to set
	 */
	public void setClaimReopenedBy(final String claimReopenedBy) {
		_claimReopenedBy = claimReopenedBy;
	}

	/**
	 * @return the claimRequestedComments
	 */
	@Column(name = "CLIM_REQ_CMNTS", length = 256)
	public String getClaimRequestedComments() {
		return _claimRequestedComments;
	}

	/**
	 * @param claimRequestedComments
	 *            the claimRequestedComments to set
	 */
	public void setClaimRequestedComments(final String claimRequestedComments) {
		_claimRequestedComments = claimRequestedComments;
	}
	
	@Column(name = "CLIM_APR_CMNTS", length = 256)
	public String getClaimApprovedComments() {
		return _claimApprovedComments;
	}

	public void setClaimApprovedComments(final String claimApprovedComments) {
		_claimApprovedComments = claimApprovedComments;
	}
	
	@Column(name = "CLIM_STLMT_CMNTS", length = 256)
	public String getClaimSettlementComments() {
		return _claimSettlementComments;
	}

	public void setClaimSettlementComments(final String claimSettlementComments) {
		_claimSettlementComments = claimSettlementComments;
	}
	
	@Column(name = "CLIM_DEC_CMNTS", length = 256)
	public String getClaimDeclinedComments() {
		return _claimDeclinedComments;
	}

	public void setClaimDeclinedComments(final String claimDeclinedComments) {
		_claimDeclinedComments = claimDeclinedComments;
	}
	
	@Column(name = "CLIM_REOPN_CMNTS", length = 256)
	public String getClaimReopenedComments() {
		return _claimReopenedComments;
	}

	public void setClaimReopenedComments(final String claimReopenedComments) {
		_claimReopenedComments = claimReopenedComments;
	}	

	@Column(name = "CLIM_TYP_OTHR", length = 32)	
	public String getClaimTypeOther() {
		return _claimTypeOther;
	}

	public void setClaimTypeOther(final String claimTypeOther) {
		_claimTypeOther = claimTypeOther;
	}
	
	@Column(name = "CLREQ_REG_TYP_OTHR", length = 32)
	public String getClaimRequestRegistrationTypeOther() {
		return _claimRequestRegistrationTypeOther;
	}

	public void setClaimRequestRegistrationTypeOther(final String claimRequestRegistrationTypeOther) {
		_claimRequestRegistrationTypeOther = claimRequestRegistrationTypeOther;
	}

	@Column(name = "POL_TYP_OTHR", length = 32)
	public String getPolicyTypeOther() {
		return _policyTypeOther;
	}

	public void setPolicyTypeOther(final String policyTypeOther) {
		_policyTypeOther = policyTypeOther;
	}

	/**
	 * Builder pattern for constructing immutable instances of {@link Claim}.
	 */
	public final static class Builder {

		private Incident _incident;
		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Claim.
		 * 
		 * @return new instance of Claim
		 */
		public Claim build() {
			return new Claim(this);
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
