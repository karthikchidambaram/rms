package com.i2g.rms.batch.task.test;

import java.util.Calendar;

public class HelloWorldTask {

	public void print() {
		System.out.println("Hello World! The current time is: " + Calendar.getInstance().getTime());
	}
}
