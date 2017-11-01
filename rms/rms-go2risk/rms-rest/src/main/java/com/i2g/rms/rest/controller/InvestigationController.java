package com.i2g.rms.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.Investigation;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.InvestigationRO;
import com.i2g.rms.rest.model.wrapper.InvestigationWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.InvestigationRestService;

/**
 * Rest controller for investigation read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class InvestigationController extends AbstractRestController {
	
	@Autowired
	private InvestigationRestService _investigationRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ALL_INVESTIGATIONS, method = RequestMethod.GET)
	@Searchable(sourceType = InvestigationRO.class, value = Investigation.class)
	public List<InvestigationRO> get() {
		return _investigationRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_INVESTIGATIONS_ASSIGNED_TO_INVESTIGATOR_LOGIN_ID, method = RequestMethod.GET)
	@Searchable(sourceType = InvestigationRO.class, value = Investigation.class)
	public List<InvestigationRO> get(@PathVariable final String investigatorLoginId) {
		return _investigationRestService.get(investigatorLoginId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_INVESTIGATION_BY_INVESTIGATION_ID, method = RequestMethod.GET)
	public InvestigationRO get(@PathVariable final Long investigationId) {
		return _investigationRestService.get(investigationId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_INVESTIGATION_BY_INCIDENT_ID, method = RequestMethod.GET)
	public InvestigationRO getInvestigationByIncidentId(@PathVariable final Long incidentId) {
		return _investigationRestService.getInvestigationByIncidentId(incidentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_INVESTIGATION_BY_UNIQUE_INCIDENT_ID, method = RequestMethod.GET)
	public InvestigationRO getInvestigationByUniqueIncidentId(@PathVariable final String uniqueIncidentId) {
		return _investigationRestService.getInvestigationByUniqueIncidentId(uniqueIncidentId);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_INVESTIGATION, method = RequestMethod.POST)
	public InvestigationRO createInvestigation(@Valid @RequestBody final InvestigationRO investigationRO) {
		return _investigationRestService.createInvestigation(investigationRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_INVESTIGATION, method = RequestMethod.PUT)
	public InvestigationRO updateInvestigation(@Valid @RequestBody final InvestigationRO investigationRO) {
		return _investigationRestService.updateInvestigation(investigationRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_OR_UPDATE_INVESTIGATION, method = RequestMethod.PUT)
	public InvestigationRO addOrUpdateInvestigation(@Valid @RequestBody final InvestigationRO investigationRO) {
		return _investigationRestService.addOrUpdateInvestigation(investigationRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.DELETE_INVESTIGATION, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInvestigation(@PathVariable final Long investigationId) {
		_investigationRestService.deleteInvestigation(investigationId);
	}
	
	@RequestMapping(value = RequestMappingConstants.DELETE_INVESTIGATIONS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInvestigations(@Valid @RequestBody final InvestigationWrapper investigationWrapper) {
		_investigationRestService.deleteInvestigations(investigationWrapper);
	}
}
