package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity representation of Password History table.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_PWD_HSTRY")
@Immutable
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "userCache")
@JsonIgnoreProperties({"user"})
public class PasswordHistory extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Primary surrogate key ID of User table. */
	private long _id;
	private String _password;
	private String _reasonDescription;
	private User _user;

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected PasswordHistory() {
	}

	/**
	 * Creates a new immutable instance of {@link PasswordHistory} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private PasswordHistory(final Builder builder) {
		_id = Objects.requireNonNull(builder._id, "ID cannot be null");
		_password = Objects.requireNonNull(builder._password, "Password cannot be null");
		_reasonDescription = Objects.requireNonNull(builder._reasonDescription, "Password change reason description cannot be null");
		_user = Objects.requireNonNull(builder._user, "User (Object) cannot be null. Foreign Key Constraint Violation.");
	}

	/**
	 * Return the PasswordHistory primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_pwd_hstry_id_seq")
	@SequenceGenerator(name = "rms_pwd_hstry_id_seq", sequenceName = "RMS_PWD_HSTRY_ID_SEQ", allocationSize = 1)
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

	@Column(name = "USR_PWD")
	@Size(min = 1, max = 20, message = "Password must be between {min} and {max} characters")
	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

	@Column(name = "PWD_CHG_RSN_DESC")
	@Size(min = 1, max = 64, message = "Password change reason description must be between {min} and {max} characters")
	public String getReasonDescription() {
		return _reasonDescription;
	}

	public void setReasonDescription(String reasonDescription) {
		_reasonDescription = reasonDescription;
	}

	@ManyToOne
	@JoinColumn(name = "USR_ID")
	public User getUser() {
		return _user;
	}

	public void setUser(User user) {
		_user = user;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link PasswordHistory}.
	 */
	public final static class Builder {

		private Long _id;
		private String _password;
		private String _reasonDescription;
		private User _user;

		/**
		 * Builds a new immutable instance of PasswordHistory.
		 * 
		 * @return new instance of PasswordHistory
		 */
		public PasswordHistory build() {
			return new PasswordHistory(this);
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

		public Builder setPassword(final String password) {
			_password = password;
			return this;
		}

		public Builder setReasonDescription(final String reasonDescription) {
			_reasonDescription = reasonDescription;
			return this;
		}

		public Builder setUser(final User user) {
			_user = user;
			return this;
		}
	}
}
