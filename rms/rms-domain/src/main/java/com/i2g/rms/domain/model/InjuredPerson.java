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
import com.i2g.rms.domain.model.tablemaintenance.BodyPart;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeature;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.domain.model.tablemaintenance.GenderType;
import com.i2g.rms.domain.model.tablemaintenance.InjuredPersonType;
import com.i2g.rms.domain.model.tablemaintenance.InjuryCause;
import com.i2g.rms.domain.model.tablemaintenance.InjuryType;
import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetail;
import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetailSpec;

/**
 * Entity representation of Injured Person.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_INJRD_PRSN")
@JsonIgnoreProperties({"accidents"})
public class InjuredPerson extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary surrogate key ID of Injured Person table. */
	private long _id;
	private StatusFlag _statusFlag;
	private InjuredPersonType _injuredPersonType;
	private String _title;
	private String _firstName;
	private String _middleName;
	private String _lastName;
	private String _nameSuffix;
	private GenderType _genderType;
	private DistinguishingFeature _distinguishingFeature;
	private DistinguishingFeatureDetail _distinguishingFeatureDetail;
	private LocalDate _dateOfBirth;
	private Integer _age;
	private String _phone;
	private String _fax;
	private String _alternatePhone;
	private String _email;
	private String _website;
	private YesNoType _firstAidGiven;
	private YesNoType _anyWitness;
	private Set<Accident> _accidents = new HashSet<Accident>(0);
	private Set<BodyPart> _bodyParts = new HashSet<BodyPart>(0);
	private Set<Address> _addresses = new HashSet<Address>(0);
	private InjuryCause _injuryCause;
	private InjuryType _injuryType;
	private InjuryTypeDetail _injuryTypeDetail;
	private InjuryTypeDetailSpec _injuryTypeDetailSpec; 

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected InjuredPerson() {
	}

	/**
	 * Creates a new immutable instance of {@link InjuredPerson} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private InjuredPerson(final Builder builder) {
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Injured person status flag cannot be null.");
	}

	/**
	 * Return the Investigation Team primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_injrd_prsn_id_seq")
	@SequenceGenerator(name = "rms_injrd_prsn_id_seq", sequenceName = "RMS_INJRD_PRSN_ID_SEQ", allocationSize = 1)
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
	@Column(name = "STS_FLG", nullable = false, length = 16)
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
	 * @return the injuredPersonType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INJRD_PRSN_TYP_CDE")
	public InjuredPersonType getInjuredPersonType() {
		return _injuredPersonType;
	}

	/**
	 * @param injuredPersonType
	 *            the injuredPersonType to set
	 */
	public void setInjuredPersonType(final InjuredPersonType injuredPersonType) {
		_injuredPersonType = injuredPersonType;
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
	 * @return the distinguishingFeatureDetail
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DIST_FEA_CHLD_CDE")
	public DistinguishingFeatureDetail getDistinguishingFeatureDetail() {
		return _distinguishingFeatureDetail;
	}

	/**
	 * @param distinguishingFeatureDetail
	 *            the distinguishingFeatureDetail to set
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
	 * @return the firstAidGiven
	 */
	@Column(name = "FST_AID_GVN")
	@Enumerated(EnumType.STRING)
	public YesNoType getFirstAidGiven() {
		return _firstAidGiven;
	}

	/**
	 * @param firstAidGiven
	 *            the firstAidGiven to set
	 */
	public void setFirstAidGiven(final YesNoType firstAidGiven) {
		_firstAidGiven = firstAidGiven;
	}

	/**
	 * @return the anyWitness
	 */
	@Column(name = "ANY_WITNS")
	@Enumerated(EnumType.STRING)
	public YesNoType getAnyWitness() {
		return _anyWitness;
	}

	/**
	 * @param anyWitness
	 *            the anyWitness to set
	 */
	public void setAnyWitness(final YesNoType anyWitness) {
		_anyWitness = anyWitness;
	}

	/**
	 * @return the accidents
	 */
	@ManyToMany(mappedBy = "injuredPersons")
	public Set<Accident> getAccidents() {
		return _accidents;
	}

	/**
	 * @param accidents
	 *            the accidents to set
	 */
	public void setAccidents(final Set<Accident> accidents) {
		_accidents = accidents;
	}

	/**
	 * @return the bodyParts
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "RMS_INJRD_PRSN_BDY_PRTS", joinColumns = @JoinColumn(name = "INJRD_PRSN_ID"), inverseJoinColumns = @JoinColumn(name = "BDY_PRTS_CDE"))
	public Set<BodyPart> getBodyParts() {
		return _bodyParts;
	}

	/**
	 * @param bodyParts
	 *            the bodyParts to set
	 */
	public void setBodyParts(Set<BodyPart> bodyParts) {
		_bodyParts = bodyParts;
	}
	
	/**
	 * @return the addresses
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "injuredPerson")
	@Fetch(FetchMode.SUBSELECT)
	public Set<Address> getAddresses() {
		return _addresses;
	}

	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(final Set<Address> addresses) {
		_addresses = addresses;
	}
	
	/**
	 * @return the distinguishingFeature
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DIST_FEA_CDE")
	public DistinguishingFeature getDistinguishingFeature() {
		return _distinguishingFeature;
	}

	/**
	 * @param distinguishingFeature the distinguishingFeature to set
	 */
	public void setDistinguishingFeature(DistinguishingFeature distinguishingFeature) {
		_distinguishingFeature = distinguishingFeature;
	}

	/**
	 * @return the injuryCause
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INJRY_CAU_CDE")
	public InjuryCause getInjuryCause() {
		return _injuryCause;
	}

	/**
	 * @param injuryCause the injuryCause to set
	 */
	public void setInjuryCause(final InjuryCause injuryCause) {
		_injuryCause = injuryCause;
	}

	/**
	 * @return the injuryType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INJRY_TYP_CDE")
	public InjuryType getInjuryType() {
		return _injuryType;
	}

	/**
	 * @param injuryType the injuryType to set
	 */
	public void setInjuryType(final InjuryType injuryType) {
		_injuryType = injuryType;
	}

	/**
	 * @return the injuryTypeDetail
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INJRY_TYP_CHLD_CDE")
	public InjuryTypeDetail getInjuryTypeDetail() {
		return _injuryTypeDetail;
	}

	/**
	 * @param injuryTypeDetail the injuryTypeDetail to set
	 */
	public void setInjuryTypeDetail(final InjuryTypeDetail injuryTypeDetail) {
		_injuryTypeDetail = injuryTypeDetail;
	}

	/**
	 * @return the injuryTypeDetailSpec 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INJRY_TYP_CHLD_SUB_CDE")
	public InjuryTypeDetailSpec getInjuryTypeDetailSpec() {
		return _injuryTypeDetailSpec;
	}

	/**
	 * @param injuryTypeDetailSpec the injuryTypeDetailSpec to set
	 */
	public void setInjuryTypeDetailSpec(final InjuryTypeDetailSpec injuryTypeDetailSpec) {
		_injuryTypeDetailSpec = injuryTypeDetailSpec;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _statusFlag);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof InjuredPerson) {
			final InjuredPerson other = (InjuredPerson) obj;
			return Objects.equals(_id, other._id) && Objects.equals(_statusFlag, other._statusFlag);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Injured Person Id: " + _id + ", Name: " + _firstName + " " + _lastName + ", Status Flag: "
				+ _statusFlag;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link InjuredPerson}.
	 */
	public final static class Builder {

		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of InjuredPerson.
		 * 
		 * @return new instance of InjuredPerson
		 */
		public InjuredPerson build() {
			return new InjuredPerson(this);
		}

		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}
}
