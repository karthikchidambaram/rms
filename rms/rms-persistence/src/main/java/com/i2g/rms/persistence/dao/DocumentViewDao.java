package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.DocumentView;

/**
 * Back-end DAO for Document view related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface DocumentViewDao {

	public List<DocumentView> get();

	public DocumentView get(final long id);

	public List<DocumentView> getDocumentsByIncidentId(final Long incidentId);
	
}
