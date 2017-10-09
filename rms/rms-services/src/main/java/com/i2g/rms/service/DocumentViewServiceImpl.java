package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.DocumentView;
import com.i2g.rms.persistence.dao.DocumentViewDao;

/**
 * Back-end service for DocumentView related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class DocumentViewServiceImpl extends AbstractService implements DocumentViewService {

	@Autowired
	private DocumentViewDao _documentViewDao;

	private DocumentViewServiceImpl() {
	}

	@Override
	public List<DocumentView> get() {
		return _documentViewDao.get();
	}

	@Override
	public DocumentView get(final long id) {
		return _documentViewDao.get(id);
	}

	@Override
	public List<DocumentView> getDocumentsByIncidentId(final Long incidentId) {
		return _documentViewDao.getDocumentsByIncidentId(incidentId);
	}	
}
