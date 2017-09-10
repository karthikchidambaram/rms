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
import com.i2g.rms.domain.model.tablemaintenance.VehicleDamageType;

/**
 * Entity representation of Building.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_VEHCL")
@JsonIgnoreProperties({"asset"})
public class Vehicle extends AbstractDataModel<Long> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Primary surrogate key ID */
	private long _id;
	private Asset _asset;
	private StatusFlag _statusFlag;
	private String _vehicleRegistrationId;
	private String _engineNumber;
	private String _chasisNumber;
	private String _make;
	private String _model;
	private String _commentDescription;
	private VehicleDamageType _vehicleDamageType;
	private AssetCategory _assetCategory;
		
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Vehicle() {
	}
	
	/**
	 * Creates a new immutable instance of {@link Vehicle} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private Vehicle(final Builder builder) {
		_asset = Objects.requireNonNull(builder._asset, "Asset object cannot be null while creating a vehicle.");
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");		
	}
	
	/**
	 * Return the Building primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_vehcl_id_seq")
	@SequenceGenerator(name = "rms_vehcl_id_seq", sequenceName = "RMS_VEHCL_ID_SEQ", allocationSize = 1)
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
	 * @return the vehicleRegistrationId
	 */
	@Column(name = "VEHCL_REG_ID", length = 32)
	public String getVehicleRegistrationId() {
		return _vehicleRegistrationId;
	}

	/**
	 * @param vehicleRegistrationId the vehicleRegistrationId to set
	 */
	public void setVehicleRegistrationId(final String vehicleRegistrationId) {
		_vehicleRegistrationId = vehicleRegistrationId;
	}

	/**
	 * @return the engineNumber
	 */
	@Column(name = "ENGN_NO", length = 64)
	public String getEngineNumber() {
		return _engineNumber;
	}

	/**
	 * @param engineNumber the engineNumber to set
	 */
	public void setEngineNumber(final String engineNumber) {
		_engineNumber = engineNumber;
	}

	/**
	 * @return the chasisNumber
	 */
	@Column(name = "CHSIS_NO", length = 64)
	public String getChasisNumber() {
		return _chasisNumber;
	}

	/**
	 * @param chasisNumber the chasisNumber to set
	 */
	public void setChasisNumber(final String chasisNumber) {
		_chasisNumber = chasisNumber;
	}

	/**
	 * @return the make
	 */
	@Column(name = "MKE", length = 32)
	public String getMake() {
		return _make;
	}

	/**
	 * @param make the make to set
	 */
	public void setMake(final String make) {
		_make = make;
	}

	/**
	 * @return the model
	 */
	@Column(name = "MDL", length = 32)
	public String getModel() {
		return _model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(final String model) {
		_model = model;
	}

	/**
	 * @return the commentDescription
	 */
	@Column(name = "COM_DESC", length = 32)
	public String getCommentDescription() {
		return _commentDescription;
	}

	/**
	 * @param commentDescription the commentDescription to set
	 */
	public void setCommentDescription(final String commentDescription) {
		_commentDescription = commentDescription;
	}

	/**
	 * @return the vehicleDamageType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VEHCL_DMGE_TYP_CDE")
	public VehicleDamageType getVehicleDamageType() {
		return _vehicleDamageType;
	}

	/**
	 * @param vehicleDamageType the vehicleDamageType to set
	 */
	public void setVehicleDamageType(final VehicleDamageType vehicleDamageType) {
		_vehicleDamageType = vehicleDamageType;
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
	 * {@link Vehicle}.
	 */
	public final static class Builder {
		
		private Asset _asset;
		private StatusFlag _statusFlag;		

		/**
		 * Builds a new immutable instance of Address.
		 * 
		 * @return new instance of Address
		 */
		public Vehicle build() {
			return new Vehicle(this);
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
