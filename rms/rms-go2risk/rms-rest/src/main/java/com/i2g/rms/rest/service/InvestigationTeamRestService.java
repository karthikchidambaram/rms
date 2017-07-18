package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.InvestigationTeamRO;

/**
 * Rest Service Interface for investigation team rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InvestigationTeamRestService {

	public List<InvestigationTeamRO> getInvestigationTeams();

}
