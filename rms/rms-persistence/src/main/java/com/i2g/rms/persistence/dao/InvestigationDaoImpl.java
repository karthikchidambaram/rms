package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.Investigation;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for InvestigationDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class InvestigationDaoImpl extends AbstractHibernateDao<Long, Investigation> implements InvestigationDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(InvestigationDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link InvestigationDaoImpl}.
	 */
	private InvestigationDaoImpl() {
		super(Investigation.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all Investigation details
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Investigation> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Investigation>) applySearch(criteria).list();
	}

	// Method to return Investigation identified by id.
	@SuppressWarnings("deprecation")
	@Override
	public Investigation get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Investigation id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Investigation) criteria.uniqueResult();
	}
	
	@Override
	public Investigation get(final Incident incident) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("incident", Objects.requireNonNull(incident, "Incident object cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Investigation) criteria.uniqueResult();
	}
	
	@Override
	public List<Investigation> get(final User investigator) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		criteria.add(Restrictions.eq("investigator", Objects.requireNonNull(investigator, "Investigator cannot be null or empty.")));
		return (List<Investigation>) applySearch(criteria).list();
	}

	@Override
	public Investigation createInvestigation(final Investigation investigation) {
		validateObject(investigation);
		final Long id = save(investigation);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
	
	@Override
	public Investigation updateInvestigation(final Investigation investigation) {
		validateObject(investigation);
		final Long id = save(investigation);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
	
	@Override
	public void deleteInvestigation(final Investigation investigation) {
		validateObject(investigation);
		super.delete(investigation);		
	}
	
	@Override
	public void deleteInvestigations(final Set<Investigation> investigations) {
		validateCollectionObject(investigations);
		for (Investigation investigation : investigations) {
			if (investigation != null) {
				super.delete(investigation);
			}
		}		
	}

	@Override
	public boolean isInvestigatorAssigned(final long id) {
		if (get(id).getInvestigator() != null) {
			return true;
		} else {
			return false;
		}
	}	
}
