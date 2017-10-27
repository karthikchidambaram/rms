package com.i2g.rms.persistence.dao;

import java.util.ArrayList;
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
import com.i2g.rms.domain.model.Building;
import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.Witness;
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
	public Address createAddress(final Address address) {
		validateObject(address);
		final Long id = save(address);
		if (id != null) {
			return get(id);	
		} else {
			return null;
		}
	}
	
	@Override
	public List<Address> createAddresses(final List<Address> addresses) {
		validateCollectionObject(addresses);
		List<Address> newAddresses = new ArrayList<Address>(0);
		for (Address address : addresses) {
			if (address != null) {
				final Long id = save(address);
				if (id != null) {
					newAddresses.add(get(id));
				}
			}
		}
		return newAddresses;
	}

	@Override
	public Address updateAddress(final Address address) {
		validateObject(address);
		final Long id = save(address);
		if (id != null) {
			return get(id);	
		} else {
			return null;
		}
	}

	@Override
	public List<Address> updateAddresses(final List<Address> addresses) {
		validateCollectionObject(addresses);
		List<Address> updatedAddresses = new ArrayList<Address>(0);
		for (Address address : addresses) {
			if (address != null) {
				final Long id = save(address);
				if (id != null) {
					updatedAddresses.add(get(id));
				}
			}
		}
		return updatedAddresses;
	}

	@Override
	public List<Address> get(final User user) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("user", Objects.requireNonNull(user, "User id (object) cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Address>) applySearch(criteria).list();
	}

	@Override
	public List<Address> get(final Building building) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("building", Objects.requireNonNull(building, "Building id (object) cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Address>) applySearch(criteria).list();
	}

	@Override
	public List<Address> get(final Witness witness) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("witness", Objects.requireNonNull(witness, "Witness id (object) cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Address>) applySearch(criteria).list();
	}

	@Override
	public List<Address> get(final InjuredPerson injuredPerson) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("injuredPerson", Objects.requireNonNull(injuredPerson, "Injured person id (object) cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Address>) applySearch(criteria).list();
	}

	@Override
	public List<Address> get(final Suspect suspect) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("suspect", Objects.requireNonNull(suspect, "Suspect id (object) cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Address>) applySearch(criteria).list();
	}

	@Override
	public List<Address> get(final CrimeSuspect crimeSuspect) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("crimeSuspect", Objects.requireNonNull(crimeSuspect, "Crime suspect id (object) cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Address>) applySearch(criteria).list();
	}
	
	@Override
	public void deleteAddress(final Address address) {
		validateObject(address);
		delete(address);
	}
	
	@Override
	public void deleteAddresses(final List<Address> addresses) {
		validateCollectionObject(addresses);
		for (Address address : addresses) {
			if (address != null) {
				delete(address);	
			}
		}
	}
}
