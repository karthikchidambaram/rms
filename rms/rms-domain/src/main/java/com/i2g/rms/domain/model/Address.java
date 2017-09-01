package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity representation of Address.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_ADDR")
@JsonIgnoreProperties({"user", "building", "asset", "witness", "injuredPerson", "suspect", "crime", "accident"})
public class Address extends AbstractDataModel<Long> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Primary surrogate key ID */
	private long _id;
	private String _organizationName;
	private String _buildingName;
	private String _streetName;
	private String _localityName;
	private String _postTown;
	private String _county;
	private String _city;
	private String _postcode;
	private String _country;
	private StatusFlag _statusFlag;
	private User _user;
	private Building _building;
	private Asset _asset;
	private Witness _witness;
	private InjuredPerson _injuredPerson;
	private Suspect _suspect;
	private Crime _crime;
	private Accident _accident;	
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Address() {
	}
	
	/**
	 * Creates a new immutable instance of {@link Address} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private Address(final Builder builder) {
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");
	}
	
	/**
	 * Return the Accident primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_addr_id_seq")
	@SequenceGenerator(name = "rms_addr_id_seq", sequenceName = "RMS_ADDR_ID_SEQ", allocationSize = 1)
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
	 * @return the organizationName
	 */
	@Column(name = "ORG_NAM", length = 64)
	public String getOrganizationName() {
		return _organizationName;
	}

	/**
	 * @param organizationName the organizationName to set
	 */
	public void setOrganizationName(final String organizationName) {
		_organizationName = organizationName;
	}

	/**
	 * @return the buildingName
	 */
	@Column(name = "BLDNG_NAM", length = 64)
	public String getBuildingName() {
		return _buildingName;
	}

	/**
	 * @param buildingName the buildingName to set
	 */
	public void setBuildingName(final String buildingName) {
		_buildingName = buildingName;
	}

	/**
	 * @return the streetName
	 */
	@Column(name = "ST_NAM", length = 64)
	public String getStreetName() {
		return _streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(final String streetName) {
		_streetName = streetName;
	}

	/**
	 * @return the localityName
	 */
	@Column(name = "LCLTY_NAM", length = 64)
	public String getLocalityName() {
		return _localityName;
	}

	/**
	 * @param localityName the localityName to set
	 */
	public void setLocalityName(final String localityName) {
		_localityName = localityName;
	}

	/**
	 * @return the postTown
	 */
	@Column(name = "PST_TWN", length = 64)
	public String getPostTown() {
		return _postTown;
	}

	/**
	 * @param postTown the postTown to set
	 */
	public void setPostTown(final String postTown) {
		_postTown = postTown;
	}

	/**
	 * @return the county
	 */
	@Column(name = "CNTY", length = 64)
	public String getCounty() {
		return _county;
	}

	/**
	 * @param county the county to set
	 */
	public void setCounty(final String county) {
		_county = county;
	}

	/**
	 * @return the postCode
	 */
	@Column(name = "PST_CDE", length = 64)
	public String getPostcode() {
		return _postcode;
	}

	/**
	 * @param postCode the postCode to set
	 */
	public void setPostcode(final String postcode) {
		_postcode = postcode;
	}

	/**
	 * @return the country
	 */
	@Column(name = "CNTRY", length = 64)
	public String getCountry() {
		return _country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(final String country) {
		_country = country;
	}

	/**
	 * @return the statusFlag
	 */
	@Column(name = "STS_FLG", nullable = false)
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
	 * @return the building
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BLDNG_ID")
	public Building getBuilding() {
		return _building;
	}

	/**
	 * @param building the building to set
	 */
	public void setBuilding(final Building building) {
		_building = building;
	}

	/**
	 * @return the asset
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASST_ID")
	public Asset getAsset() {
		return _asset;
	}

	/**
	 * @param asset the asset to set
	 */
	public void setAsset(final Asset asset) {
		_asset = asset;
	}

	/**
	 * @return the witness
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WITNS_ID")
	public Witness getWitness() {
		return _witness;
	}

	/**
	 * @param witness the witness to set
	 */
	public void setWitness(final Witness witness) {
		_witness = witness;
	}

	/**
	 * @return the injuredPerson
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INJRD_PRSN_ID")
	public InjuredPerson getInjuredPerson() {
		return _injuredPerson;
	}

	/**
	 * @param injuredPerson the injuredPerson to set
	 */
	public void setInjuredPerson(final InjuredPerson injuredPerson) {
		_injuredPerson = injuredPerson;
	}

	/**
	 * @return the suspect
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUSPT_ID")
	public Suspect getSuspect() {
		return _suspect;
	}

	/**
	 * @param suspect the suspect to set
	 */
	public void setSuspect(final Suspect suspect) {
		_suspect = suspect;
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
	 * @param crime the crime to set
	 */
	public void setCrime(final Crime crime) {
		_crime = crime;
	}

	/**
	 * @return the accident
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_ID")
	public Accident getAccident() {
		return _accident;
	}

	/**
	 * @param accident the accident to set
	 */
	public void setAccident(final Accident accident) {
		_accident = accident;
	}
	
	@Column(name = "CITY", length = 64)
	public String getCity() {
		return _city;
	}

	public void setCity(final String city) {
		_city = city;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(_id, _statusFlag);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Address) {
			final Address other = (Address) obj;
			return Objects.equals(_id, other._id) 
					&& Objects.equals(_statusFlag, other._statusFlag);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Id: " + _id + ", " 
		+ "Organization Name: " + _organizationName + ", "
		+ "Building Name: " + _buildingName + ", "
		+ "Street Name: " + _streetName + ", "
		+ "Locality Name: " + _localityName + ", "
		+ "Post Town: " + _postTown + ", "
		+ "County: " + _county + ", "
		+ "City: " + _city + ", "
		+ "Postcode: " + _postcode + ", "
		+ "Country: " + _country + ", "
		+ "Status Flag: " + _statusFlag;
	}
	
	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link Address}.
	 */
	public final static class Builder {

		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Address.
		 * 
		 * @return new instance of Address
		 */
		public Address build() {
			return new Address(this);
		}

		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}	
}
