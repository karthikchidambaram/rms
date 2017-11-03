package com.i2g.rms.rest.model.lookup;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;

/**
 * Suspect table RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuspectTableRO extends AbstractEntityRO {

	private long suspectId;
	private long employeeId;
	private String employeeLoginId;
	private long incidentId;
	private String uniqueIncidentId;
	private String suspectCategory;
	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private String nameSuffix;
	private String fullName;
	private LocalDate dateOfBirth;
	private Integer age;
	private String phone;
	private String alternatePhone;
	private String email;
	private String statusFlag;
	private String genderTypeCode;
	private String genderTypeDescription;
	private String typeCode;
	private String typeDescription;
	private String typeOtherDescription;

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
	 * @return the statusFlag
	 */
	public String getStatusFlag() {
		return statusFlag;
	}

	/**
	 * @param statusFlag
	 *            the statusFlag to set
	 */
	public void setStatusFlag(final String statusFlag) {
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
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode
	 *            the typeCode to set
	 */
	public void setTypeCode(final String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * @return the typeDescription
	 */
	public String getTypeDescription() {
		return typeDescription;
	}

	/**
	 * @param typeDescription
	 *            the typeDescription to set
	 */
	public void setTypeDescription(final String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}

	public long getSuspectId() {
		return suspectId;
	}

	public void setSuspectId(final long suspectId) {
		this.suspectId = suspectId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(final long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeLoginId() {
		return employeeLoginId;
	}

	public void setEmployeeLoginId(final String employeeLoginId) {
		this.employeeLoginId = employeeLoginId;
	}

	public long getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(final long incidentId) {
		this.incidentId = incidentId;
	}

	public String getUniqueIncidentId() {
		return uniqueIncidentId;
	}

	public void setUniqueIncidentId(final String uniqueIncidentId) {
		this.uniqueIncidentId = uniqueIncidentId;
	}

	public String getSuspectCategory() {
		return suspectCategory;
	}

	public void setSuspectCategory(final String suspectCategory) {
		this.suspectCategory = suspectCategory;
	}

	public String getTypeOtherDescription() {
		return typeOtherDescription;
	}

	public void setTypeOtherDescription(final String typeOtherDescription) {
		this.typeOtherDescription = typeOtherDescription;
	}	
}
