package com.i2g.rms.rest.model;

import java.time.LocalDateTime;

/**
 * Abstract class for entity-based REST Objects for defining the common audit
 * attributes shared across projections.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public interface AbstractEntityProjection {
	
	String getCreatedBy();

	LocalDateTime getCreated();

	String getLastUpdatedBy();

	LocalDateTime getLastUpdated();
	
}
