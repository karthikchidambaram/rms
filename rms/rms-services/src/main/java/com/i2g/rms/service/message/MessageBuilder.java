package com.i2g.rms.service.message;

/**
 * Service defining how dynamic messages are created from a provided
 * {@link MessageKey}.
 * 
 * <p><strong>Note:</strong> Implementation is thread-safe.</p>
 * 
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public interface MessageBuilder {
	/**
	 * Creates a dynamic message for the specified {@code messageKey}, 
	 * populating any replacements with the optional list of {@code arguments}.
	 * 
	 * @param messageKey (non-null)
	 * @param arguments (optional) replacement values
	 * @return generated message
	 */
	public String build(final MessageKey messageKey, final Object... arguments);
}
