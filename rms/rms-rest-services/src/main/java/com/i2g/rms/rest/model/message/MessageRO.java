package com.i2g.rms.rest.model.message;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.i2g.rms.domain.model.Message.Category;
import com.i2g.rms.domain.model.Message.Type;
import com.i2g.rms.rest.model.AbstractEntityRO;

/**
 * REST object for representing {@code Message} entities.
 *
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class MessageRO extends AbstractEntityRO {

	/**
	 * Primary key ID for message records.
	 */
	private Integer _id;
	/**
	 * Message text which may contain parameter placeholders.
	 */
	private String _text;
	/**
	 * Category of message.
	 */
	private Category _category;
	/**
	 * Type of message.
	 */
	private Type _type;

	/**
	 * Returns the primary key ID of the message.
	 *
	 * @return primary key ID
	 */
	public Integer getId() {
		return _id;
	}

	/**
	 * Sets the specified primary key {@code id}.
	 *
	 * @param id
	 */
	public void setId(final Integer id) {
		_id = id;
	}

	/**
	 * Returns the message text.
	 *
	 * @return message text
	 */
	@NotNull(message = "Message text cannot be null")
	@Size(min = 1, max = 256)
	public String getText() {
		return _text;
	}

	/**
	 * Sets the specified message {@code text}.
	 *
	 * @param text
	 */
	public void setText(final String text) {
		_text = text;
	}

	/**
	 * Returns the message category.
	 *
	 * @return category
	 */
	@NotNull(message = "Message category cannot be null")
	public Category getCategory() {
		return _category;
	}

	/**
	 * Sets the specified message {@code category}.
	 *
	 * @param category
	 */
	public void setCategory(final Category category) {
		_category = category;
	}

	/**
	 * Returns the message type.
	 *
	 * @return type
	 */
	@NotNull(message = "Message type cannot be null")
	public Type getType() {
		return _type;
	}

	/**
	 * Sets the specified message {@code type}.
	 *
	 * @param type
	 */
	public void setType(final Type type) {
		_type = type;
	}

}
