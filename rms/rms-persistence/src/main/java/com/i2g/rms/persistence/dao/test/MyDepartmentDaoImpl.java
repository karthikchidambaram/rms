package com.i2g.rms.persistence.dao.test;

import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.test.MyDepartment;
import com.i2g.rms.domain.model.test.MyEmployee;

/**
 * Implementation class for TestDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class MyDepartmentDaoImpl implements MyDepartmentDao {

	private final Logger _logger = LoggerFactory.getLogger(MyDepartmentDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;
	/** Hibernate session factory for executing Hibernate queries. */
	@Autowired
	private SessionFactory _sessionFactory;

	/**
	 * Creates a new instance of {@link MyDepartmentDaoImpl}.
	 */
	private MyDepartmentDaoImpl() {}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}	

	public SessionFactory getSessionFactory() {
		return _sessionFactory;
	}
	
	/**
	 * Returns the current Hibernate session for executing queries.
	 * 
	 * @return current session
	 */
	public final Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		_sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public MyDepartment getMyDeptByDeptNo(final Long deptNo) {
		
		/*CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<MyDepartment> criteriaQuery = criteriaBuilder.createQuery(MyDepartment.class);
		Root<MyDepartment> myDepartments = criteriaQuery.from(MyDepartment.class);
		criteriaQuery.select(myDepartments);
		criteriaQuery.where(criteriaBuilder.equal(myDepartments.get("deptNo"), deptNo));*/
		
		Criteria criteria = getSession().createCriteria(MyDepartment.class);
		criteria.add(Restrictions.eq("deptNo", Objects.requireNonNull(deptNo, "Department number cannot be null")));
		return (MyDepartment) criteria.uniqueResult();
		
	}

	// Method to return all departments
	@SuppressWarnings("unchecked")
	@Override
	public List<MyDepartment> getMyDepartments() {
		return (List<MyDepartment>) _hibernateTemplate.findByCriteria(DetachedCriteria.forClass(MyDepartment.class));
	}

	// Method to return all employees
	@SuppressWarnings("unchecked")
	@Override
	public List<MyEmployee> getMyEmployees() {
		return (List<MyEmployee>) _hibernateTemplate.findByCriteria(DetachedCriteria.forClass(MyEmployee.class));
	}

	// Method to create a new department in the table DEPT
	@Override
	public MyDepartment createMyDepartment(final Long deptNo, final String dname, final String loc) {
		final MyDepartment myDepartment = new MyDepartment(deptNo, dname, loc);
		_hibernateTemplate.save(myDepartment);
		return myDepartment;
	}

	@Override
	public MyDepartment updateMyDepartment(final MyDepartment myDepartment) {
		if (myDepartment == null) {
			throw new IllegalStateException("MyDepartment does not exist.");
		}
		// Begin transaction
		//_hibernateTemplate.getSessionFactory().getCurrentSession().getTransaction().begin();
		
		// Perform save or update
		_hibernateTemplate.saveOrUpdate(myDepartment);
		//_hibernateTemplate.flush();
		
		// Commit transaction
		//_hibernateTemplate.getSessionFactory().getCurrentSession().getTransaction().commit();
		return myDepartment;
	}

	@Override
	public void deleteMyDepartment(final MyDepartment myDepartment) {
		_hibernateTemplate.delete(myDepartment);
	}	
}
