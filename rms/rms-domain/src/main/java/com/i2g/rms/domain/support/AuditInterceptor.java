package com.i2g.rms.domain.support;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.i2g.rms.util.RuntimeInfo;

/**
 * Implementation of Hibernate's {@link org.hibernate.Interceptor} used by
 * Hibernate {@code Session} to add auditing information (user/process name,
 * created/updated timestamp, server name, etc) when inserting and/or updating
 * entities. Auditing information is added to any entity object which extends
 * from {@link com.i2g.rms.domain.model.AbstractDataModel<K>}, which provides the
 * common audit properties. The user or process name is retrieved from
 * {@link Auditor#getName()} and is set to the associated audit properties.
 * 
 * 
 * @see com.i2g.rms.domain.model.AbstractDataModel<K>
 * @see com.i2g.rms.domain.support.Auditor
 * @see org.hibernate.Interceptor
 * 
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class AuditInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** {@code Logger} instance. */
	private final Logger _logger = LoggerFactory.getLogger(AuditInterceptor.class);

	/*
	 * Sets the auditing properties for a newly created entity by populating the
	 * created by and created timestamp fields and setting the server name if
	 * the entity supports the property.
	 */
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		_logger.trace("On save for entity: {}", entity);
		for (int i = 0; i < propertyNames.length; i++) {
			switch (propertyNames[i]) {
			case "createdUserId":
			case "createdBy":
			case "lastUpdatedUserId":
			case "lastUpdatedBy":
				state[i] = Auditor.getName();
				break;
			case "createdDateTime":
			case "lastUpdatedDateTime":
				state[i] = new Timestamp(System.currentTimeMillis());
				break;
			case "created":
			case "lastUpdated":
				state[i] = LocalDateTime.now();
				break;
			case "serverName":
				state[i] = RuntimeInfo.getServerName();
				break;
			default: // Do nothing for other fields
			}
		}
		return true; // Entity has been updated
	}

	/*
	 * Sets the auditing properties for an updated entity by populating the last
	 * updated by and last updated timestamp fields and setting the server name
	 * (if the entity supports the property).
	 */
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		_logger.trace("On flush dirty for entity: {}", entity);
		for (int i = 0; i < propertyNames.length; i++) {
			switch (propertyNames[i]) {
			case "lastUpdatedUserId":
			case "lastUpdatedBy":
				currentState[i] = Auditor.getName();
				break;
			case "lastUpdatedDateTime":
				currentState[i] = new Timestamp(System.currentTimeMillis());
				break;
			case "lastUpdated":
				currentState[i] = LocalDateTime.now();
				break;
			case "serverName":
				currentState[i] = RuntimeInfo.getServerName();
				break;
			default: // Do nothing for other fields
			}
		}
		return true; // Entity has been updated
	}

	/*
	 * Populates the last updated user and last updated timestamp of a retrieved
	 * audited entity with the create user and created timestamp if the
	 * retrieved values are null. This is a workaround for legacy UI code which
	 * requires a non-null last updated timestamp to prevent NPEs.
	 */
	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		_logger.trace("On load for entity ID: {}", id);
		boolean updated = false;
		for (int i = 0; i < propertyNames.length; i++) {
			switch (propertyNames[i]) {
			case "lastUpdatedUserId":
			case "lastUpdatedBy":
				if (state[i] == null) {
					state[i] = getCreateUser(propertyNames, state);
					updated = true;
				}
				break;
			case "lastUpdatedDateTime":
			case "lastUpdated":
				if (state[i] == null) {
					state[i] = getCreated(propertyNames, state);
					updated = true;
				}
				break;
			default: // Do nothing for other fields
			}
		}
		return updated; // Entity has been updated
	}

	/**
	 * Returns the create user in the specified {@code propertyNames} and
	 * {@code state}; method assumes the values are associated with an audited
	 * domain entity and will throw an {@link IllegalStateException} if the
	 * value is not found.
	 * 
	 * @param propertyNames
	 * @param state
	 * @return created by user
	 */
	private Object getCreateUser(final String[] propertyNames, final Object[] state) {
		for (int i = 0; i < propertyNames.length; i++) {
			switch (propertyNames[i]) {
			case "createdUserId":
			case "createdBy":
				return state[i];
			default: // Do nothing for other fields
			}
		}
		throw new IllegalStateException("Failed to find created by property for audited entity; properties: "
				+ Arrays.toString(propertyNames) + " values: " + Arrays.toString(state));
	}

	/**
	 * Returns the created timestamp in the specified {@code propertyNames} and
	 * {@code state}; method assumes the values are associated with an audited
	 * domain entity and will throw an {@link IllegalStateException} if the
	 * value is not found.
	 * 
	 * @param propertyNames
	 * @param state
	 * @return created date timestamp
	 */
	private Object getCreated(final String[] propertyNames, final Object[] state) {
		for (int i = 0; i < propertyNames.length; i++) {
			switch (propertyNames[i]) {
			case "createdDateTime":
			case "created":
				return state[i];
			default: // Do nothing for other fields
			}
		}
		throw new IllegalStateException("Failed to find created property for audited entity; properties: "
				+ Arrays.toString(propertyNames) + " values: " + Arrays.toString(state));
	}

}
