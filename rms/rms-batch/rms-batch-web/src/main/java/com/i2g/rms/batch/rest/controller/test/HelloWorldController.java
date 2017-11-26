package com.i2g.rms.batch.rest.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.batch.rest.constants.BatchRequestMappingConstants;
import com.i2g.rms.batch.rest.services.test.HelloWorldRestService;

@RestController
public class HelloWorldController extends AbstractBatchTestRestController {
	
	@Autowired
	HelloWorldRestService _helloWorldRestService;

	@RequestMapping(value = BatchRequestMappingConstants.START_HELLO_WORLD_BATCH_JOB, method = RequestMethod.GET)
	public String startHelloWorldBatchJob() {
		return _helloWorldRestService.startHelloWorldBatchJob();
	}
}