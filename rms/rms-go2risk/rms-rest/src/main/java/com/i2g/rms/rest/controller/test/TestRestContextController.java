package com.i2g.rms.rest.controller.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.test.TestContextParamsRO;
import com.i2g.rms.rest.service.test.TestRestContextService;

/**
 * Rest controller for testing if the context information like
 * HttpServletRequest, HttpServletResponse, HttpSession object is getting
 * injected by spring.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class TestRestContextController extends TestAbstractRestController {
	
	@Autowired
	private TestRestContextService _testRestContextService;
	
	@RequestMapping(value = RequestMappingConstants.TEST_CONTEXT_PARAMS, method = RequestMethod.GET)
	public TestContextParamsRO testContextParams(final HttpServletRequest request, final HttpServletResponse response) {
		return _testRestContextService.testContextParams(request, response);
	}
}
