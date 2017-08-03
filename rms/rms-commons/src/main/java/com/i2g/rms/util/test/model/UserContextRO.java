package com.i2g.rms.util.test.model;

import java.util.List;

public class UserContextRO {

	private String _userId;
	private String _firstName;
	private String _lastName;
	private List<String> _roles;

	public String getUserId() {
		return _userId;
	}

	public void setUserId(final String userId) {
		_userId = userId;
	}

	public List<String> getRoles() {
		return _roles;
	}

	public void setRoles(final List<String> roles) {
		_roles = roles;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(final String firstName) {
		_firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(final String lastName) {
		_lastName = lastName;
	}
}
