package com.i2g.rms.batch.exec.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldExecutorServiceImpl implements HelloWorldExecutorService {

	@Override
	public String startHelloWorldBatchJob() {
		String springConfig = "spring-quartz.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		return "Hello world batch job has been started successfully...";
	}
}
