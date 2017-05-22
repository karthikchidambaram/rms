package com.i2g.rms.service.message;

import java.util.Objects;

import com.i2g.rms.domain.model.Message;

/**
 * Interface for defining how messages are built from the underlying
 * {@link Message} entity.
 * 
 * <p>
 * <strong>Note:</strong> Please note producers must be thread-safe and
 * immutable.
 * </p>
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@FunctionalInterface
public interface MessageProducer {

	/**
	 * Constant standard {@code MessageBuilder} for constructing cacheable
	 * messages for the {@link MessageService}.
	 * 
	 * <p>
	 * Messages are built using the message category, message ID and message
	 * text as follows:
	 * 
	 * <pre>
	 *	CATEGORY ID: TEXT
	 * </pre>
	 * </p>
	 */
	MessageProducer STANDARD_BUILDER = (k, m) -> {
		Objects.requireNonNull(m, "Message entity cannot be null");
		return m.getCategory() + " " + m.getId() + ": " + m.getText();
	};

	/**
	 * Constant simple {@code MessageBuilder} for constructing cacheable
	 * messages for the {@link MessageService} using only the message text.
	 */
	MessageProducer SIMPLE_BUILDER = (k, m) -> {
		Objects.requireNonNull(m, "Message entity cannot be null");
		return m.getText();
	};

	/**
	 * Builds the cacheable message String constructed from the specified
	 * {@code messageKey} and {@code message} entity.
	 * 
	 * @param messageKey
	 * @param message
	 * @return message String
	 */
	String build(final MessageKey messageKey, final Message message);

}
