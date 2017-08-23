package com.i2g.rms.persistence.exception;

public class DaoObjectCreationFailedException extends DaoException {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DaoObjectCreationFailedException() {
		super();
	}
	
	public DaoObjectCreationFailedException(final String message) {
		super(message);
	}
	
	public DaoObjectCreationFailedException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	@Override
	public String toString() {
		return "Exception while trying to create a record.";
	}
}
