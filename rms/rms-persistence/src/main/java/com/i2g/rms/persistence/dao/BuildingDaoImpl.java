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
import com.i2g.rms.domain.model.Building;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for BuildingDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class BuildingDaoImpl extends AbstractHibernateDao<Long, Building> implements BuildingDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(BuildingDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link BuildingDaoImpl}.
	 */
	private BuildingDaoImpl() {
		super(Building.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all Building details
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Building> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Building>) applySearch(criteria).list();
	}

	// Method to return Building identified by id.
	@SuppressWarnings("deprecation")
	@Override
	public Building get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Building ID cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Building) criteria.uniqueResult();
	}

	@Override
	public Building create(final Building building) {
		validateObject(building);
		final Long id = save(building);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
}
