package com.i2g.rms.persistence.dao.lookup;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.lookup.UserLookup;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

@Repository
public class UserLookupDaoImpl extends AbstractHibernateDao<Long, UserLookup> implements UserLookupDao {

	private final Logger _logger = LoggerFactory.getLogger(UserLookupDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	public UserLookupDaoImpl() {
		super(UserLookup.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List<UserLookup> get() {
		return (List<UserLookup>) applySearch(getSession().createCriteria(_modelType)).list();
	}
}
