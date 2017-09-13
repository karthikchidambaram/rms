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
	
	/** Accident Location Detail Request Mappings */
	String GET_ACCIDENT_LOCATION_DETAILS					= TABLE_MAINTENANCE + "/accident-location-detail/accident-location-details";
	String GET_ACCIDENT_LOCATION_DETAIL_BY_CODE 			= TABLE_MAINTENANCE + "/accident-location-detail/{code}";
	String GET_ACCIDENT_LOCATION_DETAILS_FOR_PARENT_CODE	= TABLE_MAINTENANCE + "/accident-location-detail/accident-location/{code}";
	String CREATE_ACCIDENT_LOCATION_DETAIL 					= TABLE_MAINTENANCE + "/accident-location-detail/create-accident-location-detail";
	String UPDATE_ACCIDENT_LOCATION_DETAIL 					= TABLE_MAINTENANCE + "/accident-location-detail/update-accident-location-detail";
	String DELETE_ACCIDENT_LOCATION_DETAIL 					= TABLE_MAINTENANCE + "/accident-location-detail/delete-accident-location-detail/{code}";
	
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
	
	/** Distinguishing Feature Request Mappings */
	String GET_DISTINGUISHING_FEATURES			= TABLE_MAINTENANCE + "/distinguishing-feature/distinguishing-features";
	String GET_DISTINGUISHING_FEATURE_BY_CODE 	= TABLE_MAINTENANCE + "/distinguishing-feature/{code}";
	String CREATE_DISTINGUISHING_FEATURE 		= TABLE_MAINTENANCE + "/distinguishing-feature/create-distinguishing-feature";
	String UPDATE_DISTINGUISHING_FEATURE 		= TABLE_MAINTENANCE + "/distinguishing-feature/update-distinguishing-feature";
	String DELETE_DISTINGUISHING_FEATURE 		= TABLE_MAINTENANCE + "/distinguishing-feature/delete-distinguishing-feature/{code}";
	
	/** Distinguishing Features Detail Request Mappings */
	String GET_DISTINGUISHING_FEATURE_DETAILS				= TABLE_MAINTENANCE + "/distinguishing-feature-detail/distinguishing-feature-details";
	String GET_DISTINGUISHING_FEATURE_DETAILS_FOR_PARENT	= TABLE_MAINTENANCE + "/distinguishing-feature-detail/distinguishing-feature/{code}";
	String GET_DISTINGUISHING_FEATURE_DETAIL_BY_CODE 		= TABLE_MAINTENANCE + "/distinguishing-feature-detail/{code}";
	String CREATE_DISTINGUISHING_FEATURE_DETAIL 			= TABLE_MAINTENANCE + "/distinguishing-feature-detail/create-distinguishing-feature-detail";
	String UPDATE_DISTINGUISHING_FEATURE_DETAIL 			= TABLE_MAINTENANCE + "/distinguishing-feature-detail/update-distinguishing-feature-detail";
	String DELETE_DISTINGUISHING_FEATURE_DETAIL 			= TABLE_MAINTENANCE + "/distinguishing-feature-detail/delete-distinguishing-feature-detail/{code}";
	
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
	
	/** Incident Location Detail Request Mappings */
	String GET_INCIDENT_LOCATION_DETAILS					= TABLE_MAINTENANCE + "/incident-location-detail/incident-location-details";
	String GET_INCIDENT_LOCATION_DETAIL_BY_CODE 			= TABLE_MAINTENANCE + "/incident-location-detail/{code}";
	String GET_INCIDENT_LOCATION_DETAILS_FOR_PARENT_CODE 	= TABLE_MAINTENANCE + "/incident-location-detail/incident-location/{code}";
	String CREATE_INCIDENT_LOCATION_DETAIL 					= TABLE_MAINTENANCE + "/incident-location-detail/create-incident-location-detail";
	String UPDATE_INCIDENT_LOCATION_DETAIL 					= TABLE_MAINTENANCE + "/incident-location-detail/update-incident-location-detail";
	String DELETE_INCIDENT_LOCATION_DETAIL 					= TABLE_MAINTENANCE + "/incident-location-detail/delete-incident-location-detail/{code}";
	
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
	String GET_INJURY_TYPE_DETAILS				= TABLE_MAINTENANCE + "/injury-type-detail/injury-type-details";
	String GET_INJURY_TYPE_DETAILS_FOR_PARENT	= TABLE_MAINTENANCE + "/injury-type-detail/injury-type/{code}";
	String GET_INJURY_TYPE_DETAIL_BY_CODE 		= TABLE_MAINTENANCE + "/injury-type-detail/{code}";
	String CREATE_INJURY_TYPE_DETAIL 			= TABLE_MAINTENANCE + "/injury-type-detail/create-injury-type-detail";
	String UPDATE_INJURY_TYPE_DETAIL 			= TABLE_MAINTENANCE + "/injury-type-detail/update-injury-type-detail";
	String DELETE_INJURY_TYPE_DETAIL 			= TABLE_MAINTENANCE + "/injury-type-detail/delete-injury-type-detail/{code}";
	
	/** Injury Type Details Specification Request Mappings */
	String GET_INJURY_TYPE_DETAIL_SPECS				= TABLE_MAINTENANCE + "/injury-type-detail-spec/injury-type-detail-specs";
	String GET_INJURY_TYPE_DETAIL_SPECS_FOR_PARENT	= TABLE_MAINTENANCE + "/injury-type-detail-spec/injury-type-detail/{code}";
	String GET_INJURY_TYPE_DETAIL_SPEC_BY_CODE		= TABLE_MAINTENANCE + "/injury-type-detail-spec/{code}";
	String CREATE_INJURY_TYPE_DETAIL_SPEC 			= TABLE_MAINTENANCE + "/injury-type-detail-spec/create-injury-type-detail-spec";
	String UPDATE_INJURY_TYPE_DETAIL_SPEC 			= TABLE_MAINTENANCE + "/injury-type-detail-spec/update-injury-type-detail-spec";
	String DELETE_INJURY_TYPE_DETAIL_SPEC 			= TABLE_MAINTENANCE + "/injury-type-detail-spec/delete-injury-type-detail-spec/{code}";
	
	/** Suspect Type Request Mappings */
	String GET_SUSPECT_TYPES				= TABLE_MAINTENANCE + "/suspect-type/suspect-types";
	String GET_SUSPECT_TYPE_BY_CODE 		= TABLE_MAINTENANCE + "/suspect-type/{code}";
	String CREATE_SUSPECT_TYPE 				= TABLE_MAINTENANCE + "/suspect-type/create-suspect-type";
	String UPDATE_SUSPECT_TYPE 				= TABLE_MAINTENANCE + "/suspect-type/update-suspect-type";
	String DELETE_SUSPECT_TYPE 				= TABLE_MAINTENANCE + "/suspect-type/delete-suspect-type/{code}";
	
	/** Weapon Type Request Mappings */
	String GET_WEAPON_TYPES				= TABLE_MAINTENANCE + "/weapon-type/weapon-types";
	String GET_WEAPON_TYPE_BY_CODE 		= TABLE_MAINTENANCE + "/weapon-type/{code}";
	String CREATE_WEAPON_TYPE			= TABLE_MAINTENANCE + "/weapon-type/create-weapon-type";
	String UPDATE_WEAPON_TYPE			= TABLE_MAINTENANCE + "/weapon-type/update-weapon-type";
	String DELETE_WEAPON_TYPE			= TABLE_MAINTENANCE + "/weapon-type/delete-weapon-type/{code}";
	
	/** Accident Type Request Mappings */
	String GET_ACCIDENT_TYPES				= TABLE_MAINTENANCE + "/accident-type/accident-types";
	String GET_ACCIDENT_TYPE_BY_CODE 		= TABLE_MAINTENANCE + "/accident-type/{code}";
	String CREATE_ACCIDENT_TYPE				= TABLE_MAINTENANCE + "/accident-type/create-accident-type";
	String UPDATE_ACCIDENT_TYPE				= TABLE_MAINTENANCE + "/accident-type/update-accident-type";
	String DELETE_ACCIDENT_TYPE				= TABLE_MAINTENANCE + "/accident-type/delete-accident-type/{code}";
	
	/** Body Parts Request Mappings */
	String GET_BODY_PARTS					= TABLE_MAINTENANCE + "/body-part/body-parts";
	String GET_BODY_PART_BY_CODE 			= TABLE_MAINTENANCE + "/body-part/{code}";
	String CREATE_BODY_PART_TYPE			= TABLE_MAINTENANCE + "/body-part/create-body-part";
	String UPDATE_BODY_PART_TYPE			= TABLE_MAINTENANCE + "/body-part/update-body-part";
	String DELETE_BODY_PART_TYPE			= TABLE_MAINTENANCE + "/body-part/delete-body-part/{code}";
	
	/** Document Category Request Mappings */
	String GET_DOCUMENT_CATEGORIES				= TABLE_MAINTENANCE + "/document-category/document-categories";
	String GET_DOCUMENT_CATEGORY_BY_CODE 		= TABLE_MAINTENANCE + "/document-category/{code}";
	String CREATE_DOCUMENT_CATEGORY				= TABLE_MAINTENANCE + "/document-category/create-document-category";
	String UPDATE_DOCUMENT_CATEGORY				= TABLE_MAINTENANCE + "/document-category/update-document-category";
	String DELETE_DOCUMENT_CATEGORY				= TABLE_MAINTENANCE + "/document-category/delete-document-category/{code}";
	
	/** Document Type Request Mappings */
	String GET_DOCUMENT_TYPES				= TABLE_MAINTENANCE + "/document-type/document-types";
	String GET_DOCUMENT_TYPE_BY_CODE 		= TABLE_MAINTENANCE + "/document-type/{code}";
	String CREATE_DOCUMENT_TYPE				= TABLE_MAINTENANCE + "/document-type/create-weapon-type";
	String UPDATE_DOCUMENT_TYPE				= TABLE_MAINTENANCE + "/document-type/update-weapon-type";
	String DELETE_DOCUMENT_TYPE				= TABLE_MAINTENANCE + "/document-type/delete-weapon-type/{code}";
	
	/** Gender Type Request Mappings */
	String GET_GENDER_TYPES				= TABLE_MAINTENANCE + "/gender-type/gender-types";
	String GET_GENDER_TYPE_BY_CODE 		= TABLE_MAINTENANCE + "/gender-type/{code}";
	String CREATE_GENDER_TYPE			= TABLE_MAINTENANCE + "/gender-type/create-gender-type";
	String UPDATE_GENDER_TYPE			= TABLE_MAINTENANCE + "/gender-type/update-gender-type";
	String DELETE_GENDER_TYPE			= TABLE_MAINTENANCE + "/gender-type/delete-gender-type/{code}";
	
	/** Incident Categories Request Mappings */
	String GET_INCIDENT_CATEGORIES			= TABLE_MAINTENANCE + "/incident-category/incident-categories";
	String GET_INCIDENT_CATEGORY_BY_CODE 	= TABLE_MAINTENANCE + "/incident-category/{code}";
	String CREATE_INCIDENT_CATEGORY			= TABLE_MAINTENANCE + "/incident-category/create-incident-category";
	String UPDATE_INCIDENT_CATEGORY			= TABLE_MAINTENANCE + "/incident-category/update-incident-category";
	String DELETE_INCIDENT_CATEGORY			= TABLE_MAINTENANCE + "/incident-category/delete-incident-category/{code}";
	
	/** Injured Person Type Request Mappings */
	String GET_INJURED_PERSON_TYPES				= TABLE_MAINTENANCE + "/injured-person-type/injured-person-types";
	String GET_INJURED_PERSON_TYPE_BY_CODE 		= TABLE_MAINTENANCE + "/injured-person-type/{code}";
	String CREATE_INJURED_PERSON_TYPE			= TABLE_MAINTENANCE + "/injured-person-type/create-injured-person-type";
	String UPDATE_INJURED_PERSON_TYPE			= TABLE_MAINTENANCE + "/injured-person-type/update-injured-person-type";
	String DELETE_INJURED_PERSON_TYPE			= TABLE_MAINTENANCE + "/injured-person-type/delete-injured-person-type/{code}";
	
	/** Loss Type Request Mappings */
	String GET_LOSS_TYPES			= TABLE_MAINTENANCE + "/loss-type/loss-types";
	String GET_LOSS_TYPE_BY_CODE	= TABLE_MAINTENANCE + "/loss-type/{code}";
	String CREATE_LOSS_TYPE			= TABLE_MAINTENANCE + "/loss-type/create-loss-type";
	String UPDATE_LOSS_TYPE			= TABLE_MAINTENANCE + "/loss-type/update-loss-type";
	String DELETE_LOSS_TYPE			= TABLE_MAINTENANCE + "/loss-type/delete-loss-type/{code}";
	
	/** Policy Type Request Mappings */
	String GET_POLICY_TYPES				= TABLE_MAINTENANCE + "/policy-type/policy-types";
	String GET_POLICY_TYPE_BY_CODE 		= TABLE_MAINTENANCE + "/policy-type/{code}";
	String CREATE_POLICY_TYPE			= TABLE_MAINTENANCE + "/policy-type/create-policy-type";
	String UPDATE_POLICY_TYPE			= TABLE_MAINTENANCE + "/policy-type/update-policy-type";
	String DELETE_POLICY_TYPE			= TABLE_MAINTENANCE + "/policy-type/delete-policy-type/{code}";
	
	/** Vehicle Damage Type Request Mappings */
	String GET_VEHICLE_DAMAGE_TYPES				= TABLE_MAINTENANCE + "/vehicle-damage-type/vehicle-damage-types";
	String GET_VEHICLE_DAMAGE_TYPE_BY_CODE 		= TABLE_MAINTENANCE + "/vehicle-damage-type/{code}";
	String CREATE_VEHICLE_DAMAGE_TYPE			= TABLE_MAINTENANCE + "/vehicle-damage-type/create-vehicle-damage-type";
	String UPDATE_VEHICLE_DAMAGE_TYPE			= TABLE_MAINTENANCE + "/vehicle-damage-type/update-vehicle-damage-type";
	String DELETE_VEHICLE_DAMAGE_TYPE			= TABLE_MAINTENANCE + "/vehicle-damage-type/delete-vehicle-damage-type/{code}";
	
	/** Organization Request Mappings */
	String GET_ORGANIZATIONS				= TABLE_MAINTENANCE + "/organization/organizations";
	String GET_ORGANIZATION_BY_CODE 		= TABLE_MAINTENANCE + "/organization/{code}";
	String CREATE_ORGANIZATION				= TABLE_MAINTENANCE + "/organization/create-organization";
	String UPDATE_ORGANIZATION				= TABLE_MAINTENANCE + "/organization/update-organization";
	String DELETE_ORGANIZATION				= TABLE_MAINTENANCE + "/organization/delete-organization/{code}";
	
	/** Department Request Mappings */
	String GET_DEPARTMENTS						= TABLE_MAINTENANCE + "/department/departments";
	String GET_DEPARTMENTS_FOR_ORGANIZATION		= TABLE_MAINTENANCE + "/department/organization/{code}";
	String GET_DEPARTMENT_BY_CODE 				= TABLE_MAINTENANCE + "/department/{code}";
	String CREATE_DEPARTMENT					= TABLE_MAINTENANCE + "/department/create-department";
	String UPDATE_DEPARTMENT					= TABLE_MAINTENANCE + "/department/update-department";
	String DELETE_DEPARTMENT					= TABLE_MAINTENANCE + "/department/delete-department/{code}";
	
	/** Position Request Mappings */
	String GET_POSITIONS							= TABLE_MAINTENANCE + "/position/positions";
	String GET_POSITIONS_FOR_ORGANIZATION			= TABLE_MAINTENANCE + "/position/organization/{code}";
	String GET_POSITIONS_FOR_DEPARTMENT				= TABLE_MAINTENANCE + "/position/department/{code}";
	String GET_POSITIONS_FOR_POSITION_LEVEL			= TABLE_MAINTENANCE + "/position/position-level/{code}";
	String GET_POSITION_BY_CODE 					= TABLE_MAINTENANCE + "/position/{code}";
	String CREATE_POSITION							= TABLE_MAINTENANCE + "/position/create-position";
	String UPDATE_POSITION							= TABLE_MAINTENANCE + "/position/update-position";
	String DELETE_POSITION							= TABLE_MAINTENANCE + "/position/delete-position/{code}";
	
	/** Position Level Request Mappings */
	String GET_POSITION_LEVELS				= TABLE_MAINTENANCE + "/position-level/position-levels";
	String GET_POSITION_LEVEL_BY_CODE 		= TABLE_MAINTENANCE + "/position-level/{code}";
	String CREATE_POSITION_LEVEL			= TABLE_MAINTENANCE + "/position-level/create-position-level";
	String UPDATE_POSITION_LEVEL			= TABLE_MAINTENANCE + "/position-level/update-position-level";
	String DELETE_POSITION_LEVEL			= TABLE_MAINTENANCE + "/position-level/delete-position-level/{code}";
	
	/** Witness Type Request Mappings */
	String GET_WITNESS_TYPES				= TABLE_MAINTENANCE + "/witness-type/witness-types";
	String GET_WITNESS_TYPE_BY_CODE 		= TABLE_MAINTENANCE + "/witness-type/{code}";
	String CREATE_WITNESS_TYPE 				= TABLE_MAINTENANCE + "/witness-type/create-witness-type";
	String UPDATE_WITNESS_TYPE 				= TABLE_MAINTENANCE + "/witness-type/update-witness-type";
	String DELETE_WITNESS_TYPE 				= TABLE_MAINTENANCE + "/witness-type/delete-witness-type/{code}";
	
	/** Incidents */
	String INCIDENT 									= SECURED + "/incident";
	String GET_INCIDENTS								= INCIDENT + "/incidents";
	String GET_INCIDENT_BY_UNIQUE_INCIDENT_ID 			= INCIDENT + "/{uniqueIncidentId}";
	String ADD_INCIDENT 								= INCIDENT + "/add-incident";
	String LOG_INCIDENT 								= INCIDENT + "/log-incident";
	String ADD_INCIDENT_DETAILS							= INCIDENT + "/add-incident-details";
	String ADD_ACCIDENT_DETAILS							= INCIDENT + "/add-accident-details";
	String ADD_ASSET_DETAILS							= INCIDENT + "/add-asset-details";
	String ADD_CRIME_DETAILS							= INCIDENT + "/add-crime-details";
	
	/** Search Incidents */
	String SEARCH_INCIDENT							= SECURED + "/search-incident";	
	
	/** Lookups */
	String USER_LOOKUP 							= SECURED + "/user-lookup";
	String SUSPECT_LOOKUP 						= SECURED + "/suspect-lookup";
	String MANAGER_LOOKUP 						= SECURED + "/manager-lookup";
	String INJURED_PERSON_LOOKUP 				= SECURED + "/injured-person-lookup";
	String WITNESS_LOOKUP 						= SECURED + "/witness-lookup";
	String CRIME_SUSPECT_LOOKUP 				= SECURED + "/crime-suspect-lookup";
	
	/** Address */
	String ADDRESS 								= SECURED + "/address";
	String GET_ADDRESSES						= ADDRESS + "/addresses";
	String GET_ADDRESS							= ADDRESS + "/{id}";
	String CREATE_ADDRESS						= ADDRESS + "/create-address";
}
