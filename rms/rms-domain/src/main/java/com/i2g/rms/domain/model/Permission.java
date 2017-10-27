package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity representation of Permission table.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_PRMSN")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "userCache")
@JsonIgnoreProperties({"roles"})
public class Permission extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID of User table. */
	private long _id;
	private String _permissionName;
	private String _permissionDescription;
	private Set<Role> _roles = new HashSet<Role>(0);

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Permission() {
	}

	/**
	 * Creates a new immutable instance of {@link Permission} from the specified
	 * {@code builder}.
	 * 
	 * @param builder
	 */
	private Permission(final Builder builder) {
		_permissionName = Objects.requireNonNull(builder._permissionName, "Permission name cannot be null");
		_permissionDescription = Objects.requireNonNull(builder._permissionDescription,
				"Permission Description cannot be null");
	}

	/**
	 * Return the Permission primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_prmsn_id_seq")
	@SequenceGenerator(name = "rms_prmsn_id_seq", sequenceName = "RMS_PRMSN_ID_SEQ", allocationSize = 1)
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

	@Column(name = "PRMSN_NAM")
	@Size(min = 1, max = 20, message = "Permission name must be between {min} and {max} characters")
	public String getPermissionName() {
		return _permissionName;
	}

	public void setPermissionName(String permissionName) {
		_permissionName = permissionName;
	}

	@Column(name = "PRMSN_DESC")
	@Size(min = 1, max = 64, message = "Permission description must be between {min} and {max} characters")
	public String getPermissionDescription() {
		return _permissionDescription;
	}

	public void setPermissionDescription(String permissionDescription) {
		_permissionDescription = permissionDescription;
	}
	
	/**
	 * @return the roles
	 */
	@ManyToMany(mappedBy = "permissions")
	public Set<Role> getRoles() {
		return _roles;		
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(final Set<Role> roles) {
		_roles = roles;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link Permission}.
	 */
	public final static class Builder {

		private String _permissionName;
		private String _permissionDescription;

		/**
		 * Builds a new immutable instance of Permission.
		 * 
		 * @return new instance of Permission
		 */
		public Permission build() {
			return new Permission(this);
		}

		public Builder setPermissionName(final String permissionName) {
			_permissionName = permissionName;
			return this;
		}

		public Builder setPermissionDescription(final String permissionDescription) {
			_permissionDescription = permissionDescription;
			return this;
		}
	}
}
