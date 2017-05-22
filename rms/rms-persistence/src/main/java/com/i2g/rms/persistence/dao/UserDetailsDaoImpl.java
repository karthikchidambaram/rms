package com.i2g.rms.persistence.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.UserDetails;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for UserDetailsDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class UserDetailsDaoImpl extends AbstractHibernateDao<Long, UserDetails> implements UserDetailsDao {

	private final Logger _logger = LoggerFactory.getLogger(UserDetailsDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link UserDetailsDaoImpl}.
	 */
	private UserDetailsDaoImpl() {
		super(UserDetails.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all user details
	@SuppressWarnings("deprecation")
	@Override
	public List<UserDetails> getUserDetails() {
		return (List<UserDetails>) applySearch(getSession().createCriteria(_modelType)).list();
	}
}
