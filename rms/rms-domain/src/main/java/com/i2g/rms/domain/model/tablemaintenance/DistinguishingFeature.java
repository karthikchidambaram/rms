package com.i2g.rms.domain.model.tablemaintenance;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.AbstractDataModel;

/**
 * Entity for representing allowable values for the drop down "Distinguishing
 * Feature" in Incident Details tab page.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_DIST_FEA")
public class DistinguishingFeature extends AbstractDataModel<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _id;
	private String _description;
	private Set<DistinguishingFeatureDetail> _distinguishingFeatureDetails = new HashSet<DistinguishingFeatureDetail>(0);

	/**
	 * Default empty constructor required for Hibernate.
	 */
	public DistinguishingFeature() {
	}

	/**
	 * Creates a new instance of {@code DistinguishingFeature} with the specified
	 * code.
	 * 
	 * @param code
	 */
	public DistinguishingFeature(final String code) {
		_id = Objects.requireNonNull(code, "Distinguishing feature code cannot be null.");
	}

	/**
	 * Creates a new instance of {@code DistinguishingFeature} with the specified
	 * code and description.
	 * 
	 * @param code
	 * @param description
	 */
	public DistinguishingFeature(final String code, final String description) {
		_id = Objects.requireNonNull(code, "Distinguishing feature code cannot be null.");
		_description = Objects.requireNonNull(description, "Distinguishing feature description cannot be null.");
	}

	/**
	 * Creates a new instance of {@code DistinguishingFeature} with the specified
	 * code, description and Distinguishing features location details.
	 * 
	 * @param code
	 * @param description
	 * @param distinguishingFeaturesDetail
	 */
	public DistinguishingFeature(final String code, final String description, final Set<DistinguishingFeatureDetail> distinguishingFeatureDetails) {
		_id = Objects.requireNonNull(code, "Distinguishing feature code cannot be null.");
		_description = Objects.requireNonNull(description, "Distinguishing feature description cannot be null.");
		_distinguishingFeatureDetails = Objects.requireNonNull(distinguishingFeatureDetails, "Distinguishing features details cannot be null or empty.");
	}

	@Id
	@Column(name = "CDE", unique = true, updatable = false, nullable = false, length = 16)
	@NotNull
	@Size(min = 1, max = 16)
	@Override
	public String getId() {
		return _id;
	}

	/**
	 * Sets the Distinguishing Features.
	 * 
	 * <p>
	 * <strong>Note:</strong> This method has protected access to prevent
	 * callers from manually setting the primary key ID; Hibernate has access to
	 * invoke this method when populating an entity. When creating a new entry
	 * the appropriate constructor should be invoked.
	 * </p>
	 * 
	 * @param id
	 */
	protected void setId(String id) {
		_id = id;
	}

	/**
	 * Returns the description.
	 * 
	 * @return description
	 */
	@Column(name = "DSCPTN", nullable = false, length = 100)
	@NotNull
	@Size(max = 100)
	public String getDescription() {
		return _description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		_description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "distinguishingFeature")
	@JsonIgnoreProperties("distinguishingFeature")
	public Set<DistinguishingFeatureDetail> getDistinguishingFeatureDetails() {
		return _distinguishingFeatureDetails;
	}

	public void setDistinguishingFeatureDetails(final Set<DistinguishingFeatureDetail> distinguishingFeatureDetails) {
		_distinguishingFeatureDetails = distinguishingFeatureDetails;
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == this
				|| (obj instanceof DistinguishingFeature && Objects.equals(_id, ((DistinguishingFeature) obj)._id));
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(_id);
	}

	@Override
	public String toString() {
		return "Code: " + _id + ", Description: " + _description;
	}
}
