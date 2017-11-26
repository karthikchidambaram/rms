package com.i2g.rms.batch.services.job.test;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.i2g.rms.batch.services.task.test.HelloWorldTask;

public class HelloWorldJob extends QuartzJobBean {

		private HelloWorldTask _helloWorldTask;

		public HelloWorldTask getHelloWorldTask() {
			return _helloWorldTask;
		}

		public void setHelloWorldTask(final HelloWorldTask helloWorldTask) {
			_helloWorldTask = helloWorldTask;
		}

		@Override
		protected void executeInternal(final JobExecutionContext arg0) throws JobExecutionException {
			_helloWorldTask.print();
		}
}
