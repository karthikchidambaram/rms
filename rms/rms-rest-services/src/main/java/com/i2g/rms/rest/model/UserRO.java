package com.i2g.rms.rest.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.tablemaintenance.EmployeeTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.GenderTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.PositionRO;

/**
 * REST Object for returning user details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRO extends AbstractEntityRO {

	private long _id;
	private String _loginId;
	private String _password;
	private String _firstName;
	private String _lastName;
	private String _middleName;
	private String _title;
	private String _nameSuffix;
	private StatusFlagRO _statusFlag;
	private Set<PasswordHistoryRO> _passwordHistory;
	private Set<RoleRO> _roles;
	private Long _expires;
	private String _username;	
	private GenderTypeRO _genderType;
	private LocalDate _dateOfBirth;
	private Integer _age;
	private LocalDate _dateOfJoining;
	private LocalDate _dateOfLeaving;
	private String _phone;
	private String _alternatePhone;
	private String _fax;
	private String _email;
	private String _employeeId;
	private PositionRO _position;
	private EmployeeTypeRO _employeeType;
	private UserRO _manager;
	private Set<UserRO> _subordinates = new HashSet<UserRO>(0);
	private Set<AddressRO> _addresses = new HashSet<AddressRO>(0);
	private String _genderTypeOther;
	private String _employeeTypeOther;

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

	public String getNameSuffix() {
		return _nameSuffix;
	}

	public void setNameSuffix(final String nameSuffix) {
		_nameSuffix = nameSuffix;
	}

	public StatusFlagRO getStatusFlag() {
		return _statusFlag;
	}

	public void setStatusFlag(final StatusFlagRO statusFlag) {
		_statusFlag = statusFlag;
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

	public Long getExpires() {
		return _expires;
	}

	public void setExpires(final Long expires) {
		if (expires != null) {
			_expires = expires;
		} else {
			_expires = 0l;
		}
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

	/**
	 * @return the genderType
	 */
	public GenderTypeRO getGenderType() {
		return _genderType;
	}

	/**
	 * @param genderType the genderType to set
	 */
	public void setGenderType(final GenderTypeRO genderType) {
		_genderType = genderType;
	}

	/**
	 * @return the dateOfBirth
	 */
	public LocalDate getDateOfBirth() {
		return _dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(final LocalDate dateOfBirth) {
		_dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return _age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(final Integer age) {
		if (_age != null) {
			_age = age;
		} else {
			_age = 0;
		}
	}

	/**
	 * @return the dateOfJoining
	 */
	public LocalDate getDateOfJoining() {
		return _dateOfJoining;
	}

	/**
	 * @param dateOfJoining the dateOfJoining to set
	 */
	public void setDateOfJoining(final LocalDate dateOfJoining) {
		_dateOfJoining = dateOfJoining;
	}

	/**
	 * @return the dateOfLeaving
	 */
	public LocalDate getDateOfLeaving() {
		return _dateOfLeaving;
	}

	/**
	 * @param dateOfLeaving the dateOfLeaving to set
	 */
	public void setDateOfLeaving(final LocalDate dateOfLeaving) {
		_dateOfLeaving = dateOfLeaving;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return _phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(final String phone) {
		_phone = phone;
	}

	/**
	 * @return the alternatePhone
	 */
	public String getAlternatePhone() {
		return _alternatePhone;
	}

	/**
	 * @param alternatePhone the alternatePhone to set
	 */
	public void setAlternatePhone(final String alternatePhone) {
		_alternatePhone = alternatePhone;
	}
	
	/**
	 * @return the fax
	 */
	public String getFax() {
		return _fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(final String fax) {
		_fax = fax;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return _email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(final String email) {
		_email = email;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return _employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(final String employeeId) {
		_employeeId = employeeId;
	}

	/**
	 * @return the position
	 */
	public PositionRO getPosition() {
		return _position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(final PositionRO position) {
		_position = position;
	}

	/**
	 * @return the employeeType
	 */
	public EmployeeTypeRO getEmployeeType() {
		return _employeeType;
	}

	/**
	 * @param employeeType the employeeType to set
	 */
	public void setEmployeeType(final EmployeeTypeRO employeeType) {
		_employeeType = employeeType;
	}

	/**
	 * @return the manager
	 */
	public UserRO getManager() {
		return _manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(final UserRO manager) {
		_manager = manager;
	}

	/**
	 * @return the subordinates
	 */
	public Set<UserRO> getSubordinates() {
		return _subordinates;
	}

	/**
	 * @param subordinates the subordinates to set
	 */
	public void setSubordinates(final Set<UserRO> subordinates) {
		_subordinates = subordinates;
	}

	public Set<AddressRO> getAddresses() {
		return _addresses;
	}

	public void setAddresses(final Set<AddressRO> address) {
		_addresses = address;
	}

	/**
	 * @return the genderTypeOther
	 */
	public String getGenderTypeOther() {
		return _genderTypeOther;
	}

	/**
	 * @param genderTypeOther the genderTypeOther to set
	 */
	public void setGenderTypeOther(final String genderTypeOther) {
		_genderTypeOther = genderTypeOther;
	}

	/**
	 * @return the employeeTypeOther
	 */
	public String getEmployeeTypeOther() {
		return _employeeTypeOther;
	}

	/**
	 * @param employeeTypeOther the employeeTypeOther to set
	 */
	public void setEmployeeTypeOther(final String employeeTypeOther) {
		_employeeTypeOther = employeeTypeOther;
	}	
}
