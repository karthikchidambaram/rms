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
import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for WitnessDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class WitnessDaoImpl extends AbstractHibernateDao<Long, Witness> implements WitnessDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(WitnessDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link WitnessDaoImpl}.
	 */
	private WitnessDaoImpl() {
		super(Witness.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Witness> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Witness>) criteria.list();
	}
	
	@Override
	public Witness get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Witness id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Witness) criteria.uniqueResult();
	}

	@Override
	/**
	 * While creating new Witnesses, there will be no unique id representing the records uniquely.
	 * So a list will be used. But after saving the records the return collection can be a set.
	 */	
	public Set<Witness> createNewWitnesses(final List<Witness> witnesses) {
		validateCollectionObject(witnesses);
		final Set<Witness> newWitnesses = new HashSet<Witness>(0);
		for (Witness witness : witnesses) {
			final Long id = save(witness);
			if (id != null) {
				newWitnesses.add(get(id));
			}
		}
		return newWitnesses;
	}	
}
