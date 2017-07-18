package com.i2g.rms.rest.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.i2g.rms.domain.model.User.UserStatus;

/**
 * REST Object for returning user details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRO extends AbstractEntityRO {

	private long _id;
	private String _loginId;
	private String _password;
	private String _firstName;
	private String _lastName;
	private String _middleName;
	private String _title;
	private String _suffix;
	private UserStatus _status;
	private Set<PasswordHistoryRO> _passwordHistory;
	private Set<RoleRO> _roles;
	private long _expires;
	private String _username;	 

	public long getId() {
		return _id;
	}

	public void setId(final long id) {
		_id = id;
	}

	public String getLoginId() {
		return _loginId;
	}

	public void setLoginId(final String loginId) {
		_loginId = loginId;
		_username = loginId;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(final String password) {
		_password = password;
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

	public String getMiddleName() {
		return _middleName;
	}

	public void setMiddleName(final String middleName) {
		_middleName = middleName;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(final String title) {
		_title = title;
	}

	public String getSuffix() {
		return _suffix;
	}

	public void setSuffix(final String suffix) {
		_suffix = suffix;
	}

	public UserStatus getStatus() {
		return _status;
	}

	public void setStatus(final UserStatus status) {
		_status = status;
	}

	public Set<PasswordHistoryRO> getPasswordHistory() {
		return _passwordHistory;
	}

	public void setPasswordHistory(final Set<PasswordHistoryRO> passwordHistory) {
		_passwordHistory = passwordHistory;
	}

	public Set<RoleRO> getRoles() {
		return _roles;
	}

	public void setRoles(final Set<RoleRO> roles) {
		_roles = roles;
	}

	@Override
	public String toString() {
		return "Id: " + _id + ", Login Id: " + _loginId + ", First Name: " + _firstName + ", Last Name: " + _lastName;
	}

	public long getExpires() {
		return _expires;
	}

	public void setExpires(final long expires) {
		_expires = expires;
	}

	public String getUsername() {
		return _username;
	}

	public void setUsername(final String username) {
		_username = username;
		_loginId = username;
	}

	public boolean hasRole(final String roleName) {
		return getRoles().contains(roleName);
	}	
}
