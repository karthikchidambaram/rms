package com.i2g.rms.persistence.dao.lookup;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.Manager;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

@Repository
public class ManagerLookupDaoImpl extends AbstractHibernateDao<Long, Manager> implements ManagerLookupDao {

	private final Logger _logger = LoggerFactory.getLogger(ManagerLookupDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	public ManagerLookupDaoImpl() {
		super(Manager.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List<Manager> get() {
		return (List<Manager>) applySearch(getSession().createCriteria(_modelType)).list();
	}
}
