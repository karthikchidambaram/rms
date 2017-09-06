package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.Accident;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for AccidentDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class AccidentDaoImpl extends AbstractHibernateDao<Long, Accident> implements AccidentDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(AccidentDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link AccidentDaoImpl}.
	 */
	private AccidentDaoImpl() {
		super(Accident.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all Accident details
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Accident> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Accident>) applySearch(criteria).list();
	}

	// Method to return Accident identified by id.
	@SuppressWarnings("deprecation")
	@Override
	public Accident get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Accident id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Accident) criteria.uniqueResult();
	}

	@Override
	public Accident create(final Accident accident) {
		validateObject(accident);
		Long id = save(accident);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
}
