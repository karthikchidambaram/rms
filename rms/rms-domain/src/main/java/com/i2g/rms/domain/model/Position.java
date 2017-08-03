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

import com.i2g.rms.domain.model.tablemaintenance.PositionLevel;

/**
 * Entity representation of Department.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_POSTN")
public class Position extends AbstractDataModel<String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID */
	private String _id;
	private String _description;
	private PositionLevel _positionLevel;
	private Organization _organization;
	private Department _department;
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	public Position() {
	}

	/**
	 * Creates a new instance of {@code Position} with the specified
	 * code.
	 * 
	 * @param code
	 */
	public Position(final String code) {
		_id = Objects.requireNonNull(code, "Position code cannot be null.");
	}
	
	/**
	 * Creates a new instance of {@code Position} with the specified
	 * code and description.
	 * 
	 * @param code
	 * @param description
	 * @param positionLevel
	 * @param organization
	 */
	public Position(final String code, final String description, final PositionLevel positionLevel, final Organization organization) {
		_id = Objects.requireNonNull(code, "Position code cannot be null.");
		_description = Objects.requireNonNull(description, "Position description cannot be null.");
		_positionLevel = Objects.requireNonNull(positionLevel, "Position level cannot be null.");
		_organization = Objects.requireNonNull(organization, "Organization cannot be null.");
	}
	
	/**
	 * Creates a new instance of {@code Position} with the specified
	 * code and description.
	 * 
	 * @param code
	 * @param description
	 * @param positionLevel
	 * @param department
	 */
	public Position(final String code, final String description, final PositionLevel positionLevel, final Department department) {
		_id = Objects.requireNonNull(code, "Position code cannot be null.");
		_description = Objects.requireNonNull(description, "Position description cannot be null.");
		_positionLevel = Objects.requireNonNull(positionLevel, "Position level cannot be null.");
		_department = Objects.requireNonNull(department, "Department cannot be null.");
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
	 * Sets the Position.
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
		return obj == this || (obj instanceof Position && Objects.equals(_id, ((Position) obj)._id));
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
	 * @return the postionLevel
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POSTN_LVL_CDE")
	@Size(min = 1, max = 16, message = "Position level code must be between {min} and {max} characters")
	public PositionLevel getPositionLevel() {
		return _positionLevel;
	}

	/**
	 * @param postionLevel the postionLevel to set
	 */
	public void setPositionLevel(final PositionLevel positionLevel) {
		_positionLevel = positionLevel;
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

	/**
	 * @return the department
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPT_CDE")
	@Size(min = 1, max = 16, message = "Department code must be between {min} and {max} characters")
	public Department getDepartment() {
		return _department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(final Department department) {
		_department = department;
	}	
}
