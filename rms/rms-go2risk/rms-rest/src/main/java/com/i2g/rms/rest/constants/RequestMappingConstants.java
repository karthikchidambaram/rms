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
	String ROLE 						= SECURED + "/role";
	String GET_ALL_ROLES 				= ROLE + "/roles";
	String GET_ROLE_BY_ROLE_ID 			= ROLE + "/{id}";
	String GET_ROLE_BY_ROLE_NAME		= ROLE + "/role-name/{roleName}";
	String DELETE_PERMISSION_FROM_ROLE	= ROLE + "/role-name/{roleName}/permission-name/{permissionName}";
		
	/** Document */
	String DOCUMENT 					= SECURED + "/document";
	String GET_ALL_DOCUMENTS			= DOCUMENT + "/documents";
	String GET_DOCUMENT_BY_ID			= DOCUMENT + "/{documentId}";
	String GET_DOCUMENTS_BY_INCIDENT_ID	= DOCUMENT + "/documents-for-incident/{uniqueIncidentId}";
	String SAVE_DOCUMENTS				= DOCUMENT + "/save-documents";
	String SAVE_DOCUMENT				= DOCUMENT + "/save-document";
	String DELETE_DOCUMENT				= DOCUMENT + "/delete-document/{id}";	
	String DELETE_DOCUMENTS				= DOCUMENT + "/delete-documents";
	String DOWNLOAD_DOCUMENT			= DOCUMENT + "/download-document/{id}";
	
	/** Investigation Team */
	String INVESTIGATION_TEAM			= SECURED + "/investigation-team";
	String GET_ALL_INVESTIGATION_TEAMS 	= INVESTIGATION_TEAM + "/investigation-teams";
	
	/** User */
	String USER 					= SECURED + "/user";
	String GET_ALL_USERS 			= USER + "/users";
	String GET_USER_BY_LOGIN_ID 	= USER + "/{loginId}";
	
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
	String TEST_FILE_UPLOAD						= TEST + "/file-upload";
			
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
	String GET_BODY_PARTS									= TABLE_MAINTENANCE + "/body-part/body-parts";
	String GET_BODY_PART_BY_CODE 							= TABLE_MAINTENANCE + "/body-part/{code}";
	String CREATE_BODY_PART_TYPE							= TABLE_MAINTENANCE + "/body-part/create-body-part";
	String UPDATE_BODY_PART_TYPE							= TABLE_MAINTENANCE + "/body-part/update-body-part";
	String CREATE_BODY_PART_TYPE_WITH_FRONT_OR_BACK			= TABLE_MAINTENANCE + "/body-part/create-body-part-fb";
	String UPDATE_BODY_PART_TYPE_WITH_FRONT_OR_BACK			= TABLE_MAINTENANCE + "/body-part/update-body-part-fb";
	String DELETE_BODY_PART_TYPE							= TABLE_MAINTENANCE + "/body-part/delete-body-part/{code}";
	
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
	String ADD_CLAIM_DETAILS							= INCIDENT + "/add-claim-details";
	String ADD_INVESTIGATION_DETAILS					= INCIDENT + "/add-investigation-details";
	String ADD_OR_UPDATE_INVESTIGATION_DETAILS			= INCIDENT + "/add-or-update-investigation-details";
	String ADD_SUPPORTING_DOCUMENTS						= INCIDENT + "/add-supporting-documents";
	
	String ADD_SUSPECT_FOR_INCIDENT							= INCIDENT + "/add-suspect/uniqueIncidentId/{uniqueIncidentId}";
	String ADD_SUSPECTS_FOR_INCIDENT						= INCIDENT + "/add-suspects";
	String ADD_EXISTING_SUSPECT_FOR_INCIDENT				= INCIDENT + "/add-existing-suspect/uniqueIncidentId/{uniqueIncidentId}/suspectId/{suspectId}";
	String ADD_EXISTING_SUSPECTS_FOR_INCIDENT				= INCIDENT + "/add-existing-suspects";
	String ADD_EMPLOYEE_SUSPECT_FOR_INCIDENT_BY_ID			= INCIDENT + "/add-employee-suspect/uniqueIncidentId/{uniqueIncidentId}/employeeId/{employeeId}";
	String ADD_EMPLOYEE_SUSPECT_FOR_INCIDENT_BY_LOGIN_ID	= INCIDENT + "/add-employee-suspect/uniqueIncidentId/{uniqueIncidentId}/employeeLoginId/{employeeLoginId}";
	String ADD_EMPLOYEE_SUSPECTS_FOR_INCIDENT_BY_IDS		= INCIDENT + "/add-emp-suspects-by-ids";
	String ADD_EMPLOYEE_SUSPECTS_FOR_INCIDENT_BY_LOGIN_IDS	= INCIDENT + "/add-emp-suspects-by-login-ids";
	String REMOVE_SUSPECT_FROM_INCIDENT						= INCIDENT + "/remove-suspect/uniqueIncidentId/{uniqueIncidentId}/suspectId/{suspectId}";
	String REMOVE_SUSPECTS_FROM_INCIDENT					= INCIDENT + "/remove-suspects";
	String REMOVE_EMPLOYEE_SUSPECT_FROM_INCIDENT_BY_ID				= INCIDENT + "/remove-employee-suspect/uniqueIncidentId/{uniqueIncidentId}/employeeId/{employeeId}";
	String REMOVE_EMPLOYEE_SUSPECT_FROM_INCIDENT_BY_LOGIN_ID		= INCIDENT + "/remove-employee-suspect/uniqueIncidentId/{uniqueIncidentId}/employeeLoginId/{employeeLoginId}";
	String REMOVE_EMPLOYEE_SUSPECTS_FROM_INCIDENT_BY_IDS			= INCIDENT + "/remove-employee-suspects-by-ids";
	String REMOVE_EMPLOYEE_SUSPECTS_FROM_INCIDENT_BY_LOGIN_IDS		= INCIDENT + "/remove-employee-suspects-by-login-ids";
		
	/** Update flow - Incidents */
	String UPDATE_LOG_INCIDENT 							= INCIDENT + "/update-log-incident";
		
	/** Add or update incident flows */
	String ADD_OR_UPDATE_LOG_INCIDENT 					= INCIDENT + "/add-or-update-log-incident";
				
	/** Suspect operations */
	String SUSPECT 												= SECURED + "/suspect";
	String GET_ALL_SUSPECTS										= SUSPECT + "/suspects";
	String GET_SUSPECT_BY_SUSPECT_ID							= SUSPECT + "/suspectId/{suspectId}";
	String CREATE_SUSPECT										= SUSPECT + "/create-suspect";
	String CREATE_SUSPECTS										= SUSPECT + "/create-suspects";
	String UPDATE_SUSPECT										= SUSPECT + "/update-suspect";
	String UPDATE_SUSPECTS										= SUSPECT + "/update-suspects";
	String REMOVE_DISTINGUISHING_FEATURE_DETAILS_FROM_SUSPECT 	= SUSPECT + "/remove-dist-fea-details";	
	
	/** Witness operations */
	String WITNESS 												= SECURED + "/witness";
	String GET_ALL_WITNESSES									= WITNESS + "/witnesses";
	String GET_WITNESS_BY_WITNESS_ID							= WITNESS + "/witnessId/{witnessId}";
	String CREATE_WITNESS										= WITNESS + "/create-witness";
	String CREATE_WITNESSES										= WITNESS + "/create-witnesses";
	String UPDATE_WITNESS										= WITNESS + "/update-witness";
	String UPDATE_WITNESSES										= WITNESS + "/update-witnesses";
	String REMOVE_DISTINGUISHING_FEATURE_DETAILS_FROM_WITNESS 	= WITNESS + "/remove-dist-fea-details";
	
	/** Accident operations */
	String ACCIDENT												= SECURED + "/accident";
	String GET_ALL_ACCIDENTS									= ACCIDENT + "/accidents";
	String GET_ACCIDENT_BY_ACCIDENT_ID							= ACCIDENT + "/accidentId/{accidentId}";
	String GET_ACCIDENT_BY_INCIDENT_ID							= ACCIDENT + "/incidentId/{incidentId}";
	String GET_ACCIDENT_BY_UNIQUE_INCIDENT_ID					= ACCIDENT + "/uniqueIncidentId/{uniqueIncidentId}";
	String CREATE_ACCIDENT										= ACCIDENT + "/create-accident";
	String UPDATE_ACCIDENT										= ACCIDENT + "/update-accident";
	String ADD_OR_UPDATE_ACCIDENT								= ACCIDENT + "/add-or-update-accident";
	
	String ADD_INJURED_PERSON_TO_ACCIDENT								= ACCIDENT + "/add-injured-person/accidentId/{accidentId}";
	String ADD_INJURED_PERSONS_TO_ACCIDENT								= ACCIDENT + "/add-injured-persons";
	String ADD_EXISTING_INJURED_PERSON_TO_ACCIDENT						= ACCIDENT + "/add-existing-injured-person/accidentId/{accidentId}/injuredPersonId/{injuredPersonId}";
	String ADD_EXISTING_INJURED_PERSONS_TO_ACCIDENT						= ACCIDENT + "/add-existing-injured-persons";
	String ADD_EMPLOYEE_INJURED_PERSON_TO_ACCIDENT_BY_ID				= ACCIDENT + "/add-employee-injured-person/accidentId/{accidentId}/employeeId/{employeeId}";
	String ADD_EMPLOYEE_INJURED_PERSON_TO_ACCIDENT_BY_LOGIN_ID			= ACCIDENT + "/add-employee-injured-person/accidentId/{accidentId}/employeeLoginId/{employeeLoginId}";
	String ADD_EMPLOYEE_INJURED_PERSONS_TO_ACCIDENT_BY_IDS				= ACCIDENT + "/add-employee-injured-persons-by-ids";
	String ADD_EMPLOYEE_INJURED_PERSONS_TO_ACCIDENT_BY_LOGIN_IDS		= ACCIDENT + "/add-employee-injured-persons-by-login-ids";
	String REMOVE_INJURED_PERSON_FROM_ACCIDENT							= ACCIDENT + "/remove-injured-person/accidentId/{accidentId}/injuredPersonId/{injuredPersonId}";
	String REMOVE_INJURED_PERSONS_FROM_ACCIDENT							= ACCIDENT + "/remove-injured-persons";
	String REMOVE_EMPLOYEE_INJURED_PERSON_FROM_ACCIDENT_BY_ID			= ACCIDENT + "/remove-employee-injured-person/accidentId/{accidentId}/employeeId/{employeeId}";
	String REMOVE_EMPLOYEE_INJURED_PERSON_FROM_ACCIDENT_BY_LOGIN_ID		= ACCIDENT + "/remove-employee-injured-person/accidentId/{accidentId}/employeeLoginId/{employeeLoginId}";
	String REMOVE_EMPLOYEE_INJURED_PERSONS_FROM_ACCIDENT_BY_IDS			= ACCIDENT + "/remove-employee-injured-persons-by-ids";
	String REMOVE_EMPLOYEE_INJURED_PERSONS_FROM_ACCIDENT_BY_LOGIN_IDS	= ACCIDENT + "/remove-employee-injured-persons-by-login-ids";
	
	String ADD_WITNESS_TO_ACCIDENT									= ACCIDENT + "/add-witness/accidentId/{accidentId}";
	String ADD_WITNESSES_TO_ACCIDENT								= ACCIDENT + "/add-witnesses";
	String ADD_EXISTING_WITNESS_TO_ACCIDENT							= ACCIDENT + "/add-existing-witness/accidentId/{accidentId}/witnessId/{witnessId}";
	String ADD_EXISTING_WITNESSES_TO_ACCIDENT						= ACCIDENT + "/add-existing-witnesses";
	String ADD_EMPLOYEE_WITNESS_TO_ACCIDENT_BY_ID					= ACCIDENT + "/add-employee-witness/accidentId/{accidentId}/employeeId/{employeeId}";
	String ADD_EMPLOYEE_WITNESS_TO_ACCIDENT_BY_LOGIN_ID				= ACCIDENT + "/add-employee-witness/accidentId/{accidentId}/employeeLoginId/{employeeLoginId}";
	String ADD_EMPLOYEE_WITNESSES_TO_ACCIDENT_BY_IDS				= ACCIDENT + "/add-employee-witnesses-by-ids";
	String ADD_EMPLOYEE_WITNESSES_TO_ACCIDENT_BY_LOGIN_IDS			= ACCIDENT + "/add-employee-witnesses-by-login-ids";
	String REMOVE_WITNESS_FROM_ACCIDENT								= ACCIDENT + "/remove-witness/accidentId/{accidentId}/witnessId/{witnessId}";
	String REMOVE_WITNESSES_FROM_ACCIDENT							= ACCIDENT + "/remove-witnesses";
	String REMOVE_EMPLOYEE_WITNESS_FROM_ACCIDENT_BY_ID				= ACCIDENT + "/remove-employee-witness/accidentId/{accidentId}/employeeId/{employeeId}";
	String REMOVE_EMPLOYEE_WITNESS_FROM_ACCIDENT_BY_LOGIN_ID		= ACCIDENT + "/remove-employee-witness/accidentId/{accidentId}/employeeLoginId/{employeeLoginId}";
	String REMOVE_EMPLOYEE_WITNESSES_FROM_ACCIDENT_BY_IDS			= ACCIDENT + "/remove-employee-witnesses-by-ids";
	String REMOVE_EMPLOYEE_WITNESSES_FROM_ACCIDENT_BY_LOGIN_IDS		= ACCIDENT + "/remove-employee-witnesses-by-login-ids";
	
	/** Reported Loss flows */
	String REPORTED_LOSS 												= SECURED + "/reported-loss";
	String GET_ALL_REPORTED_LOSSES										= REPORTED_LOSS + "/reported-losses";
	String GET_REPORTED_LOSS_BY_REPORTED_LOSS_ID						= REPORTED_LOSS + "/reportedLossId/{reportedLossId}";
	String CREATE_REPORTED_LOSS											= REPORTED_LOSS + "/create-reported-loss";
	String CREATE_REPORTED_LOSSES										= REPORTED_LOSS + "/create-reported-losses";
	String UPDATE_REPORTED_LOSS											= REPORTED_LOSS + "/update-reported-loss";
	String UPDATE_REPORTED_LOSSES										= REPORTED_LOSS + "/update-reported-losses";
	String DELETE_REPORTED_LOSS											= REPORTED_LOSS + "/delete-reported-loss/reportedLossId/{reportedLossId}";
	String DELETE_REPORTED_LOSSES										= REPORTED_LOSS + "/delete-reported-losses";
	
	/** Search Incidents */
	String SEARCH_INCIDENT						= SECURED + "/search-incident";	
	
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
	String GET_ADDRESSES_FOR_USER_ID			= ADDRESS + "/addresses/userId/{userId}";
	String GET_ADDRESSES_FOR_USER_LOGIN_ID		= ADDRESS + "/addresses/userLoginId/{userLoginId}";
	String GET_ADDRESSES_FOR_BUILDING_ID		= ADDRESS + "/addresses/buildingId/{buildingId}";
	String GET_ADDRESSES_FOR_WITNESS_ID			= ADDRESS + "/addresses/witnessId/{witnessId}";
	String GET_ADDRESSES_FOR_SUSPECT_ID			= ADDRESS + "/addresses/suspectId/{suspectId}";
	String GET_ADDRESSES_FOR_CRIME_SUSPECT_ID	= ADDRESS + "/addresses/crimeSuspectId/{crimeSuspectId}";
	String GET_ADDRESSES_FOR_INJURED_PERSON_ID	= ADDRESS + "/addresses/injuredPersonId/{injuredPersonId}";
	String GET_ADDRESS							= ADDRESS + "/addressId/{addressId}";
	String CREATE_ADDRESS						= ADDRESS + "/create-address";
	String CREATE_ADDRESSES						= ADDRESS + "/create-addresses";
	String UPDATE_ADDRESS						= ADDRESS + "/update-address";
	String UPDATE_ADDRESSES						= ADDRESS + "/update-addresses";
	String DELETE_ADDRESS						= ADDRESS + "/delete-address/addressId/{addressId}";
	String DELETE_ADDRESSES						= ADDRESS + "/delete-addresses";		
	
	/** Office Address */
	String OFFICE_ADDRESS 								= SECURED + "/office-address";
	String GET_OFFICE_ADDRESSES							= OFFICE_ADDRESS + "/office-addresses";
	String GET_OFFICE_ADDRESS							= OFFICE_ADDRESS + "/officeAddressId/{officeAddressId}";
	String CREATE_OFFICE_ADDRESS						= OFFICE_ADDRESS + "/create-office-address";
	
	/** Injured Person flows */
	String INJURED_PERSON 												= SECURED + "/injured-person";
	String GET_INJURED_PERSONS											= INJURED_PERSON + "/injured-persons";
	String GET_INJURED_PERSON_BY_INJURED_PERSON_ID						= INJURED_PERSON + "/injuredPersonId/{injuredPersonId}";
	String CREATE_INJURED_PERSON										= INJURED_PERSON + "/create-injured-person";
	String CREATE_INJURED_PERSONS										= INJURED_PERSON + "/create-injured-persons";
	String UPDATE_INJURED_PERSON										= INJURED_PERSON + "/update-injured-person";
	String UPDATE_INJURED_PERSONS										= INJURED_PERSON + "/update-injured-persons";
	String REMOVE_DISTINGUISHING_FEATURE_DETAILS_FROM_INJURED_PERSON 	= INJURED_PERSON + "/remove-dist-fea-details";
	String REMOVE_BODY_PARTS_FROM_INJURED_PERSON 						= INJURED_PERSON + "/remove-body-parts";
	
	/** Building flows */
	String BUILDING 										= SECURED + "/building";
	String GET_BUILDINGS									= BUILDING + "/buildings";
	String GET_BUILDING_BY_BUILDING_ID						= BUILDING + "/buildingId/{buildingId}";
	String GET_BUILDING_BY_UNIQUE_BUILDING_ID				= BUILDING + "/uniqueBuildingId/{uniqueBuildingId}";
	String CREATE_BUILDING									= BUILDING + "/create-building";
	String CREATE_BUILDINGS									= BUILDING + "/create-buildings";
	String UPDATE_BUILDING									= BUILDING + "/update-building";
	String UPDATE_BUILDINGS									= BUILDING + "/update-buildings";
	
	/** Equipment flows */
	String EQUIPMENT 										= SECURED + "/equipment";
	String GET_EQUIPMENTS									= EQUIPMENT + "/equipments";
	String GET_EQUIPMENT_BY_EQUIPMENT_ID					= EQUIPMENT + "/equipmentId/{equipmentId}";
	String GET_EQUIPMENT_BY_UNIQUE_EQUIPMENT_ID				= EQUIPMENT + "/uniqueEquipmentId/{uniqueEquipmentId}";
	String CREATE_EQUIPMENT									= EQUIPMENT + "/create-equipment";
	String CREATE_EQUIPMENTS								= EQUIPMENT + "/create-equipments";
	String UPDATE_EQUIPMENT									= EQUIPMENT + "/update-equipment";
	String UPDATE_EQUIPMENTS								= EQUIPMENT + "/update-equipments";
	
	/** Vehicle flows */
	String VEHICLE 										= SECURED + "/vehicle";
	String GET_VEHICLES									= VEHICLE + "/vehicles";
	String GET_VEHICLE_BY_VEHICLE_ID					= VEHICLE + "/vehicleId/{vehicleId}";
	String GET_VEHICLE_BY_VEHICLE_REGISTRATION_ID		= VEHICLE + "/vehicleRegistrationId/{vehicleRegistrationId}";
	String CREATE_VEHICLE								= VEHICLE + "/create-vehicle";
	String CREATE_VEHICLES								= VEHICLE + "/create-vehicles";
	String UPDATE_VEHICLE								= VEHICLE + "/update-vehicle";
	String UPDATE_VEHICLES								= VEHICLE + "/update-vehicles";
	
	/** Asset operations */
	String ASSET											= SECURED + "/asset";
	String GET_ALL_ASSETS									= ASSET + "/assets";
	String GET_ASSET_BY_ASSET_ID							= ASSET + "/assetId/{assetId}";
	String GET_ASSET_BY_INCIDENT_ID							= ASSET + "/incidentId/{incidentId}";
	String GET_ASSET_BY_UNIQUE_INCIDENT_ID					= ASSET + "/uniqueIncidentId/{uniqueIncidentId}";
	String CREATE_ASSET										= ASSET + "/create-asset";
	String UPDATE_ASSET										= ASSET + "/update-asset";
	String ADD_OR_UPDATE_ASSET								= ASSET + "/add-or-update-asset";
	
	/** Attach building to asset */
	String ADD_BUILDING_TO_ASSET							= ASSET + "/add-building/assetId/{assetId}";
	String ADD_BUILDINGS_TO_ASSET							= ASSET + "/add-buildings";
	String ADD_EXISTING_BUILDING_TO_ASSET					= ASSET + "/add-existing-building/assetId/{assetId}/buildingId/{buildingId}";
	String ADD_EXISTING_BUILDINGS_TO_ASSET					= ASSET + "/add-existing-buildings";
	String REMOVE_BUILDING_FROM_ASSET						= ASSET + "/remove-building/assetId/{assetId}/buildingId/{buildingId}";
	String REMOVE_BUILDINGS_FROM_ASSET						= ASSET + "/remove-buildings";
	
	/** Attach equipment to asset */
	String ADD_EQUIPMENT_TO_ASSET							= ASSET + "/add-equipment/assetId/{assetId}";
	String ADD_EQUIPMENTS_TO_ASSET							= ASSET + "/add-equipments";
	String ADD_EXISTING_EQUIPMENT_TO_ASSET					= ASSET + "/add-existing-equipment/assetId/{assetId}/equipmentId/{equipmentId}";
	String ADD_EXISTING_EQUIPMENTS_TO_ASSET					= ASSET + "/add-existing-equipments";
	String REMOVE_EQUIPMENT_FROM_ASSET						= ASSET + "/remove-equipment/assetId/{assetId}/equipmentId/{equipmentId}";
	String REMOVE_EQUIPMENTS_FROM_ASSET						= ASSET + "/remove-equipments";
	
	/** Attach vehicle to asset */
	String ADD_VEHICLE_TO_ASSET								= ASSET + "/add-vehicle/assetId/{assetId}";
	String ADD_VEHICLES_TO_ASSET							= ASSET + "/add-vehicles";
	String ADD_EXISTING_VEHICLE_TO_ASSET					= ASSET + "/add-existing-vehicle/assetId/{assetId}/vehicleId/{vehicleId}";
	String ADD_EXISTING_VEHICLES_TO_ASSET					= ASSET + "/add-existing-vehicles";
	String REMOVE_VEHICLE_FROM_ASSET						= ASSET + "/remove-vehicle/assetId/{assetId}/vehicleId/{vehicleId}";
	String REMOVE_VEHICLES_FROM_ASSET						= ASSET + "/remove-vehicles";
	
	/** Crime operations */
	String CRIME											= SECURED + "/crime";
	String GET_ALL_CRIMES									= CRIME + "/crimes";
	String GET_CRIME_BY_CRIME_ID							= CRIME + "/crimeId/{crimeId}";
	String GET_CRIME_BY_INCIDENT_ID							= CRIME + "/incidentId/{incidentId}";
	String GET_CRIME_BY_UNIQUE_INCIDENT_ID					= CRIME + "/uniqueIncidentId/{uniqueIncidentId}";
	String CREATE_CRIME										= CRIME + "/create-crime";
	String UPDATE_CRIME										= CRIME + "/update-crime";
	String ADD_OR_UPDATE_CRIME								= CRIME + "/add-or-update-crime";
	
	/** adding and removing crimes suspects to a crime flows */
	String ADD_CRIME_SUSPECT_FOR_CRIME								= CRIME + "/add-crime-suspect/crimeId/{crimeId}";
	String ADD_CRIME_SUSPECTS_FOR_CRIME								= CRIME + "/add-crime-suspects";
	String ADD_EXISTING_CRIME_SUSPECT_FOR_CRIME						= CRIME + "/add-existing-crime-suspect/crimeId/{crimeId}/crimeSuspectId/{crimeSuspectId}";
	String ADD_EXISTING_CRIME_SUSPECTS_FOR_CRIME					= CRIME + "/add-existing-crime-suspects";
	String ADD_EMPLOYEE_CRIME_SUSPECT_FOR_CRIME_BY_ID				= CRIME + "/add-employee-crime-suspect/crimeId/{crimeId}/employeeId/{employeeId}";
	String ADD_EMPLOYEE_CRIME_SUSPECT_FOR_CRIME_BY_LOGIN_ID			= CRIME + "/add-employee-crime-suspect/crimeId/{crimeId}/employeeLoginId/{employeeLoginId}";
	String ADD_EMPLOYEE_CRIME_SUSPECTS_FOR_CRIME_BY_IDS				= CRIME + "/add-emp-crime-suspects-by-ids";
	String ADD_EMPLOYEE_CRIME_SUSPECTS_FOR_CRIME_BY_LOGIN_IDS		= CRIME + "/add-emp-crime-suspects-by-login-ids";
	String REMOVE_CRIME_SUSPECT_FROM_CRIME							= CRIME + "/remove-crime-suspect/crimeId/{crimeId}/crimeSuspectId/{crimeSuspectId}";
	String REMOVE_CRIME_SUSPECTS_FROM_CRIME							= CRIME + "/remove-crime-suspects";
	String REMOVE_EMPLOYEE_CRIME_SUSPECT_FROM_CRIME_BY_ID			= CRIME + "/remove-employee-crime-suspect/crimeId/{crimeId}/employeeId/{employeeId}";
	String REMOVE_EMPLOYEE_CRIME_SUSPECT_FROM_CRIME_BY_LOGIN_ID		= CRIME + "/remove-employee-crime-suspect/crimeId/{crimeId}/employeeLoginId/{employeeLoginId}";
	String REMOVE_EMPLOYEE_CRIME_SUSPECTS_FROM_CRIME_BY_IDS			= CRIME + "/remove-employee-crime-suspects-by-ids";
	String REMOVE_EMPLOYEE_CRIME_SUSPECTS_FROM_CRIME_BY_LOGIN_IDS	= CRIME + "/remove-employee-crime-suspects-by-login-ids";
	
	/** adding and removing witness to crime */
	String ADD_WITNESS_TO_CRIME									= CRIME + "/add-witness/crimeId/{crimeId}";
	String ADD_WITNESSES_TO_CRIME								= CRIME + "/add-witnesses";
	String ADD_EXISTING_WITNESS_TO_CRIME						= CRIME + "/add-existing-witness/crimeId/{crimeId}/witnessId/{witnessId}";
	String ADD_EXISTING_WITNESSES_TO_CRIME						= CRIME + "/add-existing-witnesses";
	String ADD_EMPLOYEE_WITNESS_TO_CRIME_BY_ID					= CRIME + "/add-employee-witness/crimeId/{crimeId}/employeeId/{employeeId}";
	String ADD_EMPLOYEE_WITNESS_TO_CRIME_BY_LOGIN_ID			= CRIME + "/add-employee-witness/crimeId/{crimeId}/employeeLoginId/{employeeLoginId}";
	String ADD_EMPLOYEE_WITNESSES_TO_CRIME_BY_IDS				= CRIME + "/add-employee-witnesses-by-ids";
	String ADD_EMPLOYEE_WITNESSES_TO_CRIME_BY_LOGIN_IDS			= CRIME + "/add-employee-witnesses-by-login-ids";
	String REMOVE_WITNESS_FROM_CRIME							= CRIME + "/remove-witness/crimeId/{crimeId}/witnessId/{witnessId}";
	String REMOVE_WITNESSES_FROM_CRIME							= CRIME + "/remove-witnesses";
	String REMOVE_EMPLOYEE_WITNESS_FROM_CRIME_BY_ID				= CRIME + "/remove-employee-witness/crimeId/{crimeId}/employeeId/{employeeId}";
	String REMOVE_EMPLOYEE_WITNESS_FROM_CRIME_BY_LOGIN_ID		= CRIME + "/remove-employee-witness/crimeId/{crimeId}/employeeLoginId/{employeeLoginId}";
	String REMOVE_EMPLOYEE_WITNESSES_FROM_CRIME_BY_IDS			= CRIME + "/remove-employee-witnesses-by-ids";
	String REMOVE_EMPLOYEE_WITNESSES_FROM_CRIME_BY_LOGIN_IDS	= CRIME + "/remove-employee-witnesses-by-login-ids";
	
	/** Crime Suspect operations */
	String CRIME_SUSPECT 											= SECURED + "/crime-suspect";
	String GET_ALL_CRIME_SUSPECTS									= CRIME_SUSPECT + "/crime-suspects";
	String GET_CRIME_SUSPECT_BY_CRIME_SUSPECT_ID					= CRIME_SUSPECT + "/crimeSuspectId/{crimeSuspectId}";
	String CREATE_CRIME_SUSPECT										= CRIME_SUSPECT + "/create-crime-suspect";
	String CREATE_CRIME_SUSPECTS									= CRIME_SUSPECT + "/create-crime-suspects";
	String UPDATE_CRIME_SUSPECT										= CRIME_SUSPECT + "/update-crime-suspect";
	String UPDATE_CRIME_SUSPECTS									= CRIME_SUSPECT + "/update-crime-suspects";
	String REMOVE_DISTINGUISHING_FEATURE_DETAILS_FROM_CRIME_SUSPECT = CRIME_SUSPECT + "/remove-dist-fea-details";
}
