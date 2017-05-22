package com.i2g.rms.rest.service.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.i2g.rms.rest.model.test.TestContextParamsRO;
import com.i2g.rms.rest.service.AbstractRestService;

/**
 * Rest services for testing context params.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class TestRestContextServiceImpl extends AbstractRestService implements TestRestContextService {

	private final Logger _logger = LoggerFactory.getLogger(TestRestContextServiceImpl.class);

	@Override
	public TestContextParamsRO testContextParams(HttpServletRequest request, HttpServletResponse response) {

		_logger.info("Inside test context params service...");
		TestContextParamsRO testContextParamsRO = new TestContextParamsRO();

		if (request != null) {
			testContextParamsRO.setRequest("Request object successfully injected.");
			HttpSession session = request.getSession(true);
			
			if (session != null) {
				testContextParamsRO.setSession("Session object successfully created.");
			} else {
				testContextParamsRO.setSession("Session object cannot be created from request.");
			}
		} else {
			testContextParamsRO.setRequest("Request object is null.");
		}

		if (response != null) {
			testContextParamsRO.setResponse("Response object successfully injected.");
		} else {
			testContextParamsRO.setResponse("Response object is null.");
		}
		
		return testContextParamsRO;
	}

}
