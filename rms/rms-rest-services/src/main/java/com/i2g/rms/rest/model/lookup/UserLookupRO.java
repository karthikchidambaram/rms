package com.i2g.rms.rest.model.lookup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.StatusFlagRO;

/**
 * Search Incident RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLookupRO extends AbstractEntityRO {

	private long id;
	private String userLoginId;
	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private String nameSuffix;
	private StatusFlagRO statusFlag;
	private String genderTypeCode;
	private String genderTypeDescription;
	private String phone;
	private String alternatePhone;
	private String email;
	private String employeeId;
	private String managerLoginId;
	private String employeeTypeCode;
	private String employeeTypeDescription;
	private String positionCode;
	private String positionDescription;
	private String organizationCode;
	private String organizationDescription;
	private String departmentCode;
	private String departmentDescription;
	private String departmentOrganizationCode;
	private String positionLevelCode;
	private String positionLevelDescription;

	/**
	 * @return the id
	 */
	public long getId() {
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the firstName
	 */
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
	 * @return the nameSuffix
	 */
	public String getNameSuffix() {
		return nameSuffix;
	}

	/**
	 * @param nameSuffix
	 *            the nameSuffix to set
	 */
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	/**
	 * @return the statusFlag
	 */
	public StatusFlagRO getStatusFlag() {
		return statusFlag;
	}

	/**
	 * @param statusFlag
	 *            the statusFlag to set
	 */
	public void setStatusFlag(StatusFlagRO statusFlag) {
		this.statusFlag = statusFlag;
	}

	/**
	 * @return the genderTypeCode
	 */
	public String getGenderTypeCode() {
		return genderTypeCode;
	}

	/**
	 * @param genderTypeCode
	 *            the genderTypeCode to set
	 */
	public void setGenderTypeCode(String genderTypeCode) {
		this.genderTypeCode = genderTypeCode;
	}

	/**
	 * @return the genderTypeDescription
	 */
	public String getGenderTypeDescription() {
		return genderTypeDescription;
	}

	/**
	 * @param genderTypeDescription
	 *            the genderTypeDescription to set
	 */
	public void setGenderTypeDescription(String genderTypeDescription) {
		this.genderTypeDescription = genderTypeDescription;
	}

	/**
	 * @return the phone
	 */
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

	/**
	 * @return the positionCode
	 */
	public String getPositionCode() {
		return positionCode;
	}

	/**
	 * @param positionCode
	 *            the positionCode to set
	 */
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	/**
	 * @return the positionDescription
	 */
	public String getPositionDescription() {
		return positionDescription;
	}

	/**
	 * @param positionDescription
	 *            the positionDescription to set
	 */
	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}

	/**
	 * @return the organizationCode
	 */
	public String getOrganizationCode() {
		return organizationCode;
	}

	/**
	 * @param organizationCode
	 *            the organizationCode to set
	 */
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	/**
	 * @return the organizationDescription
	 */
	public String getOrganizationDescription() {
		return organizationDescription;
	}

	/**
	 * @param organizationDescription
	 *            the organizationDescription to set
	 */
	public void setOrganizationDescription(String organizationDescription) {
		this.organizationDescription = organizationDescription;
	}

	/**
	 * @return the departmentCode
	 */
	public String getDepartmentCode() {
		return departmentCode;
	}

	/**
	 * @param departmentCode
	 *            the departmentCode to set
	 */
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	/**
	 * @return the departmentDescription
	 */
	public String getDepartmentDescription() {
		return departmentDescription;
	}

	/**
	 * @param departmentDescription
	 *            the departmentDescription to set
	 */
	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

	/**
	 * @return the departmentOrganizationCode
	 */
	public String getDepartmentOrganizationCode() {
		return departmentOrganizationCode;
	}

	/**
	 * @param departmentOrganizationCode
	 *            the departmentOrganizationCode to set
	 */
	public void setDepartmentOrganizationCode(String departmentOrganizationCode) {
		this.departmentOrganizationCode = departmentOrganizationCode;
	}

	/**
	 * @return the positionLevelCode
	 */
	public String getPositionLevelCode() {
		return positionLevelCode;
	}

	/**
	 * @param positionLevelCode
	 *            the positionLevelCode to set
	 */
	public void setPositionLevelCode(String positionLevelCode) {
		this.positionLevelCode = positionLevelCode;
	}

	/**
	 * @return the positionLevelDescription
	 */
	public String getPositionLevelDescription() {
		return positionLevelDescription;
	}

	/**
	 * @param positionLevelDescription
	 *            the positionLevelDescription to set
	 */
	public void setPositionLevelDescription(String positionLevelDescription) {
		this.positionLevelDescription = positionLevelDescription;
	}

}
