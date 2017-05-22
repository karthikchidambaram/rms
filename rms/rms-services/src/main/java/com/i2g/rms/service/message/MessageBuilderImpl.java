package com.i2g.rms.service.message;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.service.AbstractService;
import com.i2g.rms.service.support.Resetable;

/**
 * Service defining how dynamic messages are created from a provided
 * {@link MessageKey}.
 * 
 * <p><strong>Note:</strong> Implementation is thread-safe.</p>
 * 
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Service
public class MessageBuilderImpl extends AbstractService implements MessageBuilder, Resetable {
	
	/** Service for looking up messages to populate in cache. */
	@Autowired
	private MessageService _messageService;
	
	/** Map for caching messages by key. */
	private final ConcurrentMap<MessageKey, String> _messageCache = new ConcurrentHashMap<>();
	
	@Override
	public void reset() {
		_messageCache.clear();
	}
	
	/**
	 * Creates a dynamic message for the specified {@code messageKey}, 
	 * populating any replacements with the optional list of {@code arguments}.
	 * 
	 * @param messageKey (non-null)
	 * @param arguments (optional) replacement values
	 * @return generated message
	 */
	@Override
	public String build(final MessageKey messageKey, final Object... arguments) {
		// Retrieve message from cache or generate into cache if not present
		final String message = _messageCache.computeIfAbsent(messageKey, (k) -> _messageService.getMessage(k));
		
		// Generate the dynamic message by formatting any supplied arguments
		return MessageFormat.format(message, arguments);
	}
}
