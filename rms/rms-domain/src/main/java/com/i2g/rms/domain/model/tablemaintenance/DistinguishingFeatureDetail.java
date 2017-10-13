package com.i2g.rms.domain.model.tablemaintenance;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.AbstractDataModel;
import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.Witness;

/**
 * Entity for representing allowable values for the drop down "Distinguishing
 * Feature Detail" in Incident Details tab page.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_DIST_FEA_CHLD")
@JsonIgnoreProperties({ "distinguishingFeature", "suspects", "injuredPersons", "crimeSuspects", "witnesses" })
public class DistinguishingFeatureDetail extends AbstractDataModel<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _id;
	private String _description;
	private DistinguishingFeature _distinguishingFeature;
	private Set<Suspect> _suspects = new HashSet<Suspect>(0);	
	private Set<InjuredPerson> _injuredPersons = new HashSet<InjuredPerson>(0);
	private Set<CrimeSuspect> _crimeSuspects = new HashSet<CrimeSuspect>(0);
	private Set<Witness> _witnesses = new HashSet<Witness>(0);
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	public DistinguishingFeatureDetail() {
	}

	/**
	 * Creates a new instance of {@code DistinguishingFeatureDetail} with the
	 * specified code.
	 * 
	 * @param code
	 */
	public DistinguishingFeatureDetail(final String code) {
		_id = Objects.requireNonNull(code, "Distinguishing feature detail code cannot be null.");
	}

	/**
	 * Creates a new instance of {@code DistinguishingFeatureDetail} with the
	 * specified code and description.
	 * 
	 * @param code
	 * @param description
	 */
	public DistinguishingFeatureDetail(final String code, final String description) {
		_id = Objects.requireNonNull(code, "Distinguishing feature detail code cannot be null.");
		_description = Objects.requireNonNull(description, "Distinguishing feature detail description cannot be null.");
	}

	/**
	 * Creates a new instance of {@code DistinguishingFeatureDetail} with the
	 * specified code, description and distinguishingFeature.
	 * 
	 * @param code
	 * @param description
	 * @param distinguishingFeatures
	 */
	public DistinguishingFeatureDetail(final String code, final String description,
			final DistinguishingFeature distinguishingFeature) {
		_id = Objects.requireNonNull(code, "Distinguishing feature detail code cannot be null.");
		_description = Objects.requireNonNull(description, "Distinguishing feature detail description cannot be null.");
		_distinguishingFeature = Objects.requireNonNull(distinguishingFeature,
				"Distinguishing feature cannot be null.");
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
	 * Sets the Distinguishing Feature Detail.
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
	public void setDescription(final String description) {
		_description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REF_CDE")
	public DistinguishingFeature getDistinguishingFeature() {
		return _distinguishingFeature;
	}

	public void setDistinguishingFeature(final DistinguishingFeature distinguishingFeature) {
		_distinguishingFeature = distinguishingFeature;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "distinguishingFeatureDetails")
	public Set<Suspect> getSuspects() {
		return _suspects;
	}

	public void setSuspects(final Set<Suspect> suspects) {
		_suspects = suspects;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "distinguishingFeatureDetails")
	public Set<InjuredPerson> getInjuredPersons() {
		return _injuredPersons;
	}

	public void setInjuredPersons(final Set<InjuredPerson> injuredPersons) {
		_injuredPersons = injuredPersons;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "distinguishingFeatureDetails")
	public Set<CrimeSuspect> getCrimeSuspects() {
		return _crimeSuspects;
	}

	public void setCrimeSuspects(final Set<CrimeSuspect> crimeSuspects) {
		_crimeSuspects = crimeSuspects;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "distinguishingFeatureDetails")
	public Set<Witness> getWitnesses() {
		return _witnesses;
	}

	public void setWitnesses(final Set<Witness> witnesses) {
		_witnesses = witnesses;
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == this || (obj instanceof DistinguishingFeatureDetail
				&& Objects.equals(_id, ((DistinguishingFeatureDetail) obj)._id));
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
