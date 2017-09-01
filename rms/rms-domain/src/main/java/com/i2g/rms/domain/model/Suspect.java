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
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.domain.model.tablemaintenance.GenderType;
import com.i2g.rms.domain.model.tablemaintenance.SuspectType;
import com.i2g.rms.domain.model.tablemaintenance.WeaponType;

/**
 * Entity representation of Suspect.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_SUSPT")
@JsonIgnoreProperties({"incidents"})
public class Suspect extends AbstractDataModel<Long> implements Serializable {
	
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
	private DistinguishingFeatureDetail _distinguishingFeatureDetail;
	private LocalDate _dateOfBirth;
	private Integer _age;
	private String _phone;
	private String _fax;
	private String _alternatePhone;
	private String _email;
	private String _website;
	private YesNoType _weaponInvolved;
	private WeaponType _weaponType;
	private SuspectType _suspectType;
	private Set<Address> _address = new HashSet<Address>(0);
	private Set<Incident> _incidents = new HashSet<Incident>(0);
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Suspect() {
	}
	
	/**
	 * Creates a new immutable instance of {@link Suspect} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private Suspect(final Builder builder) {
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Suspect status flag cannot be null.");
	}
	
	/**
	 * Return the Suspect primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_suspt_id_seq")
	@SequenceGenerator(name = "rms_suspt_id_seq", sequenceName = "RMS_SUSPT_ID_SEQ", allocationSize = 1)
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
	 * @return the statusFlag
	 */
	@Column(name = "STS_FLG", nullable = false)
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
	 * @return the title
	 */
	@Column(name = "TITLE", length = 32)
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
	@Column(name = "FNAME", length = 64)
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
	@Column(name = "MNAME", length = 64)
	public String getMiddleName() {
		return _middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(final String middleName) {
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
	 * @param lastName the lastName to set
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
	 * @return the distinguishingFeatureDetail
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DIST_FEA_CHLD_CDE")
	public DistinguishingFeatureDetail getDistinguishingFeatureDetail() {
		return _distinguishingFeatureDetail;
	}

	/**
	 * @param distinguishingFeatureDetail the distinguishingFeatureDetail to set
	 */
	public void setDistinguishingFeatureDetail(final DistinguishingFeatureDetail distinguishingFeatureDetail) {
		_distinguishingFeatureDetail = distinguishingFeatureDetail;
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
	@Column(name = "PHN", length = 20)
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
	@Column(name = "ALT_PHN", length = 20)
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
	@Column(name = "FAX", length = 20)
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
	@Column(name = "EML", length = 128)
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
	@Column(name = "WEB_STE", length = 128)
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
	 * @return the weaponInvolved
	 */
	@Column(name = "WPN_INVLD")
	@Enumerated(EnumType.STRING)
	public YesNoType getWeaponInvolved() {
		return _weaponInvolved;
	}

	/**
	 * @param weaponInvolved the weaponInvolved to set
	 */
	public void setWeaponInvolved(final YesNoType weaponInvolved) {
		_weaponInvolved = weaponInvolved;
	}

	/**
	 * @return the weaponType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WPN_TYP_CDE")
	public WeaponType getWeaponType() {
		return _weaponType;
	}

	/**
	 * @param weaponType the weaponType to set
	 */
	public void setWeaponType(final WeaponType weaponType) {
		_weaponType = weaponType;
	}

	/**
	 * @return the suspectType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUSPT_TYP_CDE")
	public SuspectType getSuspectType() {
		return _suspectType;
	}

	/**
	 * @param suspectType the suspectType to set
	 */
	public void setSuspectType(final SuspectType suspectType) {
		_suspectType = suspectType;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "suspect")
	@Fetch(FetchMode.SUBSELECT)
	public Set<Address> getAddress() {
		return _address;
	}

	public void setAddress(final Set<Address> address) {
		_address = address;
	}
	
	@ManyToMany(mappedBy = "suspects")
	public Set<Incident> getIncidents() {
		return _incidents;
	}

	public void setIncidents(final Set<Incident> incidents) {
		_incidents = incidents;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _statusFlag);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Suspect) {
			final Suspect other = (Suspect) obj;
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
	 * {@link Suspect}.
	 */
	public final static class Builder {

		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Address.
		 * 
		 * @return new instance of Address
		 */
		public Suspect build() {
			return new Suspect(this);
		}

		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}
}
