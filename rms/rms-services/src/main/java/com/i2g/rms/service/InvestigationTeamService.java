package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.InvestigationTeam;

/**
 * Back-end service for Investigation Team related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InvestigationTeamService {

	public List<InvestigationTeam> getInvestigationTeams();

}
