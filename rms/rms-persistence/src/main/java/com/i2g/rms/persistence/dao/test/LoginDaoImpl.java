package com.i2g.rms.persistence.dao.test;

import java.util.Objects;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for GroupDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class LoginDaoImpl extends AbstractHibernateDao<Long, User> implements LoginDao {

	private final Logger _logger = LoggerFactory.getLogger(LoginDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link LoginDaoImpl}.
	 */
	private LoginDaoImpl() {
		super(User.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all groups
	@SuppressWarnings("deprecation")
	@Override
	public User doLogin(final String loginId, final String password) {
		return (User) getSession().createCriteria(_modelType)
				.add(Restrictions.eq("loginId", Objects.requireNonNull(loginId, "User login id cannot be null or empty.")))
				.add(Restrictions.eq("password", Objects.requireNonNull(password, "Password cannot be null or empty.")))
				.uniqueResult();
	}
}
