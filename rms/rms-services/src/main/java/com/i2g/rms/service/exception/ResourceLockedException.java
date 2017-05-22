package com.i2g.rms.service.exception;

/**
 * {@link ServiceException} for when a resource is locked and should not be 
 * modified.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class ResourceLockedException extends ServiceException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of {@code ResourceLockedException} with the
	 * specified {@code message}.
	 * 
	 * @param message 
	 */
	public ResourceLockedException(final String message) {
		super(message);
	}
	
	/**
	 * Creates a new instance of {@code ResourceLockedException} with the
	 * specified {@code message} and {@code cause}.
	 * 
	 * @param message
	 * @param cause 
	 */
	public ResourceLockedException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
}
