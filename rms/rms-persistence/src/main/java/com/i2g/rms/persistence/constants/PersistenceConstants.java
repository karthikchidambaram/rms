package com.i2g.rms.persistence.constants;

public final class PersistenceConstants {

	/**
	 * Constant configuration property key for specifying the JNDI name for the
	 * rms-persistence RMS Oracle database JDBC connection pool. The JDBC
	 * {@link javax.sql.DataSource} is managed/configured through JBoss and
	 * loaded through the Spring configuration.
	 * 
	 * <p>
	 * <strong>Property Key:</strong> {@value} <br/>
	 * <strong>Value:</strong> {@code java.lang.String} - JNDI pool name
	 * </p>
	 */
	public final static String RMS_JDBC_POOL = "com.i2g.rms.persistence.rmsPool";	

	/**
	 * Private constructor to prevent instantiation of static constants class.
	 */
	private PersistenceConstants() {
	}

}
