package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.Message;

/**
 * Data access interface for {@link Message} entities.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public interface MessageDao {
	/**
	 * Returns the {@code Message} entity with the specified {@code id}.
	 * 
	 * @param id of message
	 * @return message or {@code null} if no message exists with the ID
	 */
	Message get(final Integer id);
	
	/**
	 * Returns all messages.
	 * 
	 * @return messages
	 */
	List<Message> getMessages();
	
	/**
	 * Returns {@code true} if a {@code Message} exists with the specified 
	 * {@code id}.
	 * 
	 * @param id (non-null)
	 * @return {@code true} if message ID exists
	 */
	boolean exists(final Integer id);
	
	/**
	 * Persists the specified {@code message} to the persistent store.
	 * 
	 * @param message to persist
	 * @return persisted message
	 */
	Message create(final Message message);
	
	/**
	 * Updates the specified {@code message} in the persistent store.
	 * 
	 * @param message to update
	 */
	void update(final Message message);
	
	/**
	 * Deletes the specified {@code message} from the persistent store.
	 * 
	 * @param message to delete
	 */
	void delete(final Message message);
}
