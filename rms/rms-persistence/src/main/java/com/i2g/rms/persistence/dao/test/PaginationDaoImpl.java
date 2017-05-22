package com.i2g.rms.persistence.dao.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.test.Pagination;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for PaginationDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class PaginationDaoImpl extends AbstractHibernateDao<Long, Pagination> implements PaginationDao {
	
	@Autowired
	private HibernateTemplate _hibernateTemplate;
	
	/**
	 * Creates a new instance of {@link PaginationDaoImpl}.
	 */
	private PaginationDaoImpl() {
		super(Pagination.class);
	}

	// Method to test pagination example
	@Override
	public List<Pagination> getPaginationRecords() {
		return (List<Pagination>) applySearch(getSession().createCriteria(_modelType)).list();
	}
}
