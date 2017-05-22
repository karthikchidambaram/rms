package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

/**
 * Entity representation of User Details table.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Immutable
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "userCache")
@Table(name = "RMS_USR_DTLS")
public class UserDetails extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary surrogate key ID of User table. */
	private long _id;
	private String _phone;
	private String _email;
	private String _address;
	private String _pin;
	private String _state;
	private String _country;
	private User _user;

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected UserDetails() {
	}

	/**
	 * Creates a new immutable instance of {@link UserDetails} from the
	 * specified {@code builder}.
	 * 
	 * @param builder
	 */
	private UserDetails(final Builder builder) {
		_id = Objects.requireNonNull(builder._id, "ID cannot be null");
		_user = Objects.requireNonNull(builder._user, "User (Object) cannot be null. Foreign Key Constraint Violation.");
	}

	/**
	 * Return the UserDetails primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false)
	@GeneratedValue(generator = "sequence")
	@SequenceGenerator(name = "sequence", sequenceName = "RMS_USR_DTLS_ID_SEQ")
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

	@Column(name = "USR_PHN")
	@Size(min = 1, max = 20, message = "Phone must be between {min} and {max} characters")
	public String getPhone() {
		return _phone;
	}

	public void setPhone(String phone) {
		_phone = phone;
	}

	@Column(name = "USR_EML")
	@Size(min = 1, max = 128, message = "Email must be between {min} and {max} characters")
	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	@Column(name = "USR_ADDR")
	@Size(min = 1, max = 256, message = "Address must be between {min} and {max} characters")
	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	@Column(name = "USR_PIN")
	@Size(min = 1, max = 20, message = "Pin (code) must be between {min} and {max} characters")
	public String getPin() {
		return _pin;
	}

	public void setPin(String pin) {
		_pin = pin;
	}

	@Column(name = "USR_STE")
	@Size(min = 1, max = 32, message = "State name must be between {min} and {max} characters")
	public String getState() {
		return _state;
	}

	public void setState(String state) {
		_state = state;
	}

	@Column(name = "USR_CNTRY")
	@Size(min = 1, max = 32, message = "Country name must be between {min} and {max} characters")
	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
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
	 * {@link UserDetails}.
	 */
	public final static class Builder {

		private Long _id;
		private User _user;

		/**
		 * Builds a new immutable instance of user.
		 * 
		 * @return new instance of user
		 */
		public UserDetails build() {
			return new UserDetails(this);
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
		 * Sets the specified {@code user}.
		 * 
		 * @param user
		 * @return this builder
		 */
		public Builder setUser(final User user) {
			_user = user;
			return this;
		}
	}
}
