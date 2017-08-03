package com.i2g.rms.rest.service.incident;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.incident.SearchIncident;
import com.i2g.rms.rest.model.incident.SearchIncidentRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.service.incident.SearchIncidentService;

/**
 * Rest services for search incident.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class SearchIncidentRestServiceImpl extends AbstractRestService implements SearchIncidentRestService {

	private final Logger _logger = LoggerFactory.getLogger(SearchIncidentRestServiceImpl.class);

	@Autowired
	private SearchIncidentService _searchIncidentService;

	@Override
	@Transactional(readOnly = true)
	public List<SearchIncidentRO> get() {
		List<SearchIncident> searchIncidents = _searchIncidentService.get();
		List<SearchIncidentRO> searchIncidentROs = searchIncidents.isEmpty() ? Collections.emptyList()
				: _mapperService.map(searchIncidents, SearchIncidentRO.class);
		return searchIncidentROs;
	}
}
