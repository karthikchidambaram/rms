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

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for AddressDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class AddressDaoImpl extends AbstractHibernateDao<Long, Address> implements AddressDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(AddressDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link AddressDaoImpl}.
	 */
	private AddressDaoImpl() {
		super(Address.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all address details
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Address> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Address>) applySearch(criteria).list();
	}

	// Method to return user identified by given user login id.
	@SuppressWarnings("deprecation")
	@Override
	public Address get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);		
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Address id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Address) criteria.uniqueResult();
	}

	@Override
	public Address create(final Address address) {
		validateObject(address);
		Long id = save(address);
		if (id != null) {
			return get(id);	
		} else {
			return null;
		}
	}
}
