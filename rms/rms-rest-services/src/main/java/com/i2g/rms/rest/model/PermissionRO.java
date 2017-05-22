package com.i2g.rms.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * REST Object for returning permissions associated to a role to the REST
 * client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissionRO extends AbstractEntityRO {

	private long _id;
	private String _permissionName;
	private String _permissionDescription;

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public String getPermissionName() {
		return _permissionName;
	}

	public void setPermissionName(String permissionName) {
		_permissionName = permissionName;
	}

	public String getPermissionDescription() {
		return _permissionDescription;
	}

	public void setPermissionDescription(String permissionDescription) {
		_permissionDescription = permissionDescription;
	}
}
