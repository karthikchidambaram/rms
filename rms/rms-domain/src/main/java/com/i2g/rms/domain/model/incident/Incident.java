package com.i2g.rms.domain.model.incident;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.jdo.annotations.Unique;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.i2g.rms.domain.model.AbstractDataModel;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.YesNoType;
import com.i2g.rms.domain.model.tablemaintenance.EntryPoint;
import com.i2g.rms.domain.model.tablemaintenance.IncidentCategory;
import com.i2g.rms.domain.model.tablemaintenance.IncidentLocationDetail;
import com.i2g.rms.domain.model.tablemaintenance.IncidentType;

/**
 * Entity representation of Incident.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_INC")
public class Incident extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID of incident table. */
	private long _id;
	/** Unique identifier for each incident */
	private String _uniqueIncidentId;
	private LocalDateTime _incidentOpenedDateTime;
	private IncidentType _incidentType;
	private String _placeOfIncident;
	private String _landmark;
	private EntryPoint _entryPoint;
	private IncidentStatus _incidentStatus;
	private IncidentLocationDetail _incidentLocationDetails;
	private String _incidentDescription;
	private StatusFlag _statusFlag;
	private YesNoType _personInjured;
	private YesNoType _propertyDamage;
	private YesNoType _crimeInvolved;
	private User _incidentReportedBy;
	private IncidentCategory _incidentCategory;
	private LocalDateTime _incidentClosedDateTime;
	private Set<Suspect> _suspects = new HashSet<Suspect>(0);	
	private Set<User> _employeeSuspects = new HashSet<User>(0);
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Incident() {
	}

	/**
	 * Creates a new immutable instance of {@link Incident} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private Incident(final Builder builder) {
		_id = Objects.requireNonNull(builder._id, "Incident Id cannot be null.");
		_uniqueIncidentId = Objects.requireNonNull(builder._uniqueIncidentId, "Unique Incident Id cannot be null.");
		_incidentStatus = Objects.requireNonNull(builder._incidentStatus, "Incident status cannot be null.");
		_incidentReportedBy = Objects.requireNonNull(builder._user, "The user (object) who reported the incident cannot be null.");
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Incident status flag cannot be null.");
	}

	/**
	 * Return the Incident primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_inc_id_seq")
	@SequenceGenerator(name = "rms_inc_id_seq", sequenceName = "RMS_INC_ID_SEQ", allocationSize = 1)
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
	
	@Column(name = "UNQ_INC_ID", nullable = false, updatable = false)
	@NotNull
	@Unique
	@Size(min = 1, max = 24, message = "Unique Incident ID must be between {min} and {max} characters")
	public String getUniqueIncidentId() {
		return _uniqueIncidentId;
	}

	public void setUniqueIncidentId(final String uniqueIncidentId) {
		_uniqueIncidentId = uniqueIncidentId;
	}

	@Column(name = "INC_OPN_DTM")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateTimeType")
	public LocalDateTime getIncidentOpenedDateTime() {
		return _incidentOpenedDateTime;
	}

	public void setIncidentOpenedDateTime(final LocalDateTime incidentOpenedDateTime) {
		_incidentOpenedDateTime = incidentOpenedDateTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INC_TYP_CDE")
	@Size(min = 1, max = 16, message = "Incident type code must be between {min} and {max} characters")
	public IncidentType getIncidentType() {
		return _incidentType;
	}

	public void setIncidentType(final IncidentType incidentType) {
		_incidentType = incidentType;
	}
	
	@Column(name = "INC_PLACE")
	@Size(min = 1, max = 256, message = "Place of incident must be between {min} and {max} characters")
	public String getPlaceOfIncident() {
		return _placeOfIncident;
	}

	public void setPlaceOfIncident(final String placeOfIncident) {
		_placeOfIncident = placeOfIncident;
	}
	
	@Column(name = "LNDMRK")
	@Size(min = 1, max = 64, message = "Nearest landmark of the place of incident must be between {min} and {max} characters")
	public String getLandmark() {
		return _landmark;
	}
	
	public void setLandmark(final String landmark) {
		_landmark = landmark;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENTY_PNT_CDE")
	@Size(min = 1, max = 16, message = "Entry point code must be between {min} and {max} characters")
	public EntryPoint getEntryPoint() {
		return _entryPoint;
	}

	public void setEntryPoint(final EntryPoint entryPoint) {
		_entryPoint = entryPoint;
	}
	
	@Column(name = "INC_STS", nullable = false)
	@Size(min = 1, max = 16, message = "Incident status code must be between {min} and {max} characters")
	@Enumerated(EnumType.STRING)
	@NotNull
	public IncidentStatus getIncidentStatus() {
		return _incidentStatus;
	}

	public void setIncidentStatus(final IncidentStatus incidentStatus) {
		_incidentStatus = incidentStatus;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INC_LOC_CHLD_CDE")
	@Size(min = 1, max = 16, message = "Incident location details code must be between {min} and {max} characters")
	public IncidentLocationDetail getIncidentLocationDetails() {
		return _incidentLocationDetails;
	}

	public void setIncidentLocationDetails(final IncidentLocationDetail incidentLocationDetails) {
		_incidentLocationDetails = incidentLocationDetails;
	}
	
	@Column(name = "INC_DESC")
	@Size(min = 1, max = 256, message = "Incident type code must be between {min} and {max} characters")
	public String getIncidentDescription() {
		return _incidentDescription;
	}

	public void setIncidentDescription(final String incidentDescription) {
		_incidentDescription = incidentDescription;
	}
	
	@Column(name = "STS_FLG", nullable = false)
	@Size(min = 1, max = 16, message = "Status flag code must be between {min} and {max} characters")
	@NotNull
	@Enumerated(EnumType.STRING)
	public StatusFlag getStatusFlag() {
		return _statusFlag;
	}

	public void setStatusFlag(final StatusFlag statusFlag) {
		_statusFlag = statusFlag;
	}
	
	@Column(name = "PRSN_INJRD", nullable = false)
	@Size(max = 1, message = "Person injured is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
	@Enumerated(EnumType.STRING)
	@NotNull
	public YesNoType getPersonInjured() {
		return _personInjured;
	}
	
	public void setPersonInjured(final YesNoType personInjured) {
		_personInjured = personInjured;
	}
	
	@Column(name = "PROP_DAMAGE", nullable = false)
	@Size(max = 1, message = "Property damage is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
	@Enumerated(EnumType.STRING)
	@NotNull
	public YesNoType getPropertyDamage() {
		return _propertyDamage;
	}

	public void setPropertyDamage(final YesNoType propertyDamage) {
		_propertyDamage = propertyDamage;
	}
	
	@Column(name = "CRIME_INVLD", nullable = false)
	@Size(max = 1, message = "Crime involved is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
	@Enumerated(EnumType.STRING)
	@NotNull
	public YesNoType getCrimeInvolved() {
		return _crimeInvolved;
	}

	public void setCrimeInvolved(final YesNoType crimeInvolved) {
		_crimeInvolved = crimeInvolved;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USR_ID")
	@NotNull
	public User getIncidentReportedBy() {
		return _incidentReportedBy;
	}

	public void setIncidentReportedBy(final User incidentReportedBy) {
		_incidentReportedBy = incidentReportedBy;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INC_CTGRY_CDE")
	@Size(min = 1, max = 16, message = "Incident category code must be between {min} and {max} characters")
	public IncidentCategory getIncidentCategory() {
		return _incidentCategory;
	}

	public void setIncidentCategory(final IncidentCategory incidentCategory) {
		_incidentCategory = incidentCategory;
	}
	
	@Column(name = "INC_CLSD_DTM")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateTimeType")
	public LocalDateTime getIncidentClosedDateTime() {
		return _incidentClosedDateTime;
	}

	public void setIncidentClosedDateTime(final LocalDateTime incidentClosedDateTime) {
		_incidentClosedDateTime = incidentClosedDateTime;
	}
	
	/**
	 * @return the suspects
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "RMS_INC_SUSPT",
			joinColumns = @JoinColumn(name = "INC_ID"),
			inverseJoinColumns = @JoinColumn(name = "SUSPT_ID")
	)
	public Set<Suspect> getSuspects() {
		return _suspects;
	}

	/**
	 * @param suspects the suspects to set
	 */
	public void setSuspects(final Set<Suspect> suspects) {
		_suspects = suspects;
	}
	
	/**
	 * @return the employeeSuspects
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "RMS_INC_SUSPT",
			joinColumns = @JoinColumn(name = "INC_ID"),
			inverseJoinColumns = @JoinColumn(name = "USR_ID")
	)
	public Set<User> getEmployeeSuspects() {
		return _employeeSuspects;
	}

	/**
	 * @param employeeSuspects the employeeSuspects to set
	 */
	public void setEmployeeSuspects(final Set<User> employeeSuspects) {
		_employeeSuspects = employeeSuspects;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _uniqueIncidentId, _incidentStatus, _statusFlag);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Incident) {
			final Incident other = (Incident) obj;
			return Objects.equals(_id, other._id) 
					&& Objects.equals(_uniqueIncidentId, other._uniqueIncidentId) 
					&& Objects.equals(_incidentStatus, other._incidentStatus) 
					&& Objects.equals(_statusFlag, other._statusFlag);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Incident Id: " + _id + ", Unique Incident Id: " + _uniqueIncidentId + ", Incident Description: " + _incidentDescription;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link Incident}.
	 */
	public final static class Builder {

		private Long _id;
		private String _uniqueIncidentId;
		private IncidentStatus _incidentStatus;
		private User _user;
		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Incident.
		 * 
		 * @return new instance of Incident
		 */
		public Incident build() {
			return new Incident(this);
		}

		/**
		 * Sets the specified {@code id}.
		 * 
		 * @param id
		 * @return this builder
		 */
		public Builder setId(final Long id) {
			_id = id;
			return this;
		}

		public Builder setUniqueIncidentId(final String uniqueIncidentId) {
			_uniqueIncidentId = uniqueIncidentId;
			return this;
		}
		
		public Builder setIncidentStatus(final IncidentStatus incidentStatus) {
			_incidentStatus = incidentStatus;
			return this;
		}

		public Builder setUser(final User user) {
			_user = user;
			return this;
		}		
		
		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}
}
