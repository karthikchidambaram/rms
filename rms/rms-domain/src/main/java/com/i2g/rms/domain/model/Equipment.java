package com.i2g.rms.domain.model;

import java.io.Serializable;
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
@Table(name = "RMS_EQPMT")
@JsonIgnoreProperties({"asset"})
public class Equipment extends AbstractDataModel<Long> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Primary surrogate key ID */
	private long _id;
	private Asset _asset;
	private StatusFlag _statusFlag;
	private String _equipmentId;
	private String _equipmentDetails;
	private String _serialNumber;
	private AssetCategory _assetCategory;
		
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Equipment() {
	}
	
	/**
	 * Creates a new immutable instance of {@link Equipment} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private Equipment(final Builder builder) {
		_asset = Objects.requireNonNull(builder._asset, "Asset object cannot be null while creating an equipment.");
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
	 * @return the equipmentId
	 */
	@Column(name = "EQPMT_ID", length = 64)
	public String getEquipmentId() {
		return _equipmentId;
	}

	/**
	 * @param equipmentId the equipmentId to set
	 */
	public void setEquipmentId(final String equipmentId) {
		_equipmentId = equipmentId;
	}

	/**
	 * @return the equipmentDetails
	 */
	@Column(name = "EQPMT_DTLS", length = 256)
	public String getEquipmentDetails() {
		return _equipmentDetails;
	}

	/**
	 * @param equipmentDetails the equipmentDetails to set
	 */
	public void setEquipmentDetails(final String equipmentDetails) {
		_equipmentDetails = equipmentDetails;
	}

	/**
	 * @return the serialNumber
	 */
	@Column(name = "SRL_NO", length = 64)
	public String getSerialNumber() {
		return _serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(final String serialNumber) {
		_serialNumber = serialNumber;
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
	 * Builder pattern for constructing immutable instances of
	 * {@link Equipment}.
	 */
	public final static class Builder {
		
		private Asset _asset;
		private StatusFlag _statusFlag;
		
		/**
		 * Builds a new immutable instance of Address.
		 * 
		 * @return new instance of Address
		 */
		public Equipment build() {
			return new Equipment(this);
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
