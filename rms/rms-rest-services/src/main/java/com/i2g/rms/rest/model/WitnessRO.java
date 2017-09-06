package com.i2g.rms.rest.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeature;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.GenderTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.WitnessTypeRO;

/**
 * REST Object for Witness.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WitnessRO extends AbstractEntityRO {

	private long _id;
	private StatusFlagRO _statusFlag;
	private String _title;
	private String _firstName;
	private String _middleName;
	private String _lastName;
	private String _nameSuffix;
	private GenderTypeRO _genderType;
	private DistinguishingFeature _distinguishingFeature;
	private DistinguishingFeatureDetailRO _distinguishingFeatureDetail;
	private LocalDate _dateOfBirth;
	private Integer _age;
	private String _phone;
	private String _fax;
	private String _alternatePhone;
	private String _email;
	private String _website;
	private Set<AccidentRO> _accidents = new HashSet<AccidentRO>(0);
	private Set<AddressRO> _addresses = new HashSet<AddressRO>(0);
	private WitnessTypeRO _witnessType;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return _id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final long id) {
		_id = id;
	}

	/**
	 * @return the statusFlag
	 */
	public StatusFlagRO getStatusFlag() {
		return _statusFlag;
	}

	/**
	 * @param statusFlag
	 *            the statusFlag to set
	 */
	public void setStatusFlag(final StatusFlagRO statusFlag) {
		_statusFlag = statusFlag;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return _title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(final String title) {
		_title = title;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return _firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(final String firstName) {
		_firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return _middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(final String middleName) {
		_middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return _lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(final String lastName) {
		_lastName = lastName;
	}

	/**
	 * @return the nameSuffix
	 */
	public String getNameSuffix() {
		return _nameSuffix;
	}

	/**
	 * @param nameSuffix
	 *            the nameSuffix to set
	 */
	public void setNameSuffix(final String nameSuffix) {
		_nameSuffix = nameSuffix;
	}

	/**
	 * @return the genderType
	 */
	public GenderTypeRO getGenderType() {
		return _genderType;
	}

	/**
	 * @param genderType
	 *            the genderType to set
	 */
	public void setGenderType(final GenderTypeRO genderType) {
		_genderType = genderType;
	}

	/**
	 * @return the distinguishingFeatureDetail
	 */
	public DistinguishingFeatureDetailRO getDistinguishingFeatureDetail() {
		return _distinguishingFeatureDetail;
	}

	/**
	 * @param distinguishingFeatureDetail
	 *            the distinguishingFeatureDetail to set
	 */
	public void setDistinguishingFeatureDetail(final DistinguishingFeatureDetailRO distinguishingFeatureDetail) {
		_distinguishingFeatureDetail = distinguishingFeatureDetail;
	}

	/**
	 * @return the dateOfBirth
	 */
	public LocalDate getDateOfBirth() {
		return _dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
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
	 * @param age
	 *            the age to set
	 */
	public void setAge(final Integer age) {
		_age = age;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return _phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(final String phone) {
		_phone = phone;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return _fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(final String fax) {
		_fax = fax;
	}

	/**
	 * @return the alternatePhone
	 */
	public String getAlternatePhone() {
		return _alternatePhone;
	}

	/**
	 * @param alternatePhone
	 *            the alternatePhone to set
	 */
	public void setAlternatePhone(final String alternatePhone) {
		_alternatePhone = alternatePhone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return _email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(final String email) {
		_email = email;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return _website;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(final String website) {
		_website = website;
	}

	/**
	 * @return the accidents
	 */
	public Set<AccidentRO> getAccidents() {
		return _accidents;
	}

	/**
	 * @param accidents
	 *            the accidents to set
	 */
	public void setAccidents(final Set<AccidentRO> accidents) {
		_accidents = accidents;
	}
	
	/**
	 * @return the addresses
	 */
	public Set<AddressRO> getAddresses() {
		return _addresses;
	}

	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(final Set<AddressRO> addresses) {
		_addresses = addresses;
	}

	/**
	 * @return the witnessType
	 */
	public WitnessTypeRO getWitnessType() {
		return _witnessType;
	}

	/**
	 * @param witnessType the witnessType to set
	 */
	public void setWitnessType(final WitnessTypeRO witnessType) {
		_witnessType = witnessType;
	}

	/**
	 * @return the distinguishingFeature
	 */
	public DistinguishingFeature getDistinguishingFeature() {
		return _distinguishingFeature;
	}

	/**
	 * @param distinguishingFeature the distinguishingFeature to set
	 */
	public void setDistinguishingFeature(final DistinguishingFeature distinguishingFeature) {
		_distinguishingFeature = distinguishingFeature;
	}	
}
