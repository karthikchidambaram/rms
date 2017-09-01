package com.i2g.rms.rest.service;

import java.util.Objects;

import com.i2g.rms.service.message.MessageKey;
import com.i2g.rms.service.message.MessageProducer;

/**
 * Enumeration to define message constants for the common error messages in
 * REST end points.
 *
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public enum RestMessage implements MessageKey {
	
	TEST_ERROR_MESSAGE(1001),
	DEPT_NO_NULL_OR_EMPTY(1002),
	DEPT_NAME_NULL_OR_EMPTY(1003),
	DEPT_LOC_NULL_OR_EMPTY(1004),
	INVALID_REQUEST_BODY(1005),
	DEPT_DOES_NOT_EXIST(1006),
	LOGIN_REQ_BODY_NULL_OR_EMPTY(1007),
	USER_LOGIN_ID_NULL_OR_EMPTY(1008),
	PASSWORD_NULL_OR_EMPTY(1009),
	INVALID_SESSION(1010),
	
	/** Table Maintenance Error Codes */
	INPUT_OBJECT_NULL_OR_EMPTY(1011),
	CODE_NULL_OR_EMPTY(1012),
	DESCRIPTION_NULL_OR_EMPTY(1013),
	TABLE_NAME_NULL_OR_EMPTY(1014),
	OPERATION_NULL_OR_EMPTY(1015),
	OBJECT_ARRAY_NULL_OR_EMPTY(1016),
	CREATE_TABLE_MAINTENANCE_RECORD_FAILED(1017),
	DELETE_OPERATION_FAILED(1018),
	NOT_ALL_RECORDS_WERE_DELETED(1019),
	CREDENTIALS_NOT_PRESENT_IN_REQUEST(1020),
	REQUEST_OBJECT_NULL_OR_EMPTY(1021),
	UNIQUE_INCIDENT_ID_NULL_OR_EMPTY(1022),
	PARENT_CODE_NULL_OR_EMPTY(1023),
	PARENT_OBJECT_NULL_OR_EMPTY(1024),	
	OBJECT_NULL_OR_EMPTY(1025),
	UNABLE_TO_CREATE_RECORD(1026),
	UNABLE_TO_FETCH_USER_DETAILS(1027),
	CONFLICT_BETWEEN_INCIDENT_REPORTED_BY_AND_LOGGED_IN_USER(1028),
	UNABLE_TO_UPDATE_RECORD(1029),
	WEAPON_TYPE_NOT_APPLICABLE_WITHOUT_WEAPON_INVOLVED(1030),
	WEAPON_TYPE_MUST_BE_SPECIFIED_WHEN_WEAPON_INVOLVED(1031),
	EXTERNAL_AGENCY_NOT_APPLICABLE_WITHOUT_AGENCY_CONTACTED(1032),
	EXTERNAL_AGENCY_MUST_BE_SPECIFIED_WHEN_AGENCY_CONTACTED(1033),
	EXTERNAL_AGENCY_DATE_TIME_MUST_BE_SPECIFIED_WHEN_AGENCY_CONTACTED(1034),
	EXTERNAL_AGENCY_DATE_TIME_NOT_APPLICABLE_WITHOUT_AGENCY_CONTACTED(1035)
	;
	
	/** Primary key ID of Rest Message ID entity in persistent store for retrieval. */
	private final Integer _id;

	/**
	 * Creates a new enum {@code RestMessageId} constant with the specified
	 * {@code id}.
	 *
	 * @param id of message entity
	 */
	private RestMessage(final Integer id) {
		_id = Objects.requireNonNull(id, "REST Message ID cannot be null");
	}

	@Override
	public Integer getKey() {
		return _id;
	}

	@Override
	public String getName() {
		return name();
	}

	@Override
	public int getArgsCount() {
		return -1;
	}

	@Override
	public MessageProducer getBuilder() {
		return MessageProducer.STANDARD_BUILDER;
	}
}
