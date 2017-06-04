package com.i2g.rms.rest.constants;

public interface RequestMappingConstants {
	
	/** URL pattern for protecting rest end points, this requires authentication.*/
	String SECURED 	= "/s";
	/** Public URL base pattern */
	String PUBLIC 	= "/p";
	
	/** Login */
	String LOGIN 					= PUBLIC + "/login";
	String SECURED_LOGIN			= SECURED + "/login";
	
	String LOGIN_ACTION 			= LOGIN + "/do-login";
	String LOGIN_ACTION_FROM_HTML 	= LOGIN + "/do-login-html";
	String LOGIN_ACTION_BASIC 		= LOGIN + "/do-login-basic";
	String LOGIN_STATUS_CHECK 		= LOGIN + "/check-login-status/{loginId}";
	String LOGOUT_ACTION 			= LOGIN + "/do-logout/{loginId}";
	String GET_ENCRYPTED_PASSWORD	= LOGIN + "/get-encrypted-password";
	String LOGIN_ACTION_STATELESS	= LOGIN + "/do-login-stateless";
	
	
	/** Group */
	String GROUP 					= SECURED + "/group";
	String GET_ALL_GROUPS 			= GROUP + "/groups";
	
	/** Password History */
	String PASSWORD_HISTORY 			= SECURED + "/password-history";
	String GET_ALL_PASSWORD_HISTORIES 	= PASSWORD_HISTORY + "/password-histories";
	
	/** Permission */
	String PERMISSION 			= SECURED + "/permission";
	String GET_ALL_PERMISSIONS 	= PERMISSION + "/permissions";
	
	/** Role */
	String ROLE 			= SECURED + "/role";
	String GET_ALL_ROLES 	= ROLE + "/roles";
	
	/** User */
	String USER 			= SECURED + "/user";
	String GET_ALL_USERS 	= USER + "/users";
	
	/** User Details */
	String USER_DETAILS 			= SECURED + "/user-detail";
	String GET_ALL_USER_DETAILS 	= USER_DETAILS + "/user-details";
	
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
	
	/** Entry Point Request Mappings (Testing purpose URLs) */
	String TEST_GET_ENTRY_POINTS 			= TABLE_MAINTENANCE + "/test-entry-point/entry-points";
	String TEST_GET_ENTRY_POINT_BY_CODE 	= TABLE_MAINTENANCE + "/test-entry-point/{code}";
	String TEST_CREATE_ENTRY_POINT 			= TABLE_MAINTENANCE + "/test-entry-point/create-entry-point";
	String TEST_UPDATE_ENTRY_POINT 			= TABLE_MAINTENANCE + "/test-entry-point/update-entry-point";
	String TEST_DELETE_ENTRY_POINT 			= TABLE_MAINTENANCE + "/test-entry-point/delete-entry-point/{code}";
	
	/** Entry Point Request Mappings (Generic Approach) */
	String GET_ENTRY_POINTS			= TABLE_MAINTENANCE + "/entry-point/get/{tableName}/{operation}";
	String GET_ENTRY_POINT_BY_CODE 	= TABLE_MAINTENANCE + "/entry-point/getByCode/{tableName}/{operation}/{code}";
	String CREATE_ENTRY_POINT 		= TABLE_MAINTENANCE + "/entry-point/create";
	String UPDATE_ENTRY_POINT 		= TABLE_MAINTENANCE + "/entry-point/update";
	String DELETE_ENTRY_POINT 		= TABLE_MAINTENANCE + "/entry-point/delete";
	
	/** Generic approach for all table maintenance operations */
	String GET_TABLE_MAINTENANCE_DATA 			= TABLE_MAINTENANCE + "/get/{tableName}/{operation}";
	String GET_TABLE_MAINTENANCE_DATA_BY_CODE 	= TABLE_MAINTENANCE + "/getByCode/{tableName}/{operation}/{code}";
	String CREATE_TABLE_MAINTENANCE_DATA 		= TABLE_MAINTENANCE + "/create/{tableName}/{operation}";
	String UPDATE_TABLE_MAINTENANCE_DATA 		= TABLE_MAINTENANCE + "/update/{tableName}/{operation}";
	String DELETE_TABLE_MAINTENANCE_DATA 		= TABLE_MAINTENANCE + "/delete/{tableName}/{operation}";
}
