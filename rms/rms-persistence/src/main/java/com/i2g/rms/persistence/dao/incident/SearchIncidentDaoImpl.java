package com.i2g.rms.persistence.dao.incident;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.incident.SearchIncident;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

@Repository
public class SearchIncidentDaoImpl extends AbstractHibernateDao<Long, SearchIncident> implements SearchIncidentDao {

	private final Logger _logger = LoggerFactory.getLogger(SearchIncidentDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;
	
	public SearchIncidentDaoImpl() {
		super(SearchIncident.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List<SearchIncident> get() {
		return (List<SearchIncident>) applySearch(getSession().createCriteria(_modelType)).list();
	}
}
