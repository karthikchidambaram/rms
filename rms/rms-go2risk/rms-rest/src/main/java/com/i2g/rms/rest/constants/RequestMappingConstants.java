package com.i2g.rms.rest.constants;

public interface RequestMappingConstants {
	
	/** URL pattern for protecting rest end points, this requires authentication.*/
	String SECURED 	= "/s";
	/** Public URL base pattern */
	String PUBLIC 	= "/p";
	
	/** Login */
	String LOGIN 					= PUBLIC + "/login";
	String SECURED_LOGIN			= SECURED + "/login";
	
	String LOGIN_ACTION 				= LOGIN + "/do-login";
	String LOGIN_ACTION_FROM_HTML 		= LOGIN + "/do-login-html";
	String LOGIN_ACTION_BASIC 			= LOGIN + "/do-login-basic";
	String LOGIN_STATUS_CHECK 			= LOGIN + "/check-login-status/{loginId}";
	String LOGOUT_ACTION 				= LOGIN + "/do-logout/{loginId}";
	String GET_RMS_ENCRYPTED_PASSWORD	= LOGIN + "/get-rms-encrypted-password";
	String GET_RMS_BCRYPT_PASSWORD		= LOGIN + "/get-rms-bcrypt-password";
	String LOGIN_ACTION_STATELESS		= LOGIN + "/do-login-stateless";
	
	/** Password History */
	String PASSWORD_HISTORY 			= SECURED + "/password-history";
	String GET_ALL_PASSWORD_HISTORIES 	= PASSWORD_HISTORY + "/password-histories";
	
	/** Permission */
	String PERMISSION 			= SECURED + "/permission";
	String GET_ALL_PERMISSIONS 	= PERMISSION + "/permissions";
	
	/** Role */
	String ROLE 			= SECURED + "/role";
	String GET_ALL_ROLES 	= ROLE + "/roles";
	
	/** Investigation Team */
	String INVESTIGATION_TEAM			= SECURED + "/investigation-team";
	String GET_ALL_INVESTIGATION_TEAMS 	= INVESTIGATION_TEAM + "/investigation-teams";
	
	/** User */
	String USER 			= SECURED + "/user";
	String GET_ALL_USERS 	= USER + "/users";	
	
	/** Testing purpose URLs */
	String TEST 								= PUBLIC + "/test";
	String TEST_SECURED 						= SECURED + "/test";
	
	String TEST_CONTEXT_PARAMS 					= TEST + "/context-params";
	String TEST_SAY_HELLO 						= TEST + "/{username}";
	String TEST_GREET_CUSTOMER 					= TEST + "/greeting";
	String TEST_GET_DEPT_BY_DEPTNO 				= TEST + "/department/{deptNo}";
	String TEST_GET_ALL_DEPARTMENTS 			= TEST + "/department/departments";
	String TEST_ERROR_MESSAGE 					= TEST + "/error-message";
	String TEST_THROW_EXCEPTION 				= TEST + "/throw-exception";
	String TEST_PAGINATION 						= TEST + "/pagination";
	String TEST_GET_ALL_MY_DEPARTMENTS 			= TEST + "/my-department/departments";
	String TEST_GET_ALL_MY_EMPLOYEES 			= TEST + "/my-employee/employees";
	String TEST_HIBERNATE_SECOND_LEVEL_CACHE 	= TEST + "/second-level-cache";
	String TEST_CREATE_DEPARTMENT 				= TEST + "/department/create-department";
	String TEST_UPDATE_DEPARTMENT 				= TEST + "/department/update-department";
	String TEST_DELETE_DEPARTMENT 				= TEST + "/department/delete-department/{deptNo}";
	
	String TEST_AUTHENTICATION 					= TEST_SECURED + "/test-authentication";
	String TEST_CREATE_MY_DEPARTMENT 			= TEST_SECURED + "/my-department/create-my-department";
	String TEST_UPDATE_MY_DEPARTMENT 			= TEST_SECURED + "/my-department/update-my-department";
	String TEST_DELETE_MY_DEPARTMENT			= TEST_SECURED + "/my-department/delete-my-department/{deptNo}";
	
