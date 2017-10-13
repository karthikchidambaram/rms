package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Document;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.persistence.dao.DocumentDao;

/**
 * Back-end service for Document related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class DocumentServiceImpl extends AbstractService implements DocumentService {

	@Autowired
	private DocumentDao _documentDao;

	private DocumentServiceImpl() {
	}

	@Override
	public List<Document> get() {
		return _documentDao.get();
	}

	@Override
	public Document get(final long id) {
		return _documentDao.get(id);
	}

	@Override
	public List<Document> getDocumentsByIncident(final Incident incident) {
		return _documentDao.getDocumentsByIncident(incident);
	}

	@Override
	public List<Document> saveDocuments(final List<Document> documents) {
		return _documentDao.saveDocuments(documents);
	}

	@Override
	public Document saveDocument(final Document document) {
		return _documentDao.saveDocument(document);
	}

	@Override
	public void deleteDocument(final Document document) {
		_documentDao.deleteDocument(document);		
	}
	
	@Override
	public void deleteDocuments(final Set<Document> documents) {
		_documentDao.deleteDocuments(documents);		
	}
}
