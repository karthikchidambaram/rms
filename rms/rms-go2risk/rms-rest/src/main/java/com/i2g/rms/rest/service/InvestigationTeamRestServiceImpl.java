package com.i2g.rms.rest.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.InvestigationTeam;
import com.i2g.rms.rest.model.InvestigationTeamRO;
import com.i2g.rms.service.InvestigationTeamService;

/**
 * Rest services for Investigation Team rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class InvestigationTeamRestServiceImpl extends AbstractRestService implements InvestigationTeamRestService {

	private final Logger _logger = LoggerFactory.getLogger(InvestigationTeamRestServiceImpl.class);

	@Autowired
	private InvestigationTeamService _investigationTeamService;

	@Transactional(readOnly = true)
	public List<InvestigationTeamRO> getInvestigationTeams() {
		List<InvestigationTeam> investigationTeams = _investigationTeamService.getInvestigationTeams();
		List<InvestigationTeamRO> investigationTeamROs = investigationTeams.isEmpty() ? Collections.emptyList()
				: _mapperService.map(investigationTeams, InvestigationTeamRO.class);
		return investigationTeamROs;
	}
}