	/** Table Maintenance Requests */
	String TABLE_MAINTENANCE 		= SECURED + "/table-maintenance";
	
	/** Generic approach for all table maintenance operations */
	String GET_TABLE_MAINTENANCE_DATA 			= TABLE_MAINTENANCE + "/get/{tableName}/{operation}";
	String GET_TABLE_MAINTENANCE_DATA_BY_CODE 	= TABLE_MAINTENANCE + "/getByCode/{tableName}/{operation}/{code}";
	String CREATE_TABLE_MAINTENANCE_DATA 		= TABLE_MAINTENANCE + "/create/{tableName}/{operation}";
	String UPDATE_TABLE_MAINTENANCE_DATA 		= TABLE_MAINTENANCE + "/update/{tableName}/{operation}";
	String DELETE_TABLE_MAINTENANCE_DATA 		= TABLE_MAINTENANCE + "/delete/{tableName}/{operation}";
	
	/** Table Maintenance Individual Approach */
	/** Accident Location Request Mappings */
	String GET_ACCIDENT_LOCATIONS			= TABLE_MAINTENANCE + "/accident-location/accident-locations";
	String GET_ACCIDENT_LOCATION_BY_CODE 	= TABLE_MAINTENANCE + "/accident-location/{code}";
	String CREATE_ACCIDENT_LOCATION 		= TABLE_MAINTENANCE + "/accident-location/create-accident-location";
	String UPDATE_ACCIDENT_LOCATION 		= TABLE_MAINTENANCE + "/accident-location/update-accident-location";
	String DELETE_ACCIDENT_LOCATION 		= TABLE_MAINTENANCE + "/accident-location/delete-accident-location/{code}";
	
	/** Accident Location Details Request Mappings */
	String GET_ACCIDENT_LOCATION_DETAILS			= TABLE_MAINTENANCE + "/accident-location-details/accident-location-details";
	String GET_ACCIDENT_LOCATION_DETAILS_BY_CODE 	= TABLE_MAINTENANCE + "/accident-location-details/{code}";
	String CREATE_ACCIDENT_LOCATION_DETAILS 		= TABLE_MAINTENANCE + "/accident-location-details/create-accident-location-details";
	String UPDATE_ACCIDENT_LOCATION_DETAILS 		= TABLE_MAINTENANCE + "/accident-location-details/update-accident-location-details";
	String DELETE_ACCIDENT_LOCATION_DETAILS 		= TABLE_MAINTENANCE + "/accident-location-details/delete-accident-location-details/{code}";
	
	/** Asset Category Request Mappings */
	String GET_ASSET_CATEGORIES			= TABLE_MAINTENANCE + "/asset-category/asset-categories";
	String GET_ASSET_CATEGORY_BY_CODE 	= TABLE_MAINTENANCE + "/asset-category/{code}";
	String CREATE_ASSET_CATEGORY 		= TABLE_MAINTENANCE + "/asset-category/create-asset-category";
	String UPDATE_ASSET_CATEGORY 		= TABLE_MAINTENANCE + "/asset-category/update-asset-category";
	String DELETE_ASSET_CATEGORY 		= TABLE_MAINTENANCE + "/asset-category/delete-asset-category/{code}";
	
	/** Claim Request Registration Type Request Mappings */
	String GET_CLAIM_REQUEST_REGISTRATION_TYPES			= TABLE_MAINTENANCE + "/claim-request-registration-type/claim-request-registration-types";
	String GET_CLAIM_REQUEST_REGISTRATION_TYPE_BY_CODE 	= TABLE_MAINTENANCE + "/claim-request-registration-type/{code}";
	String CREATE_CLAIM_REQUEST_REGISTRATION_TYPE 		= TABLE_MAINTENANCE + "/claim-request-registration-type/create-claim-request-registration-type";
	String UPDATE_CLAIM_REQUEST_REGISTRATION_TYPE 		= TABLE_MAINTENANCE + "/claim-request-registration-type/update-claim-request-registration-type";
	String DELETE_CLAIM_REQUEST_REGISTRATION_TYPE 		= TABLE_MAINTENANCE + "/claim-request-registration-type/delete-claim-request-registration-type/{code}";
	
