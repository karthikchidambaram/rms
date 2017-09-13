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

import com.i2g.rms.domain.model.Crime;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for CrimeDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class CrimeDaoImpl extends AbstractHibernateDao<Long, Crime> implements CrimeDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(CrimeDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link CrimeDaoImpl}.
	 */
	private CrimeDaoImpl() {
		super(Crime.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all Asset details
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Crime> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Crime>) applySearch(criteria).list();
	}

	// Method to return Asset identified by id.
	@SuppressWarnings("deprecation")
	@Override
	public Crime get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Crime ID cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Crime) criteria.uniqueResult();
	}

	@Override
	public Crime createCrime(final Crime crime) {
		validateObject(crime);
		Long id = save(crime);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
	
	@Override
	public Crime updateCrime(final Crime crime) {
		validateObject(crime);
		Long id = save(crime);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
}
