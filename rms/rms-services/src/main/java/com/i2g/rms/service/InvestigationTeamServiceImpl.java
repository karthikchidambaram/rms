package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.InvestigationTeam;
import com.i2g.rms.persistence.dao.InvestigationTeamDao;

/**
 * Back-end service for InvestigationTeam related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class InvestigationTeamServiceImpl extends AbstractService implements InvestigationTeamService {

	@Autowired
	private InvestigationTeamDao _investigationTeamDao;

	private InvestigationTeamServiceImpl() {
	}

	@Override
	public List<InvestigationTeam> getInvestigationTeams() {
		return _investigationTeamDao.getInvestigationTeams();
	}
}
