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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.tablemaintenance.AssetCategory;

/**
 * Entity representation of Building.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_BLDNG")
@JsonIgnoreProperties({"asset"})
public class Building extends AbstractDataModel<Long> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Primary surrogate key ID */
	private long _id;
	private Asset _asset;
	private StatusFlag _statusFlag;
	private String _buildingId;
	private String _buildingDescription;
	private String _incidentDescription;	
	private AssetCategory _assetCategory;
	private Set<Address> _addresses = new HashSet<Address>(0);
		
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Building() {
	}
	
	/**
	 * Creates a new immutable instance of {@link Building} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private Building(final Builder builder) {
		_asset = Objects.requireNonNull(builder._asset, "Asset object cannot be null while creating a buiding.");
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");		
	}
	
	/**
	 * Return the Building primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_bldng_id_seq")
	@SequenceGenerator(name = "rms_bldng_id_seq", sequenceName = "RMS_BLDNG_ID_SEQ", allocationSize = 1)
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
	 * @return the asset
	 */
	@ManyToOne
	@JoinColumn(name = "ASST_ID")
	public Asset getAsset() {
		return _asset;
	}

	/**
	 * @param asset the asset to set
	 */
	public void setAsset(final Asset asset) {
		_asset = asset;
	}

	/**
	 * @return the buildingId
	 */
	@Column(name = "BLDNG_ID", length = 64)
	public String getBuildingId() {
		return _buildingId;
	}

	/**
	 * @param buildingId the buildingId to set
	 */
	public void setBuildingId(String buildingId) {
		_buildingId = buildingId;
	}

	/**
	 * @return the buildingDescription
	 */
	@Column(name = "BLDNG_DESC", length = 256)
	public String getBuildingDescription() {
		return _buildingDescription;
	}

	/**
	 * @param buildingDescription the buildingDescription to set
	 */
	public void setBuildingDescription(String buildingDescription) {
		_buildingDescription = buildingDescription;
	}

	/**
	 * @return the incidentDescription
	 */
	@Column(name = "INC_DESC", length = 256)
	public String getIncidentDescription() {
		return _incidentDescription;
	}

	/**
	 * @param incidentDescription the incidentDescription to set
	 */
	public void setIncidentDescription(String incidentDescription) {
		_incidentDescription = incidentDescription;
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
	 * @return the addresses
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "building")
	@Fetch(FetchMode.SUBSELECT)
	public Set<Address> getAddresses() {
		return _addresses;
	}

	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(final Set<Address> addresses) {
		_addresses = addresses;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link Building}.
	 */
	public final static class Builder {
		
		private Asset _asset;
		private StatusFlag _statusFlag;
		
		/**
		 * Builds a new immutable instance of Building.
		 * 
		 * @return new instance of Building
		 */
		public Building build() {
			return new Building(this);
		}
		
		public Builder setAsset(final Asset asset) {
			_asset = asset;
			return this;
		}
		
		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}
}
