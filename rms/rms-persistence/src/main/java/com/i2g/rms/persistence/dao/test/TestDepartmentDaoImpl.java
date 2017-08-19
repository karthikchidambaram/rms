package com.i2g.rms.persistence.dao.test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.test.TestDepartment;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for TestDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class TestDepartmentDaoImpl extends AbstractHibernateDao<Long, TestDepartment> implements TestDepartmentDao {

	private final Logger _logger = LoggerFactory.getLogger(TestDepartmentDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link TestDepartmentDaoImpl}.
	 */
	private TestDepartmentDaoImpl() {
		super(TestDepartment.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@Override
	public String greetCustomer() {
		return "Hello and welcome to RMS!";
	}

	// Method to return one department by id
	@Override
	public TestDepartment getDeptByDeptNo(final Long deptNo) {
		return (TestDepartment) applySearch(getSession().createCriteria(_modelType)
				.add(Restrictions.eq("deptNo", Objects.requireNonNull(deptNo, "Department number cannot be null"))))
						.uniqueResult();
	}

	// Method to return all departments
	@Override
	public List<TestDepartment> getAllDepartments() {
		// return (List<TestDepartment>)
		// hibernateTemplate.findByCriteria(DetachedCriteria.forClass(TestDepartment.class));
		return (List<TestDepartment>) applySearch(getSession().createCriteria(_modelType), Order.asc("deptNo").ignoreCase())
				.list();
	}

	// Method to create a new department in the table RMS_TEST_DEPT
	@Override
	public TestDepartment createDepartment(final Long deptNo, final String dname, final String loc) {
		final TestDepartment department = new TestDepartment.Builder().setDeptNo(deptNo).setDname(dname).setLoc(loc).build();
		LocalDateTime now = getCurrentTimestamp();
		department.setCreated(now);
		department.setCreatedBy("ADMIN");
		department.setLastUpdated(now);
		department.setLastUpdatedBy("ADMIN");
		save(department);
		return department;
	}

	@Override
	public TestDepartment updateDepartment(final TestDepartment department) {
		if (department == null) {
			throw new IllegalStateException("Department does not exist.");
		}
		LocalDateTime now = getCurrentTimestamp();
		department.setLastUpdated(now);
		department.setLastUpdatedBy("ADMIN");
		update(department);
		return department;
	}

	@Override
	public void deleteDepartment(final TestDepartment department) {
		super.delete(department);
	}
}
