package com.i2g.rms.util;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationContextListener implements ServletContextListener {
	
	/** {@code Logger} instance. */
	private final static Logger _logger = LoggerFactory.getLogger(ApplicationContextListener.class);
	
	
	/**
	 * Initializes the application context and logs application start.
	 * 
	 * <p>{@inheritDoc}
	 */
	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		LoggingContext.setSystemId();	// Set System as context ID
		try {
			// Message is logged as an 'error' as a quick hack to send email
			_logger.error("Application instance started|Version:{}|Revision:{}", 
					RuntimeInfo.getBuildVersion(), RuntimeInfo.getBuildRevision());
		} finally {
			LoggingContext.clear();		// Always clear logging context
		}
	}
	
	/**
	 * Shuts down the application context and logs application shutdown.
	 * 
	 * <p>{@inheritDoc}
	 */
	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		LoggingContext.setSystemId();	// Set System as context ID
		try {
			// Message is logged as an 'error' as a quick hack to send email
			_logger.error("Application instance stopped|Version:{}|Revision:{}|Uptime:{} seconds", 
					RuntimeInfo.getBuildVersion(), RuntimeInfo.getBuildRevision(),
					TimeUnit.MILLISECONDS.toSeconds(ManagementFactory.getRuntimeMXBean().getUptime()));
		} finally {
			LoggingContext.clear();		// Always clear logging context
		}
	}
	
}
