package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	private String _claimHandlerName;
	private Set<ClaimHistory> _claimHistory = new HashSet<ClaimHistory>(0);
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Claim() {
	}
	
	/**
	 * Creates a new immutable instance of {@link Claim} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private Claim(final Builder builder) {
		_id = Objects.requireNonNull(builder._id, "Claim Id cannot be null.");		
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");
	}
	
	/**
	 * Return the Claim primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false)
	@GeneratedValue(generator = "sequence")
	@SequenceGenerator(name = "sequence", sequenceName = "RMS_CLIM_ID_SEQ")
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
	 * @return the claimStatus
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIM_STS_CDE")
	@Size(min = 1, max = 16, message = "Claim status code must be between {min} and {max} characters")
	public ClaimStatus getClaimStatus() {
		return _claimStatus;
	}

	/**
	 * @param claimStatus the claimStatus to set
	 */
	public void setClaimStatus(final ClaimStatus claimStatus) {
		_claimStatus = claimStatus;
	}

	/**
	 * @return the claimType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIM_TYP_CDE")
	@Size(min = 1, max = 16, message = "Claim type code must be between {min} and {max} characters")
	public ClaimType getClaimType() {
		return _claimType;
	}

	/**
	 * @param claimType the claimType to set
	 */
	public void setClaimType(final ClaimType claimType) {
		_claimType = claimType;
	}

	/**
	 * @return the claimRequestRegistrationType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLREQ_REG_TYP_CDE")
	@Size(min = 1, max = 16, message = "Claim request registration type code must be between {min} and {max} characters")
	public ClaimRequestRegistrationType getClaimRequestRegistrationType() {
		return _claimRequestRegistrationType;
	}

	/**
	 * @param claimRequestRegistrationType the claimRequestRegistrationType to set
	 */
	public void setClaimRequestRegistrationType(final ClaimRequestRegistrationType claimRequestRegistrationType) {
		_claimRequestRegistrationType = claimRequestRegistrationType;
	}

	/**
	 * @return the claimantName
	 */
	@Column(name = "CLIMNT_NAM")
	@Size(min = 1, max = 128, message = "Claimant name must be between {min} and {max} characters")
	public String getClaimantName() {
		return _claimantName;
	}

	/**
	 * @param claimantName the claimantName to set
	 */
	public void setClaimantName(final String claimantName) {
		_claimantName = claimantName;
	}

	/**
	 * @return the claimId
	 */
	@Column(name = "CLIMNT_ID")
	@Size(min = 1, max = 32, message = "Claimant ID must be between {min} and {max} characters")
	public String getClaimId() {
		return _claimId;
	}

	/**
	 * @param claimId the claimId to set
	 */
	public void setClaimId(final String claimId) {
		_claimId = claimId;
	}

	/**
	 * @return the insuredName
	 */
	@Column(name = "INSRD_NAM")
	@Size(min = 1, max = 32, message = "Insured name must be between {min} and {max} characters")
	public String getInsuredName() {
		return _insuredName;
	}

	/**
	 * @param insuredName the insuredName to set
	 */
	public void setInsuredName(final String insuredName) {
		_insuredName = insuredName;
	}

	/**
	 * @return the policyNumber
	 */
	@Column(name = "POL_NO")
	@Size(min = 1, max = 32, message = "Policy number must be between {min} and {max} characters")
	public String getPolicyNumber() {
		return _policyNumber;
	}

	/**
	 * @param policyNumber the policyNumber to set
	 */
	public void setPolicyNumber(final String policyNumber) {
		_policyNumber = policyNumber;
	}

	/**
	 * @return the policyHolderName
	 */
	@Column(name = "POL_HLDR_NAM")
	@Size(min = 1, max = 128, message = "Policy holder name must be between {min} and {max} characters")
	public String getPolicyHolderName() {
		return _policyHolderName;
	}

	/**
	 * @param policyHolderName the policyHolderName to set
	 */
	public void setPolicyHolderName(final String policyHolderName) {
		_policyHolderName = policyHolderName;
	}

	/**
	 * @return the policyType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POL_TYP_CDE")
	@Size(min = 1, max = 16, message = "Policy type code must be between {min} and {max} characters")
	public PolicyType getPolicyType() {
		return _policyType;
	}

	/**
	 * @param policyType the policyType to set
	 */
	public void setPolicyType(final PolicyType policyType) {
		_policyType = policyType;
	}

	/**
	 * @return the cliamHandlerName
	 */
	@Column(name = "CLIM_HNDLR_NAM")
	@Size(min = 1, max = 128, message = "Claim handler name must be between {min} and {max} characters")
	public String getClaimHandlerName() {
		return _claimHandlerName;
	}

	/**
	 * @param cliamHandlerName the cliamHandlerName to set
	 */
	public void setClaimHandlerName(final String claimHandlerName) {
		_claimHandlerName = claimHandlerName;
	}
	
	/**
	 * @return the claimHistory
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "claim")
	@Fetch(FetchMode.SUBSELECT)
	@JsonIgnoreProperties("claim")
	public Set<ClaimHistory> getClaimHistory() {
		return _claimHistory;
	}

	/**
	 * @param claimHistory the claimHistory to set
	 */
	public void setClaimHistory(final Set<ClaimHistory> claimHistory) {
		_claimHistory = claimHistory;
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

	@Override
	public int hashCode() {
		return Objects.hash(_id, _statusFlag);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Claim) {
			final Claim other = (Claim) obj;
			return Objects.equals(_id, other._id) 
					&& Objects.equals(_statusFlag, other._statusFlag);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Id: " + _id + ", " 
		+ "Claimant Name: " + _claimantName + ", "		
		+ "Status Flag: " + _statusFlag;
	}
	
	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link Claim}.
	 */
	public final static class Builder {

		private Long _id;
		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Claim.
		 * 
		 * @return new instance of Claim
		 */
		public Claim build() {
			return new Claim(this);
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
