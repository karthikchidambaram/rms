package com.i2g.rms.rest.model;

import java.util.List;

public class UserContextRO {

	private String _userId;
	private String _firstName;
	private String _lastName;
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

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}
}
