package com.i2g.rms.domain.model.tablemaintenance;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.AbstractDataModel;

/**
 * Entity for representing allowable values for the drop down "Incident Location
 * Detail" in Incident Details tab page.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_INC_LOC_CHLD")
@JsonIgnoreProperties({"incidentLocation"})
public class IncidentLocationDetail extends AbstractDataModel<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _id;
	private String _description;
	private IncidentLocation _incidentLocation;

	/**
	 * Default empty constructor required for Hibernate.
	 */
	public IncidentLocationDetail() {
	}

	/**
	 * Creates a new instance of {@code IncidentLocationDetail} with the
	 * specified code.
	 * 
	 * @param code
	 */
	public IncidentLocationDetail(final String code) {
		_id = Objects.requireNonNull(code, "Incident location detail code cannot be null.");
	}

	/**
	 * Creates a new instance of {@code IncidentLocationDetail} with the
	 * specified code and description.
	 * 
	 * @param code
	 * @param description
	 */
	public IncidentLocationDetail(final String code, final String description) {
		_id = Objects.requireNonNull(code, "Incident location detail code cannot be null.");
		_description = Objects.requireNonNull(description, "Incident location detail description cannot be null.");
	}

	/**
	 * Creates a new instance of {@code IncidentLocationDetail} with the
	 * specified code, description and incident location.
	 * 
	 * @param code
	 * @param description
	 * @param incidentLocation
	 */
	public IncidentLocationDetail(final String code, final String description, final IncidentLocation incidentLocation) {
		_id = Objects.requireNonNull(code, "Incident location detail code cannot be null.");
		_description = Objects.requireNonNull(description, "Incident location detail description cannot be null.");
		_incidentLocation = Objects.requireNonNull(incidentLocation, "Incident location cannot be null.");
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
	public IncidentLocation getIncidentLocation() {
		return _incidentLocation;
	}

	public void setIncidentLocation(IncidentLocation incidentLocation) {
		_incidentLocation = incidentLocation;
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == this
				|| (obj instanceof IncidentLocationDetail && Objects.equals(_id, ((IncidentLocationDetail) obj)._id));
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
