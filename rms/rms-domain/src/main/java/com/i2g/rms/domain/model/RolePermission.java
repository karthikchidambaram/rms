package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Cacheable;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

/**
 * Relational entity for representing the relationship between a {@link Role}
 * and a {@link Permission} in a Role-Based Access Control security design.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_RLE_PRMSN")
@Immutable
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region="userCache")
public class RolePermission extends AbstractDataModel<RolePermission.PrimaryKey> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary composite key for relationship. */
	private PrimaryKey _id;

	public RolePermission() {
	}

	public RolePermission(final Role role, final Permission permission) {
		_id = new PrimaryKey(role, permission);
	}

	@EmbeddedId
	@Override
	public PrimaryKey getId() {
		return _id;
	}

	public void setId(final PrimaryKey id) {
		_id = id;
	}

	/**
	 * Returns the {@link Role} the role is associated to.
	 * 
	 * @return role
	 */
	@Transient
	public Role getRole() {
		return _id.getRole();
	}

	/**
	 * Returns the {@link Permission} associated to the role.
	 * 
	 * @return permission
	 */
	@Transient
	public Permission getPermission() {
		return _id.getPermission();
	}

	@Override
	public boolean equals(final Object obj) {
		return this == obj || (obj instanceof RolePermission && getId().equals(((RolePermission) obj).getId()));
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public String toString() {
		return "Role-Permission: " + _id.getRole().getRoleName() + " -> " + _id.getPermission().getPermissionName();
	}

	/**
	 * Primary key ID for the role/role relationship.
	 */
	@Embeddable
	public static class PrimaryKey implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/** Role of the relationship. */
		private Role _role;
		/** Role of the relationship. */
		private Permission _permission;

		/**
		 * Creates a new instance of {@code PrimaryKey}. Empty constructor
		 * required for Hibernate.
		 */
		public PrimaryKey() {
		}

		/**
		 * Creates a new instance of {@code PrimaryKey} for the specified
		 * {@code role} and {@code permission}.
		 * 
		 * @param role
		 * @param permission
		 */
		PrimaryKey(final Role role, final Permission permission) {
			_role = role;
			_permission = permission;
		}

		@ManyToOne
		@JoinColumn(name = "RLE_ID")
		public Role getRole() {
			return _role;
		}

		public void setRole(final Role role) {
			_role = role;
		}

		@ManyToOne
		@JoinColumn(name = "PRMSN_ID")
		public Permission getPermission() {
			return _permission;
		}

		public void setPermission(final Permission permission) {
			_permission = permission;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			} else if (obj instanceof PrimaryKey) {
				final PrimaryKey key = (PrimaryKey) obj;
				return Objects.equals(_role, key.getRole()) && Objects.equals(_permission, key.getPermission());
			}
			return false;
		}

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 79 * hash + Objects.hashCode(_role);
			hash = 79 * hash + Objects.hashCode(_permission);
			return hash;
		}

	}
}
