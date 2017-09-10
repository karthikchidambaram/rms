package com.i2g.rms.domain.model;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.tablemaintenance.AssetCategory;

/**
 * Entity representation of Asset.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_ASST")
@JsonIgnoreProperties({"incident"})
public class Asset extends AbstractDataModel<Long> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID */
	private long _id;
	private Incident _incident;
	private StatusFlag _statusFlag;
	private AssetCategory _assetCategory;
	private String _statementDescription;
	private YesNoType _anyWitness;
	private String _otherDescription;
	private Set<Equipment> _equipments = new HashSet<Equipment>(0);
	private Set<Building> _buildings = new HashSet<Building>(0);
	private Set<Vehicle> _vehicles = new HashSet<Vehicle>(0);
	private Set<Witness> _witnesses = new HashSet<Witness>(0);
	private Set<User> _employeeWitnesses = new HashSet<User>(0);
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Asset() {
	}
	
	/**
	 * Creates a new immutable instance of {@link Asset} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private Asset(final Builder builder) {
		_incident = Objects.requireNonNull(builder._incident, "Incident (object) cannot be null.");
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null or empty.");
	}
	
	/**
	 * Return the Asset primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_asst_id_seq")
	@SequenceGenerator(name = "rms_asst_id_seq", sequenceName = "RMS_ASST_ID_SEQ", allocationSize = 1)
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
	 * @return the assetCategory
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASST_CTGRY_CDE")
	public AssetCategory getAssetCategory() {
		return _assetCategory;
	}

	/**
	 * @param assetCategory the assetCategory to set
	 */
	public void setAssetCategory(final AssetCategory assetCategory) {
		_assetCategory = assetCategory;
	}

	/**
	 * @return the statementDescription
	 */
	@Column(name = "STMT_DESC", length = 256)
	public String getStatementDescription() {
		return _statementDescription;
	}

	/**
	 * @param statementDescription the statementDescription to set
	 */
	public void setStatementDescription(final String statementDescription) {
		_statementDescription = statementDescription;
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
	 * @param anyWitness the anyWitness to set
	 */
	public void setAnyWitness(final YesNoType anyWitness) {
		_anyWitness = anyWitness;
	}
	
	/**
	 * @return the otherDescription
	 */
	@Column(name = "OTHR_DESC", length = 256)
	public String getOtherDescription() {
		return _otherDescription;
	}

	/**
	 * @param otherDescription the otherDescription to set
	 */
	public void setOtherDescription(final String otherDescription) {
		_otherDescription = otherDescription;
	}
	
	/**
	 * @return the equipments
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "asset")
	@Fetch(FetchMode.SUBSELECT)
	public Set<Equipment> getEquipments() {
		return _equipments;
	}

	/**
	 * @param equipments the equipments to set
	 */
	public void setEquipments(final Set<Equipment> equipments) {
		_equipments = equipments;
	}

	/**
	 * @return the buildings
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "asset")
	@Fetch(FetchMode.SUBSELECT)
	public Set<Building> getBuildings() {
		return _buildings;
	}

	/**
	 * @param buildings the buildings to set
	 */
	public void setBuildings(final Set<Building> buildings) {
		_buildings = buildings;
	}

	/**
	 * @return the vehicles
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "asset")
	@Fetch(FetchMode.SUBSELECT)
	public Set<Vehicle> getVehicles() {
		return _vehicles;
	}

	/**
	 * @param vehicles the vehicles to set
	 */
	public void setVehicles(final Set<Vehicle> vehicles) {
		_vehicles = vehicles;
	}
	
	/**
	 * @return the witnesses
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_ASST_WITNS",
			joinColumns = @JoinColumn(name = "ASST_ID"),
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
			name = "RMS_ASST_USR_WITNS",
			joinColumns = @JoinColumn(name = "ASST_ID"),
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
	 * Builder pattern for constructing immutable instances of
	 * {@link Asset}.
	 */
	public final static class Builder {

		private StatusFlag _statusFlag;
		private Incident _incident;

		/**
		 * Builds a new immutable instance of Asset.
		 * 
		 * @return new instance of Asset
		 */
		public Asset build() {
			return new Asset(this);
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
