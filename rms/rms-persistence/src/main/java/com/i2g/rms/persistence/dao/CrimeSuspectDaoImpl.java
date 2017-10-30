package com.i2g.rms.persistence.dao;

import java.util.HashSet;
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

import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.domain.model.StatusFlag;
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
public class CrimeSuspectDaoImpl extends AbstractHibernateDao<Long, CrimeSuspect> implements CrimeSuspectDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(CrimeSuspectDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link CrimeSuspectDaoImpl}.
	 */
	private CrimeSuspectDaoImpl() {
		super(CrimeSuspect.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CrimeSuspect> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<CrimeSuspect>) criteria.list();
	}
	
	@Override
	public CrimeSuspect get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Crime suspect id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (CrimeSuspect) criteria.uniqueResult();
	}
	
	@Override
	public CrimeSuspect createNewCrimeSuspect(final CrimeSuspect crimeSuspect) {
		validateObject(crimeSuspect);
		final Long id = save(crimeSuspect);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}

	@Override
	public Set<CrimeSuspect> createNewCrimeSuspects(final Set<CrimeSuspect> crimeSuspects) {
		validateCollectionObject(crimeSuspects);
		final Set<CrimeSuspect> newCrimeSuspects = new HashSet<CrimeSuspect>(0);
		for (CrimeSuspect crimeSuspect : crimeSuspects) {
			if (crimeSuspect != null) {
				final Long id = save(crimeSuspect);
				if (id != null) {
					newCrimeSuspects.add(get(id));
				}
			}
		}
		return newCrimeSuspects;
	}	

	@Override
	public CrimeSuspect updateCrimeSuspect(final CrimeSuspect crimeSuspect) {
		validateObject(crimeSuspect);
		final Long id = save(crimeSuspect);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}

	@Override
	public Set<CrimeSuspect> updateCrimeSuspects(final Set<CrimeSuspect> crimeSuspects) {
		validateCollectionObject(crimeSuspects);
		final Set<CrimeSuspect> updatedCrimeSuspects = new HashSet<CrimeSuspect>(0);
		for (CrimeSuspect crimeSuspect : crimeSuspects) {
			if (crimeSuspect != null) {
				final Long id = save(crimeSuspect);
				if (id != null) {
					updatedCrimeSuspects.add(get(id));
				}
			}
		}
		return updatedCrimeSuspects;
	}

	@Override
	public void removeDistinguishingFeatureDetailsFromCrimeSuspect(final CrimeSuspect crimeSuspect, final Set<DistinguishingFeatureDetail> distinguishingFeatureDetails) {
		validateObject(crimeSuspect);
		validateCollectionObject(distinguishingFeatureDetails);
		for (DistinguishingFeatureDetail distinguishingFeatureDetail : distinguishingFeatureDetails) {
			if (distinguishingFeatureDetail != null) {
				crimeSuspect.getDistinguishingFeatureDetails().remove(distinguishingFeatureDetail);				
			}
		}
		save(crimeSuspect);		
	}	
}
