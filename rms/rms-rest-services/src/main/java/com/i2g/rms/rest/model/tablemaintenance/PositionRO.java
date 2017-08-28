package com.i2g.rms.rest.model.tablemaintenance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;

/**
 * REST Object for returning table maintenance details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionRO extends AbstractEntityRO {

	private String _id;
	private String _description;
	private PositionLevelRO _positionLevel;
	private OrganizationRO _organization;
	private DepartmentRO _department;
	private String _parentOrganizationId;
	private String _parentDepartmentId;
	private String _parentPositionLevelId;

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
	 * @param organization
	 *            the organization to set
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
	 * @param positionLevel
	 *            the positionLevel to set
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
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(final DepartmentRO department) {
		_department = department;
	}

	/**
	 * @return the parentOrganizationId
	 */
	public String getParentOrganizationId() {
		return _parentOrganizationId;
	}

	/**
	 * @param parentOrganizationId
	 *            the parentOrganizationId to set
	 */
	public void setParentOrganizationId(final String parentOrganizationId) {
		_parentOrganizationId = parentOrganizationId;
	}

	/**
	 * @return the parentDepartmentId
	 */
	public String getParentDepartmentId() {
		return _parentDepartmentId;
	}

	/**
	 * @param parentDepartmentId
	 *            the parentDepartmentId to set
	 */
	public void setParentDepartmentId(final String parentDepartmentId) {
		_parentDepartmentId = parentDepartmentId;
	}

	/**
	 * @return the parentPositionLevelId
	 */
	public String getParentPositionLevelId() {
		return _parentPositionLevelId;
	}

	/**
	 * @param parentPositionLevelId the parentPositionLevelId to set
	 */
	public void setParentPositionLevelId(final String parentPositionLevelId) {
		_parentPositionLevelId = parentPositionLevelId;
	}	
}
