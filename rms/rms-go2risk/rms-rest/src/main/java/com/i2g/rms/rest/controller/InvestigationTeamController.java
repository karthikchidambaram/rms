package com.i2g.rms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.InvestigationTeam;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.InvestigationTeamRO;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.InvestigationTeamRestService;

/**
 * Rest controller for InvestigationTeam read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class InvestigationTeamController extends AbstractRestController {
	@Autowired
	private InvestigationTeamRestService _investigationTeamRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ALL_INVESTIGATION_TEAMS, method = RequestMethod.GET)
	@Searchable(sourceType = InvestigationTeamRO.class, value = InvestigationTeam.class)
	public List<InvestigationTeamRO> getInvestigationTeams() {
		return _investigationTeamRestService.getInvestigationTeams();
	}
}
