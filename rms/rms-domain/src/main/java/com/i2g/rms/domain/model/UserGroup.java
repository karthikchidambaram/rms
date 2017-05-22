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
 * Relational entity for representing the relationship between a {@link User}
 * and a {@link Group} in a Role-Based Access Control security design.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_USR_GRP")
@Immutable
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "userCache")
public class UserGroup extends AbstractDataModel<UserGroup.PrimaryKey> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary composite key for relationship. */
	private PrimaryKey _id;

	public UserGroup() {
	}

	public UserGroup(final User user, final Group group) {
		_id = new PrimaryKey(user, group);
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
	 * Returns the {@link Group} associated to the user.
	 * 
	 * @return group
	 */
	@Transient
	public Group getGroup() {
		return _id.getGroup();
	}

	@Override
	public boolean equals(final Object obj) {
		return this == obj || (obj instanceof UserGroup && getId().equals(((UserGroup) obj).getId()));
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public String toString() {
		return "User-Group: " + _id.getUser().getLoginId() + " -> " + _id.getGroup().getGroupName();
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
		private Group _group;

		/**
		 * Creates a new instance of {@code PrimaryKey}. Empty constructor
		 * required for Hibernate.
		 */
		public PrimaryKey() {
		}

		/**
		 * Creates a new instance of {@code PrimaryKey} for the specified
		 * {@code user} and {@code group}.
		 * 
		 * @param user
		 * @param group
		 */
		PrimaryKey(final User user, final Group group) {
			_user = user;
			_group = group;
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
		@JoinColumn(name = "GRP_ID")
		public Group getGroup() {
			return _group;
		}

		public void setGroup(final Group group) {
			_group = group;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			} else if (obj instanceof PrimaryKey) {
				final PrimaryKey key = (PrimaryKey) obj;
				return Objects.equals(_user, key.getUser()) && Objects.equals(_group, key.getGroup());
			}
			return false;
		}

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 79 * hash + Objects.hashCode(_user);
			hash = 79 * hash + Objects.hashCode(_group);
			return hash;
		}

	}
}