	/** Claim Status Request Mappings */
	String GET_CLAIM_STATUSES			= TABLE_MAINTENANCE + "/claim-status/claim-statuses";
	String GET_CLAIM_STATUS_BY_CODE 	= TABLE_MAINTENANCE + "/claim-status/{code}";
	String CREATE_CLAIM_STATUS 			= TABLE_MAINTENANCE + "/claim-status/create-claim-status";
	String UPDATE_CLAIM_STATUS 			= TABLE_MAINTENANCE + "/claim-status/update-claim-status";
	String DELETE_CLAIM_STATUS 			= TABLE_MAINTENANCE + "/claim-status/delete-claim-status/{code}";
	
	/** Claim Type Request Mappings */
	String GET_CLAIM_TYPES			= TABLE_MAINTENANCE + "/claim-type/claim-types";
	String GET_CLAIM_TYPE_BY_CODE 	= TABLE_MAINTENANCE + "/claim-type/{code}";
	String CREATE_CLAIM_TYPE 		= TABLE_MAINTENANCE + "/claim-type/create-claim-type";
	String UPDATE_CLAIM_TYPE 		= TABLE_MAINTENANCE + "/claim-type/update-claim-type";
	String DELETE_CLAIM_TYPE 		= TABLE_MAINTENANCE + "/claim-type/delete-claim-type/{code}";
	
	/** Distinguishing Features Request Mappings */
	String GET_DISTINGUISHING_FEATURES			= TABLE_MAINTENANCE + "/distinguishing-features/distinguishing-features";
	String GET_DISTINGUISHING_FEATURES_BY_CODE 	= TABLE_MAINTENANCE + "/distinguishing-features/{code}";
	String CREATE_DISTINGUISHING_FEATURES 		= TABLE_MAINTENANCE + "/distinguishing-features/create-distinguishing-features";
	String UPDATE_DISTINGUISHING_FEATURES 		= TABLE_MAINTENANCE + "/distinguishing-features/update-distinguishing-features";
	String DELETE_DISTINGUISHING_FEATURES 		= TABLE_MAINTENANCE + "/distinguishing-features/delete-distinguishing-features/{code}";
	
	/** Distinguishing Features Detail Request Mappings */
	String GET_DISTINGUISHING_FEATURES_DETAILS			= TABLE_MAINTENANCE + "/distinguishing-features-detail/distinguishing-features-details";
	String GET_DISTINGUISHING_FEATURES_DETAILS_BY_CODE 	= TABLE_MAINTENANCE + "/distinguishing-features-detail/{code}";
	String CREATE_DISTINGUISHING_FEATURES_DETAILS 		= TABLE_MAINTENANCE + "/distinguishing-features-detail/create-distinguishing-features-detail";
	String UPDATE_DISTINGUISHING_FEATURES_DETAILS 		= TABLE_MAINTENANCE + "/distinguishing-features-detail/update-distinguishing-features-detail";
	String DELETE_DISTINGUISHING_FEATURES_DETAILS 		= TABLE_MAINTENANCE + "/distinguishing-features-detail/delete-distinguishing-features-detail/{code}";
	
	/** Employee Type Request Mappings */
	String GET_EMPLOYEE_TYPES			= TABLE_MAINTENANCE + "/employee-type/employee-types";
	String GET_EMPLOYEE_TYPE_BY_CODE 	= TABLE_MAINTENANCE + "/employee-type/{code}";
	String CREATE_EMPLOYEE_TYPE 		= TABLE_MAINTENANCE + "/employee-type/create-employee-type";
	String UPDATE_EMPLOYEE_TYPE 		= TABLE_MAINTENANCE + "/employee-type/update-employee-type";
	String DELETE_EMPLOYEE_TYPE 		= TABLE_MAINTENANCE + "/employee-type/delete-employee-type/{code}";
	
	/** Entry Point Request Mappings */
	String GET_ENTRY_POINTS			= TABLE_MAINTENANCE + "/entry-point/entry-points";
	String GET_ENTRY_POINT_BY_CODE 	= TABLE_MAINTENANCE + "/entry-point/{code}";
	String CREATE_ENTRY_POINT 		= TABLE_MAINTENANCE + "/entry-point/create-entry-point";
	String UPDATE_ENTRY_POINT 		= TABLE_MAINTENANCE + "/entry-point/update-entry-point";
	String DELETE_ENTRY_POINT 		= TABLE_MAINTENANCE + "/entry-point/delete-entry-point/{code}";
	
