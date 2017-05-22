package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

/**
 * Entity representation of Group table.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_GRP")
@Immutable
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "userCache")
public class Group extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID of User table. */
	private long _id;
	private String _groupName;
	private String _groupDescription;

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Group() {
	}

	/**
	 * Creates a new immutable instance of {@link Group} from the specified
	 * {@code builder}.
	 * 
	 * @param builder
	 */
	private Group(final Builder builder) {
		_id = Objects.requireNonNull(builder._id, "ID cannot be null");
		_groupName = Objects.requireNonNull(builder._groupName, "Group name cannot be null");
		_groupDescription = Objects.requireNonNull(builder._groupDescription, "Group Description cannot be null");
	}

	/**
	 * Return the Group primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false)
	@GeneratedValue(generator = "sequence")
	@SequenceGenerator(name = "sequence", sequenceName = "RMS_GRP_ID_SEQ")
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

	@Column(name = "GRP_NAM")
	@Size(min = 1, max = 20, message = "Group name must be between {min} and {max} characters")
	public String getGroupName() {
		return _groupName;
	}

	public void setGroupName(String groupName) {
		_groupName = groupName;
	}

	@Column(name = "GRP_DESC")
	@Size(min = 1, max = 64, message = "Group description must be between {min} and {max} characters")
	public String getGroupDescription() {
		return _groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		_groupDescription = groupDescription;
	}

	/**
	 * Builder pattern for constructing immutable instances of {@link Group}.
	 */
	public final static class Builder {

		private Long _id;
		private String _groupName;
		private String _groupDescription;

		/**
		 * Builds a new immutable instance of Group.
		 * 
		 * @return new instance of Group
		 */
		public Group build() {
			return new Group(this);
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

		public Builder setGroupName(final String groupName) {
			_groupName = groupName;
			return this;
		}

		public Builder setGroupDescription(final String groupDescription) {
			_groupDescription = groupDescription;
			return this;
		}
	}
}
