package com.i2g.rms.util.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.i2g.rms.util.test.model.tablemaintenance.PositionLevelRO;

/**
 * REST Object for returning table maintenance details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionRO extends AbstractEntityRO {
	
	private String _id;
	private String _description;
	private PositionLevelRO _positionLevel;
	private OrganizationRO _organization;
	private DepartmentRO _department;

	public String getId() {
		return _id;
	}

	public void setId(final String id) {
		_id = id;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(final String description) {
		_description = description;
	}

	/**
	 * @return the organization
	 */
	public OrganizationRO getOrganization() {
		return _organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(final OrganizationRO organization) {
		_organization = organization;
	}

	/**
	 * @return the positionLevel
	 */
	public PositionLevelRO getPositionLevel() {
		return _positionLevel;
	}

	/**
	 * @param positionLevel the positionLevel to set
	 */
	public void setPositionLevel(final PositionLevelRO positionLevel) {
		_positionLevel = positionLevel;
	}

	/**
	 * @return the department
	 */
	public DepartmentRO getDepartment() {
		return _department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(final DepartmentRO department) {
		_department = department;
	}	
}
