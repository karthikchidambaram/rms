package com.i2g.rms.util.security;

public interface RMSSecurityProperties {

	/** RMS Salt Key to encrypt passwords */
	public static final String SALT_KEY = "RMS-S@LT-KEY";
	public static final String RMS_USER_CONTEXT = "RMS_USER_CONTEXT";
	public static final String DEFAULT_PASSWORD = "London01";

	public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	
	public static final long ONE_DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
	public static final long FIVE_DAYS_IN_MILLIS = 1000 * 60 * 60 * 24 * 5;
	public static final long TEN_DAYS_IN_MILLIS = 1000 * 60 * 60 * 24 * 10;
	public static final long FIFTEEN_DAYS_IN_MILLIS = 1000 * 60 * 60 * 24 * 15;
	public static final long TWENTY_DAYS_IN_MILLIS = 1000 * 60 * 60 * 24 * 20;
	public static final long THIRTY_DAYS_IN_MILLIS = 1000 * 60 * 60 * 24 * 30;
	
	public static final String ONE_DAY_IN_SECONDS = "86400";
	public static final String FIVE_DAYS_IN_SECONDS = "432000";
	public static final String TEN_DAYS_IN_SECONDS = "864000";
	public static final String FIFTEEN_DAYS_IN_SECONDS = "1296000";
	public static final String TWENTY_DAYS_IN_SECONDS = "1728000";
	public static final String THIRTY_DAYS_IN_SECONDS = "2592000";
	
	public static final String STATELESS_REALM_NAME = "STATELESS_RESTFUL_REALM";
	public static final String REALM_NAME = "RESTFUL_REALM";
	
	public static final String TOKEN_SECRET = "${token.secret}";
	
	public static final String HMAC_ALGO = "HmacSHA256";
	public static final String SEPARATOR = ".";
	public static final String SEPARATOR_SPLITTER = "\\.";
	
	public static final String APPLICATION_RESPONSE_TYPE = "application/vnd.captech-v1.0+json";
	
	public static final String USER_NOT_AUTHENTICATED_MSG = "{\"message\":\"Full authentication is required to access this resource.\", \"access-denied\":true,\"cause\":\"NOT AUTHENTICATED\"}";
}
