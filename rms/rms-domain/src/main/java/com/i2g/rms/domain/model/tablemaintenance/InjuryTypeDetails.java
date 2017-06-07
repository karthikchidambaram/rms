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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.i2g.rms.domain.model.AbstractDataModel;

/**
 * Entity for representing allowable values for the drop down "Injury Type
 * Details" in Incident Details tab page.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_INJRY_TYP_CHLD")
public class InjuryTypeDetails extends AbstractDataModel<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _id;
	private String _description;
	private InjuryType _injuryType;
	private Set<InjuryTypeDetailsSpec> _injuryTypeDetailsSpec = new HashSet<InjuryTypeDetailsSpec>(0);
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	public InjuryTypeDetails() {
	}

	/**
	 * Creates a new instance of {@code InjuryTypeDetails} with the specified
	 * code.
	 * 
	 * @param code
	 */
	public InjuryTypeDetails(final String code) {
		_id = Objects.requireNonNull(code, "Injury type detail code cannot be null.");
	}

	/**
	 * Creates a new instance of {@code InjuryTypeDetails} with the specified
	 * code and description.
	 * 
	 * @param code
	 * @param description
	 */
	public InjuryTypeDetails(final String code, final String description) {
		_id = Objects.requireNonNull(code, "Injury type detail code cannot be null.");
		_description = Objects.requireNonNull(description, "Injury type detail description cannot be null.");
	}

	/**
	 * Creates a new instance of {@code InjuryTypeDetails} with the specified
	 * code, description and injury type.
	 * 
	 * @param code
	 * @param description
	 * @param injuryType
	 */
	public InjuryTypeDetails(final String code, final String description, final InjuryType injuryType) {
		_id = Objects.requireNonNull(code, "Injury type detail code cannot be null.");
		_description = Objects.requireNonNull(description, "Injury type detail description cannot be null.");
		_injuryType = Objects.requireNonNull(injuryType, "Injury type cannot be null.");
	}
	
	/**
	 * Creates a new instance of {@code InjuryTypeDetails} with the specified
	 * code, description and injury type.
	 * 
	 * @param code
	 * @param description
	 * @param injuryType
	 * @param injuryTypeDetailsSpec
	 */
	public InjuryTypeDetails(final String code, final String description, final InjuryType injuryType, final Set<InjuryTypeDetailsSpec> injuryTypeDetailsSpec) {
		_id = Objects.requireNonNull(code, "Injury type detail code cannot be null.");
		_description = Objects.requireNonNull(description, "Injury type detail description cannot be null.");
		_injuryType = Objects.requireNonNull(injuryType, "Injury type cannot be null.");
		_injuryTypeDetailsSpec = Objects.requireNonNull(injuryTypeDetailsSpec, "Injury type detail specifications cannot be null or empty.");
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
	 * Sets the Injury Type Details.
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REF_CDE")
	public InjuryType getInjuryType() {
		return _injuryType;
	}

	public void setInjuryType(InjuryType injuryType) {
		_injuryType = injuryType;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "injuryTypeDetails")
	public Set<InjuryTypeDetailsSpec> getInjuryTypeDetailsSpec() {
		return _injuryTypeDetailsSpec;
	}

	public void setInjuryTypeDetailsSpec(Set<InjuryTypeDetailsSpec> injuryTypeDetailsSpec) {
		_injuryTypeDetailsSpec = injuryTypeDetailsSpec;
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == this || (obj instanceof InjuryTypeDetails && Objects.equals(_id, ((InjuryTypeDetails) obj)._id));
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
