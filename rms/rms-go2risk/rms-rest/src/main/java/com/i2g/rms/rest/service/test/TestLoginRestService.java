package com.i2g.rms.rest.service.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;

import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.test.LoginRO;

/**
 * Rest Service Interface for group rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TestLoginRestService {

	public ResponseEntity<UserRO> doLogin(final LoginRO loginRO, HttpSession session);
	
	public String checkLoginStatus(final String loginId, HttpServletRequest request, HttpServletResponse response);
	
	public void doLogout(final String loginId, HttpServletRequest request, HttpServletResponse response);
	
	public void doLogin(final String loginId, final String password, HttpServletRequest request, HttpServletResponse response);
	
	public ResponseEntity<UserRO> doLogin(HttpServletRequest request, HttpServletResponse response);
	
	public String doLoginHtml(HttpServletRequest request, HttpServletResponse response);

}
