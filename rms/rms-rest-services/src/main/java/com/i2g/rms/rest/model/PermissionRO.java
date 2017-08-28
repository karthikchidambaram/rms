package com.i2g.rms.rest.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * REST Object for returning permissions associated to a role to the REST
 * client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissionRO extends AbstractEntityRO {

	private long _id;
	private String _permissionName;
	private String _permissionDescription;
	private Set<RoleRO> _roles = new HashSet<RoleRO>(0);

	public long getId() {
		return _id;
	}

	public void setId(final long id) {
		_id = id;
	}

	public String getPermissionName() {
		return _permissionName;
	}

	public void setPermissionName(final String permissionName) {
		_permissionName = permissionName;
	}

	public String getPermissionDescription() {
		return _permissionDescription;
	}

	public void setPermissionDescription(final String permissionDescription) {
		_permissionDescription = permissionDescription;
	}
	
	public Set<RoleRO> getRoles() {
		return _roles;
	}

	public void setRoles(final Set<RoleRO> roles) {
		_roles = roles;
	}
}
