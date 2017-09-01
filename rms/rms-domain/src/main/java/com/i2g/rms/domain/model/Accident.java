package com.i2g.rms.domain.model;

import java.io.Serializable;
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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.tablemaintenance.AccidentLocationDetail;
import com.i2g.rms.domain.model.tablemaintenance.AccidentType;
import com.i2g.rms.domain.model.tablemaintenance.InjuryCause;
import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetailSpec;

/**
 * Entity representation of Incident.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_ACC")
public class Accident extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID of accident table. */
	private long _id;
	private Incident _incident;
	private String _accidentDescription;
	private StatusFlag _statusFlag;
	private LocalDateTime _accidentDateTime;
	private AccidentLocationDetail _accidentLocationDetails;
	private String _landmark;
	private AccidentType _accidentType;
	private InjuryTypeDetailSpec _injuryTypeDetailsSpec;
	private InjuryCause _injuryCause;	
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Accident() {
	}
	
	/**
	 * Creates a new immutable instance of {@link Accident} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private Accident(final Builder builder) {
		_incident = Objects.requireNonNull(builder._incident, "Incident (object) cannot be null inside accident details.");
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Accident status flag cannot be null.");
	}
	
	/**
	 * Return the Accident primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_acc_id_seq")
	@SequenceGenerator(name = "rms_acc_id_seq", sequenceName = "RMS_ACC_ID_SEQ", allocationSize = 1)
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
	 * @return the incident
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
	 * @return the accidentDescription
	 */
	@Column(name = "ACC_DESC")
	@Size(min = 1, max = 256, message = "Accident description must be between {min} and {max} characters")
	public String getAccidentDescription() {
		return _accidentDescription;
	}

	/**
	 * @param accidentDescription the accidentDescription to set
	 */
	public void setAccidentDescription(final String accidentDescription) {
		_accidentDescription = accidentDescription;
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

	/**
	 * @return the accidentDateTime
	 */
	@Column(name = "ACC_DTM")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateTimeType")
	public LocalDateTime getAccidentDateTime() {
		return _accidentDateTime;
	}

	/**
	 * @param accidentDateTime the accidentDateTime to set
	 */
	public void setAccidentDateTime(final LocalDateTime accidentDateTime) {
		_accidentDateTime = accidentDateTime;
	}

	/**
	 * @return the accidentLocationDetails
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_LOC_CHLD_CDE")
	@Size(min = 1, max = 16, message = "Accident location code must be between {min} and {max} characters")
	public AccidentLocationDetail getAccidentLocationDetails() {
		return _accidentLocationDetails;
	}

	/**
	 * @param accidentLocationDetails the accidentLocationDetails to set
	 */
	public void setAccidentLocationDetails(final AccidentLocationDetail accidentLocationDetails) {
		_accidentLocationDetails = accidentLocationDetails;
	}

	/**
	 * @return the landmark
	 */
	@Column(name = "LNDMRK")
	@Size(min = 1, max = 64, message = "Landmark description must be between {min} and {max} characters")
	public String getLandmark() {
		return _landmark;
	}

	/**
	 * @param landmark the landmark to set
	 */
	public void setLandmark(final String landmark) {
		_landmark = landmark;
	}

	/**
	 * @return the accidentType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_TYP_CDE")
	@Size(min = 1, max = 16, message = "Accident type code must be between {min} and {max} characters")
	public AccidentType getAccidentType() {
		return _accidentType;
	}

	/**
	 * @param accidentType the accidentType to set
	 */
	public void setAccidentType(final AccidentType accidentType) {
		_accidentType = accidentType;
	}

	/**
	 * @return the injuryTypeDetailsSpec
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INJRY_TYP_CHLD_SUB")
	@Size(min = 1, max = 16, message = "Injury type detail code must be between {min} and {max} characters")
	public InjuryTypeDetailSpec getInjuryTypeDetailsSpec() {
		return _injuryTypeDetailsSpec;
	}

	/**
	 * @param injuryTypeDetailsSpec the injuryTypeDetailsSpec to set
	 */
	public void setInjuryTypeDetailsSpec(final InjuryTypeDetailSpec injuryTypeDetailsSpec) {
		_injuryTypeDetailsSpec = injuryTypeDetailsSpec;
	}

	/**
	 * @return the injuryCause
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INJRY_CAU_CDE")
	@Size(min = 1, max = 16, message = "Injury cause code must be between {min} and {max} characters")
	public InjuryCause getInjuryCause() {
		return _injuryCause;
	}

	/**
	 * @param injuryCause the injuryCause to set
	 */
	public void setInjuryCause(final InjuryCause injuryCause) {
		_injuryCause = injuryCause;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(_id, _statusFlag);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Accident) {
			final Accident other = (Accident) obj;
			return Objects.equals(_id, other._id) 
					&& Objects.equals(_statusFlag, other._statusFlag);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Accident Id: " + _id + ", Accident Description: " + _accidentDescription;
	}
	
	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link Accident}.
	 */
	public final static class Builder {

		private Incident _incident;
		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Accident.
		 * 
		 * @return new instance of Accident
		 */
		public Accident build() {
			return new Accident(this);
		}

		public Builder seIncident(final Incident incident) {
			_incident = incident;
			return this;
		}
		
		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}
}
