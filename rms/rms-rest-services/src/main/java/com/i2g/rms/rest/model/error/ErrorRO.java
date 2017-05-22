package com.i2g.rms.rest.model.error;

import java.util.Arrays;
import java.util.List;

/**
 * REST Object for returning error information to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class ErrorRO {
	
	/** Display title for error on the UI. */
	private final String _title;
	/** List of one or more error messages. */
	private final List<String> _errorMessages;
	
	
	/**
	 * Creates a new instance of {@code ErrorRO} with the specified 
	 * {@code errorMessage}.
	 * 
	 * @param errorMessage 
	 */
	public ErrorRO(final String errorMessage) {
		this(null, errorMessage);
	}
	
	/**
	 * Creates a new instance of {@code ErrorRO} with the specified list of
	 * {@code errorMessages}.
	 * 
	 * @param errorMessages 
	 */
	public ErrorRO(final List<String> errorMessages) {
		this(null, errorMessages);
	}
	
	/**
	 * Creates a new instance of {@code ErrorRO} with the specified display 
	 * {@code title} and {@code errorMessage}.
	 * 
	 * @param title to display
	 * @param errorMessage 
	 */
	public ErrorRO(final String title, final String errorMessage) {
		_errorMessages = Arrays.asList(errorMessage);
		_title = title;
	}
	
	/**
	 * Creates a new instance of {@code ErrorRO} with the specified display 
	 * {@code title} and list of {@code errorMessages}.
	 * 
	 * @param title to display
	 * @param errorMessages 
	 */
	public ErrorRO(final String title, final List<String> errorMessages) {
		_errorMessages = errorMessages;
		_title = title;
	}
	
	/**
	 * Returns the display title for error(s).
	 * 
	 * @return display title
	 */
	public String getTitle() {
		return _title;
	}
	
	/**
	 * Returns the list of error messages.
	 * 
	 * @return error messages
	 */
	public List<String> getErrorMessages() {
		return _errorMessages;
	}
	
}
