package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity representation of Building.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_BLDNG")
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
		_id = Objects.requireNonNull(builder._id, "Building Id cannot be null.");		
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
	@Column(name = "BLDNG_ID")
	@Size(min = 1, max = 64, message = "Building ID must be between {min} and {max} characters")
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
	@Column(name = "BLDNG_DESC")
	@Size(min = 1, max = 256, message = "Building description must be between {min} and {max} characters")
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
	@Column(name = "INC_DESC")
	@Size(min = 1, max = 256, message = "Incident description must be between {min} and {max} characters")
	public String getIncidentDescription() {
		return _incidentDescription;
	}

	/**
	 * @param incidentDescription the incidentDescription to set
	 */
	public void setIncidentDescription(String incidentDescription) {
		_incidentDescription = incidentDescription;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _statusFlag);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Building) {
			final Building other = (Building) obj;
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
	 * {@link Building}.
	 */
	public final static class Builder {

		private Long _id;
		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Building.
		 * 
		 * @return new instance of Building
		 */
		public Building build() {
			return new Building(this);
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
