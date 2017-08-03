package com.i2g.rms.rest.controller.incident;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.AbstractRestController;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.incident.IncidentRestService;

/**
 * Rest controller for all incident related activities.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class IncidentController extends AbstractRestController {
	
	@Autowired
	private IncidentRestService _incidentRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_INCIDENTS, method = RequestMethod.GET)
	@Searchable(sourceType = IncidentRO.class, value = Incident.class)
	public List<IncidentRO> get() {
		return _incidentRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_INCIDENT_BY_UNIQUE_INCIDENT_ID, method = RequestMethod.GET)
	public IncidentRO getIncidentByUniqueIncidentId(@PathVariable final String uniqueIncidentId) {
		return _incidentRestService.getIncidentByUniqueIncidentId(uniqueIncidentId);
	}
}
