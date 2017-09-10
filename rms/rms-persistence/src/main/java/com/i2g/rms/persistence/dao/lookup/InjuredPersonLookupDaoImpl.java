package com.i2g.rms.persistence.dao.lookup;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.lookup.InjuredPersonLookup;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

@Repository
public class InjuredPersonLookupDaoImpl extends AbstractHibernateDao<Long, InjuredPersonLookup> implements InjuredPersonLookupDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(InjuredPersonLookupDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	public InjuredPersonLookupDaoImpl() {
		super(InjuredPersonLookup.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InjuredPersonLookup> get() {
		return (List<InjuredPersonLookup>) applySearch(getSession().createCriteria(_modelType)).list();
	}
}
