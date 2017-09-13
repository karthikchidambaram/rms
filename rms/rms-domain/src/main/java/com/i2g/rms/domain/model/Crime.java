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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.incident.Incident;

/**
 * Entity representation of Crime.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_CRME")
@JsonIgnoreProperties({ "incident" })
public class Crime extends AbstractDataModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID */
	private long _id;
	private Incident _incident;
	private StatusFlag _statusFlag;
	private LocalDateTime _crimeDateTime;
	private String _crimeDescription;
	private YesNoType _anyWitness;
	private Set<CrimeSuspect> _crimeSuspects = new HashSet<CrimeSuspect>(0);
	private Set<User> _employeeCrimeSuspects = new HashSet<User>(0);
	private Set<Witness> _witnesses = new HashSet<Witness>(0);
	private Set<User> _employeeWitnesses = new HashSet<User>(0);

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Crime() {
	}

	/**
	 * Creates a new immutable instance of {@link Crime} from the specified
	 * {@code builder}.
	 * 
	 * @param builder
	 */
	private Crime(final Builder builder) {
		_incident = Objects.requireNonNull(builder._incident, "Incident object cannot be null for a Crime record.");
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");
	}

	/**
	 * Return the Crime primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_crme_id_seq")
	@SequenceGenerator(name = "rms_crme_id_seq", sequenceName = "RMS_CRME_ID_SEQ", allocationSize = 1)
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
	 * @return the crimeDateTime
	 */
	@Column(name = "CRME_DTM")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateTimeType")
	public LocalDateTime getCrimeDateTime() {
		return _crimeDateTime;
	}

	/**
	 * @param crimeDateTime
	 *            the crimeDateTime to set
	 */
	public void setCrimeDateTime(final LocalDateTime crimeDateTime) {
		_crimeDateTime = crimeDateTime;
	}

	/**
	 * @return the crimeDescription
	 */
	@Column(name = "CRME_DESC", length = 256)
	public String getCrimeDescription() {
		return _crimeDescription;
	}

	/**
	 * @param crimeDescription
	 *            the crimeDescription to set
	 */
	public void setCrimeDescription(final String crimeDescription) {
		_crimeDescription = crimeDescription;
	}

	/**
	 * @return the crimeSuspects
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_CRME_CRME_SUSPT",
			joinColumns = @JoinColumn(name = "CRME_ID"),
			inverseJoinColumns = @JoinColumn(name = "CRME_SUSPT_ID")
	)
	public Set<CrimeSuspect> getCrimeSuspects() {
		return _crimeSuspects;
	}

	/**
	 * @param crimeSuspects
	 *            the crimeSuspects to set
	 */
	public void setCrimeSuspects(final Set<CrimeSuspect> crimeSuspects) {
		_crimeSuspects = crimeSuspects;
	}

	/**
	 * @return the employeeCrimeSuspects
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_CRME_USR_SUSPT",
			joinColumns = @JoinColumn(name = "CRME_ID"),
			inverseJoinColumns = @JoinColumn(name = "USR_ID")
	)
	public Set<User> getEmployeeCrimeSuspects() {
		return _employeeCrimeSuspects;
	}

	/**
	 * @param employeeCrimeSuspects
	 *            the employeeCrimeSuspects to set
	 */
	public void setEmployeeCrimeSuspects(final Set<User> employeeCrimeSuspects) {
		_employeeCrimeSuspects = employeeCrimeSuspects;
	}

	/**
	 * @return the witnesses
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_CRME_WITNS",
			joinColumns = @JoinColumn(name = "CRME_ID"),
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
		_witnesses = witnesses;
	}

	/**
	 * @return the employeeWitnesses
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_CRME_USR_WITNS",
			joinColumns = @JoinColumn(name = "CRME_ID"),
			inverseJoinColumns = @JoinColumn(name = "USR_ID")
	)
	public Set<User> getEmployeeWitnesses() {
		return _employeeWitnesses;
	}

	/**
	 * @param employeeWitnesses
	 *            the employeeWitnesses to set
	 */
	public void setEmployeeWitnesses(final Set<User> employeeWitnesses) {
		_employeeWitnesses = employeeWitnesses;
	}

	/**
	 * Builder pattern for constructing immutable instances of {@link Crime}.
	 */
	public final static class Builder {

		private Incident _incident;
		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Crime.
		 * 
		 * @return new instance of Crime
		 */
		public Crime build() {
			return new Crime(this);
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
