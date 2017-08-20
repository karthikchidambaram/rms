package com.i2g.rms.rest.controller.security.stateless;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.UserContextRO;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.security.stateless.UserAuthentication;
import com.i2g.rms.rest.service.UserRestService;
import com.i2g.rms.rest.service.test.TestStatelessAuthenticationRestService;

@RestController
public class TestStatelessAuthenticationController {
	
	@Autowired
	private UserRestService _userRestService;
	@Autowired
	private TestStatelessAuthenticationRestService _testStatelessAuthenticationRestService;
	
	
	@RequestMapping(value = "/p/api/users/current", method = RequestMethod.GET)
	public User getCurrent() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication instanceof UserAuthentication) {
			return ((UserAuthentication) authentication).getDetails();
		}
		return new User(authentication.getName()); //anonymous user support
	}
	
	@RequestMapping(value = "/s/admin/api/users", method = RequestMethod.GET)
	public List<UserRO> getUsers() {
		return _userRestService.getUsers();
	}
	
	@RequestMapping(value = "/p/api/login", method = RequestMethod.POST)
	public UserContextRO doLogin(final HttpServletRequest request, final HttpServletResponse response) {
		return _testStatelessAuthenticationRestService.doLogin(request, response);
	}
	
	@RequestMapping(value = RequestMappingConstants.LOGIN_ACTION_STATELESS, method = RequestMethod.POST)
	public String doLoginStateless() {
		return "To do..";
	}
}
