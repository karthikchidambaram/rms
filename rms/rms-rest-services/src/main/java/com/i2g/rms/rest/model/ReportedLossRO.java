package com.i2g.rms.rest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.tablemaintenance.ExternalAgencyRO;
import com.i2g.rms.rest.model.tablemaintenance.LossTypeRO;

/**
 * REST Object for Reported Loss RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportedLossRO extends AbstractEntityRO {

	private long id;
	private IncidentRO incident;
	private StatusFlagRO statusFlag;
	private LossTypeRO lossType;
	private BigDecimal lossValue;
	private YesNoTypeRO externalAgencyContacted;
	private ExternalAgencyRO externalAgency;
	private LocalDateTime dateTimeContacted;
	private BigDecimal costEstimation;
	private String lossTypeOther;
	private String externalAgencyTypeOther;

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

	public LossTypeRO getLossType() {
		return lossType;
	}

	public void setLossType(final LossTypeRO lossType) {
		this.lossType = lossType;
	}

	public BigDecimal getLossValue() {
		return lossValue;
	}

	public void setLossValue(final BigDecimal lossValue) {
		this.lossValue = lossValue;
	}

	public YesNoTypeRO getExternalAgencyContacted() {
		return externalAgencyContacted;
	}

	public void setExternalAgencyContacted(final YesNoTypeRO externalAgencyContacted) {
		this.externalAgencyContacted = externalAgencyContacted;
	}

	public ExternalAgencyRO getExternalAgency() {
		return externalAgency;
	}

	public void setExternalAgency(final ExternalAgencyRO externalAgency) {
		this.externalAgency = externalAgency;
	}

	public LocalDateTime getDateTimeContacted() {
		return dateTimeContacted;
	}

	public void setDateTimeContacted(final LocalDateTime dateTimeContacted) {
		this.dateTimeContacted = dateTimeContacted;
	}

	public BigDecimal getCostEstimation() {
		return costEstimation;
	}

	public void setCostEstimation(final BigDecimal costEstimation) {
		this.costEstimation = costEstimation;
	}

	/**
	 * @return the lossTypeOther
	 */
	public String getLossTypeOther() {
		return lossTypeOther;
	}

	/**
	 * @param lossTypeOther
	 *            the lossTypeOther to set
	 */
	public void setLossTypeOther(final String lossTypeOther) {
		this.lossTypeOther = lossTypeOther;
	}

	/**
	 * @return the externalAgencyTypeOther
	 */
	public String getExternalAgencyTypeOther() {
		return externalAgencyTypeOther;
	}

	/**
	 * @param externalAgencyTypeOther
	 *            the externalAgencyTypeOther to set
	 */
	public void setExternalAgencyTypeOther(final String externalAgencyTypeOther) {
		this.externalAgencyTypeOther = externalAgencyTypeOther;
	}
}
