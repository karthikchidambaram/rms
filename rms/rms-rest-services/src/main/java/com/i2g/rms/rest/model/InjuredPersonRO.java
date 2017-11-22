package com.i2g.rms.rest.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.tablemaintenance.BodyPartRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.GenderTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuredPersonTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryCauseRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryTypeDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryTypeDetailSpecRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryTypeRO;

/**
 * REST Object for Injured Person.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InjuredPersonRO extends AbstractEntityRO {

	private long _id;
	private IncidentRO _incident;
	private StatusFlagRO _statusFlag;
	private InjuredPersonTypeRO _injuredPersonType;
	private String _title;
	private String _firstName;
	private String _middleName;
	private String _lastName;
	private String _nameSuffix;
	private GenderTypeRO _genderType;
	private LocalDate _dateOfBirth;
	private Integer _age;
	private String _phone;
	private String _fax;
	private String _alternatePhone;
	private String _email;
	private String _website;
	private YesNoTypeRO _firstAidGiven;
	private Set<AccidentRO> _accidents = new HashSet<AccidentRO>(0);
	private Set<BodyPartRO> _bodyParts = new HashSet<BodyPartRO>(0);
	private Set<AddressRO> _addresses = new HashSet<AddressRO>(0);
	private InjuryCauseRO _injuryCause;
	private InjuryTypeRO _injuryType;
	private InjuryTypeDetailRO _injuryTypeDetail;
	private InjuryTypeDetailSpecRO _injuryTypeDetailSpec;
	private Set<DistinguishingFeatureDetailRO> _distinguishingFeatureDetails = new HashSet<DistinguishingFeatureDetailRO>(0);
	private String _distinguishingFeatureOther;
	private String _injuredPersonTypeOther;
	private String _injuryTypeOther;
	private String _genderTypeOther;
	private String _injuryCauseOther;

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
	 * @return the incident
	 */
	public IncidentRO getIncident() {
		return _incident;
	}

	/**
	 * @param incident
	 *            the incident to set
	 */
	public void setIncident(final IncidentRO incident) {
		_incident = incident;
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
	 * @return the injuredPersonType
	 */
	public InjuredPersonTypeRO getInjuredPersonType() {
		return _injuredPersonType;
	}

	/**
	 * @param injuredPersonType
	 *            the injuredPersonType to set
	 */
	public void setInjuredPersonType(final InjuredPersonTypeRO injuredPersonType) {
		_injuredPersonType = injuredPersonType;
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
	 * @return the firstAidGiven
	 */
	public YesNoTypeRO getFirstAidGiven() {
		return _firstAidGiven;
	}

	/**
	 * @param firstAidGiven
	 *            the firstAidGiven to set
	 */
	public void setFirstAidGiven(final YesNoTypeRO firstAidGiven) {
		_firstAidGiven = firstAidGiven;
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
	 * @return the bodyParts
	 */
	public Set<BodyPartRO> getBodyParts() {
		return _bodyParts;
	}

	/**
	 * @param bodyParts
	 *            the bodyParts to set
	 */
	public void setBodyParts(final Set<BodyPartRO> bodyParts) {
		_bodyParts = bodyParts;
	}

	/**
	 * @return the addresses
	 */
	public Set<AddressRO> getAddresses() {
		return _addresses;
	}

	/**
	 * @param addresses
	 *            the addresses to set
	 */
	public void setAddresses(final Set<AddressRO> addresses) {
		_addresses = addresses;
	}

	/**
	 * @return the injuryCause
	 */
	public InjuryCauseRO getInjuryCause() {
		return _injuryCause;
	}

	/**
	 * @param injuryCause
	 *            the injuryCause to set
	 */
	public void setInjuryCause(final InjuryCauseRO injuryCause) {
		_injuryCause = injuryCause;
	}

	/**
	 * @return the injuryType
	 */
	public InjuryTypeRO getInjuryType() {
		return _injuryType;
	}

	/**
	 * @param injuryType
	 *            the injuryType to set
	 */
	public void setInjuryType(final InjuryTypeRO injuryType) {
		_injuryType = injuryType;
	}

	/**
	 * @return the injuryTypeDetail
	 */
	public InjuryTypeDetailRO getInjuryTypeDetail() {
		return _injuryTypeDetail;
	}

	/**
	 * @param injuryTypeDetail
	 *            the injuryTypeDetail to set
	 */
	public void setInjuryTypeDetail(final InjuryTypeDetailRO injuryTypeDetail) {
		_injuryTypeDetail = injuryTypeDetail;
	}

	/**
	 * @return the injuryTypeDetailSpec
	 */
	public InjuryTypeDetailSpecRO getInjuryTypeDetailSpec() {
		return _injuryTypeDetailSpec;
	}

	/**
	 * @param injuryTypeDetailSpec
	 *            the injuryTypeDetailSpec to set
	 */
	public void setInjuryTypeDetailSpec(final InjuryTypeDetailSpecRO injuryTypeDetailSpec) {
		_injuryTypeDetailSpec = injuryTypeDetailSpec;
	}

	/**
	 * @return the distinguishingFeatureOther
	 */
	public String getDistinguishingFeatureOther() {
		return _distinguishingFeatureOther;
	}

	/**
	 * @param distinguishingFeatureOther
	 *            the distinguishingFeatureOther to set
	 */
	public void setDistinguishingFeatureOther(final String distinguishingFeatureOther) {
		_distinguishingFeatureOther = distinguishingFeatureOther;
	}

	/**
	 * @return the distinguishingFeatureDetails
	 */
	public Set<DistinguishingFeatureDetailRO> getDistinguishingFeatureDetails() {
		return _distinguishingFeatureDetails;
	}

	/**
	 * @param distinguishingFeatureDetails
	 *            the distinguishingFeatureDetails to set
	 */
	public void setDistinguishingFeatureDetails(final Set<DistinguishingFeatureDetailRO> distinguishingFeatureDetails) {
		_distinguishingFeatureDetails = distinguishingFeatureDetails;
	}

	/**
	 * @return the injuredPersonTypeOther
	 */
	public String getInjuredPersonTypeOther() {
		return _injuredPersonTypeOther;
	}

	/**
	 * @param injuredPersonTypeOther
	 *            the injuredPersonTypeOther to set
	 */
	public void setInjuredPersonTypeOther(final String injuredPersonTypeOther) {
		_injuredPersonTypeOther = injuredPersonTypeOther;
	}

	/**
	 * @return the injuryTypeOther
	 */
	public String getInjuryTypeOther() {
		return _injuryTypeOther;
	}

	/**
	 * @param injuryTypeOther
	 *            the injuryTypeOther to set
	 */
	public void setInjuryTypeOther(final String injuryTypeOther) {
		_injuryTypeOther = injuryTypeOther;
	}

	/**
	 * @return the genderTypeOther
	 */
	public String getGenderTypeOther() {
		return _genderTypeOther;
	}

	/**
	 * @param genderTypeOther
	 *            the genderTypeOther to set
	 */
	public void setGenderTypeOther(final String genderTypeOther) {
		_genderTypeOther = genderTypeOther;
	}

	/**
	 * @return the injuryCauseOther
	 */
	public String getInjuryCauseOther() {
		return _injuryCauseOther;
	}

	/**
	 * @param injuryCauseOther
	 *            the injuryCauseOther to set
	 */
	public void setInjuryCauseOther(final String injuryCauseOther) {
		_injuryCauseOther = injuryCauseOther;
	}
}