	/** Event Type Request Mappings */
	String GET_EVENT_TYPES			= TABLE_MAINTENANCE + "/event-type/event-types";
	String GET_EVENT_TYPE_BY_CODE 	= TABLE_MAINTENANCE + "/event-type/{code}";
	String CREATE_EVENT_TYPE 		= TABLE_MAINTENANCE + "/event-type/create-event-type";
	String UPDATE_EVENT_TYPE 		= TABLE_MAINTENANCE + "/event-type/update-event-type";
	String DELETE_EVENT_TYPE 		= TABLE_MAINTENANCE + "/event-type/delete-event-type/{code}";
	
	/** External Agencies Request Mappings */
	String GET_EXTERNAL_AGENCIES		= TABLE_MAINTENANCE + "/external-agency/external-agencies";
	String GET_EXTERNAL_AGENCY_BY_CODE 	= TABLE_MAINTENANCE + "/external-agency/{code}";
	String CREATE_EXTERNAL_AGENCY 		= TABLE_MAINTENANCE + "/external-agency/create-external-agency";
	String UPDATE_EXTERNAL_AGENCY 		= TABLE_MAINTENANCE + "/external-agency/update-external-agency";
	String DELETE_EXTERNAL_AGENCY 		= TABLE_MAINTENANCE + "/external-agency/delete-external-agency/{code}";
	
	/** Incident Location Request Mappings */
	String GET_INCIDENT_LOCATIONS			= TABLE_MAINTENANCE + "/incident-location/incident-locations";
	String GET_INCIDENT_LOCATION_BY_CODE 	= TABLE_MAINTENANCE + "/incident-location/{code}";
	String CREATE_INCIDENT_LOCATION 		= TABLE_MAINTENANCE + "/incident-location/create-incident-location";
	String UPDATE_INCIDENT_LOCATION 		= TABLE_MAINTENANCE + "/incident-location/update-incident-location";
	String DELETE_INCIDENT_LOCATION 		= TABLE_MAINTENANCE + "/incident-location/delete-incident-location/{code}";
	
	/** Incident Location Details Request Mappings */
	String GET_INCIDENT_LOCATION_DETAILS			= TABLE_MAINTENANCE + "/incident-location-details/incident-location-details";
	String GET_INCIDENT_LOCATION_DETAILS_BY_CODE 	= TABLE_MAINTENANCE + "/incident-location-details/{code}";
	String CREATE_INCIDENT_LOCATION_DETAILS 		= TABLE_MAINTENANCE + "/incident-location-details/create-incident-location-details";
	String UPDATE_INCIDENT_LOCATION_DETAILS 		= TABLE_MAINTENANCE + "/incident-location-details/update-incident-location-details";
	String DELETE_INCIDENT_LOCATION_DETAILS 		= TABLE_MAINTENANCE + "/incident-location-details/delete-incident-location-details/{code}";
	
	/** Incident Type Request Mappings */
	String GET_INCIDENT_TYPES			= TABLE_MAINTENANCE + "/incident-type/incident-types";
	String GET_INCIDENT_TYPE_BY_CODE 	= TABLE_MAINTENANCE + "/incident-type/{code}";
	String CREATE_INCIDENT_TYPE 		= TABLE_MAINTENANCE + "/incident-type/create-incident-type";
	String UPDATE_INCIDENT_TYPE 		= TABLE_MAINTENANCE + "/incident-type/update-incident-type";
	String DELETE_INCIDENT_TYPE 		= TABLE_MAINTENANCE + "/incident-type/delete-incident-type/{code}";
	
