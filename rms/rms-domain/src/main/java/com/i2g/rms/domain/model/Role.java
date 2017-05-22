package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;

/**
 * Entity representation of Role table.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_RLE")
@Immutable
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region="userCache")
public class Role extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID of User table. */
	private long _id;
	private String _roleName;
	private String _roleDescription;
	/** Set of permissions associated with the role. */
	private Set<Permission> _permissions = new HashSet<Permission>(0);

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Role() {
	}

	/**
	 * Creates a new immutable instance of {@link Role} from the specified
	 * {@code builder}.
	 * 
	 * @param builder
	 */
	private Role(final Builder builder) {
		_id = Objects.requireNonNull(builder._id, "ID cannot be null");
		_roleName = Objects.requireNonNull(builder._roleName, "Role name cannot be null");
		_roleDescription = Objects.requireNonNull(builder._roleDescription, "Role Description cannot be null");
	}

	/**
	 * Return the Role primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false)
	@GeneratedValue(generator = "sequence")
	@SequenceGenerator(name = "sequence", sequenceName = "RMS_RLE_ID_SEQ")
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
	
	@Column(name = "RLE_NAM")
	@Size(min = 1, max = 20, message = "Role name must be between {min} and {max} characters")
	public String getRoleName() {
		return _roleName;
	}

	public void setRoleName(String roleName) {
		_roleName = roleName;
	}
	
	@Column(name = "RLE_DESC")
	@Size(min = 1, max = 64, message = "Role description must be between {min} and {max} characters")
	public String getRoleDescription() {
		return _roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		_roleDescription = roleDescription;
	}
	
	/**
	 * Returns the set of {@code Permission}s which are associated with this
	 * role.  Any associated permissions are eagerly fetched by Hibernate.
	 * 
	 * @return set of associated permissions
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "RMS_RLE_PRMSN",
			joinColumns = @JoinColumn(name = "RLE_ID"),
			inverseJoinColumns = @JoinColumn(name = "PRMSN_ID")
	)
	@Fetch(FetchMode.SUBSELECT)
	@Immutable
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region="userCache")
	public Set<Permission> getPermissions() {
		return _permissions;
	}
	
	/**
	 * Sets the set of {@code permissions} which are associated to this role.
	 * 
	 * <p><strong>Note:</strong> This method has protected access to prevent
	 * callers from manually setting the permissions as Roles should never  
	 * be created/updated programmatically; Hibernate has access to invoke this 
	 * method when populating an entity.</p>
	 * 
	 * @param permissions 
	 */
	protected void setPermissions(final Set<Permission> permissions) {
		_permissions = permissions;
	}

	/**
	 * Builder pattern for constructing immutable instances of {@link Role}.
	 */
	public final static class Builder {

		private Long _id;
		private String _roleName;
		private String _roleDescription;

		/**
		 * Builds a new immutable instance of Role.
		 * 
		 * @return new instance of Role
		 */
		public Role build() {
			return new Role(this);
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

		public Builder setRoleName(final String roleName) {
			_roleName = roleName;
			return this;
		}

		public Builder setRoleDescription(final String roleDescription) {
			_roleDescription = roleDescription;
			return this;
		}
	}
}
