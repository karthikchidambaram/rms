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

import com.i2g.rms.domain.model.OfficeAddress;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for OfficeAddressDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class OfficeAddressDaoImpl extends AbstractHibernateDao<Long, OfficeAddress> implements OfficeAddressDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(OfficeAddressDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link OfficeAddressDaoImpl}.
	 */
	private OfficeAddressDaoImpl() {
		super(OfficeAddress.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all Office Address details
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<OfficeAddress> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<OfficeAddress>) applySearch(criteria).list();
	}

	// Method to return user identified by given user login id.
	@SuppressWarnings("deprecation")
	@Override
	public OfficeAddress get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Office address id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (OfficeAddress) criteria.uniqueResult();
	}

	@Override
	public OfficeAddress create(final OfficeAddress officeAddress) {
		validateObject(officeAddress);
		Long id = save(officeAddress);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
}
