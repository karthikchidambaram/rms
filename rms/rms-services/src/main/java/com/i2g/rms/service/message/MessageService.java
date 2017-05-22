package com.i2g.rms.service.message;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.Message;
import com.i2g.rms.domain.model.Message.Category;
import com.i2g.rms.domain.model.Message.Type;
import com.i2g.rms.persistence.dao.MessageDao;
import com.i2g.rms.service.exception.ResourceAlreadyExistsException;
import com.i2g.rms.service.exception.ResourceNotFoundException;

/**
 * Service for working with {@link Message} entities.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Service
public class MessageService {

	/** {@code Logger} instance. */
	private final Logger _logger = LoggerFactory.getLogger(MessageService.class);

	/** Message data access interface. */
	@Autowired
	private MessageDao _messageDao;

	/**
	 * Returns all messages (supports filtering).
	 * 
	 * @return list of all messages
	 */
	@Transactional(readOnly = true)
	public List<Message> getMessages() {
		return _messageDao.getMessages();
	}

	/**
	 * Returns the message with the specified {@code msgId}.
	 * 
	 * @param id
	 *            (non-null)
	 * @return message or {@code null} if no matching record
	 */
	@Transactional(readOnly = true)
	public Message getMessage(final Integer id) {
		return _messageDao.get(id);
	}

	/**
	 * Returns the message content for the specified {@code messageKey} or
	 * throws an {@link ResourceNotFoundException} if the key is not found.
	 * 
	 * @param messageKey
	 *            (non-null)
	 * @return message content
	 * @throws ResourceNotFoundException
	 *             if message is not found
	 */
	@Transactional(readOnly = true, noRollbackFor = { ResourceNotFoundException.class, NullPointerException.class })
	public String getMessage(final MessageKey messageKey) throws ResourceNotFoundException {
		final Message message = _messageDao
				.get(Objects.requireNonNull(messageKey, "Message key cannot be null").getKey());
		if (message == null) {
			throw new ResourceNotFoundException(
					"MessageKey [" + messageKey.getName() + "](" + messageKey.getKey() + ") not found");
		}
		return messageKey.getBuilder().build(messageKey, message);
	}

	/**
	 * Returns {@code true} if a message exists with the specified {@code id}.
	 * 
	 * @param id
	 * @return {@code true} if message exists
	 */
	@Transactional(readOnly = true)
	public boolean exists(final Integer id) {
		return _messageDao.exists(id);
	}

	/**
	 * Creates a new persistent {@link Message} with the specified {@code id},
	 * {@code text}, {@code category} and {@code type}.
	 * 
	 * @param id
	 *            (non-null)
	 * @param text
	 *            (non-null)
	 * @param category
	 *            (non-null)
	 * @param type
	 *            (non-null)
	 * @return new persistent message entity
	 * @throws ResourceAlreadyExistsException
	 *             if message ID already exists
	 */
	@Transactional
	public Message createMessage(final Integer id, final String text, final Category category, final Type type)
			throws ResourceAlreadyExistsException {
		if (exists(id)) {
			throw new ResourceAlreadyExistsException("Message [" + id + "] already exists");
		}
		final Message message = new Message(id, text, category, type);
		_messageDao.create(message);
		_logger.info("Created new message: {}", message);
		return message;
	}

	/**
	 * Updates the specified {@code message}.
	 * 
	 * @param message
	 *            (non-null)
	 * @return updated message
	 */
	@Transactional
	public Message updateMessage(final Message message) {
		_messageDao.update(message);
		_logger.info("Updated message to: {}", message);
		return message;
	}

	/**
	 * Deletes the specified {@code message}.
	 * 
	 * @param message
	 *            (non-null)
	 */
	@Transactional
	public void deleteMessage(final Message message) {
		_messageDao.delete(message);
		_logger.info("Deleted message: {}", message);
	}
}
