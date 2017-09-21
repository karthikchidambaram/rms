package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
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
	private ClaimHistory _claimHistory;
	private User claimHandler;

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
		_incident = Objects.requireNonNull(builder._incident,
				"Incident object cannot be null when creating a claim record.");
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
	 * @return the claimHistory
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "claim")
	public ClaimHistory getClaimHistory() {
		return _claimHistory;
	}

	/**
	 * @param claimHistory
	 *            the claimHistory to set
	 */
	public void setClaimHistory(final ClaimHistory claimHistory) {
		_claimHistory = claimHistory;
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
		return claimHandler;
	}

	public void setClaimHandler(final User claimHandler) {
		this.claimHandler = claimHandler;
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
