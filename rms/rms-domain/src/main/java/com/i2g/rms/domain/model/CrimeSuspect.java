package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.domain.model.tablemaintenance.GenderType;

/**
 * Entity representation of Crime Suspect.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_CRME_SUSPT")
@JsonIgnoreProperties({ "crime" })
public class CrimeSuspect extends AbstractDataModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID */
	private long _id;
	private StatusFlag _statusFlag;
	private String _title;
	private String _firstName;
	private String _middleName;
	private String _lastName;
	private String _nameSuffix;
	private GenderType _genderType;	
	private LocalDate _dateOfBirth;
	private Integer _age;
	private String _phone;
	private String _fax;
	private String _alternatePhone;
	private String _email;
	private String _website;
	private Set<Address> _addresses = new HashSet<Address>(0);
	private Crime _crime;
	private String _otherComments;
	private Set<DistinguishingFeatureDetail> _distinguishingFeatureDetails = new HashSet<DistinguishingFeatureDetail>(0); 

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected CrimeSuspect() {
	}

	/**
	 * Creates a new immutable instance of {@link CrimeSuspect} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private CrimeSuspect(final Builder builder) {
		_crime = Objects.requireNonNull(builder._crime, "Crime object cannot be null for a crime suspect record.");
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");
	}

	/**
	 * Return the Crime primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_crme_suspt_id_seq")
	@SequenceGenerator(name = "rms_crme_suspt_id_seq", sequenceName = "RMS_CRME_SUSPT_ID_SEQ", allocationSize = 1)
	@Override
	public Long getId() {
		return _id;
	}

	/**
	 * Sets the primary surrogate key ID.
	 * 
	 * <p>
	 * <strong>Note:</strong> This method has protected access to prevent
	 * callers from manually setting the auto-generated primary key ID;
	 * Hibernate has access to invoke this method when populating an entity.
	 * </p>
	 * 
	 * @param id
	 */
	protected void setId(final long id) {
		_id = id;
	}

	/**
	 * @return the title
	 */
	@Column(name = "TITLE", length = 32)
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
	@Column(name = "FNAME", length = 64)
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
	@Column(name = "MNAME", length = 64)
	public String getMiddleName() {
		return _middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		_middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	@Column(name = "LNAME", length = 64)
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
	@Column(name = "NAM_SUFFIX", length = 32)
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GNDR_TYP_CDE")
	public GenderType getGenderType() {
		return _genderType;
	}

	/**
	 * @param genderType
	 *            the genderType to set
	 */
	public void setGenderType(final GenderType genderType) {
		_genderType = genderType;
	}

	/**
	 * @return the dateOfBirth
	 */
	@Column(name = "DOB")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateType")
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
	@Column(name = "AGE")
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
	 * @return the phone
	 */
	@Column(name = "PHN", length = 20)
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
	 * @return the alternatePhone
	 */
	@Column(name = "ALT_PHN", length = 20)
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
	@Column(name = "FAX", length = 20)
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
	 * @return the email
	 */
	@Column(name = "EML", length = 128)
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
	@Column(name = "WEB_STE", length = 128)
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
	 * @return the statusFlag
	 */
	@Column(name = "STS_FLG")
	@NotNull
	@Enumerated(EnumType.STRING)
	public StatusFlag getStatusFlag() {
		return _statusFlag;
	}

	/**
	 * @param statusFlag
	 *            the statusFlag to set
	 */
	public void setStatusFlag(final StatusFlag statusFlag) {
		_statusFlag = statusFlag;
	}

	/**
	 * @return the addresses
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "crimeSuspect")
	@Fetch(FetchMode.SUBSELECT)
	public Set<Address> getAddresses() {
		return _addresses;
	}

	/**
	 * @param addresses
	 *            the addresses to set
	 */
	public void setAddresses(final Set<Address> addresses) {
		_addresses = addresses;
	}

	/**
	 * @return the crime
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CRME_ID")
	public Crime getCrime() {
		return _crime;
	}

	/**
	 * @param crime
	 *            the crime to set
	 */
	public void setCrime(final Crime crime) {
		_crime = crime;
	}
	
	/**
	 * @return the otherComments
	 */
	@Column(name = "DIST_FEA_OTHR_CMNTS", length = 128)
	public String getOtherComments() {
		return _otherComments;
	}

	/**
	 * @param otherComments the otherComments to set
	 */
	public void setOtherComments(final String otherComments) {
		_otherComments = otherComments;
	}
	
	/**
	 * @return the distinguishingFeatureDetails
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "RMS_CRME_SUSPT_DIST_FEA_CHLD",
			joinColumns = @JoinColumn(name = "CRME_SUSPT_ID"),
			inverseJoinColumns = @JoinColumn(name = "DIST_FEA_CHLD_CDE")
	)
	public Set<DistinguishingFeatureDetail> getDistinguishingFeatureDetails() {
		return _distinguishingFeatureDetails;
	}

	/**
	 * @param distinguishingFeatureDetails the distinguishingFeatureDetails to set
	 */
	public void setDistinguishingFeatureDetails(final Set<DistinguishingFeatureDetail> distinguishingFeatureDetails) {
		_distinguishingFeatureDetails = distinguishingFeatureDetails;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link Crime}.
	 */
	public final static class Builder {

		private Crime _crime;
		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Crime.
		 * 
		 * @return new instance of Crime
		 */
		public CrimeSuspect build() {
			return new CrimeSuspect(this);
		}

		public Builder setCrime(final Crime crime) {
			_crime = crime;
			return this;
		}

		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}
}
