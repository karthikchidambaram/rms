package com.i2g.rms.rest.model;

import java.util.List;

public class UserContextRO {

	private String _userId;
	private List<String> _roles;

	public String getUserId() {
		return _userId;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	public List<String> getRoles() {
		return _roles;
	}

	public void setRoles(List<String> roles) {
		_roles = roles;
	}

}
