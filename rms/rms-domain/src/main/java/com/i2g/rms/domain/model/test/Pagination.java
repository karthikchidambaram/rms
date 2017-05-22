package com.i2g.rms.domain.model.test;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.i2g.rms.domain.model.AbstractDataModel;

/**
 * Entity representation of an Pagination.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_PGNTN_TST")
public class Pagination extends AbstractDataModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Primary surrogate key ID of pagination sample table. */
	private long _id;
	private String _type;
	private String _description;

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Pagination() {
	}

	/**
	 * Creates a new immutable instance of {@link Pagination} from the specified
	 * {@code builder}.
	 * 
	 * @param builder
	 */
	private Pagination(final Builder builder) {
		_id = Objects.requireNonNull(builder._id, "ID cannot be null");
		_type = Objects.requireNonNull(builder._type, "Type code cannot be null");
		_description = Objects.requireNonNull(builder._description, "Description text cannot be null");
	}

	/**
	 * Return the Pagination primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "ID", updatable = false)
	@GeneratedValue(generator = "sequence")
	@SequenceGenerator(name = "sequence", sequenceName = "RMS_PGNTN_TST_ID_SEQ")
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

	@Column(name = "TYPE")
	@Size(min = 1, max = 20, message = "Type code must be between {min} and {max} characters")
	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	@Column(name = "DESCR")
	@Size(min = 1, max = 32, message = "Description must be between {min} and {max} characters")
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _type, _description);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Pagination) {
			final Pagination other = (Pagination) obj;
			return Objects.equals(_id, other._id) && Objects.equals(_type, other._type)
					&& Objects.equals(_description, other._description);
		}
		return false;
	}

	@Override
	public String toString() {
		return " id: " + _id + " type: " + _type + " description: " + _description;
	}

	/**
	 * Builder pattern for constructing immutable instances of
	 * {@link Pagination}.
	 */
	public final static class Builder {

		private Long _id;
		private String _type;
		private String _description;

		/**
		 * Builds a new immutable instance of pagination.
		 * 
		 * @return new instance of pagination
		 */
		public Pagination build() {
			return new Pagination(this);
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
		 * Sets the specified {@code type}.
		 * 
		 * @param type
		 * @return this builder
		 */
		public Builder setType(final String type) {
			_type = type;
			return this;
		}

		/**
		 * Sets the specified item source {@code description}.
		 * 
		 * @param description
		 * @return this builder
		 */
		public Builder setDescription(final String description) {
			_description = description;
			return this;
		}
	}
}
