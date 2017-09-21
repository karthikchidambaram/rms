package com.i2g.rms.rest.model;

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
	private ClaimHistoryRO claimHistory;
	private UserRO claimHandler;

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

	public ClaimHistoryRO getClaimHistory() {
		return claimHistory;
	}

	public void setClaimHistory(final ClaimHistoryRO claimHistory) {
		this.claimHistory = claimHistory;
	}

	public UserRO getClaimHandler() {
		return claimHandler;
	}

	public void setClaimHandler(final UserRO claimHandler) {
		this.claimHandler = claimHandler;
	}
}
