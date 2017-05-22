package com.i2g.rms.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * REST Object for returning password history details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordHistoryRO extends AbstractEntityRO {

	private long _id;
	private String _password;
	private String _reasonCode;
	private String _reasonDescription;
	private UserRO _user;

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public String getReasonCode() {
		return _reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		_reasonCode = reasonCode;
	}

	public String getReasonDescription() {
		return _reasonDescription;
	}

	public void setReasonDescription(String reasonDescription) {
		_reasonDescription = reasonDescription;
	}

	public UserRO getUser() {
		return _user;
	}

	public void setUser(UserRO user) {
		_user = user;
	}
}
