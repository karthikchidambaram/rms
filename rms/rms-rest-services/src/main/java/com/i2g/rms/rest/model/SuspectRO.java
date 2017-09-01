package com.i2g.rms.rest.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureRO;
import com.i2g.rms.rest.model.tablemaintenance.GenderTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.SuspectTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.WeaponTypeRO;

/**
 * REST Object for Suspect.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuspectRO extends AbstractEntityRO {

	private long _id;
	private StatusFlagRO _statusFlag;
	private String _title;
	private String _firstName;
	private String _middleName;
	private String _lastName;
	private String _nameSuffix;
	private GenderTypeRO _genderType;
	private DistinguishingFeatureRO _distinguishingFeature; 
	private DistinguishingFeatureDetailRO _distinguishingFeatureDetail;
	private LocalDate _dateOfBirth;
	private Integer _age;
	private String _phone;
	private String _fax;
	private String _alternatePhone;
	private String _email;
	private String _website;
	private YesNoTypeRO _weaponInvolved;
	private WeaponTypeRO _weaponType;
	private SuspectTypeRO _suspectType;
	private Set<AddressRO> _address = new HashSet<AddressRO>(0);
	private Set<IncidentRO> _incidents = new HashSet<IncidentRO>(0);

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
	
	public DistinguishingFeatureRO getDistinguishingFeature() {
		return _distinguishingFeature;
	}

	public void setDistinguishingFeature(final DistinguishingFeatureRO distinguishingFeature) {
		_distinguishingFeature = distinguishingFeature;
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
		if (age != null) {
			_age = age;
		} else {
			_age = 0;
		}
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
	 * @return the weaponInvolved
	 */
	public YesNoTypeRO getWeaponInvolved() {
		return _weaponInvolved;
	}

	/**
	 * @param weaponInvolved
	 *            the weaponInvolved to set
	 */
	public void setWeaponInvolved(final YesNoTypeRO weaponInvolved) {
		_weaponInvolved = weaponInvolved;
	}

	/**
	 * @return the weaponType
	 */
	public WeaponTypeRO getWeaponType() {
		return _weaponType;
	}

	/**
	 * @param weaponType
	 *            the weaponType to set
	 */
	public void setWeaponType(final WeaponTypeRO weaponType) {
		_weaponType = weaponType;
	}

	/**
	 * @return the suspectType
	 */
	public SuspectTypeRO getSuspectType() {
		return _suspectType;
	}

	/**
	 * @param suspectType
	 *            the suspectType to set
	 */
	public void setSuspectType(final SuspectTypeRO suspectType) {
		_suspectType = suspectType;
	}

	public Set<AddressRO> getAddress() {
		return _address;
	}

	public void setAddress(final Set<AddressRO> address) {
		_address = address;
	}

	public Set<IncidentRO> getIncidents() {
		return _incidents;
	}

	public void setIncidents(final Set<IncidentRO> incidents) {
		_incidents = incidents;
	}	
}
