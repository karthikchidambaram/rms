package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity representation of Department.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_DEPT")
public class Department extends AbstractDataModel<String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID */
	private String _id;
	private String _description;
	private Organization _organization;
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	public Department() {
	}

	/**
	 * Creates a new instance of {@code Department} with the specified
	 * code.
	 * 
	 * @param code
	 */
	public Department(final String code) {
		_id = Objects.requireNonNull(code, "Department code cannot be null.");
	}
	
	/**
	 * Creates a new instance of {@code Department} with the specified
	 * code and description.
	 * 
	 * @param code
	 * @param description
	 */
	public Department(final String code, final String description) {
		_id = Objects.requireNonNull(code, "Department code cannot be null.");
		_description = Objects.requireNonNull(description, "Department description cannot be null.");
	}
	
	/**
	 * Creates a new instance of {@code Department} with the specified
	 * code and description.
	 * 
	 * @param code
	 * @param description
	 */
	public Department(final String code, final String description, final Organization organization) {
		_id = Objects.requireNonNull(code, "Department code cannot be null.");
		_description = Objects.requireNonNull(description, "Department description cannot be null.");
		_organization = Objects.requireNonNull(organization, "Organization cannot be null in a department.");
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
	 * Sets the Department.
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
	
	@Override
	public boolean equals(final Object obj) {
		return obj == this || (obj instanceof Department && Objects.equals(_id, ((Department) obj)._id));
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(_id);
	}

	@Override
	public String toString() {
		return "Code: " + _id + ", Description: " + _description;
	}

	/**
	 * @return the organization
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORG_CDE")
	@Size(min = 1, max = 16, message = "Organization code must be between {min} and {max} characters")
	public Organization getOrganization() {
		return _organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(final Organization organization) {
		_organization = organization;
	}	
}
