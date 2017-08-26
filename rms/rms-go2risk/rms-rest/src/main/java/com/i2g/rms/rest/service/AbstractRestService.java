package com.i2g.rms.rest.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.rest.constants.RequestConstants;
import com.i2g.rms.rest.mapping.MapperService;
import com.i2g.rms.rest.security.stateless.UserAuthentication;
import com.i2g.rms.service.exception.ResourceNotFoundException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.message.MessageBuilder;

/**
 * Base class for all rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public abstract class AbstractRestService {

	/** Service for mapping between entities and REST objects. */
	@Autowired
	protected MapperService _mapperService;

	/** Service for generating messages. */
	@Autowired
	protected MessageBuilder _messageBuilder;

	/**
	 * Fetch the authenticated username from context.
	 * 
	 * @return String
	 */
	protected String getPrincipalFromContext() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	protected String getUsernameFromContext() {
		String username = RequestConstants.ANONYMOUS_USER;
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			if (auth instanceof UserAuthentication) {					
				final User user = (User) auth.getDetails();
				if (user != null) {
					if ((user.getUsername() != null) && !(user.getUsername().isEmpty()) && !(user.getUsername().equalsIgnoreCase("anonymousUser"))) {
						// Set username in auditing contexts
						username = user.getUsername();
					}
				}
			} else if (auth.getPrincipal() instanceof User) {
				final User user = (User) auth.getPrincipal();
				if (user != null) {
					if ((user.getUsername() != null) && !(user.getUsername().isEmpty()) && !(user.getUsername().equalsIgnoreCase("anonymousUser"))) {
						// Set username in auditing contexts
						username = user.getUsername();
					}
				}
			}
		}
		return username;	
	}
	
	protected User getUserFromContext() {
		User user = null;
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			if (auth instanceof UserAuthentication) {					
				user = (User) auth.getDetails();				
			} else if (auth.getPrincipal() instanceof User) {
				user = (User) auth.getPrincipal();				
			}
		}
		return user;	
	}

	/**
	 * Get info about currently logged in user
	 * 
	 * @return UserDetails if found in the context, null otherwise
	 */
	protected UserDetails getUserDetailsFromContext() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		return userDetails;
	}

	/**
	 * Check if a role is present in the authorities of current user
	 * 
	 * @param authorities
	 *            all authorities assigned to current user
	 * @param role
	 *            required authority
	 * @return true if role is present in list of authorities assigned to
	 *         current user, false otherwise
	 */
	protected boolean isRolePresentFromContext(Collection<? extends GrantedAuthority> authorities, String role) {
		boolean isRolePresent = false;
		for (GrantedAuthority grantedAuthority : authorities) {
			isRolePresent = grantedAuthority.getAuthority().equals(role);
			if (isRolePresent) {
				break;
			}
		}
		return isRolePresent;
	}

	/**
	 * Method to check if the authenticated user has the given role.
	 * 
	 * @param role
	 * @return true if the role is present, else return false.
	 */
	protected final boolean hasRoleFromContext(String role) {
		boolean hasRole = false;
		UserDetails userDetails = getUserDetailsFromContext();
		if (userDetails != null) {
			Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
			if (isRolePresentFromContext(authorities, role)) {
				hasRole = true;
			}
		}
		return hasRole;
	}

	/**
	 * Get all the role names associated to the authenticated user from context.
	 * 
	 * @return roles
	 */
	protected List<String> getUserRolesFromContext() {
		List<String> roles = new ArrayList<>();
		UserDetails userDetails = getUserDetailsFromContext();
		if (userDetails != null) {
			Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				roles.add(grantedAuthority.getAuthority());
			}
		}
		return roles;
	}

	/**
	 * Validation method for table maintenance objects. Validates {@code object}
	 * for null or empty.
	 * 
	 * @param object
	 * @throws ResourceNotValidException
	 *             if the input object is null or empty.
	 */
	protected void validateObject(final Object object) {
		if (object == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.INPUT_OBJECT_NULL_OR_EMPTY));
		}
	}
	
	/**
	 * Generic method which validates object for null or empty.
	 *  
	 * @param object
	 * @throws ResourceNotValidException
	 *             if the object is null or empty.
	 */
	protected void validateGenericObject(final Object object) {
		if (object == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.OBJECT_NULL_OR_EMPTY));
		}
	}
	
	/**
	 * Validation method for table maintenance objects. Validates {@code object}
	 * for null or empty.
	 * 
	 * @param object
	 * @throws ResourceNotValidException
	 *             if the input object is null or emtpy.
	 */
	protected void validateParentObject(final Object object) {
		if (object == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.PARENT_OBJECT_NULL_OR_EMPTY));
		}
	}

	/**
	 * Validation method for table maintenance objects. Validates {@code code}
	 * for null or empty.
	 * 
	 * @param code
	 * @throws ResourceNotValidException
	 *             if the input object is null or emtpy.
	 */
	protected void validateCode(final String code) {
		if (code == null || code.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.CODE_NULL_OR_EMPTY));
		}
	}
	
	/**
	 * Validation method for table maintenance objects. Validates {@code code}
	 * for null or empty.
	 * 
	 * @param code
	 * @throws ResourceNotValidException
	 *             if the input object is null or emtpy.
	 */
	protected void validateParentCode(final String code) {
		if (code == null || code.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.PARENT_CODE_NULL_OR_EMPTY));
		}
	}

	/**
	 * Validation method for table maintenance objects. Validates
	 * {@code description} for null or empty.
	 * 
	 * @param description
	 * @throws ResourceNotValidException
	 *             if the input object is null or emtpy.
	 */
	protected void validateDescription(final String description) {
		if (description == null || description.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.DESCRIPTION_NULL_OR_EMPTY));
		}
	}

	protected void validateTableName(final String tableName) {
		if (tableName == null || tableName.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.TABLE_NAME_NULL_OR_EMPTY));
		}
	}

	protected void validateOperation(final String operation) {
		if (operation == null || operation.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.OPERATION_NULL_OR_EMPTY));
		}
	}

	protected void validateObjectArray(final List<Object[]> objects) {
		if (objects == null || objects.isEmpty()) {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.OBJECT_ARRAY_NULL_OR_EMPTY));
		}
	}

	protected void validateUsername(final String username) {
		if (username == null || username.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.USER_LOGIN_ID_NULL_OR_EMPTY));
		}
	}

	protected void validatePassword(final String password) {
		if (password == null || password.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.PASSWORD_NULL_OR_EMPTY));
		}
	}

	protected void validateRequest(final HttpServletRequest request) {
		// Check if the request is valid
		if (request == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.REQUEST_OBJECT_NULL_OR_EMPTY));
		}
	}

	protected void validateCredentials(final String[] credentials) {
		if (credentials == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.CREDENTIALS_NOT_PRESENT_IN_REQUEST));
		}
	}
	
	protected void validateUniqueIncidentId(final String uniqueIncidentId) {
		if (uniqueIncidentId == null || uniqueIncidentId.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.UNIQUE_INCIDENT_ID_NULL_OR_EMPTY));
		}
	}
	
	/**
	 * Validation method for User object. Validates {@code user}
	 * for null or empty.
	 * 
	 * @param user
	 * @throws ResourceNotValidException
	 *             if the user object is null or empty.
	 */
	protected void validateUserObject(final User user) {
		if (user == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.UNABLE_TO_FETCH_USER_DETAILS));
		}
	}
}
