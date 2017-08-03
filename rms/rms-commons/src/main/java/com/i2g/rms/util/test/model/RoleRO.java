package com.i2g.rms.util.test.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * REST Object for returning role details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleRO extends AbstractEntityRO {

	private long _id;
	private String _roleName;
	private String _roleDescription;
	private Set<PermissionRO> _permissions;
	
	public long getId() {
		return _id;
	}

	public void setId(final long id) {
		_id = id;
	}

	public String getRoleName() {
		return _roleName;
	}

	public void setRoleName(final String roleName) {
		_roleName = roleName;
	}

	public String getRoleDescription() {
		return _roleDescription;
	}

	public void setRoleDescription(final String roleDescription) {
		_roleDescription = roleDescription;
	}

	public Set<PermissionRO> getPermissions() {
		return _permissions;
	}

	public void setPermissions(final Set<PermissionRO> permissions) {
		_permissions = permissions;
	}	
}
