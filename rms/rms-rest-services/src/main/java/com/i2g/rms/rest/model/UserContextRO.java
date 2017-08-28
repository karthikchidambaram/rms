package com.i2g.rms.rest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserContextRO {

	private String _userId;
	private String _firstName;
	private String _lastName;
	private List<String> _roles;
	private String _xAuthToken;

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

	/**
	 * @return the xAuthToken
	 */
	@JsonProperty("XAuthToken")
	public String getXAuthToken() {
		return _xAuthToken;
	}

	/**
	 * @param xAuthToken the xAuthToken to set
	 */
	public void setXAuthToken(final String xAuthToken) {
		_xAuthToken = xAuthToken;
	}	
}
