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
 * Entity for representing allowable values for the drop down "Incident
 * Location" in Incident Details tab page.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_INC_LOC")
public class IncidentLocation extends AbstractDataModel<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _id;
	private String _description;
	private Set<IncidentLocationDetail> _incidentLocationDetails = new HashSet<IncidentLocationDetail>(0);

	/**
	 * Default empty constructor required for Hibernate.
	 */
	public IncidentLocation() {
	}

	/**
	 * Creates a new instance of {@code IncidentLocation} with the specified
	 * code.
	 * 
	 * @param code
	 */
	public IncidentLocation(final String code) {
		_id = Objects.requireNonNull(code, "Incident location code cannot be null.");
	}

	/**
	 * Creates a new instance of {@code IncidentLocation} with the specified
	 * code and description.
	 * 
	 * @param code
	 * @param description
	 */
	public IncidentLocation(final String code, final String description) {
		_id = Objects.requireNonNull(code, "Incident location code cannot be null.");
		_description = Objects.requireNonNull(description, "Incident location description cannot be null.");
	}

	/**
	 * Creates a new instance of {@code IncidentLocation} with the specified
	 * code, description and incident location details.
	 * 
	 * @param code
	 * @param description
	 * @param incidentLocationDetails
	 */
	public IncidentLocation(final String code, final String description, final Set<IncidentLocationDetail> incidentLocationDetails) {
		_id = Objects.requireNonNull(code, "Incident location code cannot be null.");
		_description = Objects.requireNonNull(description, "Incident location description cannot be null.");
		_incidentLocationDetails = Objects.requireNonNull(incidentLocationDetails, "Incident location details cannot be null or empty.");
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
	 * Sets the Incident Location.
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "incidentLocation")
	@JsonIgnoreProperties("incidentLocation")
	public Set<IncidentLocationDetail> getIncidentLocationDetails() {
		return _incidentLocationDetails;		
	}

	public void setIncidentLocationDetails(final Set<IncidentLocationDetail> incidentLocationDetails) {
		_incidentLocationDetails = incidentLocationDetails;
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == this || (obj instanceof IncidentLocation && Objects.equals(_id, ((IncidentLocation) obj)._id));
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
