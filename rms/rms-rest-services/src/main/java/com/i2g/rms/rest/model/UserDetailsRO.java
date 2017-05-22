package com.i2g.rms.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * REST Object for returning user details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsRO extends AbstractEntityRO {

	private long _id;
	private String _phone;
	private String _email;
	private String _address;
	private String _pin;
	private String _state;
	private String _country;
	private UserRO _user;

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public String getPhone() {
		return _phone;
	}

	public void setPhone(String phone) {
		_phone = phone;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public String getPin() {
		return _pin;
	}

	public void setPin(String pin) {
		_pin = pin;
	}

	public String getState() {
		return _state;
	}

	public void setState(String state) {
		_state = state;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public UserRO getUser() {
		return _user;
	}

	public void setUser(UserRO user) {
		_user = user;
	}

}
