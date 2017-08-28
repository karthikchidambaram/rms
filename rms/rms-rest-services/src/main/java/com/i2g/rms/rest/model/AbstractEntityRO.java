package com.i2g.rms.rest.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Base RO Object for RMS Application - Presentation Layer.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractEntityRO {
	
	/**
	 * User/System ID which created the entity.
	 */
	private String _createdBy;
	/**
	 * Timestamp when the entity was created.
	 */
	private LocalDateTime _created;
	/**
	 * User/System ID which last updated the entity.
	 */
	private String _lastUpdatedBy;
	/**
	 * Timestamp when the entity was last updated.
	 */
	private LocalDateTime _lastUpdated;
	

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(final String createdBy) {
		_createdBy = createdBy;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public LocalDateTime getCreated() {
		return _created;
	}

	public void setCreated(final LocalDateTime created) {
		_created = created;
	}

	public String getLastUpdatedBy() {
		return _lastUpdatedBy;
	}

	public void setLastUpdatedBy(final String lastUpdatedBy) {
		_lastUpdatedBy = lastUpdatedBy;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public LocalDateTime getLastUpdated() {
		return _lastUpdated;
	}

	public void setLastUpdated(final LocalDateTime lastUpdated) {
		_lastUpdated = lastUpdated;
	}
}
