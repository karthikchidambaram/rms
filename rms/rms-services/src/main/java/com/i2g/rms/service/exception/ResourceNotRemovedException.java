package com.i2g.rms.service.exception;

/**
 * {@link ServiceException} for when a resource is not valid.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class ResourceNotRemovedException extends ServiceException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotRemovedException() {
		super();
	}

	/**
	 * Creates a new instance of {@code ResourceNotRemovedException} with the
	 * specified {@code message}.
	 * 
	 * @param message 
	 */
	public ResourceNotRemovedException(final String message) {
		super(message);
	}
	
	/**
	 * Creates a new instance of {@code ResourceNotRemovedException} with the
	 * specified {@code message} and {@code cause}.
	 * 
	 * @param message
	 * @param cause 
	 */
	public ResourceNotRemovedException(final String message, final Throwable cause) {
		super(message, cause);
	}	
}
