package com.i2g.rms.service.exception;

/**
 * Generic service exception for the service layer.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class ServiceException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServiceException() {
		super();
	}

	/**
	 * Creates a new instance of {@link ServiceException} with the specified
	 * {@code message}.
	 * 
	 * @param message (non-null)
	 */
	public ServiceException(final String message) {
		super(message);
	}
	
	/**
	 * Creates a new instance of {@link ServiceException} with the specified
	 * {@code message} and {@code cause}.
	 * 
	 * @param message (non-null)
	 * @param cause (non-null)
	 */
	public ServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
}
