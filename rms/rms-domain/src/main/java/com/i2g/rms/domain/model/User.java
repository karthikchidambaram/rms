package com.i2g.rms.domain.model;

import java.io.Serializable;
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity representation of User table.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_USR")
@Immutable
@DynamicUpdate
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "userCache")
public class User extends AbstractDataModel<Long> implements Serializable, org.springframework.security.core.userdetails.UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Possible status code for user data */
	public enum UserStatus {
		PENDING, ACTIVE, INACTIVE, DELETED, LOCKED;
	}

	/** Primary surrogate key ID of User table. */
	private long _id;
	/** Unique user login id */
	private String _loginId;
	private String _password;
	private String _firstName;
	private String _lastName;
	private String _middleName;
	private String _title;
	private String _suffix;
	private UserStatus _status;
	private Set<PasswordHistory> _passwordHistory = new HashSet<PasswordHistory>(0);
	/** Set of Roles associated with the user. */
	private Set<Role> _roles = new HashSet<Role>(0);
	@Transient
	private long _expires;
	@Transient
	private String _username;	
	
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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
	@Transient
	private Set<UserAuthority> _authorities;
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected User() {
	}
	
	public User(String loginId) {
		_username = loginId;
		_loginId = loginId;
	}

	public User(String loginId, Date expires) {
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
		_id = Objects.requireNonNull(builder._id, "ID cannot be null");
		_loginId = Objects.requireNonNull(builder._loginId, "User login ID cannot be null");
		_password = Objects.requireNonNull(builder._password, "Password cannot be null");
		_firstName = Objects.requireNonNull(builder._firstName, "First name cannot be null");
		_status = Objects.requireNonNull(builder._status, "Status code cannot be null");
		_username = Objects.requireNonNull(builder._username, "Username cannot be null");
	}

	/**
	 * Return the User primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false)
	@GeneratedValue(generator = "sequence")
	@SequenceGenerator(name = "sequence", sequenceName = "RMS_USR_ID_SEQ")
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

	@Column(name = "LGN_ID", nullable = false, updatable = false)
	@NotNull
	@Unique
	@Size(min = 1, max = 40, message = "User login ID must be between {min} and {max} characters")
	public String getLoginId() {
		return _loginId;
	}

	public void setLoginId(String loginId) {
		_loginId = loginId;
		_username = loginId;
	}

	@Column(name = "PWD", nullable = false)
	@NotNull
	@Size(min = 1, max = 128, message = "User password must be between {min} and {max} characters")
	@JsonIgnore
	public String getPassword() {
		return _password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		_password = password;
	}
	
	@Column(name = "FNAME", nullable = false)
	@NotNull
	@Size(min = 1, max = 50, message = "User first name must be between {min} and {max} characters")
	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	@Column(name = "LNAME")
	@Size(min = 1, max = 50, message = "User last name must be between {min} and {max} characters")
	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	@Column(name = "MNAME")
	@Size(min = 1, max = 50, message = "User middle name must be between {min} and {max} characters")
	public String getMiddleName() {
		return _middleName;
	}

	public void setMiddleName(String middleName) {
		_middleName = middleName;
	}

	@Column(name = "TITLE")
	@Size(min = 1, max = 32, message = "User title must be between {min} and {max} characters")
	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Column(name = "NAM_SUFFIX")
	@Size(min = 1, max = 32, message = "User name suffix must be between {min} and {max} characters")
	public String getSuffix() {
		return _suffix;
	}

	public void setSuffix(String suffix) {
		_suffix = suffix;
	}

	@Column(name = "STS_FLG", nullable = false)
	@Size(min = 1, max = 10, message = "User status code must be between {min} and {max} characters")
	@Enumerated(EnumType.STRING)
	public UserStatus getStatus() {
		return _status;
	}

	public void setStatus(UserStatus status) {
		_status = status;
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
	@Immutable
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "user")
	@Fetch(FetchMode.SUBSELECT)
	@JsonIgnoreProperties("user")
	public Set<PasswordHistory> getPasswordHistory() {
		return _passwordHistory;
	}

	public void setPasswordHistory(Set<PasswordHistory> passwordHistory) {
		_passwordHistory = passwordHistory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _loginId, _status);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof User) {
			final User other = (User) obj;
			return Objects.equals(_id, other._id) && Objects.equals(_loginId, other._loginId)
					&& Objects.equals(_status, other._status);
		}
		return false;
	}

	@Override
	public String toString() {
		return " id: " + _id + " loginId: " + _loginId + " status: " + _status;
	}

	/**
	 * Builder pattern for constructing immutable instances of {@link User}.
	 */
	public final static class Builder {

		private Long _id;
		private String _loginId;
		private String _password;
		private String _firstName;
		private UserStatus _status;
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
		 * Sets the specified {@code id}.
		 * 
		 * @param id
		 * @return this builder
		 */
		public Builder setId(final Long id) {
			_id = id;
			return this;
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
		public Builder setPassword(String password) {
			_password = password;
			return this;
		}

		/**
		 * Sets the specified {@code firstName}.
		 * 
		 * @param firstName
		 * @return this builder
		 */
		public Builder setFirstName(String firstName) {
			_firstName = firstName;
			return this;
		}

		/**
		 * Sets the specified {@code status}.
		 * 
		 * @param status
		 * @return this builder
		 */
		public Builder setStatus(UserStatus status) {
			_status = status;
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
	public void setExpires(long expires) {
		_expires = expires;
	}

	@Transient
	public String getUsername() {
		return _username;
	}
	
	@Transient
	public void setUsername(String username) {
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
	
	private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role role : roles) {
			setAuths.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		List<GrantedAuthority> results = new ArrayList<GrantedAuthority>(setAuths);
		return results;
	}
}
