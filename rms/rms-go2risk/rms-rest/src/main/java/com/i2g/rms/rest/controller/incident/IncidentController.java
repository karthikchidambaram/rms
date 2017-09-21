package com.i2g.rms.rest.controller.incident;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.AbstractRestController;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.incident.AccidentDetailRO;
import com.i2g.rms.rest.model.incident.AssetDetailRO;
import com.i2g.rms.rest.model.incident.ClaimDetailRO;
import com.i2g.rms.rest.model.incident.CrimeDetailRO;
import com.i2g.rms.rest.model.incident.IncidentDetailRO;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.incident.LogIncidentRO;
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
	
	@RequestMapping(value = RequestMappingConstants.ADD_INCIDENT, method = RequestMethod.GET)
	public UserRO addIncident() {
		return _incidentRestService.addIncident();
	}
	
	@RequestMapping(value = RequestMappingConstants.LOG_INCIDENT, method = RequestMethod.POST)
	public IncidentRO logIncident(final @Valid @RequestBody LogIncidentRO logIncidentRO) {
		return _incidentRestService.logIncident(logIncidentRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_INCIDENT_DETAILS, method = RequestMethod.POST)
	public IncidentRO addIncidentDetail(final @Valid @RequestBody IncidentDetailRO incidentDetailRO) {
		return _incidentRestService.addIncidentDetail(incidentDetailRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_ACCIDENT_DETAILS, method = RequestMethod.POST)
	public IncidentRO addAccidentDetail(final @Valid @RequestBody AccidentDetailRO accidentDetailRO) {
		return _incidentRestService.addAccidentDetail(accidentDetailRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_ASSET_DETAILS, method = RequestMethod.POST)
	public IncidentRO addAssetDetail(final @Valid @RequestBody AssetDetailRO assetDetailRO) {
		return _incidentRestService.addAssetDetail(assetDetailRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_CRIME_DETAILS, method = RequestMethod.POST)
	public IncidentRO addCrimeDetail(final @Valid @RequestBody CrimeDetailRO crimeDetailRO) {
		return _incidentRestService.addCrimeDetail(crimeDetailRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_CLAIM_DETAILS, method = RequestMethod.POST)
	public IncidentRO addClaimDetail(final @Valid @RequestBody ClaimDetailRO claimDetailRO) {
		return _incidentRestService.addClaimDetail(claimDetailRO);
	}
}
