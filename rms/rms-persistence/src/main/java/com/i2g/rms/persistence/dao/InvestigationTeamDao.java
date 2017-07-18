package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.InvestigationTeam;

/**
 * Back-end DAO for investigation team related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InvestigationTeamDao {

	public List<InvestigationTeam> getInvestigationTeams();

}
