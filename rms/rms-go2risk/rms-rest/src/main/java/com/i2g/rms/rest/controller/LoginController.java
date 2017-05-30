package com.i2g.rms.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.service.LoginRestService;

/**
 * Rest controller for all login related activities.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class LoginController extends AbstractRestController {
	
	@Autowired
	private LoginRestService _loginRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ENCRYPTED_PASSWORD, method = RequestMethod.POST)
	public String getEncryptedPassword(final HttpServletRequest request, final HttpServletResponse response) {
		return _loginRestService.getEncryptedPassword(request, response);
	}
}
