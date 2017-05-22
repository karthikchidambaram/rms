package com.i2g.rms.service.exception;

/**
 * {@link ServiceException} for when a resource already exists, typically when 
 * attempting to create a new entity.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class ResourceAlreadyExistsException extends ServiceException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of {@code ResourceAlreadyExistsException} with the
	 * specified {@code message}.
	 *
	 * @param message
	 */
	public ResourceAlreadyExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Creates a new instance of {@code ResourceAlreadyExistsException} with the
	 * specified {@code message} and {@code cause}.
	 *
	 * @param message
	 * @param cause
	 */
	public ResourceAlreadyExistsException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
}
