package com.i2g.rms.persistence.dao.incident;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

@Repository
public class IncidentDaoImpl extends AbstractHibernateDao<Long, Incident> implements IncidentDao {
	
	@SuppressWarnings("unused")
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Incident> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Incident>) criteria.list();
	}
	
	@Override
	public Incident get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Incident id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Incident) criteria.uniqueResult();
	}

	@Override
	public Incident getIncidentByUniqueIncidentId(final String  uniqueIncidentId) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("uniqueIncidentId", Objects.requireNonNull(uniqueIncidentId, "Unique incident id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Incident) criteria.uniqueResult();
	}

	@Override
	public Incident logIncident(final Incident incident) {
		//Validate the object before save.
		validateObject(incident);
		Long id = save(incident);
		if (id != null) {
			return get(id);	
		} else {
			return null;
		}
	}
	
	@Override
	public Incident updateIncident(final Incident incident) {
		//Validate the object before save.
		validateObject(incident);
		Long id = save(incident);
		if (id != null) {
			return get(id);	
		} else {
			return null;
		}
	}		
}
