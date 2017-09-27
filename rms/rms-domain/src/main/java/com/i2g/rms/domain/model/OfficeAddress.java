package com.i2g.rms.domain.model;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.tablemaintenance.Organization;

/**
 * Entity representation of Office Address.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_OFF_ADDR")
@JsonIgnoreProperties({ "incidents", "users" })
public class OfficeAddress extends AbstractDataModel<Long> implements Serializable {

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
	private Organization _organization;
	private Set<Incident> _incidents = new HashSet<Incident>(0);
	private String _doorNumber;
	private String _blockNumber;
	private String _apartmentNumber;
	private Set<User> _users = new HashSet<User>(0);

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected OfficeAddress() {
	}

	/**
	 * Creates a new immutable instance of {@link OfficeAddress} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private OfficeAddress(final Builder builder) {
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "Status flag cannot be null.");
	}

	/**
	 * Return the Accident primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_off_addr_id_seq")
	@SequenceGenerator(name = "rms_off_addr_id_seq", sequenceName = "RMS_OFF_ADDR_ID_SEQ", allocationSize = 1)
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
	 * @param organizationName
	 *            the organizationName to set
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
	 * @param buildingName
	 *            the buildingName to set
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
	 * @param streetName
	 *            the streetName to set
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
	 * @param localityName
	 *            the localityName to set
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
	 * @param postTown
	 *            the postTown to set
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
	 * @param county
	 *            the county to set
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
	 * @param postCode
	 *            the postCode to set
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
	 * @param country
	 *            the country to set
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
	 * @param statusFlag
	 *            the statusFlag to set
	 */
	public void setStatusFlag(final StatusFlag statusFlag) {
		_statusFlag = statusFlag;
	}

	@Column(name = "CITY", length = 64)
	public String getCity() {
		return _city;
	}

	public void setCity(final String city) {
		_city = city;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_CDE")
	public Organization getOrganization() {
		return _organization;
	}

	public void setOrganization(final Organization organization) {
		_organization = organization;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "officeAddress")
	@Fetch(FetchMode.SUBSELECT)
	public Set<Incident> getIncidents() {
		return _incidents;
	}

	public void setIncidents(final Set<Incident> incidents) {
		_incidents = incidents;
	}
	
	@Column(name = "DOOR_NO", length = 16)	
	public String getDoorNumber() {
		return _doorNumber;
	}

	public void setDoorNumber(final String doorNumber) {
		_doorNumber = doorNumber;
	}
	
	@Column(name = "BLCK_NO", length = 16)
	public String getBlockNumber() {
		return _blockNumber;
	}

	public void setBlockNumber(final String blockNumber) {
		_blockNumber = blockNumber;
	}
	
	@Column(name = "APTMT_NO", length = 16)
	public String getApartmentNumber() {
		return _apartmentNumber;
	}

	public void setApartmentNumber(final String apartmentNumber) {
		_apartmentNumber = apartmentNumber;
	}
	
	@OneToMany(mappedBy = "officeAddress")
	public Set<User> getUsers() {
		return _users;
	}

	public void setUsers(final Set<User> users) {
		_users = users;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link OfficeAddress}.
	 */
	public final static class Builder {

		private StatusFlag _statusFlag;

		/**
		 * Builds a new immutable instance of Office Address.
		 * 
		 * @return new instance of Office Address
		 */
		public OfficeAddress build() {
			return new OfficeAddress(this);
		}

		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
	}
}
