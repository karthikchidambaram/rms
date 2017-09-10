package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.tablemaintenance.ExternalAgency;
import com.i2g.rms.domain.model.tablemaintenance.LossType;

/**
 * Entity representation of ReportedLoss.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_RPT_LOSS")
@JsonIgnoreProperties({"incident"})
public class ReportedLoss extends AbstractDataModel<Long> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Primary surrogate key ID */
	private long _id;
	private Incident _incident;
	private StatusFlag _statusFlag;
	private LossType _lossType;
	private BigDecimal _lossValue;
	private YesNoType _externalAgencyContacted;
	private ExternalAgency _externalAgency;
	private LocalDateTime _dateTimeContacted;
	private BigDecimal _costEstimation;
		
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected ReportedLoss() {
	}
	
	/**
	 * Creates a new immutable instance of {@link ReportedLoss} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private ReportedLoss(final Builder builder) {
		_incident = Objects.requireNonNull(builder._incident, "Incident object cannot be null.");
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");
	}
	
	/**
	 * Return the ReportedLoss primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_rpt_loss_id_seq")
	@SequenceGenerator(name = "rms_rpt_loss_id_seq", sequenceName = "RMS_RPT_LOSS_ID_SEQ", allocationSize = 1)
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
	 * @return the statusFlag
	 */
	@Column(name = "STS_FLG", nullable = false)
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
	
	/**
	 * @return the lossType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOS_TYP_CDE")
	public LossType getLossType() {
		return _lossType;
	}

	/**
	 * @param lossType the lossType to set
	 */
	public void setLossType(final LossType lossType) {
		_lossType = lossType;
	}

	/**
	 * @return the lossValue
	 */
	@Column(name = "LOS_VAL")
	public BigDecimal getLossValue() {
		return _lossValue;
	}

	/**
	 * @param lossValue the lossValue to set
	 */
	public void setLossValue(final BigDecimal lossValue) {
		_lossValue = lossValue;		
	}

	/**
	 * @return the externalAgencyContacted
	 */
	@Column(name = "EXTNL_AGNCY_CNTD")
	@Enumerated(EnumType.STRING)
	public YesNoType getExternalAgencyContacted() {
		return _externalAgencyContacted;
	}

	/**
	 * @param externalAgencyContacted the externalAgencyContacted to set
	 */
	public void setExternalAgencyContacted(final YesNoType externalAgencyContacted) {
		_externalAgencyContacted = externalAgencyContacted;
	}

	/**
	 * @return the externalAgency
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXTNL_AGNCY_TYP_CDE")
	public ExternalAgency getExternalAgency() {
		return _externalAgency;
	}

	/**
	 * @param externalAgency the externalAgency to set
	 */
	public void setExternalAgency(final ExternalAgency externalAgency) {
		_externalAgency = externalAgency;
	}

	/**
	 * @return the dateTimeContacted
	 */
	@Column(name = "CNTD_DTM")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateTimeType")
	public LocalDateTime getDateTimeContacted() {
		return _dateTimeContacted;
	}

	/**
	 * @param dateTimeContacted the dateTimeContacted to set
	 */
	public void setDateTimeContacted(final LocalDateTime dateTimeContacted) {
		_dateTimeContacted = dateTimeContacted;
	}

	/**
	 * @return the costEstimation
	 */
	@Column(name = "CST_EST")
	public BigDecimal getCostEstimation() {
		return _costEstimation;
	}

	/**
	 * @param costEstimation the costEstimation to set
	 */
	public void setCostEstimation(final BigDecimal costEstimation) {
		_costEstimation = costEstimation;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link ReportedLoss}.
	 */
	public final static class Builder {

		private StatusFlag _statusFlag;
		private Incident _incident;

		/**
		 * Builds a new immutable instance of ReportedLoss.
		 * 
		 * @return new instance of ReportedLoss
		 */
		public ReportedLoss build() {
			return new ReportedLoss(this);
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
