package com.i2g.rms.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Implementation class for UniqueIncidentIDGeneratorDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class UniqueIncidentIDGeneratorDaoImpl extends AbstractPersistenceDao implements UniqueIncidentIDGeneratorDao {

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link UniqueIncidentIDGeneratorDaoImpl}.
	 */
	private UniqueIncidentIDGeneratorDaoImpl() {
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@Override
	public Long getUniqueIncidentID() {
		return 0l;
	}
}
