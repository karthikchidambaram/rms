package com.i2g.rms.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

/**
 * Abstract base class for data models which provides the default standard audit
 * columns for created by, created timestamp, updated by and updated timestamp.
 * Domain objects persisted to the rms database which have these four columns
 * should extend this base class. The following columns are provided:
 * <ul>
 * <li>{@code CRET_LGN_UID} - Created by (user or process ID)</li>
 * <li>{@code CRET_DTM} - Created timestamp</li>
 * <li>{@code LST_UPDT_LGN_UID} - Updated by (user or process ID)</li>
 * <li>{@code LST_UPDT_DTM} - Updated timestamp</li>
 * </ul>
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author rms Development Team
 * 
 * @param <K>
 *            type of {@link Serializable} primary key for entity
 */
@MappedSuperclass
public abstract class AbstractDataModel<K extends Serializable> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** User ID which created record (for auditing). */
	private String _createdBy;
	/** Timestamp when record was created. */
	private LocalDateTime _created;
	/** User ID which last updated record (for auditing). */
	private String _lastUpdatedBy;
	/**
	 * Timestamp when record was last updated (may be null if never updated).
	 */
	private LocalDateTime _lastUpdated;

	/**
	 * Returns the primary key ID for the entity.
	 * 
	 * @return primary key ID
	 */
	@Transient
	public abstract K getId();

	/**
	 * Returns the user or process which created this record.
	 * 
	 * @return user or process which created record
	 */
	@Column(name = "CRET_LGN_UID", length = 32, nullable = false, updatable = false)
	public String getCreatedBy() {
		return _createdBy;
	}

	/**
	 * Sets the user or process which created the record.
	 * 
	 * <p>
	 * <strong>Note:</strong> Please note this method must remain protected as
	 * it should never be directly invoked; by design, it's only to used by
	 * Hibernate when setting the property. Any user set value will be replaced
	 * by the {@code AuditInterceptor}.
	 * </p>
	 * 
	 * @param createdBy
	 *            (user or process ID)
	 */
	public void setCreatedBy(final String createdBy) {
		_createdBy = createdBy;
	}

	/**
	 * Returns the timestamp when the record was created.
	 * 
	 * @return timestamp when record was created
	 */
	@Column(name = "CRET_DTM", length = 11, nullable = false, updatable = false)
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateTimeType")
	public LocalDateTime getCreated() {
		return _created;
	}

	/**
	 * Sets the timestamp when the record was {@code created}.
	 *
	 * <p>
	 * <strong>Note:</strong> Please note this method must remain protected as
	 * it should never be directly invoked; by design, it's only to used by
	 * Hibernate when setting the property. Any user set value will be replaced
	 * by the {@code AuditInterceptor}.
	 * </p>
	 * 
	 * @param created
	 */
	public void setCreated(final LocalDateTime created) {
		_created = created;
	}

	/**
	 * Returns the user or process which last updated record.
	 * 
	 * @return user or process which last updated record
	 */
	@Column(name = "LST_UPDT_LGN_UID", length = 32)
	public String getLastUpdatedBy() {
		return _lastUpdatedBy;
	}

	/**
	 * Sets the user or process which last updated record.
	 * 
	 * <p>
	 * <strong>Note:</strong> Please note this method must remain protected as
	 * it should never be directly invoked; by design, it's only to used by
	 * Hibernate when setting the property. Any user set value will be replaced
	 * by the {@code AuditInterceptor}.
	 * </p>
	 * 
	 * @param lastUpdatedBy
	 */
	public void setLastUpdatedBy(final String lastUpdatedBy) {
		_lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * Returns the timestamp when the record was last updated.
	 * 
	 * @return timestamp when record was last updated
	 */
	@Column(name = "LST_UPDT_DTM", length = 11)
	@Type(type = "com.i2g.rms.domain.model.type.LocalDateTimeType")
	public LocalDateTime getLastUpdated() {
		return _lastUpdated;
	}

	/**
	 * Sets the timestamp when the record was {@code lastUpdated}.
	 * 
	 * <p>
	 * <strong>Note:</strong> Please note this method must remain protected as
	 * it should never be directly invoked; by design, it's only to used by
	 * Hibernate when setting the property. Any user set value will be replaced
	 * by the {@code AuditInterceptor}.
	 * </p>
	 * 
	 * @param lastUpdated
	 */
	public void setLastUpdated(final LocalDateTime lastUpdated) {
		_lastUpdated = lastUpdated;
	}
}
