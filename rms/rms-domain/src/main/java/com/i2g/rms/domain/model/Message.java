package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity for representing (optionally) parameterized messages which can be
 * referenced when generating error messages, warnings, debug statements, etc.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Entity
@Table(name = "RMS_MSG_RPSTRY")
public class Message extends AbstractDataModel<Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Enum constant to represent the available categories a message can belong
	 * to, used to prefix generated message.
	 */
	public enum Category {
		/** Debug category for messages. */
		DEBUG,
		/** Info category for messages. */
		INFO,
		/** Warn category for messages. */
		WARN,
		/** Error category for messages. */
		ERROR;
	}

	/**
	 * Enum constant to represent the available messages types.
	 */
	public enum Type {
		/** Generic type of message. */
		GENERIC("Generic"),
		/** Admin action history related message. */
		ADMINHISTORY("Admin Action History"),
		/** REST endpoints related message. */
		REST("Rest End Point");

		/** Display label for message type. */
		private final String _label;

		/**
		 * Creates a new enum of {@code Type} with the specified {@code label}.
		 * 
		 * @param label
		 *            for type
		 */
		private Type(final String label) {
			_label = label;
		}

		@Override
		public String toString() {
			return _label;
		}
	}

	/** Primary key ID for message records. */
	private int _id;
	/** Message text which may contain parameter place holders. */
	private String _text;
	/** Category of message. */
	private Category _category;
	/** Type of message. */
	private Type _type;

	/**
	 * Default empty constructor required by Hibernate.
	 */
	public Message() {
	}

	/**
	 * Creates a new instance of {@code Message} with the specified values.
	 * 
	 * @param id
	 *            (non-null)
	 * @param text
	 *            (non-null)
	 * @param category
	 *            (non-null)
	 * @param type
	 *            (non-null)
	 */
	public Message(final Integer id, final String text, final Category category, final Type type) {
		_id = Objects.requireNonNull(id, "Message ID cannot be null");
		_text = Objects.requireNonNull(text, "Message text cannot be null");
		_category = Objects.requireNonNull(category, "Message category cannot be null");
		_type = Objects.requireNonNull(type, "Message type cannot be null");
	}

	@Id
	@Column(name = "MSG_ID")
	@Override
	public Integer getId() {
		return _id;
	}

	/**
	 * Sets the message ID.
	 * 
	 * <p>
	 * <strong>Note:</strong> This method has protected access to prevent
	 * callers from manually setting or updating the primary key ID; Hibernate
	 * has access to invoke this method when populating an entity.
	 * </p>
	 * 
	 * @param id
	 */
	protected void setId(final int id) {
		_id = id;
	}

	/**
	 * Returns the message text, which may contain replacement parameters.
	 * 
	 * @return message text
	 */
	@Column(name = "MSG_TXT", nullable = false)
	@NotNull(message = "Message text cannot be null")
	@Size(min = 1, max = 2000)
	public String getText() {
		return _text;
	}

	/**
	 * Sets the specified message {@code text}.
	 * 
	 * @param text
	 *            (non-null)
	 */
	public void setText(final String text) {
		_text = text;
	}

	/**
	 * Returns the message category.
	 * 
	 * @return category of message
	 */
	@Column(name = "EXCPTN_CTGRY_CDE", nullable = false)
	@NotNull(message = "Message category cannot be null")
	@Enumerated(EnumType.STRING)
	public Category getCategory() {
		return _category;
	}

	/**
	 * Sets the specified message {@code category}.
	 * 
	 * @param category
	 *            (non-null)
	 */
	public void setCategory(final Category category) {
		_category = category;
	}

	/**
	 * Returns the type of message.
	 * 
	 * @return type of message
	 */
	@Column(name = "MSG_TYP_CDE", nullable = false)
	@NotNull(message = "Message type cannot be null")
	@Enumerated(EnumType.STRING)
	public Type getType() {
		return _type;
	}

	/**
	 * Sets the specified message {@code type}.
	 * 
	 * @param type
	 *            (non-null)
	 */
	public void setType(final Type type) {
		_type = type;
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == this || (obj instanceof Message && ((Message) obj)._id == _id);
	}

	@Override
	public int hashCode() {
		return _id;
	}

	@Override
	public String toString() {
		return _category + " " + _id + ": " + _text;
	}

}
