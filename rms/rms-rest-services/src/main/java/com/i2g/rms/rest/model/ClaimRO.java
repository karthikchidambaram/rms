package com.i2g.rms.rest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimRequestRegistrationTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimStatusRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.PolicyTypeRO;

/**
 * REST Object for Claim RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimRO extends AbstractEntityRO {

	private long id;
	private IncidentRO incident;
	private StatusFlagRO statusFlag;
	private ClaimStatusRO claimStatus;
	private ClaimTypeRO claimType;
	private ClaimRequestRegistrationTypeRO claimRequestRegistrationType;
	private String claimantName;
	private String claimId;
	private String insuredName;
	private String policyNumber;
	private String policyHolderName;
	private PolicyTypeRO policyType;
	private YesNoTypeRO securityRequested;
	private YesNoTypeRO trainingRequested;
	private UserRO claimHandler;
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

	public IncidentRO getIncident() {
		return incident;
	}

	public void setIncident(final IncidentRO incident) {
		this.incident = incident;
	}

	public StatusFlagRO getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(final StatusFlagRO statusFlag) {
		this.statusFlag = statusFlag;
	}

	public ClaimStatusRO getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(final ClaimStatusRO claimStatus) {
		this.claimStatus = claimStatus;
	}

	public ClaimTypeRO getClaimType() {
		return claimType;
	}

	public void setClaimType(final ClaimTypeRO claimType) {
		this.claimType = claimType;
	}

	public ClaimRequestRegistrationTypeRO getClaimRequestRegistrationType() {
		return claimRequestRegistrationType;
	}

	public void setClaimRequestRegistrationType(final ClaimRequestRegistrationTypeRO claimRequestRegistrationType) {
		this.claimRequestRegistrationType = claimRequestRegistrationType;
	}

	public String getClaimantName() {
		return claimantName;
	}

	public void setClaimantName(final String claimantName) {
		this.claimantName = claimantName;
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(final String claimId) {
		this.claimId = claimId;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(final String insuredName) {
		this.insuredName = insuredName;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(final String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyHolderName() {
		return policyHolderName;
	}

	public void setPolicyHolderName(final String policyHolderName) {
		this.policyHolderName = policyHolderName;
	}

	public PolicyTypeRO getPolicyType() {
		return policyType;
	}

	public void setPolicyType(final PolicyTypeRO policyType) {
		this.policyType = policyType;
	}

	public YesNoTypeRO getSecurityRequested() {
		return securityRequested;
	}

	public void setSecurityRequested(final YesNoTypeRO securityRequested) {
		this.securityRequested = securityRequested;
	}

	public YesNoTypeRO getTrainingRequested() {
		return trainingRequested;
	}

	public void setTrainingRequested(final YesNoTypeRO trainingRequested) {
		this.trainingRequested = trainingRequested;
	}

	public UserRO getClaimHandler() {
		return claimHandler;
	}

	public void setClaimHandler(final UserRO claimHandler) {
		this.claimHandler = claimHandler;
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
