package com.i2g.rms.persistence.dao;

import java.util.ArrayList;
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

import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for SuspectDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class SuspectDaoImpl extends AbstractHibernateDao<Long, Suspect> implements SuspectDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(SuspectDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link SuspectDaoImpl}.
	 */
	private SuspectDaoImpl() {
		super(Suspect.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Suspect> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Suspect>) applySearch(criteria).list();
	}
	
	@Override
	public List<Suspect> get(final String uniqueIncidentId) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		criteria.createAlias("incidents", "incident"); 
		criteria.add(Restrictions.eq("incident.uniqueIncidentId", Objects.requireNonNull(uniqueIncidentId, "Unique incident id cannot be null or empty.")));		
		return (List<Suspect>) criteria.list();
	}
	
	@Override
	public List<Suspect> getSuspectsByIncidentId(final Long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		criteria.createAlias("incidents", "incident"); 
		criteria.add(Restrictions.eq("incident.id", Objects.requireNonNull(id, "Incident id cannot be null or empty.")));
		return (List<Suspect>) criteria.list();
	}
	
	@Override
	public Suspect get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Suspect id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Suspect) criteria.uniqueResult();
	}
	
	@Override
	public Suspect createNewSuspect(final Suspect suspect) {
		validateObject(suspect);
		final Long id = save(suspect);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}

	@Override
	/**
	 * While creating new suspects, there will be no unique id representing the records uniquely.
	 * So a list will be used. But after saving the records the return collection can be a set.
	 */	
	public List<Suspect> createNewSuspects(final Set<Suspect> suspects) {
		validateCollectionObject(suspects);
		final List<Suspect> newSuspects = new ArrayList<Suspect>(0);
		for (Suspect suspect : suspects) {
			if (suspect != null) {
				final Long id = save(suspect);
				if (id != null) {
					newSuspects.add(get(id));
				}
			}
		}
		return newSuspects;
	}

	@Override
	public Suspect updateSuspect(final Suspect suspect) {
		validateObject(suspect);
		final Long id = save(suspect);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
	
	@Override
	public List<Suspect> updateSuspects(final Set<Suspect> suspects) {
		validateCollectionObject(suspects);
		final List<Suspect> updatedSuspects = new ArrayList<Suspect>(0);
		for (Suspect suspect : suspects) {
			if (suspect != null) {
				final Long id = save(suspect);
				if (id != null) {
					updatedSuspects.add(get(id));
				}
			}
		}
		return updatedSuspects;
	}

	@Override
	public void removeDistinguishingFeatureDetailsFromSuspect(final Suspect suspect, final Set<DistinguishingFeatureDetail> distinguishingFeatureDetails) {
		validateObject(suspect);
		validateCollectionObject(distinguishingFeatureDetails);
		for (DistinguishingFeatureDetail distinguishingFeatureDetail : distinguishingFeatureDetails) {
			if (distinguishingFeatureDetail != null) {
				suspect.getDistinguishingFeatureDetails().remove(distinguishingFeatureDetail);				
			}
		}
		save(suspect);
	}				
}
