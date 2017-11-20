package com.i2g.rms.batch.exec.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldExec {
	
	public static void main(String[] args) throws Exception {
		String springConfig = "spring-quartz.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
	}
}
