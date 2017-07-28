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

import com.i2g.rms.domain.model.AbstractDataModel;

/**
 * Entity for representing allowable values for the drop down "Injury Type
 * Detail Specifications" in Incident Details tab page.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_INJRY_TYP_CHLD_SUB")
public class InjuryTypeDetailSpec extends AbstractDataModel<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _id;
	private String _description;
	private InjuryTypeDetail _injuryTypeDetail;

	/**
	 * Default empty constructor required for Hibernate.
	 */
	public InjuryTypeDetailSpec() {
	}

	/**
	 * Creates a new instance of {@code InjuryTypeDetailSpec} with the
	 * specified code.
	 * 
	 * @param code
	 */
	public InjuryTypeDetailSpec(final String code) {
		_id = Objects.requireNonNull(code, "Injury type details specification code cannot be null.");
	}

	/**
	 * Creates a new instance of {@code InjuryTypeDetailSpec} with the
	 * specified code and description.
	 * 
	 * @param code
	 * @param description
	 */
	public InjuryTypeDetailSpec(final String code, final String description) {
		_id = Objects.requireNonNull(code, "Injury type details specification code cannot be null.");
		_description = Objects.requireNonNull(description, "Injury type details specification description cannot be null.");
	}

	/**
	 * Creates a new instance of {@code InjuryTypeDetailSpec} with the
	 * specified code, description and injury type details.
	 * 
	 * @param code
	 * @param description
	 * @param injuryTypeDetail
	 */
	public InjuryTypeDetailSpec(final String code, final String description, final InjuryTypeDetail injuryTypeDetails) {
		_id = Objects.requireNonNull(code, "Injury type detail specification code cannot be null.");
		_description = Objects.requireNonNull(description, "Injury type detail specification description cannot be null.");
		_injuryTypeDetail = Objects.requireNonNull(injuryTypeDetails, "Injury type detail cannot be null.");
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
	 * Sets the Injury Type Detail Specifications.
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
	@Size(min = 1, max = 16, message = "Injury type detail must be between {min} and {max} characters")
	public InjuryTypeDetail getInjuryTypeDetail() {
		return _injuryTypeDetail;
	}

	public void setInjuryTypeDetail(InjuryTypeDetail injuryTypeDetail) {
		_injuryTypeDetail = injuryTypeDetail;
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == this
				|| (obj instanceof InjuryTypeDetailSpec && Objects.equals(_id, ((InjuryTypeDetailSpec) obj)._id));
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
