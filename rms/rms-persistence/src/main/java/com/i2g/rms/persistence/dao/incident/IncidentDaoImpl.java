package com.i2g.rms.persistence.dao.incident;

import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

@Repository
public class IncidentDaoImpl extends AbstractHibernateDao<Long, Incident> implements IncidentDao {
	
	private final Logger _logger = LoggerFactory.getLogger(IncidentDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	protected IncidentDaoImpl() {
		super(Incident.class);		
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List<Incident> get() {
		return (List<Incident>) applySearch(getSession().createCriteria(_modelType)).list();
	}

	@Override
	public Incident getIncidentByUniqueIncidentId(final String  uniqueIncidentId) {
		return (Incident) getSession().createCriteria(_modelType).add(
				Restrictions.eq("uniqueIncidentId", Objects.requireNonNull(uniqueIncidentId, "Unique incident id cannot be null or empty.")))
				.uniqueResult();
	}
}
