package com.i2g.rms.domain.model.lookup;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.i2g.rms.domain.model.AbstractDataModel;

/**
 * User lookup entity object based on a view.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_USR_LKUP_VW")
public class UserLookup extends AbstractDataModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String userLoginId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String phone;
	private String alternatePhone;
	private String email;
	private String employeeId;
	private String managerLoginId;
	private String employeeTypeCode;
	private String employeeTypeDescription;

	/**
	 * @return the id
	 */
	@Id
	@Column(name = "USR_ID")
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userLoginId
	 */
	@Column(name = "LGN_ID")
	public String getUserLoginId() {
		return userLoginId;
	}

	/**
	 * @param userLoginId
	 *            the userLoginId to set
	 */
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	/**
	 * @return the firstName
	 */
	@Column(name = "FNAME")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	@Column(name = "MNAME")
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	@Column(name = "LNAME")
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phone
	 */
	@Column(name = "PHN")
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the alternatePhone
	 */
	@Column(name = "ALT_PHN")
	public String getAlternatePhone() {
		return alternatePhone;
	}

	/**
	 * @param alternatePhone
	 *            the alternatePhone to set
	 */
	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}

	/**
	 * @return the email
	 */
	@Column(name = "EML")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the employeeId
	 */
	@Column(name = "EMP_ID")
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the managerLoginId
	 */
	@Column(name = "MGR_LGN_ID")
	public String getManagerLoginId() {
		return managerLoginId;
	}

	/**
	 * @param managerLoginId
	 *            the managerLoginId to set
	 */
	public void setManagerLoginId(String managerLoginId) {
		this.managerLoginId = managerLoginId;
	}

	/**
	 * @return the employeeTypeCode
	 */
	@Column(name = "EMP_TYP_CDE")
	public String getEmployeeTypeCode() {
		return employeeTypeCode;
	}

	/**
	 * @param employeeTypeCode
	 *            the employeeTypeCode to set
	 */
	public void setEmployeeTypeCode(String employeeTypeCode) {
		this.employeeTypeCode = employeeTypeCode;
	}

	/**
	 * @return the employeeTypeDescription
	 */
	@Column(name = "EMP_TYP_DSCPTN")
	public String getEmployeeTypeDescription() {
		return employeeTypeDescription;
	}

	/**
	 * @param employeeTypeDescription
	 *            the employeeTypeDescription to set
	 */
	public void setEmployeeTypeDescription(String employeeTypeDescription) {
		this.employeeTypeDescription = employeeTypeDescription;
	}
}
