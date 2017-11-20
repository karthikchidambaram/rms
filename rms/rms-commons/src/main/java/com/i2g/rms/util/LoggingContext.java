package com.i2g.rms.util;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

public class LoggingContext {
	/**
	 * Constant MDC property key for specifying the unique request ID.
	 * 
	 * <p>
	 * <strong>Property key:</strong> {@value}
	 * </p>
	 */
	public final static String MDC_REQUEST_ID = "REQUEST_ID";

	/**
	 * Constant MDC property key for specifying the test program for an admin.
	 * 
	 * <p>
	 * <strong>Property key:</strong> {@value}
	 * </p>
	 */
	public final static String MDC_TEST_PROGRAM = "TEST_PROGRAM";

	/**
	 * Constant MDC property key for specifying the test title for an admin.
	 * 
	 * <p>
	 * <strong>Property key:</strong> {@value}
	 * </p>
	 */
	public final static String MDC_TEST_TITLE = "TEST_TITLE";

	/**
	 * Constant MDC property key for specifying the admin code for an admin.
	 * 
	 * <p>
	 * <strong>Property key:</strong> {@value}
	 * </p>
	 */
	public final static String MDC_ADMIN_CODE = "ADMIN_CODE";

	/**
	 * Constant MDC property key for specifying the package code for an admin.
	 * 
	 * <p>
	 * <strong>Property key:</strong> {@value}
	 * </p>
	 */
	public final static String MDC_PACKAGE_CODE = "PROGRAM_CODE";

	/**
	 * Constant character delimiter ({@value}) used to separate logging fields.
	 */
	public final static char DELIMITER = '|';

	/**
	 * Constant logging context ID ({@value}) to represent the system.
	 */
	public final static String SYSTEM_ID = "SYSTEM";

	/**
	 * Private constructor to prevent instantiation of static utility.
	 */
	private LoggingContext() {
	}

	/**
	 * Sets the specified {@code ids} as the mapped diagnostic context value for
	 * the MDC key {@value #MDC_REQUEST_ID}. If more than one ID is specified,
	 * then the values are joined together using delimiter {@link #DELIMITER}.
	 * If the specified value is {@code null} or empty, then a unique ID is
	 * generated and set using {@link #setId(java.lang.Object[])}. The method
	 * returns the unique ID value which was set in the {@code MDC}.
	 * 
	 * @param ids
	 *            to set in {@code MDC}
	 * @return value set in {@code MDC}
	 */
	public static String setId(final Object... ids) {
		if (ids == null || ids.length == 0) {
			return setGeneratedId(); // Generate unique ID if none specified
		}
		final String uniqueId = ids.length == 1 ? String.valueOf(ids[0]) : StringUtils.join(ids, DELIMITER);
		MDC.put(MDC_REQUEST_ID, uniqueId);
		return uniqueId;
	}

	/**
	 * Sets the mapped diagnostic context value for the MDC key
	 * {@value #MDC_REQUEST_ID} to a randomly generated unique value using
	 * {@link UUID#randomUUID()} followed by the specified {@code ids} and
	 * returns the value set in the {@code MDC}.
	 * 
	 * @param ids
	 *            to append to unique generated ID
	 * @return generated value and appended {@code ids} set in {@code MDC}
	 */
	public static String setGeneratedId(final Object... ids) {
		if (ids == null || ids.length == 0) {
			return setGeneratedId();
		}
		final String uniqueId = UUID.randomUUID().toString() + DELIMITER
				+ (ids.length == 1 ? String.valueOf(ids[0]) : StringUtils.join(ids, DELIMITER));
		MDC.put(MDC_REQUEST_ID, uniqueId);
		return uniqueId;
	}

	/**
	 * Sets the mapped diagnostic context value for the MDC key
	 * {@value #MDC_REQUEST_ID} to a randomly generated unique value using
	 * {@link UUID#randomUUID()} and returns the value set in the {@code MDC}.
	 * 
	 * @see java.util.UUID
	 * 
	 * @return generated value set in {@code MDC}
	 */
	public static String setGeneratedId() {
		final String uniqueId = UUID.randomUUID().toString();
		MDC.put(MDC_REQUEST_ID, uniqueId);
		return uniqueId;
	}

	/**
	 * Sets the mapped diagnostic context value for the MDC key
	 * {@value #MDC_REQUEST_ID} to {@link #SYSTEM_ID} and returns the set value.
	 * 
	 * @return {@link #SYSTEM_ID} set in {@code MDC}
	 */
	public static String setSystemId() {
		return setId(SYSTEM_ID);
	}

	/**
	 * Appends the specified {@code ids} to the currently set mapped diagnostic
	 * context value for the MDC key {@value #MDC_REQUEST_ID}, separating each
	 * value with {@value #DELIMITER}.
	 * 
	 * @param ids
	 *            to append to current ID
	 * @return new ID set in {@code MDC}
	 */
	public static String appendId(final Object... ids) {
		if (ids == null || ids.length == 0) {
			return getId(); // Return the current ID if nothing to append
		}
		final String appendId = ids.length == 1 ? String.valueOf(ids[0]) : StringUtils.join(ids, DELIMITER);
		MDC.put(MDC_REQUEST_ID, getId() + DELIMITER + appendId);
		return getId();
	}

	/**
	 * Returns the current ID value set in the {@code MDC} as specified by the
	 * key {@value #MDC_REQUEST_ID}.
	 * 
	 * @return current ID set in {@code MDC}
	 */
	public static String getId() {
		return MDC.get(MDC_REQUEST_ID);
	}

	/**
	 * Clears the {@code MDC} and returns the value previously set for
	 * {@link #MDC_REQUEST_ID}.
	 * 
	 * @return value set for {@link #MDC_REQUEST_ID}
	 */
	public static String clear() {
		final String uniqueId = MDC.get(MDC_REQUEST_ID);
		MDC.clear();
		return uniqueId;
	}
}
