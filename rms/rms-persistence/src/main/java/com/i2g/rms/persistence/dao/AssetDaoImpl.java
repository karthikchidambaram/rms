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

import com.i2g.rms.domain.model.Asset;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for AccidentDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class AssetDaoImpl extends AbstractHibernateDao<Long, Asset> implements AssetDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(AssetDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link AssetDaoImpl}.
	 */
	private AssetDaoImpl() {
		super(Asset.class);
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
	public List<Asset> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Asset>) applySearch(criteria).list();
	}

	// Method to return Asset identified by id.
	@SuppressWarnings("deprecation")
	@Override
	public Asset get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Asset ID cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Asset) criteria.uniqueResult();
	}
	
	@Override
	public Asset get(final Incident incident) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("incident", Objects.requireNonNull(incident, "Incident ID cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Asset) criteria.uniqueResult();
	}

	@Override
	public Asset createAsset(final Asset asset) {
		validateObject(asset);
		final Long id = save(asset);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}

	@Override
	public Asset updateAsset(Asset asset) {
		validateObject(asset);
		final Long id = save(asset);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}	
}
