package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		return (List<User>) applySearch(getSession().createCriteria(_modelType)).list();
	}

	// Method to return user identified by given user login id.
	@SuppressWarnings("deprecation")
	@Override
	public User getUserByUserLoginId(final String loginId) {
		return (User) getSession().createCriteria(_modelType).add(
				Restrictions.eq("loginId", Objects.requireNonNull(loginId, "User login id cannot be null or empty.")))
				.uniqueResult();
	}
}
