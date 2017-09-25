package com.i2g.rms.service.incident;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.incident.SearchIncident;
import com.i2g.rms.persistence.dao.incident.SearchIncidentDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for search incidents.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class SearchIncidentServiceImpl extends AbstractService implements SearchIncidentService {

	@Autowired
	private SearchIncidentDao _searchIncidentDao;

	private SearchIncidentServiceImpl() {
	}

	@Override
	public List<SearchIncident> get() {
		return _searchIncidentDao.get();
	}

	@Override
	public List<SearchIncident> get(final Set<String> loginIds, final boolean isAdmin) {
		return _searchIncidentDao.get(loginIds, isAdmin);
	}
}
