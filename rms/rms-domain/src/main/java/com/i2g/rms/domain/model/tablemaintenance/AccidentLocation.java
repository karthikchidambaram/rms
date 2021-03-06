package com.i2g.rms.domain.model.tablemaintenance;

import java.io.Serializable;
import java.util.Collections;
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
 * Entity for representing allowable values for the drop down "Accident
 * Location" in Incident Details tab page.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_ACC_LOC")
public class AccidentLocation extends AbstractDataModel<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String _id;
	private String _description;
	private Set<AccidentLocationDetail> _accidentLocationDetails = new HashSet<AccidentLocationDetail>(0);

	/**
	 * Default empty constructor required for Hibernate.
	 */
	public AccidentLocation() {
	}

	/**
	 * Creates a new instance of {@code AccidentLocation} with the specified
	 * code.
	 * 
	 * @param code
	 */
	public AccidentLocation(final String code) {
		_id = Objects.requireNonNull(code, "Accident location code cannot be null.");
	}

	/**
	 * Creates a new instance of {@code AccidentLocation} with the specified
	 * code and description.
	 * 
	 * @param code
	 * @param description
	 */
	public AccidentLocation(final String code, final String description) {
		_id = Objects.requireNonNull(code, "Accident location code cannot be null.");
		_description = Objects.requireNonNull(description, "Accident location description cannot be null.");
	}

	/**
	 * Creates a new instance of {@code AccidentLocation} with the specified
	 * code, description and accident location details.
	 * 
	 * @param code
	 * @param description
	 * @param accidentLocationDetails
	 */
	public AccidentLocation(final String code, final String description, final Set<AccidentLocationDetail> accidentLocationDetails) {
		_id = Objects.requireNonNull(code, "Accident location code cannot be null.");
		_description = Objects.requireNonNull(description, "Accident location description cannot be null.");
		_accidentLocationDetails = Objects.requireNonNull(accidentLocationDetails, "Accident location detail cannot be null or empty.");
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
	 * Sets the Accident Location.
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accidentLocation")
	@JsonIgnoreProperties("accidentLocation")
	public Set<AccidentLocationDetail> getAccidentLocationDetails() {
		return _accidentLocationDetails;		
	}

	public void setAccidentLocationDetails(Set<AccidentLocationDetail> accidentLocationDetails) {
		_accidentLocationDetails = accidentLocationDetails;
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == this || (obj instanceof AccidentLocation && Objects.equals(_id, ((AccidentLocation) obj)._id));
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
