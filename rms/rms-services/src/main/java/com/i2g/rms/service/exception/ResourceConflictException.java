package com.i2g.rms.service.exception;

/**
 * {@link ServiceException} for when a resource conflict exists; this may 
 * indicate an invalid/unexpected state during processing.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class ResourceConflictException extends ServiceException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of {@code ResourceConflictException} with the
	 * specified {@code message}.
	 * 
	 * @param message 
	 */
	public ResourceConflictException(final String message) {
		super(message);
	}
	
	/**
	 * Creates a new instance of {@code ResourceConflictException} with the
	 * specified {@code message} and {@code cause}.
	 * 
	 * @param message
	 * @param cause 
	 */
	public ResourceConflictException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
}
