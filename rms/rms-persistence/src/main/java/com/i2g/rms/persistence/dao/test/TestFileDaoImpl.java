package com.i2g.rms.persistence.dao.test;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.test.TestFile;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for TestFileDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class TestFileDaoImpl extends AbstractHibernateDao<Long, TestFile> implements TestFileDao {

	private final Logger _logger = LoggerFactory.getLogger(TestFileDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link TestFileDaoImpl}.
	 */
	private TestFileDaoImpl() {
		super(TestFile.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestFile> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		return (List<TestFile>) criteria.list();
	}

	@Override
	public TestFile get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "File Id cannot be null or empty.")));
		return (TestFile) criteria.uniqueResult();
	}

	@Override
	public void saveFiles(final List<TestFile> files) {
		validateCollectionObject(files);
		for (TestFile testFile : files) {
			save(testFile);
		}	
	}
}
