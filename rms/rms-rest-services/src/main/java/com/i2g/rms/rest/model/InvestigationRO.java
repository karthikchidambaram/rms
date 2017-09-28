package com.i2g.rms.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.incident.IncidentRO;

/**
 * REST Object for Investigation.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvestigationRO extends AbstractEntityRO {

	private long id;
	private IncidentRO incident;
	private StatusFlagRO statusFlag;
	private YesNoTypeRO securityRequested;
	private YesNoTypeRO trainingRequested;
	private YesNoTypeRO reviewedInvestigationRecords;
	private YesNoTypeRO reviewedCCTV;
	private YesNoTypeRO reviewedPictures;
	private YesNoTypeRO reviewedWitnessStatement;
	private YesNoTypeRO reviewedLearnerRecords;
	private YesNoTypeRO reviewedAssetRecords;
	private YesNoTypeRO reviewedComplianceRecords;
	private UserRO investigator;
	private String investigatorStatement;
	
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

	public YesNoTypeRO getReviewedInvestigationRecords() {
		return reviewedInvestigationRecords;
	}

	public void setReviewedInvestigationRecords(final YesNoTypeRO reviewedInvestigationRecords) {
		this.reviewedInvestigationRecords = reviewedInvestigationRecords;
	}

	public YesNoTypeRO getReviewedCCTV() {
		return reviewedCCTV;
	}

	public void setReviewedCCTV(final YesNoTypeRO reviewedCCTV) {
		this.reviewedCCTV = reviewedCCTV;
	}

	public YesNoTypeRO getReviewedPictures() {
		return reviewedPictures;
	}

	public void setReviewedPictures(final YesNoTypeRO reviewedPictures) {
		this.reviewedPictures = reviewedPictures;
	}

	public YesNoTypeRO getReviewedWitnessStatement() {
		return reviewedWitnessStatement;
	}

	public void setReviewedWitnessStatement(final YesNoTypeRO reviewedWitnessStatement) {
		this.reviewedWitnessStatement = reviewedWitnessStatement;
	}

	public YesNoTypeRO getReviewedLearnerRecords() {
		return reviewedLearnerRecords;
	}

	public void setReviewedLearnerRecords(final YesNoTypeRO reviewedLearnerRecords) {
		this.reviewedLearnerRecords = reviewedLearnerRecords;
	}

	public YesNoTypeRO getReviewedAssetRecords() {
		return reviewedAssetRecords;
	}

	public void setReviewedAssetRecords(final YesNoTypeRO reviewedAssetRecords) {
		this.reviewedAssetRecords = reviewedAssetRecords;
	}

	public YesNoTypeRO getReviewedComplianceRecords() {
		return reviewedComplianceRecords;
	}

	public void setReviewedComplianceRecords(final YesNoTypeRO reviewedComplianceRecords) {
		this.reviewedComplianceRecords = reviewedComplianceRecords;
	}

	public UserRO getInvestigator() {
		return investigator;
	}

	public void setInvestigator(final UserRO investigator) {
		this.investigator = investigator;
	}

	public String getInvestigatorStatement() {
		return investigatorStatement;
	}

	public void setInvestigatorStatement(final String investigatorStatement) {
		this.investigatorStatement = investigatorStatement;
	}	
}