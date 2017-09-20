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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.AbstractDataModel;
import com.i2g.rms.domain.model.OfficeAddress;

/**
 * Entity representation of Organization.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_ORG")
@JsonIgnoreProperties({ "officeAddresses" })
public class Organization extends AbstractDataModel<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID */
	private String _id;
	private String _description;
	private Set<OfficeAddress> _officeAddresses = new HashSet<OfficeAddress>(0);

	/**
	 * Default empty constructor required for Hibernate.
	 */
	public Organization() {
	}

	/**
	 * Creates a new instance of {@code Organization} with the specified code.
	 * 
	 * @param code
	 */
	public Organization(final String code) {
		_id = Objects.requireNonNull(code, "Organization code cannot be null.");
	}

	/**
	 * Creates a new instance of {@code Organization} with the specified code
	 * and description.
	 * 
	 * @param code
	 * @param description
	 */
	public Organization(final String code, final String description) {
		_id = Objects.requireNonNull(code, "Organization code cannot be null.");
		_description = Objects.requireNonNull(description, "Organization description cannot be null.");
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
	 * Sets the Organization.
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
	protected void setId(final String id) {
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "organization")
	@Fetch(FetchMode.SUBSELECT)
	public Set<OfficeAddress> getOfficeAddresses() {
		return _officeAddresses;
	}

	public void setOfficeAddresses(final Set<OfficeAddress> officeAddresses) {
		_officeAddresses = officeAddresses;
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == this || (obj instanceof Organization && Objects.equals(_id, ((Organization) obj)._id));
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
