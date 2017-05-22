package com.i2g.rms.rest.service.test;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.test.LoginRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.rest.service.RestMessage;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.test.TestLoginService;
import com.i2g.rms.util.security.RMSPasswordHashingService;
import com.i2g.rms.util.security.RMSSecurityProperties;

/**
 * Rest services for group rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class TestLoginRestServiceImpl extends AbstractRestService implements TestLoginRestService {

	private final Logger _logger = LoggerFactory.getLogger(TestLoginRestServiceImpl.class);
	
	@Autowired
	TestLoginService _loginService;
	@Autowired
	private RMSPasswordHashingService _rMSPasswordHashingService;

	/**
	 * Method to authenticate user with the supplied credentials.
	 * 
	 * @param loginRO
	 * @return 
	 */
	@Override
	@Transactional
	public ResponseEntity<UserRO> doLogin(final LoginRO loginRO, HttpSession session) {
		HttpStatus httpStatus = null;
		UserRO userRO = null;
		// Validate input params
		validateLoginInputParams(loginRO);		
		
		final String loginId = loginRO.getLoginId();
		final String password = loginRO.getPassword();
		final String saltedPassword = _rMSPasswordHashingService.getSaltedPassword(password);
		final String hashedPassword = _rMSPasswordHashingService.getHashedPassword(saltedPassword);
		
		User user = _loginService.doLogin(loginId, hashedPassword);
		
		if (user != null) {
			if (user.getLoginId() != null && !user.getLoginId().isEmpty() && user.getLoginId().equals(loginId)) {
				userRO = _mapperService.map(user, UserRO.class);
				if (userRO != null && !userRO.getLoginId().isEmpty() && userRO.getLoginId().equals(loginId)) {
					session.setAttribute(RMSSecurityProperties.RMS_USER_CONTEXT, userRO);
					httpStatus = HttpStatus.OK;
					_logger.info("Authentication successful! User context is set.");
				}
			}
		} else {
			httpStatus = HttpStatus.UNAUTHORIZED;
			_logger.info("Authentication failed! Invalid user login id and/or password.");			
		}
		return new ResponseEntity<>(userRO, httpStatus);
	}
	
	@Override
	public String checkLoginStatus(final String loginId, HttpServletRequest request, HttpServletResponse response) {
		String returnString = "User is not authenticated.";
		//Validate input params
		validateLoginId(loginId);
		//Get the existing session object
		HttpSession session = request.getSession();
		final UserRO userRO = (UserRO) session.getAttribute(RMSSecurityProperties.RMS_USER_CONTEXT);
		if (userRO != null) {
			if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty() && userRO.getLoginId().equals(loginId)) {
				returnString = "User is already logged in. Session object present.";
				_logger.info("User is already logged in. Session object present.");
			}
		}
		return returnString;
	}
	
	/**
	 * Method which performs logout operation.
	 * 
	 * @param loginId
	 */
	@Override
	public void doLogout(final String loginId, HttpServletRequest request, HttpServletResponse response) {
		//Validate input params
		validateLoginId(loginId);
		//Get the existing session object
		HttpSession session = request.getSession();
		final UserRO userRO = (UserRO) session.getAttribute(RMSSecurityProperties.RMS_USER_CONTEXT);
		
		if (userRO != null) {
			if (userRO.getLoginId() != null && !userRO.getLoginId().trim().isEmpty() && userRO.getLoginId().equals(loginId)) {
				session.invalidate();
				_logger.info("Logout successful. Session invalidated.");
			}
		} else {
			_logger.info("Logout failed. Session object does not contain user context.");
		}
	}
	
	@Override
	@Transactional
	public void doLogin(final String loginId, final String password, HttpServletRequest request, HttpServletResponse response) {
		UserRO userRO = null;
		//validate input parameters
		validateLoginId(loginId);
		validatePassword(password);
		final String saltedPassword = _rMSPasswordHashingService.getSaltedPassword(password);
		final String hashedPassword = _rMSPasswordHashingService.getHashedPassword(saltedPassword);
		User user = _loginService.doLogin(loginId, hashedPassword);
		
		if (user != null) {
			if (user.getLoginId() != null && !user.getLoginId().isEmpty() && user.getLoginId().equals(loginId)) {
				userRO = _mapperService.map(user, UserRO.class);
				if (userRO != null && !userRO.getLoginId().isEmpty() && userRO.getLoginId().equals(loginId)) {
					request.getSession().setAttribute(RMSSecurityProperties.RMS_USER_CONTEXT, userRO);
					_logger.info("Authentication successful! User context is set.");
				}
			}
		} else {
			_logger.info("Authentication failed! Invalid user login id and/or password.");			
		}		
	}
	
	@Override
	@Transactional
	public ResponseEntity<UserRO> doLogin(HttpServletRequest request, HttpServletResponse response) {
		_logger.info("Inside perform login using header parameters.");
		UserRO userRO = null;
		HttpStatus httpStatus = null;
		// Parse the credentials from the request
		final String[] credentials = getCredentials(request);
		final String loginId = credentials[0];
		final String password = credentials[1];
		//validate input parameters
		validateLoginId(loginId);
		validatePassword(password);
		final String saltedPassword = _rMSPasswordHashingService.getSaltedPassword(password);
		final String hashedPassword = _rMSPasswordHashingService.getHashedPassword(saltedPassword);
		User user = _loginService.doLogin(loginId, hashedPassword);
		
		if (user != null) {
			if (user.getLoginId() != null && !user.getLoginId().isEmpty() && user.getLoginId().equals(loginId)) {
				userRO = _mapperService.map(user, UserRO.class);
				if (userRO != null && !userRO.getLoginId().isEmpty() && userRO.getLoginId().equals(loginId)) {
					request.getSession().setAttribute(RMSSecurityProperties.RMS_USER_CONTEXT, userRO);
					httpStatus = HttpStatus.OK;
					_logger.info("Authentication successful! User context is set.");
				}
			}
		} else {
			httpStatus = HttpStatus.UNAUTHORIZED;
			_logger.info("Authentication failed! Invalid user login id and/or password.");			
		}
		return new ResponseEntity<>(userRO, httpStatus);
	}
	
	@Override
	@Transactional
	public String doLoginHtml(HttpServletRequest request, HttpServletResponse response) {
		_logger.info("Inside perform login from Html form submit.");
		String returnString = "Authentication failed! Invalid user login id and/or password.";
		UserRO userRO = null;
		// Parse the credentials from the request
		final String loginId = request.getParameter("txtUserLoginId");
		final String password = request.getParameter("txtUserPassword");
		//validate input parameters
		validateLoginId(loginId);
		validatePassword(password);
		final String saltedPassword = _rMSPasswordHashingService.getSaltedPassword(password);
		final String hashedPassword = _rMSPasswordHashingService.getHashedPassword(saltedPassword);
		User user = _loginService.doLogin(loginId, hashedPassword);
		
		if (user != null) {
			if (user.getLoginId() != null && !user.getLoginId().isEmpty() && user.getLoginId().equals(loginId)) {
				userRO = _mapperService.map(user, UserRO.class);
				if (userRO != null && !userRO.getLoginId().isEmpty() && userRO.getLoginId().equals(loginId)) {
					request.getSession().setAttribute(RMSSecurityProperties.RMS_USER_CONTEXT, userRO);
					returnString = "Authentication successful! User context is set.";
					_logger.info("Authentication successful! User context is set.");
				}
			}
		} else {
			_logger.info("Authentication failed! Invalid user login id and/or password.");			
		}
		return returnString;
	}
	
	private void validateLoginId(final String loginId) {
		if (loginId == null || loginId.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.USER_LOGIN_ID_NULL_OR_EMPTY));
		}
	}
	
	private void validatePassword(final String password) {
		if (password == null || password.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.PASSWORD_NULL_OR_EMPTY));
		}
	}

	private void validateLoginInputParams(final LoginRO loginRO) {
		if (loginRO == null) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.LOGIN_REQ_BODY_NULL_OR_EMPTY));
		}
		
		if (loginRO.getLoginId() == null || loginRO.getLoginId().trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.USER_LOGIN_ID_NULL_OR_EMPTY));
		}
		
		if (loginRO.getPassword() == null || loginRO.getPassword().trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.PASSWORD_NULL_OR_EMPTY));
		}
	}
	
	/**
	 * Returns the username/password from the specified {@code request}.  The 
	 * method first attempts to pull credentials from a standard Basic
	 * Authentication header, then falls back to simple header fields.
	 * 
	 * @param request (non-null)
	 * @return array of username and password
	 */
	private String[] getCredentials(final HttpServletRequest request) {
		// First attempt to pull out Basic Authentication credentials
		final String basicAuthHeader = request.getHeader("Authorization");
		if( basicAuthHeader != null && basicAuthHeader.startsWith("Basic ") ) {
			return getBasicAuthHeader(basicAuthHeader);
		}
		
		// Otherwise fall back to explicit username/password headers
		final String username = request.getHeader("username");
		final String password = request.getHeader("password");
		return new String[]{username, password};
	}
	
	/**
	 * Decodes the specified Basic Authentication {@code header} into a username 
	 * and password.
	 * 
	 * @return username/password or {@code null} if not specified
	 * @throws BadCredentialsException if the Basic header is invalid
	 */
	private String[] getBasicAuthHeader(final String header) {
		// Base64 decode the username/password portion of header
		final byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
		final byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		} catch( IllegalArgumentException e ) {
			throw new BadCredentialsException("Failed to decode basic authentication token", e);
		}
		
		// Parse the username/password from the decoded token
		final String token = new String(decoded, StandardCharsets.UTF_8);
		final int delim = token.indexOf(":");
		if( delim == -1 ) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[]{token.substring(0, delim), token.substring(delim + 1)};
	}
}
