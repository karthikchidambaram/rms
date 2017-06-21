package com.i2g.rms.domain.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Static utility for capturing auditing information for a unit of work executed
 * upon a single thread through the use of {@link ThreadLocal}.  The auditing
 * information is captured in domain models which extend from 
 * {@link org.ets.eskm.domain.model.AbstractDataModel}.
 * 
 * <p><strong>Note:</strong> The audit name (process or user ID) should be set
 * from any entry point to a unit of work.  It is the responsibility of the
 * process to ensure proper clean up of the thread context by calling the
 * {@link #clear()} method in a finally block after the unit of work is
 * completed.</p>
 * 
 * @see com.i2g.rms.domain.model.AbstractDataModel<K>
 * @see com.i2g.rms.domain.support.AuditInterceptor
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public final class Auditor {

	/** {@link Logger} instance. */
	private final static Logger LOGGER = LoggerFactory.getLogger(Auditor.class);
	
	/** 
	 * Constant {@code System} property key ({@value}) for defining the default 
	 * audit name when none has been set through 
	 * {@link #setName(java.lang.String)}.
	 */
	public final static String DEFAULT_AUDIT_NAME = "com.i2g.rms.domain.support.auditor.defaultName";
	/**
	 * Maximum length {@value} of the audit name column.
	 */
	public final static int AUDIT_NAME_MAX_LENGTH = 32;
	
	/**
	 * {@link ThreadLocal} implementation for storing the audit name (either a 
	 * process or user ID) for the current unit of work's executing thread.
	 * The initial value will return the {@code System} property value 
	 * associated with {@link #DEFAULT_AUDIT_NAME}.
	 */
	private final static ThreadLocal<String> CALLER_THREAD_CONTEXT = new ThreadLocal<String>() {
		@Override protected String initialValue() {
			final String defaultName = System.getProperty(DEFAULT_AUDIT_NAME);
			LOGGER.trace("Using default audit name: {}", defaultName);
			return defaultName;
		}
	};
	
	
	/**
	 * Private constructor to prevent instantiation of static utility.
	 */
	private Auditor() { }
	
	/**
	 * Sets the specified audit {@code name} for the current unit of work's 
	 * thread.  The {@code name} should be a non-null, non-empty String which
	 * represents the process or user name.  Please note that the audit name
	 * will only be available for the current thread.
	 * 
	 * <p><strong>Note:</strong> The associated {@link #clear()} method should
	 * be called at the end of any unit of work which call this setter.</p>
	 * 
	 * @param name to set for auditing the unit of work
	 * @throws IllegalArgumentException if audit name is null/empty
	 */
	public static void setName(String name) {
		if( name == null || name.isEmpty() ) {
			throw new IllegalArgumentException("Audit name cannot be null/empty");
		} else if( name.length() > AUDIT_NAME_MAX_LENGTH ) {
			LOGGER.warn("Audit name [{}] exceeds maximum length [{}], truncating value...", name, AUDIT_NAME_MAX_LENGTH);
			name = name.substring(0, AUDIT_NAME_MAX_LENGTH);
		}
		CALLER_THREAD_CONTEXT.set(name);
		LOGGER.trace("Set audit name: {}", name);
	}
	
	/**
	 * Returns the audit name (user or process ID) registered on the current
	 * thread for the unit of work as set by {@link #setName(java.lang.String)}
	 * or the default value from the {@code System} property specified by 
	 * {@link #DEFAULT_AUDIT_NAME}.  If no name value is found then an
	 * {@link IllegalStateException} is thrown, as the audit information is not
	 * allowed to be null.
	 * 
	 * @return audit name for the current thread's unit of work
	 * @throws IllegalStateException if no audit name can be found
	 */
	public static String getName() {
		final String auditName = CALLER_THREAD_CONTEXT.get();
		if( auditName == null || auditName.isEmpty() ) {
			throw new IllegalStateException("Audit name cannot be null/empty");
		}
		LOGGER.trace("Retrieved audit name: {}", auditName);
		return auditName;
	}
	
	/**
	 * Clears the current thread context audit name.
	 * 
	 * <p><strong>Note:</strong> This method must be called at the end of any
	 * unit of work which called the associated {@code setName()}.</p>
	 */
	public static void clear() {
		CALLER_THREAD_CONTEXT.remove();
		LOGGER.trace("Cleared audit name.");
	}

}
