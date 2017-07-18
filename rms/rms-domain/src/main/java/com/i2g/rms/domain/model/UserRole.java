package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Relational entity for representing the relationship between a {@link User}
 * and a {@link Role} in a Role-Based Access Control security design.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_USR_RLE")
public class UserRole extends AbstractDataModel<UserRole.PrimaryKey> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary composite key for relationship. */
	private PrimaryKey _id;

	public UserRole() {
	}

	public UserRole(final User user, final Role role) {
		_id = new PrimaryKey(user, role);
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
	 * Returns the {@link User} the role is associated to.
	 * 
	 * @return user
	 */
	@Transient
	public User getUser() {
		return _id.getUser();
	}

	/**
	 * Returns the {@link Role} associated to the user.
	 * 
	 * @return role
	 */
	@Transient
	public Role getRole() {
		return _id.getRole();
	}

	@Override
	public boolean equals(final Object obj) {
		return this == obj || (obj instanceof UserRole && getId().equals(((UserRole) obj).getId()));
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public String toString() {
		return "User -> Role: " + _id.getUser().getLoginId() + " -> " + _id.getRole().getRoleName();
	}

	/**
	 * Primary key ID for the user/role relationship.
	 */
	@Embeddable
	public static class PrimaryKey implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/** User of the relationship. */
		private User _user;
		/** Role of the relationship. */
		private Role _role;

		/**
		 * Creates a new instance of {@code PrimaryKey}. Empty constructor
		 * required for Hibernate.
		 */
		public PrimaryKey() {
		}

		/**
		 * Creates a new instance of {@code PrimaryKey} for the specified
		 * {@code user} and {@code role}.
		 * 
		 * @param user
		 * @param role
		 */
		PrimaryKey(final User user, final Role role) {
			_user = user;
			_role = role;
		}

		@ManyToOne
		@JoinColumn(name = "USR_ID")
		public User getUser() {
			return _user;
		}

		public void setUser(final User user) {
			_user = user;
		}

		@ManyToOne
		@JoinColumn(name = "RLE_ID")
		public Role getRole() {
			return _role;
		}

		public void setRole(final Role role) {
			_role = role;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			} else if (obj instanceof PrimaryKey) {
				final PrimaryKey key = (PrimaryKey) obj;
				return Objects.equals(_user, key.getUser()) && Objects.equals(_role, key.getRole());
			}
			return false;
		}

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 79 * hash + Objects.hashCode(_user);
			hash = 79 * hash + Objects.hashCode(_role);
			return hash;
		}

	}
}

