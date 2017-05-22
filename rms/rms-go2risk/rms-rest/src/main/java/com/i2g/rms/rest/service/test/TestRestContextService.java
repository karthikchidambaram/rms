package com.i2g.rms.rest.service.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.i2g.rms.rest.model.test.TestContextParamsRO;

/**
 * Interface for testing context params.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TestRestContextService {
	
	public TestContextParamsRO testContextParams(HttpServletRequest request, HttpServletResponse response);
	
}
