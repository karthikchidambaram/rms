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

import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.Suspect;
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
	/**
	 * While creating new suspects, there will be no unique id representing the records uniquely.
	 * So a list will be used. But after saving the records the return collection can be a set.
	 */	
	public Set<Suspect> createNewSuspects(final Set<Suspect> suspects) {
		validateCollectionObject(suspects);
		final Set<Suspect> newSuspects = new HashSet<Suspect>(0);
		for (Suspect suspect : suspects) {
			final Long id = save(suspect);
			if (id != null) {
				newSuspects.add(get(id));
			}
		}
		return newSuspects;
	}	
}
