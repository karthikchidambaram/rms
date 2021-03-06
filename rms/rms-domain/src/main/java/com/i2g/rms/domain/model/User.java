package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.jdo.annotations.Unique;
import javax.persistence.Cacheable;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i2g.rms.domain.model.tablemaintenance.EmployeeType;
import com.i2g.rms.domain.model.tablemaintenance.GenderType;
import com.i2g.rms.domain.model.tablemaintenance.Position;

/**
 * Entity representation of User table.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_USR")
@DynamicUpdate
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "userCache")
@JsonIgnoreProperties({"subordinates"})
public class User extends AbstractDataModel<Long> implements Serializable, org.springframework.security.core.userdetails.UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID of User table. */
	private long _id;
	/** Unique user login id */
	private String _loginId;
	private String _password;
	private String _firstName;
	private String _lastName;
	private String _middleName;
	private String _title;
	private String _nameSuffix;
	private StatusFlag _statusFlag;
	private Set<PasswordHistory> _passwordHistory = new HashSet<PasswordHistory>(0);
	/** Set of Roles associated with the user. */
	private Set<Role> _roles = new HashSet<Role>(0);
	@Transient
	private long _expires;
	@Transient
	private String _username;	
	private GenderType _genderType;
	private LocalDate _dateOfBirth;
	private Integer _age;
	private LocalDate _dateOfJoining;
	private LocalDate _dateOfLeaving;
	private String _phone;
	private String _alternatePhone;
	private String _fax;
	private String _email;
	private String _employeeId;
	private Position _position;
	private EmployeeType _employeeType;
	private User _manager;
	private Set<User> _subordinates = new HashSet<User>(0);
	private Set<Address> _addresses = new HashSet<Address>(0);
	private String _genderTypeOther;
	private String _employeeTypeOther;
	private OfficeAddress _officeAddress;
	private Set<InvestigationTeam> _investigationTeams = new HashSet<InvestigationTeam>(0);
			
	@NotNull
	@Transient
	private boolean _accountExpired;

	@NotNull
	@Transient
	private boolean _accountLocked;

	@NotNull
	@Transient
	private boolean _credentialsExpired;

	@NotNull
	@Transient
	private boolean _accountEnabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	@Transient
	private Set<UserAuthority> _authorities;	
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected User() {
	}
	
	public User(final String loginId) {
		_username = loginId;
		_loginId = loginId;
	}

	public User(final String loginId, final Date expires) {
		_username = loginId;
		_loginId = loginId;
		_expires = expires.getTime();
	}

	/**
	 * Creates a new immutable instance of {@link User} from the specified
	 * {@code builder}.
	 * 
	 * @param builder
	 */
	private User(final Builder builder) {
		_loginId = Objects.requireNonNull(builder._loginId, "User login ID cannot be null.");
		_password = Objects.requireNonNull(builder._password, "Password cannot be null.");
		_firstName = Objects.requireNonNull(builder._firstName, "First name cannot be null.");
		_statusFlag = Objects.requireNonNull(builder._statusFlag, "User status flag code cannot be null.");
		_username = Objects.requireNonNull(builder._username, "Username cannot be null.");
	}

	/**
	 * Return the User primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_usr_id_seq")
	@SequenceGenerator(name = "rms_usr_id_seq", sequenceName = "RMS_USR_ID_SEQ", allocationSize = 1)
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

	@Column(name = "LGN_ID", nullable = false, updatable = false, length = 64)
	@NotNull
	@Unique
	public String getLoginId() {
		return _loginId;
	}

	public void setLoginId(final String loginId) {
		_loginId = loginId;
		_username = loginId;
	}

	@Column(name = "PWD", nullable = false, length = 128)
	@NotNull
	@JsonIgnore
	public String getPassword() {
		return _password;
	}
	
	@JsonProperty
	public void setPassword(final String password) {
		_password = password;
	}
	
	@Column(name = "FNAME", nullable = false, length = 64)
	@NotNull
	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(final String firstName) {
		_firstName = firstName;
	}

	@Column(name = "LNAME", length = 64)
	public String getLastName() {
		return _lastName;
	}

	public void setLastName(final String lastName) {
		_lastName = lastName;
	}

	@Column(name = "MNAME", length = 64)
	public String getMiddleName() {
		return _middleName;
	}

	public void setMiddleName(final String middleName) {
		_middleName = middleName;
	}

	@Column(name = "TITLE", length = 32)
	public String getTitle() {
		return _title;
	}

	public void setTitle(final String title) {
		_title = title;
	}

	@Column(name = "NAM_SUFFIX", length = 32)
	public String getNameSuffix() {
		return _nameSuffix;
	}

	public void setNameSuffix(final String nameSuffix) {
		_nameSuffix = nameSuffix;
	}

	@Column(name = "STS_FLG", nullable = false)
	@Enumerated(EnumType.STRING)
	public StatusFlag getStatusFlag() {
		return _statusFlag;
	}

	public void setStatusFlag(final StatusFlag statusFlag) {
		_statusFlag = statusFlag;
	}

	/**
	 * Returns the set of {@code Role}s which are associated with this user.
	 * 
	 * @return set of associated roles
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "RMS_USR_RLE",
			joinColumns = @JoinColumn(name = "USR_ID"),
			inverseJoinColumns = @JoinColumn(name = "RLE_ID")
	)
	@Fetch(FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region="userCache")
	public Set<Role> getRoles() {
		return _roles;
	}

	/**
	 * Sets the set of {@code roles} which are associated to this user.
	 * 
	 * <p>
	 * <strong>Note:</strong> This method has protected access to prevent
	 * callers from manually setting the roles as Roles should never be
	 * created/updated programmatically; Hibernate has access to invoke this
	 * method when populating an entity.
	 * </p>
	 * 
	 * @param roles
	 */
	protected void setRoles(final Set<Role> roles) {
		_roles = roles;		
	}	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	@Fetch(FetchMode.SUBSELECT)
	public Set<PasswordHistory> getPasswordHistory() {
		return _passwordHistory;		
	}

	public void setPasswordHistory(final Set<PasswordHistory> passwordHistory) {
		_passwordHistory = passwordHistory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _loginId, _statusFlag);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof User) {
			final User other = (User) obj;
			return Objects.equals(_id, other._id) && Objects.equals(_loginId, other._loginId)
					&& Objects.equals(_statusFlag, other._statusFlag);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Id: " + _id + ", User Id: " + _loginId + ", Status Flag: " + _statusFlag;
	}

	/**
	 * Builder pattern for constructing immutable instances of {@link User}.
	 */
	public final static class Builder {

		private String _loginId;
		private String _password;
		private String _firstName;
		private StatusFlag _statusFlag;
		private String _username;
		
		/**
		 * Builds a new immutable instance of user.
		 * 
		 * @return new instance of user
		 */
		public User build() {
			return new User(this);
		}

		/**
		 * Sets the specified {@code loginId}.
		 * 
		 * @param loginId
		 * @return this builder
		 */
		public Builder setLoginId(final String loginId) {
			_loginId = loginId;
			return this;
		}

		/**
		 * Sets the specified {@code password}.
		 * 
		 * @param password
		 * @return this builder
		 */
		public Builder setPassword(final String password) {
			_password = password;
			return this;
		}

		/**
		 * Sets the specified {@code firstName}.
		 * 
		 * @param firstName
		 * @return this builder
		 */
		public Builder setFirstName(final String firstName) {
			_firstName = firstName;
			return this;
		}

		/**
		 * Sets the specified {@code statusFlag}.
		 * 
		 * @param statusFlag
		 * @return this builder
		 */
		public Builder setStatusFlag(final StatusFlag statusFlag) {
			_statusFlag = statusFlag;
			return this;
		}
		
		/**
		 * Sets the specified {@code username}.
		 * 
		 * @param username
		 * @return this builder
		 */
		public Builder setUsername(final String username) {
			_username = username;
			return this;
		}
	}
	
	@Transient
	public boolean hasRole(String roleName) {
		return getRoles().contains(roleName);
	}
	
	@Transient
	public long getExpires() {
		return _expires;
	}

	@Transient
	public void setExpires(final long expires) {
		_expires = expires;		
	}

	@Transient
	public String getUsername() {
		return _username;
	}
	
	@Transient
	public void setUsername(final String username) {
		_username = username;
		_loginId = username;
	}

	@Override
	@JsonIgnore
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return buildUserAuthority(getRoles());
	}

	@Override
	@JsonIgnore
	@Transient
	public boolean isAccountNonExpired() {
		return !_accountExpired;
	}

	@Override
	@JsonIgnore
	@Transient
	public boolean isAccountNonLocked() {
		return !_accountLocked;
	}

	@Override
	@JsonIgnore
	@Transient
	public boolean isCredentialsNonExpired() {
		return !_credentialsExpired;
	}

	@Override
	@JsonIgnore
	@Transient
	public boolean isEnabled() {
		return !_accountEnabled;
	}
	
	private List<GrantedAuthority> buildUserAuthority(final Set<Role> roles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role role : roles) {
			setAuths.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		List<GrantedAuthority> results = new ArrayList<GrantedAuthority>(setAuths);
		return results;
	}

	/**
	 * @return the genderType
	 */
	@ManyToOne(fetch = FetchType.EAGER)
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
	 * @return the dateOfJoining
	 */
	@Column(name = "DOJ")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateType")
	public LocalDate getDateOfJoining() {
		return _dateOfJoining;
	}

	/**
	 * @param dateOfJoining the dateOfJoining to set
	 */
	public void setDateOfJoining(final LocalDate dateOfJoining) {
		_dateOfJoining = dateOfJoining;
	}

	/**
	 * @return the dateOfLeaving
	 */
	@Column(name = "DOL")
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateType")
	public LocalDate getDateOfLeaving() {
		return _dateOfLeaving;
	}

	/**
	 * @param dateOfLeaving the dateOfLeaving to set
	 */
	public void setDateOfLeaving(final LocalDate dateOfLeaving) {
		_dateOfLeaving = dateOfLeaving;
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
	 * @return the employeeId
	 */
	@Column(name = "EMP_ID", length = 20)
	public String getEmployeeId() {
		return _employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(final String employeeId) {
		_employeeId = employeeId;
	}

	/**
	 * @return the position
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POSTN_CDE")
	public Position getPosition() {
		return _position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(final Position position) {
		_position = position;
	}

	/**
	 * @return the employeeType
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMP_TYP_CDE")
	public EmployeeType getEmployeeType() {
		return _employeeType;
	}

	/**
	 * @param employeeType the employeeType to set
	 */
	public void setEmployeeType(final EmployeeType employeeType) {
		_employeeType = employeeType;
	}

	/**
	 * @return the manager
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "MGR_ID")
	public User getManager() {
		return _manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(final User manager) {
		_manager = manager;
	}
	
	/**
	 * @return the subordinates
	 */
	@OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
	public Set<User> getSubordinates() {
		return _subordinates;		
	}

	/**
	 * @param subordinates the subordinates to set
	 */
	public void setSubordinates(final Set<User> subordinates) {
		_subordinates = subordinates;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	@Fetch(FetchMode.SUBSELECT)
	public Set<Address> getAddresses() {
		return _addresses;		
	}

	public void setAddresses(final Set<Address> addresses) {
		_addresses = addresses;
	}

	/**
	 * @return the genderTypeOther
	 */
	@Column(name = "GNDR_TYP_OTHR", length = 32)
	public String getGenderTypeOther() {
		return _genderTypeOther;
	}

	/**
	 * @param genderTypeOther the genderTypeOther to set
	 */
	public void setGenderTypeOther(final String genderTypeOther) {
		_genderTypeOther = genderTypeOther;
	}

	/**
	 * @return the employeeTypeOther
	 */
	@Column(name = "EMP_TYP_OTHR", length = 32)
	public String getEmployeeTypeOther() {
		return _employeeTypeOther;
	}

	/**
	 * @param employeeTypeOther the employeeTypeOther to set
	 */
	public void setEmployeeTypeOther(final String employeeTypeOther) {
		_employeeTypeOther = employeeTypeOther;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OFF_ADDR_ID")
	public OfficeAddress getOfficeAddress() {
		return _officeAddress;
	}

	public void setOfficeAddress(final OfficeAddress officeAddress) {
		_officeAddress = officeAddress;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "RMS_INVST_TEAM_INVSTGATOR", 
			joinColumns = @JoinColumn(name = "USR_ID"), 
			inverseJoinColumns = @JoinColumn(name = "INVST_TEAM_ID")
	)
	public Set<InvestigationTeam> getInvestigationTeams() {
		return _investigationTeams;		
	}

	public void setInvestigationTeams(final Set<InvestigationTeam> investigationTeams) {
		_investigationTeams = investigationTeams;
	}
}
