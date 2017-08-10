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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
		_id = Objects.requireNonNull(builder._id, "Asset Id cannot be null.");		
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");
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
	@Size(min = 1, max = 16, message = "Asset category code must be between {min} and {max} characters")
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
	@Column(name = "STMT_DESC")
	@Size(min = 1, max = 256, message = "Statement description must be between {min} and {max} characters")
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
	@Size(max = 1, message = "Any witness is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
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
	@Column(name = "OTHR_DESC")
	@Size(min = 1, max = 256, message = "Asset category 'Other' description must be between {min} and {max} characters")
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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "asset")
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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "asset")
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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "asset")
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

	@Override
	public int hashCode() {
		return Objects.hash(_id, _statusFlag);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Asset) {
			final Asset other = (Asset) obj;
			return Objects.equals(_id, other._id) 
					&& Objects.equals(_statusFlag, other._statusFlag);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Id: " + _id + ", " 
		+ "Status Flag: " + _statusFlag;
	}
	
	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link Asset}.
	 */
	public final static class Builder {

		private Long _id;
		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Asset.
		 * 
		 * @return new instance of Asset
		 */
		public Asset build() {
			return new Asset(this);
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

		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}
}
