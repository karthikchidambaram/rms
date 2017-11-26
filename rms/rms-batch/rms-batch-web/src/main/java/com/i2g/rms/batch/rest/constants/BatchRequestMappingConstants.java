package com.i2g.rms.batch.rest.constants;

public interface BatchRequestMappingConstants {
	
	/** URL pattern for protecting rest end points, this requires authentication.*/
	String SECURED 	= "/s";
	/** Public URL base pattern */
	String PUBLIC 	= "/p";
	
	/** Test jobs */
	
	/** Hello world batch job request mapping constants */
	String START_HELLO_WORLD_BATCH_JOB = PUBLIC + "/test/start-hello-world-batch-job";
}
