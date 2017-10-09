package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.DocumentView;

/**
 * Service interface for all Document view related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface DocumentViewService {
	
	public List<DocumentView> get();

	public DocumentView get(final long id);

	public List<DocumentView> getDocumentsByIncidentId(final Long incidentId);
	
}
