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

import com.i2g.rms.domain.model.Claim;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.incident.Incident;
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
	
	@SuppressWarnings("deprecation")
	@Override
	public Claim get(final Incident incident) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("incident", Objects.requireNonNull(incident, "Incident Id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Claim) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Claim> get(final User claimHandler) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		criteria.add(Restrictions.eq("claimHandler", Objects.requireNonNull(claimHandler, "Claim handler cannot be null or empty.")));
		return (List<Claim>) applySearch(criteria).list();
	}

	@Override
	public Claim createClaim(final Claim claim) {
		validateObject(claim);
		final Long id = save(claim);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
	
	@Override
	public Claim updateClaim(final Claim claim) {
		validateObject(claim);
		final Long id = save(claim);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}

	@Override
	public void deleteClaim(final Claim claim) {
		validateObject(claim);
		super.delete(claim);		
	}	

	@Override
	public void deleteClaims(final Set<Claim> claims) {
		validateCollectionObject(claims);
		for (Claim claim : claims) {
			if (claim != null) {
				super.delete(claim);
			}
		}		
	}

	@Override
	public boolean isClaimHandlerAssigned(final long id) {
		if (get(id).getClaimHandler() != null) {
			return true;
		} else {
			return false;
		}
	}
}
