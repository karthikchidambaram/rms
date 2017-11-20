package com.i2g.rms.batch.rest.services.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.batch.exec.test.HelloWorldExecutorService;

@Service
public class HelloWorldRestServiceImpl extends AbstractBatchTestRestService implements HelloWorldRestService {
	
	@Autowired
	HelloWorldExecutorService _helloWorldExecutorService;

	@Override
	public String startHelloWorldBatchJob() {
		String result = _helloWorldExecutorService.startHelloWorldBatchJob();
		if (result != null) {
			return result;
		} else {
			return null;
		}
	}
}
