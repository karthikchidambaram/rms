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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@JsonIgnoreProperties({ "claim" })
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
	private String _claimRequestedComments;
	private String _claimApprovedComments;
	private String _claimSettlementComments;
	private String _claimDeclinedComments;
	private String _claimReopenedComments;

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
		_claim = Objects.requireNonNull(builder._claim, "Claim object cannot be null when creating a claim history record.");
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
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIM_ID")
	@NotNull
	public Claim getClaim() {
		return _claim;
	}

	/**
	 * @param claim
	 *            the claim to set
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

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link ClaimHistory}.
	 */
	public final static class Builder {

		private Claim _claim;
		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of ClaimHistory.
		 * 
		 * @return new instance of ClaimHistory
		 */
		public ClaimHistory build() {
			return new ClaimHistory(this);
		}

		public Builder setClaim(final Claim claim) {
			_claim = claim;
			return this;
		}

		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}
}
