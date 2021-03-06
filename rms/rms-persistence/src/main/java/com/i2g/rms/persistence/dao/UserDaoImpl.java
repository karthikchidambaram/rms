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

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for UserDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class UserDaoImpl extends AbstractHibernateDao<Long, User> implements UserDao {

	private final Logger _logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link UserDaoImpl}.
	 */
	private UserDaoImpl() {
		super(User.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all users
	@SuppressWarnings("deprecation")
	@Override
	public List<User> getUsers() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<User>) applySearch(criteria).list();
	}

	// Method to return user identified by given user login id.
	@SuppressWarnings("deprecation")
	@Override
	public User getUserByUserLoginId(final String loginId) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("loginId", Objects.requireNonNull(loginId, "User login id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (User) criteria.uniqueResult();
	}
	
	@Override
	public List<User> getSubordinates(final User manager) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("manager", Objects.requireNonNull(manager, "Manager cannot be null or empty.")));
		return (List<User>) applySearch(criteria).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<User>) applySearch(criteria).list();
	}

	@Override
	public User get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "User id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (User) criteria.uniqueResult();
	}

	@Override
	public User updateUser(final User user) {
		validateObject(user);
		final Long id = save(user);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}

	@Override
	public void deleteAddresses(final User user, final Set<Address> addresses) {		
		validateObject(user);
		validateCollectionObject(addresses);
		for (Address address : addresses) {
			if (address != null) {
				user.getAddresses().remove(address);
				save(user);
			}
		}		
	}
}
