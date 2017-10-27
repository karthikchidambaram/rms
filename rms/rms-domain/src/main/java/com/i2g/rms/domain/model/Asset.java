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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	private String _otherDescription;
	private Set<Equipment> _equipments = new HashSet<Equipment>(0);
	private Set<Building> _buildings = new HashSet<Building>(0);
	private Set<Vehicle> _vehicles = new HashSet<Vehicle>(0);		
	
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
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_ASST_EQPMT",
			joinColumns = @JoinColumn(name = "ASST_ID"),
			inverseJoinColumns = @JoinColumn(name = "EQPMT_ID")
	)
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
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_ASST_BLDNG",
			joinColumns = @JoinColumn(name = "ASST_ID"),
			inverseJoinColumns = @JoinColumn(name = "BLDNG_ID")
	)
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
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_ASST_VEHCL",
			joinColumns = @JoinColumn(name = "ASST_ID"),
			inverseJoinColumns = @JoinColumn(name = "VEHCL_ID")
	)
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
