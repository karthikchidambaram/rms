package com.i2g.rms.service.message;

/**
 * Interface for defining a message key for retrieving dynamic messages through
 * the associated {@link MessageService}.
 * 
 * <p><strong>Note:</strong> Message key implementations must provide valid 
 * {@link #equals(java.lang.Object)} and {@link #hashCode()} methods as the keys
 * will be used for storing/retrieval of associated values.  Instances must be
 * immutable and thread-safe.</p>
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public interface MessageKey {
	/**
	 * Returns the message key for retrieving the message.
	 * 
	 * @return message key
	 */
	Integer getKey();
	
	/**
	 * Returns the message name.
	 * 
	 * @return message name
	 */
	String getName();
	
	/**
	 * Returns the expected number of arguments for the message.
	 * 
	 * @return expected number of arguments for message
	 */
	int getArgsCount();
	
	/**
	 * Returns the message builder for constructing the replacement message.
	 * 
	 * @return message builder
	 */
	MessageProducer getBuilder();
}
