package com.i2g.rms.rest.model.lookup;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.model.AddressRO;
import com.i2g.rms.rest.model.StatusFlagRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.GenderTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.WitnessTypeRO;

/**
 * Suspect table RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WitnessTableRO extends AbstractEntityRO {

	private long witnessId;
	private long employeeId;
	private String employeeLoginId;
	private long crimeId;
	private long accidentId;
	private long incidentId;
	private String uniqueIncidentId;
	private String witnessCategory;
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
	private String genderTypeCode;
	private String genderTypeDescription;
	private String typeCode;
	private String typeDescription;
	private String typeOtherDescription;
	//additional fields added to make it consistent with WitnessRO
	private long id;
	private StatusFlagRO statusFlag;
	private GenderTypeRO genderType;
	private String website;
	private Set<AddressRO> addresses = new HashSet<AddressRO>(0);
	private WitnessTypeRO witnessType;
	private String distinguishingFeatureOther;
	private Set<DistinguishingFeatureDetailRO> distinguishingFeatureDetails = new HashSet<DistinguishingFeatureDetailRO>(0);
	private String witnessTypeOther;
	private String genderTypeOther;

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

	public long getWitnessId() {
		return witnessId;
	}

	public void setWitnessId(final long witnessId) {
		this.witnessId = witnessId;
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

	public String getWitnessCategory() {
		return witnessCategory;
	}

	public void setWitnessCategory(final String witnessCategory) {
		this.witnessCategory = witnessCategory;
	}

	public long getCrimeId() {
		return crimeId;
	}

	public void setCrimeId(final long crimeId) {
		this.crimeId = crimeId;
	}

	public long getAccidentId() {
		return accidentId;
	}

	public void setAccidentId(final long accidentId) {
		this.accidentId = accidentId;
	}

	public String getTypeOtherDescription() {
		return typeOtherDescription;
	}

	public void setTypeOtherDescription(final String typeOtherDescription) {
		this.typeOtherDescription = typeOtherDescription;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public StatusFlagRO getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(final StatusFlagRO statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(final String website) {
		this.website = website;
	}

	public Set<AddressRO> getAddresses() {
		return addresses;
	}

	public void setAddresses(final Set<AddressRO> addresses) {
		this.addresses = addresses;
	}

	public WitnessTypeRO getWitnessType() {
		return witnessType;
	}

	public void setWitnessType(final WitnessTypeRO witnessType) {
		this.witnessType = witnessType;
	}

	public String getDistinguishingFeatureOther() {
		return distinguishingFeatureOther;
	}

	public void setDistinguishingFeatureOther(final String distinguishingFeatureOther) {
		this.distinguishingFeatureOther = distinguishingFeatureOther;
	}

	public Set<DistinguishingFeatureDetailRO> getDistinguishingFeatureDetails() {
		return distinguishingFeatureDetails;
	}

	public void setDistinguishingFeatureDetails(final Set<DistinguishingFeatureDetailRO> distinguishingFeatureDetails) {
		this.distinguishingFeatureDetails = distinguishingFeatureDetails;
	}

	public String getWitnessTypeOther() {
		return witnessTypeOther;
	}

	public void setWitnessTypeOther(final String witnessTypeOther) {
		this.witnessTypeOther = witnessTypeOther;
	}

	public String getGenderTypeOther() {
		return genderTypeOther;
	}

	public void setGenderTypeOther(final String genderTypeOther) {
		this.genderTypeOther = genderTypeOther;
	}

	public GenderTypeRO getGenderType() {
		return genderType;
	}

	public void setGenderType(final GenderTypeRO genderType) {
		this.genderType = genderType;
	}	
}
