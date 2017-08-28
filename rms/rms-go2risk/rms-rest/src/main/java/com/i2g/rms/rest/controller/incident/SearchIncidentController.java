package com.i2g.rms.rest.controller.incident;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.incident.SearchIncident;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.AbstractRestController;
import com.i2g.rms.rest.model.incident.SearchIncidentRO;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.incident.SearchIncidentRestService;

/**
 * Rest controller for all search incident related activities.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class SearchIncidentController extends AbstractRestController {

	@Autowired
	private SearchIncidentRestService _searchIncidentRestService;

	@RequestMapping(value = RequestMappingConstants.SEARCH_INCIDENT, method = RequestMethod.GET)
	@Searchable(sourceType = SearchIncidentRO.class, value = SearchIncident.class)
	public List<SearchIncidentRO> get() {
		return _searchIncidentRestService.get();
	}
	
}
