package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Document;
import com.i2g.rms.domain.model.incident.Incident;

/**
 * Back-end DAO for Document related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface DocumentDao {

	public List<Document> get();

	public Document get(final long id);

	public List<Document> getDocumentsByIncident(final Incident incident);
	
	public List<Document> saveDocuments(final List<Document> documents);
	
	public Document saveDocument(final Document document);
	
	public void deleteDocument(final Document document);

	public void deleteDocuments(final Set<Document> documents);
}
