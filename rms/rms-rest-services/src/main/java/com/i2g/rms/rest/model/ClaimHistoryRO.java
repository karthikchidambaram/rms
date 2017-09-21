package com.i2g.rms.rest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * REST Object for Claim History RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimHistoryRO extends AbstractEntityRO {

	private long id;
	private ClaimRO claim;
	private StatusFlagRO statusFlag;
	private BigDecimal claimRequestedAmount;
	private LocalDate claimRequestedDate;
	private String claimRequestedBy;
	private BigDecimal claimApprovedAmount;
	private LocalDate claimApprovedDate;
	private String claimApprovedBy;
	private BigDecimal claimSettlementAmount;
	private LocalDate claimSettlementDate;
	private String claimSettlementBy;
	private LocalDate claimDeclinedDate;
	private String claimDeclinedBy;
	private LocalDate claimReopenedDate;
	private String claimReopenedBy;
	private String claimRequestedComments;
	private String claimApprovedComments;
	private String claimSettlementComments;
	private String claimDeclinedComments;
	private String claimReopenedComments;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public ClaimRO getClaim() {
		return claim;
	}

	public void setClaim(final ClaimRO claim) {
		this.claim = claim;
	}

	public StatusFlagRO getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(final StatusFlagRO statusFlag) {
		this.statusFlag = statusFlag;
	}

	public BigDecimal getClaimRequestedAmount() {
		return claimRequestedAmount;
	}

	public void setClaimRequestedAmount(final BigDecimal claimRequestedAmount) {
		this.claimRequestedAmount = claimRequestedAmount;
	}

	public LocalDate getClaimRequestedDate() {
		return claimRequestedDate;
	}

	public void setClaimRequestedDate(final LocalDate claimRequestedDate) {
		this.claimRequestedDate = claimRequestedDate;
	}

	public String getClaimRequestedBy() {
		return claimRequestedBy;
	}

	public void setClaimRequestedBy(final String claimRequestedBy) {
		this.claimRequestedBy = claimRequestedBy;
	}

	public BigDecimal getClaimApprovedAmount() {
		return claimApprovedAmount;
	}

	public void setClaimApprovedAmount(final BigDecimal claimApprovedAmount) {
		this.claimApprovedAmount = claimApprovedAmount;
	}

	public LocalDate getClaimApprovedDate() {
		return claimApprovedDate;
	}

	public void setClaimApprovedDate(final LocalDate claimApprovedDate) {
		this.claimApprovedDate = claimApprovedDate;
	}

	public String getClaimApprovedBy() {
		return claimApprovedBy;
	}

	public void setClaimApprovedBy(final String claimApprovedBy) {
		this.claimApprovedBy = claimApprovedBy;
	}

	public BigDecimal getClaimSettlementAmount() {
		return claimSettlementAmount;
	}

	public void setClaimSettlementAmount(final BigDecimal claimSettlementAmount) {
		this.claimSettlementAmount = claimSettlementAmount;
	}

	public LocalDate getClaimSettlementDate() {
		return claimSettlementDate;
	}

	public void setClaimSettlementDate(final LocalDate claimSettlementDate) {
		this.claimSettlementDate = claimSettlementDate;
	}

	public String getClaimSettlementBy() {
		return claimSettlementBy;
	}

	public void setClaimSettlementBy(final String claimSettlementBy) {
		this.claimSettlementBy = claimSettlementBy;
	}

	public LocalDate getClaimDeclinedDate() {
		return claimDeclinedDate;
	}

	public void setClaimDeclinedDate(final LocalDate claimDeclinedDate) {
		this.claimDeclinedDate = claimDeclinedDate;
	}

	public String getClaimDeclinedBy() {
		return claimDeclinedBy;
	}

	public void setClaimDeclinedBy(final String claimDeclinedBy) {
		this.claimDeclinedBy = claimDeclinedBy;
	}

	public LocalDate getClaimReopenedDate() {
		return claimReopenedDate;
	}

	public void setClaimReopenedDate(final LocalDate claimReopenedDate) {
		this.claimReopenedDate = claimReopenedDate;
	}

	public String getClaimReopenedBy() {
		return claimReopenedBy;
	}

	public void setClaimReopenedBy(final String claimReopenedBy) {
		this.claimReopenedBy = claimReopenedBy;
	}

	public String getClaimRequestedComments() {
		return claimRequestedComments;
	}

	public void setClaimRequestedComments(final String claimRequestedComments) {
		this.claimRequestedComments = claimRequestedComments;
	}

	public String getClaimApprovedComments() {
		return claimApprovedComments;
	}

	public void setClaimApprovedComments(final String claimApprovedComments) {
		this.claimApprovedComments = claimApprovedComments;
	}

	public String getClaimSettlementComments() {
		return claimSettlementComments;
	}

	public void setClaimSettlementComments(final String claimSettlementComments) {
		this.claimSettlementComments = claimSettlementComments;
	}

	public String getClaimDeclinedComments() {
		return claimDeclinedComments;
	}

	public void setClaimDeclinedComments(final String claimDeclinedComments) {
		this.claimDeclinedComments = claimDeclinedComments;
	}

	public String getClaimReopenedComments() {
		return claimReopenedComments;
	}

	public void setClaimReopenedComments(final String claimReopenedComments) {
		this.claimReopenedComments = claimReopenedComments;
	}
}
