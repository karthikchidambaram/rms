package com.i2g.rms.persistence.dao;

import java.util.ArrayList;
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

import com.i2g.rms.domain.model.Document;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for DocumentDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class DocumentDaoImpl extends AbstractHibernateDao<Long, Document> implements DocumentDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(DocumentDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;	

	/**
	 * Creates a new instance of {@link DocumentDaoImpl}.
	 */
	private DocumentDaoImpl() {
		super(Document.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Document>) criteria.list();
	}

	@Override
	public Document get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Document id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (Document) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> getDocumentsByIncident(final Incident incident) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("incident", Objects.requireNonNull(incident, "Incident cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<Document>) criteria.list();
	}

	@Override
	public List<Document> saveDocuments(final List<Document> documents) {
		validateCollectionObject(documents);
		List<Document> newDocuments = new ArrayList<Document>(0);
		for (Document document : documents) {
			if (document != null) {
				final Long id = save(document);
				newDocuments.add(get(id));
			}
		}
		return newDocuments;
	}

	@Override
	public Document saveDocument(final Document document) {
		validateObject(document);
		final Long id = save(document);
		return get(id);
	}
	
	@Override
	public void deleteDocument(final Document document) {
		if (document != null) {
			delete(document);
		}
	}
	
	@Override
	public void deleteDocuments(final Set<Document> documents) {
		if (documents != null && !documents.isEmpty()) {
			for (Document document : documents) {
				if (document != null) {
					deleteDocument(document);
				}
			}
		}
	}
}
