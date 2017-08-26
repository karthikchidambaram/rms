package com.i2g.rms.rest.model.lookup;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.StatusFlagRO;

/**
 * User lookup RO Object
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
	private LocalDate dateOfBirth;
	private Integer age;
	private LocalDate dateOfJoining;
	private LocalDate dateOfLeaving;
	private StatusFlagRO statusFlag;
	private String genderTypeCode;
	private String genderTypeDescription;
	private String phone;
	private String alternatePhone;
	private String email;
	private String employeeId;
	private Long managerId;
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
	public void setId(final long id) {
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
	public void setUserLoginId(final String userLoginId) {
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
	public void setTitle(final String title) {
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
	public void setFirstName(final String firstName) {
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
	public void setMiddleName(final String middleName) {
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
	public void setLastName(final String lastName) {
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
	public void setNameSuffix(final String nameSuffix) {
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
	public void setStatusFlag(final StatusFlagRO statusFlag) {
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
	public void setGenderTypeCode(final String genderTypeCode) {
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
	public void setGenderTypeDescription(final String genderTypeDescription) {
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
	public void setPhone(final String phone) {
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
	public void setAlternatePhone(final String alternatePhone) {
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
	public void setEmail(final String email) {
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
	public void setEmployeeId(final String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the managerId
	 */
	public Long getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId
	 *            the managerId to set
	 */
	public void setManagerId(final Long managerId) {
		this.managerId = managerId;
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
	public void setEmployeeTypeCode(final String employeeTypeCode) {
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
	public void setEmployeeTypeDescription(final String employeeTypeDescription) {
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
	public void setPositionCode(final String positionCode) {
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
	public void setPositionDescription(final String positionDescription) {
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
	public void setOrganizationCode(final String organizationCode) {
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
	public void setOrganizationDescription(final String organizationDescription) {
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
	public void setDepartmentCode(final String departmentCode) {
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
	public void setDepartmentDescription(final String departmentDescription) {
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
	public void setDepartmentOrganizationCode(final String departmentOrganizationCode) {
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
	public void setPositionLevelCode(final String positionLevelCode) {
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
	public void setPositionLevelDescription(final String positionLevelDescription) {
		this.positionLevelDescription = positionLevelDescription;
	}

	/**
	 * @return the dateOfBirth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(final LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(final Integer age) {
		this.age = age;
	}

	/**
	 * @return the dateOfJoining
	 */
	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	/**
	 * @param dateOfJoining
	 *            the dateOfJoining to set
	 */
	public void setDateOfJoining(final LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	/**
	 * @return the dateOfLeaving
	 */
	public LocalDate getDateOfLeaving() {
		return dateOfLeaving;
	}

	/**
	 * @param dateOfLeaving
	 *            the dateOfLeaving to set
	 */
	public void setDateOfLeaving(final LocalDate dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}

}
