package com.i2g.rms.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Static utility class providing helper methods for retrieving cached
 * information about the current runtime environment.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public final class RuntimeInfo {

	/** {@code Logger} instance. */
	private final static Logger LOGGER = LoggerFactory.getLogger(RuntimeInfo.class);

	/** Constant property key for build version. */
	final static String BUILD_VERSION = "com.i2g.rms.build.version";
	/** Constant property key for build revision. */
	final static String BUILD_REVISION = "com.i2g.rms.build.revision";
	/** Constant property key for build branch. */
	final static String BUILD_BRANCH = "com.i2g.rms.build.branch";
	/** Constant property key for build Java version. */
	final static String BUILD_JAVA_VERSION = "com.i2g.rms.build.javaBuildVersion";
	/** Constant property key for build Java target version. */
	final static String BUILD_JAVA_TARGET_VERSION = "com.i2g.rms.build.javaTargetVersion";
	/** Constant property key for build create date. */
	final static String BUILD_CREATE_DATE = "com.i2g.rms.build.createDate";
	/** Constant property key for build created by. */
	final static String BUILD_CREATED_BY = "com.i2g.rms.build.createdBy";

	/**
	 * Constant for the server name as retrieved through {@link InetAddress}.
	 */
	private final static String SERVER_NAME;
	/**
	 * Constant for the server IP address as retrieved through
	 * {@link InetAddress}.
	 */
	private final static String SERVER_IP;
	/** Constant map containing build properties. */
	private final static Properties BUILD_PROPERTIES = new Properties();

	/*
	 * Static initialization of constant values at JVM load. Any errors should
	 * be thrown as an ExceptionInInitializationError to halt JVM.
	 */
	static {
		InputStream in = null;
		try {
			// Cache lookup of server host name, IP address and build properties
			SERVER_NAME = resolveServerName();
			SERVER_IP = resolveServerAddress();
			BUILD_PROPERTIES.load(in = RuntimeInfo.class.getResourceAsStream("build.properties"));
		} catch (IOException e) {
			LOGGER.error("Fatal error: Failed to initialize server name/IP", e);
			throw new ExceptionInInitializerError(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * Private constructor to prevent instantiation of static utility.
	 */
	private RuntimeInfo() {
	}

	/**
	 * Returns the local server name; if any error occurs while attempting to
	 * resolve local host name, then default "UNKNOWN SERVER" is returned.
	 * 
	 * <p>
	 * <strong>Note:</strong> Method should remain private as it's an internal
	 * helper for resolving server name and should not be invoked externally.
	 * Any errors should be logged to notify support team.
	 * </p>
	 * 
	 * @return server name
	 */
	private static String resolveServerName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// Log as error to send notication email something is not right!
			LOGGER.error("ERROR: Failed to initialize server name, defaulting to 'UNKNOWN SERVER'", e);
			return "UNKNOWN SERVER";
		}
	}

	/**
	 * Returns the local server IP address; if any error occurs while attempting
	 * to resolve local IP address, then default "UNKNOWN ADDRESS" is returned.
	 * 
	 * <p>
	 * <strong>Note:</strong> Method should remain private as it's an internal
	 * helper for resolving server address and should not be invoked externally.
	 * Any errors should be logged to notify support team.
	 * </p>
	 * 
	 * @return server address
	 */
	private static String resolveServerAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// Log as error to send notication email something is not right!
			LOGGER.error("ERROR: Failed to initialize server address, defaulting to 'UNKNOWN ADDRESS'", e);
			return "UNKNOWN ADDRESS";
		}
	}

	/**
	 * Returns the server's host name as determined by
	 * {@link InetAddress#getLocalHost()}.
	 * 
	 * @return server host name
	 */
	public static String getServerName() {
		return SERVER_NAME;
	}

	/**
	 * Returns the server's IP address as determined by
	 * {@link InetAddress#getLocalHost()}.
	 * 
	 * @return server IP address
	 */
	public static String getServerIP() {
		return SERVER_IP;
	}

	/**
	 * Returns the average system load of server.
	 * 
	 * @see java.lang.management.OperatingSystemMXBean#getSystemLoadAverage()
	 * 
	 * @return average system load
	 */
	public static double getServerSystemLoad() {
		return ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();
	}

	/**
	 * Returns the timestamp when the JVM was started.
	 * 
	 * @see java.lang.management.RuntimeMXBean#getStartTime()
	 * 
	 * @return JVM start timestamp
	 */
	public static long getJvmStartTime() {
		return ManagementFactory.getRuntimeMXBean().getStartTime();
	}

	/**
	 * Returns the amount of time in milliseconds the JVM has been running.
	 * 
	 * @see java.lang.management.RuntimeMXBean#getUptime()
	 * 
	 * @return JVM up time in milliseconds
	 */
	public static long getJvmUpTime() {
		return ManagementFactory.getRuntimeMXBean().getUptime();
	}

	/**
	 * Returns the currently used heap memory in bytes.
	 * 
	 * @see java.lang.management.MemoryMXBean#getHeapMemoryUsage()
	 * 
	 * @return used heap memory in bytes
	 */
	public static long getJvmUsedHeapMemory() {
		return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
	}

	/**
	 * Returns the currently used non-heap memory in bytes.
	 * 
	 * @see java.lang.management.MemoryMXBean#getNonHeapMemoryUsage()
	 * 
	 * @return used non-heap memory in bytes
	 */
	public static long getJvmUsedNonHeapMemory() {
		return ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
	}

	/**
	 * Returns the build version.
	 * 
	 * @since 2.0.0
	 * @return build version
	 */
	public static String getBuildVersion() {
		return BUILD_PROPERTIES.getProperty(BUILD_VERSION, "Unknown version");
	}

	/**
	 * Returns the build revision.
	 * 
	 * @since 2.0.0
	 * @return build revision
	 */
	public static String getBuildRevision() {
		return BUILD_PROPERTIES.getProperty(BUILD_REVISION, "Unknown revision");
	}

	/**
	 * Returns the build branch.
	 * 
	 * @since 2.0.0
	 * @return build branch
	 */
	public static String getBuildBranch() {
		return BUILD_PROPERTIES.getProperty(BUILD_BRANCH, "Unknown branch");
	}

	/**
	 * Returns the build Java version.
	 * 
	 * @since 2.0.0
	 * @return build Java version
	 */
	public static String getBuildJavaVersion() {
		return BUILD_PROPERTIES.getProperty(BUILD_JAVA_VERSION, "Unknown Java version");
	}

	/**
	 * Returns the build Java target version.
	 * 
	 * @since 2.0.0
	 * @return build Java target version
	 */
	public static String getBuildJavaTargetVersion() {
		return BUILD_PROPERTIES.getProperty(BUILD_JAVA_TARGET_VERSION, "Unknown Java target version");
	}

	/**
	 * Returns the build create date.
	 * 
	 * @since 2.0.0
	 * @return build create date
	 */
	public static String getBuildCreateDate() {
		return BUILD_PROPERTIES.getProperty(BUILD_CREATE_DATE, "Unknown create date");
	}

	/**
	 * Returns the build created by.
	 * 
	 * @since 2.0.0
	 * @return build created by
	 */
	public static String getBuildCreatedBy() {
		return BUILD_PROPERTIES.getProperty(BUILD_CREATED_BY, "Unknown created by");
	}

}
