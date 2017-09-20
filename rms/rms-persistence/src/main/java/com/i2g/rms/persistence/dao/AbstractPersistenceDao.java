package com.i2g.rms.persistence.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractPersistenceDao {

	/** Hibernate session factory for executing Hibernate queries. */
	@Autowired
	protected SessionFactory _sessionFactory;

	protected AbstractPersistenceDao() {
	}

	/**
	 * Returns the current Hibernate session for executing queries.
	 * 
	 * @return current session
	 */
	protected final Session getSession() {
		return _sessionFactory.getCurrentSession();
	}
}
