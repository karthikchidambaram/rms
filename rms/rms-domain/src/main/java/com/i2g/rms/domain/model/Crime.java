package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.domain.model.tablemaintenance.GenderType;

/**
 * Entity representation of Crime.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_CRME")
public class Crime extends AbstractDataModel<Long> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID */
	private long _id;
	private Incident _incident;
	private StatusFlag _statusFlag;
	private String _title;
	private String _firstName;
	private String _middleName;
	private String _lastName;
	private String _nameSuffix;
	private GenderType _genderType;
	private DistinguishingFeatureDetail _distinguishingFeaturesDetail;
	private LocalDate _dateOfBirth;
	private Integer _age;
	private String _phone;
	private String _fax;
	private String _alternatePhone;
	private String _email;
	private String _website;
	private YesNoType _anyWitness;
	private User _user;
	private LocalDate _crimeDate;
	private Long _crimeTime;
	private String _crimeDescription;	
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Crime() {
	}
	
	/**
	 * Creates a new immutable instance of {@link Crime} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private Crime(final Builder builder) {
		_id = Objects.requireNonNull(builder._id, "Crime Id cannot be null.");		
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");
	}
	
	/**
	 * Return the Crime primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false)
	@GeneratedValue(generator = "sequence")
	@SequenceGenerator(name = "sequence", sequenceName = "RMS_CRME_ID_SEQ")
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
	 * @return the incidentId
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INC_ID")
	public Incident getIncident() {
		return _incident;
	}

	/**
	 * @param incident the incident to set
	 */
	public void setIncident(final Incident incident) {
		_incident = incident;
	}
	
	/**
	 * @return the title
	 */
	@Column(name = "TITLE")
	@Size(min = 1, max = 32, message = "Title must be between {min} and {max} characters")
	public String getTitle() {
		return _title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(final String title) {
		_title = title;
	}

	/**
	 * @return the firstName
	 */
	@Column(name = "FNAME")
	@Size(min = 1, max = 64, message = "First name must be between {min} and {max} characters")
	public String getFirstName() {
		return _firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(final String firstName) {
		_firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	@Column(name = "MNAME")
	@Size(min = 1, max = 64, message = "Middle name must be between {min} and {max} characters")
	public String getMiddleName() {
		return _middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		_middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	@Column(name = "LNAME")
	@Size(min = 1, max = 64, message = "Last name must be between {min} and {max} characters")
	public String getLastName() {
		return _lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(final String lastName) {
		_lastName = lastName;
	}

	/**
	 * @return the nameSuffix
	 */
	@Column(name = "NAM_SUFFIX")
	@Size(min = 1, max = 32, message = "Last name must be between {min} and {max} characters")
	public String getNameSuffix() {
		return _nameSuffix;
	}

	/**
	 * @param nameSuffix the nameSuffix to set
	 */
	public void setNameSuffix(final String nameSuffix) {
		_nameSuffix = nameSuffix;
	}

	/**
	 * @return the genderType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GNDR_TYP_CDE")
	@Size(min = 1, max = 16, message = "Gender type code must be between {min} and {max} characters")
	public GenderType getGenderType() {
		return _genderType;
	}

	/**
	 * @param genderType the genderType to set
	 */
	public void setGenderType(final GenderType genderType) {
		_genderType = genderType;
	}

	/**
	 * @return the distinguishingFeaturesDetail
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DIST_FEA_CHLD_CDE")
	@Size(min = 1, max = 16, message = "Distinguishing features code must be between {min} and {max} characters")
	public DistinguishingFeatureDetail getDistinguishingFeaturesDetail() {
		return _distinguishingFeaturesDetail;
	}

	/**
	 * @param distinguishingFeaturesDetail the distinguishingFeaturesDetail to set
	 */
	public void setDistinguishingFeaturesDetail(final DistinguishingFeatureDetail distinguishingFeaturesDetail) {
		_distinguishingFeaturesDetail = distinguishingFeaturesDetail;
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
	 * @param dateOfBirth the dateOfBirth to set
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
	 * @param age the age to set
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
	@Column(name = "PHN")
	@Size(min = 1, max = 20, message = "Phone number must be between {min} and {max} characters")
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
	@Column(name = "ALT_PHN")
	@Size(min = 1, max = 20, message = "Alternate phone number must be between {min} and {max} characters")
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
	@Column(name = "FAX")
	@Size(min = 1, max = 20, message = "Fax number must be between {min} and {max} characters")
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
	@Column(name = "EML")
	@Size(min = 1, max = 128, message = "Email must be between {min} and {max} characters")
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
	 * @return the website
	 */
	@Column(name = "WEB_STE")
	@Size(min = 1, max = 128, message = "Website address must be between {min} and {max} characters")
	public String getWebsite() {
		return _website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(final String website) {
		_website = website;
	}
	
	/**
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USR_ID")
	public User getUser() {
		return _user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(final User user) {
		_user = user;
	}
	
	/**
	 * @return the statusFlag
	 */
	@Column(name = "STS_FLG", nullable = false)
	@Size(min = 1, max = 16, message = "Status flag code must be between {min} and {max} characters")
	@NotNull
	@Enumerated(EnumType.STRING)
	public StatusFlag getStatusFlag() {
		return _statusFlag;
	}

	/**
	 * @param statusFlag the statusFlag to set
	 */
	public void setStatusFlag(final StatusFlag statusFlag) {
		_statusFlag = statusFlag;
	}
	
	/**
	 * @return the anyWitness
	 */
	@Column(name = "ANY_WITNS")
	@Size(max = 1, message = "Any witness is 'Yes' or 'No' data type. The max length for the corresponding code is 1.")
	@Enumerated(EnumType.STRING)
	public YesNoType getAnyWitness() {
		return _anyWitness;
	}

	/**
	 * @param anyWitness the anyWitness to set
	 */
	public void setAnyWitness(final YesNoType anyWitness) {
		_anyWitness = anyWitness;
	}
	
	/**
	 * @return the crimeDate
	 */
	@Column(name = "CRME_DT")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateType")
	public LocalDate getCrimeDate() {
		return _crimeDate;
	}

	/**
	 * @param crimeDate the crimeDate to set
	 */
	public void setCrimeDate(final LocalDate crimeDate) {
		_crimeDate = crimeDate;
	}

	/**
	 * @return the crimeTime
	 */
	@Column(name = "CRME_TIME")
	public Long getCrimeTime() {
		return _crimeTime;
	}

	/**
	 * @param crimeTime the crimeTime to set
	 */
	public void setCrimeTime(final Long crimeTime) {
		if (crimeTime != null) {
			_crimeTime = crimeTime;
		} else {
			_crimeTime = 0l;
		}
	}

	/**
	 * @return the crimeDescription
	 */
	@Column(name = "CRME_DESC")
	@Size(min = 1, max = 256, message = "Crime description must be between {min} and {max} characters")
	public String getCrimeDescription() {
		return _crimeDescription;
	}

	/**
	 * @param crimeDescription the crimeDescription to set
	 */
	public void setCrimeDescription(final String crimeDescription) {
		_crimeDescription = crimeDescription;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _statusFlag);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Crime) {
			final Crime other = (Crime) obj;
			return Objects.equals(_id, other._id) 
					&& Objects.equals(_statusFlag, other._statusFlag);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Id: " + _id + ", " 
		+ "Status Flag: " + _statusFlag;
	}
	
	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link Crime}.
	 */
	public final static class Builder {

		private Long _id;
		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Crime.
		 * 
		 * @return new instance of Crime
		 */
		public Crime build() {
			return new Crime(this);
		}

		/**
		 * Sets the specified {@code id}.
		 * 
		 * @param id
		 * @return this builder
		 */
		public Builder setId(final Long id) {
			_id = id;
			return this;
		}

		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}
}
