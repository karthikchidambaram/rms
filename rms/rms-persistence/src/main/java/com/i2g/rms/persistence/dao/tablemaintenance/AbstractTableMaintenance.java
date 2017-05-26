package com.i2g.rms.persistence.dao.tablemaintenance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.i2g.rms.util.tablemaintenance.TableMaintenanceConstants;

public abstract class AbstractTableMaintenance {
	
	/** Hibernate session factory for executing Hibernate queries. */
	@Autowired
	protected SessionFactory _sessionFactory;
		
	
	protected AbstractTableMaintenance() {}
	
	/**
	 * Returns the current Hibernate session for executing queries.
	 * 
	 * @return current session
	 */
	protected final Session getSession() {
		return _sessionFactory.getCurrentSession();
	}
	
	protected void validateTableName(final String tableName) {
		if (tableName == null || tableName.trim().isEmpty()) {
			throw new IllegalArgumentException("Table name cannot be null or empty.");
		}
	}
	
	protected void validateOperation(final String operation) {
		if (operation == null || operation.trim().isEmpty()) {
			throw new IllegalArgumentException("Operation cannot be null or empty.");
		}
	}
	
	protected void validateCode(final String code) {
		if (code == null || code.trim().isEmpty()) {
			throw new IllegalArgumentException("Code cannot be null or empty.");
		}
	}
	
	protected void validateDescription(final String description) {
		if (description == null || description.trim().isEmpty()) {
			throw new IllegalArgumentException("Description cannot be null or empty.");
		}
	}
	
	protected String getSqlQueryName(final String tableName, final String operation) {
		return TableMaintenanceConstants.TABLE_MAINTENANCE + "." + tableName + "." + operation;
	}
	
	protected static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
}