	/** Injury Cause Request Mappings */
	String GET_INJURY_CAUSES			= TABLE_MAINTENANCE + "/injury-cause/injury-causes";
	String GET_INJURY_CAUSE_BY_CODE 	= TABLE_MAINTENANCE + "/injury-cause/{code}";
	String CREATE_INJURY_CAUSE 			= TABLE_MAINTENANCE + "/injury-cause/create-injury-cause";
	String UPDATE_INJURY_CAUSE 			= TABLE_MAINTENANCE + "/injury-cause/update-injury-cause";
	String DELETE_INJURY_CAUSE 			= TABLE_MAINTENANCE + "/injury-cause/delete-injury-cause/{code}";
	
	/** Injury Type Request Mappings */
	String GET_INJURY_TYPES				= TABLE_MAINTENANCE + "/injury-type/injury-types";
	String GET_INJURY_TYPE_BY_CODE 		= TABLE_MAINTENANCE + "/injury-type/{code}";
	String CREATE_INJURY_TYPE 			= TABLE_MAINTENANCE + "/injury-type/create-injury-type";
	String UPDATE_INJURY_TYPE 			= TABLE_MAINTENANCE + "/injury-type/update-injury-type";
	String DELETE_INJURY_TYPE 			= TABLE_MAINTENANCE + "/injury-type/delete-injury-type/{code}";
	
	/** Injury Type Details Request Mappings */
	String GET_INJURY_TYPE_DETAILS				= TABLE_MAINTENANCE + "/injury-type-details/injury-type-details";
	String GET_INJURY_TYPE_DETAILS_BY_CODE 		= TABLE_MAINTENANCE + "/injury-type-details/{code}";
	String CREATE_INJURY_TYPE_DETAILS 			= TABLE_MAINTENANCE + "/injury-type-details/create-injury-type-details";
	String UPDATE_INJURY_TYPE_DETAILS 			= TABLE_MAINTENANCE + "/injury-type-details/update-injury-type-details";
	String DELETE_INJURY_TYPE_DETAILS 			= TABLE_MAINTENANCE + "/injury-type-details/delete-injury-type-details/{code}";
	
	/** Injury Type Details Specification Request Mappings */
	String GET_INJURY_TYPE_DETAILS_SPECS			= TABLE_MAINTENANCE + "/injury-type-details-spec/injury-type-details-specs";
	String GET_INJURY_TYPE_DETAILS_SPEC_BY_CODE		= TABLE_MAINTENANCE + "/injury-type-details-spec/{code}";
	String CREATE_INJURY_TYPE_DETAILS_SPEC 			= TABLE_MAINTENANCE + "/injury-type-details-spec/create-injury-type-details-spec";
	String UPDATE_INJURY_TYPE_DETAILS_SPEC 			= TABLE_MAINTENANCE + "/injury-type-details-spec/update-injury-type-details-spec";
	String DELETE_INJURY_TYPE_DETAILS_SPEC 			= TABLE_MAINTENANCE + "/injury-type-details-spec/delete-injury-type-details-spec/{code}";
	
	/** Suspect Type Request Mappings */
	String GET_SUSPECT_TYPES				= TABLE_MAINTENANCE + "/suspect-type/suspect-types";
	String GET_SUSPECT_TYPE_BY_CODE 		= TABLE_MAINTENANCE + "/suspect-type/{code}";
	String CREATE_SUSPECT_TYPE 				= TABLE_MAINTENANCE + "/suspect-type/create-suspect-type";
	String UPDATE_SUSPECT_TYPE 				= TABLE_MAINTENANCE + "/suspect-type/update-suspect-type";
	String DELETE_SUSPECT_TYPE 				= TABLE_MAINTENANCE + "/suspect-type/delete-suspect-type/{code}";
	
	/** Weapon Type Request Mappings */
	String GET_WEAPON_TYPES				= TABLE_MAINTENANCE + "/weapon-type/weapons-types";
	String GET_WEAPON_TYPE_BY_CODE 		= TABLE_MAINTENANCE + "/weapon-type/{code}";
	String CREATE_WEAPON_TYPE			= TABLE_MAINTENANCE + "/weapon-type/create-weapon-type";
	String UPDATE_WEAPON_TYPE			= TABLE_MAINTENANCE + "/weapon-type/update-weapon-type";
	String DELETE_WEAPON_TYPE			= TABLE_MAINTENANCE + "/weapon-type/delete-weapon-type/{code}";
	
}
