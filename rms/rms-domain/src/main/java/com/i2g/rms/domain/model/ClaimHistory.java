package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity representation of Claim History.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_CLIM_HSTRY")
public class ClaimHistory extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Primary surrogate key ID */
	private long _id;
	private Claim _claim;
	private StatusFlag _statusFlag;	
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
	private String _comments;
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected ClaimHistory() {
	}
	
	/**
	 * Creates a new immutable instance of {@link ClaimHistory} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private ClaimHistory(final Builder builder) {
		_id = Objects.requireNonNull(builder._id, "Claim history Id cannot be null.");		
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");
	}
	
	/**
	 * Return the Claim primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_clim_hstry_id_seq")
	@SequenceGenerator(name = "rms_clim_hstry_id_seq", sequenceName = "RMS_CLIM_HSTRY_ID_SEQ", allocationSize = 1)
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
	 * @return the claim
	 */
	@ManyToOne
	@JoinColumn(name = "CLIM_ID")
	@JsonIgnoreProperties("claimHistory")
	public Claim getClaim() {
		return _claim;
	}

	/**
	 * @param claim the claim to set
	 */
	public void setClaim(final Claim claim) {
		_claim = claim;
	}
	
	/**
	 * @return the claimRequestedAmount
	 */
	@Column(name = "CLIM_REQ_AMT")
	public BigDecimal getClaimRequestedAmount() {
		return _claimRequestedAmount;
	}

	/**
	 * @param claimRequestedAmount the claimRequestedAmount to set
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
	 * @param claimRequestedDate the claimRequestedDate to set
	 */
	public void setClaimRequestedDate(final LocalDate claimRequestedDate) {
		_claimRequestedDate = claimRequestedDate;
	}

	/**
	 * @return the claimRequestedBy
	 */
	@Column(name = "CLIM_REQ_BY")
	@Size(min = 1, max = 128, message = "Claim requested by (name) must be between {min} and {max} characters")
	public String getClaimRequestedBy() {
		return _claimRequestedBy;
	}

	/**
	 * @param claimRequestedBy the claimRequestedBy to set
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
	 * @param claimApprovedAmount the claimApprovedAmount to set
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
	 * @param claimApprovedDate the claimApprovedDate to set
	 */
	public void setClaimApprovedDate(final LocalDate claimApprovedDate) {
		_claimApprovedDate = claimApprovedDate;
	}

	/**
	 * @return the claimApprovedBy
	 */
	@Column(name = "CLIM_APR_BY")
	@Size(min = 1, max = 128, message = "Claim approved by (name) must be between {min} and {max} characters")
	public String getClaimApprovedBy() {
		return _claimApprovedBy;
	}

	/**
	 * @param claimApprovedBy the claimApprovedBy to set
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
	 * @param claimSettlementAmount the claimSettlementAmount to set
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
	 * @param claimSettlementDate the claimSettlementDate to set
	 */
	public void setClaimSettlementDate(final LocalDate claimSettlementDate) {
		_claimSettlementDate = claimSettlementDate;
	}

	/**
	 * @return the claimSettlementBy
	 */
	@Column(name = "CLIM_STLMT_BY")
	@Size(min = 1, max = 128, message = "Claim settlement by (name) must be between {min} and {max} characters")
	public String getClaimSettlementBy() {
		return _claimSettlementBy;
	}

	/**
	 * @param claimSettlementBy the claimSettlementBy to set
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
	 * @param claimDeclinedDate the claimDeclinedDate to set
	 */
	public void setClaimDeclinedDate(final LocalDate claimDeclinedDate) {
		_claimDeclinedDate = claimDeclinedDate;
	}

	/**
	 * @return the claimDeclinedBy
	 */
	@Column(name = "CLIM_DEC_BY")
	@Size(min = 1, max = 128, message = "Claim declined by (name) must be between {min} and {max} characters")
	public String getClaimDeclinedBy() {
		return _claimDeclinedBy;
	}

	/**
	 * @param claimDeclinedBy the claimDeclinedBy to set
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
	 * @param claimReopenedDate the claimReopenedDate to set
	 */
	public void setClaimReopenedDate(final LocalDate claimReopenedDate) {
		_claimReopenedDate = claimReopenedDate;
	}

	/**
	 * @return the claimReopenedBy
	 */
	@Column(name = "CLIM_REOPN_BY")
	@Size(min = 1, max = 128, message = "Claim re-opened by (name) must be between {min} and {max} characters")
	public String getClaimReopenedBy() {
		return _claimReopenedBy;
	}

	/**
	 * @param claimReopenedBy the claimReopenedBy to set
	 */
	public void setClaimReopenedBy(final String claimReopenedBy) {
		_claimReopenedBy = claimReopenedBy;
	}
	
	/**
	 * @return the comments
	 */
	@Column(name = "COMNTS")
	@Size(min = 1, max = 256, message = "Comments must be between {min} and {max} characters")
	public String getComments() {
		return _comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(final String comments) {
		_comments = comments;
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
		} else if (obj instanceof ClaimHistory) {
			final ClaimHistory other = (ClaimHistory) obj;
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
	 * {@link ClaimHistory}.
	 */
	public final static class Builder {

		private Long _id;
		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of ClaimHistory.
		 * 
		 * @return new instance of ClaimHistory
		 */
		public ClaimHistory build() {
			return new ClaimHistory(this);
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
