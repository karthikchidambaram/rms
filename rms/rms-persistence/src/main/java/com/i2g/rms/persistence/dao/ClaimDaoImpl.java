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

import com.i2g.rms.domain.model.Claim;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for ClaimDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class ClaimDaoImpl extends AbstractHibernateDao<Long, Claim> implements ClaimDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(ClaimDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link ClaimDaoImpl}.
	 */
	private ClaimDaoImpl() {
		super(Claim.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all Claim details
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Claim> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Claim>) applySearch(criteria).list();
	}

	// Method to return Claim identified by id.
	@SuppressWarnings("deprecation")
	@Override
	public Claim get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Claim id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Claim) criteria.uniqueResult();
	}

	@Override
	public Claim create(final Claim claim) {
		validateObject(claim);
		Long id = save(claim);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
}
