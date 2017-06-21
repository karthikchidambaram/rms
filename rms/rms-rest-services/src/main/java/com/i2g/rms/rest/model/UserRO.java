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
	private String _prefix;
	private String _suffix;
	private UserStatus _status;
	private Set<UserDetailsRO> _userDetails;
	private Set<PasswordHistoryRO> _passwordHistory;
	private Set<GroupRO> _groups;
	private Set<RoleRO> _roles;
	private long _expires;
	private String _username;

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public String getLoginId() {
		return _loginId;
	}

	public void setLoginId(String loginId) {
		_loginId = loginId;
		_username = loginId;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
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

	public String getMiddleName() {
		return _middleName;
	}

	public void setMiddleName(String middleName) {
		_middleName = middleName;
	}

	public String getPrefix() {
		return _prefix;
	}

	public void setPrefix(String prefix) {
		_prefix = prefix;
	}

	public String getSuffix() {
		return _suffix;
	}

	public void setSuffix(String suffix) {
		_suffix = suffix;
	}

	public UserStatus getStatus() {
		return _status;
	}

	public void setStatus(UserStatus status) {
		_status = status;
	}

	public Set<UserDetailsRO> getUserDetails() {
		return _userDetails;
	}

	public void setUserDetails(Set<UserDetailsRO> userDetails) {
		_userDetails = userDetails;
	}

	public Set<PasswordHistoryRO> getPasswordHistory() {
		return _passwordHistory;
	}

	public void setPasswordHistory(Set<PasswordHistoryRO> passwordHistory) {
		_passwordHistory = passwordHistory;
	}

	public Set<GroupRO> getGroups() {
		return _groups;
	}

	public void setGroups(Set<GroupRO> groups) {
		_groups = groups;
	}

	public Set<RoleRO> getRoles() {
		return _roles;
	}

	public void setRoles(Set<RoleRO> roles) {
		_roles = roles;
	}

	@Override
	public String toString() {
		return "Id: " + _id + ", Login Id: " + _loginId + ", First Name: " + _firstName + ", Last Name: " + _lastName;
	}

	public long getExpires() {
		return _expires;
	}

	public void setExpires(long expires) {
		_expires = expires;
	}

	public String getUsername() {
		return _username;
	}

	public void setUsername(String username) {
		_username = username;
		_loginId = username;
	}

	public boolean hasRole(String roleName) {
		return getRoles().contains(roleName);
	}
}
