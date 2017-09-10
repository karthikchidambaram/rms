package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.tablemaintenance.AccidentLocation;
import com.i2g.rms.domain.model.tablemaintenance.AccidentLocationDetail;
import com.i2g.rms.domain.model.tablemaintenance.AccidentType;

/**
 * Entity representation of Incident.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_ACC")
@JsonIgnoreProperties({"incident"})
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
	private AccidentLocation _accidentLocation;
	private AccidentLocationDetail _accidentLocationDetails;
	private String _landmark;
	private String _accidentPlace;
	private AccidentType _accidentType;	
	private Set<InjuredPerson> _injuredPersons = new HashSet<InjuredPerson>(0);
	private Set<User> _employeeInjuredPersons = new HashSet<User>(0);
	private Set<Witness> _witnesses = new HashSet<Witness>(0);
	private Set<User> _employeeWitnesses = new HashSet<User>(0);
	private YesNoType _anyWitness;

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Accident() {
	}

	/**
	 * Creates a new immutable instance of {@link Accident} from the specified
	 * {@code builder}.
	 * 
	 * @param builder
	 */
	private Accident(final Builder builder) {
		_incident = Objects.requireNonNull(builder._incident, "Incident object cannot be null for an accident record.");
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
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INC_ID")
	@NotNull
	public Incident getIncident() {
		return _incident;
	}

	/**
	 * @param incident
	 *            the incident to set
	 */
	public void setIncident(final Incident incident) {
		_incident = incident;
	}

	/**
	 * @return the accidentDescription
	 */
	@Column(name = "ACC_DESC", length = 256)
	public String getAccidentDescription() {
		return _accidentDescription;
	}

	/**
	 * @param accidentDescription
	 *            the accidentDescription to set
	 */
	public void setAccidentDescription(final String accidentDescription) {
		_accidentDescription = accidentDescription;
	}

	/**
	 * @return the statusFlag
	 */
	@Column(name = "STS_FLG", nullable = false, length = 16)
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

	/**
	 * @return the accidentDateTime
	 */
	@Column(name = "ACC_DTM")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateTimeType")
	public LocalDateTime getAccidentDateTime() {
		return _accidentDateTime;
	}

	/**
	 * @param accidentDateTime
	 *            the accidentDateTime to set
	 */
	public void setAccidentDateTime(final LocalDateTime accidentDateTime) {
		_accidentDateTime = accidentDateTime;
	}

	/**
	 * @return the accidentLocationDetails
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_LOC_CHLD_CDE")
	public AccidentLocationDetail getAccidentLocationDetails() {
		return _accidentLocationDetails;
	}

	/**
	 * @param accidentLocationDetails
	 *            the accidentLocationDetails to set
	 */
	public void setAccidentLocationDetails(final AccidentLocationDetail accidentLocationDetails) {
		_accidentLocationDetails = accidentLocationDetails;
	}

	/**
	 * @return the landmark
	 */
	@Column(name = "LNDMRK", length = 64)
	public String getLandmark() {
		return _landmark;
	}

	/**
	 * @param landmark
	 *            the landmark to set
	 */
	public void setLandmark(final String landmark) {
		_landmark = landmark;
	}

	/**
	 * @return the accidentType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_TYP_CDE")
	public AccidentType getAccidentType() {
		return _accidentType;
	}

	/**
	 * @param accidentType
	 *            the accidentType to set
	 */
	public void setAccidentType(final AccidentType accidentType) {
		_accidentType = accidentType;
	}

	/**
	 * @return the injuredPersons
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_ACC_INJRD_PRSN",
			joinColumns = @JoinColumn(name = "ACC_ID"),
			inverseJoinColumns = @JoinColumn(name = "INJRD_PRSN_ID")
	)
	public Set<InjuredPerson> getInjuredPersons() {
		return _injuredPersons;
	}

	/**
	 * @param injuredPersons
	 *            the injuredPersons to set
	 */
	public void setInjuredPersons(final Set<InjuredPerson> injuredPersons) {
		this._injuredPersons = injuredPersons;
	}

	/**
	 * @return the employeeInjuredPersons
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_ACC_USR_INJRD_PRSN",
			joinColumns = @JoinColumn(name = "ACC_ID"),
			inverseJoinColumns = @JoinColumn(name = "USR_ID")
	)
	public Set<User> getEmployeeInjuredPersons() {
		return _employeeInjuredPersons;
	}

	/**
	 * @param employeeInjuredPersons
	 *            the employeeInjuredPersons to set
	 */
	public void setEmployeeInjuredPersons(final Set<User> employeeInjuredPersons) {
		this._employeeInjuredPersons = employeeInjuredPersons;
	}

	/**
	 * @return the witnesses
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_ACC_WITNS",
			joinColumns = @JoinColumn(name = "ACC_ID"),
			inverseJoinColumns = @JoinColumn(name = "WITNS_ID")
	)
	public Set<Witness> getWitnesses() {
		return _witnesses;
	}

	/**
	 * @param witnesses
	 *            the witnesses to set
	 */
	public void setWitnesses(final Set<Witness> witnesses) {
		this._witnesses = witnesses;
	}
	
	/**
	 * @return the employeeWitnesses
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_ACC_USR_WITNS",
			joinColumns = @JoinColumn(name = "ACC_ID"),
			inverseJoinColumns = @JoinColumn(name = "USR_ID")
	)
	public Set<User> getEmployeeWitnesses() {
		return _employeeWitnesses;
	}

	/**
	 * @param employeeWitnesses the employeeWitnesses to set
	 */
	public void setEmployeeWitnesses(final Set<User> employeeWitnesses) {
		_employeeWitnesses = employeeWitnesses;
	}
	
	/**
	 * @return the accidentPlace
	 */
	@Column(name = "ACC_PLACE", length = 256)
	public String getAccidentPlace() {
		return _accidentPlace;
	}

	/**
	 * @param accidentPlace the accidentPlace to set
	 */
	public void setAccidentPlace(final String accidentPlace) {
		_accidentPlace = accidentPlace;
	}
	
	/**
	 * @return the accidentLocation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_LOC_CDE")
	public AccidentLocation getAccidentLocation() {
		return _accidentLocation;
	}

	/**
	 * @param accidentLocation the accidentLocation to set
	 */
	public void setAccidentLocation(final AccidentLocation accidentLocation) {
		_accidentLocation = accidentLocation;
	}
	
	/**
	 * @return the anyWitness
	 */
	@Column(name = "ANY_WITNS")
	@Enumerated(EnumType.STRING)
	public YesNoType getAnyWitness() {
		return _anyWitness;
	}

	/**
	 * @param anyWitness
	 *            the anyWitness to set
	 */
	public void setAnyWitness(final YesNoType anyWitness) {
		_anyWitness = anyWitness;
	}

	/**
	 * Builder pattern for constructing immutable instances of {@link Accident}.
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
