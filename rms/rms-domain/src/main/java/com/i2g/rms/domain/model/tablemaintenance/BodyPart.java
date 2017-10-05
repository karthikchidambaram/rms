package com.i2g.rms.domain.model.tablemaintenance;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.AbstractDataModel;
import com.i2g.rms.domain.model.InjuredPerson;

/**
 * Entity for representing allowable values for the drop down "Body Parts" in
 * Incident Details tab page.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_BDY_PRTS")
@JsonIgnoreProperties({"injuredPersons"})
public class BodyPart extends AbstractDataModel<String> implements Serializable {
	
	public enum BodyPartFrontOrBack {
		
		FRONT("Front"),
		BACK("Back");

		public String value;

		private BodyPartFrontOrBack(final String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _id;
	private String _description;
	private Set<InjuredPerson> _injuredPersons = new HashSet<InjuredPerson>(0);
	private BodyPartFrontOrBack _bodyPartFrontOrBack;

	/**
	 * Default empty constructor required for Hibernate.
	 */
	public BodyPart() {
	}

	/**
	 * Creates a new instance of {@code BodyPart} with the specified code.
	 * 
	 * @param code
	 */
	public BodyPart(final String code) {
		_id = Objects.requireNonNull(code, "Body parts code cannot be null.");
	}

	/**
	 * Creates a new instance of {@code BodyPart} with the specified code and
	 * description.
	 * 
	 * @param code
	 * @param description
	 */
	public BodyPart(final String code, final String description) {
		_id = Objects.requireNonNull(code, "Body parts code cannot be null.");
		_description = Objects.requireNonNull(description, "Body parts description cannot be null.");
	}
	
	public BodyPart(final String code, final String description, final BodyPartFrontOrBack bodyPartFrontOrBack) {
		_id = Objects.requireNonNull(code, "Body parts code cannot be null.");
		_description = Objects.requireNonNull(description, "Body parts description cannot be null.");
		_bodyPartFrontOrBack = Objects.requireNonNull(bodyPartFrontOrBack, "Body part front or back must be mentioned.");
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
	 * Sets the Body Parts.
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

	/**
	 * @return the injuredPersons
	 */
	@ManyToMany(mappedBy = "bodyParts")
	public Set<InjuredPerson> getInjuredPersons() {
		return _injuredPersons;
	}

	/**
	 * @param injuredPersons
	 *            the injuredPersons to set
	 */
	public void setInjuredPersons(final Set<InjuredPerson> injuredPersons) {
		_injuredPersons = injuredPersons;
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == this || (obj instanceof BodyPart && Objects.equals(_id, ((BodyPart) obj)._id));
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(_id);
	}

	@Override
	public String toString() {
		return "Code: " + _id + ", Description: " + _description;
	}
	
	@Column(name = "FRNT_BCK")
	@Enumerated(EnumType.STRING)
	public BodyPartFrontOrBack getBodyPartFrontOrBack() {
		return _bodyPartFrontOrBack;
	}

	public void setBodyPartFrontOrBack(final BodyPartFrontOrBack bodyPartFrontOrBack) {
		_bodyPartFrontOrBack = bodyPartFrontOrBack;
	}	
}
